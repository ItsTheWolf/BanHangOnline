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
            for (int i = 1; i <= 3; i++) {
                Role role = roleDAO.read(i);
                if (role == null) {
                    request.setCharacterEncoding("UTF-8");
                    String roleName = null, roleDesc = null;
                    if (i == 1) {
                        roleName = "Admin";
                        roleDesc = "- Can manage Products, Categories and Users details.";
                    }
                    if (i == 2) {
                        roleName = "Staff";
                        roleDesc = "- Can view Users, Roles details.<br>- Can manage Products, Categories and 'Customer' role Users details.";
                    }
                    if (i == 3) {
                        roleName = "Customer";
                        roleDesc = "- Can view Products and Categories details.";
                    }
                    if (roleName != null || roleDesc != null) {
                        Role item = new Role(roleName, roleDesc);
                        roleDAO.createRole(item);
                    }
                }
            }
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
