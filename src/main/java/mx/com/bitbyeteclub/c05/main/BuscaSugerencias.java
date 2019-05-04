/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.bitbyeteclub.c05.main;

import java.awt.EventQueue;
import java.util.Arrays;
import java.util.List;
import mx.com.bitbyeteclub.c01.Sugerencia;
import mx.com.bitbyeteclub.c02.casosdeuso.BuscadorDeSugerenciasI;
import org.springframework.stereotype.Component;

/**
 *
 * @author ELIALVA
 */
@Component
public class BuscaSugerencias implements BuscadorDeSugerenciasI {

    /**
     * Este método valida que su llamado sea por un hilo secundario al de
     * eventos.
     *
     * @param texto
     * @return
     */
    @Override
    public List<Sugerencia> recuperaTextoEImagen(String ambito, String texto) {
        System.out.println("epale " + ambito + " " + texto);
        assert (!EventQueue.isDispatchThread());
        Sugerencia s1 = new Sugerencia();
        s1.setImagen(null);
        s1.setTexto(texto + "##");
        Sugerencia s2 = new Sugerencia();
        s2.setImagen(null);
        s2.setTexto(texto + "##");
          Sugerencia s3 = new Sugerencia();
        s3.setImagen(null);
        s3.setTexto(texto + "##");
      Sugerencia s4 = new Sugerencia();
        s4.setImagen(null);
        s4.setTexto(texto + "##");
         Sugerencia s5 = new Sugerencia();
        s5.setImagen(null);
        s5.setTexto(texto + "##5");
        return Arrays.asList(s1, s2 ,s3, s4, s5);
    }
    
}
