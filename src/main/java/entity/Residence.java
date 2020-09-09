package entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.swing.event.DocumentEvent;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="residence")
public class Residence {
    @Id
    @NotNull
    @GeneratedValue
    private Integer residence_id;

    @Column
    @NotNull
    private String address;

    @Column
    @NotNull
    private String residence_name;

    @ManyToOne
    @JoinColumn(name="user_id")
    @NotNull
    private User user;

    @OneToMany(mappedBy = "residence", cascade = CascadeType.ALL, orphanRemoval = true)
    @NotNull
    private List<Request> requestList;

    public Integer getResidence_id() {
        return residence_id;
    }

    public void setResidence_id(Integer residence_id) {
        this.residence_id = residence_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getResidence_name() {
        return residence_name;
    }

    public void setResidence_name(String residence_name) {
        this.residence_name = residence_name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<Request> requestList) {
        this.requestList = requestList;
    }
}
