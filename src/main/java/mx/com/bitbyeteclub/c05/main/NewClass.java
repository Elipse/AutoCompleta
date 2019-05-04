/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.bitbyeteclub.c05.main;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 *
 * @author ELIALVA
 */
public class NewClass {
    
    
    public static void main(String[] args) throws MalformedURLException {
        File file = new File("D:\\mots ada\\a.txt");
        
        URI toURI = file.toURI();
        
        System.out.println("toURI " + toURI);
        System.out.println("toURL " + toURI.toURL());
        
    }
    
}
