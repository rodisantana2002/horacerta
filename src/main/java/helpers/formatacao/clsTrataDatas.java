package helpers.formatacao;

/**
 * Esta classe contém métodos para efetuar conversão de datas entre os formatos ISO (yyyy-MM-dd) e ABNT (dd/MM/yyyy).
 * Estes métodos são voltados pra agilizar a gravação e recuperação de datas em Bancos de Dados MySQL.
 */
import helpers.types.clsConstants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class clsTrataDatas {
   private SimpleDateFormat formatIso;
   private SimpleDateFormat formatBra;
   private Date   date;
   private String strDate;
   private int    intMes;
   private int    intDia;
   private int    intAno;

   public clsTrataDatas() {
      formatIso = new SimpleDateFormat("yyyy-MM-dd");
      formatBra = new SimpleDateFormat("dd/MM/yyyy");
   }

   public String getDataAtual(){
       return formatBra.format(new Date(System.currentTimeMillis()));
   }
   
   /**
    * Converte uma data no formato ABNT em formato ISO;
    * @param strDataBra Argumento que recebe data no formato ABNT(dd/MM/yyyy);
    * @return Uma data no formato ISO(yyyy-MM-dd).
    */
   public String parseDataIso(String strDataBra){
      try {
         date = formatIso.parse(strDataBra);
         return(formatIso.format(date));
      }
      catch (ParseException e){return("Date Error");}
   }

   /**
    * Converte uma data no formato ISO em formato ABNT;
    * @param strDataIso Argumento que recebe data no formato ISO(yyyy-MM-dd);
    * @return Uma data no formato ABNT(dd/MM/yyyy).
    */
   public String parseDataBra(String strDataIso){
      try {
         date = formatBra.parse(strDataIso);
         return(formatBra.format(date));
      }
      catch(ParseException e){return("Date Error");}
   }

   /**
    * Converte uma data no formato ISO em formato ABNT;
    * @param strDataIso Argumento que recebe data no formato ISO(yyyy-MM-dd);
    * @return Uma data no formato ABNT(dd/MM/yyyy).para exibir como String na Lista
    */
   public String parseDataEsp(String strDataIso){
       try{
           date = formatIso.parse(strDataIso);
           return(String.format("%1$td/%1$tm/%1$tY", date));
       }
       catch (ParseException e){return("Date Error");}
   }

   /**
    * Converte uma data no formato ISO em formato ABNT;
    * @param strDataIso Argumento que recebe data no formato ISO(yyyy-MM-dd);
    * @return Uma data no formato ABNT(dd/MM/yyyy).para exibir como String na Lista
    */
   public String parseDataBraIso(String strDataIso){
       try{
           date = formatBra.parse(strDataIso);
           return(String.format("%1$tY-%1$tm-%1$td", date));
       }
       catch (ParseException e){return("Date Error");}
   }

   
   /**
    * realiza a validação de dadas
    * @param strData
    * @return
    */
   public boolean isDate(String strData){
       try{
           //atribui valores
           strDate = strData.replace("/","");

           //não passou nada retorna sempre true
           if (strDate.trim().length()==0){
               return true;
           }
           //prepara variaveis, pois passou algo
           intDia = Integer.parseInt(strDate.substring(0,2).trim());
           intMes = Integer.parseInt(strDate.substring(2,4).trim());
           intAno = Integer.parseInt(strDate.substring(4).trim());
           //verifica se a data e valida
           if (isMesValido() && isDiaValido() && isAnoValido()){
               return true;
           }
       }
       //algo de errado com a data
       catch(Exception ex){return false;}
       return false;
   }
   
   //Valida a Data Ini e maior que a Data Fim
   public boolean isDateMaior(String strDataInicio, String strDataFim){
       Date dtInicio = new Date();
       Date dtFim  = new Date();
       try{
           dtInicio = formatIso.parse(parseDataIso(strDataInicio));
           dtFim = formatIso.parse(parseDataIso(strDataFim));
           if (dtInicio.after(dtFim)){
               return true;
           }
       }    
       //algo de errado com a data       
       catch(ParseException exNFE){System.out.println("formato da data invalida" + " - " + strDataInicio + " - " + strDataFim);}       
       return false;
   }


   //Valida a Data Ini e menor que a Data Fim
   public boolean isDateMenor(String strDataInicio, String strDataFim){
       Date dtInicio = new Date();
       Date dtFim  = new Date();
       try{
           dtInicio = formatIso.parse(parseDataIso(strDataInicio));
           dtFim = formatIso.parse(parseDataIso(strDataFim));
           if (dtInicio.before(dtFim)){
               return true;
           }
       }    
       //algo de errado com a data       
       catch(ParseException exNFE){System.out.println("formato da data invalida"  + " - " + strDataInicio + " - " + strDataFim);}       
       return false;
   }
   
//Verifica o dia 
   private boolean isDiaValido() {
       if ((intDia>0) && (intDia<=getUltDiaMes())){
           return true;
       }
       return false;
    }

   //verifica o mes
   private boolean isMesValido() {
       if(intMes > 0 && intMes <= 12){
           return true;
       }
       return false;
    }

   //verifica o ano
   private boolean isAnoValido() {
        if (intAno>=clsConstants.DT_ANO_MINIMO){
            return true;
        }
        return false;
    }
   
   //valida se o ult dia do mes é valido
   private int getUltDiaMes() {
        int intUltDia;

        //verifica o mes
        if (intMes==clsConstants.DT_MES_FEV){
           if (intAno%4==0){
               intUltDia = clsConstants.DT_ULT_DIA_MES_FEV_BIX;
           }
           else{
               intUltDia = clsConstants.DT_ULT_DIA_MES[intMes];
           }
        }
        else{
           intUltDia = clsConstants.DT_ULT_DIA_MES[intMes];
        }
        return intUltDia;
    }
   
}  