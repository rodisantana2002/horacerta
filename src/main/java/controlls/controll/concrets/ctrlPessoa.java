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
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import model.entity.Pessoa;

/**
 *
 * @author Rodolfo
 */
public class ctrlPessoa implements Icontroll<Pessoa>{
    private Ibusiness ibusiness;
    private Ivalidator<Pessoa> ivalidator;
    private List<String> msgs, regras;
    
    public ctrlPessoa(){
        ibusiness = new businessFactory<Pessoa>(new Pessoa()).getBusiness();
        ivalidator = new validatorFactory<Pessoa>(new Pessoa()).getValidator();
        msgs = new ArrayList<String>();
        regras = new ArrayList<String>();
    }
   
    public List<String> salvar(Pessoa entity) { 
        msgs = validar(entity);        
        if (msgs.isEmpty()){            
            if (ibusiness.salvar(entity)!=null){
                msgs.add(excMessages.STR_OPERACAO_SUCESSO);            
                return msgs;
            }
        }
        msgs.add(excMessages.STR_OPERACAO_INSUCESSO);
        return msgs;
    }

    ///metodo personalizado para poder salvar e recuperar a pessoa criada no momento 
    ///da criação de um novo usuário ---utilizar sommente para essa situação
    public Pessoa salvarByUser(Pessoa entity) { 
        Predicate<Pessoa> predEMail = p -> p.getEmail().equalsIgnoreCase(entity.getEmail());
        List<Pessoa> lstPessoas = ibusiness.listarByFilter(entity, predEMail);
        
        if (!lstPessoas.isEmpty()){
            return (Pessoa) lstPessoas.get(0);
        }          
        return (Pessoa) ibusiness.salvar(entity);
    }    
    
    @Override
    public Pessoa obter(Integer id) {
        Pessoa p = new Pessoa();
        p.setId(id);
        return (Pessoa) ibusiness.consultar(p);
    }

    @Override
    public List<Pessoa> obterTodos() {
        return ibusiness.listarAll(new Pessoa());
    }

    @Override
    public List<String> deletar(Pessoa entity) {
        msgs = validarDelete(entity);        
        if (msgs.isEmpty()){            
            if (ibusiness.deletar(entity)){
                msgs.add(excMessages.STR_OPERACAO_SUCESSO);            
                return msgs;
            }
        }
        msgs.add(excMessages.STR_OPERACAO_INSUCESSO);
        return msgs;
    }
    
    private List<String> validarDelete(Pessoa entity){
        if(entity.getId()!=null){
            regras.add("validarPessoaNaoCadastrada");
        }
        return ivalidator.validarRegras(entity, regras);                              
    }    

    private List<String> validar(Pessoa entity){
        regras.add("validarCamposObrigatorios");     
        if(entity.getId()==null){
            regras.add("validarPessoaCadastrada");
        }       
        else{
            regras.add("validarPessoaNaoCadastrada");
        }
        return ivalidator.validarRegras(entity, regras);                              
    }    
    
    @Override
    public List<Pessoa> obterByFilter(Pessoa entity, Predicate<Pessoa> predicate) {
        return ibusiness.listarByFilter(entity, predicate);
    }    
}
