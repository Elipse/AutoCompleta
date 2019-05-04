/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.bitbyeteclub.c02.casosdeuso;

import java.util.List;
import mx.com.bitbyeteclub.c01.Sugerencia;

/**
 *
 * @author ELIALVA
 */
public interface BuscadorDeSugerenciasI {

    public List<Sugerencia> recuperaTextoEImagen(String ambito, String texto);
}
