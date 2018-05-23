
package com.mycompany.showroomonline.bus.CategoryServlets;

import com.mycompany.showroomonline.bus.UserServlets.UserIndexServlet;
import com.mycompany.showroomonline.dao.CategoryDAO;
import com.mycompany.showroomonline.dao.RoleDAO;
import com.mycompany.showroomonline.dto.Category;
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

@WebServlet("/categoryindex")
public class CategoryIndexServlet extends HttpServlet {

   private CategoryDAO CategoryDAO=new CategoryDAO();
    
    private RoleDAO roleDAO = new RoleDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Category> listItem = CategoryDAO.readAll();
            request.setAttribute("model", listItem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/categoryindex.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            Logger.getLogger(UserIndexServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
