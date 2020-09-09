package entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.swing.event.DocumentEvent;
import java.time.LocalDate;


@Entity
@Table(name="request")
public class Request {

    @Id
    @NotNull
    @GeneratedValue
    private Integer request_id;

    @Column
    @NotNull
    private LocalDate date;

    @Column
    @NotNull
    private Integer max_number;

    @Column
    @NotNull
    private String status; // APPROVED / REJECTED

    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "document_id")
    @NotNull
    private Document document;


    @ManyToOne
    @JoinColumn(name = "residence_id")
    @NotNull
    private Residence residence;


    public Integer getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Integer request_id) {
        this.request_id = request_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getMax_number() {
        return max_number;
    }

    public void setMax_number(Integer max_number) {
        this.max_number = max_number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Residence getResidence() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
    }
}
