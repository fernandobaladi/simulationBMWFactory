/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectooperativosbmwbaladiinvernon;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando Baladi
 */
public class ProductorDeRuedas extends Thread{
    boolean[] capacidadRuedas = new boolean[30];
    int f = 1;
    boolean ruedaCreada = false; 
    @Override
    public void run(){
        while(f == 1){
            for (int i = 0; i < capacidadRuedas.length; i++) {
                if(!ruedaCreada && !capacidadRuedas[i]){
                    capacidadRuedas[i] = true;
                    ruedaCreada = true;
                    System.out.println("Produje una rueda " + i);
                }else if(ruedaCreada){
                    i = 40;
                }
            }
            ruedaCreada = false;
            
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ProductorDeRuedas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
