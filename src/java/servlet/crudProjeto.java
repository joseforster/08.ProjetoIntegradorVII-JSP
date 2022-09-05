/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.ProjetoDAO;
import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProjetoModel;
import model.UsuarioModel;

/**
 *
 * @author forster
 */
public class crudProjeto extends HttpServlet {

    HttpServletRequest requisicao;
    HttpServletResponse resposta;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet crudProjeto</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet crudProjeto at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        requisicao = request; 
        resposta = response;

        String parametro = request.getParameter("param");

        
        if (parametro.equals("create")) {
            
            String descricao = requisicao.getParameter("descricao");
            String usuarioSenha = requisicao.getParameter("usuarioSenha");
            String usuarioNome = requisicao.getParameter("usuarioNome");
            
            UsuarioModel usuarioModel = new UsuarioModel();
            usuarioModel.setUsername(usuarioNome);
            usuarioModel.setPassword(usuarioSenha);
            
            UsuarioModel usuarioBDModel = new UsuarioDAO().getUsuario(usuarioModel);
            
            ProjetoModel model = new ProjetoModel();
            model.setDescricao(descricao);
            model.setUsuario_criacao(usuarioBDModel);
            
            if (new ProjetoDAO().create(model)) {
                
                requisicao.setAttribute("usuario", usuarioBDModel);
                
                requisicao.setAttribute("success", "true");
                
                encaminharPagina("menu.jsp");
                
            } else {
                
                requisicao.setAttribute("usuario", usuarioModel);
                
                requisicao.setAttribute("success", "false");
                
                encaminharPagina("menu.jsp");
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private void encaminharPagina(String pagina) {
        try {
            
            RequestDispatcher rd = requisicao.getRequestDispatcher(pagina);
            
            rd.forward(requisicao, resposta);
            
        } catch (Exception e) {
            System.out.println("Erro no encaminhamento: " + e);
        }
    }

}
