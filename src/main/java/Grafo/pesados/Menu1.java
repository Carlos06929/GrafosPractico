/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo.pesados;

import grafos.Excepciones.ExcepcionAristaNoExiste;
import grafos.Excepciones.ExcepcionAristaYaExiste;
import grafos.Excepciones.ExcepcionNroVerticesInvalido;

/**
 *
 * @author Roberto
 */
public class Menu1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste {
        // TODO code application logic here
        
        GrafoPesado g=new GrafoPesado();
        GrafoPesado p3=new GrafoPesado(5);
        p3.insertarArista(0, 1,10);
        p3.insertarArista(1, 2,15);
        p3.insertarArista(2, 3,20);
        p3.insertarArista(3, 1,50);
        p3.insertarArista(2, 4,40);
        p3.insertarArista(0, 4,40);
       
        
        g=p3.Kruskal();
        System.out.println(g);
        
        
        /*GrafoPesado p=new DigrafoPesado(5);
        p.insertarArista(0, 1,1);
        p.insertarArista(1, 4,7);
        p.insertarArista(1, 3,4);
        p.insertarArista(2, 0,3);
        p.insertarArista(2, 1,2);
        p.insertarArista(2,4 ,4);
        p.insertarArista(3, 0,6);
        p.insertarArista(3, 4,2);
        p.insertarArista(4, 3,3);



        System.out.println(p.prim());
        Matriz m1=new Matriz(p);
        m1.matriAdy();
        m1.algoritmoFloid();
       m1.matriAdy();
        m1.matrizPred();
        System.out.println(m1.caminoCostoMinimo(0, 4));*/
        
        
        
        DigrafoPesado p=new DigrafoPesado(8);
        p.insertarArista(0, 1,20);
        p.insertarArista(0, 2,40);
        p.insertarArista(0, 3,10);
        p.insertarArista(1, 4,50);
        p.insertarArista(2, 1,20);
        p.insertarArista(2, 6,20);
        p.insertarArista(3,5,20);
        p.insertarArista(3, 6,30);
        p.insertarArista(4, 5,15);
        p.insertarArista(4, 7,70);
        p.insertarArista(5, 4,45);
        p.insertarArista(5, 7,40);
        p.insertarArista(6, 7,10);
        
        p.Dikstra(0, 4);
        
        
      //  System.out.println( p.algoritmoFordFulkerson(0,7));
        
    }
    
    
   
}
