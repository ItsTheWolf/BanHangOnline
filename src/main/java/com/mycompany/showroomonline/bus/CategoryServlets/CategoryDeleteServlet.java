package com.mycompany.showroomonline.bus.CategoryServlets;

import com.mycompany.showroomonline.dao.CategoryDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/categorydelete")
public class CategoryDeleteServlet extends HttpServlet {

    private CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            int id = Integer.parseInt(request.getParameter("id"));
            categoryDAO.deleteCategory(id);
            response.sendRedirect(request.getContextPath() + "/categoryindex");
        } catch (Exception e) {
            request.setCharacterEncoding("UTF-8");
            int id = Integer.parseInt(request.getParameter("id"));
            categoryDAO.deleteallProducts(id);
            categoryDAO.deleteCategory(id);
            response.sendRedirect(request.getContextPath() + "/categoryindex");
        }
    }
}
