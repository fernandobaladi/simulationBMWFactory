/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectooperativosbmwbaladiinvernon;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando Baladi
 */
public class ProductorDeParabrisas extends Thread{
    
    boolean[] capacidadParabrisas = new boolean[25];
    int f = 1;
    int contadorDias = 0;
    boolean parabrisaCreado = false; 
    @Override
    public void run(){
        while(f == 1){
            if (contadorDias == 1) {
                
            
                for (int i = 0; i < capacidadParabrisas.length; i++) {
                    if(!parabrisaCreado && !capacidadParabrisas[i]){
                        capacidadParabrisas[i] = true;
                        parabrisaCreado = true;
                        System.out.println("Produje un parabrisas " + i);
                    }else if(parabrisaCreado){
                        i = 40;
                    }
                }
                parabrisaCreado = false;
                contadorDias = 0;
            }else{
                contadorDias++;
            }
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ProductorDeRuedas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
