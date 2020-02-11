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

/**
 *
 * @author Fernando Baladi
 */
public class ProyectoOperativosBMWBaladiInvernon {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        Semaphore semR = new Semaphore(30);
        Semaphore semER = new Semaphore(-3);
        Semaphore semM = new Semaphore(25);
        Semaphore semEM = new Semaphore(0);

        ProductorRuedas e = new ProductorRuedas(semR, "e", semER);
        ProductorRuedas e2 = new ProductorRuedas(semR, "e2", semER);
        ProductorMotores e3 = new ProductorMotores(semM, "e3", semEM);
        Ensambladores epa = new Ensambladores(semR, "epa", semER, semM, semEM);

        e.start();
        e2.start();
        e3.start();
        epa.start();

    }
    
  
}
