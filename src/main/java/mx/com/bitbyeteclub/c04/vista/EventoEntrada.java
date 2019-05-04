/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.bitbyeteclub.c04.vista;

import java.awt.Point;
import javax.swing.text.JTextComponent;

/**
 *
 * @author ELIALVA
 */
class EventoEntrada {

    private JTextComponent textoEntrada;
    private String comando;
    private Point ubicacion;

    public JTextComponent getTextoEntrada() {
        return textoEntrada;
    }

    public void setTextoEntrada(JTextComponent textoEntrada) {
        this.textoEntrada = textoEntrada;
    }

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public Point getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Point ubicacion) {
        this.ubicacion = ubicacion;
    }

   
}
