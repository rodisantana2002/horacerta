package helpers.mensagens;
/**
 * Apenas para imprimir na janela imediata
 * @author Rodolfo
 */
public class clsPSR {

    //imprime saida normal
    public static void prt(String strTexto){
        System.out.println(strTexto);
    }
    
    //imprime saida formatada
    public static void prf(String formatacao, Object objValor){
        System.out.printf(formatacao, objValor);
    }    
}
