package entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "document")
public class Document {

    @Id
    @GeneratedValue
    @NotNull
    private Integer document_id;

    @Column
    @NotNull
    private String type_of_doc;



    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
    @NotNull
    private List<Request> request;

    public Document() {
    }

    public Integer getDocument_id() {
        return document_id;
    }

    public void setDocument_id(Integer document_id) {
        this.document_id = document_id;
    }

    public String getType_of_doc() {
        return type_of_doc;
    }

    public void setType_of_doc(String type_of_doc) {
        this.type_of_doc = type_of_doc;
    }

    public List<Request> getRequest() {
        return request;
    }

    public void setRequest(List<Request> request) {
        this.request = request;
    }
}
