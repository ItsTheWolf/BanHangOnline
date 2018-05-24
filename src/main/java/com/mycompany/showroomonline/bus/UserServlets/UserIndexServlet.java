package com.mycompany.showroomonline.bus.UserServlets;

import com.mycompany.showroomonline.dao.RoleDAO;
import com.mycompany.showroomonline.dao.UserDAO;
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

@WebServlet("/userindex")
public class UserIndexServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();
    private RoleDAO roleDAO = new RoleDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<User> listItem = userDAO.readAll();
            request.setAttribute("model", listItem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/userindex.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            Logger.getLogger(UserIndexServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    protected void addNecessaryValues(HttpServletRequest request) {
        try {
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
            Logger.getLogger(UserIndexServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
