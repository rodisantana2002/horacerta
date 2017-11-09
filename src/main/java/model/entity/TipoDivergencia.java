/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import model.enumeration.TipoSaldo;



/**
 *
 * @author rsantana
 */
@Entity
@XmlRootElement
public class TipoDivergencia implements Serializable{
    private Integer id;
    private String descricao;
    private TipoSaldo tipoSaldo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Enumerated(EnumType.ORDINAL)
    public TipoSaldo getTipoSaldo() {
        return tipoSaldo;
    }

    public void setTipoSaldo(TipoSaldo tipoSaldo) {
        this.tipoSaldo = tipoSaldo;
    }
}
