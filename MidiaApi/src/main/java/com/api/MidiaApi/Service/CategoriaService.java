package com.api.MidiaApi.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.MidiaApi.Model.CategoriaModel;
import com.api.MidiaApi.Repository.CategoriaRepository;


@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;

    public List<CategoriaModel> findAll(){// vai retornar uma lista de Categorias
        return categoriaRepository.findAll();
    }
    public Optional<CategoriaModel> findById(UUID id){ // vai retornar um unico Categoria passando o id
        return categoriaRepository.findById(id);
    }
    public CategoriaModel save(CategoriaModel post){ // vai salvar um Categoria no Banco
        return categoriaRepository.save(post);
    }
    public void delete(CategoriaModel post){ // vai excluir um Categoria
        categoriaRepository.delete(post);
    }
}
