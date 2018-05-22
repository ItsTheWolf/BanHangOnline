package com.mycompany.banhangonline.bus.RoleServlets;

import com.mycompany.banhangonline.dao.RoleDAO;
import com.mycompany.banhangonline.dto.Role;
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
            if (listItem == null || listItem.isEmpty()) {
                request.setCharacterEncoding("UTF-8");
                String admin = "Admin";
                String adminDesc = "- Can manage Products, Categories and Users details.";
                String staff = "Staff";
                String staffDesc = "- Can view Users, Roles details.<br>- Can manage Products, Categories and 'Customer' role Users details.";
                String customer = "Customer";
                String customerDesc = "- Can view Products and Categories details.";
                Role item1 = new Role(admin, adminDesc);
                Role item2 = new Role(staff, staffDesc);
                Role item3 = new Role(customer, customerDesc);
                roleDAO.resetAI();
                roleDAO.createRole(item1);
                roleDAO.createRole(item2);
                roleDAO.createRole(item3);
                response.sendRedirect(request.getContextPath() + "/roleindex");
            } else {
                request.setAttribute("model", listItem);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/roleindex.jsp");
                rd.forward(request, response);
            }
        } catch (IOException | ServletException e) {
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