/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.Excepciones;

/**
 *
 * @author Usuario
 */
public class ExcepcionAristaNoExiste extends Exception{
    public ExcepcionAristaNoExiste(){
        super("Arista No existe En El Grafo");
    }
    
    public ExcepcionAristaNoExiste(String message){
        super(message); 
    }
}
