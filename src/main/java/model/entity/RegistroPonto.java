/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rsantana
 */
@Entity
@XmlRootElement
public class RegistroPonto implements Serializable{
    private Integer id;
    private String dataRegistro;
    private Frequencia frequencia;
    private Pessoa pessoa;      
    private List<LancamentoDivergencia> lstLancamentosDivergencias;  
    private List<LancamentoPonto> lstLancamentosPonto;    
    
    
    public RegistroPonto(){
        lstLancamentosDivergencias = new ArrayList<LancamentoDivergencia>();
        lstLancamentosPonto = new ArrayList<LancamentoPonto>();
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(String dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    @OneToOne
    @JoinColumn(name = "id_frequencia")        
    public Frequencia getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Frequencia frequencia) {
        this.frequencia = frequencia;
    }

    @OneToOne
    @JoinColumn(name = "id_pessoa")    
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "registroPonto")
    @JsonManagedReference
    public List<LancamentoPonto> getLancamentoPonto() {
        return lstLancamentosPonto;
    }

    public void setLancamentoPonto(List<LancamentoPonto> lstLancamentosPonto) {
        this.lstLancamentosPonto = lstLancamentosPonto;
    }

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "registroPonto",fetch = FetchType.EAGER )
    @JsonManagedReference
    public List<LancamentoDivergencia> getLancamentoDivergencia() {
        return lstLancamentosDivergencias;
    }

    public void setLancamentoDivergencia(List<LancamentoDivergencia> lstLancamentosDivergencias) {
        this.lstLancamentosDivergencias = lstLancamentosDivergencias;
    }
}
