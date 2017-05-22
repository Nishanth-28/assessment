/**
 * ApplicationException class whenever exception came it returns welcome page
 *
 * @author CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.commons.exceptions;


/**
 * @ExceptionHandler annotation handles exceptions in spring MVC.
 * @author cjp
 */
public class ApplicationException extends Exception {
    
    public ApplicationException(String message) {
        super(message);
    }

    /**
     *
     * @param exception
     * @return
     */
//    @ExceptionHandler(Exception.class)
//    public String handleApplicationException(HttpSessionRequiredException exception) {
//        //reirect to welcome.
//        return ReturnConst.WELCOME;
//    }

}
