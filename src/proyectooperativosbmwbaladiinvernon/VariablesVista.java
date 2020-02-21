/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectooperativosbmwbaladiinvernon;

/**
 *
 * @author Fernando Baladi
 */
public class VariablesVista {
    public int ensambladoresActuales;
    public int productoresRuedasActuales;
    public int productoresMotoresActuales;
    public int productoresParabirsasActuales;
    public int contadorRuedasProducidas;
    public int contadorMotoresProducidos;
    public int contadorParabrisasProducidos;
    public int contadorCarrosProducidos;
    public int diasParaDespacho;
    public String estadoDelJefe;
    public String estadoDelGerente;

    public VariablesVista(int ensambladoresActuales, int productoresRuedasActuales, 
            int productoresMotoresActuales, int productoresParabirsasActuales, 
            int contadorRuedasProducidas, int contadorMotoresProducidos, 
            int contadorParabrisasProducidos, int contadorCarrosProducidos, 
            int diasParaDespacho, String estadoJefe, String estadoGerente) {
        this.ensambladoresActuales = ensambladoresActuales;
        this.productoresRuedasActuales = productoresRuedasActuales;
        this.productoresMotoresActuales = productoresMotoresActuales;
        this.productoresParabirsasActuales = productoresParabirsasActuales;
        this.contadorRuedasProducidas = contadorRuedasProducidas;
        this.contadorMotoresProducidos = contadorMotoresProducidos;
        this.contadorParabrisasProducidos = contadorParabrisasProducidos;
        this.contadorCarrosProducidos = contadorCarrosProducidos;
        this.diasParaDespacho = diasParaDespacho;
        this.estadoDelGerente = estadoGerente;
        this.estadoDelJefe = estadoJefe;
    }
    
    public int getEnsambladoresActuales() {
        return ensambladoresActuales;
    }

    public void setEnsambladoresActuales(int ensambladoresActuales) {
        this.ensambladoresActuales = ensambladoresActuales;
    }

    public int getProductoresRuedasActuales() {
        return productoresRuedasActuales;
    }

    public void setProductoresRuedasActuales(int productoresRuedasActuales) {
        this.productoresRuedasActuales = productoresRuedasActuales;
    }

    public int getProductoresMotoresActuales() {
        return productoresMotoresActuales;
    }

    public void setProductoresMotoresActuales(int productoresMotoresActuales) {
        this.productoresMotoresActuales = productoresMotoresActuales;
    }

    public int getProductoresParabirsasActuales() {
        return productoresParabirsasActuales;
    }

    public void setProductoresParabirsasActuales(int productoresParabirsasActuales) {
        this.productoresParabirsasActuales = productoresParabirsasActuales;
    }

    public int getContadorRuedasProducidas() {
        return contadorRuedasProducidas;
    }

    public void setContadorRuedasProducidas(int contadorRuedasProducidas) {
        this.contadorRuedasProducidas = contadorRuedasProducidas;
    }

    public int getContadorMotoresProducidos() {
        return contadorMotoresProducidos;
    }

    public void setContadorMotoresProducidos(int contadorMotoresProducidos) {
        this.contadorMotoresProducidos = contadorMotoresProducidos;
    }

    public int getContadorParabrisasProducidos() {
        return contadorParabrisasProducidos;
    }

    public void setContadorParabrisasProducidos(int contadorParabrisasProducidos) {
        this.contadorParabrisasProducidos = contadorParabrisasProducidos;
    }

    public int getContadorCarrosProducidos() {
        return contadorCarrosProducidos;
    }

    public void setContadorCarrosProducidos(int contadorCarrosProducidos) {
        this.contadorCarrosProducidos = contadorCarrosProducidos;
    }

    public int getDiasParaDespacho() {
        return diasParaDespacho;
    }

    public void setDiasParaDespacho(int diasParaDespacho) {
        this.diasParaDespacho = diasParaDespacho;
    }

    public String getEstadoDelJefe() {
        return estadoDelJefe;
    }

    public void setEstadoDelJefe(String estadoDelJefe) {
        this.estadoDelJefe = estadoDelJefe;
    }

    public String getEstadoDelGerente() {
        return estadoDelGerente;
    }

    public void setEstadoDelGerente(String estadoDelGerente) {
        this.estadoDelGerente = estadoDelGerente;
    }

    
    
    
    

}
