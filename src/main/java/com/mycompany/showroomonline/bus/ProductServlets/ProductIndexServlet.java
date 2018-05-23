package com.mycompany.showroomonline.bus.ProductServlets;

import com.mycompany.showroomonline.dao.ProductDAO;
import com.mycompany.showroomonline.dao.RoleDAO;
import com.mycompany.showroomonline.dao.UserDAO;
import com.mycompany.showroomonline.dto.Product;
import com.mycompany.showroomonline.dto.Role;
import com.mycompany.showroomonline.dto.User;
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

@WebServlet("/index")
public class ProductIndexServlet extends HttpServlet {

    private ProductDAO productDAO = new ProductDAO();
    private RoleDAO roleDAO = new RoleDAO();
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            addNecessaryValues(request);
            List<Product> listItem = productDAO.readAll();
            request.setAttribute("model", listItem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            Logger.getLogger(ProductIndexServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    protected void addNecessaryValues(HttpServletRequest request) {
        try {
            for (int i = 1; i <= 3; i++) {
                Role role = roleDAO.read(i);
                if (role == null) {
                    request.setCharacterEncoding("UTF-8");
                    int roleId = 0;
                    String roleName = null, roleDesc = null;
                    if (i == 1) {
                        roleName = "Admin";
                        roleDesc = "- Can manage Products, Categories and Users details.";
                    }
                    if (i == 2) {
                        roleName = "Staff";
                        roleDesc = "- Can view Users, Roles details.<br>- Can manage Products, Categories and ''Customer'' role Users details.";
                    }
                    if (i == 3) {
                        roleName = "Customer";
                        roleDesc = "- Can view Products and Categories details.";
                    }
                    if (roleId != 0 || roleName != null || roleDesc != null) {
                        roleId = i;
                        roleDAO.createRole(roleId, roleName, roleDesc);
                    }
                }
            }
            User user = userDAO.read("admin");
            if (user == null) {
                request.setCharacterEncoding("UTF-8");
                String username = "admin";
                String password = "admin";
                String email = "admin@showroomonline.com";
                String fullname = "Admin";
                String address = "Net";
                Role roleid = roleDAO.read(1);
                User item = new User(username, password, fullname, email, address, roleid);
                userDAO.createUser(item);
            }
        } catch (Exception e) {
            Logger.getLogger(ProductIndexServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
