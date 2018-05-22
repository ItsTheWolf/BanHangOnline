package com.mycompany.showroomonline.bus.RoleServlets;

import com.mycompany.showroomonline.dao.RoleDAO;
import com.mycompany.showroomonline.dto.Role;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/roleindex")
public class RoleIndexServlet extends HttpServlet {

    private RoleDAO roleDAO = new RoleDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Role> listItem = roleDAO.readAll();
            request.setAttribute("model", listItem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/roleindex.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            Logger.getLogger(RoleIndexServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        } catch (Exception e) {
            Logger.getLogger(RoleIndexServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
