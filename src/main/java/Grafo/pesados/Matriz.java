/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo.pesados;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Roberto
 */
public class Matriz {
    protected double[][] matAdyacencia;
    protected int numeroVertices;
    protected int [][] matPred;
    static final double INFINITO=1000;
    
    public Matriz(GrafoPesado g){
        numeroVertices=g.cantidadDeVertices();
        matAdyacencia=new double[numeroVertices][numeroVertices];
        matPred=new int[numeroVertices][numeroVertices];
        for (int i = 0; i < numeroVertices; i++) {
            for (int j = 0; j < numeroVertices; j++) {
                if (i==j) {
                    matAdyacencia[i][j]=0;
                }else{   
                    matAdyacencia[i][j]=INFINITO;
                }
                matPred[i][j]=-1;
            }
        }
        continuarMat(g);
    }
    
    public void continuarMat(GrafoPesado g){
        List<AdyacenteConPeso> l1;
        for (int i = 0; i < numeroVertices; i++) {
            l1=g.listaDeAdyacencias.get(i);
            for (int j = 0; j < l1.size(); j++) {
                int verticeAux=l1.get(j).getIndiceVertice();
                if (i!=verticeAux) {
                    double pesoAux=l1.get(j).getPeso();
                    matAdyacencia[i][verticeAux]=pesoAux;
                }
            }
        }
        }
        public void matriAdy(){
            String s="";
            for (int i =0; i < numeroVertices; i++) {
                s=s+"[";
                for (int j = 0; j <numeroVertices; j++) {
                    if (j==numeroVertices-1) {
                        s=s+matAdyacencia[i][j];
                    }else{
                        s=s+matAdyacencia[i][j]+" |";
                    }
                }
                s=s+"]\n";
            }

            System.out.println(s);
        }
        
        public void matrizPred(){
            String s="";
            for (int i =0; i < numeroVertices; i++) {
                s=s+"[";
                for (int j = 0; j <numeroVertices; j++) {
                    if (j==numeroVertices-1) {
                        s=s+matPred[i][j];
                    }else{
                        s=s+matPred[i][j]+" |";
                    }
                }
                s=s+"]\n";
            }

            System.out.println(s);
        }
        
    public void algoritmoFloid(){
        
        
        //PIVOTE
        for (int k = 0; k < numeroVertices; k++) {
            for (int i = 0; i < numeroVertices; i++) {
                for (int j = 0; j < numeroVertices; j++) {
                    if(i!=j){
                        if (matAdyacencia[i][j]>(matAdyacencia[i][k]+matAdyacencia[k][j])) {
                          matAdyacencia[i][j]=matAdyacencia[i][k]+matAdyacencia[k][j];
                          matPred[i][j]=k;
                        }
                    }
                }
            }
           
        }
    
    
    }
    public void encontrarIntermedio(int verticeOrigen,int verticeDestino,List l1){
        if (matPred[verticeOrigen][verticeDestino]!=-1) {
            int verticeAux=matPred[verticeOrigen][verticeDestino];
            encontrarIntermedio(verticeOrigen,verticeAux,l1);
            l1.add(verticeAux);
            encontrarIntermedio(verticeAux, verticeDestino, l1);
        }
    }
    public List<Integer> caminoCostoMinimo(int verticeOrigen,int verticeDestino){
        List<Integer> l1=new ArrayList<>();
        if (matAdyacencia[verticeOrigen][verticeDestino]==-1) {
            throw new Error("No hay camino entre los vertices");
        }
        l1.add(verticeOrigen);
        
        encontrarIntermedio(verticeOrigen,verticeDestino,l1);
        l1.add(verticeDestino);
        
        return l1;
    }
    
    
    
    
    
    
}
