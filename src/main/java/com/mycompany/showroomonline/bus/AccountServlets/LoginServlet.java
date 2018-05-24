package com.mycompany.showroomonline.bus.AccountServlets;

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
    String USERNAME_AND_PASSWORD_IS_EMPTY = "Please input username and password.";
    String WRONG_USERNAME_OR_PASSWORD = "Wrong username or password.";
    String BACK = "Click <a href='login'>here</a> to turn back.";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        resetError(session);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        session.setAttribute("BACK", BACK);
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        if ("".equals(username) && "".equals(password)) {
            session.setAttribute("ERROR1", USERNAME_AND_PASSWORD_IS_EMPTY);
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        } else {
            try {
                User user = userDAO.read(username);
                if (user.getPassword().equals(password)) {
                    int id = user.getRole().getId();
                    String role = user.getRole().getName();
                    session = request.getSession();
                    session.setAttribute("loggedName", username);
                    session.setAttribute("loggedRole", role);
                    session.setAttribute("loggedRoleId", id);
                    response.sendRedirect(request.getContextPath() + "/index");
                } else {
                    session.setAttribute("ERROR1", WRONG_USERNAME_OR_PASSWORD);
                    response.sendRedirect(request.getContextPath() + "/error.jsp");
                }
            } catch (NullPointerException e) {
                session.setAttribute("ERROR1", WRONG_USERNAME_OR_PASSWORD);
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            }
        }
    }

    protected void resetError(HttpSession session) {
        session.setAttribute("ERROR1", "");
        session.setAttribute("ERROR2", "");
        session.setAttribute("ERROR3", "");
        session.setAttribute("ERROR4", "");
    }
}
