package com.dd.descontodiretoapi.repositories;

import com.dd.descontodiretoapi.models.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Long> {
    
    @Query("SELECT o FROM Oferta o LEFT JOIN o.clientes c GROUP BY o.id ORDER BY COUNT(c) DESC")
    List<Oferta> findTop10ByOrderByLikesDesc();
    
    List<Oferta> findTop10ByOrderByDataPostagemDesc();
}
