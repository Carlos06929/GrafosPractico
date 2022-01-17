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
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Usuario
 */
public class DigrafoPesado extends GrafoPesado{
    
    public DigrafoPesado() {
    }

    public DigrafoPesado(int nroDeVerticesInicial) throws ExcepcionNroVerticesInvalido {
        super(nroDeVerticesInicial);
    }
    
    
  
    @Override
    public int cantidadAristas() {
        //terminar esto, Implemetar
        int cantidadAristas=0;
        for (List<AdyacenteConPeso> verticesAdyacentes : listaDeAdyacencias) {
                cantidadAristas=cantidadAristas+verticesAdyacentes.size();
        }
        return cantidadAristas;
    }
    
    @Override
    public void insertarArista(int posVerticeOrigen, int posVerticeDestino,double peso) throws ExcepcionAristaYaExiste {
       validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        
        if (this.existeAdayacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaYaExiste();
        }
       
        List<AdyacenteConPeso> adyacenciasDelOrigen=this.listaDeAdyacencias.get(posVerticeOrigen);
        adyacenciasDelOrigen.add(new AdyacenteConPeso(posVerticeDestino,peso)); 
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
        for(List<AdyacenteConPeso> adyacentesDeVertice: super.listaDeAdyacencias){
            for(AdyacenteConPeso posAdyacente:adyacentesDeVertice){
                if (posAdyacente.getIndiceVertice()==posDeVertice) {
                    entradasDeVertice++;
                }
            }
        }
        return entradasDeVertice;
    }

    

    @Override
    public void eliminarAristas(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        
        if (!this.existeAdayacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaNoExiste();
        }
        
        List<AdyacenteConPeso> adyacenciasDelOrigen=this.listaDeAdyacencias.get(posVerticeOrigen);
        //AdyacenteConPeso adyacenciaAlOrigen=new AdyacenteConPeso(posVerticeDestino, 0);
        int posicionDelDestino=adyacenciasDelOrigen.indexOf(new AdyacenteConPeso(posVerticeDestino, 0));
        adyacenciasDelOrigen.remove(posicionDelDestino);
        //implementar
    }
    
    
    public void listaDeGradosVertice(List<Integer> l1){
        for (int i = 0; i < cantidadDeVertices(); i++) {
            l1.add(gradoDeEntrada(i));
        }
    }
    
