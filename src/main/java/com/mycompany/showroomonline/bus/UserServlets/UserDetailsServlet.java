package com.mycompany.showroomonline.bus.UserServlets;

import com.mycompany.showroomonline.dao.UserDAO;
import com.mycompany.showroomonline.dto.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/userdetails")
public class UserDetailsServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            User item = userDAO.read(username);
            request.setAttribute("model", item);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/userdetails.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            Logger.getLogger(UserDetailsServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}