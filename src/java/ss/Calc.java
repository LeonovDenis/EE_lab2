/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author denleo
 */
@WebServlet(name = "Calc", urlPatterns = {"/Calc"})
public class Calc extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Calc</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Calc at " + request.getContextPath() + "</h1>");
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
        String x = request.getParameter("b2");
        if (x != null) {
            response.sendRedirect("index.jsp");
            return;
        }
        String n1 = request.getParameter("n1");
        String n2 = request.getParameter("n2");
        // Проверка является ли введённая строка "пустой"
        String result = "";
        if (isNumeric(n1.trim()) && isNumeric(n2.trim())) {

            result = Integer.toString(Integer.parseInt(n1) + Integer.parseInt(n2));

        } else {
            result = "Not number";
        }
        send(request, response, result);
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

        String x = request.getParameter("b2");
        if (x != null) {
            response.sendRedirect("index.jsp");
            return;
        }

        String k1 = request.getParameter("k1");
        String k2 = request.getParameter("k2");

        // Проверка является ли введённая строка "пустой"
        String result = "";
        if (isNumeric(k1.trim()) && isNumeric(k2.trim())) {

            result = Integer.toString(Integer.parseInt(k1) * Integer.parseInt(k2));

        } else {
            result = "Not number";
        }
        send(request, response, result);
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

    private void send(HttpServletRequest req, HttpServletResponse res, String msg) {
        req.setAttribute("result", msg);
        try {
            req.getRequestDispatcher("index.jsp").forward(req, res);
        } catch (Exception ex) {
            Logger.getLogger(Calc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

}
