package com.example.pruebajava.controller;

import com.example.pruebajava.persistencia.entity.Producto;
import com.example.pruebajava.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductoController {
    @Autowired
private ProductoService productoService;

    @GetMapping("/productos")
    public List<Producto> getProductos()   {
        return productoService.obtenerProductos();
    }

    @GetMapping("productos/{id}")
    public ResponseEntity<Producto> getProductoById(Long id){
        Producto producto = productoService.obtenerPorId(id);
        if (producto == null) {
            throw new CustomExceptionHandler.ProductoNotFoundException(id);
        }
        return  ResponseEntity.ok(producto);
    }

    @PostMapping( "/GuardarProductos")
    public ResponseEntity<String> crearProducto(@Valid @RequestBody Producto newProducto){
        if (newProducto.getPrecio() < 0) {
            throw new CustomExceptionHandler.InvalidProductoException("El precio no puede ser negativo");
        }
        productoService.guardarProducto(newProducto);
        return ResponseEntity.ok("Producto creado exitosamente");
    }

    @DeleteMapping("productos/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(@RequestBody Producto newProducto, @PathVariable Long id){
        return new ResponseEntity<>(productoService.actualizarProducto(newProducto,id),HttpStatus.OK);
    }

}
