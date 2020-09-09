package service;

import entity.*;
import repository.DocumentRepo;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class DocumentService {
    private DocumentRepo documentRepo = new DocumentRepo();


    public void createNewDocument(String docType){
        Document newDoc = new Document();

        newDoc.setType_of_doc(docType);
        documentRepo.insertEntry(newDoc);
    }



    public List<String> getAllDocsName(){
        List<Document> documentList = documentRepo.queryForAll();
        List<String> docNames = new ArrayList<>();

        for(Document doc : documentList){
            docNames.add(doc.getType_of_doc());
        }

        return docNames;
    }

    public Document getDocumentByType(String docType){
        return documentRepo.findByType(docType);
    }

    public void deleteDocument(String docType){
        documentRepo.deleteEntry(documentRepo.findByType(docType));
    }
}
