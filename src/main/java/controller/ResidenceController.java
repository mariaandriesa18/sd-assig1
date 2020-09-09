package controller;

import dto.RequestDto;
import entity.Residence;
import entity.User;
import service.ResidenceService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ResidenceController {

    private ResidenceService residenceService = new ResidenceService();

    public List<String[]> fetchTableConfiguredResidences(String uid){
        List<Residence> residenceList = residenceService.getAllResidenceByUser(uid);
        List<String[]> tableData = new ArrayList<>();
        for(Residence res : residenceList ){
            String[] row = new String[3];
            row[0] = String.valueOf(res);
            row[1] = String.valueOf(res.getResidence_id());
            row[2] = String.valueOf(res.getResidence_name());

            tableData.add(row);
        }
        return tableData;
    }

    public List<Residence> fetchAllResidenceFromUser(String uid){
        return residenceService.getAllResidenceByUser(uid);
    }

    public String[] fetchResidenceNamesForComboBox(String uid){
        List<Residence> residenceList = fetchAllResidenceFromUser(uid);
        String[] residenceNames = new String[residenceList.size()];

        int i = 0;
        for(Residence res : residenceList){
            residenceNames[i++] = res.getResidence_name();
        }

        return residenceNames;
    }

    public void createResidence(User user, String resName, String resAddress){
        residenceService.createResidence(user,resName, resAddress);
    }
}
