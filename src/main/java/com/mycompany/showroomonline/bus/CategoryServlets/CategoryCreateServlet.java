/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.showroomonline.bus.CategoryServlets;

import com.mycompany.showroomonline.dao.CategoryDAO;
import com.mycompany.showroomonline.dao.RoleDAO;
import com.mycompany.showroomonline.dto.Category;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/CategoryCreate")
public class CategoryCreateServlet extends HttpServlet {

    
    
    private CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/categorycreate.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            Logger.getLogger(CategoryCreateServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String name = request.getParameter("txtCategory");
            Category item = new Category(name);
            categoryDAO.createCategory(item);
            response.sendRedirect(request.getContextPath() + "/categoryindex");
        } catch (Exception e) {
            Logger.getLogger(CategoryCreateServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    
}
