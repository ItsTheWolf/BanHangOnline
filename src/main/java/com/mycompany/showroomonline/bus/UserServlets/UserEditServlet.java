package com.mycompany.showroomonline.bus.UserServlets;

import com.mycompany.showroomonline.dao.RoleDAO;
import com.mycompany.showroomonline.dao.UserDAO;
import com.mycompany.showroomonline.dto.Role;
import com.mycompany.showroomonline.dto.User;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

@WebServlet("/useredit")
public class UserEditServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();
    private RoleDAO roleDAO = new RoleDAO();
    String REQUIRED_FIELDS_BLANK = "Please fill in the required (*) fields.";
    String INVALID_EMAIL_FORMAT = "Email is invalid.";
    String BACK1 = "Click <a href='useredit?username=";
    String BACK2 = "'>here</a> to turn back.";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String username = request.getParameter("username");
            HttpSession session = request.getSession();
            resetError(session);
            if (!username.equals("admin")) {
                getRolesList(request);
                User item = userDAO.read(username);
                request.setAttribute("model", item);
                getRolesList(request);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/useredit.jsp");
                rd.forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/error-authorization.jsp");
            }
        } catch (IOException | ServletException e) {
            Logger.getLogger(UserEditServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            getRolesList(request);
            HttpSession session = request.getSession();
            String username = request.getParameter("txtUsername");
            session.setAttribute("BACK", BACK1 + username + BACK2);
            String fullname = request.getParameter("txtFullname");
            String email = request.getParameter("txtEmail");
            String address = request.getParameter("txtAddress");
            int rid;
            boolean error;
            Role roleid;
            try {
                rid = (Integer.parseInt(request.getParameter("txtRoleId")));
                roleid = roleDAO.read(rid);
                error = validation(email, rid, session, request);
            } catch (NumberFormatException e) {
                roleid = userDAO.read(username).getRole();
                error = validationWithoutRid(email, session);
            }
            if (error) {
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            } else {
                User item = new User(username, fullname, email, address, roleid);
                userDAO.updateUser(item);
                response.sendRedirect(request.getContextPath() + "/userindex");
            }
        } catch (IOException e) {
            Logger.getLogger(UserEditServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    protected boolean validation(String email, int rid, HttpSession session, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        int err1 = 0, err2 = 0;
        try {
            if (email.equals("") || rid == 0) {
                session.setAttribute("ERROR1", REQUIRED_FIELDS_BLANK);
                err1 = 1;
            }
            if (!email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
                session.setAttribute("ERROR2", INVALID_EMAIL_FORMAT);
                err2 = 1;
            }
            return err1 == 1 || err2 == 1;
        } catch (Exception e) {
            Logger.getLogger(UserCreateServlet.class.getName()).log(Level.SEVERE, null, e);
            return true;
        }
    }

    protected boolean validationWithoutRid(String email, HttpSession session) {
        int err1 = 0, err2 = 0;
        try {
            if (email.equals("")) {
                session.setAttribute("ERROR1", REQUIRED_FIELDS_BLANK);
                err1 = 1;
            }
            if (!email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
                session.setAttribute("ERROR2", INVALID_EMAIL_FORMAT);
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

    protected void getRolesList(HttpServletRequest request) {
        List<Role> listItem = roleDAO.readAll();
        request.setAttribute("listItem", listItem);
    }
}
