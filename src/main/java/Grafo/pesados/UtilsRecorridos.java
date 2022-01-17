/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo.pesados;

import grafos.nopesados.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class UtilsRecorridos {
    protected List<Boolean> marcados;
    protected int nroVertices;
    public UtilsRecorridos(int nroVertices){
        this.nroVertices=nroVertices;
    }
    public void desmarcarTodo(){
        marcados=new ArrayList<>();
        
        for (int i = 0; i <this.nroVertices; i++) {
            marcados.add(Boolean.FALSE);
        }
    }
    
    public void marcarVertice(int posVertice){
        marcados.set(posVertice, Boolean.TRUE);
    }
    
     public  boolean estaMarcado(int posVertice){
        return marcados.get(posVertice);
    }
     
     public boolean estanTodosMarcados(){
         for(boolean marcado:this.marcados){
             if(!marcado)
                 return false;
         }
         return true;
     }
     
}
