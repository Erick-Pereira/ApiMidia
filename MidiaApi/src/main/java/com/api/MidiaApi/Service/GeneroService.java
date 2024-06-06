package com.api.MidiaApi.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.MidiaApi.Model.GeneroModel;
import com.api.MidiaApi.Repository.GeneroRepository;

@Service
public class GeneroService {
    
    @Autowired
    GeneroRepository generoRepository;

    public List<GeneroModel> findAll(){
        return generoRepository.findAll();
    }
    public Optional<GeneroModel> findById(UUID id){ 
        return generoRepository.findById(id);
    }
    public GeneroModel save(GeneroModel post){ 
        return generoRepository.save(post);
    }
    public void delete(GeneroModel post){
        generoRepository.delete(post);
    }
}
