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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rsantana
 */
@Entity
@XmlRootElement
public class Configuracao implements Serializable{
    private Integer id;
    private Integer horaEntrada;
    private Integer horaSaida;
    private Integer intervalo;
    private Integer toleranciaDia;
    private Integer cargaHorariaDia;
    private Pessoa pessoa;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }      

    @OneToOne
    @JoinColumn(name = "id_pessoa")        
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Integer getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Integer horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Integer getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(Integer horaSaida) {
        this.horaSaida = horaSaida;
    }

    public Integer getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(Integer intervalo) {
        this.intervalo = intervalo;
    }

    public Integer getToleranciaDia() {
        return toleranciaDia;
    }

    public void setToleranciaDia(Integer toleranciaDia) {
        this.toleranciaDia = toleranciaDia;
    }

    public Integer getCargaHorariaDia() {
        return cargaHorariaDia;
    }

    public void setCargaHorariaDia(Integer cargaHorariaDia) {
        this.cargaHorariaDia = cargaHorariaDia;
    }
}
