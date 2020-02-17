/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectooperativosbmwbaladiinvernon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.Semaphore;
import javax.swing.JFrame;

/**
 *
 * @author Fernando Baladi
 */
public final class Fabrica {
    
    public VariablesVista variablesVarias;
    public static volatile boolean[] almacenRuedas;
    public static volatile boolean[] almacenMotores;
    public static volatile boolean[] almacenParabrisas;
    public static int disponibilidadMaximaRuedas;
    public static int disponibilidadMaximaParabrisas;
    public static int disponibilidadMaximaMotores;
    public static volatile int contadorRuedasProducidas;
    public static volatile int contadorMotoresProducidos;
    public static volatile int contadorParabrisasProducidos;
    public static volatile int contadorRuedasConsumidas;
    public static volatile int contadorMotoresConsumidos;
    public static volatile int contadorParabrisasConsumidos;
    public static volatile int contadorCarrosProducidos;
    public static volatile int diasParaDespacho;
    public static int diasParaDespachoEstatico;
    public static int duracionDelDia;
    public static int diasDeEnsamblaje;
    public static int diasDeProduccionMotor;
    public static int diasDeProduccionRueda;
    public static int diasDeProduccionParabrisas;
    public static int productoresRuedasIniciales;
    public static int productoresMotoresIniciales;
    public static int productoresParabrisasIniciales;
    public static int ensambladoresIniciales;
    public static int ensambladoresActuales;
    public static int productoresRuedasActuales;
    public static int productoresMotoresActuales;
    public static int productoresParabirsasActuales;
    File archivoBase = new File("archivoBase.txt"); 
    public static Productor[] prodRuedas;
    public static Productor[] prodParabrisas;
    public static Productor[] prodMotores;
    public static Ensambladores[] ensambladoresA;
    public static Vista vista;
    public Gerente ger;
    public Jefe jef;
    
