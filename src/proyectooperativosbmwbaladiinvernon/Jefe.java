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
    ContadorDias contador;
    Semaphore JG;
    
    public Jefe(Semaphore JG, int dia, ContadorDias contador, int diasParaDespacho){
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
                        if(this.contador.getContadorDias()!=0){
                            this.contador.setContadorDias(this.contador.getContadorDias()-1);
                            System.out.println("Faltan " + this.contador.getContadorDias() + " días para despachar.");
                        }else{
                           this.contador.setContadorDias(this.diasParaDespacho);
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
