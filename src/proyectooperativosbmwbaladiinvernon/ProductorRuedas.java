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
public class ProductorRuedas extends Thread{
    String nombre;
    Semaphore semR;
    Semaphore semER;
    
    public ProductorRuedas(Semaphore sem, String nombre, Semaphore sem2) {
        this.semR = sem;
        this.nombre = nombre;
        this.semER = sem2;
    }

    public void run(){
        while(true){
            try{
                this.semR.acquire();
                System.out.println("produje una rueda " + nombre);
                this.semER.release();
                Thread.sleep(1000);
            }catch(InterruptedException ex){
                System.out.println("Mamaste");
            }
        }
    }
    
    
}
