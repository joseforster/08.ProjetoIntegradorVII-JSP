package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import dao.UsuarioDAO;
import model.UsuarioModel;

public final class cadastroUsuario_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"style.css\">\n");
      out.write("        <title>Cadastro Usuário</title>\n");
      out.write("    </head>\n");
      out.write("    <body>        \n");
      out.write("        ");

            int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));
            
            UsuarioModel usuarioModel = new UsuarioDAO().getById(usuarioId);
            
            UsuarioModel model = (UsuarioModel) request.getAttribute("usuario");
            
            if(model == null){
                model = new UsuarioModel();
                model.setId(0);
                model.setUsername("");
                model.setPassword("");
                model.setAdministrador("");
            }
            
        
      out.write("\n");
      out.write("        \n");
      out.write("            <div id=\"div-header\" class=\"div-menu\">\n");
      out.write("                <span class=\"span\" id=\"span-username\">Cadastro de usuário</span>\n");
      out.write("                <br/>\n");
      out.write("                <div class=\"botao-container\">\n");
      out.write("                    <a href=\"menu.jsp?usuarioId=");
      out.print( usuarioId);
      out.write("\" class=\"botao\" id=\"a-sair\">Sair</a>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("                \n");
      out.write("            <div class=\"div-menu\">\n");
      out.write("                \n");
      out.write("                <form method=\"post\" action=\"/ProjetoIntegradorVII/crudUsuario?param=cadUsuario&userId=");
      out.print( usuarioModel.getId() );
      out.write("\">\n");
      out.write("\n");
      out.write("                    <input type=\"hidden\" name=\"id\" value=\"");
      out.print( model.getId());
      out.write("\"><br>\n");
      out.write("\n");
      out.write("                    <input type=\"text\" name=\"nome\"  placeholder=\"Digite o nome...\" value=\"");
      out.print( model.getUsername());
      out.write("\" required=\"\"><br>\n");
      out.write("                    \n");
      out.write("                    <input type=\"password\" name=\"senha\"  placeholder=\"Digite o senha...\" value=\"");
      out.print( model.getPassword());
      out.write("\" required=\"\"><br>\n");
      out.write("                    \n");
      out.write("                    <label id=\"label-checkbox\" for=\"checkbox-administrador\">Administrador</label>\n");
      out.write("                    \n");
      out.write("                    <input id=\"checkbox-administrador\" type=\"checkbox\" name=\"administrador\" value=\"adm\" \n");
      out.write("                    ");
 
                        if(model.getAdministradorChecked() == "checked")
                        {
                            
      out.write("\n");
      out.write("                                checked\n");
      out.write("                            ");

                        }
                    
      out.write(">\n");
      out.write("                    \n");
      out.write("                    <br/>\n");
      out.write("\n");
      out.write("                    <input type=\"submit\" value=\"Salvar\">\n");
      out.write("\n");
      out.write("                    <input type=\"reset\" value=\"Cancelar\">\n");
      out.write("                </form>\n");
      out.write("                    \n");
      out.write("            </div>\n");
      out.write("                    \n");
      out.write("            ");
 
                ArrayList<UsuarioModel> lista = new UsuarioDAO().getListaUsuario(); 
            
      out.write("\n");
      out.write("\n");
      out.write("            <div class=\"div-menu\">\n");
      out.write("                <table>\n");
      out.write("                    <tr>\n");
      out.write("                        <th>Id</th>\n");
      out.write("                        <th>Username</th>\n");
      out.write("                        <th>Administrador</th>\n");
      out.write("                    </tr>\n");
      out.write("                    ");
 
                        for(int i = 0; i < lista.size(); i++){
                    
      out.write("\n");
      out.write("                    <tr>\n");
      out.write("                        <td>");
      out.print( lista.get(i).getId());
      out.write("</td>\n");
      out.write("                        <td>");
      out.print( lista.get(i).getUsername());
      out.write("</td>\n");
      out.write("                        <td>");
      out.print( lista.get(i).getAdministrador());
      out.write("</td>\n");
      out.write("                        <td>\n");
      out.write("                            <a class=\"lista-item\" href=\"/ProjetoIntegradorVII/crudUsuario?param=updateUsuario&id=");
      out.print( lista.get(i).getId());
      out.write("&userId=");
      out.print( usuarioModel.getId() );
      out.write("\">Editar</a>&nbsp;\n");
      out.write("                            <a class=\"lista-item\" href=\"/ProjetoIntegradorVII/crudUsuario?param=deleteUsuario&id=");
      out.print( lista.get(i).getId());
      out.write("&userId=");
      out.print( usuarioModel.getId() );
      out.write("\">Excluir</a>\n");
      out.write("                        </td>\n");
      out.write("                    </tr>\n");
      out.write("                    ");
 
                        }
                    
      out.write("\n");
      out.write("                    \n");
      out.write("                    \n");
      out.write("                </table>\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("        ");

            String success = (String) request.getAttribute("success");
            
            if(success == "true"){
        
      out.write("\n");
      out.write("            <script type=\"text/javascript\">alert(\"Operação realizada com sucesso.\");</script>\n");
      out.write("        ");

            }if(success == "false"){
        
      out.write("\n");
      out.write("            <script type=\"text/javascript\">alert(\"Erro ao realizar operação.\");</script>\n");
      out.write("        ");

            }
        
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
