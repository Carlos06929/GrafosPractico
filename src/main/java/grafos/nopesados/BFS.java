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
public class BFS {
    
    private List<Integer> recorrido;
    private Grafo grafo;
    private UtilsRecorridos controlMarcados;
    //esto hay que acondicionarlo como el DFS
    
    
    
    public BFS(Grafo unGrafo, int posVerticePartida){
        this.grafo = unGrafo;
        recorrido=new ArrayList<>();
        grafo.validarVertice(posVerticePartida);
        controlMarcados=new UtilsRecorridos(grafo.cantidadDeVertices());
        controlMarcados.desmarcarTodo();
        ajecutarBFS(posVerticePartida);
    }
  
    private void ajecutarBFS(int posVertice){
        Queue<Integer> cola= new LinkedList<>();
        cola.offer(posVertice);
        controlMarcados.marcarVertice(posVertice);
        do {            
            int posVerticeEnTurno=cola.poll();
            recorrido.add(posVerticeEnTurno);
            Iterable<Integer> adyacentesEnTurno=grafo.adyacentesDeVertice(posVerticeEnTurno);
            for (Integer posVerticeAdyacente : adyacentesEnTurno) {
                if (!controlMarcados.estaMarcado(posVerticeAdyacente)) {
                    cola.add(posVertice);
                    controlMarcados.marcarVertice(posVerticeAdyacente);
                }
            }
        } while (!cola.isEmpty());
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
    
   
    
}
