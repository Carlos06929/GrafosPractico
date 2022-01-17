/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.nopesados;

import grafos.Excepciones.ExcepcionAristaNoExiste;
import grafos.Excepciones.ExcepcionAristaYaExiste;
import grafos.Excepciones.ExcepcionNroVerticesInvalido;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Usuario
 */
public class Digrafo extends Grafo{

    public Digrafo() {
    }

    public Digrafo(int nroDeVerticesInicial) throws ExcepcionNroVerticesInvalido {
        super(nroDeVerticesInicial);
    }

    @Override
    public void insertarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaYaExiste {
       
        if (this.existeAdayacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaYaExiste();
        }
       
        List<Integer> adyacenciasDelOrigen=this.listaDeAdyacencias.get(posVerticeOrigen);
        adyacenciasDelOrigen.add(posVerticeDestino); 
        Collections.sort(adyacenciasDelOrigen);
    }

    @Override
    public int gradoDeVertice(int posVertice) {
        throw new UnsupportedOperationException("No soportado en grafos dirigidos");
    }
    
    public int gradoDeSalida(int posVertice){
        return super.gradoDeVertice(posVertice);
    }
    
    public int gradoDeEntrada(int posDeVertice){
        super.validarVertice(posDeVertice);
        int entradasDeVertice=0;
        for(List<Integer> adyacentesDeVertice: super.listaDeAdyacencias){
            for(Integer posAdyacente:adyacentesDeVertice){
                if (posAdyacente==posDeVertice) {
                    entradasDeVertice++;
                }
            }
        }
        
        /*for (int i=0;i<super.listaDeAdyacencias.size();i++){
            Iterable<Integer> adyacentesDeUnVertice=super.adyacentesDeVertice(i);
            for (Integer posDeADyacente : adyacentesDeUnVertice) {
                if (posDeADyacente==posDeVertice) {
                    entradasDeVertice++;
                }
            }
        }*/
        return entradasDeVertice;
    }

    @Override
    public int cantidadAristas() {
        //terminar esto, Implemetar
        int cantidadAristas=0;
        for (List<Integer> verticesAdyacentes : listaDeAdyacencias) {
                cantidadAristas=cantidadAristas+verticesAdyacentes.size();
        }
        return cantidadAristas;
    }

