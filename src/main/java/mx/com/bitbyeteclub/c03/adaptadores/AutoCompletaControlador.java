/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.bitbyeteclub.c03.adaptadores;

import java.util.function.Consumer;
import mx.com.bitbyeteclub.c02.casosdeuso.AutoCompletaCasoDeUso;
import mx.com.bitbyeteclub.c02.casosdeuso.AutoCompletaModeloRespuesta;
import mx.com.bitbyeteclub.c02.casosdeuso.BuscadorDeSugerenciasI;

/**
 *
 * @author ELIALVA
 */
public class AutoCompletaControlador implements Consumer<AutoCompletaModeloRespuesta> {
    
    private final AutoCompletaCasoDeUso casoDeUso;
    private final AutoCompletaPresentador presentador;
    private final AutoCompletaVista vista;
    
    public AutoCompletaControlador(AutoCompletaCasoDeUso casoDeUso,
            AutoCompletaPresentador presentador,
            AutoCompletaVista vista) {
        this.casoDeUso = casoDeUso;
        this.presentador = presentador;
        this.vista = vista;
    }
    
    public void autocompleta(String ambito, String texto, BuscadorDeSugerenciasI buscador) {
        casoDeUso.buscaSugerencias(ambito, texto, buscador, AutoCompletaControlador.this);
    }
    
    private void autocompleta(AutoCompletaModeloRespuesta modeloRespuesta) {
        AutoCompletaModeloVista modeloVista = presentador.presenta(modeloRespuesta);
        vista.sugiere(modeloVista);
    }
    
    @Override
    public void accept(AutoCompletaModeloRespuesta modeloRespuesta) {
        autocompleta(modeloRespuesta);
    }
}
