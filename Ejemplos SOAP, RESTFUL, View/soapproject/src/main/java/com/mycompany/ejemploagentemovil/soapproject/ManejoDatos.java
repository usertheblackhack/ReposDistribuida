/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejemploagentemovil.soapproject;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class ManejoDatos 
{
    private static String insertar = "insert into persona (nombre,apellido,cedula,correo) values (?,?,?,?) returning idpersona,nombre,apellido,cedula,correo,estado";
    private static String seleccionar = "select * from persona";
    private static String eliminar = "delete from persona where idpersona=? returning idpersona,nombre,apellido,cedula,correo,estado";
    private static String actualizar = "update persona set nombre=?,apellido=?,cedula=?,correo=?,estado=? where idpersona=? returning idpersona,nombre,apellido,cedula,correo,estado";
    private static String buscarC="select * from persona p where p.cedula like ?";

    public Persona insertar(Persona p) {
        try {
            ConexionPostgres cxp = new ConexionPostgres();

            if (p != null) {
                CallableStatement cs = cxp.getConexion().prepareCall(insertar);
                cs.setString(1, p.getNombre());
                cs.setString(2, p.getApellido());
                cs.setString(3, p.getCedula());
                cs.setString(4, p.getCorreo());
                cs.execute();
                ResultSet rs=cs.getResultSet();
                rs.next();
                Persona pp=new Persona();
                pp.setIdpersona(rs.getInt("idpersona"));
                pp.setCedula(rs.getString("cedula"));
                pp.setNombre(rs.getString("nombre"));
                pp.setApellido(rs.getString("apellido"));
                pp.setCorreo(rs.getString("correo"));
                pp.setEstado(rs.getBoolean("estado"));
                System.out.println(pp.getCedula());
                return pp;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public List<Persona> buscar(String cedula) {
        try {
            
            ConexionPostgres cxp = new ConexionPostgres();
            
            if (cedula != null) {
                CallableStatement cs= cxp.getConexion().prepareCall(buscarC);
                cs.setString(1, "%"+cedula+"%");
                
                cs.execute();
                ResultSet rs=cs.getResultSet();
                Persona p=null;
                List<Persona> lst_person=new ArrayList<>();
                
                while(rs.next())
                {
                    System.out.println(cedula);
                    p=new Persona();
                    p.setIdpersona(rs.getInt("idpersona"));
                    p.setCedula(rs.getString("cedula"));
                    p.setNombre(rs.getString("nombre"));
                    p.setApellido(rs.getString("apellido"));
                    p.setCorreo(rs.getString("correo"));
                    p.setEstado(rs.getBoolean("estado"));
                    lst_person.add(p);
                }
                return lst_person;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
      public Persona eliminar(Integer id) {
        try {
            
            ConexionPostgres cxp = new ConexionPostgres();

            if (id != null) {
                CallableStatement cs = cxp.getConexion().prepareCall(eliminar);
                cs.setInt(1, id);
                cs.execute();
                ResultSet rs=cs.getResultSet();
                rs.next();
                Persona p=new Persona();
                p.setIdpersona(rs.getInt("idpersona"));
                p.setCedula(rs.getString("cedula"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setCorreo(rs.getString("correo"));
                p.setEstado(rs.getBoolean("estado"));
                return p;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    
    
    public List<Persona> listar()
    {
        try 
        {
            ConexionPostgres cxp = new ConexionPostgres();
            CallableStatement cs = cxp.getConexion().prepareCall(seleccionar);
            cs.execute();
            ResultSet rs=cs.getResultSet();
            
            Persona p;
            List<Persona> lstpersona=new ArrayList<Persona>();
            while(rs.next())
            {
                p=new Persona();
                p.setIdpersona(rs.getInt("idpersona"));
                p.setCedula(rs.getString("cedula"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setCorreo(rs.getString("correo"));
                p.setEstado(rs.getBoolean("estado"));
                lstpersona.add(p);
            }
            return lstpersona;
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    
    public Persona actualizar(Persona p) 
    {
        try
        {
            ConexionPostgres cxp = new ConexionPostgres();
            //System.out.print(p.getCedula()+" ");
            if (p != null) 
            {
                CallableStatement cs = cxp.getConexion().prepareCall(actualizar);
                cs.setString(1, p.getNombre());
                cs.setString(2, p.getApellido());
                cs.setString(3, p.getCedula());
                cs.setString(4, p.getCorreo());
                //System.out.print(p.getEstado()+" oka");
                cs.setBoolean(5, p.getEstado());
                cs.setInt(6, p.getIdpersona());
                System.out.print(p.getCedula()+" ");
                cs.execute();
                ResultSet rs=cs.getResultSet();
                rs.next();
                Persona pp=new Persona();
                pp.setIdpersona(rs.getInt("idpersona"));
                pp.setCedula(rs.getString("cedula"));
                pp.setNombre(rs.getString("nombre"));
                pp.setApellido(rs.getString("apellido"));
                pp.setCorreo(rs.getString("correo"));
                pp.setEstado(rs.getBoolean("estado"));
                
                return pp;
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage()+"fdff");
        }
        System.out.print(p.getIdpersona()+" holiiiiiii");
        return null;
    }
    
    
    
}
