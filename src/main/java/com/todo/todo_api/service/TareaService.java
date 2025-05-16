/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.todo.todo_api.service;

import com.todo.todo_api.model.Tarea;
import com.todo.todo_api.repository.TareaRepository;
import exception.BadRequestException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Owner
 */

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public List<Tarea> listarTodas() {
        return tareaRepository.findAll();
    }

    public Tarea guardar(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public Tarea obtenerPorId(Long id) {
        return tareaRepository.findById(id)
            .orElseThrow(() -> new BadRequestException("Tarea no encontrada con id " + id));
    }

    public void eliminar(Long id) {
        if (!tareaRepository.existsById(id)) {
            throw new BadRequestException("Tarea no encontrada con id " + id);
        }
        tareaRepository.deleteById(id);
    }
}
