package com.leverton.discussion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author anaida.gasparyan
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Order")
public class NoFoundException extends RuntimeException {
}
