/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.exceptions;

/**
 *
 * @author alext
 */
public class OlderPasswordException extends Exception {

    /**
     * Creates a new instance of <code>OlderPasswordException</code> without
     * detail message.
     */
    public OlderPasswordException() {
    }

    /**
     * Constructs an instance of <code>OlderPasswordException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public OlderPasswordException(String msg) {
        super(msg);
    }
}
