package com.example.tvtcsknow.utility;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;
    private WebRequest webRequest;

    @BeforeEach
    public void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
        webRequest = mock(WebRequest.class);
    }

    @Test
    public void testHandleHttpMessageNotReadable() {
        HttpMessageNotReadableException ex = mock(HttpMessageNotReadableException.class);
        when(ex.getMessage()).thenReturn("Malformed JSON request");

        ResponseEntity<Object> responseEntity = globalExceptionHandler.handleHttpMessageNotReadable(ex, null, null, webRequest);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        ApiError apiError = (ApiError) responseEntity.getBody();
        assertEquals("Malformed JSON request", apiError.getMessage());
    }

    @Test
    public void testHandleEntityNotFound() {
        EntityNotFoundException ex = mock(EntityNotFoundException.class);
        when(ex.getMessage()).thenReturn("Entity not found");

        ResponseEntity<Object> responseEntity = globalExceptionHandler.handleEntityNotFound(ex);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        ApiError apiError = (ApiError) responseEntity.getBody();
        assertEquals("Entity not found", apiError.getMessage());
    }

    @Test
    public void testHandleIllegalArgumentException() {
        IllegalArgumentException ex = mock(IllegalArgumentException.class);
        when(ex.getMessage()).thenReturn("Illegal argument");

        ResponseEntity<Object> responseEntity = globalExceptionHandler.handleIllegalArgumentException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        ApiError apiError = (ApiError) responseEntity.getBody();
        assertEquals("Illegal argument", apiError.getMessage());
    }

    @Test
    public void testHandleIllegalStateException() {
        IllegalStateException ex = mock(IllegalStateException.class);
        when(ex.getMessage()).thenReturn("Illegal state");

        ResponseEntity<Object> responseEntity = globalExceptionHandler.handleIllegalStateException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        ApiError apiError = (ApiError) responseEntity.getBody();
        assertEquals("Illegal state", apiError.getMessage());
    }

    @Test
    public void testHandleException() {
        Exception ex = mock(Exception.class);
        when(ex.getMessage()).thenReturn("General exception");

        ResponseEntity<Object> responseEntity = globalExceptionHandler.handleException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        ApiError apiError = (ApiError) responseEntity.getBody();
        assertEquals("General exception", apiError.getMessage());
    }
}
