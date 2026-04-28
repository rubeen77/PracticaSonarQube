package com.biblioteca.service;

import com.biblioteca.model.Libro;
import com.biblioteca.repository.LibroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibroServiceTest {

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroService libroService;

    private Libro libroDisponible;
    private Libro libroPrestado;

    @BeforeEach
    void setUp() {
        libroDisponible = new Libro("El Quijote", "Cervantes", 1605, "ISBN-001");
        libroPrestado = new Libro("1984", "Orwell", 1949, "ISBN-002");
        libroPrestado.setDisponible(false);
    }

    @Test
    void registrarLibro_deberiaLlamarGuardar() {
        libroService.registrarLibro(libroDisponible);
        verify(libroRepository, times(1)).guardar(libroDisponible);
    }

    @Test
    void buscarDisponibles_deberiaRetornarSoloDisponibles() {
        when(libroRepository.findAll()).thenReturn(Arrays.asList(libroDisponible, libroPrestado));

        List<Libro> resultado = libroService.buscarDisponibles();

        assertEquals(1, resultado.size());
        assertTrue(resultado.get(0).isDisponible());
    }

    @Test
    void prestarLibro_deberiaPonerLibroNoDisponible() {
        when(libroRepository.buscarPorIsbn("ISBN-001")).thenReturn(Optional.of(libroDisponible));

        libroService.prestarLibro("ISBN-001");

        assertFalse(libroDisponible.isDisponible());
    }

    @Test
    void devolverLibro_deberiaPonerLibroDisponible() {
        when(libroRepository.buscarPorIsbn("ISBN-002")).thenReturn(Optional.of(libroPrestado));

        libroService.devolverLibro("ISBN-002");

        assertTrue(libroPrestado.isDisponible());
    }

    @Test
    void prestarLibro_libroYaPrestado_deberiaMantenersePrestado() {
        when(libroRepository.buscarPorIsbn("ISBN-002")).thenReturn(Optional.of(libroPrestado));

        libroService.prestarLibro("ISBN-002");

        assertFalse(libroPrestado.isDisponible());
    }
}
