/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectooperativosbmwbaladiinvernon;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Fernando Baladi
 */
public class Gerente extends Thread{

    Semaphore JG;
    int dia;
    ContadorDias contador;
    boolean despacha = false;
    Almacen carros;
    public Gerente(Semaphore JG, int dia, ContadorDias contador, Almacen carros) {
        this.JG = JG;
        this.dia = dia;
        this.contador = contador;
        this.carros = carros;
    }
    
    @Override
    public void run(){
        while(true){
            try{
                this.JG.acquire();
                if (contador.getContadorDias()!=0) {
                    this.JG.release();
                    Thread.sleep(tiempoDurmiendo());
                }else{
                    despacha = true;
                    this.carros.setCarros(0);
                    System.out.println("Nos quedamos sin conta");
                    Thread.sleep(tiempoDurmiendo());
                    despacha = false;
                }
            }catch(InterruptedException ex){
                System.out.println("Mamaste");
            }
        }
    }
    
    
    public int tiempoDurmiendo(){
        int n = (int)(Math.random() * 13 + 6);
        double f =((double)n/24);
        int t =  (int)(this.dia * f);
        return t;
    }
}
