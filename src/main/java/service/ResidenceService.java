package service;

import entity.*;
import repository.ResidenceRepo;

import java.util.List;

public class ResidenceService {

    private ResidenceRepo residenceRepo = new ResidenceRepo();

    public void createNewResidence(User user, String address, String name){

        Residence residence = new Residence();

        residence.setAddress(address);
        residence.setUser(user);
        residence.setResidence_name(name);

        residenceRepo.insertEntry(residence);
    }

    public List<Residence> getAllResidenceByUser(String userId){
            return residenceRepo.queryForAllFromUser(userId);
    }

    public Residence getResidenceByName(String name){
        return residenceRepo.findByName(name);
    }

    public void createResidence(User user, String resName, String resAddress){
        Residence residence = new Residence();
        residence.setUser(user);
        residence.setResidence_name(resName);
        residence.setAddress(resAddress);
        residenceRepo.insertEntry(residence);
    }
}