    public Fabrica() {
        
        
        this.LeerInformacion();     
        
        variablesVarias = new VariablesVista(ensambladoresActuales, 
        productoresRuedasActuales, productoresMotoresActuales, 
        productoresParabirsasActuales, contadorRuedasProducidas, 
        contadorMotoresProducidos, contadorParabrisasProducidos, 
        contadorCarrosProducidos, diasParaDespacho);
        contadorCarrosProducidos = 0;
        contadorRuedasProducidas = 0;
        contadorRuedasConsumidas = 0;
        contadorParabrisasConsumidos = 0;
        contadorParabrisasProducidos = 0;
        contadorMotoresConsumidos = 0;
        contadorMotoresProducidos = 0;
        
        
        productoresMotoresActuales = productoresMotoresIniciales;
        productoresParabirsasActuales = productoresParabrisasIniciales;
        productoresRuedasActuales = productoresRuedasIniciales;
        ensambladoresActuales = ensambladoresIniciales;
        
        //Días de producción por productor
        diasDeProduccionMotor = 3;
        diasDeProduccionParabrisas = 2;
        diasDeProduccionRueda = 1;
        diasDeEnsamblaje = 2;
        
        almacenRuedas = new boolean[disponibilidadMaximaRuedas];
        almacenParabrisas = new boolean[disponibilidadMaximaParabrisas];
        almacenMotores = new boolean[disponibilidadMaximaMotores];
        System.out.println(ensambladoresIniciales);
        System.out.println(disponibilidadMaximaRuedas);
        //Semáforos de exclusión mutua
        Semaphore semRG = new Semaphore(1);
        Semaphore semMG = new Semaphore(1);
        Semaphore semPG = new Semaphore(1);
        
        //semáforos de control
        Semaphore semR = new Semaphore(disponibilidadMaximaRuedas);
        System.out.println(semR.availablePermits());
        Semaphore semER = new Semaphore(-3);
        Semaphore semM = new Semaphore(disponibilidadMaximaMotores);
        Semaphore semP = new Semaphore(disponibilidadMaximaParabrisas);
        Semaphore semEM = new Semaphore(0);
        Semaphore semEP = new Semaphore(0);
        
        //Semáforos gerenciales
        Semaphore semGE = new Semaphore(1);
        Semaphore semJG = new Semaphore(1);
        
        
        vista = new Vista();
        vista.setVisible(true);
        vista.setResizable(false);
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setLocationRelativeTo(null);
        vista.WheelMakerjTextField.setText(String.valueOf(productoresRuedasActuales));
        vista.WindshieldMakerjTextField.setText(String.valueOf(productoresParabirsasActuales));
        vista.EngineMakerjTextField.setText(String.valueOf(productoresMotoresActuales));
        vista.CarMakerjTextField.setText(String.valueOf(ensambladoresActuales));
        vista.WheelQuantityjTextField.setText(String.valueOf(contadorRuedasProducidas));
        vista.WindshieldQuantityjTextField.setText(String.valueOf(contadorParabrisasProducidos));
        vista.EngineQuantityjTextField.setText(String.valueOf(contadorMotoresProducidos));
        vista.CarQuantityjTextField.setText(String.valueOf(contadorCarrosProducidos));
        
        for (int i = 0; i < productoresRuedasIniciales; i++) {
            System.out.println(i);
            System.out.println(productoresRuedasIniciales);
            prodRuedas[i] = new Productor(semR, "r"," una rueda", semER, 
                    diasDeProduccionRueda, duracionDelDia, semRG, almacenRuedas,1);    
        }
        
        for (int i = 0; i < productoresMotoresIniciales; i++) {
            prodMotores[i] = new Productor(semM, "m", " un motor", semEM, 
                    diasDeProduccionMotor, duracionDelDia, semMG,  almacenMotores,2);
        }
        
        for (int i = 0; i < productoresParabrisasIniciales; i++) {
            prodParabrisas[i] = new Productor(semP, "p", " un parabrisas", semEP, 
                    diasDeProduccionParabrisas, duracionDelDia, semPG,  
                    almacenParabrisas,3);
        }
        
        for (int i = 0; i < ensambladoresIniciales; i++) {
            ensambladoresA[i] = new Ensambladores(semR, "epa", semER, semM, semEM, 
                semP, semEP, semRG, semPG, semMG, semGE, contadorCarrosProducidos, 
                almacenRuedas, almacenParabrisas, almacenMotores, contadorRuedasConsumidas, 
                contadorParabrisasConsumidos, contadorMotoresConsumidos);    
        }

        ger = new Gerente(semJG, duracionDelDia, diasParaDespacho);
        jef = new Jefe(semJG, duracionDelDia, diasParaDespacho, diasParaDespachoEstatico);
        iniciarFabrica();
        
        
    }
    
    
    public void iniciarFabrica(){
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
    
    public void verificador(){
        while(true){
            
            if (variablesVarias.getContadorCarrosProducidos() != contadorCarrosProducidos) {
                variablesVarias.setContadorCarrosProducidos(contadorCarrosProducidos);
                vista.CarQuantityjTextField.setText(String.valueOf(contadorCarrosProducidos));
            }
            
            if (variablesVarias.getContadorMotoresProducidos() != contadorMotoresProducidos) {
                variablesVarias.setContadorMotoresProducidos(contadorMotoresProducidos);
                vista.EngineQuantityjTextField.setText(String.valueOf(contadorMotoresProducidos));
            }
            
            if (variablesVarias.getContadorParabrisasProducidos() != contadorParabrisasProducidos) {
                variablesVarias.setContadorParabrisasProducidos(contadorParabrisasProducidos);
                vista.WindshieldQuantityjTextField.setText(String.valueOf(contadorParabrisasProducidos));
            }
            
            if (variablesVarias.getContadorRuedasProducidas() != contadorRuedasProducidas) {
                variablesVarias.setContadorRuedasProducidas(contadorRuedasProducidas);
                vista.WheelQuantityjTextField.setText(String.valueOf(contadorRuedasProducidas));
            }
            
            if (variablesVarias.getProductoresRuedasActuales() != productoresRuedasActuales) {
                variablesVarias.setProductoresRuedasActuales(productoresRuedasActuales);
                vista.WheelMakerjTextField.setText(String.valueOf(productoresRuedasActuales));
            }
            
            if (variablesVarias.getProductoresParabirsasActuales() != productoresParabirsasActuales) {
                variablesVarias.setProductoresParabirsasActuales(productoresParabirsasActuales);
                vista.WindshieldMakerjTextField.setText(String.valueOf(productoresParabirsasActuales));
            }
            
            if (variablesVarias.getProductoresMotoresActuales() != productoresMotoresActuales) {
                variablesVarias.setProductoresMotoresActuales(productoresMotoresActuales);
                vista.EngineMakerjTextField.setText(String.valueOf(productoresMotoresActuales));
            }
            
            if (variablesVarias.getEnsambladoresActuales() != ensambladoresActuales) {
                variablesVarias.setEnsambladoresActuales(ensambladoresActuales);
                vista.CarMakerjTextField.setText(String.valueOf(ensambladoresActuales));
            }
            
            if (variablesVarias.getDiasParaDespacho() != diasParaDespacho) {
                variablesVarias.setDiasParaDespacho(diasParaDespacho);
                vista.RemainingDaysjTextField.setText(String.valueOf(diasParaDespacho));
                System.out.println("Entré aquí");
            }
        }
    }
    
    public void LeerInformacion(){
        String aux;
        String[] arrayAux;
        try{
            BufferedReader bf = new BufferedReader(new FileReader(archivoBase));           
            aux = bf.readLine();
            arrayAux = aux.split(":");
            duracionDelDia = Integer.parseInt(arrayAux[1])*1000;
            aux = bf.readLine();
            arrayAux = aux.split(":");
            diasParaDespachoEstatico = Integer.parseInt(arrayAux[1]);
            diasParaDespacho = diasParaDespachoEstatico;
            aux = bf.readLine();
            arrayAux = aux.split(":");
            disponibilidadMaximaRuedas = Integer.parseInt(arrayAux[1]);
            aux = bf.readLine();
            arrayAux = aux.split(":");
            disponibilidadMaximaParabrisas = Integer.parseInt(arrayAux[1]);
            aux = bf.readLine();
            arrayAux = aux.split(":");
            disponibilidadMaximaMotores = Integer.parseInt(arrayAux[1]);
            aux = bf.readLine();
            arrayAux = aux.split(":");
            prodRuedas = new Productor[Integer.parseInt(arrayAux[1])];
            System.out.println(arrayAux[1]);
            aux = bf.readLine();
            arrayAux = aux.split(":");
            prodParabrisas = new Productor[Integer.parseInt(arrayAux[1])];
            aux = bf.readLine();
            arrayAux = aux.split(":");
            prodMotores = new Productor[Integer.parseInt(arrayAux[1])];
            aux = bf.readLine();
            arrayAux = aux.split(":");
            ensambladoresA = new Ensambladores[Integer.parseInt(arrayAux[1])];
            aux = bf.readLine();
            arrayAux = aux.split(":");
            productoresRuedasIniciales = Integer.parseInt(arrayAux[1]);
            aux = bf.readLine();
            arrayAux = aux.split(":");
            productoresParabrisasIniciales = Integer.parseInt(arrayAux[1]);
            aux = bf.readLine();
            arrayAux = aux.split(":");
            productoresMotoresIniciales = Integer.parseInt(arrayAux[1]);
            aux = bf.readLine();
            arrayAux = aux.split(":");
            ensambladoresIniciales = Integer.parseInt(arrayAux[1]);
            
        }catch(Exception e){
            System.out.println("No se logró leer la base de datos de películas");
        }
    }
}
