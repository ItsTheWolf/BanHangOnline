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
import javax.servlet.http.HttpSession;

@WebServlet("/usereditpw")
public class UserEditPWServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();
    HttpServletRequest request;
    String username = request.getParameter("username");
    String REQUIRED_FIELDS_BLANK = "Please fill in the required (*) fields.";
    String PASSWORD_CPW_NOT_MATHCED = "Password and Confirm Password do not match.";
    String BACK = "Click <a href='/usereditpw?username=" + username + "'>here</a> to turn back.";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String username = request.getParameter("username");
            HttpSession session = request.getSession();
            resetError(session);
            if (!username.equals("admin")) {
                User item = userDAO.read(username);
                request.setAttribute("model", item);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/usereditpw.jsp");
                rd.forward(request, response);
            } else {
                response.sendRedirect("userindex");
            }
        } catch (Exception e) {
            Logger.getLogger(UserEditServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            HttpSession session = request.getSession();
            session.setAttribute("BACK", BACK);
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String cpw = request.getParameter("txtCPassword");
            boolean error = validation(password, cpw, session);
            if (error) {
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            } else {
                User item = new User(username, password);
                userDAO.updateUser(item);
                response.sendRedirect(request.getContextPath() + "/userindex");
            }
        } catch (Exception e) {
            Logger.getLogger(UserEditServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    protected boolean validation(String pw, String cpw, HttpSession session) {
        try {
            if (!cpw.equals(pw)) {
                session.setAttribute("ERROR1", PASSWORD_CPW_NOT_MATHCED);
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.getLogger(UserCreateServlet.class.getName()).log(Level.SEVERE, null, e);
            return true;
        }
    }

    protected void resetError(HttpSession session) {
        session.setAttribute("ERROR1", "");
        session.setAttribute("ERROR2", "");
        session.setAttribute("ERROR3", "");
        session.setAttribute("ERROR4", "");
    }
}
