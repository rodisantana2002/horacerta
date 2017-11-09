package helpers.excecoes;
/**
 *
 * @author Rodolfo
 */
public class excMessages {
    //FORMATCAO 
    public static final String STR_DATA_REGISTRO_MAIOR_DATA_ATUAL   = "A Data de Registro não pode ser superior a Data Atual.";
    public static final String STR_DATA_PERIODO   = "A Data de Início deve ser anterior a Data Fim.";
    public static final String STR_DATA_INVALIDA  = "A data informada no campo não é válida.";
    public static final String STR_DATA_REGISTRO_IS_DATA_SEMPONTO = "A Data de Registro do Ponto foi registrada como Sem Ponto."; 
    public static final String STR_DATA_REGISTRO_IS_FORA_FREQUENCIA = "A Data de Registro não esta dentro do período de Frequência."; 

    public static final String STR_HORA_PERIODO_INVALIDO = "A Hora de Entrada deve ser anterior a Hora de Saida.";
    public static final String STR_HORA_INTERVALO_MAIOR_INVALIDO = "O total de horas do horário de Almoço é superior ao permitido.";
    public static final String STR_HORA_INTERVALO_MENOR_INVALIDO = "O total de horas do horário de Almoço é inferior ao permitido.";    
    public static final String STR_HORA_CARGA_HORARIA_INVALIDA = "A carga horária é superior ao permitido.";

    //CRUDS
    public static final String STR_REG_JA_EXISTE = "Os dados informados já estão cadastrados.";    
    public static final String STR_REG_NAO_EXISTE = "Os dados informados não foram Localizados ou não estão cadastrados no sistema.";    
    public static final String STR_DADOS_OBRIGATORIOS = "Ops! Os campos obrigatórios devem ser informados.";    
       
    public static final String STR_OPERACAO_SUCESSO = "A operação foi realizada com sucesso.";
    public static final String STR_OPERACAO_INSUCESSO = "Ops! A operação não pode ser realizada.";
    
    //SERVICES
    public static final Integer STATUS_OK = 200;
    public static final Integer STATUS_CREATE = 201;
    public static final Integer STATUS_NO_CONTENT = 204;
    public static final Integer STATUS_BAD_REQUEST = 400;
    public static final Integer STATUS_NOT_FOUND = 404;
    public static final Integer STATUS_INTERNAL_SERVER_ERROR = 500;
    public static final Integer STATUS_NOT_IMPLEMENTED = 501;
    public static final Integer STATUS_SERVICE_UNAVAILABLE = 503;
                 
}
