package model;

public class RequisitoVersaoModel {
    
    private int id;
    private RequisitoModel requisito;
    private int versao;
    private String descricao;

    public RequisitoVersaoModel(RequisitoModel requisito, int versao, String descricao) {
        this.requisito = requisito;
        this.versao = versao;
        this.descricao = descricao;
    }

    public RequisitoVersaoModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public RequisitoModel getRequisito() {
        return requisito;
    }

    public void setRequisito(RequisitoModel requisito) {
        this.requisito = requisito;
    }

    public int getVersao() {
        return versao;
    }

    public void setVersao(int versao) {
        this.versao = versao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
