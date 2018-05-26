package com.mycompany.showroomonline.bus.ThumbnailServlets;

import com.mycompany.showroomonline.dao.ThumbnailDAO;
import com.mycompany.showroomonline.dto.Thumbnail;
import java.io.File;
import java.io.IOException;
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
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/upload")
public class ThumbnailUploadServlet extends HttpServlet{
   private ThumbnailDAO thumbnailDAO = new ThumbnailDAO();
    
   private boolean isMultipart;
   
   // Nơi lưu trữ file hình
   private String filePath = "C:\\Users\\danie\\Documents\\NetBeansProjects\\ShowroomOnline\\src\\main\\webapp\\resources\\img\\";
   private int maxFileSize = 50 * 1024; // KB
   private int maxMemSize = 4 * 1024; // KB
   private File file ;
     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/upload.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ThumbnailUploadServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {      
        // kiểm tra xem có file upload hay không
      isMultipart = ServletFileUpload.isMultipartContent(request);
      response.setContentType("text/html");
      java.io.PrintWriter out = response.getWriter( );
   
      // Xuất ra Error nếu không có file upload
      if( !isMultipart ) {
         out.println("<html>");
         out.println("<head>");
         out.println("<title>Servlet upload</title>");  
         out.println("</head>");
         out.println("<body>");
         out.println("<p>No file uploaded</p>"); 
         out.println("</body>");
         out.println("</html>");
         return;
      }
  
      DiskFileItemFactory factory = new DiskFileItemFactory();
   
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
   
      // Location to save data that is larger than maxMemSize.
      factory.setRepository(new File("D:\\")); // Nếu file gửi lên lớn hơn maxMemSize thì lưu qua ổ C

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
   
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );

      
      try { 
         // Parse the request to get file items.
         List fileItems = upload.parseRequest(request);
	
         // Process the uploaded file items
         Iterator i = fileItems.iterator();

         out.println("<html>");
         out.println("<head>");
         out.println("<title>Servlet upload</title>");  
         out.println("</head>");
         out.println("<body>");
         
         
         String name=""; // biến lưu lại field name
         
         while ( i.hasNext () ) {
            FileItem fi = (FileItem)i.next();
            if ( !fi.isFormField () ) {
               // Get the uploaded file parameters
               String fieldName = fi.getFieldName();
               String fileName = fi.getName();
               String contentType = fi.getContentType();
               boolean isInMemory = fi.isInMemory();
               long sizeInBytes = fi.getSize();
            
               // Write the file - Bắt đầu upload file
               if( fileName.lastIndexOf("\\") >= 0 ) {
                  file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
               } else {
                  file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
               }
               fi.write( file ) ;
               out.println("Uploaded Filename: " + fileName + "<br>");
                            
              
               
              
            }
            else{
                // Chỗ này lấy dữ liệu của các input filed trên form
                String inputName = (String)fi.getFieldName(); 
                if(inputName.equalsIgnoreCase("txtName")){
                    name = (String)fi.getString(); 
                }
            }
         }
         
         // Bắt đầu từ chỗ này ta lưu xuống CSDL với tên file name đã được lưu
         Thumbnail item = new Thumbnail(name,file.getName());
         thumbnailDAO.createThumbnail(item);
               
        response.sendRedirect(request.getContextPath() +  "/index");
         
         out.println("</body>");
         out.println("</html>");
         } catch(Exception ex) {
            System.out.println(ex);
         }
    }
}
