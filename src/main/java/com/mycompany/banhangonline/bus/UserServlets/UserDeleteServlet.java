package com.mycompany.banhangonline.bus.UserServlets;

import com.mycompany.banhangonline.dao.UserDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/userdelete")
public class UserDeleteServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            userDAO.deleteUser(username);
            response.sendRedirect("userindex");
        } catch (Exception e) {
            Logger.getLogger(UserDeleteServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
