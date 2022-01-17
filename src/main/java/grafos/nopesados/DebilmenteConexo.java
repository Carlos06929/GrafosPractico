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
public class DebilmenteConexo extends DFS{
    //private Conexo GrafoConexo;
    
    public DebilmenteConexo(Grafo unDigrafo){
        //GrafoConexo=new Conexo(unDigrafo);
       super(unDigrafo,0); 
    }
    //RECONOCER SI ES UN VERTICE DEBILMENTE CONEXO 
    //tambien se podria hacer el debilmente conexo con dos for dentro del codigo
    //uno para buscar el vertice no marcado despues del recorrido y el segundo para
    //encontrar si el vertice no marcado tiene un vertice marcado y si no fuera asi 
    //retornar falso por que ya no seria debilmente conexo
    
    /*
    ===========================================================
    -----------------PREGUNTA 7--------------------------------
    ===========================================================
    */
    public boolean esDebilmenteConexo(){
        
        if (!hayCaminoATodos()) {
            int i=this.posVerticeNoMarcado();
            while(i!=-1){
            
                if (this.tieneVertMarcado(i)) {
                    this.continuarDFS(i);
                    i=this.posVerticeNoMarcado();  
                }else{
                    return false;
                }
 
            }
        }
        return true;
    }
}
