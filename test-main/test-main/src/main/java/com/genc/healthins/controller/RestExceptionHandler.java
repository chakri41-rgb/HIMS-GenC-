package com.genc.healthins.controller;

import com.genc.healthins.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler {

    private boolean wantsJson(HttpServletRequest req) {
        String accept = req.getHeader("Accept");
        String uri = req.getRequestURI();
        return (accept != null && accept.contains("application/json")) || (uri != null && uri.startsWith("/api/"));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public Object handleNotFound(ResourceNotFoundException ex, HttpServletRequest req){
        if(wantsJson(req)){
            Map<String,String> m = new HashMap<>();
            m.put("error", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(m);
        } else {
            ModelAndView mv = new ModelAndView("forward:/not-found.html");
            mv.addObject("message", ex.getMessage());
            return mv;
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req){
        if(wantsJson(req)){
            Map<String,String> m = new HashMap<>();
            ex.getBindingResult().getFieldErrors().forEach(fe -> m.put(fe.getField(), fe.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
        } else {
            ModelAndView mv = new ModelAndView("forward:/not-found.html");
            return mv;
        }
    }

    @ExceptionHandler(Exception.class)
    public Object handleOther(Exception ex, HttpServletRequest req){
        if(wantsJson(req)){
            Map<String,String> m = new HashMap<>();
            m.put("error", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(m);
        } else {
            ModelAndView mv = new ModelAndView("forward:/not-found.html");
            return mv;
        }
    }
}
