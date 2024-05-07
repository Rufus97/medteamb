package com.medteamb.medteamb.service.ExceptionHandler.PatientHandler;

<<<<<<< HEAD


=======
>>>>>>> 4c1455b5073586f65b8ba742c28db9ded35e6e1b
import com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions.ConflictException;
import com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions.NotFound;
import com.medteamb.medteamb.service.ResponseHandler.BaseResponse;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
<<<<<<< HEAD

=======
>>>>>>> 4c1455b5073586f65b8ba742c28db9ded35e6e1b
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PatientHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFound.class)
    public BaseResponse patientNotFound(NotFound e){
       return new BaseResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(PropertyValueException.class)
    public BaseResponse patientNotSaved(PropertyValueException e) {
        String exMessage = e.getPropertyName() + " cannot be null";
        return new BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR, exMessage);
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public BaseResponse patientConflictException(ConflictException e){
        return new BaseResponse(HttpStatus.CONFLICT, e.getMessage());
    }
}
