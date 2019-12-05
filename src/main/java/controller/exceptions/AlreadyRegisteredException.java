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
public class AlreadyRegisteredException extends Exception {

    /**
     * Creates a new instance of <code>AlreadyRegisteredException</code> without
     * detail message.
     */
    public AlreadyRegisteredException() {
    }

    /**
     * Constructs an instance of <code>AlreadyRegisteredException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public AlreadyRegisteredException(String msg) {
        super(msg);
    }
}
