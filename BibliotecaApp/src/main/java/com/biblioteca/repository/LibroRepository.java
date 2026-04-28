package com.biblioteca.repository;

import com.biblioteca.model.Libro;
import java.util.List;
import java.util.Optional;

public interface LibroRepository {
    void guardar(Libro libro);
    Optional<Libro> buscarPorIsbn(String isbn);
    List<Libro> findAll();
    void eliminar(String isbn);
}
