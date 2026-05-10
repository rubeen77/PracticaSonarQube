package com.biblioteca.service;

import com.biblioteca.model.Libro;
import com.biblioteca.repository.LibroRepository;

import java.util.ArrayList;
import java.util.List;

public class LibroService {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LibroService.class.getName());
    private LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }


    public void registrarLibro(Libro libro) {
        libroRepository.guardar(libro);
    }


    public List<Libro> buscarDisponibles() {
        List<Libro> todos = libroRepository.findAll();
        List<Libro> disponibles = new ArrayList<>();


        for (Libro l : todos) {
            if (l.isDisponible()) {
                disponibles.add(l);
            }
        }
        return disponibles;
    }


    public void prestarLibro(String isbn) {
        Libro libro = libroRepository.buscarPorIsbn(isbn)  // Bug corregido
                .orElseThrow(() -> new IllegalArgumentException("Libro no encontrado: " + isbn));
        if (libro.isDisponible()) {
            libro.setDisponible(false);
        } else {
            // Code smell solucionado
            logger.warning("El libro no está disponible: " + isbn);;
        }
    }


    public String obtenerResumenLibro(String isbn) {
        Libro libro = libroRepository.buscarPorIsbn(isbn)  // Bug corregido
                .orElseThrow(() -> new IllegalArgumentException("Libro no encontrado: " + isbn));


        String estado = "";
        if (libro.isDisponible() == true) {
            estado = "DISPONIBLE";
        } else {
            estado = "PRESTADO";
        }

        // Code smell
        String resumen = "";
        resumen = resumen + "Título: " + libro.getTitulo() + "\n";
        resumen = resumen + "Autor: " + libro.getAutor() + "\n";
        resumen = resumen + "Año: " + libro.getAnio() + "\n";
        resumen = resumen + "Estado: " + estado + "\n";

        return resumen;
    }

    public void devolverLibro(String isbn) {
        Libro libro = libroRepository.buscarPorIsbn(isbn) // Bug corregido
                .orElseThrow(() -> new IllegalArgumentException("Libro no encontrado: " + isbn));
        libro.setDisponible(true);
    }
}
