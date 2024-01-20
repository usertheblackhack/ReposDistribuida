/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.restproject.controller;

import com.example.restproject.entity.Persona;
import com.example.restproject.service.Spersona;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author USUARIO
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class ControllerApi 
{
    @Autowired
    Spersona spersona;
    
    @PostMapping("/listar")
    public ResponseEntity<?> listar()
    {
        Map<String,Object> response=new HashMap();
        try
        {
            List<Persona> lp=spersona.listar();
            return new ResponseEntity<List<Persona>>(lp,HttpStatus.OK);
        }
        catch(Exception ex)
        {
            response.put("error", ex.getMessage());
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
    }
    
    
    @PostMapping("/insertar")
    public ResponseEntity<?> insertar(Persona person)
    {
        Map<String,Object> response=new HashMap();
        try
        {
            
            Persona p=spersona.guardar(person);
            
            return new ResponseEntity<Persona>(p,HttpStatus.OK);
        }
        catch(Exception ex)
        {
            response.put("error", ex.getMessage());
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminar(@RequestParam(name="id") Long id)
    {
         Map<String,Object> response=new HashMap();
        try
        {
            Persona p=spersona.buscarByIdPersona(id);
            spersona.eliminar(p);
            return new ResponseEntity<Persona>(p,HttpStatus.OK);
        }
        catch(Exception ex)
        {
            response.put("error", ex.getMessage());
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
    }
    
    
    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(Persona p)
    {
        Map<String,Object> response=new HashMap();
        try
        {
            //Persona p=spersona.buscar(id);
            p=spersona.guardar(p);
            return new ResponseEntity<Persona>(p,HttpStatus.OK);
        }
        catch(Exception ex)
        {
            response.put("error", ex.getMessage());
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/buscar")
    public ResponseEntity<?> buscar(@RequestParam(name="cedula") String cedula)
    {
        Map<String,Object> response=new HashMap();
        try
        {
            System.out.println(cedula+" ----");
            List<Persona> p=spersona.buscarByCedula(cedula);
            return new ResponseEntity<List<Persona>>(p,HttpStatus.OK);
        }
        catch(Exception ex)
        {
            response.put("error", ex.getMessage());
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
    }
}
