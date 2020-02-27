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
public class Productor extends Thread{
    
    String nombre, objeto;
    Semaphore sem,  semE, semEX;
    int dias, diasDeProduccion, tiempoProducción;
    boolean contratado = true;
    boolean[] almacen;
    int tipoDePieza;
    
    public Productor(Semaphore sem, String nombre, String objeto, Semaphore sem2,
            int dias, int diasDeProduccion, Semaphore semEX, boolean[] almacen, 
            int tipoDePieza) {
        this.sem = sem;
        this.nombre = nombre;
        this.semE = sem2;
        this.objeto = objeto;
        this.tiempoProducción = dias * diasDeProduccion;
        this.semEX = semEX;
        this.almacen = almacen;
        this.tipoDePieza = tipoDePieza;
    }

    public void run(){
        
        while(contratado){
            try{
                
                this.sem.acquire();
                Thread.sleep(tiempoProducción);
                this.semEX.acquire();
                
                //if (this.tipoDePieza==1 && contratado) {
                if (this.tipoDePieza==1) {
                    Fabrica.almacenRuedas[(Fabrica.contadorRuedasProducidas%Fabrica.almacenRuedas.length)] = true;
                    Fabrica.contadorRuedasProducidasVistas++;
                    Fabrica.contadorRuedasProducidas++;
                    
                    //System.out.println(Fabrica.contadorRuedasProducidas + " de " + objeto);
                    //System.out.println(this.sem.availablePermits());
                }

                //if (this.tipoDePieza==2 && contratado) {
                if (this.tipoDePieza==2) {
                    Fabrica.almacenMotores[(Fabrica.contadorMotoresProducidos%Fabrica.almacenMotores.length)] = true;
                    Fabrica.contadorMotoresProducidosVista++;
                    Fabrica.contadorMotoresProducidos++;
                    System.out.println("***************************************");
                    System.out.println("Se han consumido " + Fabrica.contadorMotoresConsumidos + " motores");
                    System.out.println("Se muestran " + Fabrica.contadorMotoresProducidosVista+ " motores");
                    System.out.println("Se han hecho " + Fabrica.contadorMotoresProducidos + " motores");
                    for (int i = 0; i < Fabrica.almacenMotores.length; i++) {
                        System.out.println("Numero"+i+": "+ Fabrica.almacenMotores[i]);
                    }
                    System.out.println("Se puede entrar " + this.semE + " veces más (Semáforo ensamblador)");
                    System.out.println("Se puede entrar " + this.semEX + " veces más (Semáforo exclusión mutua)");
                    System.out.println("Se puede entrar " + this.sem + " veces más (Semáforo productor)");
                    System.out.println("***************************************");
                    //System.out.println( Fabrica.contadorMotoresProducidos + " de " + objeto);
                    //System.out.println(this.sem.availablePermits());
                }
                
                //if (this.tipoDePieza==3 && contratado) {
                if (this.tipoDePieza==3 ) {
                    Fabrica.almacenParabrisas[(Fabrica.contadorParabrisasProducidos%Fabrica.almacenParabrisas.length)] = true;
                    Fabrica.contadorParabrisasProducidosVista++;
                    Fabrica.contadorParabrisasProducidos++;
                    //ystem.out.println( Fabrica.contadorParabrisasProducidos + " de " + objeto);
                    //System.out.println(this.sem.availablePermits());                
                }
                
                this.semEX.release();
                this.semE.release();
            }catch(InterruptedException ex){
                System.out.println("Mamaste");
            }
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public Semaphore getSem() {
        return sem;
    }

    public void setSem(Semaphore sem) {
        this.sem = sem;
    }

    public Semaphore getSemE() {
        return semE;
    }

    public void setSemE(Semaphore semE) {
        this.semE = semE;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public int getDiasDeProduccion() {
        return diasDeProduccion;
    }

    public void setDiasDeProduccion(int diasDeProduccion) {
        this.diasDeProduccion = diasDeProduccion;
    }

    public int getTiempoProducción() {
        return tiempoProducción;
    }

    public void setTiempoProducción(int tiempoProducción) {
        this.tiempoProducción = tiempoProducción;
    }

    public boolean isContratado() {
        return contratado;
    }

    public void setContratado(boolean contratado) {
        this.contratado = contratado;
    }
    
    
}
