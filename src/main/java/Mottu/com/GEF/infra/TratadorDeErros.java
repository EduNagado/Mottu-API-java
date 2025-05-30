package Mottu.com.GEF.infra;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErros {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> TratarErro404(){
        return ResponseEntity.notFound().build();
    }

  @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> TratarErro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErrosValidacao::new).toList());
    }

    private record DadosErrosValidacao(String campo,  String mensagem){
        public DadosErrosValidacao(FieldError erro) {
            this(erro.getField(),erro.getDefaultMessage());
        }
    }

}
