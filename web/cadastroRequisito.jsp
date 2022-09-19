<%@page import="model.UsuarioModel"%>
<%@page import="dao.UsuarioDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.RequisitoModel"%>
<%@page import="dao.RequisitoVersaoDAO"%>
<%@page import="model.RequisitoVersaoModel"%>
<%@page import="dao.ProjetoDAO"%>
<%@page import="model.ProjetoModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <title>Cadastro Requisito</title>
    </head>
    <body>
         <%
             
            int requisitoVersaoId = Integer.parseInt(request.getParameter("requisitoVersaoId"));
            
            int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));
            
            UsuarioModel usuarioModel = new UsuarioDAO().getById(usuarioId);
            
            int projetoId = Integer.parseInt(request.getParameter("projetoId"));
            
            ProjetoModel projetoModel = new ProjetoDAO().getById(projetoId);
            
            RequisitoVersaoModel requisitoVersaoModel = new RequisitoVersaoModel();
            
            if(requisitoVersaoId > 0){
                
                requisitoVersaoModel = new RequisitoVersaoDAO().getById(requisitoVersaoId);
            }else{
                requisitoVersaoModel.setRequisito(new RequisitoModel("", "", null));
                requisitoVersaoModel.setDescricao("");
            
            }
            
            String isNovaVersao = (String) request.getAttribute("isNovaVersao");
        %>
        
        <div id="div-header" class="div-menu">
                <span class="span" id="span-name"><%= projetoModel.getDescricao() %></span>
                <br/>
                <div class="botao-container">
                    <a href="menu.jsp?usuarioId=<%= usuarioId%>" class="botao" id="a-sair">Voltar</a>
                </div>
        </div>
        
        <% if(usuarioModel.getAdministrador().equals("S"))
            {
        %>
        
        <div class="div-menu">
            
                <form method="post" action="/ProjetoIntegradorVII/crudRequisito?param=cadRequisito&usuarioId=<%= usuarioId %>" >

                    <input type="hidden" name="requisitoId" value="<%= requisitoVersaoModel.getRequisito().getId() %>"><br>
                    <input type="hidden" name="requisitoVersaoId" value="<%= requisitoVersaoModel.getId()%>"><br>
                    <input type="hidden" name="projetoId" value="<%= projetoId %>"><br>
                    
                    <%
                        if(isNovaVersao == null){
                    %>
                    <input type="text" name="codigo"  placeholder="Digite o código do requisito..." value="<%= requisitoVersaoModel.getRequisito().getCodigo() %>"><br>
                    
                    <input type="text" name="titulo"  placeholder="Digite o título do requisito..." value="<%= requisitoVersaoModel.getRequisito().getTitulo()%>" required=""><br>
                    
                    <% 
                        }
                    %>
                    
                    <input type="text" name="descricao" placeholder="Digite a descrição do requisito..." value="<%= requisitoVersaoModel.getDescricao() %>"> 
                    
                    <br/>

                    <input type="submit" value="Salvar">

                    <input type="reset" value="Cancelar">
                </form>
        </div>
                    
        <% 
            }
        %>
                    
        <% 
            ArrayList<RequisitoVersaoModel> lista = new RequisitoVersaoDAO().getListaByProjeto(projetoId);
        %>
        
            <div class="div-menu">
                
                <button id="btn-historico-versoes"><a href="/ProjetoIntegradorVII/crudRequisito?param=gerarRelatorio&usuarioId=<%= usuarioId %>&projetoId=<%= projetoId %>&requisitoVersaoId=0">Relatório - Histórico de versões</a></button>
            
                <table>
                    <tr>
                        <th>Código</th>
                        <th>Versão</th>
                        <th>Requisito</th>
                        <th>Descricão</th>
                        <th></th>
                    </tr>
                    <% 
                        for(int i = 0; i < lista.size(); i++){
                    %>
                    <tr>
                        <td><%= lista.get(i).getRequisito().getCodigo() %></td>
                        <td><%= lista.get(i).getVersao() %></td>
                        <td><%= lista.get(i).getRequisito().getTitulo() %></td>
                        <td><%= lista.get(i).getDescricao()%></td>
                        <td>
                        <% if(usuarioModel.getAdministrador().equals("S"))
                            {
                        %>
                            <button><a href="/ProjetoIntegradorVII/crudRequisito?param=updateRequisito&requisitoVersaoId=<%= lista.get(i).getId()%>&usuarioId=<%= usuarioId %>&projetoId=<%= projetoId %>">Editar</a></button>&nbsp;
                            <button><a href="/ProjetoIntegradorVII/crudRequisito?param=novaVersaoRequisito&requisitoVersaoId=<%= lista.get(i).getId()%>&usuarioId=<%= usuarioId %>&projetoId=<%= projetoId %>">Nova Versão</a></button>&nbsp;
                            <button><a href="/ProjetoIntegradorVII/crudRequisito?param=destroyRequisito&requisitoVersaoId=<%= lista.get(i).getId()%>&usuarioId=<%= usuarioId %>&projetoId=<%= projetoId %>">Excluir</a></button>
                        <% 
                            }
                        %>
                        </td>
                    </tr>
                    <% 
                        }
                    %>
                    
                    
                </table>

            </div>
                    
            
        <%
            String success = (String) request.getAttribute("success");
            
            if(success == "true"){
        %>
            <script type="text/javascript">alert("Operação realizada com sucesso.");</script>
        <%
            }if(success == "false"){
        %>
            <script type="text/javascript">alert("Erro ao realizar operação.");</script>
        <%
            }
        %>
        
    </body>
</html>
