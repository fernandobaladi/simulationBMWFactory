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
public class Productor extends Thread{
    String nombre, objeto;
    Semaphore sem,  semE;
    int dias, diasDeProduccion, tiempoProducción;
    public Productor(Semaphore sem, String nombre, String objeto, Semaphore sem2, int dias, int diasDeProduccion) {
        this.sem = sem;
        this.nombre = nombre;
        this.semE = sem2;
        this.objeto = objeto;
        this.tiempoProducción = dias * diasDeProduccion;
    }

    public void run(){
        while(true){
            try{
                Thread.sleep(tiempoProducción);
                this.sem.acquire();
                System.out.println("Produje "+ objeto+ " " + nombre+".");
                this.semE.release();
            }catch(InterruptedException ex){
                System.out.println("Mamaste");
            }
        }
    }
    
    
}
