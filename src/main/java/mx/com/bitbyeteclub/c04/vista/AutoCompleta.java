/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.bitbyeteclub.c04.vista;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.JTextComponent;
import mx.com.bitbyeteclub.c01.Sugerencia;
import mx.com.bitbyeteclub.c02.casosdeuso.AutoCompletaCasoDeUso;
import mx.com.bitbyeteclub.c02.casosdeuso.BuscadorDeSugerenciasI;
import mx.com.bitbyeteclub.c03.adaptadores.AutoCompletaControlador;
import mx.com.bitbyeteclub.c03.adaptadores.AutoCompletaModeloVista;
import mx.com.bitbyeteclub.c03.adaptadores.AutoCompletaPresentador;

/**
 *
 * @author ELIALVA
 */
public class AutoCompleta implements ChangeListener, Vista {

    private static final AutoCompleta AUTO_COMPLETA = new AutoCompleta();

    private static final DialogoSugerencias DIALOGO = DialogoSugerencias.getInstance();
    private static final TextoEntrada TEXTO_ENTRADA = TextoEntrada.getInstance();
    private static final Map<JTextComponent, BuscadorDeSugerenciasI> MAPA_BUSCADORES = new HashMap<>();

    private final AutoCompletaControlador controlador;

    static {
        TEXTO_ENTRADA.addChangeListener(AUTO_COMPLETA);
    }

    private AutoCompleta() {
        ExecutorService servicio = Executors.newFixedThreadPool(4);
        AutoCompletaCasoDeUso casoDeUso = new AutoCompletaCasoDeUso(servicio);
        AutoCompletaPresentador presentador = new AutoCompletaPresentador();

        controlador = new AutoCompletaControlador(casoDeUso, presentador, AutoCompleta.this);
    }

    public static void instala(BuscadorDeSugerenciasI buscador, JTextComponent... camposDeTexto) {
        for (JTextComponent campoDeTexto : camposDeTexto) {
            MAPA_BUSCADORES.put(campoDeTexto, buscador);
            TEXTO_ENTRADA.addListeners(campoDeTexto);
        }
    }

    @Override
    public void sugiere(AutoCompletaModeloVista modeloVista) {
        if (modeloVista.getSugerencias().isEmpty()) {
            DIALOGO.oculta();
        } else {
            DIALOGO.sugiere(modeloVista);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        EventoEntrada evento = (EventoEntrada) e.getSource();
        JTextComponent textoEntrada = evento.getTextoEntrada();
        String comando = evento.getComando();
        String ambito = textoEntrada.getName();
        String texto = textoEntrada.getText();
        Point ubicacion = evento.getUbicacion();

        switch (comando) {
            case "BUSCA":
                controlador.autocompleta(ambito, texto, MAPA_BUSCADORES.get(textoEntrada));
                break;
            case "OCULTA":
                DIALOGO.oculta();
                break;
            case "SELECCIONA":
                if (DIALOGO.esVisible()) {
                    Sugerencia sugerencia = DIALOGO.selecciona();
                    TEXTO_ENTRADA.setSugerencia(sugerencia);
                }
                break;
            case "POSICIONA":
                DIALOGO.posiciona((int) ubicacion.getX(), (int) ubicacion.getY());
                break;
            case "SUBE":
                DIALOGO.sube();
                break;
            case "BAJA":
                DIALOGO.baja();
                break;
        }
    }
}
