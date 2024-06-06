package com.api.MidiaApi.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.MidiaApi.Model.TipoMidiaModel;


public interface TipoMidiaRepository extends JpaRepository<TipoMidiaModel, UUID>{

}