    @Override
    public void eliminarAristas(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste{
        if (!this.existeAdayacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaNoExiste();
        }
        
        List<Integer> adyacenciasDelOrigen=this.listaDeAdyacencias.get(posVerticeOrigen);
        int posicionDelDestino=adyacenciasDelOrigen.indexOf(posVerticeDestino);
        adyacenciasDelOrigen.remove(posicionDelDestino);
    }
    public void listaDeGradosVertice(List<Integer> l1){
        for (int i = 0; i < cantidadDeVertices(); i++) {
            l1.add(gradoDeEntrada(i));
        }
    }
    
    
    /*
    ===========================================================
    -----------------PREGUNTA 18--------------------------------
    ===========================================================
    */
    public List<Integer> OrdenamientoTopologico() throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste{
        FuertementeConexo f1=new FuertementeConexo(this);
        List<Integer> listaEnOrden=new ArrayList<>();
        if(!this.tieneCiclo()&& 
                f1.esFuertementeConexo()){
        List<Integer> listaGradosVertice=new ArrayList<>();
        
        
        listaDeGradosVertice(listaGradosVertice);
        Queue<Integer> colaAux=new LinkedList<>();
        for (int i = 0; i < listaGradosVertice.size(); i++) {
            if (listaGradosVertice.get(i)==0) {
                colaAux.add(i);
            }
        }while (!colaAux.isEmpty()) {            
            int i=colaAux.poll();
            listaEnOrden.add(i);
            for (int j = 0; j < listaDeAdyacencias.get(i).size(); j++) {
                int a=listaDeAdyacencias.get(i).get(j);
                listaGradosVertice.set(a,listaGradosVertice.get(a)-1);
                if (listaGradosVertice.get(a)==0) {
                    colaAux.add(a);
                }
            }
          }  
        }else{
            listaEnOrden.add(-1);
        }
        return listaEnOrden;
        }
    
    
    /*
    ===========================================================
    -----------------PREGUNTA 3--------------------------------
    ===========================================================
    */
    @Override
    public boolean tieneCiclo(){
        boolean[] pilaRecursiva=new boolean[cantidadDeVertices()];
        boolean[] listaVisitados=new boolean[cantidadDeVertices()];
        for (int i = 0; i < cantidadDeVertices(); i++){
            listaVisitados[i]=false;
            pilaRecursiva[i]=false;
        }
        for (int i = 0; i < cantidadDeVertices(); i++) 
            if (utilidadCiclo(i,listaVisitados,pilaRecursiva)) {
                return true;
            }
        return false;
        
    }

    private boolean utilidadCiclo(int i, boolean[] listaVisitados, boolean[] pilaRecursiva) {
        if(pilaRecursiva[i])
            return true;
        if(listaVisitados[i])
            return false;
        listaVisitados[i]=true;
        pilaRecursiva[i]=true;
        List<Integer> listaVerticesAdyacentes=listaDeAdyacencias.get(i);
        for (int j = 0; j < listaVerticesAdyacentes.size(); j++) 
            if (utilidadCiclo(listaVerticesAdyacentes.get(j), listaVisitados, pilaRecursiva))
                return true;
        pilaRecursiva[i]=false;
        return false;
        }
    
    
    //codigo para resolver
     public int cantidadDeIslasDigrafo(){
         DFS dfs=new DFS(this, 0);
         int contIslas=0;
         if (dfs.hayCaminoATodos()) {
             contIslas++;
         }else{
             
            int verticeProceso=dfs.posVerticeNoMarcado();
             if (!dfs.tieneVertMarcado(verticeProceso)) {
                 dfs.continuarDFS(verticeProceso);contIslas=cantidadDeIslasGrafo()+1;  
             }else{
            dfs.continuarDFS(verticeProceso);
            contIslas=cantidadDeIslasDigrafo();
         }
         }
         return contIslas;
     
     }
     
     
     
     
     public int verticeConAdyacenteMarcado(DFS recorrido){
         int vertice=-1;
         for (int i = 0; i <cantidadDeVertices() && vertice==-1; i++) {
             if (!recorrido.hayCaminoA(i)) {
                    Iterable<Integer> verAdyacentes=adyacentesDeVertice(i);
                    for (Integer verAdyacente : verAdyacentes) {
                        if (recorrido.hayCaminoA(verAdyacente)) {
                            return i;
                        }
                    }
             }
         }
         return vertice;
     }
     
     
     
     /*
    ===========================================================
    -----------------PREGUNTA 11--------------------------------
    ===========================================================
    */
      @Override
     public int cantidadDeIslas(){//
         int contador=0;
         int verticeEnTurno=0;
         DFS recorrido=new DFS(this, verticeEnTurno);
         if (recorrido.hayCaminoATodos()) {
             contador=contador+1;
         }else{
             
             while (!recorrido.hayCaminoATodos()) {                 
                 verticeEnTurno=verticeConAdyacenteMarcado(recorrido);
                 
                 if (verticeEnTurno!=-1) {
                     recorrido.continuarDFS(verticeEnTurno);
                 } else {
                     contador=contador+1;
                     verticeEnTurno=recorrido.posVerticeNoMarcado();
                     if (verticeEnTurno!=-1) {
                     recorrido.continuarDFS(verticeEnTurno);
                     }
                     if (recorrido.hayCaminoATodos()) {
                         contador++;
                     }
                 }
             }
         }
     return contador;
     }
     
    
      public int verticeConAdyacenteMarcado(UtilsRecorridos util,Digrafo g1) throws ExcepcionAristaYaExiste{
         int vertice=-1;
         for (int i = 0; i <cantidadDeVertices() && vertice==-1; i++) {
             if (!util.estaMarcado(i)) {
                    Iterable<Integer> verAdyacentes=adyacentesDeVertice(i);
                    for (Integer verAdyacente : verAdyacentes) {
                        if (util.estaMarcado(verAdyacente)) {
                            g1.insertarArista(i,verAdyacente);
                            return i;
                        }
                    }
             }
         }
         return vertice;
     }
     
    @Override
     public boolean hayCiclos()throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste{
         Digrafo g1=new Digrafo(this.cantidadDeVertices());
         UtilsRecorridos util=new UtilsRecorridos(this.cantidadDeVertices());
         util.desmarcarTodo();
         boolean validacion=false;
             while (!util.estanTodosMarcados() && validacion==false) {                 
                int verticeEnTurno=verticeConAdyacenteMarcado(util,g1);
                 
                 if (verticeEnTurno!=-1) {
                     
                     validacion=hayCiclos(g1, verticeEnTurno, util);
                 } else {
                     for (int i = 0; i <cantidadDeVertices(); i++) {
                         if (!util.estaMarcado(i)) {
                            validacion= hayCiclos(g1, i, util);
                             break;
                         }
                 }
               }
             }
         return validacion;             
         }
     
         public boolean hayCiclos(Digrafo g1,int vertice,UtilsRecorridos util) throws ExcepcionAristaYaExiste{
             util.marcarVertice(vertice);
             Iterable<Integer> adyacentesEnTurno=adyacentesDeVertice(vertice);
             for(Integer posVerticeAdyacente:adyacentesEnTurno){
                 if(!g1.existeAdayacencia(vertice,posVerticeAdyacente)
                             && util.estaMarcado(posVerticeAdyacente)){
                         return true;
                     }
                 if (!util.estaMarcado(posVerticeAdyacente)) {
                     
                     g1.insertarArista(vertice, posVerticeAdyacente);
                     if(hayCiclos(g1,posVerticeAdyacente,util)){
                        return true;
                     }
                 }
             }
             return false;
         }
      
         
         public int verticeConAdyacenteMarcado1(DFS recorrido){
         int vertice=-1;
         for (int i = 0; i <cantidadDeVertices() && vertice==-1; i++) {
             if (!recorrido.hayCaminoA(i)) {
                    Iterable<Integer> verAdyacentes=adyacentesDeVertice(i);
                    for (Integer verAdyacente : verAdyacentes) {
                        if (recorrido.hayCaminoA(verAdyacente)) {
                            return i;
                        }
                    }
             }
         }
         return vertice;
     }
     
     /*
    ===========================================================
    -----------------PREGUNTA 10--------------------------------
    ===========================================================
    */
     public List<List<Integer>> listaDeIslas(){//
         List<List<Integer>> listaIslas=new ArrayList<>();
         int verticeEnTurno=0;
         DFS recorrido=new DFS(this);
        List<Integer> listaAux=new ArrayList<>();
         recorrido.continuarDFS2(verticeEnTurno,listaAux);
             while (!recorrido.hayCaminoATodos()) {   
                 
                 verticeEnTurno=verticeConAdyacenteMarcado1(recorrido);
                 if (verticeEnTurno!=-1) {
                     recorrido.continuarDFS2(verticeEnTurno,listaAux);
                 } else {
                     //
                     verticeEnTurno=recorrido.posVerticeNoMarcado();
                     if (verticeEnTurno!=-1) {
                     listaIslas.add(listaAux);
                     listaAux=new ArrayList<>();
                     listaAux.clear();
                     recorrido.continuarDFS2(verticeEnTurno,listaAux);
                         if (recorrido.hayCaminoATodos()) {
                             listaIslas.add(listaAux);
                         }
                     }
                 }
             }
     return listaIslas;
     }
     
     public void hayCamino(UtilsRecorridos util,int verticeP){
        util.marcarVertice(verticeP);
        Iterable<Integer> adyacentesEnTurno=adyacentesDeVertice(verticeP);
            for (Integer posVerticeAdyacente : adyacentesEnTurno) {
                if (!util.estaMarcado(posVerticeAdyacente)) {
                    hayCamino(util,posVerticeAdyacente);
                }

            }
     }
     
     
     /*
    ===========================================================
    -----------------PREGUNTA 13--------------------------------
    ===========================================================
    */
     public int compFuert(){
         UtilsRecorridos util=new  UtilsRecorridos(this.cantidadDeVertices());
         util.desmarcarTodo();
         int contador=0;
         for (int i = 0; i < this.cantidadDeVertices(); i++) {
             for (int j = 0; j <this.cantidadDeVertices(); j++) {
                 if (j!=i){
                     hayCamino(util, i);
                     if (util.estaMarcado(j)){
                        util.desmarcarTodo();
                         hayCamino(util, j);
                         if (util.estaMarcado(i)) 
                            contador++;
                     }else{
                        util.desmarcarTodo();
                 }
              
                 }else{
                     if (listaDeAdyacencias.get(i).contains(j)) {
                         contador++;
                     }
                 }
             }
         }
         return contador;
     }
     
     public List<Integer> verticesConCiclo(){
        List<Integer> listaVertices=new ArrayList<>();
        boolean[] pilaRecursiva=new boolean[cantidadDeVertices()];
        boolean[] listaVisitados=new boolean[cantidadDeVertices()];
        for (int i = 0; i < cantidadDeVertices(); i++){
            listaVisitados[i]=false;
            pilaRecursiva[i]=false;
        }
        for (int i = 0; i < cantidadDeVertices(); i++) {
            utilidadCantCiclos(i,listaVisitados,pilaRecursiva,listaVertices);
            for (int j = 0; j < cantidadDeVertices(); j++){
            listaVisitados[j]=false;
            pilaRecursiva[j]=false;}
        
        }
            
        return listaVertices;
        
    }

    private void utilidadCantCiclos(int i, boolean[] listaVisitados, boolean[] pilaRecursiva,List<Integer> listavertices) {
        if(pilaRecursiva[i]){
            if(!listavertices.contains(i))
                listavertices.add(i);
            return;
        }
        if(listaVisitados[i])
            return;
        listaVisitados[i]=true;
        pilaRecursiva[i]=true;
        List<Integer> listaVerticesAdyacentes=listaDeAdyacencias.get(i);
        for (int j = 0; j < listaVerticesAdyacentes.size(); j++) 
            utilidadCantCiclos(listaVerticesAdyacentes.get(j), listaVisitados, pilaRecursiva,listavertices);
        pilaRecursiva[i]=false;
        }
     
     }
    
    
    

  
