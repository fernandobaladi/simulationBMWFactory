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
import javax.swing.JOptionPane;

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
    public static volatile int contadorRuedasProducidasVistas;
    public static volatile int contadorMotoresProducidosVista;
    public static volatile int contadorParabrisasProducidosVista;
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
    public static int productoresParabrisasActuales;
    public static volatile String estadoGerente = "Nuevo";
    public static volatile String estadoJefe = "Nuevo";
    File archivoBase = new File("archivoBase.txt"); 
    public static Productor[] prodRuedas;
    public static Productor[] prodParabrisas;
    public static Productor[] prodMotores;
    public static Ensambladores[] ensambladoresA;
    public static Vista vista;
    public Gerente ger;
    public Jefe jef;
    public static volatile boolean iniciar = false;
    //Semáforos de exclusión mutua
    public static Semaphore semRG;
    public static Semaphore semMG;
    public static Semaphore semPG;

            //semáforos de control
    public static Semaphore semR;
    public static Semaphore semER;
    public static Semaphore semM;
    public static Semaphore semP;
    public static Semaphore semEM;
    public static Semaphore semEP;

            //Semáforos gerenciales
    public static Semaphore semGE;
    public static Semaphore semJG;
    
    public Fabrica() {
        
        
        this.LeerInformacion();     
        if(iniciar){
            variablesVarias = new VariablesVista(ensambladoresActuales, 
            productoresRuedasActuales, productoresMotoresActuales, 
            productoresParabrisasActuales, contadorRuedasProducidasVistas, 
            contadorMotoresProducidosVista, contadorParabrisasProducidosVista, 
            contadorCarrosProducidos, diasParaDespacho, estadoJefe, estadoGerente);
            contadorCarrosProducidos = 0;
            contadorRuedasProducidasVistas = 0;
            contadorRuedasConsumidas = 0;
            contadorParabrisasConsumidos = 0;
            contadorParabrisasProducidosVista = 0;
            contadorMotoresConsumidos = 0;
            contadorMotoresProducidosVista = 0;


            productoresMotoresActuales = productoresMotoresIniciales;
            productoresParabrisasActuales = productoresParabrisasIniciales;
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
            
            //Semáforos de exclusión mutua
            semRG = new Semaphore(1);
            semMG = new Semaphore(1);
            semPG = new Semaphore(1);

            //semáforos de control
            semR = new Semaphore(disponibilidadMaximaRuedas);
            semM = new Semaphore(disponibilidadMaximaMotores);
            semP = new Semaphore(disponibilidadMaximaParabrisas);
            semER = new Semaphore(0);
            semEM = new Semaphore(0);
            semEP = new Semaphore(0);

            //Semáforos gerenciales
            semGE = new Semaphore(1);
            semJG = new Semaphore(1);

            vista = new Vista();
            vista.setVisible(true);
            vista.setResizable(false);
            vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            vista.setLocationRelativeTo(null);
            vista.WheelMakerjTextField.setText(String.valueOf(productoresRuedasActuales));
            vista.WindshieldMakerjTextField.setText(String.valueOf(productoresParabrisasActuales));
            vista.EngineMakerjTextField.setText(String.valueOf(productoresMotoresActuales));
            vista.CarMakerjTextField.setText(String.valueOf(ensambladoresActuales));
            vista.WheelQuantityjTextField.setText(String.valueOf(contadorRuedasProducidasVistas));
            vista.WindshieldQuantityjTextField.setText(String.valueOf(contadorParabrisasProducidosVista));
            vista.EngineQuantityjTextField.setText(String.valueOf(contadorMotoresProducidosVista));
            vista.CarQuantityjTextField.setText(String.valueOf(contadorCarrosProducidos));
            vista.ManagerjTextField.setText(estadoGerente);
            vista.BossjTextField.setText(estadoJefe);

            for (int i = 0; i < productoresRuedasIniciales; i++) {
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
                    semP, semEP, semRG, semPG, semMG, semGE);    
            }

            ger = new Gerente(semJG, semGE);
            jef = new Jefe(semJG);
            iniciarFabrica();
        }
        
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
    public static void contratar(int num){
        switch (num) {
            case 1:
                if (productoresRuedasActuales != prodRuedas.length) {
                    prodRuedas[productoresRuedasActuales] = new Productor(semR, "r"," una rueda", 
                            semER, diasDeProduccionRueda, duracionDelDia, semRG, almacenRuedas,1);
                    prodRuedas[productoresRuedasActuales].start();
                    productoresRuedasActuales++;
                }
                break;
            case 2:
                if (productoresParabrisasActuales != prodParabrisas.length) {
                    prodParabrisas[productoresParabrisasActuales] = new Productor(semP, "p",
                            " un parabrisas", semEP, diasDeProduccionParabrisas, 
                            duracionDelDia, semPG, almacenParabrisas,3);
                    prodParabrisas[productoresParabrisasActuales].start();
                    productoresParabrisasActuales++;
                }
                break;
            case 3:
                if (productoresMotoresActuales != prodMotores.length) {
                    prodMotores[productoresMotoresActuales] = new Productor(semM, "m", 
                            " un motor", semEM,  diasDeProduccionMotor, duracionDelDia, 
                            semMG,  almacenMotores,2);
                    prodMotores[productoresMotoresActuales].start();
                    productoresMotoresActuales++;
                }
                break;
            case 4:
                if (ensambladoresActuales != ensambladoresA.length) {
                    ensambladoresA[ensambladoresActuales] = new Ensambladores(semR, 
                            "epa", semER, semM, semEM, semP, semEP, semRG, semPG, 
                            semMG, semGE);
                    ensambladoresA[ensambladoresActuales].start();
                    ensambladoresActuales++;
                }
                break;    
            default:
                throw new AssertionError();
        }
    }
    
    public static void despedir(int num){
        switch (num) {
            case 1:
                if(productoresRuedasActuales > 0){
                    productoresRuedasActuales--;
                    prodRuedas[productoresRuedasActuales].setContratado(false);
                }
                break;
            case 2:
                if(productoresParabrisasActuales > 0){
                    productoresParabrisasActuales--;
                    prodParabrisas[productoresParabrisasActuales].setContratado(false);
                }
                break;
            case 3:
                if(productoresMotoresActuales > 0){
                    productoresMotoresActuales--;
                    prodMotores[productoresMotoresActuales].setContratado(false);
                }
                break;
            case 4:
                if(ensambladoresActuales > 0){
                    ensambladoresActuales--;
                    ensambladoresA[ensambladoresActuales].setContratado(false);
                }
                break;    
            default:
                throw new AssertionError();
        }
    }
    
    public void verificador(){
        while(iniciar){
            
            if (variablesVarias.getContadorCarrosProducidos() != contadorCarrosProducidos) {
                variablesVarias.setContadorCarrosProducidos(contadorCarrosProducidos);
                vista.CarQuantityjTextField.setText(String.valueOf(contadorCarrosProducidos));
            }
            
            if (variablesVarias.getContadorMotoresProducidos() != contadorMotoresProducidosVista) {
                variablesVarias.setContadorMotoresProducidos(contadorMotoresProducidosVista);
                vista.EngineQuantityjTextField.setText(String.valueOf(contadorMotoresProducidosVista));
            }
            
            if (variablesVarias.getContadorParabrisasProducidos() != contadorParabrisasProducidosVista) {
                variablesVarias.setContadorParabrisasProducidos(contadorParabrisasProducidosVista);
                vista.WindshieldQuantityjTextField.setText(String.valueOf(contadorParabrisasProducidosVista));
            }
            
            if (variablesVarias.getContadorRuedasProducidas() != contadorRuedasProducidasVistas) {
                variablesVarias.setContadorRuedasProducidas(contadorRuedasProducidasVistas);
                vista.WheelQuantityjTextField.setText(String.valueOf(contadorRuedasProducidasVistas));
            }
            
            if (variablesVarias.getProductoresRuedasActuales() != productoresRuedasActuales) {
                variablesVarias.setProductoresRuedasActuales(productoresRuedasActuales);
                vista.WheelMakerjTextField.setText(String.valueOf(productoresRuedasActuales));
            }
            
            if (variablesVarias.getProductoresParabirsasActuales() != productoresParabrisasActuales) {
                variablesVarias.setProductoresParabirsasActuales(productoresParabrisasActuales);
                vista.WindshieldMakerjTextField.setText(String.valueOf(productoresParabrisasActuales));
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
            }
            
            if (!variablesVarias.getEstadoDelJefe().equals(this.estadoJefe)) {
                variablesVarias.setEstadoDelJefe(this.estadoJefe);
                vista.BossjTextField.setText(this.estadoJefe);
            }

            if (!variablesVarias.getEstadoDelGerente().equals(this.estadoGerente)) {
                variablesVarias.setEstadoDelGerente(this.estadoGerente);
                vista.ManagerjTextField.setText(this.estadoGerente);
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
            productoresRuedasActuales = Integer.parseInt(arrayAux[1]);
            aux = bf.readLine();
            arrayAux = aux.split(":");
            productoresParabrisasIniciales = Integer.parseInt(arrayAux[1]);
            productoresParabrisasActuales = Integer.parseInt(arrayAux[1]);
            aux = bf.readLine();
            arrayAux = aux.split(":");
            productoresMotoresIniciales = Integer.parseInt(arrayAux[1]);
            productoresMotoresActuales = Integer.parseInt(arrayAux[1]);
            aux = bf.readLine();
            arrayAux = aux.split(":");
            ensambladoresIniciales = Integer.parseInt(arrayAux[1]);
            ensambladoresActuales = Integer.parseInt(arrayAux[1]);

            if ((productoresParabrisasIniciales > prodParabrisas.length) || productoresParabrisasIniciales < 0) {
                JOptionPane.showMessageDialog(null, "Inconsistencia de datos en los productores de parabrisas");
                System.exit(0);
            }else if((productoresMotoresIniciales > prodMotores.length) || productoresMotoresIniciales < 0){
                JOptionPane.showMessageDialog(null, "Inconsistencia de datos en los productores de motores");
                System.exit(0);
            }else if((productoresRuedasIniciales > prodRuedas.length) || productoresRuedasIniciales < 0){
                JOptionPane.showMessageDialog(null, "Inconsistencia de datos en los productores de ruedas");
                System.exit(0);
            }else if((ensambladoresIniciales > ensambladoresA.length) || ensambladoresIniciales < 0){
                JOptionPane.showMessageDialog(null, "Inconsistencia de datos en los ensambladores");
                System.exit(0);
            }else if(duracionDelDia <=0 || diasParaDespachoEstatico <= 0){
                JOptionPane.showMessageDialog(null, "Inconsistencia de datos en los días");
                System.exit(0);
            }else if((disponibilidadMaximaMotores <= 0) || (disponibilidadMaximaParabrisas <= 0) 
                    || (disponibilidadMaximaRuedas<=0)) {
                JOptionPane.showMessageDialog(null, "Inconsistencia de datos en la disponibilidad máxima.\nLa disponibilidad máxima tiene que ser mayor a 0");
                System.exit(0);
            }else{
                iniciar = true;
            }
            bf.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se logró leer la base de datos.\nRecuerda usar enteros y no decimales");
            System.exit(0);
        }
    }
}
