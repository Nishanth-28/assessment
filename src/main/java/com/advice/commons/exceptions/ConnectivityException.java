/**
 * ConnectivityException class whenever exception came it returns welcome page
 *
 * @author CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.commons.exceptions;

import com.advice.commons.constants.ReturnConst;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @ExceptionHandler annotation handles exceptions in spring MVC.
 * @author cjp
 */
public class ConnectivityException extends Exception {

    /**
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public String handleConnectivityException(HttpSessionRequiredException exception) {
        //reirect to welcome.
        return ReturnConst.WELCOME;
    }

}
