package com.mycompany.showroomonline.bus.CategoryServlets;

import com.mycompany.showroomonline.dao.CategoryDAO;
import com.mycompany.showroomonline.dto.Category;
import com.mycompany.showroomonline.dto.Product;
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

@WebServlet("/categorydetails")
public class CategoryDetailsServlet extends HttpServlet {

    CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            getCategoryList(request);
            Category item = categoryDAO.read(id);
            List<Product> products = categoryDAO.findbyProduct(id);
            request.setAttribute("model", item);
            request.setAttribute("products", products);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/categorydetails.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            Logger.getLogger(CategoryDetailsServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    protected void getCategoryList(HttpServletRequest request) {
        List<Category> listItem = categoryDAO.readAll();
        request.setAttribute("listItem", listItem);
    }
}
