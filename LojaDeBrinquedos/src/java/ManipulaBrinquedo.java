/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Conn.Database;
import Models.BrinquedoMOD;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fagner
 */
@WebServlet(urlPatterns = {"/Manipula Brinquedo"})
public class ManipulaBrinquedo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String titulo = "Adicionar Brinquedo";
        String n = "Adicionar novo brinquedo!";
        int id = 0;
        BrinquedoMOD b = new BrinquedoMOD();
        b.categoria = "";
        b.codigo = 0;
        b.descricao = "";
        b.detalhe = "";
        b.imagem = "";
        b.marca = "";
        b.preco = 0;

        try {
            id = Integer.parseInt(request.getParameter("brinquedo"));
            titulo = "Alterar Brinquedo";
            Database c = new Database();
            b.codigo = id;
            b = c.select(b);

            n = "Alterar brinquedo";
        } catch (Exception ex) {
            Logger.getLogger(ManipulaBrinquedo.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (PrintWriter out = response.getWriter()) {
            out.println("<address>Você esta em: <b><a href='Javascript:Void(0)'  onclick='pagina(\"Administração\")'>Administração</a></b> -> <b>" + titulo + "</a></b> </address>");
            out.println("<h3>" + n + "</h3>");

            if (id > 0) {
                out.println("<label for='descricao'>Código</label>");
                out.println("<input type='text' readonly='readonly'  disabled='disabled' class='form-control' value='" + b.codigo + "'/>");
                out.println("</br>");
            }

            out.println("<label for='descricao'>Descrição</label>");
            out.println("<input type='text' id='descricao' placeholder='Digite a descrição / titulo do brinquedo' class='form-control' value='" + b.descricao + "'/>");
            out.println("</br>");

            out.println("<label for='categoria'>Categoria</label>");
            out.println("<input type='text' id='categoria' placeholder='Digite o nome da categoria' value='" + b.categoria + "' class='form-control'/>");
            out.println("</br>");

            out.println("<label for='marca'>Marca</label>");
            out.println("<input type='text' id='marca' placeholder='Digite a marca' value='" + b.marca + "' class='form-control'/>");
            out.println("</br>");

            out.println("<label for='imagem'>Imagem URL:</label>");
            out.println("<input type='text' id='imagem' placeholder='Digite o url da imagem' value='" + b.imagem + "' class='form-control'/>");
            out.println("</br>");

            out.println("<label for='preco'>Preço:</label>");
            out.println("<div class=\"input-group\">\n"
                    + "      <div class=\"input-group-addon\">R$</div>\n"
                    + "      <input type=\"numeric\" onblur=\"formataDecimal(this)\" value='" + b.preco + "' onkeypress='return SomenteNumero(event)' class=\"form-control\" id=\"preco\" placeholder=\"Preço\" step=\"0.01\">\n"
                    + "</div>");
            out.println("</br>");

            out.println("<label for='detalhe'>Detalhes:</label>");
            out.println("<textarea id='detalhe' placeholder='Digite o detalhe do produto' class='form-control'>" + b.detalhe + "</textarea>");

            out.println("</br>");
            titulo = (b.codigo == 0) ? "Cadastrar Brinquedo" : "Alterar Brinquedo";
            out.println("<div class=\"input-group\"><a href=\"javascript:void(0)\" class=\"btn btn-" + request.getParameter("cor") + "\" onclick=\"manipulaBrinquedo('" + b.codigo + "')\">" + titulo + "</a>\n");

            out.close();
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

        int codigo = 0;
        try {
            codigo = Integer.parseInt(request.getParameter("codigo"));
        } catch (Exception ex) {
            Logger.getLogger(ManipulaBrinquedo.class.getName()).log(Level.SEVERE, null, ex);
        }
        int apaga = 0;
        try {
            apaga = Integer.parseInt(request.getParameter("apaga"));
        } catch (Exception ex) {
            Logger.getLogger(ManipulaBrinquedo.class.getName()).log(Level.SEVERE, null, ex);
        }

        BrinquedoMOD c = new BrinquedoMOD();

        String categoria = "";
        String descricao = "";
        String detalhe = "";
        String imagem = "";
        String marca = "";
        double preco = 0;

        try {
            categoria = request.getParameter("categoria");
            descricao = request.getParameter("descricao");
            detalhe = request.getParameter("detalhe");
            imagem = request.getParameter("imagem");
            marca = request.getParameter("marca");
            preco = Double.parseDouble(request.getParameter("preco"));
        } catch (Exception ex) {
            Logger.getLogger(ManipulaBrinquedo.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (PrintWriter out = response.getWriter()) {

            c.categoria = categoria;
            c.codigo = codigo;
            c.descricao = descricao;
            c.detalhe = detalhe;
            c.imagem = imagem;
            c.marca = marca;
            c.preco = preco;

            Database d = new Database();
            if (apaga > 0) {
                c.codigo = apaga;
                out.println("var resultado = " + d.delete(c));
            } else if (codigo == 0) {
                out.println("var resultado = " + d.insert(c));
            } else {
                out.println("var resultado = " + d.update(c));
            }
            out.close();
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

}
