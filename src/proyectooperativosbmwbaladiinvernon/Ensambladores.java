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
    Semaphore semR, semER, semM, semEM, semEP, semP, semMG, semPG, semRG, semGE;
    boolean contratado, produje;
    public Ensambladores(Semaphore sem, String nombre, Semaphore sem2, Semaphore sem3, Semaphore sem4, 
            Semaphore sem5, Semaphore sem6, Semaphore sem7, Semaphore sem8, Semaphore sem9, Semaphore sem10) {
        this.semR = sem;
        this.nombre = nombre;
        this.semER = sem2;
        this.semM = sem3;
        this.semEM = sem4;
        this.semP = sem5;
        this.semEP = sem6;
        this.semRG = sem7;
        this.semPG = sem8;
        this.semMG = sem9;
        this.semGE = sem10;
        this.contratado = true;
        this.produje = false;
    }

    public void run(){
        while(contratado){
            try{
                
                this.semER.acquire(4);
                this.semEM.acquire();
                this.semEP.acquire();
                
                this.semRG.acquire();
                this.semPG.acquire();
                this.semMG.acquire();
                
                this.produje = true;
                    for (int i = 0; i < 4; i++) {
                        Fabrica.almacenRuedas[Fabrica.contadorRuedasConsumidas%Fabrica.almacenRuedas.length]= false;
                        Fabrica.contadorRuedasConsumidas++;
                        Fabrica.contadorRuedasProducidasVistas--;
                    }

                    Fabrica.almacenParabrisas[Fabrica.contadorParabrisasConsumidos%Fabrica.almacenParabrisas.length] = false;
                    Fabrica.contadorParabrisasConsumidos++;
                    Fabrica.contadorParabrisasProducidosVista--;
                    /*System.out.println("-----------------------------------");
                    System.out.println("Se han consumido " + Fabrica.contadorParabrisasConsumidos + " parabrisas");
                    System.out.println("Se muestran " + Fabrica.contadorParabrisasProducidosVista+ " parabrisas");
                    System.out.println("Se han hecho " + Fabrica.contadorParabrisasProducidos + " parabrisas");
                    System.out.println("Se puede entrar " + this.semEP + " veces más (Semáforo ensamblador)");
                    System.out.println("Se puede entrar " + this.semPG + " veces más (Semáforo exclusión mutua)");
                    System.out.println("Se puede entrar " + this.semP + " veces más (Semáforo productor)");
                    System.out.println("-----------------------------------");*/
                    Fabrica.almacenMotores[Fabrica.contadorMotoresConsumidos%Fabrica.almacenMotores.length] = false;
                    Fabrica.contadorMotoresConsumidos++;
                    Fabrica.contadorMotoresProducidosVista--;
                    System.out.println("-----------------------------------");
                    System.out.println("Se han consumido " + Fabrica.contadorMotoresConsumidos + " motores");
                    System.out.println("Se muestran " + Fabrica.contadorMotoresProducidosVista+ " motores");
                    System.out.println("Se han hecho " + Fabrica.contadorMotoresProducidos + " motores");
                    for (int i = 0; i < Fabrica.almacenMotores.length; i++) {
                        System.out.println("Numero "+i+": "+Fabrica.almacenMotores[i]);
                    }
                    System.out.println("hola:");
                    System.out.println("Se puede entrar " + this.semEM + " veces más (Semáforo ensamblador)");
                    System.out.println("Se puede entrar " + this.semMG + " veces más (Semáforo exclusión mutua)");
                    System.out.println("Se puede entrar " + this.semM + " veces más (Semáforo productor)");
                    System.out.println("-----------------------------------");
                
                this.semMG.release();
                this.semPG.release();
                this.semRG.release();
                
                this.semP.release();                
                this.semM.release();
                this.semR.release(4);
                
                if (this.produje) {
                    Thread.sleep(Fabrica.diasDeEnsamblaje*Fabrica.duracionDelDia);
                    this.semGE.acquire();
                    Fabrica.contadorCarrosProducidos++;
                    this.semGE.release();
                    this.produje = false;
                    
                }
                
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
