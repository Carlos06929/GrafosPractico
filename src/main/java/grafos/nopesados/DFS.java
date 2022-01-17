/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.nopesados;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Usuario
 */
public class DFS {
    
    protected List<Integer> recorrido;
    protected Grafo grafo;
    protected UtilsRecorridos controlMarcados;
    
    
    public DFS(Grafo unGrafo){
        this.grafo = unGrafo;
     
        controlMarcados=new UtilsRecorridos(grafo.cantidadDeVertices());
        controlMarcados.desmarcarTodo();
      
    }
    public DFS(Grafo unGrafo, int posVerticePartida){
        this.grafo = unGrafo;
        grafo.validarVertice(posVerticePartida);
        recorrido=new ArrayList<>();
        controlMarcados=new UtilsRecorridos(grafo.cantidadDeVertices());
        controlMarcados.desmarcarTodo();
        continuarDFS(posVerticePartida);
    }


    
    
    protected void continuarDFS(int posVertice){
        controlMarcados.marcarVertice(posVertice);
        recorrido.add(posVertice);
        Iterable<Integer> adyacentesEnTurno=grafo.adyacentesDeVertice(posVertice);
            for (Integer posVerticeAdyacente : adyacentesEnTurno) {
                if (!controlMarcados.estaMarcado(posVerticeAdyacente)) {
                    continuarDFS(posVerticeAdyacente);
                }
                
            }
    }
    
    protected void continuarDFS2(int posVertice , List<Integer> l1){
        controlMarcados.marcarVertice(posVertice);
        
        l1.add(posVertice);
        Iterable<Integer> adyacentesEnTurno=grafo.adyacentesDeVertice(posVertice);
            for (Integer posVerticeAdyacente : adyacentesEnTurno) {
                if (!controlMarcados.estaMarcado(posVerticeAdyacente)) {
                    continuarDFS2(posVerticeAdyacente,l1);
                }
                
            }
           
    }
    
   
    
    public boolean hayCaminoA(int posVertice){
        grafo.validarVertice(posVertice);
        return controlMarcados.estaMarcado(posVertice);
    }
    
    public Iterable<Integer> elRecorrido(){
        return recorrido;
    
    }
    
    public boolean hayCaminoATodos(){
        return controlMarcados.estanTodosMarcados();
    }
    
     
     
     public boolean tieneVertMarcado(int posVertice){
         List<Integer> verticesAdyacentes=grafo.listaDeAdyacencias.get(posVertice);
         for (Integer vertice : verticesAdyacentes) {
             if ( controlMarcados.estaMarcado(vertice)) {
                 return true;
             }
         }
         return false;
     }
     
     public int posVerticeNoMarcado(){
         for (int i = 0; i <grafo.cantidadDeVertices(); i++) {
             if (controlMarcados.marcados.get(i)==false) {
                 return i;
             }
         }
        return -1;
    }

}
