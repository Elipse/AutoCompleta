/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.bitbyeteclub.c03.adaptadores;

import java.util.List;
import mx.com.bitbyeteclub.c01.Sugerencia;

/**
 *
 * @author ELIALVA
 */
public class AutoCompletaModeloVista {

    private List<Sugerencia> sugerencias;

    public void setSugerencias(List<Sugerencia> sugerencias) {
        this.sugerencias = sugerencias;
    }

    public List<Sugerencia> getSugerencias() {
        return this.sugerencias;
    }
}
