/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.nopesados;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 *
 * @author Usuario
 */
public class MatrizAdyacencia{
   private boolean[][] matrizAdy;
   private int numVertices;
    public MatrizAdyacencia(int numVertices,Grafo g1){
        this.numVertices=numVertices;
        matrizAdy=new boolean[numVertices][numVertices];
        for(int i=0;i<numVertices;i++){
            for (int j = 0; j < numVertices; j++) {
                matrizAdy[i][j]=false;
            }
        }
        contruirMat(g1);
    }
    
    public void contruirMat(Grafo g1){
        List<Integer> l1=new ArrayList();
        for (int i = 0; i <g1.listaDeAdyacencias.size(); i++) {
             l1=g1.listaDeAdyacencias.get(i);   
             for (int j = 0; j < l1.size(); j++) {
                matrizAdy[i][l1.get(j)]=true;
            }
        }
        
    }
    public boolean[][] matriAdy(){
        return matrizAdy;
    }
    
    /*
    ===========================================================
    -----------------PREGUNTA 12--------------------------------
    ===========================================================
    */
    public boolean[][] Warshall(){
        boolean[][] matCam=new boolean[numVertices][numVertices];
        matCam=matrizAdy;
        for (int k = 0; k <numVertices; k++) {
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    matCam[i][j]=matCam[i][j]||(matCam[i][k]&&matCam[k][j]);
                }
            }
        }
        return matCam;
    }
    
    public String daMatriz(boolean[][] b1){
        String s="";
        int[][] m1=new int[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            s=s+"[";
            for (int j = 0; j < numVertices; j++) {
                if(j<numVertices-1){
                    if (b1[i][j]==true) {
                        m1[i][j]=1;
                        s=s+"1 ,";
                    }else{
                        m1[i][j]=0;
                        s=s+"0 ,";
                    }
                }else{
                    if (b1[i][j]==true) {
                    m1[i][j]=1;
                    s=s+"1]\n";
                    }else{
                        m1[i][j]=0;
                     s=s+"0]\n";
                    } 
                }
                
            }
        }
        return s;
    }
    
    
    
    /*
    ===========================================================
    -----------------PREGUNTA 6--------------------------------
    ===========================================================
    */
    public boolean hayCiclos(){
    boolean[][] b=new boolean[numVertices][numVertices];
        b=Warshall();
        for (int i = 0; i < numVertices; i++) {
            if (b[i][i]==true) 
                return true;
            }  
            return false; 
        }
      
    
   
}
