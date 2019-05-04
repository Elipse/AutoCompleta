/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.bitbyeteclub.c02.casosdeuso;


import java.awt.EventQueue;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import mx.com.bitbyeteclub.c01.Sugerencia;

/**
 *
 * @author ELIALVA
 */
public class AutoCompletaCasoDeUso {

    private final ExecutorService servicio;
    private volatile String texto;    

    public AutoCompletaCasoDeUso(ExecutorService servicio) {            
        this.servicio = servicio;        
    }
    
    /**
     * Este método invoca la búsqueda en segundo plano y retorna la respuesta
     * en Swing-Thread.
     * @param ambito
     * @param texto
     * @param consumidor
     * @param buscadori 
     */
    public void buscaSugerencias(String ambito, String texto, BuscadorDeSugerenciasI buscadori, Consumer<AutoCompletaModeloRespuesta> consumidor) {
        this.texto = texto;        
        servicio.execute(() -> {
            AutoCompletaModeloRespuesta modeloRespuesta  = new AutoCompletaModeloRespuesta();
            List<Sugerencia> listaTextoEImagen = buscadori.recuperaTextoEImagen(ambito, texto);
            modeloRespuesta.setListaTextoEImagen(listaTextoEImagen);
            if (this.texto != null && this.texto.equals(texto)) {
                EventQueue.invokeLater(() -> {
                    this.texto = null;
                    consumidor.accept(modeloRespuesta);
                });
            }
        });
    }
}
