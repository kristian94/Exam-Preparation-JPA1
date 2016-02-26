/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Kristian Nielsen
 */
public class EntityFacade {
    
    
    public void createUser(ProjectUser u, EntityManager em){
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
    }
    
    public ProjectUser findUser(String userName, EntityManager em){
        return em.find(ProjectUser.class, userName);
    }
    
    public List<ProjectUser> getAllUsers(EntityManager em){
        Query query = em.createQuery("Select pu from ProjectUser pu");
        return query.getResultList();
    }
    
    public void createProject(Project p, EntityManager em){
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }
    
    public void assignUserToProject(ProjectUser pu, Project p, EntityManager em){
        em.getTransaction().begin();
        p.addContributor(pu);
        pu.addProject(p);
        em.persist(p);
        em.merge(pu);
        em.getTransaction().commit();
    }
    
    public Project findProject(Long id, EntityManager em){
        return em.find(Project.class, id);
    }
    
    public void createTaskAndAssignToProject(Task t, Project p, EntityManager em){
        p.addTask(t);
        em.getTransaction().begin();
        em.merge(p);
        em.persist(t);
        em.getTransaction().commit();
    }
    
    
}
