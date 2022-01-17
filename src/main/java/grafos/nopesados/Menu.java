/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.nopesados;

import Grafo.pesados.DigrafoPesado;
import Grafo.pesados.GrafoPesado;
import grafos.Excepciones.ExcepcionAristaYaExiste;
import grafos.Excepciones.ExcepcionNroVerticesInvalido;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Menu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste {
        Grafo g=new Grafo(7);
        g.insertarArista(0, 1);
        g.insertarArista(1, 2);
        g.insertarArista(2, 3);
        g.insertarArista(3, 1);
        g.insertarArista(2, 4);
        
        Digrafo g1=new Digrafo(7);
        g1.insertarArista(0, 1);
        g1.insertarArista(1, 3);
        g1.insertarArista(1, 4);
       g1.insertarArista(2, 0);
       g1.insertarArista(2, 2);
       
       Digrafo g4=new Digrafo(5);
        g4.insertarArista(0, 3);
        g4.insertarArista(3, 0);
        g4.insertarArista(0, 0);
       
       
       Digrafo g2=new Digrafo(7);
        g2.insertarArista(0, 1);
        g2.insertarArista(1, 3);
        g2.insertarArista(1, 4);
       g2.insertarArista(2, 0);
       g2.insertarArista(2, 2);
       
       Digrafo g3=new Digrafo(5);
        g3.insertarArista(0, 1);
        g3.insertarArista(0, 2);
        g3.insertarArista(0, 4);
       g3.insertarArista(0, 3);
       g3.insertarArista(1, 3);
       g3.insertarArista(2, 1);
       g3.insertarArista(2, 4);
       g3.insertarArista(3, 4);
       
       
       
       
       Grafo g5=new Grafo(5);
       g5.insertarArista(0, 2);
       g5.insertarArista(0, 1);
       g5.insertarArista(0, 4);
       g5.insertarArista(0, 3);
       g5.insertarArista(1, 3);
       g5.insertarArista(4, 3);
      
       
       
       DigrafoPesado p1=new DigrafoPesado(6);
       p1.insertarArista(0, 1,10);
        p1.insertarArista(1, 2,15);
        p1.insertarArista(2, 3,20);
        p1.insertarArista(3, 1,50);
        p1.insertarArista(2, 4,40);
        p1.insertarArista(0, 4,40);
        
        DigrafoPesado p2=new DigrafoPesado(5);
       p2.insertarArista(0, 1,10);
        p2.insertarArista(1, 2,15);
        p2.insertarArista(2, 3,20);
        p2.insertarArista(3, 1,50);
        p2.insertarArista(2, 4,40);
        p2.insertarArista(0, 4,40);
        
        GrafoPesado p3=new GrafoPesado(5);
        p3.insertarArista(0, 1,10);
        p3.insertarArista(1, 2,15);
        p3.insertarArista(2, 3,20);
        p3.insertarArista(3, 1,50);
        p3.insertarArista(2, 4,40);
        p3.insertarArista(0, 4,40);
        
         System.out.println("\n\n==================================================== \n"
                           +"-----------------PRACTICO DE GRAFOS-----------------"+
                             "\n====================================================\n");   
         
        System.out.println("PREGUNTA 1: Para un grafo no dirigido impementar los metodos insertarVertice, insertarArista, eliminarVertice,\n "
                            +"eliminarArista, cantidadDeVertices, cantidadDeArtista, gradoDeVertice");
        System.out.println("METODOS IMPLEMENTADOS CORRECTAMENTE");
                
        
              
      System.out.println("\n\n\n\n==================================================== \n"
                         + "====================================================");             
      System.out.println("PREGUNTA 2: Para un grafo dirigido impementar los metodos insertarVertice, insertarArista, eliminarVertice,\n "
                            +"eliminarArista, cantidadDeVertices, cantidadDeArtista, gradoDeVertice");
      System.out.println("METODOS IMPLEMENTADOS CORRECTAMENTE");

      
      System.out.println("\n\n\n\n==================================================== \n"
                + "===========================================================");  
       System.out.println("PREGUNTA 3: Para un grafo dirigido implementar metodo de clase para encontrar si hay ciclos sin usar matriz de caminos.");
       System.out.println("Digrafo: \n"+g3+"\n En el Digrafo hay ciclos?: "+g3.tieneCiclo());

          
       
       
       
       
       System.out.println("PREGUNTA 5:  Para un grafo dirigido implementar un metodo o clase que sea capaz de retornar los componentes de las islas que existen en dicho digrafo.");
        System.out.println("Grafo:\n"+g1+"\nLas islas son: ");
       System.out.println(g1.listaDeIslas());
        
        
     
       
        
       System.out.println("\n\n\n\n==================================================== \n"
                         + "====================================================");   
       System.out.println("PREGUNTA 7: Para um grafo dirigido implementar en metodo o clase que permita determinar si el digrafo es debilmente conexo.");
       DebilmenteConexo d1=new DebilmenteConexo(g2);
       System.out.println("Digrafo: \n"+g2+"\n En el Digrafo es DebilmenteConexo?: "+d1.esDebilmenteConexo());
       
       
       
       System.out.println("\n\n\n\n==================================================== \n"
                         + "====================================================");   
       System.out.println("PREGUNTA 8:  Para un grafo dirigido implementar un metodo o clase que permita determinar si el digrafo es fuertemente conexo.");
       FuertementeConexo d2=new FuertementeConexo(g2);
       System.out.println("Digrafo: \n"+g2+"\n En el Digrafo es DebilmenteConexo?: "+d2.esFuertementeConexo());
       
       
       System.out.println("\n\n\n\n==================================================== \n"
                         + "====================================================");   
       System.out.println("PREGUNTA 9: Para un grafo no dirigido implementar un metodo o clase que permita encontrar si en dicho grafo hay ciclos.");
        System.out.println("Grafo: "+g+"\nTiene ciclos?: "+g.tieneCiclo());

       System.out.println("\n\n\n\n==================================================== \n"
                         + "====================================================");   
       System.out.println("PREGUNTA 10:  Para un grafo no dirigido implemntar metodo o clase para encontrar los componentes de las diferentes islas que hay en dicho digrafo.");
       System.out.println("Grafo:\n"+g1+"\nLas islas son: ");
       System.out.println(g1.listaDeIslas());

       
       System.out.println("\n\n\n\n==================================================== \n"
                         + "====================================================");   
       System.out.println("PREGUNTA 11: Para um grafo dirigido implementar un algoritmo para mejorar el numero de las islas que hay en el grafo.");
            System.out.println("Digrafo: \n"+g1+"\n En el Digrafo cantidad de Islas: "+g1.cantidadDeIslas());

        
       System.out.println("\n\n\n\n==================================================== \n"
                         + "====================================================");   
       System.out.println("PREGUNTA 12: Para un grafo dirigido implementar el algoritmo de wharshal, que luego muestre entre que vertices hay camino");
       MatrizAdyacencia m1=new MatrizAdyacencia(g1.cantidadDeVertices(),g1);
       System.out.println("Matriz de adyacencias del DIgrafo: \n"+m1.daMatriz(m1.matriAdy())+"\n Algoritmo de Warshal que muestra entre que vertices hay camino: ");
        System.out.println(m1.daMatriz(m1.Warshall()));
       
       
       
        
       System.out.println("\n\n\n\n==================================================== \n"
                         + "====================================================");   
       System.out.println("PREGUNTA 15: Para un grafo dirigido pesado implementar el algoritmo de Dijkstra que muestre con que\n" +
                            "vértices hay caminos de costo mínimo partiendo desde un vértice v, con qué costo y cuáles\n" +
                            "son los caminos.");
       System.out.println("DiGrafo Pesado:\n"+p1);
       List<String> s=new ArrayList<>();
       s=p1.caminoATodos(0);
        for (int i = 0; i < s.size(); i++) {
            System.out.println(s.get(i)+"\n");
            
        }
       
      
       

       System.out.println("\n\n\n\n==================================================== \n"
                         + "====================================================");   
       System.out.println("PREGUNTA 17: Para un grafo no dirigido pesado implementar el algoritmo de Prim que muestre cual es el\n" +
                            "grafo encontrado por el algoritmo");

       System.out.println("Grafo Pesado:\n"+p3+"\nEl rabol de expancion producido Por el algoritmo Prim es:\n"+p3.prim());

       
       
       System.out.println("\n\n\n\n==================================================== \n"
                         + "====================================================");   
       System.out.println("PREGUNTA 18: Para un grafo dirigido implementar al algoritmo de ordenamiento topológico. Debe mostrar\n" +
                            "cual es el orden de los vértices según este algoritmo.");
                System.out.println("Digrafo no Pesado:\n"+g3+"\nEl orden de los vertices segun el algoritmo de Ordenamiento Topologico es:\n"+g3.OrdenamientoTopologico());

        
      
    }

    }

