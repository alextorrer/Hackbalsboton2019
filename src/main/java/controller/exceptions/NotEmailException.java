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
public class NotEmailException extends Exception {

    /**
     * Creates a new instance of <code>NotEmailException</code> without detail
     * message.
     */
    public NotEmailException() {
    }

    /**
     * Constructs an instance of <code>NotEmailException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NotEmailException(String msg) {
        super(msg);
    }
}
