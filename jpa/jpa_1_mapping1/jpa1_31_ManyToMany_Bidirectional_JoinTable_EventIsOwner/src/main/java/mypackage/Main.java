package mypackage;

import entities.Event;
import entities.Speaker;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import util.JPAUtil;

public class Main {

    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] a) throws Exception {

        // Delete join table so that you can run the application again
        JPAUtil.droptable("drop table MY_JOIN_TABLE");
        JPAUtil.droptable("drop table SPEAKER_EVENT");
        JPAUtil.droptable("drop table EVENT_SPEAKER");
        JPAUtil.droptable("drop table EVENT");
        JPAUtil.droptable("drop table SPEAKER");

        emf = Persistence.createEntityManagerFactory("pu1");
        em = emf.createEntityManager();

        // Create test data
        CreateTestData.createTestData(em);

        // Get a Event and then navigate to event's speakers
        Event e1 = em.find(Event.class, new Long(1));
        System.out.println("---->Event 1 = " + e1.getName());

        Set<Speaker> speakers = e1.getSpeakers();
        Speaker s1 = (Speaker) speakers.toArray()[0];
        System.out.println("---->First speaker of event 1 = " + s1.getFirstName());

        // Get a Speaker and then navigate to events of the speaker
        Speaker s2 = em.find(Speaker.class, new Long(1));
        System.out.println("---->Speaker 1 = " + s2.getFirstName());

        Set<Event> events = s2.getEvents();
        Event e2 = (Event) events.toArray()[0];
        System.out.println("---->First Event of the speaker 1 = " + e2.getName());

        em.close();
        emf.close();

        // Display the tables
        JPAUtil.checkData("select * from SPEAKER ORDER BY ID");
        JPAUtil.checkData("select * from EVENT ORDER BY ID");
        JPAUtil.checkData("select * from EVENT_SPEAKER ORDER BY EVENTS_ID");

    }
}
