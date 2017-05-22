/**
 * HttpSessionRequiredException class whenever exception came it redirect to welcome page.
 *
 * @author CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.commons.exceptions;

import com.advice.commons.constants.ReturnConst;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @ExceptionHandler annotation handles exceptions in spring MVC
 * @author cjp
 */
public class HttpSessionRequiredException extends Exception {

    /**
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(HttpSessionRequiredException.class)
    public String handleHttpSessionRequiredException(HttpSessionRequiredException exception) {

        //redirect to welcome 
        return ReturnConst.WELCOME;
    }

}
