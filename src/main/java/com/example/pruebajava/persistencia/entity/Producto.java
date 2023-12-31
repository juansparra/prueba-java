package com.example.pruebajava.persistencia.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "producto")
public class Producto {

    private static final long serialVersionUID = 123694641839120736L;
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;
    @NotBlank(message = "El nombre no puede estar en blanco")
    @Column(name = "nombre")
    String nombre;
    @NotNull(message = "El precio no puede ser nulo")
    @Column(name = "precio")
    double precio;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
