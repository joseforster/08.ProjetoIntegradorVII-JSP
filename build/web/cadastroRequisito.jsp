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
            int usuarioId = Integer.parseInt(request.getParameter("userId"));
            
            int projetoId = Integer.parseInt(request.getParameter("id"));
            
            ProjetoModel projetoModel = new ProjetoDAO().getById(projetoId);
            
            String idRequisito = request.getParameter("requisitoId");
            
            RequisitoVersaoModel requisitoVersaoModel = new RequisitoVersaoModel();
            requisitoVersaoModel.setRequisito(new RequisitoModel("", "", null));
            requisitoVersaoModel.setDescricao("");
            
            if(idRequisito != null){
                
                requisitoVersaoModel = new RequisitoVersaoDAO().getById(Integer.parseInt(idRequisito));
            }
            
            String isNovaVersao = request.getParameter("isNovaVersao");
        %>
        
        <div id="div-header" class="div-menu">
                <span class="span" id="span-name"><%= projetoModel.getDescricao() %></span>
                <br/>
                <div class="botao-container">
                    <a href="menu.jsp?usuarioId=<%= usuarioId%>" class="botao" id="a-sair">Voltar</a>
                </div>
        </div>
        
        <div class="div-menu">
            
                <form method="post" action="/ProjetoIntegradorVII/crudRequisito?param=cadRequisito&userId=<%= usuarioId %>" >

                    <input type="hidden" name="idRequisito" value="<%= requisitoVersaoModel.getRequisito().getId() %>"><br>
                    <input type="hidden" name="idRequisitoVersao" value="<%= requisitoVersaoModel.getId()%>"><br>
                    
                    <%
                        if(isNovaVersao == null){
                    %>
                    
                    <input type="text" name="titulo"  placeholder="Digite o titulo..." value="<%= requisitoVersaoModel.getRequisito().getTitulo()%>" required=""><br>
                    
                    <input type="text" name="codigo"  placeholder="Digite o codigo..." value="<%= requisitoVersaoModel.getRequisito().getCodigo() %>"><br>
                    <% 
                        }
                    %>
                    
                    <input type="text" name="descricao" placeholder="Digite a descrição..." value="<%= requisitoVersaoModel.getDescricao() %>"> 
                    
                    <br/>

                    <input type="submit" value="Salvar">

                    <input type="reset" value="Cancelar">
                </form>
        </div>
                    
        <% 
            ArrayList<RequisitoVersaoModel> lista = new RequisitoVersaoDAO().getListaByProjeto(projetoId);
        %>
        
            <div class="div-menu">
                 
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
                            <a class="lista-item" href="/ProjetoIntegradorVII/crudRequisito?param=updateRequisito&id=<%= lista.get(i).getId()%>&userId=<%= usuarioId %>">Editar</a>&nbsp;
                            <a class="lista-item" href="/ProjetoIntegradorVII/crudRequisito?param=novaVersaoRequisito&id=<%= lista.get(i).getId()%>&userId=<%= usuarioId %>">Nova Versão</a>&nbsp;
                            <a class="lista-item" href="/ProjetoIntegradorVII/crudRequisito?param=destroyRequisito&id=<%= lista.get(i).getId()%>&userId=<%= usuarioId %>">Excluir</a>
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
