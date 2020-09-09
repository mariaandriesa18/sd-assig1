package service;

import dto.RequestDto;
import entity.Document;
import entity.Request;
import entity.Residence;
import entity.User;
import mappers.RequestMapper;
import repository.RequestRepo;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RequestService {

    public RequestService() {
    }

    private RequestRepo requestRepo = new RequestRepo();
    public DocumentService documentService = new DocumentService();
    public ResidenceService residenceService = new ResidenceService();

    public ArrayList<RequestDto> fetchAllRequestsByUserId(String userId){
            List<Request> requests = requestRepo.queryForAllByUserId(userId);
            ArrayList<RequestDto> requestDtos = new ArrayList<>();

            for(Request req : requests){
                requestDtos.add(RequestMapper.convertToRequestDto(req));
            }
            return requestDtos;
    }

    public void createRequest(Document document, User user, Residence residence, Integer max_nb){

        Request request = new Request();
        request.setDocument(document);
        request.setUser(user);
        request.setDate(LocalDate.now());
        request.setMax_number(max_nb);
        request.setResidence(residence);
        request.setStatus("Waiting Approval");

        requestRepo.insertEntry(request);
    }

    public List<Request> getAllRequestsForDocument(String doc){
          return  requestRepo.querryForAllWithDocument(doc);
    }
    @Transactional
    public void deleteRequest(Integer reqId){
        Request request = requestRepo.findByInt(reqId);
        requestRepo.deleteEntry(request);
    }

    @Transactional
    public void updateRequest(Integer reqId, String doc, String residence){
        Request request = requestRepo.findByInt(reqId);
        request.setDocument(documentService.getDocumentByType(doc));
        request.setResidence(residenceService.getResidenceByName(residence));
        requestRepo.updateEntry(request);
    }

    @Transactional
    public void approveRequest( Integer reqId){
        Request request = requestRepo.findByInt(reqId);
        request.setStatus("Approved");
        requestRepo.updateEntry(request);
    }

    @Transactional
    public void updateMaxNb(Request req, Integer maxNb){
        req.setMax_number(maxNb);
        requestRepo.updateEntry(req);
    }

    public Request getRequestByDoctype (String docType){
         return requestRepo.findRequestByDocument(docType);
    }
}
