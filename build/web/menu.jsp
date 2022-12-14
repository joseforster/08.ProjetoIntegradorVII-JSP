<%@page import="dao.UsuarioDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.ProjetoModel"%>
<%@page import="dao.ProjetoDAO"%>
<%@page import="model.UsuarioModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <%
            String usuarioId = request.getParameter("usuarioId");
            
            UsuarioModel model = new UsuarioModel();
            
            if(usuarioId == null){
                
                model = (UsuarioModel) request.getAttribute("usuario");
                
            }else{
            
                int id = Integer.parseInt(usuarioId);
                
                model = new UsuarioDAO().getById(id);
            }
            
        %>
        <div id="div-header" class="div-menu">
            <span class="span" id="span-name">Olá, <%= model.getUsername()%> </span>

            <div class="botao-container">
                <%
                    if (model.getAdministrador().equals("S")) 
                    {
                %>
                <a href="cadastroUsuario.jsp?usuarioId=<%= model.getId()%>" class="botao">Adicionar usuário</a>
                <%
                    }
                %>
                <br/>
                <a href="index.jsp" class="botao" id="a-sair">Sair</a>
            </div>
        </div>

        <div id="div-table" class="div-menu">
            
            <% if(model.getAdministrador().equals("S"))
                {
            %>
            <form method="post" action="/ProjetoIntegradorVII/crudProjeto?param=create">
                    <input type="text" name="descricao" placeholder="Digite o nome do projeto..." maxlength="100" required>
                    <input type="hidden" name="usuarioId" value="<%= model.getId()%>">
                    <input type="submit" value="Adicionar novo projeto">
                    <input type="reset" value="Cancelar">
            </form>
            <%
                }
            %>

            <table>
                <tr>
                    <th class="invisible">Id</th>
                    <th>Descrição</th>
                    <th>Usuário Criação</th>
                    <th></th>
                    <th></th>
                </tr>
                
            <%
                ArrayList<ProjetoModel> lista = new ProjetoDAO().getLista();
                
                for(int i = 0; i < lista.size(); i++){
            %>
                <tr>
                    <td class="invisible">1</td>
                    <td><%= lista.get(i).getDescricao() %></td>
                    <td><%= lista.get(i).getUsuario_criacao().getUsername() %></td>
                    <td>
                        <button><a href="/ProjetoIntegradorVII/crudProjeto?param=visualizar&projetoId=<%= lista.get(i).getId() %>&usuarioId=<%= model.getId() %>&requisitoVersaoId=0">Visualizar</a></button>
                        <button><a href="/ProjetoIntegradorVII/crudProjeto?param=gerarRelatorio&projetoId=<%= lista.get(i).getId() %>&usuarioId=<%= model.getId() %>">Gerar relatório</a></button>
                    </td>
                    <td>
                        <% if(model.getAdministrador().equals("S"))
                            {
                        %>
                        <button><a href="/ProjetoIntegradorVII/crudProjeto?param=destroy&id=<%= lista.get(i).getId() %>&usuarioId=<%= model.getId() %>">Excluir</a></button>
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