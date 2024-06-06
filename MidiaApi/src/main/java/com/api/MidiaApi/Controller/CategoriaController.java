package com.api.MidiaApi.Controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.MidiaApi.Dto.CategoriaDto;
import com.api.MidiaApi.Model.CategoriaModel;
import com.api.MidiaApi.Service.CategoriaService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class CategoriaController {
 @Autowired
    CategoriaService categoriaService;

    @PostMapping(value = "/newcategoria")
    public ResponseEntity<Object> saveCategoria(@RequestBody @Valid CategoriaDto categoriaDto) {
        var categoriaModel = new CategoriaModel();
        BeanUtils.copyProperties(categoriaDto, categoriaModel);
        categoriaModel.setData_insercao(LocalDate.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.save(categoriaModel));
    }

    @GetMapping(value = "/categoria")
    public ResponseEntity<List<CategoriaModel>> getClassificacoes() {
        List<CategoriaModel> categorias = categoriaService.findAll();
        if (categorias.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(categorias);
        }
        return ResponseEntity.status(HttpStatus.OK).body(categorias);
    }

    @GetMapping(value = "/categoria/{id}")
    public ResponseEntity<Object> getCategoriaDetails(@PathVariable("id") UUID id) { 
        Optional<CategoriaModel> categoria = categoriaService.findById(id);
        if (!categoria.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classificação not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(categoria.get());
    }

    @DeleteMapping(value = "/deletecategoria/{id}")
    public ResponseEntity<Object> deleteCategoria(@PathVariable("id") UUID id) { 
        Optional<CategoriaModel> categoriaModelOptional = categoriaService.findById(id);
        if (!categoriaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classificação not found.");
        }
        categoriaService.delete(categoriaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
    }

    @PutMapping(value = "/putcategoria/{id}")
    public ResponseEntity<Object> putCategoria(@RequestBody @Valid CategoriaDto categoriaDto, @PathVariable("id") UUID id) { 
        Optional<CategoriaModel> categoriaModelOptional = categoriaService.findById(id);
        if (!categoriaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classificação not found.");
        }
        var categoriaModel = categoriaModelOptional.get();
        BeanUtils.copyProperties(categoriaDto, categoriaModel);
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.save(categoriaModel));
    }
}
