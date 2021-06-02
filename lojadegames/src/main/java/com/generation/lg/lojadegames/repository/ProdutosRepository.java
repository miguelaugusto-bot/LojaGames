package com.generation.lg.lojadegames.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.generation.lg.lojadegames.model.Produtos;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long>{
	
	List<Produtos> findAllByNomeContaining(String nome);
	
	Optional<Produtos> findByNome(String nome);
}
