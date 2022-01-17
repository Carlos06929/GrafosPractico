 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.nopesados;

/**
 *
 * @author Usuario
 */
public class FuertementeConexo {
    private Conexo grafoConexo;
    private Grafo unDigrafo;
    
    /*
    ===========================================================
    -----------------PREGUNTA 8--------------------------------
    ===========================================================
    */
    
    public FuertementeConexo(Grafo unDigrafo){
        this.unDigrafo=unDigrafo;
        this.grafoConexo=new Conexo(unDigrafo);
    }
    
    public boolean esFuertementeConexo(){
        return this.grafoConexo.esConexo();
    }
     public boolean esFuertementeConexo1(){
         DFS recorrido=new DFS(unDigrafo, 0);
         return recorrido.hayCaminoATodos();
     }
    
}
