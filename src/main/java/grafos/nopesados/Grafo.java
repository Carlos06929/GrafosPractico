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
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Grafo {
    //protected int cantAristas;
    protected List<List<Integer>> listaDeAdyacencias;
    public Grafo(){
        this.listaDeAdyacencias=new ArrayList<>();
        //this.cantAristas=0;
    }

    @Override
    public String toString() {
        String s="";
        for (int i = 0; i < cantidadDeVertices(); i++) {
            s=s+"Adyacentes a vertice "+i+": [";
            List<Integer> aux=listaDeAdyacencias.get(i);
            if (aux.isEmpty()) {
                s=s+"null]\n";
            }
            for(int j = 0; j < aux.size(); j++){
                if (j<aux.size()-1) 
                    s=s+aux.get(j)+",";
                else
                    s=s+aux.get(j)+"]\n";
            }
        }
        return s;
    }
    
    
    
    public Grafo(int nroDeVerticesInicial) throws ExcepcionNroVerticesInvalido{
        if (nroDeVerticesInicial<=0) {
            throw new ExcepcionNroVerticesInvalido();
        }
        this.listaDeAdyacencias= new ArrayList<>();
        //this.cantAristas=0;
        for(int i=0;i<nroDeVerticesInicial;i++){
        this.listaDeAdyacencias.add(new ArrayList<>());
        }
    }
    
    public void insertarVertice(){
        this.listaDeAdyacencias.add(new ArrayList<>());    
    }
    
    public int cantidadAristas(){
        int cantAristas=0;
        int cantLazos=0;
        for(int i=0;i<this.listaDeAdyacencias.size();i++){
            List<Integer> adyacentesDeUnVertice=this.listaDeAdyacencias.get(i);
            for(Integer posAdyacente:adyacentesDeUnVertice){
                if (i==posAdyacente) {
                    cantLazos++;
                }else{
                    cantAristas++;
                }
            }
        }
        cantAristas=(cantAristas/2)+cantLazos;
        return cantAristas;
    }
    
    public int cantidadDeVertices(){
        return listaDeAdyacencias.size();
    }
    
    public  void validarVertice(int posicionVertice){
        if (posicionVertice<0 || posicionVertice>=cantidadDeVertices()) {
            throw new IllegalArgumentException("El vertice "+ posicionVertice+" no pertenece al grafo.");
        }
    }
    
    public void insertarArista(int posVerticeOrigen,int posVerticeDestino)throws ExcepcionAristaYaExiste{
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        if (this.existeAdayacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaYaExiste();
        }
        
        List<Integer> adyacenciasDelOrigen=this.listaDeAdyacencias.get(posVerticeOrigen);
        adyacenciasDelOrigen.add(posVerticeDestino);
        //solo si lo quieres acomodar
        Collections.sort(adyacenciasDelOrigen);
        if (posVerticeOrigen!=posVerticeDestino) {
            List<Integer> adyacenciasDelDestino=this.listaDeAdyacencias.get(posVerticeDestino);
            adyacenciasDelDestino.add(posVerticeOrigen);
        }
    }
    
    public boolean existeAdayacencia(int posVerticeOrigen, int posVerticeDestino){
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        List<Integer> adyacenciasDelOrigen=this.listaDeAdyacencias.get(posVerticeOrigen);
        return adyacenciasDelOrigen.contains(posVerticeDestino);
        
    }
    
    
    /*
    ===========================================================
    -----------------PREGUNTA 1--------------------------------
    ===========================================================
    */
    public void eliminarVertice(int posVerticeAELiminar){
        //se debe recalcular las aristas que quedan en el grafo
        validarVertice(posVerticeAELiminar);
        this.listaDeAdyacencias.remove(posVerticeAELiminar);
        for (List<Integer> adyacentesDeUnVertice:this.listaDeAdyacencias) {
            int posicionDeVerticeEnAdyacencia=adyacentesDeUnVertice.indexOf(posVerticeAELiminar);
            if (posicionDeVerticeEnAdyacencia>=0) {
                adyacentesDeUnVertice.remove(posicionDeVerticeEnAdyacencia);
                
            }
            for (int i = 0; i < adyacentesDeUnVertice.size(); i++) {
                //tambien para acortar el codigo podriamos darle a
                // posicionAdyacente=adyacentesDeUnVertice.get(i) para poder acortar el codigo un poco
                if (adyacentesDeUnVertice.get(i)>posVerticeAELiminar) {
                    adyacentesDeUnVertice.set(i, adyacentesDeUnVertice.get(i)-1);
                    
                }
            }      
        }
    }
    public int gradoDeVertice(int posVertice){
        validarVertice(posVertice);
        List<Integer> adyacenciaDelVertice=this.listaDeAdyacencias.get(posVertice);
        return adyacenciaDelVertice.size();
        
    }
    
    public Iterable<Integer> adyacentesDeVertice(int posVertice){
        validarVertice(posVertice);
        List<Integer> adyacenciaDelVertice=this.listaDeAdyacencias.get(posVertice);
        Iterable<Integer> it=adyacenciaDelVertice;
        return it;
    
    }
    
    public void eliminarAristas(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste{
        if (!this.existeAdayacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaNoExiste();
        }
        
        List<Integer> adyacenciasDelOrigen=this.listaDeAdyacencias.get(posVerticeOrigen);
        int posicionDelDestino=adyacenciasDelOrigen.indexOf(posVerticeDestino);
        adyacenciasDelOrigen.remove(posicionDelDestino);
        if (posVerticeDestino!=posVerticeOrigen) {
            List<Integer> adyacenciasDelDestino=this.listaDeAdyacencias.get(posVerticeOrigen);
            int posicionDelOrigen=adyacenciasDelDestino.indexOf(posVerticeOrigen);
            adyacenciasDelDestino.remove(posicionDelOrigen);
        }
       
    }
   
    
    protected boolean utilidadCiclos(int vertice,boolean[] listaVisitados ,int padre){
        validarVertice(vertice);
        listaVisitados[vertice]=true;
        List<Integer> adyacentesEnTurno=this.listaDeAdyacencias.get(vertice);
            for (int i=0;i<adyacentesEnTurno.size();i++) {
                
                if (!listaVisitados[adyacentesEnTurno.get(i)]) {
                    if (utilidadCiclos(adyacentesEnTurno.get(i), listaVisitados, vertice)) {
                        return true;
                    }
                }else if(adyacentesEnTurno.get(i)!=padre)
                return true;
            }
            return false;
    }
    
    /*
    ===========================================================
    -----------------PREGUNTA 9--------------------------------
    ===========================================================
    */
    public boolean tieneCiclo(){
        boolean[] listaVisitados=new boolean[cantidadDeVertices()];
        for (int i = 0; i < cantidadDeVertices(); i++)
            listaVisitados[i]=false;
        for (int j = 0; j < cantidadDeVertices(); j++) {
            if (!listaVisitados[j]) {
                if (utilidadCiclos(j,listaVisitados,-1))
                    return true;
                }
            }
        return false;
        }
    
    public int cantidadDeIslasGrafo(){//cantidad de islas recursivo
        DFS dfs=new DFS(this,0);
        return cantidadDeIslasGrafo(dfs);
    }
     public int cantidadDeIslasGrafo( DFS dfs){
        int contIslas=0;
        if (dfs.hayCaminoATodos()) {
             return 1;
         }else{
             
            int verticeProceso=dfs.posVerticeNoMarcado();
            dfs.continuarDFS(verticeProceso);
            contIslas=cantidadDeIslasGrafo(dfs)+1;
         }
       return contIslas;
    }
     
     
     /*
    ===========================================================
    -----------------PREGUNTA 9--------------------------------
    ===========================================================
    */
     public int cantidadDeIslas(){//codigo planteado en el video del ingeniero
         int contador=0;
         int verticeEnTurno=0;
         DFS recorrido=new DFS(this, verticeEnTurno);
         if (recorrido.hayCaminoATodos()) {
             contador=contador+1;
         }else{
             contador=contador+1;
             while (!recorrido.hayCaminoATodos()) {             
                int i=0;
                while (recorrido.hayCaminoA(i)) {                 
                   i++;
                }
                verticeEnTurno=i;
                recorrido.continuarDFS(verticeEnTurno);
                contador=contador+1;
               }
         }
         return contador;
     }
     
     
     public boolean hayCiclos()throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste{
         Grafo g1=new Grafo(this.cantidadDeVertices());
         UtilsRecorridos util=new UtilsRecorridos(this.cantidadDeVertices());
         util.desmarcarTodo();
         boolean validacion=false;
             while (!util.estanTodosMarcados() && validacion==false) {             
                     int i=0;
                while (util.estaMarcado(i)) {                 
                   i++;
                }
                int verticeEnTurno=i;
                validacion=hayCiclos(g1, verticeEnTurno, util);
               }
         return validacion;             
         }
     
         public boolean hayCiclos(Grafo g1,int vertice,UtilsRecorridos util) throws ExcepcionAristaYaExiste{
             util.marcarVertice(vertice);
             Iterable<Integer> adyacentesEnTurno=adyacentesDeVertice(vertice);
             for(Integer posVerticeAdyacente:adyacentesEnTurno){
                 if(!g1.existeAdayacencia(vertice, posVerticeAdyacente)
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
    protected void utilidaCantCiclos(int vertice,boolean[] listaVisitados ,int padre,List<Integer> listaVertices){
        validarVertice(vertice);
        listaVisitados[vertice]=true;
        List<Integer> adyacentesEnTurno=this.listaDeAdyacencias.get(vertice);
            for (int i=0;i<adyacentesEnTurno.size();i++) {
                
                if (!listaVisitados[adyacentesEnTurno.get(i)]) {
                    utilidaCantCiclos(adyacentesEnTurno.get(i), listaVisitados, vertice,listaVertices);
                }else if(adyacentesEnTurno.get(i)!=padre){
                    
                    if (!listaVertices.contains(vertice)) {
                        listaVertices.add(vertice);
                    }
                    return ;
                }
            }
    }
    
    /*
    ===========================================================
    -----------------PREGUNTA 8--------------------------------
    ===========================================================
    */
    public List<Integer> verticesConCiclos(){
        List<Integer> listaVertices=new ArrayList<>();
        boolean[] listaVisitados=new boolean[cantidadDeVertices()];
        for (int i = 0; i < cantidadDeVertices(); i++)
            listaVisitados[i]=false;
        for (int j = 0; j < cantidadDeVertices(); j++) {
            if (!listaVisitados[j]) {
               utilidaCantCiclos(j,listaVisitados,-1,listaVertices);
               for (int i = 0; i < cantidadDeVertices(); i++)
                    listaVisitados[i]=false;
            }
        }
      return listaVertices;
    }
    
    
    
}