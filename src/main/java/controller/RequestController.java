package controller;

import dto.RequestDto;
import entity.*;

import service.DocumentService;
import service.RequestService;
import service.ResidenceService;
import service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RequestController {

    private RequestService requestService = new RequestService();
    private UserService userService = new UserService();
    private DocumentService documentService = new DocumentService();
    private ResidenceService residenceService = new ResidenceService();


    public List<String[]> fetchTableConfiguredRequestsFromUserByUserId(String userId){
        List<RequestDto> requests = fetchRequestsByUserId(userId);
        List<String[]> tableData = new ArrayList<>();
        for(RequestDto req : requests ){
            String[] row = new String[6];
            row[0] = String.valueOf(req.getRequest_id());
            row[1] = String.valueOf(req.getDocumentName());
            row[2] = String.valueOf(req.getResidenceName());
            row[3] = String.valueOf(req.getDate());
            row[4] = String.valueOf(req.getMax_nb());
            row[5] = String.valueOf(req.getStatus());

            tableData.add(row);
        }
        return tableData;
    }

    public List<RequestDto> fetchRequestsByUserId(String userId){
        if(requestService.fetchAllRequestsByUserId(userId) != null){
            return requestService.fetchAllRequestsByUserId(userId);
        }
        List<RequestDto> bla = new ArrayList<>();
        bla.add(new RequestDto());

        return bla ;
    }

    // Document document, User user, Residence residence)
    public Integer createRequest(String userId, String doc, String residence ){
        Integer curr_year = LocalDate.now().getYear();

        List<Request> requests = requestService.getAllRequestsForDocument(doc);
        for(Request req : requests){
            if(req.getResidence().getResidence_name().equals(residence)
                 && req.getDocument().getType_of_doc().equals(doc)){
                Integer req_year = req.getDate().getYear();
                Integer nb = req.getMax_number();
                if(nb == 0 && req_year.equals(curr_year)){
                    return 0;
                }
                requestService.updateMaxNb(req, nb - 1);
                return 2;
            }
        }


        requestService.createRequest(
                documentService.getDocumentByType(doc),
            userService.getUserById(userId),
            residenceService.getResidenceByName(residence),
            3
        );
        return 1;
    }

    public void deleteRequest(Integer reqId){
            requestService.deleteRequest(reqId);
    }

    public void updateRequest(Integer reqId, String doc, String residence){
            requestService.updateRequest(reqId, doc, residence);
    }

    public void approveRequest(Integer reqId){
        requestService.approveRequest(reqId);
    }
}
