package com.dd.descontodiretoapi.repositories;

import com.dd.descontodiretoapi.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
