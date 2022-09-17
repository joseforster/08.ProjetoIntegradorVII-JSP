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
        
        try{
            
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql;
            
            // insert
            if(model.getId() == 0){
                 sql = "insert into projeto_integrador_vii.requisito values (default, "
                         +"'"+model.getRequisito().getCodigo()+"',"  
                         +"'"+model.getRequisito().getTitulo()+"',"
                         +""+model.getRequisito().getProjeto().getId() +
                         ") returning id;";
                 
                 System.out.println(sql);
                 
                 int requisitoId = 0;
                         
                 ResultSet result = st.executeQuery(sql);
                 
                 while(result.next()){
                     requisitoId = result.getInt("id");
                 }
                 
                 sql = "insert into projeto_integrador_vii.requisito_versao values (default, "
                         +requisitoId+", "
                         +"1, "
                         +"'"+model.getDescricao()+"', default)";
                 
                 System.out.println(sql);
                 
                 st.executeUpdate(sql);
            }
            
            //update
            if(model.getId() > 0 && 
                model.getRequisito().getCodigo() != null && 
                model.getRequisito().getTitulo() != null)
            {
                
                sql = "update projeto_integrador_vii.requisito "
                + "set codigo = '"+model.getRequisito().getCodigo()+"', "
                + "titulo = '"+model.getRequisito().getTitulo()+"' "
                + "where id = "+model.getRequisito().getId();
                
                System.out.println(sql);
                        
                st.executeUpdate(sql);
                
                sql = "update projeto_integrador_vii.requisito_versao "
                + "set descricao = '"+model.getDescricao()+"' "
                + "where id = " + model.getId();
                
                System.out.println(sql);
                
                st.executeUpdate(sql);
            }
            
            //nova versao
            if(model.getId() > 0 && 
                model.getRequisito().getCodigo() == null && 
                model.getRequisito().getTitulo() == null && 
                model.getDescricao() != null)
            {
                sql = "select id, versao from projeto_integrador_vii.requisito_versao where id in " +
                "(select max(id) as id from projeto_integrador_vii.requisito_versao " +
                "where requisito_id =" + model.getRequisito().getId() +" and ativo = 'S');";
                
                System.out.println(sql);
                
                ResultSet result = st.executeQuery(sql);
                
                int UltimaVersaoId = 0;
                int UltimaVersaoNumero = 0;
                
                while(result.next()){
                    UltimaVersaoId = result.getInt("id");
                    UltimaVersaoNumero = result.getInt("versao");
                }
                
                sql = "update projeto_integrador_vii.requisito_versao "
                        + "set ativo = 'N' where id = " + UltimaVersaoId;
                
                System.out.println(sql);
                
                st.executeUpdate(sql);
                
                sql = "insert into projeto_integrador_vii.requisito_versao values (default, "
                        + model.getRequisito().getId() + ", "
                        + (UltimaVersaoNumero + 1) + ", " 
                        + "'" + model.getDescricao() + "', default)";
                
                System.out.println(sql);
                
                st.executeUpdate(sql);
                
            }
            
            return true;
            
        }catch(Exception e ){
            System.out.println("Erro ao salvar requisito versao: " + e);
            return false;
        }
    }

    @Override
    public ArrayList<RequisitoVersaoModel> getLista() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public RequisitoVersaoModel getById(int id) {
        
        RequisitoVersaoModel requisitoVersaoModel = new RequisitoVersaoModel();
        
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "select " +
            " rv.id, " +
            " rv.descricao, " +
            " r.id as requisito_id," +
            " r.codigo as requisito_codigo," +
            " r.titulo as requisito_titulo " +
            "from projeto_integrador_vii.requisito_versao as rv " +
            "inner join projeto_integrador_vii.requisito as r on rv.requisito_id = r.id " +
            "where rv.id = " + id;
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                
                int requisitoVersaoId = rs.getInt("id");
                String requisitoVersaoDescricao = rs.getString("descricao");
                int requisitoId = rs.getInt("requisito_id");
                String requisitoCodigo = rs.getString("requisito_codigo");
                String requisitoTitulo = rs.getString("requisito_titulo");
            
                requisitoVersaoModel.setId(requisitoVersaoId);
                requisitoVersaoModel.setDescricao(requisitoVersaoDescricao);
                
                RequisitoModel requisitoVersao = new RequisitoModel();
                
                requisitoVersao.setId(requisitoId);
                requisitoVersao.setCodigo(requisitoCodigo);
                requisitoVersao.setTitulo(requisitoTitulo);
                
                requisitoVersaoModel.setRequisito(requisitoVersao);
                
            }
            
        }catch(Exception e){
            System.out.println("Erro ao buscar requisito versão by id:" + e);
        }
        
        return requisitoVersaoModel;
    }

    @Override
    public boolean destroy(int id) {
        
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "select  requisito_id from projeto_integrador_vii.requisito_versao " +
            "where id = " + id;
            
            System.out.println(sql);
            
            ResultSet result = st.executeQuery(sql);
            
            int requisitoId = 0;
            
            while(result.next()){
                requisitoId = result.getInt("requisito_id");
            }
            
            sql = "delete from projeto_integrador_vii.requisito_versao where requisito_id = "+requisitoId;
            
            System.out.println(sql);
            
            st.executeUpdate(sql);
            
            sql = "delete from projeto_integrador_vii.requisito where id =" + requisitoId;
            
            System.out.println(sql);
            
            st.executeUpdate(sql);
            
            return true;
            
        }catch(Exception e){
            System.out.println("Erro ao excluir requisito versao: " + e);
            
            return false;
            
        }
        
    }
    
    public ArrayList<RequisitoVersaoModel> getListaByProjeto(int projetoId){
        
        ArrayList<RequisitoVersaoModel> lista = new ArrayList<RequisitoVersaoModel>();
        
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "select v.Id, r.codigo, r.titulo, v.versao, v.descricao from projeto_integrador_vii.requisito_versao as v " +
                        "inner join projeto_integrador_vii.requisito as r on v.requisito_id = r.id " +
                        "where v.ativo = 'S' and r.projeto_id = " + projetoId;
            
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
