/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.restproject.repository;

import com.example.restproject.entity.Persona;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author USUARIO
 */
@Repository
public interface Ipersona extends JpaRepository<Persona,Serializable>
{
    public Persona findByIdpersona(Long id);
    
    @Query("Select p from Persona p where p.cedula LIKE %?1%")
    public List<Persona> findByCedula(String cedula);
    public void deleteByIdpersona(Long id);
}
