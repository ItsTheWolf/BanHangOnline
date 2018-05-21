package com.mycompany.banhangonline.bus;

import com.mycompany.banhangonline.dao.RoleDAO;
import com.mycompany.banhangonline.dto.Role;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/roledetails")
public class RoleDetailsServlet extends HttpServlet {

    private RoleDAO roleDAO = new RoleDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Role item = roleDAO.read(id);
            request.setAttribute("model", item);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/roledetails.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            Logger.getLogger(RoleDetailsServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
