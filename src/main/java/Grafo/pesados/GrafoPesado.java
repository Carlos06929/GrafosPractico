/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo.pesados;

import grafos.Excepciones.ExcepcionAristaNoExiste;
import grafos.nopesados.*;
import grafos.Excepciones.ExcepcionAristaYaExiste;
import grafos.Excepciones.ExcepcionNroVerticesInvalido;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Usuario
 */
public class GrafoPesado {
    //protected int cantAristas;
    protected List<List<AdyacenteConPeso>> listaDeAdyacencias;
    public GrafoPesado(){
        this.listaDeAdyacencias=new ArrayList<>();
        //this.cantAristas=0;
    }

    @Override
    public String toString() {
        String s1="";
        for (int i = 0; i <listaDeAdyacencias.size(); i++) {
            s1=s1+"Adyacentes de vertice("+i+")->";
            List<AdyacenteConPeso> adyacentesVertice=listaDeAdyacencias.get(i);
            for (AdyacenteConPeso adyacenteConPeso : adyacentesVertice) {
                s1=s1+adyacenteConPeso.toString()+",";
            }
            s1=s1+"\n";
        }
        return s1;
    }
    
    public GrafoPesado(int nroDeVerticesInicial) throws ExcepcionNroVerticesInvalido{
        if (nroDeVerticesInicial<0) {
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
            List<AdyacenteConPeso> adyacentesDeUnVertice=this.listaDeAdyacencias.get(i);
            for(AdyacenteConPeso posAdyacente:adyacentesDeUnVertice){
                if (i==posAdyacente.getIndiceVertice()) {
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
    
    public void insertarArista(int posVerticeOrigen,int posVerticeDestino,double costo)throws ExcepcionAristaYaExiste{
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        if (this.existeAdayacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaYaExiste();
        }
        
        List<AdyacenteConPeso> adyacenciasDelOrigen=this.listaDeAdyacencias.get(posVerticeOrigen);
        adyacenciasDelOrigen.add(new AdyacenteConPeso(posVerticeDestino,costo));
        Collections.sort(adyacenciasDelOrigen);
        //solo si lo quieres acomodar Collections.sort(adyacenciasDelOrigen);
        if (posVerticeOrigen!=posVerticeDestino) {
            List<AdyacenteConPeso> adyacenciasDelDestino=this.listaDeAdyacencias.get(posVerticeDestino);
            adyacenciasDelDestino.add(new AdyacenteConPeso(posVerticeOrigen,costo));
            Collections.sort(adyacenciasDelDestino);
        }
    }
    
    public boolean existeAdayacencia(int posVerticeOrigen, int posVerticeDestino){
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        List<AdyacenteConPeso> adyacenciasDelOrigen=this.listaDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso destino=new AdyacenteConPeso(posVerticeDestino);
        return adyacenciasDelOrigen.contains(destino);
        
    }
    
    public void eliminarVertice(int posVerticeAELiminar){
        //se debe recalcular las aristas que quedan en el grafo
        validarVertice(posVerticeAELiminar);
        this.listaDeAdyacencias.remove(posVerticeAELiminar);
        for (List<AdyacenteConPeso> adyacentesDeUnVertice:this.listaDeAdyacencias) {
            AdyacenteConPeso adyacenteConPeso=new AdyacenteConPeso(posVerticeAELiminar);
            int posicionDeVerticeEnAdyacencia=adyacentesDeUnVertice.indexOf(adyacenteConPeso);
            if (posicionDeVerticeEnAdyacencia>=0) {    
                adyacentesDeUnVertice.remove(posicionDeVerticeEnAdyacencia);
                
            }
            for (int i = 0; i < adyacentesDeUnVertice.size(); i++) {
                AdyacenteConPeso posicionAdyacente= adyacentesDeUnVertice.get(i);
                //tambien para acortar el codigo podriamos darle a
                // posicionAdyacente=adyacentesDeUnVertice.get(i) para poder acortar el codigo un poco
                if (posicionAdyacente.getIndiceVertice()>posVerticeAELiminar) {
                    posicionAdyacente.setIndiceVertice(posicionAdyacente.getIndiceVertice()-1);
                    //adyacentesDeUnVertice.set(i, adyacentesDeUnVertice.get(i).getIndiceVertice()-1);
                    
                }
            }      
        }
    }
    public int gradoDeVertice(int posVertice){
        validarVertice(posVertice);
        List<AdyacenteConPeso> adyacenciaDelVertice=this.listaDeAdyacencias.get(posVertice);
        return adyacenciaDelVertice.size();
        
    }
    
    public Iterable<Integer> adyacentesDeVertice(int posVertice){
        validarVertice(posVertice);
        List<AdyacenteConPeso> adyacenciasDelVertice=this.listaDeAdyacencias.get(posVertice);
        List<Integer> adyacentesDelVertice=new ArrayList<>();
        for (AdyacenteConPeso adyacente : adyacenciasDelVertice) {
            adyacentesDelVertice.add(adyacente.getIndiceVertice());
        }
        Iterable<Integer> it=adyacentesDelVertice;
        return it;
    
    }
    
    public void eliminarAristas(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste{
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        if (!this.existeAdayacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaNoExiste();
        }
        List<AdyacenteConPeso> adyacenciasDelOrigen=this.listaDeAdyacencias.get(posVerticeOrigen);
        //AdyacenteConPeso adyacenciaAlOrigen=new AdyacenteConPeso(posVerticeDestino, 0);
        int posicionDelDestino=adyacenciasDelOrigen.indexOf(new AdyacenteConPeso(posVerticeDestino, 0));
        adyacenciasDelOrigen.remove(posicionDelDestino);
        if (posVerticeDestino!=posVerticeOrigen) {
            List<AdyacenteConPeso> adyacenciasDelDestino=this.listaDeAdyacencias.get(posVerticeDestino);
            //AdyacenteConPeso adyacenciaAlOrigen=new AdyacenteConPeso(posVerticeDestino, 0);
            int posicionDelOrigen=adyacenciasDelDestino.indexOf(new AdyacenteConPeso(posVerticeOrigen, 0));
            adyacenciasDelDestino.remove(posicionDelOrigen);
        }
       
        
    }
    
    public double peso(int deVertice,int aVertice){
        validarVertice(aVertice);
        validarVertice(deVertice);
        List<AdyacenteConPeso> adyacenteDelVertice=this.listaDeAdyacencias.get(deVertice);
        AdyacenteConPeso auxiliar=new AdyacenteConPeso(aVertice);
        int posicion=adyacenteDelVertice.indexOf(auxiliar);
        return adyacenteDelVertice.get(posicion).getPeso();
        
    }
   
    
    protected boolean utilidadCiclos(int vertice,boolean[] listaVisitados ,int padre){
        validarVertice(vertice);
        listaVisitados[vertice]=true;
        List<AdyacenteConPeso> adyacentesEnTurno=this.listaDeAdyacencias.get(vertice);
            for (int i=0;i<adyacentesEnTurno.size();i++) {
                
                if (!listaVisitados[adyacentesEnTurno.get(i).getIndiceVertice()]) {
                    if (utilidadCiclos(adyacentesEnTurno.get(i).getIndiceVertice(), listaVisitados, vertice)) {
                        return true;
                    }
                }else if(adyacentesEnTurno.get(i).getIndiceVertice()!=padre)
                return true;
            }
            return false;
    }
    
    
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
    
     public int cantidadDeIslasGrafo(){
         DFSPesado dfs=new DFSPesado(this, 0);
        int contIslas=0;
        if (dfs.hayCaminoATodos()) {
             contIslas++;
         }else{
             
            int verticeProceso=dfs.posVerticeNoMarcado();
            dfs.continuarDFS(verticeProceso);
            contIslas=cantidadDeIslasGrafo()+1;
         }
       return contIslas;
    }
     
     public void Dikstra(int verticeInicial, int verticeFinal){
	int numVertice=listaDeAdyacencias.size();
	List<Integer> predecesor=new ArrayList<>();
	boolean[] marcados=new boolean[numVertice];
	List<Double> listaDeCostos=new ArrayList<>();
	for(int i=0;i<numVertice;i++){
            listaDeCostos.add(1000.0);
            marcados[i]=false;
            predecesor.add(-1);
	}
        listaDeCostos.set(verticeInicial,0.0);
        int VmenorCosto=verticeInicial;
        while(listaDeCostos.get(VmenorCosto)!=1000.0 && 
                 marcados[verticeFinal]==false) {
            marcados[VmenorCosto]=true;
            Iterable<Integer> it=adyacentesDeVertice(VmenorCosto);
            for (Integer verticeEnTurno: it) {
             if (marcados[verticeEnTurno]==false &&
                 listaDeCostos.get(verticeEnTurno)>
                 listaDeCostos.get(VmenorCosto)+peso(VmenorCosto,verticeEnTurno)) {
                 listaDeCostos.set(verticeEnTurno,(listaDeCostos.get(VmenorCosto)+peso(VmenorCosto,verticeEnTurno)));
                 predecesor.set(verticeEnTurno, VmenorCosto);
                 
             }
         }
            double menor=1000;
             for (int j = 0; j < numVertice; j++) {
                 if (marcados[j]==false && listaDeCostos.get(j)<menor) {
                     menor=listaDeCostos.get(j);
                     VmenorCosto=j;
                 }
             }
        }
         if (marcados[verticeFinal]==false) {
             System.out.println("No hay camino al Vertice");
         }else{
             int enTurno=verticeFinal;
             Stack<Integer> pila=new Stack<>();
             while(enTurno!=-1){
                 pila.push(enTurno);
                 enTurno=predecesor.get(enTurno);
             }
             String s="El camino del costo minimo es: [";
             while (pila.size()>1) {                 
                s=s+pila.pop()+",";
             }
             s=s+pila.pop()+"]";
             System.out.println(s + "\n El costo minimo para llegar de "+verticeInicial+" a "+verticeFinal+" es->"+listaDeCostos.get(verticeFinal));
         }
        
     }
     
     public int utilPrim(GrafoPesado g,UtilsRecorridos util) throws ExcepcionAristaYaExiste{
         double menor=Double.MAX_VALUE;
         int i=0;
         int vertice=-1;
         AdyacenteConPeso aux=new AdyacenteConPeso();
         while(i<cantidadDeVertices()){
             if (util.estaMarcado(i)) {
                 List<AdyacenteConPeso> adyacentes=listaDeAdyacencias.get(i);
                 for(AdyacenteConPeso x:adyacentes){
                     if (x.getPeso()<menor && !util.estaMarcado(x.getIndiceVertice())) {
                         menor=x.getPeso();
                        vertice=i;
                        aux=x;
                     }
                 }
             }
             i=i+1;
         }
            if (vertice!=-1 && !g.existeAdayacencia(vertice, aux.getIndiceVertice())) {
             g.insertarArista(vertice,aux.getIndiceVertice(),menor);
             vertice=aux.getIndiceVertice();
         }
         return vertice;
     }
     
     
     /*
    ===========================================================
    -----------------PREGUNTA 17--------------------------------
    ===========================================================
    */
        public GrafoPesado prim() throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste{
            UtilsRecorridos util=new UtilsRecorridos(this.cantidadDeVertices());
            GrafoPesado g=new GrafoPesado(cantidadDeVertices());
            util.desmarcarTodo();
            int verticeEnTurno=0;
            while(!util.estanTodosMarcados()){
                util.marcarVertice(verticeEnTurno);
                verticeEnTurno=utilPrim(g,util);
                if (verticeEnTurno==-1) {
                    break;
                }
            }

        return g;
        }
        
        
        
        
        
        
        public boolean existe(List<NodoKruskal> l1,int origen,int destino){
            for(int i=0;i<l1.size();i++){
                int o=l1.get(i).getOrigen();
                int d=l1.get(i).getDestino();
                if (origen==d && destino==o) {
                    return true;
                }
            }
            return false;
        }
        public void llenarLista(List<NodoKruskal> l1){
            for (int i = 0; i < listaDeAdyacencias.size(); i++) {
                List<AdyacenteConPeso> l2=listaDeAdyacencias.get(i);
                for(AdyacenteConPeso adyacenteVertice:l2){
                    int verticeDestino=adyacenteVertice.getIndiceVertice();
                    double pesoADestino=adyacenteVertice.getPeso();
                    if(!existe(l1,i,verticeDestino)){
                        NodoKruskal k1=new NodoKruskal(i, verticeDestino,pesoADestino);
                        l1.add(k1);
                    }
                }
            }
        }
        public GrafoPesado Kruskal() throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste{
            /*List<NodoKruskal> l1=new ArrayList<>();
            NodoKruskal k=new NodoKruskal(0, 1, 300);
            l1.add(k);
            k=new NodoKruskal(0, 5, 50);
            l1.add(k);
            k=new NodoKruskal(6, 1, 150);
            l1.add(k);
            k=new NodoKruskal(9, 1, 130);
            l1.add(k);
            k=new NodoKruskal(9, 1, 300);
            l1.add(k);
            System.out.println(l1);
            
            System.out.println(l1);*/
            GrafoPesado g=new GrafoPesado(cantidadDeVertices());
            List<NodoKruskal> l1=new ArrayList<>();
            llenarLista(l1);
            Collections.sort(l1);
            for(int i=0;i<l1.size();i++){
                int origen=l1.get(i).getOrigen();
                int destino=l1.get(i).getDestino();
                double peso=l1.get(i).getPeso();
                g.insertarArista(origen, destino, peso);
                if (g.tieneCiclo()) {
                    g.eliminarAristas(origen, destino);
                }
            }
            
            
            
            
            return g;
        }
       
}
