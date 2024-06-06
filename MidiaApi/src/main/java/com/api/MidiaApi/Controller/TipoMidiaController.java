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

import com.api.MidiaApi.Dto.TipoMidiaDto;
import com.api.MidiaApi.Model.TipoMidiaModel;
import com.api.MidiaApi.Service.TipoMidiaService;

@RestController
@CrossOrigin(origins = "*")
public class TipoMidiaController {

    @Autowired
    TipoMidiaService tipoMidiaService;

    @PostMapping(value = "/tiposmidia")
    public ResponseEntity<Object> createTipoMidia(@RequestBody @Valid TipoMidiaDto tipoMidiaDto) {
        var tipoMidiaModel = new TipoMidiaModel();
        BeanUtils.copyProperties(tipoMidiaDto, tipoMidiaModel);
        tipoMidiaModel.setData_insercao(LocalDate.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoMidiaService.save(tipoMidiaModel));
    }

    @GetMapping(value = "/tiposmidia")
    public ResponseEntity<List<TipoMidiaModel>> getAllTiposMidia() {
        List<TipoMidiaModel> tiposMidia = tipoMidiaService.findAll();
        if (tiposMidia.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(tiposMidia);
        }
        return ResponseEntity.status(HttpStatus.OK).body(tiposMidia);
    }

    @GetMapping(value = "/tipomidia/{id}")
    public ResponseEntity<Object> getTipoMidiaById(@PathVariable("id") UUID id) {
        Optional<TipoMidiaModel> tipoMidia = tipoMidiaService.findById(id);
        if (!tipoMidia.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de mídia not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tipoMidia.get());
    }

    @DeleteMapping(value = "/deletetipomidia/{id}")
    public ResponseEntity<Object> deleteTipoMidia(@PathVariable("id") UUID id) {
        Optional<TipoMidiaModel> tipoMidiaModelOptional = tipoMidiaService.findById(id);
        if (!tipoMidiaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de mídia not found.");
        }
        tipoMidiaService.delete(tipoMidiaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
    }

    @PutMapping(value = "/puttipomidia/{id}")
    public ResponseEntity<Object> updateTipoMidia(@RequestBody @Valid TipoMidiaDto tipoMidiaDto, @PathVariable("id") UUID id) {
        Optional<TipoMidiaModel> tipoMidiaModelOptional = tipoMidiaService.findById(id);
        if (!tipoMidiaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de mídia not found.");
        }
        var tipoMidiaModel = tipoMidiaModelOptional.get();
        BeanUtils.copyProperties(tipoMidiaDto, tipoMidiaModel);
        return ResponseEntity.status(HttpStatus.OK).body(tipoMidiaService.save(tipoMidiaModel));
    }
}
