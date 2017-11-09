/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlls.controll.concrets;

import business.core.Ibusiness;
import business.core.Ivalidator;
import business.factory.businessFactory;
import business.factory.validatorFactory;
import controlls.core.Icontroll;
import helpers.excecoes.excMessages;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import model.entity.Usuario;

/**
 *
 * @author Rodolfo
 */
public class ctrlUsuario implements Icontroll<Usuario> {

    private Ivalidator<Usuario> ivalidator;
    private List<String> msgs, regras;
    private Ibusiness ibusiness;

    public ctrlUsuario() {
        ibusiness = new businessFactory<Usuario>(new Usuario()).getBusiness();
        ivalidator = new validatorFactory<Usuario>(new Usuario()).getValidator();
        msgs = new ArrayList<String>();
        regras = new ArrayList<String>();
    }

    public List<String> autenticarUsuario(Usuario entity) {
        regras.add("validarAutenticacaoUsuario");
        return ivalidator.validarRegras(entity, regras);
    }

    public List<String> autenticarToken(Usuario entity) {
        regras.add("validarTokenUsuario");
        return ivalidator.validarRegras(entity, regras);
    }

    public Usuario atualizarToken(Usuario entity) {
        Random random = new SecureRandom();
        String token = new BigInteger(130, random).toString(32);
        Predicate<Usuario> predEMail = p -> p.getPessoa().getEmail().equalsIgnoreCase(entity.getPessoa().getEmail());

        List<Usuario> lstUsuario = obterByFilter(entity, predEMail);
        Usuario usuario = lstUsuario.get(0);
        usuario.setToken(token);
        salvar(usuario);
        usuario.setSenha("");
        return usuario;
    }

    public void efetuarLogout(Usuario entity) {
        Predicate<Usuario> predEMail = p -> p.getPessoa().getEmail().equalsIgnoreCase(entity.getPessoa().getEmail());

        List<Usuario> lstUsuario = obterByFilter(entity, predEMail);
        Usuario usuario = lstUsuario.get(0);
        usuario.setToken(null);
        salvar(usuario);
    }

    @Override
    public List<String> salvar(Usuario entity) {
        msgs = validar(entity);
        if (msgs.isEmpty()) {
            if (ibusiness.salvar(entity) != null) {
                msgs.add(excMessages.STR_OPERACAO_SUCESSO);
                return msgs;
            }
        }
        msgs.add(excMessages.STR_OPERACAO_INSUCESSO);
        return msgs;
    }

    @Override
    public List<String> deletar(Usuario entity) {
        msgs = validarDelete(entity);
        if (msgs.isEmpty()) {
            if (ibusiness.deletar(entity)) {
                msgs.add(excMessages.STR_OPERACAO_SUCESSO);
                return msgs;
            }
        }
        msgs.add(excMessages.STR_OPERACAO_INSUCESSO);
        return msgs;
    }

    @Override
    public Usuario obter(Integer id) {
        Usuario u = new Usuario();
        u.setId(id);
        return (Usuario) ibusiness.consultar(u);
    }

    @Override
    public List<Usuario> obterTodos() {
        return ibusiness.listarAll(new Usuario());
    }

    @Override
    public List<Usuario> obterByFilter(Usuario entity, Predicate<Usuario> predicate) {
        return ibusiness.listarByFilter(entity, predicate);
    }

    private List<String> validarDelete(Usuario entity) {
        return new ArrayList<String>();
    }

    private List<String> validar(Usuario entity) {
        return new ArrayList<String>();
    }
}
