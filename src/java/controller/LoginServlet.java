package controller;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    Connection con;
    ResultSet rs;
    PreparedStatement ps;
    int errorctr = 3;

    @Override
    public void init(ServletConfig config) throws ServletException { //initializes Database connection with Web.xml parameters
        try {
            Class.forName(config.getInitParameter("jdbcClassName"));
            String username = config.getInitParameter("dbUserName");
            String password = config.getInitParameter("dbPassword");
            StringBuffer url = new StringBuffer(config.getInitParameter("jdbcDriverURL"))
                    .append("://")
                    .append(config.getInitParameter("dbHostName"))
                    .append(":")
                    .append(config.getInitParameter("dbPort"))
                    .append("/")
                    .append(config.getInitParameter("databaseName"));

            con = DriverManager.getConnection(url.toString(), username, password);

        } catch (SQLException sqle) {
            System.out.println("SQLException error occured - "
                    + sqle.getMessage());
        } catch (ClassNotFoundException nfe) {
            System.out.println("ClassNotFoundException error occured - "
                    + nfe.getMessage());
        } catch (NullPointerException ne) {
            System.out.println("NullPointerException error occured - "
                    + ne.getMessage());
        }

    }

    public boolean checkCorrect(HttpServletRequest request) throws SQLException { //checks from database for request parameter username and password then returns boolean
        String query = "SELECT * FROM covidtracker WHERE Username = ? AND Password = ?";
        ps = con.prepareStatement(query);
        ps.setString(1, request.getParameter("username"));
        ps.setString(2, request.getParameter("password"));
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean isCorrect = false;
        try {
            if (con != null) {
                isCorrect = checkCorrect(request);

                if (isCorrect != true) {
                    response.sendRedirect("error500.jsp");
                }

                HttpSession session = request.getSession(); // creates a new session if one doesn't exist
                ps = con.prepareStatement("SELECT * FROM covidtracker ORDER BY Name");
                rs = ps.executeQuery();
                session.setAttribute("tablerecords", rs); 
                ps = con.prepareStatement("SELECT * FROM covidtracker WHERE Username = ?");
                ps.setString(1, request.getParameter("username"));
                rs = ps.executeQuery();
                request.setAttribute("records", rs);
                session.setAttribute("record", rs); // store relevant user information in the session
                response.sendRedirect("profile.jsp"); //Proceeds to profile page for successful logins
            } else {
                response.sendRedirect("error500.jsp");
            }

        } catch (SQLException | IllegalStateException sqle) {
            sqle.printStackTrace();
            response.sendRedirect("error500.jsp");
        }

    }

    @Override
    public void destroy() {
        try {
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
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
