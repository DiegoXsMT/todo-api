package com.todo.todo_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @NotNull(message = "El estatus no puede estar vacío")
    @Enumerated(EnumType.STRING)
    private Estatus estatus;

    public enum Estatus {
        pendiente, progreso, completado
    }

    public Long getId()
    { 
        return id; 
    }
    public void setId(Long id) 
    { 
        this.id = id; 
    }
    public String getDescripcion() 
    { 
        return descripcion; 
    }
    public void setDescripcion(String descripcion) 
    { 
        this.descripcion = descripcion; 
    }
    public LocalDate getFecha() 
    { 
        return fecha;
    }
    public void setFecha(LocalDate fecha) 
    { 
        this.fecha = fecha; 
    }
    public Estatus getEstatus() 
    { 
        return estatus; 
    }
    public void setEstatus(Estatus estatus) 
    { 
        this.estatus = estatus; 
    }
}
