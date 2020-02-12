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
    Semaphore semR, semER, semM, semEM, semEP, semP;
    boolean contratado = true;
    Almacen carrosProducidos;
    public Ensambladores(Semaphore sem, String nombre, Semaphore sem2, Semaphore sem3, Semaphore sem4, Semaphore sem5, Semaphore sem6, Almacen carrosProducidos) {
        this.semR = sem;
        this.nombre = nombre;
        this.semER = sem2;
        this.semM = sem3;
        this.semEM = sem4;
        this.semP = sem5;
        this.semEP = sem6;
        this.carrosProducidos = carrosProducidos;
    }

    public void run(){
        while(contratado){
            try{
                Thread.sleep(2000);
                this.semER.acquire(4);
                this.semEM.acquire();
                this.semEP.acquire();
                System.out.println("produje un carro " + nombre);
                this.carrosProducidos.setCarros(this.carrosProducidos.getCarros()+1);
                System.out.println(this.carrosProducidos.getCarros());
//                System.out.println("Llevamos " + carrosProducidos + " producidos por "+ this.nombre);
                this.semR.release(4);
                this.semM.release();
                this.semP.release();
            }catch(InterruptedException ex){
                System.out.println("Mamaste");
            }
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isContratado() {
        return contratado;
    }

    public void setContratado(boolean contratado) {
        this.contratado = contratado;
    }
    
    
}
