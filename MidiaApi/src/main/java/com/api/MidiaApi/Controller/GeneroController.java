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
import org.springframework.web.bind.annotation.*;

import com.api.MidiaApi.Dto.GeneroDto;
import com.api.MidiaApi.Model.GeneroModel;
import com.api.MidiaApi.Service.GeneroService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class GeneroController {

    @Autowired
    GeneroService generoService;

    @PostMapping(value = "/newgenero")
    public ResponseEntity<Object> saveGenero(@RequestBody @Valid GeneroDto generoDto) {
        var generoModel = new GeneroModel();
        BeanUtils.copyProperties(generoDto, generoModel);
        generoModel.setData_insercao(LocalDate.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(generoService.save(generoModel));
    }

    @GetMapping(value = "/generos")
    public ResponseEntity<List<GeneroModel>> getClassificacoes() {
        List<GeneroModel> generos = generoService.findAll();
        if (generos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(generos);
        }
        return ResponseEntity.status(HttpStatus.OK).body(generos);
    }

    @GetMapping(value = "/genero/{id}")
    public ResponseEntity<Object> getGeneroDetails(@PathVariable("id") UUID id) { 
        Optional<GeneroModel> genero = generoService.findById(id);
        if (!genero.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classificação not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(genero.get());
    }

    @DeleteMapping(value = "/deletegenero/{id}")
    public ResponseEntity<Object> deleteGenero(@PathVariable("id") UUID id) { 
        Optional<GeneroModel> generoModelOptional = generoService.findById(id);
        if (!generoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classificação not found.");
        }
        generoService.delete(generoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
    }

    @PutMapping(value = "/putgenero/{id}")
    public ResponseEntity<Object> putGenero(@RequestBody @Valid GeneroDto generoDto, @PathVariable("id") UUID id) { 
        Optional<GeneroModel> generoModelOptional = generoService.findById(id);
        if (!generoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classificação not found.");
        }
        var generoModel = generoModelOptional.get();
        BeanUtils.copyProperties(generoDto, generoModel);
        return ResponseEntity.status(HttpStatus.OK).body(generoService.save(generoModel));
    }
}
