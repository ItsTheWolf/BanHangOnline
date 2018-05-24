package com.mycompany.showroomonline.bus.CategoryServlets;

import com.mycompany.showroomonline.dao.CategoryDAO;
import com.mycompany.showroomonline.dto.Category;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/categorycreate")
public class CategoryCreateServlet extends HttpServlet {

    private CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//            resetError(request);
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
//            resetError(request);
            String category = request.getParameter("txtCategory");
            boolean error = validation(category, request);
            if (error) {
                response.sendRedirect(request.getContextPath() + "/categorycreate");
            } else {
                Category item = new Category(category);
                categoryDAO.createCategory(item);
                response.sendRedirect(request.getContextPath() + "/categoryindex");
            }
        } catch (Exception e) {
            Logger.getLogger(CategoryCreateServlet.class.getName()).log(Level.SEVERE, null, e);
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
