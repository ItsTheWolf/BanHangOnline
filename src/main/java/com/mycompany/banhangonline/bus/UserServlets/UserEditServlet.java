package com.mycompany.banhangonline.bus.UserServlets;

import com.mycompany.banhangonline.dao.RoleDAO;
import com.mycompany.banhangonline.dao.UserDAO;
import com.mycompany.banhangonline.dto.Role;
import com.mycompany.banhangonline.dto.User;
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

@WebServlet("/useredit")
public class UserEditServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();
    private RoleDAO roleDAO = new RoleDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            User item = userDAO.read(username);
            request.setAttribute("model", item);
            List<Role> listItem = roleDAO.readAll();
            request.setAttribute("listItem", listItem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/useredit.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            Logger.getLogger(UserEditServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String fullname = request.getParameter("txtFullname");
            String email = request.getParameter("txtEmail");
            String address = request.getParameter("txtAddress");
            Role roleid = roleDAO.read(Integer.parseInt(request.getParameter("txtRoleId")));
            User item = new User(fullname, email, address, roleid);
            userDAO.updateUser(item);
            response.sendRedirect(request.getContextPath() + "/userindex");
        } catch (Exception e) {
            Logger.getLogger(UserEditServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
