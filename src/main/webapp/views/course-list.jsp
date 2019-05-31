<%@page import="com.academik.mvc.model.Course" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../templates/header.jsp">
    <jsp:param name="custom-title" value="Academik | Cursos"/>
</jsp:include>

<!-- 
Etiqueta que declara el objeto list_of_courses 
proveniente del CourseController, método doGet.
-->
<jsp:useBean 
    id="list_of_courses"
    scope="request" 
    class="List<Course>" />
    
<table class="table">
    <thead>
        <tr>
            <th>Nombre del Curso</th>
            <th>Descripcion del Curso</th>
            <th>Creditos de Curso</th>
        </tr>
    </thead>
    <tbody>
        <% for(Course c : list_of_courses) { %> 
        <tr>
            <td><%= c.getCourseName() %></td>
            <td><%= c.getCourseDescription() %></td>
            <td><%= c.getCourseCredits() %></td>
            <td>
                <a class="btn btn-primary" href="courses/edit?id=<%= c.getCode() %>">Editar</a>
                <a class="btn btn-primary" href="courses/view?id=<%= c.getCode() %>">Ver</a>
            </td>
        </tr>
        <%}%>
    </tbody>
</table>

<%@include file="../templates/footer.jsp" %>

