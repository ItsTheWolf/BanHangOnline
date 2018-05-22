package com.mycompany.banhangonline.bus.AccountServlets;

import com.mycompany.banhangonline.dao.UserDAO;
import com.mycompany.banhangonline.dto.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private UserDAO objDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        if ("".equals(username) && "".equals(password)) {
            out.print("Please enter both username and password. <br / ><br />");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            rd.include(request, response);
        } else {
            try {
                User user = objDAO.read(username);
                if (user.getPassword().equals(password)) {
                    out.println("Logged in successfully. <br />");
                    HttpSession loginSession = request.getSession();
                    loginSession.setMaxInactiveInterval(60 * 5);
                    loginSession.setAttribute("loggedName", username);
                    response.sendRedirect(request.getContextPath() + "/home");
                } else {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/error-login.jsp");
                    rd.forward(request, response);
                }
            } catch (NullPointerException e) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/error-login.jsp");
                rd.forward(request, response);
            }
        }
    }
}
