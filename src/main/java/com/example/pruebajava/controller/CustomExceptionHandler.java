package com.example.pruebajava.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleProductoNotFoundException(ProductoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidProductoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInvalidProductoException(InvalidProductoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }



    // Excepción personalizada para cuando no se encuentra un producto
    public static class ProductoNotFoundException extends RuntimeException {
        public ProductoNotFoundException(Long id) {
            super("Producto con ID " + id + " no encontrado");
        }
    }

    // Excepción personalizada para cuando se proporciona un producto no válido
    public static class InvalidProductoException extends RuntimeException {
        public InvalidProductoException(String message) {
            super(message);
        }
    }
}