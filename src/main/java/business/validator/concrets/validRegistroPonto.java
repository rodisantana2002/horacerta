/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.validator.concrets;

import business.core.Ibusiness;
import business.factory.businessFactory;
import business.validator.abstracts.validGeneric;
import helpers.excecoes.excMessages;
import helpers.formatacao.clsTrataDatas;
import java.util.function.Predicate;
import model.entity.Frequencia;
import model.entity.LancamentoPonto;
import model.entity.PeriodoSemPonto;
import model.entity.RegistroPonto;

/**
 *
 * @author Rodolfo
 */
public class validRegistroPonto extends validGeneric<RegistroPonto> {

    private final clsTrataDatas trataDatas;

    public validRegistroPonto() {
        super();
        trataDatas = new clsTrataDatas();
    }

    public void validarDatas(RegistroPonto entity) {
        if (!trataDatas.isDate(entity.getDataRegistro())) {
            getLstMsg().add(excMessages.STR_DATA_INVALIDA + " - (" + "Data Registro" + ")");
        }
        if (trataDatas.isDateMaior(trataDatas.parseDataBraIso(entity.getDataRegistro()),
                trataDatas.parseDataBraIso(trataDatas.getDataAtual()))) {
            getLstMsg().add(excMessages.STR_DATA_REGISTRO_MAIOR_DATA_ATUAL);
        }
    }

    public void validarCamposObrigatorios(RegistroPonto entity) {
        if (entity.getId() != null && entity.getId() == 0) {
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Identificador" + ")");
        }
        if (entity.getDataRegistro().toString().trim().isEmpty() || entity.getDataRegistro() == null) {
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Data de Registro" + ")");
        }
        if (entity.getFrequencia().getId() == null) {
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Frequência" + ")");
        }
        if (entity.getFrequencia().getId() == 0) {
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Frequência" + ")");
        }
        if (entity.getPessoa().getId() == null) {
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Colaborador" + ")");
        }
        if (entity.getPessoa().getId() == 0) {
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Colaborador" + ")");
        }
    }

    public void validarRegistroPontoCadastrado(RegistroPonto entity) {
        Ibusiness<RegistroPonto> ibusiness = new businessFactory<RegistroPonto>(entity).getBusiness();
        Predicate<RegistroPonto> predID = p -> p.getId().equals(entity.getId());
        Predicate<RegistroPonto> predPeriodo = p -> (p.getDataRegistro().equalsIgnoreCase(entity.getDataRegistro()))
                && (p.getFrequencia().getId().equals(entity.getFrequencia().getId()))
                && (p.getPessoa().getId().equals(entity.getPessoa().getId()));

        if (ibusiness.listarByFilter(entity, predID).size() > 0) {
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE);
        }
        if (ibusiness.listarByFilter(entity, predPeriodo).size() > 0) {
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE + " - (A Data de Registro já foi utilizada para este Colaborador nesta Frequência)");
        }
    }

    public void validarRegistroPontoNaoCadastrado(RegistroPonto entity) {
        Ibusiness<RegistroPonto> ibusiness = new businessFactory<RegistroPonto>(entity).getBusiness();
        Predicate<RegistroPonto> predID = p -> p.getId().equals(entity.getId());

        if (ibusiness.listarByFilter(entity, predID).isEmpty()) {
            getLstMsg().add(excMessages.STR_REG_NAO_EXISTE);
        }
    }

    public void validarIsDataSemPonto(RegistroPonto entity) {
        Ibusiness<PeriodoSemPonto> ibusiness = new businessFactory<PeriodoSemPonto>(new PeriodoSemPonto()).getBusiness();
        Predicate<PeriodoSemPonto> predDataRegistro = p -> p.getData().equalsIgnoreCase(entity.getDataRegistro()) && p.getPessoa().getId() == entity.getPessoa().getId();

        if (ibusiness.listarByFilter(new PeriodoSemPonto(), predDataRegistro).size() > 0) {
            getLstMsg().add(excMessages.STR_DATA_REGISTRO_IS_DATA_SEMPONTO + " (" + entity.getDataRegistro() + ")");
        }
    }

    public void validarIsIntervaloFrequencia(RegistroPonto entity) {
        Ibusiness<Frequencia> ibusiness = new businessFactory<Frequencia>(new Frequencia()).getBusiness();

        Predicate<Frequencia> predPeriodo = p -> (( //data de registro dentro do periodo informado
                (((trataDatas.isDateMenor(trataDatas.parseDataBraIso(p.getDataInicio()), trataDatas.parseDataBraIso(entity.getDataRegistro())))
                || p.getDataInicio().equalsIgnoreCase(entity.getDataRegistro()))
                && ((trataDatas.isDateMaior(trataDatas.parseDataBraIso(p.getDataTermino()), trataDatas.parseDataBraIso(entity.getDataRegistro())))
                || p.getDataTermino().equalsIgnoreCase(entity.getDataRegistro()))))
                && (p.getPessoa().getId().equals(entity.getPessoa().getId()))
                && (p.getId().equals(entity.getFrequencia().getId())));

        if (ibusiness.listarByFilter(new Frequencia(), predPeriodo).isEmpty()) {
            getLstMsg().add(excMessages.STR_DATA_REGISTRO_IS_FORA_FREQUENCIA + " (" + entity.getDataRegistro() + ")");
        }
    }

    public void validarLancamentoPontoCadastrado(RegistroPonto entity) {
        if (!entity.getLancamentoPonto().isEmpty()) {
            for (LancamentoPonto lancamentoPonto : entity.getLancamentoPonto()) {
                if (lancamentoPonto.getId() == null) {
                    Ibusiness<LancamentoPonto> ibusiness = new businessFactory<LancamentoPonto>(lancamentoPonto).getBusiness();
                    Predicate<LancamentoPonto> predHoraLancamento = p -> (p.getHoraLancamento().equals(lancamentoPonto.getHoraLancamento()))
                            && (p.getRegistroPonto().getId() == entity.getId());

                    if (ibusiness.listarByFilter(lancamentoPonto, predHoraLancamento).size() > 0) {
                        getLstMsg().add(excMessages.STR_REG_JA_EXISTE + " - (O lançamento já foi informado para o Registro de Ponto)");
                    }
                }
            }
        }
    }
}
