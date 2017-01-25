package listeners;

import entities.Employee;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class AlertMonitor {

    @PrePersist
    public void validateCreate(Employee e) {
        System.out.println("----> AlertMonitor: validateCreate() gets called " + e.getName());
    }
    
    @PostPersist
    public void postPersist(Employee e) {
        System.out.println("----> AlertMonitor: postPersist() gets called " + e.getName());
    }     
    
    @PreRemove
    public void preRemove(Employee e) {
        System.out.println("----> AlertMonitor: preRemove() gets called " + e.getName());
    }  
    
    @PostRemove
    public void postRemove(Employee e) {
        System.out.println("----> AlertMonitor: postPersist() gets called " + e.getName());
    }    
        
    @PreUpdate
    public void preUpdate(Employee e) {
        System.out.println("----> AlertMonitor: preUpdate() gets called " + e.getName());
    }      
    
    @PostUpdate
    public void postUpdate(Employee e) {
        System.out.println("----> AlertMonitor: postUpdate() gets called " + e.getName());
    }
    
    /**
     * The method annotated with @PostLoad is called after fetching data using 
     * entity manager find() method in persistence context or refreshing it with 
     * database by using refresh() method. 
     * If the entity instance is already loaded in persistence context, 
     * then calling of find() method will not call @PostLoad. 
     * @param e 
     */
    @PostLoad
    public void postLoad(Employee e) {
        System.out.println("----> AlertMonitor: postLoad() gets called " + e.getName());
    }     
    
}
