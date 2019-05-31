<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../templates/header.jsp">
    <jsp:param name="custom-title" value="Academik | Crear curso"/>
</jsp:include>

    <h1>Nuevo curso</h1>
    <form method="POST">
        <div class="form-group">
            <label for="c_coursename">Nombre del Curso</label>
            <input class="form-control" type="text" name="c_coursename"/>
        </div>
        <div class="form-group">
            <label for="c_coursedescription">Descripcion de Curso</label>
            <input class="form-control" type="text" name="c_coursedescription"/>
        </div>
        <div class="form-group">
            <label for="c_coursecredits">Creditos del Curso</label>
            <input class="form-control" type="text" name="c_coursecredits"/>
        </div>
        <input class="btn btn-primary" type="submit" value="Crear"/>
    </form>

<%@include file="../templates/footer.jsp" %>