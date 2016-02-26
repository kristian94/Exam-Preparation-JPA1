/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examprepjpa1;

import entity.EntityFacade;
import entity.Project;
import entity.ProjectUser;
import entity.Task;
import java.time.Instant;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Kristian Nielsen
 */
public class ExamPrepJPA1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamPrepJPA1PU");
        EntityManager em = emf.createEntityManager();
        EntityFacade ef = new EntityFacade();
        
        ProjectUser pu = new ProjectUser();
        pu.setUserName("Ham Den Seje");
        pu.setEmail("hey_yo@hotmail.com");
        pu.setCreated(Date.from(Instant.EPOCH));
        ef.createUser(pu , em);
        
        Project p = new Project();
        p.setName("X");
        p.setDescription("A study on monkeys");
        p.setCreated(Date.from(Instant.EPOCH));    
        p.setLastModified(Date.from(Instant.EPOCH));
        ef.assignUserToProject(pu, p, em);
        
        Task t = new Task();
        t.setName("Running a marathon");
        t.setDescription("hey");
        t.setHoursAssigned(2);
        ef.createTaskAndAssignToProject(t, p, em);
        
        System.out.println(ef.getAllUsers(em).get(0).getUserName());
    }
    
}
