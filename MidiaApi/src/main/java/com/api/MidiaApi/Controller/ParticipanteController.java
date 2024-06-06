package com.api.MidiaApi.Controller;

import com.api.MidiaApi.Dto.ParticipanteDto;

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

import com.api.MidiaApi.Model.ParticipanteModel;
import com.api.MidiaApi.Service.ParticipanteService;



@RestController
@CrossOrigin(origins = "*")
public class ParticipanteController {

    @Autowired
    ParticipanteService participanteService;

    @PostMapping(value = "/participantes")
    public ResponseEntity<Object> createParticipante(@RequestBody @Valid ParticipanteDto participanteDto) {
        var participanteModel = new ParticipanteModel();
        BeanUtils.copyProperties(participanteDto, participanteModel);
        participanteModel.setData_insercao(LocalDate.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(participanteService.save(participanteModel));
    }

    @GetMapping(value = "/participantes")
    public ResponseEntity<List<ParticipanteModel>> getAllParticipantes() {
        List<ParticipanteModel> participantes = participanteService.findAll();
        if (participantes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(participantes);
        }
        return ResponseEntity.status(HttpStatus.OK).body(participantes);
    }

    @GetMapping(value = "/participante/{id}")
    public ResponseEntity<Object> getParticipanteById(@PathVariable("id") UUID id) {
        Optional<ParticipanteModel> participante = participanteService.findById(id);
        if (!participante.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Participante not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(participante.get());
    }

    @DeleteMapping(value = "/deleteparticipante/{id}")
    public ResponseEntity<Object> deleteParticipante(@PathVariable("id") UUID id) {
        Optional<ParticipanteModel> participanteModelOptional = participanteService.findById(id);
        if (!participanteModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Participante not found.");
        }
        participanteService.delete(participanteModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
    }

    @PutMapping(value = "/putparticipante/{id}")
    public ResponseEntity<Object> updateParticipante(@RequestBody @Valid ParticipanteDto participanteDto, @PathVariable("id") UUID id) {
        Optional<ParticipanteModel> participanteModelOptional = participanteService.findById(id);
        if (!participanteModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Participante not found.");
        }
        var participanteModel = participanteModelOptional.get();
        BeanUtils.copyProperties(participanteDto, participanteModel);
        return ResponseEntity.status(HttpStatus.OK).body(participanteService.save(participanteModel));
    }
}
