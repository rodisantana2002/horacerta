package helpers.formatacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import model.transitorio.modHorario;

/**
 *
 * @author Rodolfo
 */
public class clsTrataHorario {
           
    public boolean isTime(String strHora){
        SimpleDateFormat hora = new SimpleDateFormat("HH:mm");  
        hora.setLenient(false);  
        try{  
            hora.parse(strHora);  
        }catch(ParseException e){  
            return false;  
        }  
        return true;  
    }

    public modHorario converterHora(Integer hora){        
        int inteira = (Math.abs(hora)) / 60;
        int resto = (Math.abs(hora)) % 60;
        String horas;
        String minutos;
        
        if (inteira<10){
            horas = "0" + String.valueOf(inteira);
        }
        else{
            horas = String.valueOf(inteira);            
        }        
        if (resto<10){
            minutos = "0" + String.valueOf(resto);
        }
        else{
            minutos = String.valueOf(resto);            
        }
        return new modHorario("+", (horas + ":" + minutos), hora);        
    }
    
    public modHorario converterHora(String hora){
        int minHoras=0;
        int minMinutos=0;
        
        minHoras   = Integer.valueOf(hora.substring(0,2));
        minMinutos = Integer.valueOf(hora.substring(3,5));
        return new modHorario("+", hora, ((minHoras*60) + minMinutos));                
    }
    
    public modHorario calcularHorario(List<modHorario> lstHorarios){
        modHorario horario;
        Integer somaHorario=0;
        String sinal;
        
        for (modHorario hora : lstHorarios){
            somaHorario += Integer.valueOf(hora.getSinal() + converterHora(hora.getHorario()).getValor());
        }        
        if (somaHorario>=0) {
            sinal="+";
        }
        else{
            sinal="-";
        }
        horario = new modHorario(sinal, converterHora(somaHorario).getHorario(), somaHorario);        
        return horario;
    }        
}
