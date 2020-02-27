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

    Semaphore JG, GE;
    boolean leyo = false;
    public Gerente(Semaphore JG, Semaphore GE) {
        this.JG = JG;
        this.GE = GE;
    }
    
    @Override
    public void run(){
        while(true){
            try{
                Fabrica.estadoGerente = ("Esperando");
                this.JG.acquire();
                this.leyo = true;
                Fabrica.estadoGerente = ("Leyendo");
                if (Fabrica.diasParaDespacho == 0) {
                    this.GE.acquire();
                    Fabrica.estadoGerente = ("Despachando");
                    Fabrica.contadorCarrosProducidos = 0;
                    Fabrica.diasParaDespacho = Fabrica.diasParaDespachoEstatico;
                    this.GE.release();
                }       
                this.JG.release();
                if (leyo) {
                    Fabrica.estadoGerente = ("Durmiendo");
                    //System.out.println(Fabrica.estadoGerente);
                    Thread.sleep(tiempoDurmiendo());
                    leyo = false;
                }    
            }catch(InterruptedException ex){
                System.out.println("Mamaste");
            }
        }
    }
    
    
    public int tiempoDurmiendo(){
        int n = (int)(Math.random() * 13 + 6);
        double f =((double)n/24);
        int t =  (int)(Fabrica.duracionDelDia * f);
        return t;
    }
}
