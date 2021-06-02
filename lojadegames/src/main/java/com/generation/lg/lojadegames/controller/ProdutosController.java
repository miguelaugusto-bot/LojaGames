package com.generation.lg.lojadegames.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.generation.lg.lojadegames.model.Produtos;
import com.generation.lg.lojadegames.services.ProdutoServices;

@RestController
@RequestMapping("/produtos")
@CrossOrigin("*")
public class ProdutosController {
	

	@Autowired
	private ProdutoServices services;
	
	//findAllProdutos
	@GetMapping("/todos")
	public ResponseEntity<List<Produtos>> getAll(){
		return services.findAllProdutos();
	}
	
	//utilize isPresent ao inves de isEmpty -recomendação do ricardo
	
	//findByIDProduto
	@GetMapping("/id")
	public ResponseEntity<Produtos> getId(@RequestParam(defaultValue = "") long id){
		return services.findByIDProduto(id);
	}
	
	//findByDescricaoTitulo
	@GetMapping("/nome")
	public ResponseEntity<List<Produtos>> getNome(@RequestParam(defaultValue = "") String nome){
		return services.findByDescricaoTitulo(nome);
	}
	
	//postProdutos
	@PostMapping("/adicionar")
	public Optional<Produtos> salvarProduto(@RequestBody Produtos nome) {
		return services.postProdutos(nome);
	}
	
	//putProduto
	@PutMapping("/atualizar")
	public Optional<Produtos> atualizarProduto(@RequestParam(defaultValue = "") long id, @RequestBody Produtos produto) {
		return services.putProduto(id, produto);
	}
	
	
	//deleteProduto
	@DeleteMapping("/deletar/{id}")
	public void deleteProduto(@PathVariable long id) {
		services.deleteProdutos(id);
	}
}
