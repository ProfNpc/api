package br.belval.api.model;

//Add import com Ctrl+SHIFT+O
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;

/**
 * Corresponde à tabela produto
 */
@Entity
public class Produto {
	
	private Integer id;
	private String nome;
	private String descricao;
	private BigDecimal preco;//valores decimais como 12.34
	private LocalDateTime dataCriacao;
	
	/**
	 * Precisamos criar:
	 * 1 - Construtor padrão(sem parâmetros) e publico - OK
	 * 2 - Métodos getters e setters - OK
	 * 3 - Criar os métodos hashCode() e equals()
	 * 4 - Método toString() : representação textual do conteúdo do objeto
	 */

	/*
	 // método qualquer
	public int calcularQQcoisa(String param1, String param2) {
	   ^    ^       ^          ^---------------------------^
	   |    |       |                      |----------Lista de parâmetros
	   |    |       |------nome do método
	   |    |------- tipo de retorno
	   |--------visibilidade     
		return 0;
	}
	*/
	
	/**
	 * Método construtor é aquele que:
	 * 1 - Tem o mesmo nome da classe
	 * 2 - Não tem tipo de retorno(nem void)
	 * 
	 * Método construtor padrão
	 * 3 - Lista de parâmetros vazia 
	 */
	public Produto() {
		
	}
	
	//Método que recupera o valor do atributo id
	public Integer getId() {
		return this.id;
	}
	
	//Método que define/altera o valor do atributo id
	public void setId(Integer id) {
		this.id = id;
	}

	//Para criar os getters e os setters podemos utilizar o atalho da IDE
	//Alt + SHIFT + S >> "Generate getters and setters"
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	//Para criar o hashCode() e o equals() também temo um atalho
	//Alt + SHIFT + S >> "Generate hashCode() and equals()"
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}

	
	//Para facilitar a identificação do objeto em um momento de 
	//debug, precisamos de uma representação textual que nos permita 
	//identificar qual objeto é aquele, para isso vamos sobreescrever
	//o método toString()
	
	//Atalho:Alt + SHIFT + S >> Generate toString()
	
	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", preco=" + preco
				+ ", dataCriacao=" + dataCriacao + "]";
	}
	
}
