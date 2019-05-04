/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.bitbyeteclub.c04.vista;

import mx.com.bitbyeteclub.c03.adaptadores.AutoCompletaModeloVista;
import mx.com.bitbyeteclub.c03.adaptadores.AutoCompletaVista;

/**
 *
 * @author ELIALVA
 */
public interface Vista extends AutoCompletaVista {

    @Override
    public void sugiere(AutoCompletaModeloVista modeloVista);
    
}
