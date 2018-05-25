package com.mycompany.showroomonline.bus.UserServlets;

import com.mycompany.showroomonline.dao.UserDAO;
import com.mycompany.showroomonline.dto.User;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
    String REQUIRED_FIELDS_BLANK = "Please fill in the required (*) fields.";
    String PASSWORD_CPW_NOT_MATHCED = "Password and Confirm Password do not match.";
    String BACK1 = "Click <a href='usereditpw?username=";
    String BACK2 = "'>here</a> to turn back.";

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
                response.sendRedirect(request.getContextPath() + "/error-authorization.jsp");
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
            String username = request.getParameter("txtUsername");
            session.setAttribute("BACK", BACK1 + username + BACK2);
            String password = request.getParameter("txtPassword");
            String cpw = request.getParameter("txtCPassword");
            boolean error = validation(password, cpw, session, request);
            if (error) {
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            } else {
                User user = userDAO.read(username);
                user.setPassword(password);
                userDAO.updateUser(user);
                response.sendRedirect(request.getContextPath() + "/userindex");
            }
        } catch (Exception e) {
            Logger.getLogger(UserEditServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    protected boolean validation(String pw, String cpw, HttpSession session, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        int err1 = 0, err2 = 0;
        try {
            if (pw.equals("") || cpw.equals("")) {
                session.setAttribute("ERROR1", REQUIRED_FIELDS_BLANK);
                err1 = 1;
            }
            if (!cpw.equals(pw)) {
                session.setAttribute("ERROR2", PASSWORD_CPW_NOT_MATHCED);
                err2 = 1;
            }
            return err1 == 1 || err2 == 1;
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
