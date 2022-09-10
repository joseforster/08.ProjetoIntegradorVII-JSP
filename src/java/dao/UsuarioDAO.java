/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import apoio.ConexaoBD;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.UsuarioModel;

/**
 *
 * @author forster
 */
public class UsuarioDAO implements IDAO<UsuarioModel> {
    
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
    
    public UsuarioModel getById(int usuarioId){
        
        UsuarioModel model = new UsuarioModel();
        
        try{

               Statement st = ConexaoBD.getInstance().getConnection().createStatement();     

               String sql = "select * from projeto_integrador_vii.usuario "
                       + "where id = " + usuarioId;

               System.out.println(sql);

               ResultSet result = st.executeQuery(sql);

               while(result.next()){

                   model.setId(result.getInt("id"));
                   model.setUsername(result.getString("username"));
                   model.setAdministrador(result.getString("administrador"));
               }

           }catch(Exception e){

               System.out.println("Erro ao buscar usuário pelo id: " + e);

           }
     
        return model;
    
    }
    
    public ArrayList<UsuarioModel> getLista(){
    
        ArrayList<UsuarioModel> lista = new ArrayList<>();
        
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();  
            
            String sql = "select * from projeto_integrador_vii.usuario order by id";
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                UsuarioModel model = new UsuarioModel();
                
                model.setId(rs.getInt("id"));
                model.setUsername(rs.getString("username"));
                model.setAdministrador(rs.getString("administrador"));
                
                lista.add(model);
            }
                    
        }catch(Exception e){
            System.out.println("Erro ao buscar todos os usuários: " + e);
        }
        
        return lista;
        
    }
    
    public boolean destroy(int usuarioId){
        
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement(); 
            
            String sql = "delete from projeto_integrador_vii.usuario where id = " + usuarioId;
            
            st.executeUpdate(sql);
            
            return true;
            
        }catch(Exception e){
            
            System.out.println("Erro ao excluir usuário: "+e);
            
            return false;
        }
    }
    
    public boolean save(UsuarioModel model){
    
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement(); 
            
            String sql;
            
            if(model.getId() == 0){
                
              sql = "insert into projeto_integrador_vii.usuario values (default, '"
                      + model.getUsername() + "',"
                      + " md5('"+model.getPassword()+"'), "
                      + "'" + model.getAdministrador() + "')";
            }else{
                sql = "update projeto_integrador_vii.usuario "+
                        "set username = '"+model.getUsername()+"', "
                        + "administrador = '"+model.getAdministrador()+"' "
                        + "where id = " + model.getId();
            }
            
            st.executeUpdate(sql);
            
            return true;
            
        }catch(Exception e){
        
            System.out.println("Erro ao dar save no usuário: "+ e);
            
            return false;
        }
    }
    
    
}
