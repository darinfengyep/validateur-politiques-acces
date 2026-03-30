package com.darin.validateuracces.controleur;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GestionnaireErreurs {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> gererValidation(MethodArgumentNotValidException exception) {
        Map<String, Object> reponse = new HashMap<>();
        Map<String, String> erreurs = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(erreur -> erreurs.put(erreur.getField(), erreur.getDefaultMessage()));
        reponse.put("message", "La requête contient des erreurs de validation.");
        reponse.put("erreurs", erreurs);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reponse);
    }
}
