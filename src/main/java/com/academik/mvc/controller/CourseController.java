package com.academik.mvc.controller;

import com.academik.mvc.dao.CourseDAO;
import com.academik.mvc.model.Course;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Juanpa
 */
@WebServlet("/courses/*")
public class CourseController extends HttpServlet {

    CourseDAO dao = new CourseDAO();
    
    /**
     * Determina que "recurso" mostrar al usuario.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String complement = req.getPathInfo();
        if(complement == null)
            complement = "";
        System.err.println(complement);
        String redirectPage;
        switch(complement) {
            case "/create":
                redirectPage = "course-create.jsp";
                break;
            case "/view":
                //Obtengo el parametro desde la URL
                long idToView = Long.parseLong(req.getParameter("id"));
                Course cToView = dao.findById(idToView);
                req.setAttribute("single_course", cToView);
                redirectPage = "course-view.jsp";
                break;
            case "/edit":
                long idToEdit = Long.parseLong(req.getParameter("id"));
                Course cToEdit = dao.findById(idToEdit);
                req.setAttribute("single_course", cToEdit);
                redirectPage = "course-edit.jsp";
                break;
            case "/list":
            case "/":
            case "":
                List<Course> allCourses = dao.queryAll();
                req.setAttribute("list_of_courses", allCourses);
                redirectPage = "course-list.jsp";
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/courses");
                return;
        }

        //Renderice este JSP
        RequestDispatcher rd = req.getRequestDispatcher(
                "/views/" + redirectPage
        );
        //Adelante con la renderización
        rd.forward(req, resp);
    }

    /**
     * Para recibir la petición de crear un nuevo curso.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        if("PUT".equals(req.getParameter("_method"))){
            doPut(req, resp);
            return;
        }
        if("DELETE".equals(req.getParameter("_method"))){
            doDelete(req, resp);
            return;
        }
            
        System.out.println("Creating a new course");
        //Variable vacia
        Course nuevo = new Course();
        
        //Valores para las propiedades que vienen desde el formulario HTML
        nuevo.setCourseName(req.getParameter("c_coursename"));
        nuevo.setCourseDescription(req.getParameter("c_coursedescription"));
        nuevo.setCourseCredits(Integer.parseInt(req.getParameter("c_coursecredits")));
        
        //Utilizar el DAO para guardar la informacion en la base de datos
        dao.create(nuevo);
        resp.sendRedirect(req.getContextPath() + "/courses");
    }

    /**
     * Para modificar un curso ya existente.
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Editing an course...");
        //Variable vacia
        Course edited = new Course();
        
        //Valores NUEVOS para las propiedades que vienen desde el formulario HTML
        
        edited.setCourseName(req.getParameter("c_coursename"));
        edited.setCourseDescription(req.getParameter("c_coursedescription"));
        edited.setCourseCredits(Integer.parseInt(req.getParameter("c_coursecredits")));
                
        //Utilizar el DAO para guardar la informacion en la base de datos
        dao.edit(Integer.parseInt(req.getParameter("code")), edited);
        resp.sendRedirect(req.getContextPath() + "/courses");
    }

    /**
     * Para eliminar un curso.
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.err.println("Deleting course...");
        long id = Long.parseLong(req.getParameter("code"));
        dao.delete(id);
        resp.sendRedirect(req.getContextPath() + "/courses");
    }
    
}

