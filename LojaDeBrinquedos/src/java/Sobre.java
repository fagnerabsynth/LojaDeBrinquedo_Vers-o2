/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Models.AlunosMOD;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

/**
 *
 * @author Fagner
 */
@WebServlet(urlPatterns = {"/Sobre"})
public class Sobre extends HttpServlet {

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

        ArrayList<AlunosMOD> alunos = new ArrayList<>();
        AlunosMOD x = new AlunosMOD();
        x.rgm = 14026201;
        x.nome = "Ricardo Fagner Castelo Branco";
        x.foto = "fagner.jpg";
        alunos.add(x);

        x = new AlunosMOD();
        x.rgm = 15738558;
        x.nome = "Leandro Silva de Oliveira";
        x.foto = "leandro.jpg";
        alunos.add(x);

        x = new AlunosMOD();
        x.rgm = 14037114;
        x.nome = "Gabriel Pires da Silva";
        x.foto = "gabriel.jpg";
        alunos.add(x);

        x = new AlunosMOD();
        x.rgm = 14022133;
        x.nome = "Renan Ribeiro Pereira";
        x.foto = "renan.jpg";
        alunos.add(x);

        x = new AlunosMOD();
        x.rgm = 14020670;
        x.nome = "Kelwin Miranda";
        x.foto = "kelwin.jpg";
        alunos.add(x);
        
        x = new AlunosMOD();
        x.rgm = 14019302;
        x.nome = "Eduardo de Souza Santos";
        x.foto = "eduardo.jpg";
        alunos.add(x);

        try (PrintWriter out = response.getWriter()) {
            out.println("<address>Você esta em: <b><a href='Javascript:Void(0)'  onclick='pagina(\"" + request.getServletPath().replace('/', ' ') + "\")'>" + request.getServletPath().replace('/', ' ') + "</a></b> </address>");
            Collections.shuffle(alunos);
            int con = 0;
            for (AlunosMOD aluno : alunos) {
                if (con==0){
                out.println("<div class=\"row\">");    
                }
                con++;
                out.println("<div class=\"col-sm-4 col-sm-offset-1\" style=\"box-shadow: 2px 2px 2px #d1d1d1;border-radius:5px;padding:10px;\" >"
                        + "<center>");
                out.println("<img class='img-reponsive img-circle' width='200' height='200' src=\"imagens/" + aluno.foto + "\">");
                out.println("<h3 style=\"color:red\">" + aluno.nome + "</h3>");
                out.println("RGM: " + aluno.rgm);
                out.println("</center>"
                        + "</div>");

                if (con==2) {
                    out.println("</div>");
                    con = 0;
                }
            }

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
        processRequest(request, response);
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
