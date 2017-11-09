package helpers.actions;

import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.swing.AbstractAction;

//Principal classe para execução de comandos do framework
//implemente padrão COMMAND

public class clsTrataActionsDinamicas extends AbstractAction {
    // Atributos
    private final String   methodName;
    private final Object   mobjTemp;
    private final Object[] args;

    //Construtor
    public clsTrataActionsDinamicas(Object objTemp, String methodName, Object...args) {
        if (objTemp == null || methodName == null) {
            throw new IllegalArgumentException("Problemas ao invocar o metodo desejado");
        }
        mobjTemp        = objTemp;
        this.methodName = methodName;
        this.args       = args;
    }

    //invoca metodos dinamicamente
    public void executarMetodoDinamico() {
        //recupera a classe do objeto
        Class objClasse = mobjTemp.getClass();

        try {
            //prepara os objetos para cada argumento
            Class[] argClasses = null;
            if (args != null && args.length > 0) {
                argClasses = new Class[args.length];
                for (int i = 0; i < args.length; i++) {
                    argClasses[i] = args[i].getClass();
                }
            }
            //prepara um novo metodo conforme o que foi repassado
            Method mid = objClasse.getMethod(methodName, argClasses);
            if (mid != null) {
                if (args != null) {
                    //invoca com argumentos
                    mid.invoke(mobjTemp, args);
                } else {
                    //invoca sem argumento
                    mid.invoke(mobjTemp);
                }
            }
        } 
        catch(Exception ex){ex.printStackTrace();}
    }

    //invoca e retorna valor de metodos dinamicamente
    public Object getMetodoDinamico(){
        //recupera a classe do objeto
        Class objClasse = mobjTemp.getClass();

        try {
            //prepara os objetos para cada argumento
            Class[] argClasses = null;
            if (args != null && args.length > 0) {
                argClasses = new Class[args.length];
                for (int i = 0; i < args.length; i++) {
                    argClasses[i] = args[i].getClass();
                }
            }
            //prepara um novo metodo conforme o que foi repassado
            Method mid = objClasse.getMethod(methodName, argClasses);
            if (mid != null) {
                if (args != null) {
                    //invoca e retorne com argumentos
                    return mid.invoke(mobjTemp, args);
                } else {
                    //invoca e retorna sem argumento
                    return mid.invoke(mobjTemp);
                }
            }
        } 
        catch(Exception ex){ex.printStackTrace();}        
        return null;
    }
    
    //implementação da interface AbstractAction(NADA A IMPLEMENTAR)
    @Override
    public void actionPerformed(ActionEvent e) {}
}
