/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import apoio.ConexaoBD;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.spi.DirStateFactory;
import model.RequisitoModel;
import model.RequisitoVersaoModel;

/**
 *
 * @author forster
 */
public class RequisitoVersaoDAO implements IDAO<RequisitoVersaoModel> {

    @Override
    public boolean save(RequisitoVersaoModel model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<RequisitoVersaoModel> getLista() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public RequisitoVersaoModel getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean destroy(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public ArrayList<RequisitoVersaoModel> getListaByProjeto(int projetoId){
        
        ArrayList<RequisitoVersaoModel> lista = new ArrayList<RequisitoVersaoModel>();
        
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "select v.Id, r.codigo, r.titulo, v.versao, v.descricao from projeto_integrador_vii.requisito_versao as v " +
                        "inner join projeto_integrador_vii.requisito as r on v.requisito_id = r.id " +
                        "where r.projeto_id = " + projetoId;
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                int versaoId = Integer.parseInt(rs.getString("id"));
                String requisitoCodigo = rs.getString("codigo");
                String requisitoTitulo = rs.getString("titulo");
                int versao = Integer.parseInt(rs.getString("versao"));
                String versaoDescricao = rs.getString("descricao");
                
                RequisitoVersaoModel model = new RequisitoVersaoModel();
                model.setId(versaoId);
                model.setVersao(versao);
                model.setDescricao(versaoDescricao);
                
                RequisitoModel requisitoModel = new RequisitoModel();
                requisitoModel.setCodigo(requisitoCodigo);
                requisitoModel.setTitulo(requisitoTitulo);
                
                model.setRequisito(requisitoModel);
                
                lista.add(model);
            }
                    
        }catch(Exception e){
            System.out.println("Erro ao buscar requisitos pelo projeto: " + e);
        }
        
        return lista;
        
    }
    
    public boolean createNovaVersao(RequisitoVersaoModel model){
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
