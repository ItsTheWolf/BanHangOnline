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
            addNecessaryValues(request);
            List<Role> listItem = roleDAO.readAll();
            request.setAttribute("model", listItem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/roleindex.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            Logger.getLogger(RoleIndexServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    protected void addNecessaryValues(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
            String admin = "Admin";
            String adminDesc = "- Can manage Products, Categories and Users details.<br>- Can view Roles details.";
            String staff = "Staff";
            String staffDesc = "- Can manage Products, Categories and ''Customer'' role Users details.<br>- Can view Users, Roles details.";
            String customer = "Customer";
            String customerDesc = "- Can view Products, Categories and Users details.";
            for (int i = 1; i <= 3; i++) {
                Role role = roleDAO.read(i);
                int roleId = 0;
                String roleName = null, roleDesc = null;
                if (role == null) {
                    if (i == 1) {
                        roleName = admin;
                        roleDesc = adminDesc;
                    }
                    if (i == 2) {
                        roleName = staff;
                        roleDesc = staffDesc;
                    }
                    if (i == 3) {
                        roleName = customer;
                        roleDesc = customerDesc;
                    }
                    if (roleId != 0 || roleName != null || roleDesc != null) {
                        roleId = i;
                        roleDAO.createRole(roleId, roleName, roleDesc);
                    }
                } else {
                    if (i == 1 && (!role.getName().equals(admin)
                            || !role.getDescription().equals(adminDesc))) {
                        roleName = admin;
                        roleDesc = adminDesc;
                    }
                    if (i == 2 && (!role.getName().equals(staff)
                            || !role.getDescription().equals(staffDesc))) {
                        roleName = staff;
                        roleDesc = staffDesc;
                    }
                    if (i == 3 && (!role.getName().equals(customer)
                            || !role.getDescription().equals(customerDesc))) {
                        roleName = customer;
                        roleDesc = customerDesc;
                    }
                    if (roleId != 0 || roleName != null || roleDesc != null) {
                        roleId = i;
                        role.setId(roleId);
                        role.setName(roleName);
                        role.setDescription(roleDesc);
                        roleDAO.updateRole(role);
                    }
                }
            }
        } catch (Exception e) {
            Logger.getLogger(RoleIndexServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}