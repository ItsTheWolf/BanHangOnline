
package com.mycompany.showroomonline.bus.CategoryServlets;

import com.mycompany.showroomonline.bus.UserServlets.UserCreateServlet;
import com.mycompany.showroomonline.dao.CategoryDAO;
import com.mycompany.showroomonline.dao.RoleDAO;
import com.mycompany.showroomonline.dto.Category;
import com.mycompany.showroomonline.dto.Role;
import java.io.IOException;
import java.io.PrintWriter;
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


@WebServlet("/categorycreate")
public class CategoryCreateServlet extends HttpServlet {

     private CategoryDAO categoryDAO = new CategoryDAO();
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
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/categoryindex.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            Logger.getLogger(CategoryCreateServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    //
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try{
            request.setCharacterEncoding("UTF-8");
            getRolesList(request);
//            resetError(request);
            String CategoryName = request.getParameter("txtCategory");
            
          
           
                Category item = new Category(CategoryName);
                categoryDAO.createCategory(item);
                
                HttpSession session = request.getSession();
               
                    response.sendRedirect(request.getContextPath() + "/index");
                
            
        } catch (Exception e) {
            Logger.getLogger(CategoryCreateServlet.class.getName()).log(Level.SEVERE, null, e);
       
    }
    //
    
    
    
    }
    
   protected void getRolesList(HttpServletRequest request) {
        List<Role> listItem = roleDAO.readAll();
        request.setAttribute("model", listItem);
    }
}