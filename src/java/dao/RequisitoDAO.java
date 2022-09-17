/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import apoio.ConexaoBD;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.RequisitoModel;
import model.RequisitoVersaoModel;

/**
 *
 * @author forster
 */
public class RequisitoDAO implements IDAO<RequisitoModel>{

    @Override
    public boolean save(RequisitoModel model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<RequisitoModel> getLista() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public RequisitoModel getById(int id) {
        
        RequisitoModel requisitoModel = new RequisitoModel();
        
        try{
            
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "select * from projeto_integrador_vii.requisito where id ="+id;
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                
                int requisitoId = rs.getInt("id");
                
                String codigo = rs.getString("codigo");
                
                String titulo = rs.getString("titulo");
                
                requisitoModel.setId(requisitoId);
                requisitoModel.setCodigo(codigo);
                requisitoModel.setTitulo(titulo);
            }
            
        }catch(Exception e){
            System.out.println("Erro ao buscar requisito by id: " + e);
        }
        
        return requisitoModel;
        
    }

    @Override
    public boolean destroy(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
