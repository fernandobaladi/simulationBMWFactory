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
public class ProductorDeMotores extends Thread {
    boolean[] capacidadMotor = new boolean[15];
    int f = 1;
    int contadorDias = 0;
    boolean motorCreado = false; 
    @Override
    public void run(){
        while(f == 1){
            if (contadorDias == 2) {
                
            
                for (int i = 0; i < capacidadMotor.length; i++) {
                    if(!motorCreado && !capacidadMotor[i]){
                        capacidadMotor[i] = true;
                        motorCreado = true;
                        System.out.println("Produje un parabrisas " + i);
                    }else if(motorCreado){
                        i = 40;
                    }
                }
                motorCreado = false;
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
