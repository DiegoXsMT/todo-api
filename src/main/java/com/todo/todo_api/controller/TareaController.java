package com.todo.todo_api.controller;

import com.todo.todo_api.model.Tarea;
import com.todo.todo_api.service.TareaService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    // 1. Crear un nuevo ToDo (POST)
    @PostMapping
    public ResponseEntity<Tarea> crearTarea(@Valid @RequestBody Tarea tarea) 
    {
        
        Tarea tareaGuardada = tareaService.guardar(tarea);
        return new ResponseEntity<>(tareaGuardada, HttpStatus.CREATED);
    }

    // 2. Obtener todos los ToDos (GET)
    @GetMapping
    public ResponseEntity<List<Tarea>> obtenerTodas() 
    {
        return ResponseEntity.ok(tareaService.listarTodas());
    }

    // Obtener tarea por id (GET /api/tareas/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Tarea> obtenerPorId(@PathVariable Long id) 
    {
        Tarea tarea = tareaService.obtenerPorId(id);
        return ResponseEntity.ok(tarea);
    }
    // 3. Actualizar toda la tarea (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizarTarea(@PathVariable Long id, @Valid @RequestBody Tarea tareaDetalles) 
    {
        Tarea tareaExistente = tareaService.obtenerPorId(id);

        tareaExistente.setDescripcion(tareaDetalles.getDescripcion());
        tareaExistente.setFecha(tareaDetalles.getFecha());
        tareaExistente.setEstatus(tareaDetalles.getEstatus());

        Tarea tareaActualizada = tareaService.guardar(tareaExistente);
        return ResponseEntity.ok(tareaActualizada);
    }

    // 4. Cambiar solo el estatus (PATCH)
    @PatchMapping("/{id}/estatus")
    public ResponseEntity<Tarea> cambiarEstatus(@PathVariable Long id, @RequestBody Tarea.Estatus estatus) 
    {
        Tarea tareaExistente = tareaService.obtenerPorId(id);
        tareaExistente.setEstatus(estatus);
        Tarea tareaActualizada = tareaService.guardar(tareaExistente);
        return ResponseEntity.ok(tareaActualizada);
    }

    // 5. Eliminar por ID (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id) 
    {
        tareaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
