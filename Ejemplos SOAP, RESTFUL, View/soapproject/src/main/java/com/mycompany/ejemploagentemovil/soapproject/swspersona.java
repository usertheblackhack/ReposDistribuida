/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.mycompany.ejemploagentemovil.soapproject;

import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author USUARIO
 */
@WebService(serviceName = "swspersona")
public class swspersona {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "insertarPersona")
    public Persona insertarPersona(@WebParam(name = "persona") Persona person) 
    {
        ManejoDatos md=new ManejoDatos();
        Persona p=md.insertar(person);
        return p;
    }
    

    @WebMethod(operationName = "buscarPersona")
    public List<Persona> buscarPersona(@WebParam(name = "cedula") String cedula) 
    {
        ManejoDatos md=new ManejoDatos();
        List<Persona> p=md.buscar(cedula);
        return p;
    }
    
    
    @WebMethod(operationName = "eliminarPersona")
    public Persona eliminarPersona(@WebParam(name = "id") int id) 
    {
        ManejoDatos md=new ManejoDatos();
        Persona p=md.eliminar(id);
        return p;
    }
    
    
    @WebMethod(operationName = "listarPersonas")
    public List<Persona> listarPersonas() 
    {
        ManejoDatos md=new ManejoDatos();
        List<Persona> lstp=md.listar();
        return lstp;
    }
    
    
    @WebMethod(operationName = "actualizarPersona")
    public Persona actualizarPersona(@WebParam(name = "persona") Persona person) 
    {
        ManejoDatos md=new ManejoDatos();
        Persona p=md.actualizar(person);
        return p;
    }

}
