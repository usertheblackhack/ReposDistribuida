/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.restproject.service;

import com.example.restproject.entity.Persona;
import com.example.restproject.repository.Ipersona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author USUARIO
 */
@Service
public class Spersona 
{
    @Autowired
    private Ipersona person_inter;
    
    public Persona buscarByIdPersona(Long id)
    {
        return person_inter.findByIdpersona(id);
    }
    
    public List<Persona> buscarByCedula(String cedula)
    {
        return person_inter.findByCedula(cedula);
    }
    
    public List<Persona> listar()
    {
        return person_inter.findAll();
    }
    
    
    public Persona guardar(Persona per)
    {
        return person_inter.save(per);
    }
    
    public void eliminar(Persona person)
    {
        person_inter.delete(person);
    } 
}
