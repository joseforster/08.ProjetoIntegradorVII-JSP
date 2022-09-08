/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author forster
 */
public class UsuarioModel {
    private int id;
    private String username;
    private String password;
    private String administrador;

    public UsuarioModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UsuarioModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdministrador() {
        return administrador;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }
    
    public String getAdministradorChecked (){
        
        if(this.administrador == "S"){
            return "checked";
        }
        
        return "";
    }
}