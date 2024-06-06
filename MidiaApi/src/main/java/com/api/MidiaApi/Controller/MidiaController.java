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


import com.api.MidiaApi.Dto.MidiaDto;
import com.api.MidiaApi.Model.MidiaModel;
import com.api.MidiaApi.Service.MidiaService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class MidiaController {

      @Autowired
    MidiaService midiaService;

    @PostMapping(value = "/newmidia")
    public ResponseEntity<Object> saveMidia(@RequestBody @Valid MidiaDto midiaDto) {
        var midiaModel = new MidiaModel();
        BeanUtils.copyProperties(midiaDto, midiaModel);
        midiaModel.setData_insercao(LocalDate.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(midiaService.save(midiaModel));
    }

    @GetMapping(value = "/midias")
    public ResponseEntity<List<MidiaModel>> getClassificacoes() {
        List<MidiaModel> midias = midiaService.findAll();
        if (midias.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(midias);
        }
        return ResponseEntity.status(HttpStatus.OK).body(midias);
    }

    @GetMapping(value = "/midia/{id}")
    public ResponseEntity<Object> getMidiaDetails(@PathVariable("id") UUID id) { 
        Optional<MidiaModel> midia = midiaService.findById(id);
        if (!midia.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classificação not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(midia.get());
    }

    @DeleteMapping(value = "/deletemidia/{id}")
    public ResponseEntity<Object> deleteMidia(@PathVariable("id") UUID id) { 
        Optional<MidiaModel> midiaModelOptional = midiaService.findById(id);
        if (!midiaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classificação not found.");
        }
        midiaService.delete(midiaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
    }

    @PutMapping(value = "/putmidia/{id}")
    public ResponseEntity<Object> putMidia(@RequestBody @Valid MidiaDto midiaDto, @PathVariable("id") UUID id) { 
        Optional<MidiaModel> midiaModelOptional = midiaService.findById(id);
        if (!midiaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classificação not found.");
        }
        var midiaModel = midiaModelOptional.get();
        BeanUtils.copyProperties(midiaDto, midiaModel);
        return ResponseEntity.status(HttpStatus.OK).body(midiaService.save(midiaModel));
    }
}
