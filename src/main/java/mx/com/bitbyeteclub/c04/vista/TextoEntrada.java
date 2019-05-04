/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.bitbyeteclub.c04.vista;

import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.EventListenerList;
import javax.swing.text.JTextComponent;
import mx.com.bitbyeteclub.c01.Sugerencia;

/**
 *
 * @author ELIALVA
 */
public class TextoEntrada implements DocumentListener, KeyListener, AncestorListener, FocusListener {

    private static final TextoEntrada TEXTO_ENTRADA = new TextoEntrada();
    private static final String OCULTA = "OCULTA";
    private static final String SUBE = "SUBE";
    private static final String BAJA = "BAJA";
    private static final String SELECCIONA = "SELECCIONA";
    private static final String POSICIONA = "POSICIONA";
    private static final String BUSCA = "BUSCA";
    private JTextComponent entradaActiva;
    private final EventoEntrada eventoEntrada;

    private TextoEntrada() {
        eventoEntrada = new EventoEntrada();
    }

    public static TextoEntrada getInstance() {
        return TEXTO_ENTRADA;
    }

    public void addListeners(JTextComponent jTextComponent) {
        jTextComponent.addAncestorListener(TEXTO_ENTRADA);
        jTextComponent.addFocusListener(TEXTO_ENTRADA);
        jTextComponent.addKeyListener(TEXTO_ENTRADA);
        jTextComponent.getDocument().addDocumentListener(TEXTO_ENTRADA);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        changedUpdate(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        changedUpdate(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        eventoEntrada.setComando(BUSCA);
        fireChangeEvent(new ChangeEvent(eventoEntrada));
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        eventoEntrada.setComando("");

        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                eventoEntrada.setComando(OCULTA);
                break;
            case KeyEvent.VK_UP:
                eventoEntrada.setComando(SUBE);
                break;
            case KeyEvent.VK_DOWN:
                eventoEntrada.setComando(BAJA);
                break;
            case KeyEvent.VK_ENTER:
                eventoEntrada.setComando(SELECCIONA);
                break;
            default:
                if ((e.getKeyCode() == KeyEvent.VK_SPACE) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                    eventoEntrada.setComando(BUSCA);
                }
        }

        if (!eventoEntrada.getComando().isEmpty()) {
            fireChangeEvent(new ChangeEvent(eventoEntrada));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void ancestorAdded(AncestorEvent event) {
    }

    @Override
    public void ancestorRemoved(AncestorEvent event) {
    }

    @Override
    public void ancestorMoved(AncestorEvent event) {
        JComponent component = event.getComponent();
        if (entradaActiva != null && component == entradaActiva) {
            calculaPosicion();
            eventoEntrada.setComando(POSICIONA);
            eventoEntrada.setUbicacion(calculaPosicion());
            fireChangeEvent(new ChangeEvent(eventoEntrada));
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        entradaActiva = (JTextComponent) e.getComponent();
        eventoEntrada.setComando(POSICIONA);
        eventoEntrada.setTextoEntrada(entradaActiva);        
        eventoEntrada.setUbicacion(calculaPosicion());
        fireChangeEvent(new ChangeEvent(eventoEntrada));
    }

    @Override
    public void focusLost(FocusEvent e) {
        entradaActiva = null;
        eventoEntrada.setComando(OCULTA);
        fireChangeEvent(new ChangeEvent(eventoEntrada));
    }

    void setSugerencia(Sugerencia sugerencia) {
        entradaActiva.getDocument().removeDocumentListener(TEXTO_ENTRADA);
        entradaActiva.setText(sugerencia.getTexto());
        entradaActiva.getDocument().addDocumentListener(TEXTO_ENTRADA);
    }

    Point calculaPosicion() {
        Point locationOnScreen = entradaActiva.getLocationOnScreen();
        return new Point(locationOnScreen.x, locationOnScreen.y + entradaActiva.getHeight());
    }

    private final EventListenerList listenerList = new EventListenerList();

    public void addChangeListener(ChangeListener l) {
        listenerList.add(ChangeListener.class, l);
    }

    public void removeChangeListener(ChangeListener l) {
        listenerList.remove(ChangeListener.class, l);
    }

    // Notify all listeners that have registered interest for
    // notification on this event type.  The event instance
    // is lazily created using the parameters passed into
    // the fire method.
    protected void fireChangeEvent(ChangeEvent changeEvent) {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ChangeListener.class) {
                // Lazily create the event:
                if (changeEvent == null) {
                    changeEvent = new ChangeEvent(this);
                }
                ((ChangeListener) listeners[i + 1]).stateChanged(changeEvent);
            }
        }
    }
}
