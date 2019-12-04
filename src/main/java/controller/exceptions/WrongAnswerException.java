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
public class WrongAnswerException extends Exception {

    /**
     * Creates a new instance of <code>WrongAnswerException</code> without
     * detail message.
     */
    public WrongAnswerException() {
    }

    /**
     * Constructs an instance of <code>WrongAnswerException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public WrongAnswerException(String msg) {
        super(msg);
    }
}
