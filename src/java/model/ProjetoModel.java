/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author forster
 */
public class ProjetoModel {
    private int id;
    private String descricao;
    private UsuarioModel usuario_criacao;

    public ProjetoModel(String nome, UsuarioModel usuario_criacao) {
        this.descricao = nome;
        this.usuario_criacao = usuario_criacao;
    }

    public ProjetoModel() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UsuarioModel getUsuario_criacao() {
        return usuario_criacao;
    }

    public void setUsuario_criacao(UsuarioModel usuario_criacao) {
        this.usuario_criacao = usuario_criacao;
    }
}