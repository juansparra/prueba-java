package com.example.pruebajava.service;

import org.springframework.stereotype.Service;
import com.example.pruebajava.persistencia.entity.Producto;
import com.example.pruebajava.persistencia.repository.ProductoRepository;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    public List<Producto> obtenerProductos(){
        return productoRepository.findAll();
    }

    public  Producto obtenerPorId (Long id){
        return productoRepository.findById(id).orElse(null);
    }


    public Producto guardarProducto (Producto producto){
        return productoRepository.save(producto);
    }
    public  Producto actualizarProducto (Producto producto, Long id){
        Producto producto2 = productoRepository.findById(id).map(
                producto1 -> {
                    producto1.setNombre(producto.getNombre());
                    producto1.setPrecio(producto.getPrecio());
                    return productoRepository.save(producto1);
                }
        ).get();
        return producto2;
    }

    public void eliminarProducto (Long id){
         productoRepository.deleteById(id);
    }
}
