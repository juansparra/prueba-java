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
// Controlador para la entidad Producto.
public class ProductoController {

    // Se inyecta el servicio ProductoService utilizando la anotación @Autowired.
    @Autowired
private ProductoService productoService;
    // Endpoint para obtener todos los productos.
    @GetMapping("/productos")
    public List<Producto> getProductos()   {
        // Llama al método obtenerProductos del servicio ProductoService.
        return productoService.obtenerProductos();
    }
    // Endpoint para obtener un producto por su ID.
    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable("id") Long id){
        // Llama al método obtenerPorId del servicio ProductoService.
        Producto producto = productoService.obtenerPorId(id);
        // Si el producto no se encuentra, lanza una excepción personalizada.
        if (producto == null) {
            throw new CustomExceptionHandler.ProductoNotFoundException(id);
        }
        // Devuelve una respuesta con el producto y el código HTTP OK (200).
        return  ResponseEntity.ok(producto);
    }
    // Endpoint para crear un nuevo producto.
    @PostMapping( "/productos")
    public ResponseEntity<String> crearProducto(@Valid @RequestBody Producto newProducto){
        // Verifica que el precio del nuevo producto no sea negativo.
        if (newProducto.getPrecio() < 0) {
            throw new CustomExceptionHandler.InvalidProductoException("El precio no puede ser negativo");
        }
        // Llama al método guardarProducto del servicio ProductoService.
        productoService.guardarProducto(newProducto);
        // Devuelve una respuesta con un mensaje de éxito y el código HTTP OK (200).
        return ResponseEntity.ok("Producto creado exitosamente");
    }
    // Endpoint para eliminar un producto por su ID.
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable("id") Long id) {
        productoService.eliminarProducto(id);
        // Devuelve una respuesta sin contenido y el código HTTP NO_CONTENT (204).
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    // Endpoint para actualizar un producto existente por su ID.
    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(@RequestBody Producto newProducto, @PathVariable("id") Long id){
        return new ResponseEntity<>(productoService.actualizarProducto(newProducto,id),HttpStatus.OK);
    }

}
