package com.mycompany.showroomonline.bus.ProductServlets;

import com.mycompany.showroomonline.dao.CategoryDAO;
import com.mycompany.showroomonline.dao.ProductDAO;
import com.mycompany.showroomonline.dto.Category;
import com.mycompany.showroomonline.dto.Product;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
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
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/productedit")
public class ProductEditServlet extends HttpServlet {

    private ProductDAO productDAO = new ProductDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();
    private String filePath = "C:\\Users\\danie\\Documents\\NetBeansProjects\\ShowroomOnline\\src\\main\\webapp\\resources\\img\\";
    String REQUIRED_FIELDS_BLANK = "Please fill in the required (*) fields.";
    String BACK1 = "Click <a href='productedit?id=";
    String BACK2 = "'>here</a> to turn back.";
    private int maxFileSize = 9999 * 1024; // KB
    private int maxMemSize = 9999 * 1024; // KB
    private File file;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Product item = productDAO.read(id);
            request.setAttribute("model", item);
            getCategoryList(request);
            HttpSession session = request.getSession();
            resetError(session);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/productedit.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            Logger.getLogger(ProductEditServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            getCategoryList(request);
            HttpSession session = request.getSession();
            int id = Integer.parseInt(request.getParameter("id"));
            session.setAttribute("BACK", BACK1 + id + BACK2);
            // <editor-fold defaultstate="collapsed" desc="uploadimg">
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(maxMemSize);
            factory.setRepository(new File("D:\\"));
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(maxFileSize);
            List fileItems = upload.parseRequest(request);
            Iterator i = fileItems.iterator();
            String product = "", description = "", thumbnail = "";
            double price = 0;
            int stock = 0, cid = 0;
            while (i.hasNext()) {
                try {
                    FileItem fi = (FileItem) i.next();
                    if (!fi.isFormField()) {
                        String fileName = fi.getName();
                        if (fileName.lastIndexOf("\\") >= 0) {
                            file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
                        } else {
                            file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
                        }
                        fi.write(file);
                        thumbnail = file.getName();
                    } else {
                        String input = fi.getFieldName();
                        if (input.equalsIgnoreCase("txtProduct")) {
                            product = fi.getString();
                            if (product.equals("")) {
                                session.setAttribute("ERROR1", REQUIRED_FIELDS_BLANK);
                                response.sendRedirect(request.getContextPath() + "/error.jsp");
                            }
                        } else if (input.equalsIgnoreCase("txtPrice")) {
                            try {
                                price = Double.parseDouble(fi.getString());
                            } catch (IllegalStateException | NumberFormatException e) {
                                session.setAttribute("ERROR1", REQUIRED_FIELDS_BLANK);
                                response.sendRedirect(request.getContextPath() + "/error.jsp");
                            }
                        } else if (input.equalsIgnoreCase("txtAmount")) {
                            try {
                                stock = Integer.parseInt(fi.getString());
                            } catch (IllegalStateException | NumberFormatException e) {
                                session.setAttribute("ERROR1", REQUIRED_FIELDS_BLANK);
                                response.sendRedirect(request.getContextPath() + "/error.jsp");
                            }
                        } else if (input.equalsIgnoreCase("txtCateId")) {
                            try {
                                cid = Integer.parseInt(fi.getString());
                            } catch (IllegalStateException | NumberFormatException e) {
                                session.setAttribute("ERROR1", REQUIRED_FIELDS_BLANK);
                                response.sendRedirect(request.getContextPath() + "/error.jsp");
                            }
                        } else if (input.equalsIgnoreCase("txtDesc")) {
                            description = fi.getString();
                        }
                    }
                } catch (FileNotFoundException e) {
                    thumbnail = productDAO.read(id).getThumbnail();
                }
            }
            // </editor-fold>
            Category category = categoryDAO.read(cid);
            boolean error = validation(product, cid, session, request);
            if (error) {
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            } else {
                Product item = new Product(id, product, description, price, stock, thumbnail, category);
                productDAO.updateProduct(item);
                response.sendRedirect(request.getContextPath() + "/index");
            }
        } catch (Exception e) {
            Logger.getLogger(ProductEditServlet.class.getName()).log(Level.SEVERE, null, e);
            HttpSession session = request.getSession();
            session.setAttribute("ERROR1", REQUIRED_FIELDS_BLANK);
            try {
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            } catch (IllegalStateException ex) {
            }
        }
    }

    protected boolean validation(String product, int cid, HttpSession session, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        try {
            if (product.equals("") || cid == 0) {
                session.setAttribute("ERROR1", REQUIRED_FIELDS_BLANK);
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
        request.setAttribute("listItem", listItem);
    }

    protected void resetError(HttpSession session) {
        session.setAttribute("ERROR1", "");
        session.setAttribute("ERROR2", "");
        session.setAttribute("ERROR3", "");
        session.setAttribute("ERROR4", "");
    }
}
