package com.api.MidiaApi.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.MidiaApi.Model.TipoMidiaModel;
import com.api.MidiaApi.Repository.TipoMidiaRepository;



@Service
public class TipoMidiaService {

    @Autowired
    TipoMidiaRepository tipomidiarepository;

    public List<TipoMidiaModel> findAll(){
        return tipomidiarepository.findAll();
    }
    public Optional<TipoMidiaModel> findById(UUID id){ 
        return tipomidiarepository.findById(id);
    }
    public TipoMidiaModel save(TipoMidiaModel post){ 
        return tipomidiarepository.save(post);
    }
    public void delete(TipoMidiaModel post){ 
        tipomidiarepository.delete(post);
    }
}
