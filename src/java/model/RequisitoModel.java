/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author forster
 */
public class RequisitoModel {
    private int id;
    private String codigo;
    private String titulo;
    private ProjetoModel projeto;

    public RequisitoModel(String codigo, String titulo, ProjetoModel projeto) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.projeto = projeto;
    }

    public RequisitoModel() {
    }
    
    

    public RequisitoModel(int id, String codigo, String titulo, ProjetoModel projeto) {
        this.id = id;
        this.codigo = codigo;
        this.titulo = titulo;
        this.projeto = projeto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ProjetoModel getProjeto() {
        return projeto;
    }

    public void setProjeto(ProjetoModel projeto) {
        this.projeto = projeto;
    }
    
    
}
