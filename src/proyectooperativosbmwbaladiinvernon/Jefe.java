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
    
    int tiempoDeCambio, contador;
    Semaphore JG;
    boolean modifica = false;
    public Jefe(Semaphore JG){
        this.JG = JG;
        this.tiempoDeCambio = (Fabrica.duracionDelDia*3)/48;
    }
    
    public void run(){
        while(true){
            try{
                Fabrica.estadoJefe = ("Esperando");
                this.JG.acquire();
                    this.modifica = true;    
                    Fabrica.estadoJefe = ("Modificando");
                    Thread.sleep(tiempoDeCambio);
                    if(Fabrica.diasParaDespacho != 0){
                        Fabrica.diasParaDespacho--;
                        //System.out.println("Faltan " + this.contador + " días para despachar.");
                    }else{
                        Fabrica.diasParaDespacho = Fabrica.diasParaDespachoEstatico;
                    }
                this.JG.release();
                if (modifica) {
                    this.modifica = false;
                    Fabrica.estadoJefe = ("Durmiendo");
                    Thread.sleep(Fabrica.duracionDelDia - this.tiempoDeCambio);
                }
            
            }catch(InterruptedException ex){
                System.out.println("Mamaste");
            }
        }
    }
}
