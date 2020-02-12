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
public class Ensambladores extends Thread {
    String nombre;
    Semaphore semR;
    Semaphore semER;
    Semaphore semM;
    Semaphore semEM;
    Semaphore semEP;
    Semaphore semP;
    int carrosProducidos = 0;
    public Ensambladores(Semaphore sem, String nombre, Semaphore sem2, Semaphore sem3, Semaphore sem4, Semaphore sem5, Semaphore sem6) {
        this.semR = sem;
        this.nombre = nombre;
        this.semER = sem2;
        this.semM = sem3;
        this.semEM = sem4;
        this.semP = sem5;
        this.semEP = sem6;
    }

    public void run(){
        while(true){
            try{
                this.semER.acquire(4);
                this.semEM.acquire();
                this.semEP.acquire();
                System.out.println("produje un carro " + nombre);
                this.carrosProducidos++;
                System.out.println("Llevamos " + carrosProducidos + " producidos");
                this.semR.release(4);
                this.semM.release();
                this.semP.release();
                Thread.sleep(2000);
            }catch(InterruptedException ex){
                System.out.println("Mamaste");
            }
        }
    }
}
