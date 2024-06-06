package com.api.MidiaApi.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.MidiaApi.Model.MidiaModel;
import com.api.MidiaApi.Repository.MidiaRepository;

@Service
public class MidiaService {

    @Autowired
    MidiaRepository midiaRepository;

    public List<MidiaModel> findAll(){
        return midiaRepository.findAll();
    }
    public Optional<MidiaModel> findById(UUID id){ 
        return midiaRepository.findById(id);
    }
    public MidiaModel save(MidiaModel post){ 
        return midiaRepository.save(post);
    }
    public void delete(MidiaModel post){
        midiaRepository.delete(post);
    }

}
