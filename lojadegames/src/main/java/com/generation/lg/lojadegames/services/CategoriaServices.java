package com.generation.lg.lojadegames.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.generation.lg.lojadegames.model.Categoria;
import com.generation.lg.lojadegames.repository.CategoriaRepository;

@Service
public class CategoriaServices {

	@Autowired
	private CategoriaRepository repository;
	
	//encontrar todos valores
	public ResponseEntity<List<Categoria>> findAllCategoria(){
		List<Categoria> listaDeCategoria = repository.findAll();
		if(!listaDeCategoria.isEmpty()) {
			return ResponseEntity.status(200).body(listaDeCategoria);
		}
		else {
			return ResponseEntity.status(201).build();
		}
	}
	//encontrar o id
	public ResponseEntity<Categoria> findByIDCategoria(long id){
		return repository.findById(id)
				.map(ident -> ResponseEntity.ok(ident))
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	//encontrar o titulo
	public ResponseEntity<List<Categoria>> findByCategoria(String nome){
		List<Categoria> encontrarCategoria = repository.findAllByNomeContaining(nome);
		if(!encontrarCategoria.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(encontrarCategoria);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	//post
	public Optional<Categoria> postCategoria(Categoria categoria) {
		Optional<Categoria> adicionarCategoria = repository.findByNome(categoria.getNome());
		if(adicionarCategoria.isPresent()) {
			return Optional.empty();
		}else {
			return Optional.ofNullable(repository.save(categoria));
		}
	}
	
	//delete
	public void deleteCategoria(long id) {
		repository.deleteById(id);
	}	
	
	//put
	public Optional<Categoria> putCategoria(Long id, Categoria categoria) {
		Optional<Categoria> atualizar = repository.findById(id);
		if(atualizar.isPresent()) {
			atualizar.get().setNome(categoria.getNome());
			return Optional.ofNullable(repository.save(atualizar.get()));
		}else {
			return Optional.empty();
		}
	}
}
