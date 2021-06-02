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

import com.generation.lg.lojadegames.model.Categoria;
import com.generation.lg.lojadegames.services.CategoriaServices;

@RestController
@RequestMapping("/categorias")
@CrossOrigin("*")
public class CategoriaController {

	@Autowired
	private CategoriaServices services;
	
	//findAllcategoria
		@GetMapping("/todos")
		public ResponseEntity<List<Categoria>> getAll(){
			return services.findAllCategoria();
		}
		
		//utilize isPresent ao inves de isEmpty -recomendação do ricardo
		
		//findByIDCategoria
		@GetMapping("/id")
		public ResponseEntity<Categoria> getId(@RequestParam(defaultValue = "") long id){
			return services.findByIDCategoria(id);
		}
		
		//findByCategoriaTitulo
		@GetMapping("/nome")
		public ResponseEntity<List<Categoria>> getNome(@RequestParam(defaultValue = "") String nome){
			return services.findByCategoria(nome);
		}
		
		//postCategoria
		@PostMapping("/adicionar")
		public Optional<Categoria> salvarCategoria(@RequestBody Categoria nome) {
			return services.postCategoria(nome);
		}
		
		//putCategoria
		@PutMapping("/atualizar")
		public Optional<Categoria> atualizarCategoria(@RequestParam(defaultValue = "") long id, @RequestBody Categoria categoria) {
			return services.putCategoria(id, categoria);
		}
		
		
		//deleteCategoria
		@DeleteMapping("/deletar/{id}")
		public void deleteCategoria(@PathVariable long id) {
			services.deleteCategoria(id);
		}
}
