<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean 
    id="single_course" 
    scope="request" 
    class="com.academik.mvc.model.Course"/>
<jsp:include page="../templates/header.jsp">
    <jsp:param name="custom-title" value="Academik | Editar Curso"/>
</jsp:include>

<h1>Editar Curso</h1>
<form method="POST">
    <input type="hidden" name="_method" value="PUT"/>
    <input type="hidden" name="code" value="${single_course.code}"/>
    <div class="form-group">
        <label for="c_coursename">Nombre del Curso</label>
        <input class="form-control" type="text" name="c_coursename" value="${single_course.courseName}"/>
    </div>
    <div class="form-group">
        <label for="c_coursedescription">Descripcion del Curso</label>
        <input class="form-control" type="text" name="c_coursedescription" value="${single_course.courseDescription}"/>
    </div>
    <div class="form-group">
        <label for="c_coursecredits">Creditos del Curso</label>
        <input class="form-control" type="text" name="c_coursecredits" value="${single_course.courseCredits}"/>
    </div>
    <input class="btn btn-primary" type="submit" value="Guardar"/>
</form>

<%@include file="../templates/footer.jsp" %>