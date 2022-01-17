/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo.pesados;

/**
 *
 * @author Usuario
 */
public class NodoKruskal implements Comparable<NodoKruskal>{
    
    private int origen;
    private int destino;
    private double peso;
    
    public NodoKruskal(){
    
    }
    
    
    public NodoKruskal(int origen,int destino){
        this.origen=origen;
        this.destino=destino;
    }
    
    public NodoKruskal(int origen,int destino,double peso){
        this.origen=origen;
        this.destino=destino;
        this.peso=peso;
    }

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public int getDestino() {
        return destino;
    }

    public void setDestino(int destino) {
        this.destino = destino;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
    
   

    @Override
    public String toString() {
        return "[nodoKruskal=" + origen +","+destino+"-> ConPeso=" + peso + ']';
    }
    
    @Override
    public int compareTo(NodoKruskal vert) {
        double estePeso=this.peso;
        double elOtroPeso=vert.peso;
        if (estePeso<elOtroPeso) {
            return -1;
        }else if(elOtroPeso<estePeso){
            return 1;
        }
        return 0;
    }
    
    @Override
    public int hashCode(){
        int hash=3;
        hash=67*hash+this.origen;
        return hash;
    }
    
    @Override
    public boolean equals(Object otro){
        if (otro==null){
            return false;
        }if(getClass()!=otro.getClass()){
            return false;
        }
        NodoKruskal other=(NodoKruskal)otro;
        return this.peso==other.peso; 
    }
}
