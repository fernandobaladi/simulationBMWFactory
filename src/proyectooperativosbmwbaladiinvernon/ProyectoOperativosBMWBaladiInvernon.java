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
        Productor[] prodRuedas = new Productor[5];
        Productor[] prodParabrisas = new Productor[3];
        Productor[] prodMotores = new Productor[3];
        Ensambladores[] ensambladoresA = new Ensambladores[3];
        int contador = 10;
        Almacen carrosProducidos = new Almacen();
        ContadorDias diasParaDespacho = new ContadorDias();
        diasParaDespacho.setContadorDias(10);
        prodRuedas[0] = new Productor(semR, "r"," una rueda", semER,1, 1000);
        prodRuedas[1] = new Productor(semR, "r2", " una rueda", semER,1, 1000);
        prodRuedas[2] = new Productor(semR, "r3", " una rueda", semER,1, 1000);
        prodRuedas[3] = new Productor(semR, "r4", " una rueda", semER,1, 1000);
        prodMotores[0] = new Productor(semM, "m", " un motor", semEM, 3, 1000);
        prodMotores[1] = new Productor(semM, "m2", " un motor", semEM, 3, 1000);
        prodMotores[2] = new Productor(semM, "m3", " un motor", semEM, 3, 1000);
        prodParabrisas[0] = new Productor(semP, "p", " un parabrisas", semEP, 2, 1000);
        prodParabrisas[1] = new Productor(semP, "p2", " un parabrisas", semEP, 2, 1000);
        prodParabrisas[2] = new Productor(semP, "p3", " un parabrisas", semEP, 2, 1000);
        ensambladoresA[0] = new Ensambladores(semR, "epa", semER, semM, semEM, semP, semEP, carrosProducidos);
        ensambladoresA[1] = new Ensambladores(semR, "epa2", semER, semM, semEM, semP, semEP, carrosProducidos);
        ensambladoresA[2] = new Ensambladores(semR, "epa3", semER, semM, semEM, semP, semEP, carrosProducidos);
        Gerente ger = new Gerente(semJG, 1000, diasParaDespacho, carrosProducidos);
        Jefe jef = new Jefe(semJG, 1000, diasParaDespacho, 10);
        
        
        for (int i = 0; i < prodRuedas.length; i++) {
            if (prodRuedas[i]!=null) {
                prodRuedas[i].start();
            }
        }
        
        for (int i = 0; i < prodMotores.length; i++) {
            if (prodMotores[i]!=null) {
                prodMotores[i].start();
            }
        }
        for (int i = 0; i < prodParabrisas.length; i++) {
            if (prodParabrisas[i]!=null) {
                prodParabrisas[i].start();
            }
        }
        for (int i = 0; i < ensambladoresA.length; i++) {
            if (ensambladoresA[i]!=null) {
                ensambladoresA[i].start();
            }
        }
        ger.start();
        jef.start();
    }
    
    public static void contrataProdRueda(Semaphore semR, Semaphore semER, Productor[] prod){
        Productor rueda1 = new Productor(semR, "r"," una rueda", semER,1, 1000);
        prod[0]= rueda1;
        rueda1.start();
    }
    public static void contrataProdMotor(Semaphore semR, Semaphore semER){
        Productor rueda1 = new Productor(semR, "r"," una rueda", semER,1, 1000);
        rueda1.start();
    }
    public static void contrataProdParabrisa(Semaphore semR, Semaphore semER){
        Productor rueda1 = new Productor(semR, "r"," una rueda", semER,1, 1000);
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
    }
    
}
