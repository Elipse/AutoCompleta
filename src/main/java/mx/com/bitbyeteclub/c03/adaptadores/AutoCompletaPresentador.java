/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.bitbyeteclub.c03.adaptadores;

import java.util.List;
import mx.com.bitbyeteclub.c01.Sugerencia;
import mx.com.bitbyeteclub.c02.casosdeuso.AutoCompletaModeloRespuesta;

/**
 *
 * @author ELIALVA
 */
public class AutoCompletaPresentador {

    private AutoCompletaModeloVista autoCompletaModeloVista;

    public AutoCompletaModeloVista getModeloVista() {
        return autoCompletaModeloVista;
    }

    public AutoCompletaModeloVista presenta(AutoCompletaModeloRespuesta modeloRespuesta) {
        this.autoCompletaModeloVista = new AutoCompletaModeloVista();
        List<Sugerencia> listaTextoEImagen = modeloRespuesta.getListaTextoEImagen();
        autoCompletaModeloVista.setSugerencias(listaTextoEImagen);
        return autoCompletaModeloVista;
    }
}
