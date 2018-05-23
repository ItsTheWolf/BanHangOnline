package com.mycompany.showroomonline.bus.AccountServlets;

import com.mycompany.showroomonline.dao.RoleDAO;
import com.mycompany.showroomonline.dao.UserDAO;
import com.mycompany.showroomonline.dto.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        if ("".equals(username) && "".equals(password)) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            try {
                User user = userDAO.read(username);
                if (user.getPassword().equals(password)) {
                    int id = user.getRole().getId();
                    String role = user.getRole().getName();
                    HttpSession session = request.getSession();
                    session.setAttribute("loggedName", username);
                    session.setAttribute("loggedRole", role);
                    session.setAttribute("loggedRoleId", id);
                    response.sendRedirect(request.getContextPath() + "/index");
                } else {
                    response.sendRedirect(request.getContextPath() + "/login");
                }
            } catch (NullPointerException e) {
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }
    }
}
