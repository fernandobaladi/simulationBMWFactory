/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectooperativosbmwbaladiinvernon;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Fernando Baladi
 */
public class ProyectoOperativosBMWBaladiInvernon {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
       
         Vista v = new Vista();
        v.setVisible(true);
        v.setResizable(false);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.setLocationRelativeTo(null);
        
    /*NOT PARKING BALLS A LO QUE ESCRIBÍ AQUÍ
    public static void contrataProdRueda(Semaphore semR, Semaphore semER, Productor[] prod){
        Productor rueda1 = new Productor(semR, "r"," una rueda", semER,1, 1000);
        for (int i = 0; i < prod.length; i++) {
            if (prod[i]!=null) {
                
            }else{
                
            }
        }
    }
    public static void contrataProdMotor(Semaphore semM, Semaphore semEM){
        Productor rueda1 = new Productor(semM, "m"," un motor", semEM,1, 1000);
        rueda1.start();
    }
    public static void contrataProdParabrisa(Semaphore semP, Semaphore semEP){
        Productor rueda1 = new Productor(semP, "p"," un parabrisas", semEP,1, 1000);
        rueda1.start();
    }
    
    public static void despedir(Productor[] prod){
        if (prod.length == 1) {
            
        }else{
            for (int i = prod.length-1; i > 0; i--) {
                if(prod[i]!=null) {
                    prod[i].setContratado(false);
                }
            }
        }
    }
    public static void despedir(Ensambladores[] ensam){
        if (ensam.length == 1) {
            
        }else{
            for (int i = ensam.length-1; i > 0; i--) {
                if(ensam[i]!=null) {
                    ensam[i].setContratado(false);
                }
            }
        }
    }*/
    }
    
}
