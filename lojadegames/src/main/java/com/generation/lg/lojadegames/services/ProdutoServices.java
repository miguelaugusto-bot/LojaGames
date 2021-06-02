package com.generation.lg.lojadegames.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.generation.lg.lojadegames.model.Produtos;
import com.generation.lg.lojadegames.repository.ProdutosRepository;

@Service
public class ProdutoServices {

	@Autowired
	private ProdutosRepository repository;
	
	//encontrar todos valores
	public ResponseEntity<List<Produtos>> findAllProdutos(){
		List<Produtos> listaDeProdutos = repository.findAll();
		if(!listaDeProdutos.isEmpty()) {
			return ResponseEntity.status(200).body(listaDeProdutos);
		}
		else {
			return ResponseEntity.status(201).build();
		}
	}
	//encontrar o id
	public ResponseEntity<Produtos> findByIDProduto(long id){
		return repository.findById(id)
				.map(ident -> ResponseEntity.ok(ident))
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	//encontrar o titulo
	public ResponseEntity<List<Produtos>> findByDescricaoTitulo(String nome){
		List<Produtos> encontrarProduto = repository.findAllByNomeContaining(nome);
		if(!encontrarProduto.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(encontrarProduto);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	//post
	public Optional<Produtos> postProdutos(Produtos produto) {
		Optional<Produtos> adicionarProduto = repository.findByNome(produto.getNome());
		if(adicionarProduto.isPresent()) {
			return Optional.empty();
		}else {
			return Optional.ofNullable(repository.save(produto));
		}
	}
	
	//delete
	public void deleteProdutos(long id) {
		repository.deleteById(id);
	}	
	
	//put
	public Optional<Produtos> putProduto(Long id, Produtos produto) {
		Optional<Produtos> atualizar = repository.findById(id);
		if(atualizar.isPresent()) {
			atualizar.get().setNome(produto.getNome());
			atualizar.get().setFaixa(produto.getFaixa());
			return Optional.ofNullable(repository.save(atualizar.get()));
		}else {
			return Optional.empty();
		}
	}
}
