/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.transitorio;

/**
 *
 * @author rsantana
 */
public class modHorario {
    private String sinal;
    private String horario;
    private Integer valor;

    public modHorario(){}
    
    public modHorario(String sinal, String horario, Integer valor){
        this.sinal=sinal;
        this.horario=horario;
        this.valor=valor;
    }
    
    public String getSinal() {
        return sinal;
    }

    public void setSinal(String sinal) {
        this.sinal = sinal;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }
}
