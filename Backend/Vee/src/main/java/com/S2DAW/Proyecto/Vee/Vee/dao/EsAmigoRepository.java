package com.S2DAW.Proyecto.Vee.Vee.dao;

import com.S2DAW.Proyecto.Vee.Vee.entity.EsAmigo;
import com.S2DAW.Proyecto.Vee.Vee.entity.EsAmigoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EsAmigoRepository extends JpaRepository<EsAmigo, EsAmigoId> {
}