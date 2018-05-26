<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"></jsp:include>
    <body>
        <form action="" method="POST" enctype="multipart/form-data" >
            <div>
                Name: <input type="text"  id="txtName" name="txtName">                
            </div>
            <div>
                <input type="file"  id="file" name="file" size="50">                
            </div>
            <div>
                <button type="submit">Tạo mới</button>
            </div>
        </form>
    </body>
</html>
