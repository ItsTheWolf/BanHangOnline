package com.mycompany.showroomonline.bus.CategoryServlets;

import com.mycompany.showroomonline.dao.CategoryDAO;
import com.mycompany.showroomonline.dto.Category;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/categoryedit")
public class CategoryEditServlet extends HttpServlet {

    private CategoryDAO categoryDAO = new CategoryDAO();
    String REQUIRED_FIELDS_BLANK = "Please fill in the required (*) fields.";
    String BACK = "Click <a href='categorycreate'>here</a> to turn back.";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            resetError(session);
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
            HttpSession session = request.getSession();
            session.setAttribute("BACK", BACK);
            resetError(session);
            int id = Integer.parseInt(request.getParameter("txtId"));
            String category = request.getParameter("txtCategory");
            boolean error = validation(category, session, request);
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

    protected boolean validation(String category, HttpSession session, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        try {
            if (category.equals("")) {
                session.setAttribute("ERROR1", REQUIRED_FIELDS_BLANK);
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.getLogger(CategoryCreateServlet.class.getName()).log(Level.SEVERE, null, e);
            return true;
        }
    }

    protected void resetError(HttpSession session) {
        session.setAttribute("ERROR1", "");
        session.setAttribute("ERROR2", "");
        session.setAttribute("ERROR3", "");
        session.setAttribute("ERROR4", "");
    }
}
