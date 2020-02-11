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
public class ProductorMotores extends Thread{
    String nombre;
    Semaphore semM;
    Semaphore semEM;

    public ProductorMotores(Semaphore sem, String nombre, Semaphore sem2) {
        this.semM = sem;
        this.nombre = nombre;
        this.semEM = sem2;
    }

    public void run(){
        while(true){
            try{
                this.semM.acquire();
                System.out.println("produje un motor " + nombre);
                this.semEM.release();
                Thread.sleep(2000);
            }catch(InterruptedException ex){
                System.out.println("Mamaste");
            }
        }
    }
}
