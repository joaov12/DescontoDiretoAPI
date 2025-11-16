package com.dd.descontodiretoapi.repositories;

import com.dd.descontodiretoapi.models.Comercio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComercioRepository extends JpaRepository<Comercio, Long> {

    Optional<Comercio> findByEmail(String email);

}
