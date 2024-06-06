package com.api.MidiaApi.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.MidiaApi.Model.ClassificacaoModel;
import com.api.MidiaApi.Repository.ClassificacaoRepository;


@Service
public class ClassificacaoService {

    @Autowired
    ClassificacaoRepository classificacaorepository;

    public List<ClassificacaoModel> findAll(){
        return classificacaorepository.findAll();
    }
    public Optional<ClassificacaoModel> findById(UUID id){ 
        return classificacaorepository.findById(id);
    }
    public ClassificacaoModel save(ClassificacaoModel post){ 
        return classificacaorepository.save(post);
    }
    public void delete(ClassificacaoModel post){
        classificacaorepository.delete(post);
    }
}
