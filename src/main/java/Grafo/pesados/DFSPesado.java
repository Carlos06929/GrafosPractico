/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo.pesados;

import grafos.nopesados.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Usuario
 */
public class DFSPesado {
    
    protected List<Integer> recorrido;
    protected GrafoPesado grafo;
    protected UtilsRecorridos controlMarcados;
    
    public DFSPesado(GrafoPesado unGrafo, int posVerticePartida){
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
         List<AdyacenteConPeso> verticesAdyacentes=grafo.listaDeAdyacencias.get(posVertice);
         for (AdyacenteConPeso vertice : verticesAdyacentes) {
             if ( controlMarcados.estaMarcado(vertice.getIndiceVertice())) {
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
