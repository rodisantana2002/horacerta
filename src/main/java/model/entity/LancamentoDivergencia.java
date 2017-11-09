/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rsantana
 */
@Entity
@XmlRootElement
public class LancamentoDivergencia implements Serializable{
    private Integer id;
    private RegistroPonto registroPonto;
    private String descricao;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "id_registroPonto", nullable = false)     
    @JsonBackReference
    public RegistroPonto getRegistroPonto() {
        return registroPonto;
    }

    public void setRegistroPonto(RegistroPonto registroPonto) {
        this.registroPonto = registroPonto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
