package com.mycompany.banhangonline.bus.UserServlets;

import com.mycompany.banhangonline.dao.RoleDAO;
import com.mycompany.banhangonline.dao.UserDAO;
import com.mycompany.banhangonline.dto.Role;
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
import javax.servlet.http.HttpSession;

@WebServlet("/register")
public class UserCreateServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();
    private RoleDAO roleDAO = new RoleDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            getRolesList(request);
            resetError(request);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/usercreate.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            Logger.getLogger(UserCreateServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            getRolesList(request);
            resetError(request);
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String cpw = request.getParameter("txtCPassword");
            String fullname = request.getParameter("txtFullname");
            String address = request.getParameter("txtAddress");
            String email = request.getParameter("txtEmail");
            Role roleid = roleDAO.read(Integer.parseInt(request.getParameter("txtRoleId")));
            int rid = (Integer.parseInt(request.getParameter("txtRoleId")));
            boolean error = validation(username, password, cpw, email, rid, request);
            if (error) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/usercreate.jsp");
                rd.include(request, response);
            } else {
                User item = new User(username, password, fullname, email, address, roleid);
                userDAO.createUser(item);
                HttpSession registerSession = request.getSession();
                registerSession.setAttribute("loggedName", username);
                registerSession.setMaxInactiveInterval(60 * 5);
                response.sendRedirect(request.getContextPath() + "/userindex");
            }

        } catch (Exception e) {
            Logger.getLogger(UserCreateServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    protected boolean validation(String username, String password, String cpw, String email, int rid, HttpServletRequest request) {
        try {
            if (username.equals("") || password.equals("") || cpw.equals("") || email.equals("") || rid == 0) {
                request.setAttribute("ERROR", 1);
                return true;
            }
            if (!cpw.equals(password)) {
                request.setAttribute("ERROR", 2);
                return true;
            }
            if (!email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
                request.setAttribute("ERROR", 3);
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.getLogger(UserCreateServlet.class.getName()).log(Level.SEVERE, null, e);
            return true;
        }
    }

    protected void resetError(HttpServletRequest request) {
        request.setAttribute("ERROR", 0);
    }

    protected void getRolesList(HttpServletRequest request) {
        List<Role> listItem = roleDAO.readAll();
        request.setAttribute("model", listItem);
    }
}
