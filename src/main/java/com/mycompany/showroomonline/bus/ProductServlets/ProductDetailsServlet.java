package com.mycompany.showroomonline.bus.ProductServlets;

import com.mycompany.showroomonline.dao.ProductDAO;
import com.mycompany.showroomonline.dto.Product;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/productdetails")
public class ProductDetailsServlet extends HttpServlet {

    private ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Product item = productDAO.read(id);
            request.setAttribute("model", item);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/productdetails.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            Logger.getLogger(ProductDetailsServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}