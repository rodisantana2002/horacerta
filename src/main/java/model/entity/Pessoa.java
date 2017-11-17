/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rodolfo
 */
@Entity
@XmlRootElement
public class Pessoa implements Serializable, Comparable<Pessoa>  {
    private Integer id;
    private String primeiroNome;
    private String segundoNome;
    private String email;
    private String ddd;
    private String foneCelular;
    private String biografia;
    private String instituicao;
    private String pais;
    private String estado;
    private String cidade;
    private String matricula;
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primieroNome) {
        this.primeiroNome = primieroNome;
    }

    public String getSegundoNome() {
        return segundoNome;
    }

    public void setSegundoNome(String segundoNome) {
        this.segundoNome = segundoNome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDDD() {
        return ddd;
    }

    public void setDDD(String ddd) {
        this.ddd = ddd;
    }

    public String getFoneCelular() {
        return foneCelular;
    }

    public void setFoneCelular(String foneCelular) {
        this.foneCelular = foneCelular;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString(){
        return primeiroNome + " " + segundoNome;
    }
    
    @Override
    public int compareTo(Pessoa p) {
        if(p !=null){
            return this.toString().compareTo(p.toString());
        }
        else{
            return 0;
        }        
    }
}
