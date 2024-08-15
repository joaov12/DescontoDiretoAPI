package com.dd.descontodiretoapi.repositories;

import com.dd.descontodiretoapi.models.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Long> {
}
