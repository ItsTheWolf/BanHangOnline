package com.mycompany.showroomonline.bus.UserServlets;

import com.mycompany.showroomonline.dao.UserDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/userdelete")
public class UserDeleteServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String username = request.getParameter("username");
            HttpSession session = request.getSession();
            if (!username.equals("admin")) {
                userDAO.deleteUser(username);
                if (username.equals(session.getAttribute("loggedName"))) {
                    session.removeAttribute("loggedName");
                    session.removeAttribute("loggedRole");
                    session.removeAttribute("loggedRoleId");
                    session.invalidate();
                    response.sendRedirect(request.getContextPath() + "/productindex");
                }
            }
            response.sendRedirect("userindex");
        } catch (Exception e) {
            Logger.getLogger(UserDeleteServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
