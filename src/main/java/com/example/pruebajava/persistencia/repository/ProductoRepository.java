package com.example.pruebajava.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.pruebajava.persistencia.entity.Producto;
@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {
}
