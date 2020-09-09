package controller;

import entity.Document;
import service.DocumentService;
import java.util.List;

public class DocumentController {

    private DocumentService documentService = new DocumentService();

    public String[] getDocNames(){
        List<String> documentList = documentService.getAllDocsName();
        String[] docNames = new String[documentList.size()];
        int i= 0;
        for(String s: documentList){
            docNames[i++] = s;
        }
        return docNames;
    }

    public void createDocument(String docType){
        documentService.createNewDocument(docType);
    }
    public void deleteDocument(String doc){
        documentService.deleteDocument(doc);
    }
 }
