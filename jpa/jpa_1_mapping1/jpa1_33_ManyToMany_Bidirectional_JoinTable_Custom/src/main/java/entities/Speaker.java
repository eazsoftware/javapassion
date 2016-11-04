package entities;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String firstName;
    private String lastName;

    // Speaker is the owner of the relationship
    @ManyToMany(cascade= CascadeType.ALL)
    // We are customizing Join table
    @JoinTable(name = "my_join_table",
            joinColumns = @JoinColumn(name="my_speaker_id"),
            inverseJoinColumns= @JoinColumn(name="my_event_id"))
    private Set<Event> events;

    public Speaker() {
    }

    public Speaker(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    public Speaker(String firstName, String lastName, Event event) {
        this(firstName, lastName);
        addEvent(event);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set getEvents() {
        return this.events;
    }

    public void setEvents(Set events) {
        this.events = events;
    }

    private void addEvent(Event event) {
        if (events == null) {
            events = new HashSet();
        }
        events.add(event);
    }
}