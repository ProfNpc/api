package br.belval.api.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.belval.api.model.Produto;
import br.belval.api.repository.ProdutoRepository;

@RestController
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	/**
	 * Retorna todos os produtos
	 * @return
	 */
	@GetMapping("/produtos")
	public ResponseEntity<Iterable<Produto>> obterProdutos() {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(repository.findAll());
	}
	
	//curl POST http://localhost:8080/produtos -H "Content-Type: application/json; Charset=utf-8" -d @produto-pao.json
	@PostMapping("/produtos")
	public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
		
		produto.setDataCriacao(LocalDateTime.now());
		
		System.out.println("Chegou : " + produto.toString());
		
		repository.save(produto);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(produto);
	}
	
	
	@GetMapping("/produtos/{id}")
	public ResponseEntity<Object> buscarPorId(
			@PathVariable(value = "id") Integer id) {
		
		Optional<Produto> produtoOpt = repository.findById(id);
		
		if (produtoOpt.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(produtoOpt.get());
		}
		
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body("Produto não encontrado!");
	}
	
	//curl -X PUT http://localhost:8080/produtos/1 -H "Content-Type: application/json; Charset=utf-8" -d @produto-pao2.json
	@PutMapping("/produtos/{id}")
	public ResponseEntity<Object> atualizarProduto(
			@PathVariable Integer id,
			@RequestBody Produto produto) {
		
		Optional<Produto> produtoOpt = repository.findById(id);
		
		if (produtoOpt.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("Produto não encontrado!");
		}
		
		produto.setId(id);
		produto.setDataCriacao(produtoOpt.get().getDataCriacao());
		
		repository.save(produto);

		return ResponseEntity
				.status(HttpStatus.OK)
				.body("Produto atualizado com sucesso!");
		
	}

	//curl -X DELETE http://localhost:8080/produtos/1
	@DeleteMapping("/produtos/{id}")
	public ResponseEntity<String> apagarProduto(@PathVariable Integer id) {
		
		Optional<Produto> produtoOpt = repository.findById(id);
		
		if (produtoOpt.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("Produto não encontrado!");
		}

		repository.deleteById(id);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body("Produto apagado com sucesso!");
	}	
}
