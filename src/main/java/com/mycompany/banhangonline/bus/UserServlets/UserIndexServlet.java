package com.mycompany.banhangonline.bus.UserServlets;

import com.mycompany.banhangonline.dao.UserDAO;
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

@WebServlet("/userindex")
public class UserIndexServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

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
}
