package utils;

import entity.Document;
import entity.Request;
import entity.Residence;
import entity.User;

public class CloneObject {

    public static User CloneUser(User src, User dst){
        src.setUser_id(dst.getUser_id());
        src.setUser_type(dst.getUser_type());
        src.setPassword(dst.getPassword());
        src.setEmail(dst.getEmail());
        src.setCreate_time(dst.getCreate_time());
        return dst;
    }

    public static Request CloneRequest(Request src, Request dst){
        src.setRequest_id(dst.getRequest_id());
        src.setUser(dst.getUser());
        src.setMax_number(dst.getMax_number());
        src.setResidence(dst.getResidence());
        src.setDocument(dst.getDocument());
        return dst;
    }

    public static Document CloneDocument(Document src, Document dst){
        src.setDocument_id(dst.getDocument_id());
        src.setType_of_doc(dst.getType_of_doc());
        return dst;
    }

    public static Residence CloneResidence(Residence src, Residence dst){
        src.setResidence_id(dst.getResidence_id());
        src.setAddress(dst.getAddress());
        return dst;
    }




}
