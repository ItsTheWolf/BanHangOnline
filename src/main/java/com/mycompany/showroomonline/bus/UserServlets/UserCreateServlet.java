package com.mycompany.showroomonline.bus.UserServlets;

import com.mycompany.showroomonline.dao.RoleDAO;
import com.mycompany.showroomonline.dao.UserDAO;
import com.mycompany.showroomonline.dto.Role;
import com.mycompany.showroomonline.dto.User;
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
    String USERNAME_EXIST = "Username already exists.";
    String REQUIRED_FIELDS_BLANK = "Please fill in the required (*) fields.";
    String PASSWORD_CPW_NOT_MATHCED = "Password and Confirm Password do not match.";
    String INVALID_EMAIL_FORMAT = "Email is invalid.";
    String BACK = "Click <a href='register'>here</a> to turn back.";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            getRolesList(request);
            HttpSession session = request.getSession();
            if (session.getAttribute("loggedRole") == null) {
                session.setAttribute("loggedRole", "null");
            }
            resetError(session);
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
            HttpSession session = request.getSession();
            session.setAttribute("BACK", BACK);
            getRolesList(request);
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String cpw = request.getParameter("txtCPassword");
            String fullname = request.getParameter("txtFullname");
            String address = request.getParameter("txtAddress");
            String email = request.getParameter("txtEmail");
            int rid;
            try {
                rid = (Integer.parseInt(request.getParameter("txtRoleId")));
            } catch (NumberFormatException e) {
                rid = 3;
            }
            Role roleid = roleDAO.read(rid);
            boolean error = validation(username, password, cpw, email, rid, session);
            if (error) {
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            } else {
                User item = new User(username, password, fullname, email, address, roleid);
                userDAO.createUser(item);
                session = request.getSession();
                if (session.getAttribute("loggedName") != null) {
                    response.sendRedirect(request.getContextPath() + "/userindex");
                } else {
                    session.setAttribute("loggedName", username);
                    session.setAttribute("loggedRole", roleid.getName());
                    session.setAttribute("loggedRoleId", rid);
                    response.sendRedirect(request.getContextPath() + "/index");
                }
            }
        } catch (Exception e) {
            Logger.getLogger(UserCreateServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    protected boolean validation(String username, String password, String cpw, String email, int rid, HttpSession session) {
        User user = userDAO.read(username);
        int err1 = 0, err2 = 0, err3 = 0, err4 = 0;
        try {
            if (username.equals("") || password.equals("") || cpw.equals("") || email.equals("") || rid == 0) {
                session.setAttribute("ERROR1", REQUIRED_FIELDS_BLANK);
                err1 = 1;
            }
            if (user.getUsername().equals(username)) {
                session.setAttribute("ERROR2", USERNAME_EXIST);
                err2 = 1;
            }
            if (!cpw.equals(password)) {
                session.setAttribute("ERROR3", PASSWORD_CPW_NOT_MATHCED);
                err3 = 1;
            }
            if (!email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
                session.setAttribute("ERROR4", INVALID_EMAIL_FORMAT);
                err4 = 1;
            }
            return err1 == 1 || err2 == 1 || err3 == 1 || err4 == 1;
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

    protected void getRolesList(HttpServletRequest request) {
        List<Role> listItem = roleDAO.readAll();
        request.setAttribute("model", listItem);
    }
}
