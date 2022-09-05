/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import apoio.ConexaoBD;
import java.sql.ResultSet;
import java.sql.Statement;
import model.UsuarioModel;

/**
 *
 * @author forster
 */
public class UsuarioDAO {
    public UsuarioModel getUsuario(UsuarioModel model){
        try{
        
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();     
            
            String sql = "select * from projeto_integrador_vii.usuario where "
                    + "username like '"+model.getUsername()+"' and "
                    + "password like md5('"+model.getPassword()+"')";
            
            System.out.println(sql);
            
            ResultSet result = st.executeQuery(sql);
            
            UsuarioModel usuarioModel = new UsuarioModel();
            
            while(result.next()){
                    
                int id = result.getInt("id");
            
                String administrador = result.getString("administrador");
                
                usuarioModel.setId(id);
                usuarioModel.setUsername(model.getUsername());
                usuarioModel.setPassword(model.getPassword());
                usuarioModel.setAdministrador(administrador);
                
                return usuarioModel;
            }
            
            return null;
            
        }catch(Exception e){
            
            System.out.println("Erro ao autenticar usuário: " + e);
            
            return null;
        }
    }
}
