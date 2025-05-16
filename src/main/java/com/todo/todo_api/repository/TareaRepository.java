/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.todo.todo_api.repository;

import com.todo.todo_api.model.Tarea;
import jakarta.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Owner
 */
public interface TareaRepository extends JpaRepository<Tarea, Long>
{

    public Object findById(SingularAttribute<AbstractPersistable, Serializable> id);
    
}
