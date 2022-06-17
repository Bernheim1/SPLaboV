package com.example.splabov;

public class Usuario {

    private Integer id;
    private String nombre;
    private String rol;
    private Boolean admin;

    public Usuario(Integer id, String nombre, String rol, Boolean admin){
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
        this.admin = admin;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String toString(){
        return this.id.toString() + this.getNombre() + this.getRol() + this.getAdmin().toString();
    }
}
