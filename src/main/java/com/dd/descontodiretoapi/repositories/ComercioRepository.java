package com.dd.descontodiretoapi.repositories;

import com.dd.descontodiretoapi.models.Comercio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComercioRepository extends JpaRepository<Comercio, Long> {
}
