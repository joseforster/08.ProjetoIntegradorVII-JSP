/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import apoio.ConexaoBD;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.ProjetoModel;
import model.UsuarioModel;

/**
 *
 * @author forster
 */
public class ProjetoDAO {
    
    public ArrayList<ProjetoModel> getListaProjeto(){
        
        ArrayList<ProjetoModel> lista = new ArrayList<>();
        
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql 
                    = "select p.* , u.username "
                    + "from projeto_integrador_vii.projeto as p "
                    + "inner join projeto_integrador_vii.usuario as u on p.usuario_id = u.id "
                    + "order by p.id";

            System.out.println("SQL: " + sql);

            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next()) {

                ProjetoModel p = new ProjetoModel();
                p.setId(resultado.getInt("id"));
                p.setDescricao(resultado.getString("descricao"));

                UsuarioModel usuario = new UsuarioModel();

                usuario.setUsername(resultado.getString("username"));

                p.setUsuario_criacao(usuario);

                lista.add(p);
            }

        }catch(Exception e){

            System.out.println("Erro ao buscar lista de projetos: " +e);
        }

        return lista;
    }
    
    public boolean create(ProjetoModel model){
        try{
        
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();     
            
            String sql = "insert into projeto_integrador_vii.projeto values ("
                        + "default,"
                        + "'" + model.getDescricao()+ "', "
                        + model.getUsuario_criacao().getId()+ ");";
                
            System.out.println("SQL: " + sql);

            int resultado = st.executeUpdate(sql);
            
            return true;
            
        }catch(Exception e){
            System.out.println("Erro ao criar projeto: "+ e);
            return false;
        }
    }
    
    public boolean destroy(int id){
        try{
        
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();     
            
            String sql = "delete from projeto_integrador_vii.projeto where id = " + id;
                
            System.out.println("SQL: " + sql);

            int resultado = st.executeUpdate(sql);
            
            return true;
            
        }catch(Exception e){
            System.out.println("Erro ao excluir projeto: "+ e);
            return false;
        }
    }
}