    public List<Integer> OrdenamientoTopologico(){
        List<Integer> listaGradosVertice=new ArrayList<>();
        
        List<Integer> listaEnOrden=new ArrayList<>();
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
                listaGradosVertice.set(j,listaGradosVertice.get(j)-1);
                if (listaGradosVertice.get(j)==0) {
                    colaAux.add(j);
                }
            }
        }  
        return listaEnOrden;
        }

    
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
        List<AdyacenteConPeso> listaVerticesAdyacentes=listaDeAdyacencias.get(i);
        for (int j = 0; j < listaVerticesAdyacentes.size(); j++) 
            if (utilidadCiclo(listaVerticesAdyacentes.get(i).getIndiceVertice(), listaVisitados, pilaRecursiva))
                return true;
        pilaRecursiva[i]=false;
        return false;
        }
    
     public int cantidadDeIslasDigrafo(){
         DFSPesado dfs=new DFSPesado(this, 0);
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
     
     
     /*
    ===========================================================
    -----------------PREGUNTA 14--------------------------------
    ===========================================================
    */
    @Override
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
      
      
      public String Dikstra2(int verticeInicial, int verticeFinal){
          String s="";
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
                 if (marcados[j]==false && listaDeCostos.get(j)<=menor) {
                     menor=listaDeCostos.get(j);
                     VmenorCosto=j;
                 }
             }
        }
         if (marcados[verticeFinal]==false) {
             s="No hay camino del vertice "+verticeInicial+" a "+verticeFinal;
         }else{
             int enTurno=verticeFinal;
             Stack<Integer> pila=new Stack<>();
             while(enTurno!=-1){
                 pila.push(enTurno);
                 enTurno=predecesor.get(enTurno);
             }
             s="El camino del costo minimo de "+verticeInicial+" a "+verticeFinal+" es: [";
             while (pila.size()>1) {                 
                s=s+pila.pop()+",";
             }
             s=s+pila.pop()+"]";
             s=s + "\nEl costo minimo para llegar de "+verticeInicial+" a "+verticeFinal+" es->"+listaDeCostos.get(verticeFinal);
         }
         return s;
        
     }
     
      /*
    ===========================================================
    -----------------PREGUNTA 15--------------------------------
    ===========================================================
    */
      public List<String> caminoATodos(int verticeInicial){
          List<String> s=new ArrayList<>();
          String s1="";
          for (int i = 0; i < cantidadDeVertices(); i++) {
              s1=Dikstra2(verticeInicial, i);
              s.add(s1);
          }

          return s;
      }
      
      
      
      
      
      public DigrafoPesado copiarGrafo() throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste{
          DigrafoPesado g=new DigrafoPesado(cantidadDeVertices());
          for (int i = 0; i < cantidadDeVertices(); i++) {
              List<AdyacenteConPeso> vertice=listaDeAdyacencias.get(i);
              for (AdyacenteConPeso verticesAdyacentes: vertice) {
                  g.insertarArista(i, verticesAdyacentes.getIndiceVertice(), verticesAdyacentes.getPeso());
              }
          }
      
          return g;
      }
      
      public AdyacenteConPeso mayorAdyacente(int vertice){
          double menor=0;
          AdyacenteConPeso verticeaux=null;
          List<AdyacenteConPeso> adyacente=listaDeAdyacencias.get(vertice);
          for (int i = 0; i < adyacente.size(); i++) {
              if (adyacente.get(i).getPeso()>menor) {
                  menor=adyacente.get(i).getPeso();
                  verticeaux=adyacente.get(i);
              }
          }
         return verticeaux;
      }
      
      public boolean caminoASumidero(int fuente,int sumidero,List<AdyacenteConPeso> l1){
          AdyacenteConPeso verticeAux=mayorAdyacente(fuente);
          while(verticeAux.getIndiceVertice()!=sumidero){
              
              l1.add(verticeAux);
              int indice=verticeAux.getIndiceVertice();
              verticeAux=mayorAdyacente(indice);
                 if (verticeAux==null) {
                  l1.clear();
                  return false;
              }
          }
          if (verticeAux.getIndiceVertice()==sumidero) {
              l1.add(verticeAux);
          }
          return true;
      }
      
      public double valorMinimo(List<Double> pesos){
          double min=10000;
          for(double pesoLista:pesos){
                 if (pesoLista<min) {
                  min=pesoLista;
              }
             }
          return min;
      }
      
      public double algoritmoFordFulkerson(int fuente,int sumidero){
          List<Double> flujosMinimos=new ArrayList<>();
          List<AdyacenteConPeso> camino=new ArrayList<>();
          boolean validacion=caminoASumidero(fuente, sumidero, camino);
          while(validacion==true){
             List<Double> pesos=new ArrayList<>();
             for(AdyacenteConPeso verticesLista:camino){
                 pesos.add(verticesLista.getPeso());
             }
             double flujoMinimo=valorMinimo(pesos);
              System.out.println(pesos);
              System.out.println(flujoMinimo);
             for(AdyacenteConPeso verticesLista:camino){
                 double pesoAVertice=verticesLista.getPeso();
                 verticesLista.setPeso(pesoAVertice-flujoMinimo);
             }
             flujosMinimos.add(flujoMinimo);camino.clear();
             validacion=caminoASumidero(fuente, sumidero, camino);
             
          }
          double resultado=0;
          for (Double flujosMinimo : flujosMinimos) {
              resultado=resultado+flujosMinimo;
          }
          System.out.println(flujosMinimos);
          return resultado;
      }
}
    
    

  
