/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectooperativosbmwbaladiinvernon;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Fernando Baladi
 */
public class Jefe extends Thread {
    
    int dia, tiempoDeCambio, diasParaDespacho;
    int contador;
    Semaphore JG;
    
    public Jefe(Semaphore JG, int dia, int contador, int diasParaDespacho){
        this.dia = dia;
        this.JG = JG;
        this.contador = contador;
        this.tiempoDeCambio = dia/24;
        this.diasParaDespacho = diasParaDespacho;
    }
    
    public void run(){
        while(true){
            try{
                while(this.JG.availablePermits()!=0){
                    this.JG.acquire();
                        Thread.sleep(tiempoDeCambio);
                        if(this.contador != 0){
                            this.contador--;
                            System.out.println("Faltan " + this.contador + " días para despachar.");
                        }else{
                           this.contador = this.diasParaDespacho;
                        }
                }
                this.JG.release();
                Thread.sleep(this.dia - this.tiempoDeCambio);
            
            }catch(InterruptedException ex){
                System.out.println("Mamaste");
            }
        }
    }
}
