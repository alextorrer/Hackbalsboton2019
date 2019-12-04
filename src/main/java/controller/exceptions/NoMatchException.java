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
public class NoMatchException extends Exception {

    /**
     * Creates a new instance of <code>NoMatchException</code> without detail
     * message.
     */
    public NoMatchException() {
    }

    /**
     * Constructs an instance of <code>NoMatchException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NoMatchException(String msg) {
        super(msg);
    }
}
