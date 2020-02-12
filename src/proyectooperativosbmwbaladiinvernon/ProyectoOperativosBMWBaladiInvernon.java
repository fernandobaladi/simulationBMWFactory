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
        Semaphore semP = new Semaphore(15);
        Semaphore semEM = new Semaphore(0);
        Semaphore semEP = new Semaphore(0);
        Semaphore semJG = new Semaphore(1);
        int contador = 10;
        Productor rueda1 = new Productor(semR, "r"," una rueda", semER,1, 1000);
        Productor rueda2 = new Productor(semR, "r", " una rueda", semER,1, 1000 );
        Productor motor = new Productor(semM, "m", " un motor", semEM, 3, 1000);
        Productor parabrisas = new Productor(semP, "p", " un parabrisas", semEP, 2, 1000);
        Ensambladores epa = new Ensambladores(semR, "epa", semER, semM, semEM, semP, semEP);
        Gerente ger = new Gerente(semJG, 1000, contador);
        Jefe jef = new Jefe();
        
        //ger.start();
        rueda1.start();
        rueda2.start();
        motor.start();
        parabrisas.start();
        epa.start();
    }
    
  
}
