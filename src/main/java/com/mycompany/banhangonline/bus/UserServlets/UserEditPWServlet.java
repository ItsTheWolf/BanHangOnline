package com.mycompany.banhangonline.bus.UserServlets;

import com.mycompany.banhangonline.dao.UserDAO;
import com.mycompany.banhangonline.dto.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/usereditpw")
public class UserEditPWServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            if (!username.equals("admin")) {
//                resetError(request);
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
//            resetError(request);
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String cpw = request.getParameter("txtCPassword");
            boolean error = validation(password, cpw, request);
            if (error) {
                response.sendRedirect(request.getContextPath() + "/userindexpw?username=" + username);
            } else {
                User item = new User(username, password);
                userDAO.updateUser(item);
                response.sendRedirect(request.getContextPath() + "/userindexpw");
            }
        } catch (Exception e) {
            Logger.getLogger(UserEditServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    protected boolean validation(String pw, String cpw, HttpServletRequest request) {
        try {
            if (!cpw.equals(pw)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.getLogger(UserCreateServlet.class.getName()).log(Level.SEVERE, null, e);
            return true;
        }
    }

//    protected void resetError(HttpServletRequest request) {
//        request.setAttribute("ERROR", 0);
//    }
}