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
import helpers.email.clsTrataEmail;
import helpers.excecoes.excMessages;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import model.entity.Pessoa;
import model.entity.Usuario;

/**
 *
 * @author Rodolfo
 */
public class ctrlUsuario implements Icontroll<Usuario> {
    private ctrlPessoa ctrlPessoa;
    private Ivalidator<Usuario>  ivalidatorUsuario;
    private List<String> msgs, regras;
    private Ibusiness ibusiness;

    public ctrlUsuario() {
        ibusiness = new businessFactory<Usuario>(new Usuario()).getBusiness();
        ivalidatorUsuario = new validatorFactory<Usuario>(new Usuario()).getValidator();
        msgs = new ArrayList<String>();
        ctrlPessoa = new ctrlPessoa();
        regras = new ArrayList<String>();
    }

    @Override
    public List<String> salvar(Usuario entity) {
        msgs = validarUsuario(entity);
        if (msgs.isEmpty()) {
            //se tudo ok..vai avaliar se precisa criar uma nova pessoa
            Pessoa pessoa = (Pessoa) ctrlPessoa.salvarByUser(entity.getPessoa());
            
            if(pessoa!=null){
                entity.setPessoa(pessoa);
                if (ibusiness.salvar(entity) != null) {                                    
                    msgs.add(excMessages.STR_OPERACAO_SUCESSO);                    
                    return msgs;
                }
                else{
                    ctrlPessoa.deletar(pessoa);
                }
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
        if(entity.getId()!=null){
            regras.add("validarRegistroNaoCadastrado");
        }
        return ivalidatorUsuario.validarRegras(entity, regras);      
    }

    private List<String> validarUsuario(Usuario entity) {
        regras.add("validarCamposObrigatorios");     
        if(entity.getId()==null){
            regras.add("validarRegistroCadastrado");
        }       
        else{
            regras.add("validarRegistroNaoCadastrado");
        }
        return ivalidatorUsuario.validarRegras(entity, regras);   
    }
    
    public List<String> autenticarUsuario(Usuario entity) {
        regras.add("validarAutenticacaoUsuario");
        return ivalidatorUsuario.validarRegras(entity, regras);
    }

    public List<String> autenticarToken(Usuario entity) {
        regras.add("validarTokenUsuario");
        return ivalidatorUsuario.validarRegras(entity, regras);
    }

    public Usuario atualizarToken(Usuario entity) {
        Random random = new SecureRandom();
        String token = new BigInteger(130, random).toString(32);
        Date dtDataHoraAtual = new Date();        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");                 
        Predicate<Usuario> predEMail = p -> p.getPessoa().getEmail().equalsIgnoreCase(entity.getPessoa().getEmail());

        List<Usuario> lstUsuario = obterByFilter(entity, predEMail);
        Usuario usuario = lstUsuario.get(0);
        usuario.setToken(token);
        usuario.setDtUltAcesso(dateFormat.format(dtDataHoraAtual));
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

    public String enviarSenha(String email) {
        Predicate<Usuario> predEMail = p -> p.getPessoa().getEmail().equals(email);
        
        Usuario usuario = new Usuario();
        Pessoa pessoa = new Pessoa();
        pessoa.setEmail(email);
        usuario.setPessoa(pessoa);

        List<Usuario> lstUsuario = obterByFilter(usuario, predEMail);
                     
        if(lstUsuario.isEmpty()){
            return excMessages.STR_EMAIL_NAO_CADASTRADO;            
        }
        else{
            clsTrataEmail trataEmail = new clsTrataEmail();
            String endereco = email;
            String assunto = "Sistema Hora Certa - reenvio de dados para acesso ao sistema";            
            String conteudo = getEmailCorpo(lstUsuario.get(0)).toString();
            return trataEmail.enviarEmail(endereco, assunto, conteudo);          
        }        
    }    

    private StringBuilder getEmailCorpo(Usuario usuario) {
        StringBuilder emailCorpo = new StringBuilder();
        emailCorpo.append("<h1>Olá, " + usuario.getPessoa().getPrimeiroNome() + "!</h1>");
        emailCorpo.append("</br>");
        emailCorpo.append("<h3>Seguem os dados para o acesso ao sistema Hora Certa: </h3>");
        emailCorpo.append("<h4>Usuário...: " + usuario.getPessoa().getEmail() + "</h4>");
        emailCorpo.append("<h4>Senha.....: " + usuario.getSenha() + "</h4>");
        emailCorpo.append("</br>");
        emailCorpo.append("</h4>Att, </h4>");        
        emailCorpo.append("</br>");
        emailCorpo.append("</h4>Equipe Hora Certa </h4>");
        return emailCorpo;
    }
}
