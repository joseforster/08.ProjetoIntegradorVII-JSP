/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UsuarioModel;

/**
 *
 * @author forster
 */
public class crudUsuario extends HttpServlet {

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
            out.println("<title>Servlet crudUsuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet crudUsuario at " + request.getContextPath() + "</h1>");
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
        requisicao = request;
        resposta = response; 
        
                
        String parametro = request.getParameter("param");
        
        int userId = Integer.parseInt(request.getParameter("userId"));
        
        
        if (parametro.equals("updateUsuario")) {
            
            int id = Integer.parseInt(requisicao.getParameter("id"));
            
            UsuarioModel model = new UsuarioDAO().getById(id);
            
            requisicao.setAttribute("usuario", model);
            
            encaminharPagina("cadastroUsuario.jsp?usuarioId="+userId);
        
        
        } else if (parametro.equals("deleteUsuario")) {
            
           int id = Integer.parseInt(request.getParameter("id"));

           if (new UsuarioDAO().destroy(id)) {
               
               requisicao.setAttribute("success", "true");
               
           } else {
               requisicao.setAttribute("success", "false");
           }
           
           this.encaminharPagina("cadastroUsuario.jsp?usuarioId="+userId);
                   
        }
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
        
        int userId = Integer.parseInt(request.getParameter("userId"));
        
        if (parametro.equals("cadUsuario")) {
            
            int id = Integer.parseInt(requisicao.getParameter("id"));
            String username = requisicao.getParameter("nome");
            String administrador = requisicao.getParameter("administrador");
            String senha = requisicao.getParameter("senha");
             
            UsuarioModel model = new UsuarioModel();
            model.setId(id);
            model.setUsername(username);
            model.setPassword(senha);
            
            if(administrador == null){
                model.setAdministrador("N");
            }else{
                model.setAdministrador("S");
            }
            
            if (new UsuarioDAO().save(model)){
               
               requisicao.setAttribute("success", "true");
               
           } else {
               requisicao.setAttribute("success", "false");
           }
           
           this.encaminharPagina("cadastroUsuario.jsp?usuarioId="+userId);
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
