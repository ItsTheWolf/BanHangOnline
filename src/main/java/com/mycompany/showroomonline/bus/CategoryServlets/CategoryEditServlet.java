/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.showroomonline.bus.CategoryServlets;

import com.mycompany.showroomonline.bus.UserServlets.UserCreateServlet;
import com.mycompany.showroomonline.dao.CategoryDAO;
import com.mycompany.showroomonline.dao.ProductDAO;
import com.mycompany.showroomonline.dto.Category;
import com.mycompany.showroomonline.dto.Product;
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

/**
 *
 * @author danie
 */
@WebServlet("/categoryedit")
public class CategoryEditServlet extends HttpServlet {

    private CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//            resetError(request);
            int id = Integer.parseInt(request.getParameter("id"));
            Category item = categoryDAO.read(id);
            request.setAttribute("model", item);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/categoryedit.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            Logger.getLogger(CategoryEditServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
//            resetError(request);
            int id = Integer.parseInt(request.getParameter("txtId"));
            String category = request.getParameter("txtCategory");
            boolean error = validation(category, request);
            if (error) {
                response.sendRedirect(request.getContextPath() + "/categoryedit");
            } else {
                Category item = new Category(id, category);
                categoryDAO.updateCategory(item);
                response.sendRedirect(request.getContextPath() + "/categoryindex");
            }
        } catch (Exception e) {
            Logger.getLogger(CategoryEditServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    protected boolean validation(String category, HttpServletRequest request) {
        try {
            if (category.equals("")) {
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.getLogger(CategoryCreateServlet.class.getName()).log(Level.SEVERE, null, e);
            return true;
        }
    }
}
