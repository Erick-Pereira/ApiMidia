package com.api.MidiaApi.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.MidiaApi.Model.ParticipanteModel;
import com.api.MidiaApi.Repository.ParticipanteRepository;


@Service
public class ParticipanteService {

    @Autowired
    ParticipanteRepository participanterepository;

    public List<ParticipanteModel> findAll(){
        return participanterepository.findAll();
    }
    public Optional<ParticipanteModel> findById(UUID id){ 
        return participanterepository.findById(id);
    }
    public ParticipanteModel save(ParticipanteModel post){ 
        return participanterepository.save(post);
    }
    public void delete(ParticipanteModel post){ 
        participanterepository.delete(post);
    }
}
