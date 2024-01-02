package com.example.pruebajava.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.pruebajava.persistencia.entity.Producto;

// La anotación @Repository indica que esta interfaz es un componente de repositorio de Spring y permite el manejo de operaciones CRUD para la entidad Producto.
@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {
}
