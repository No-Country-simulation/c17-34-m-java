package com.c174.controllers.handlrexcetion;

import com.c174.exception.*;
import com.c174.models.ExceptionDto;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice(annotations = RestController.class)
public class ApiControllerAdvice {
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ExceptionDto exceptionValidacionException(MethodArgumentNotValidException ex){
        List<FieldError> errorList = ex.getBindingResult().getFieldErrors();
        Map<String, Object> detalle = new HashMap<>();
        errorList.forEach(error -> detalle.put(error.getField(), error.getDefaultMessage()));
        return new ExceptionDto(HttpStatus.BAD_REQUEST.value(),"Validaciones - Los datos ingresados no son correctos o son insuficientes", detalle, Boolean.FALSE);

    }
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto exceptionEntityNotFound(EntityNotFoundException ex){
        HashMap<String, Object> detalle = new HashMap<>();
        detalle.put("error", ex.getMessage());

        return new ExceptionDto(HttpStatus.NOT_FOUND.value(),"No se encontraron datos", detalle, Boolean.FALSE);

    }

    @ExceptionHandler(EntityExistsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto exceptionEntityExist(EntityExistsException ex){
        HashMap<String, Object> detalle = new HashMap<>();
        detalle.put("error", ex.getMessage());
        return new ExceptionDto(HttpStatus.BAD_REQUEST.value(),"Ya existe esa entidad", detalle, Boolean.FALSE);

    }

    @ExceptionHandler(EntityDeleteException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto exceptionDeleteException(EntityDeleteException ex){
        HashMap<String, Object> detalle = new HashMap<>();
        detalle.put("error", ex.getMessage());
        return new ExceptionDto(HttpStatus.BAD_REQUEST.value(),"Ese dato fue borrado de la base de datos", detalle, Boolean.FALSE);
    }

    @ExceptionHandler(NoBodyException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto exceptionNoBodyException(NoBodyException ex){
        HashMap<String, Object> detalle = new HashMap<>();
        detalle.put("error", ex.getMessage());
        return new ExceptionDto(HttpStatus.BAD_REQUEST.value(),"Falta el body del pedido", detalle, Boolean.FALSE);
    }

    @ExceptionHandler({MPApiException.class, MPException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto exceptionMPException(MPException ex){
        HashMap<String, Object> detalle = new HashMap<>();
        detalle.put("error", ex.getMessage());
        return new ExceptionDto(HttpStatus.BAD_REQUEST.value(),"Error en Mercado Pago", detalle, Boolean.FALSE);
    }


}
