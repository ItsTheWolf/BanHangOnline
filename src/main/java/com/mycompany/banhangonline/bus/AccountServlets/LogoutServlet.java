package com.mycompany.banhangonline.bus.AccountServlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);
        session.removeAttribute("loggedName");
        session.removeAttribute("loggedRole");
        session.removeAttribute("loggedRoleId");
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/productindex");
    }
}
