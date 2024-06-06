package com.api.MidiaApi.Controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID; 

import com.api.MidiaApi.Dto.ClassificacaoDto;
import com.api.MidiaApi.Model.ClassificacaoModel;
import com.api.MidiaApi.Service.ClassificacaoService;

@RestController
@CrossOrigin(origins = "*")
public class ClassificacaoController {

    @Autowired
    ClassificacaoService classificacaoService;

    @PostMapping(value = "/newclassificacao")
    public ResponseEntity<Object> saveClassificacao(@RequestBody @Valid ClassificacaoDto classificacaoDto) {
        var classificacaoModel = new ClassificacaoModel();
        BeanUtils.copyProperties(classificacaoDto, classificacaoModel);
        classificacaoModel.setData_insercao(LocalDate.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(classificacaoService.save(classificacaoModel));
    }

    @GetMapping(value = "/classificacoes")
    public ResponseEntity<List<ClassificacaoModel>> getClassificacoes() {
        List<ClassificacaoModel> classificacoes = classificacaoService.findAll();
        if (classificacoes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(classificacoes);
        }
        return ResponseEntity.status(HttpStatus.OK).body(classificacoes);
    }

    @GetMapping(value = "/classificacao/{id}")
    public ResponseEntity<Object> getClassificacaoDetails(@PathVariable("id") UUID id) { 
        Optional<ClassificacaoModel> classificacao = classificacaoService.findById(id);
        if (!classificacao.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classificação not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(classificacao.get());
    }

    @DeleteMapping(value = "/deleteclassificacao/{id}")
    public ResponseEntity<Object> deleteClassificacao(@PathVariable("id") UUID id) { 
        Optional<ClassificacaoModel> classificacaoModelOptional = classificacaoService.findById(id);
        if (!classificacaoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classificação not found.");
        }
        classificacaoService.delete(classificacaoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
    }

    @PutMapping(value = "/putclassificacao/{id}")
    public ResponseEntity<Object> putClassificacao(@RequestBody @Valid ClassificacaoDto classificacaoDto, @PathVariable("id") UUID id) { 
        Optional<ClassificacaoModel> classificacaoModelOptional = classificacaoService.findById(id);
        if (!classificacaoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classificação not found.");
        }
        var classificacaoModel = classificacaoModelOptional.get();
        BeanUtils.copyProperties(classificacaoDto, classificacaoModel);
        return ResponseEntity.status(HttpStatus.OK).body(classificacaoService.save(classificacaoModel));
    }
}
