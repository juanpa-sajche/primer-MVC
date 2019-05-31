package com.academik.mvc.dao;

import com.academik.mvc.model.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de todas las operaciones de base de datos referentes a la
 * clase Course.
 *
 * @author Juanpa
 */
public class CourseDAO implements GeneralDAO<Course> {

    /**
     * Método que se encarga de traer los objetos Course 
     * desde la base de datos.
     *
     * @return
     */
    @Override
    public List<Course> queryAll() {
        List<Course> temp = new ArrayList<>();
        try {
            Connection conn = CONN_WRAPPER.getConnection();
            Statement stmnt = conn.createStatement();
            ResultSet result = stmnt.executeQuery("SELECT code, course_name, course_description, course_credits FROM course");
            while (result.next()) {
                Course c = new Course();
                c.setCode(result.getLong("code"));
                c.setCourseName(result.getString("course_name"));
                c.setCourseDescription(result.getString("course_description"));
                c.setCourseCredits(result.getInt("course_credits"));
                temp.add(c);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return temp;
    }

    @Override
    public Course findById(long id) {
        Course c = null;
        try {
            Connection conn = CONN_WRAPPER.getConnection();       
            PreparedStatement stmnt = conn.prepareStatement("SELECT code, course_name, course_description, course_credits FROM course WHERE code = ?");
            stmnt.setLong(1, id);

            ResultSet result = stmnt.executeQuery();
            if (result.next()) {
                c = new Course();
                c.setCode(result.getLong("code"));
                c.setCourseName(result.getString("course_name"));
                c.setCourseDescription(result.getString("course_description"));
                c.setCourseCredits(result.getInt("course_credits"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }

        return c;
    }

    @Override
    public void create(Course nuevo) {
        try {
            Connection conn = CONN_WRAPPER.getConnection();
            PreparedStatement stmnt = conn.prepareStatement(
                "INSERT INTO course"
              + " (course_name, course_description, course_credits)"
              + " VALUES (?, ?, ?)"
            );
            stmnt.setString(1, nuevo.getCourseName());
            stmnt.setString(2, nuevo.getCourseDescription());
            stmnt.setInt(3, nuevo.getCourseCredits());
            stmnt.execute();
        } catch(ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void edit(long id, Course edited) {
        try {
            Connection conn = CONN_WRAPPER.getConnection();
            PreparedStatement stmnt = conn.prepareStatement(
                "UPDATE course SET "
              + " course_name = ?,"
              + " course_description = ?,"
              + " course_credits = ?"
              + " WHERE code = ?"
            );
            stmnt.setString(1, edited.getCourseName());
            stmnt.setString(2, edited.getCourseDescription());
            stmnt.setInt(3, edited.getCourseCredits());
            stmnt.setLong(4, id);
            stmnt.execute();
        } catch(ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try {
            Connection conn = CONN_WRAPPER.getConnection();
            
            PreparedStatement stmnt = conn.prepareStatement(
                    "DELETE FROM course WHERE code = ?"
            );
            stmnt.setLong(1, id);
            stmnt.executeUpdate();
        } catch(ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

}