package com.mycompany.showroomonline.bus.ProductServlets;

import com.mycompany.showroomonline.dao.CategoryDAO;
import com.mycompany.showroomonline.dao.ProductDAO;
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

@WebServlet("/productcreate")
public class ProductCreateServlet extends HttpServlet {

    private ProductDAO productDAO = new ProductDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            getCategoryList(request);
//            resetError(request);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/productcreate.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            Logger.getLogger(ProductCreateServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            getCategoryList(request);
//            resetError(request);
            String product = request.getParameter("txtProduct");
            double price = Double.parseDouble(request.getParameter("txtPrice"));
            int stock = Integer.parseInt(request.getParameter("txtAmount"));
            int cid = Integer.parseInt(request.getParameter("txtCateId"));
            Category category = categoryDAO.read(cid);
            String description = request.getParameter("txtDesc");
            String link = request.getParameter("txtLink");
            if (link.equals("resources/img/") || link.equals("")) {
                link = "resources/img/thumbnailtmp.png";
            }
            boolean error = validation(product, price, stock, cid, request);
            if (error) {
                response.sendRedirect(request.getContextPath() + "/productcreate");
            } else {
                Product item = new Product(product, description, price, stock, link, category);
                productDAO.createProduct(item);
                response.sendRedirect(request.getContextPath() + "/index");
            }
        } catch (Exception e) {
            Logger.getLogger(ProductCreateServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    protected boolean validation(String product, double price, int stock, int cid, HttpServletRequest request) {
        try {
            if (product.equals("") || price == 0 || stock == 0 || cid == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.getLogger(ProductCreateServlet.class.getName()).log(Level.SEVERE, null, e);
            return true;
        }
    }

    protected void getCategoryList(HttpServletRequest request) {
        List<Category> listItem = categoryDAO.readAll();
        request.setAttribute("model", listItem);
    }
}
