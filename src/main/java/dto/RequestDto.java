package dto;

import java.time.LocalDate;

public class RequestDto {

    private String request_id;
    private String documentName;
    private String residenceName;
    private String status;
    private String date;
    private String max_nb;

    public RequestDto() {
    }


    public RequestDto(String request_id, String type_of_doc, String residence_name, String prettyDate, String  max_nb, String status) {
        this.request_id = request_id;
        this.documentName = type_of_doc;
        this.residenceName = residence_name;
        this.status = status;
        this.date = prettyDate;
        this.max_nb =max_nb;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getResidenceName() {
        return residenceName;
    }

    public void setResidenceName(String residenceName) {
        this.residenceName = residenceName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMax_nb() {
        return max_nb;
    }

    public void setMax_nb(String max_nb) {
        this.max_nb = max_nb;
    }
}
