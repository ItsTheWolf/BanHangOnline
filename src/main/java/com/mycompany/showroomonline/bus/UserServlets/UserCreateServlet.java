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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            getRolesList(request);
            HttpSession session = request.getSession();
            if (session.getAttribute("loggedRole") == null) {
                session.setAttribute("loggedRole", "null");
            }
//            resetError(request);
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
//            resetError(request);
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
            boolean error = validation(username, password, cpw, email, rid, request);
            if (error) {
                response.sendRedirect(request.getContextPath() + "/register");
            } else {
                User item = new User(username, password, fullname, email, address, roleid);
                userDAO.createUser(item);
                HttpSession session = request.getSession();
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

    protected boolean validation(String username, String password, String cpw, String email, int rid, HttpServletRequest request) {
        try {
            if (username.equals("") || password.equals("") || cpw.equals("") || email.equals("") || rid == 0 || !cpw.equals(password)
                    || !email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.getLogger(UserCreateServlet.class.getName()).log(Level.SEVERE, null, e);
            return true;
        }
    }

//    protected void resetError(HttpServletRequest request) {
//        request.setAttribute("ERROR1", "");
//        request.setAttribute("ERROR2", "");
//        request.setAttribute("ERROR3", "");
//    }
    protected void getRolesList(HttpServletRequest request) {
        List<Role> listItem = roleDAO.readAll();
        request.setAttribute("model", listItem);
    }
}
