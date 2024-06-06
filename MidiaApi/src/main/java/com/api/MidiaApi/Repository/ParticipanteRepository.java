package com.api.MidiaApi.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.MidiaApi.Model.ParticipanteModel;


public interface ParticipanteRepository extends JpaRepository<ParticipanteModel, UUID>{

}
