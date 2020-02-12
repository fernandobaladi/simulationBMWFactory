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

    Semaphore JG;
    int dia;
    int contador;
    
    public Gerente(Semaphore JG, int dia, int contador) {
        this.JG = JG;
        this.dia = dia;
        this.contador = contador;
    }
    
    public void run(){
        while(true){
            try{
                
                if(this.JG.availablePermits() == 1){
                    this.JG.acquire();
                    if (contador!=0) {
                        System.out.println("conta" + contador);
                        System.out.println(tiempoDurmiendo());
                        System.out.println("Ya chequeé");
                        this.JG.release();
                        Thread.sleep(tiempoDurmiendo());
                    }else{
                        System.out.println("Nos quedamos sin conta");
                    }
                    
                    
                }
            }catch(InterruptedException ex){
                System.out.println("Mamaste");
            }
        }
    }
    
    
    public int tiempoDurmiendo(){
        int n = (int)(Math.random() * 13 + 6);
        double f =(n/24);
        System.out.println(f);
        int t = this.dia * (int)f;
        return t;
    }
}
