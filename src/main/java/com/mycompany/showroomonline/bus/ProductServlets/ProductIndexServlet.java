package com.mycompany.showroomonline.bus.ProductServlets;

import com.mycompany.showroomonline.dao.ProductDAO;
import com.mycompany.showroomonline.dao.RoleDAO;
import com.mycompany.showroomonline.dao.UserDAO;
import com.mycompany.showroomonline.dto.Category;
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
            System.out.println("/n/nproduct/n/n");
            List<Product> listItem = productDAO.readAll();
            for (int i = 1; i <= listItem.size(); i++) {
                try {
                    Product product = productDAO.read(i);
                    if (product.getThumbnail().equals("")) {
                        int id = i;
                        String name = product.getName();
                        double price = product.getPrice();
                        int stock = product.getAmount();
                        String desc = product.getDescription();
                        Category cateid = product.getCategory();
                        String link = "resources/img/thumbnailtmp.png";
                        Product item = new Product(id, name, desc, price, stock, link, cateid);
                        productDAO.updateProduct(item);
                    }
                } catch (Exception e) {
                }
            }
            String admin = "Admin";
            String adminDesc = "- Can manage Products, Categories and Users details.<br>- Can view Roles details.";
            String staff = "Staff";
            String staffDesc = "- Can manage Products, Categories and ''Customer'' role Users details.<br>- Can view Users, Roles details.";
            String customer = "Customer";
            String customerDesc = "- Can view Products and Categories and Users details.";
            request.setCharacterEncoding("UTF-8");
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
            request.setCharacterEncoding("UTF-8");
            String username = "admin";
            String password = "admin";
            String email = "admin@showroomonline.com";
            String fullname = "Admin";
            String address = "Net";
            Role roleid = roleDAO.read(1);
            User user = userDAO.read(username);
            if (user == null) {
                request.setCharacterEncoding("UTF-8");
                User item = new User(username, password, fullname, email, address, roleid);
                userDAO.createUser(item);
            } else {
                if (!user.getUsername().equals(username) || !user.getPassword().equals(password) || !user.getEmail().equals(email)
                        || !user.getFullname().equals(fullname) || !user.getAddress().equals(address) || !user.getRole().equals(roleid)) {
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setEmail(email);
                    user.setFullname(fullname);
                    user.setAddress(address);
                    user.setRole(roleid);
                    userDAO.updateUser(user);
                }
            }
        } catch (Exception e) {
            Logger.getLogger(ProductIndexServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
