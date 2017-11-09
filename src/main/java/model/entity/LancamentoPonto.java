/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import model.enumeration.TipoRegistro;

/**
 *
 * @author rsantana
 */
@Entity
@XmlRootElement
public class LancamentoPonto implements Serializable{
    private Integer id;
    private Integer horaLancamento;
    private RegistroPonto registroPonto;
    private TipoRegistro tipoRegistro;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHoraLancamento() {
        return horaLancamento;
    }

    public void setHoraLancamento(Integer horaLancamento) {
        this.horaLancamento = horaLancamento;
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

    @Enumerated(EnumType.ORDINAL)
    public TipoRegistro getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(TipoRegistro tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }
}
