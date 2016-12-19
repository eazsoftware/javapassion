package examples.model;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;


public class ManagerService {
    protected EntityManager em;

    public ManagerService(EntityManager em) {
        this.em = em;
    }

    public Manager createManager(int id, String name, long salary, String department) {
        Manager emp = new Manager();
        emp.setId(id);
        emp.setName(name);
        emp.setSalary(salary);
        emp.setDepartment(department);
        em.persist(emp);
        return emp;
    }

    public void removeManager(int id) {
        Manager emp = findManager(id);
        if (emp != null) {
            em.remove(emp);
        }
    }

    public Manager raiseManagerSalary(int id, long raise) {
        Manager emp = em.find(Manager.class, id);
        if (emp != null) {
            emp.setSalary(emp.getSalary() + raise);
        }
        return emp;
    }

    public Manager findManager(int id) {
        return em.find(Manager.class, id);
    }

    public Collection<Manager> findAllManagers() {
        Query query = em.createQuery("SELECT e FROM Manager e");
        return (Collection<Manager>) query.getResultList();
    }
}
