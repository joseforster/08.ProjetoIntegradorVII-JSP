<%@page import="java.util.ArrayList"%>
<%@page import="com.sun.org.apache.bcel.internal.generic.AALOAD"%>
<%@page import="dao.UsuarioDAO"%>
<%@page import="model.UsuarioModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <title>Cadastro Usuário</title>
    </head>
    <body>        
        <%
            int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));
            
            UsuarioModel usuarioModel = new UsuarioDAO().getById(usuarioId);
            
            UsuarioModel model = (UsuarioModel) request.getAttribute("usuario");
            
            if(model == null){
                model = new UsuarioModel();
                model.setId(0);
                model.setUsername("");
                model.setAdministrador("");
            }else{
                model.setPassword("");
            }
            
        %>
        
            <div id="div-header" class="div-menu">
                <span class="span" id="span-name">Cadastro de usuário</span>
                <br/>
                <div class="botao-container">
                    <a href="menu.jsp?usuarioId=<%= usuarioId%>" class="botao" id="a-sair">Voltar</a>
                </div>
            </div>
                
            <div class="div-menu">
                
                <form method="post" action="/ProjetoIntegradorVII/crudUsuario?param=cadUsuario&userId=<%= usuarioModel.getId() %>">

                    <input type="hidden" name="id" value="<%= model.getId()%>"><br>

                    <input type="text" name="nome"  placeholder="Digite o nome..." value="<%= model.getUsername()%>" required=""><br>
                    <%
                        if(model.getId() == 0){
                    %>
                    <input type="password" name="senha"  placeholder="Digite o senha..." value=""><br>
                    <% 
                        }
                    %>
                    <label id="label-checkbox" for="checkbox-administrador">Administrador</label>
                    
                    <input id="checkbox-administrador" type="checkbox" name="administrador" value="adm" 
                    <% 
                        if(model.getAdministrador().equals("S"))
                        {
                            %>
                                checked
                            <%
                        }
                    %>>
                    
                    <br/>

                    <input type="submit" value="Salvar">

                    <input type="reset" value="Cancelar">
                </form>
                    
            </div>
                    
            <% 
                ArrayList<UsuarioModel> lista = new UsuarioDAO().getLista(); 
            %>

            <div class="div-menu">
                 
                <table>
                    <tr>
                        <th>Id</th>
                        <th>Username</th>
                        <th>Administrador</th>
                    </tr>
                    <% 
                        for(int i = 0; i < lista.size(); i++){
                    %>
                    <tr>
                        <td><%= lista.get(i).getId()%></td>
                        <td><%= lista.get(i).getUsername()%></td>
                        <td><%= lista.get(i).getAdministrador()%></td>
                        <td>
                            <a class="lista-item" href="/ProjetoIntegradorVII/crudUsuario?param=updateUsuario&id=<%= lista.get(i).getId()%>&userId=<%= usuarioModel.getId() %>">Editar</a>&nbsp;
                            <a class="lista-item" href="/ProjetoIntegradorVII/crudUsuario?param=deleteUsuario&id=<%= lista.get(i).getId()%>&userId=<%= usuarioModel.getId() %>">Excluir</a>
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
