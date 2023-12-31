package com.example.pruebajava.controller;


import com.example.pruebajava.persistencia.entity.ListaReproduccion;
import com.example.pruebajava.service.ListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class ListaRestController {
    @Autowired
    private ListaService listaService;

    @GetMapping("/lists")
    public List<ListaReproduccion> getAll() {
        return listaService.getAllListas();
    }

    @GetMapping("/lists/{nombre}")
    public ResponseEntity<?> getLista(@PathVariable("nombre") String nombre) {
        Optional<ListaReproduccion> listaReproduccion = null;
        Map<String, Object> response = new HashMap<>();
        try {
            listaReproduccion = listaService.getLista(nombre);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (listaReproduccion == null) {
            response.put("mensaje",
                    "La lista: ".concat(nombre.toString().concat(" no existe en nuestra base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<ListaReproduccion>>(listaReproduccion, HttpStatus.OK);

    }

    @PostMapping("/lists")
    public ResponseEntity<ListaReproduccion> save(@RequestBody ListaReproduccion listaReproduccion) {
        return new ResponseEntity<>(listaService.save(listaReproduccion), HttpStatus.CREATED);
    }

    @DeleteMapping("/lists/{nombre}")
    ResponseEntity<?> deleteByNombre(@PathVariable("nombre") String nombre) {
        Optional<ListaReproduccion> listaServiceOptional = listaService.getLista(nombre);
        if (!listaServiceOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        listaService.deleteByNombre(nombre);
        return ResponseEntity.noContent().build();
    }


}
