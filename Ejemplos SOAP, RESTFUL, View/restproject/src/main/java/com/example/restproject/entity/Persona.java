/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.restproject.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import jakarta.persistence.*;
import lombok.*;


/**
 *
 * @author USUARIO
 */
@Entity
@Table(name="persona")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Persona 
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int idpersona;
    
    String nombre;
    String apellido;
    String cedula;
    String correo;

    @Column(name = "estado", nullable = false)
    Boolean estado=true;
}
