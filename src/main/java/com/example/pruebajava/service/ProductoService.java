package com.example.pruebajava.service;

import org.springframework.stereotype.Service;
import com.example.pruebajava.persistencia.entity.Producto;
import com.example.pruebajava.persistencia.repository.ProductoRepository;

import java.util.List;
// La anotación @Service indica que esta clase es un servicio de Spring y debe ser gestionada por el contenedor de Spring.
@Service
public class ProductoService {
    // Repositorio que maneja las operaciones de base de datos para la entidad Producto.

    private final ProductoRepository productoRepository;
    // Constructor que inyecta el repositorio a través de la inversión de control de dependencias (DI).
    public ProductoService(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }
    // Método para obtener todos los productos.
    public List<Producto> obtenerProductos(){
        return productoRepository.findAll();
    }
    // Método para obtener un producto por su ID.
    public  Producto obtenerPorId (Long id){
        return productoRepository.findById(id).orElse(null);
    }
    // Método para guardar un nuevo producto.
    public Producto guardarProducto (Producto producto){
        return productoRepository.save(producto);
    }
    // Método para actualizar un producto existente por su ID.
    public  Producto actualizarProducto (Producto producto, Long id){
        // Se busca el producto por ID y se realiza la actualización si existe.
        Producto producto2 = productoRepository.findById(id).map(
                producto1 -> {
                    producto1.setNombre(producto.getNombre());
                    producto1.setPrecio(producto.getPrecio());
                    return productoRepository.save(producto1);
                }
        ).get();
        return producto2;
    }
    // Método para eliminar un producto por su ID.
    public void eliminarProducto (Long id){
         productoRepository.deleteById(id);
    }
}
