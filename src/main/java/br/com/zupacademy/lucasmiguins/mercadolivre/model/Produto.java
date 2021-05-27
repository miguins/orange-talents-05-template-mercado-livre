package br.com.zupacademy.lucasmiguins.mercadolivre.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.lucasmiguins.mercadolivre.dto.request.NovaCaracteristicaProdutoRequest;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotNull
	@Positive
	private BigDecimal valor;
	
	@NotNull
	@PositiveOrZero
	private Integer quantidade;
	
	private LocalDateTime dataCriacao;
	
	@NotBlank
	@Length(max = 1000)
	private String descricao;
	
	@NotNull
	@ManyToOne
	private Categoria categoria;
	
	@Size(min = 3)
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();
	
	@NotNull
	@ManyToOne
	private Usuario usuarioCriacao;
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<ImagemProduto> imagens = new HashSet<>();
	
	@OneToMany(mappedBy = "produto")
    private Set<Opiniao> opinioes = new HashSet<>();

    @OneToMany(mappedBy = "produto")
    private Set<PerguntaProduto> perguntas = new HashSet<>();
	

	@Deprecated
	public Produto() {}

	public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
			@NotNull @PositiveOrZero Integer quantidade, @NotBlank @Length(max = 1000) String descricao,
			@NotNull Categoria categoria, @Size(min = 3) Collection<NovaCaracteristicaProdutoRequest> caracteristicas, @NotNull Usuario usuarioLogado) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.usuarioCriacao = usuarioLogado;

		this.dataCriacao = LocalDateTime.now();
		this.caracteristicas = caracteristicas.stream().map(c -> c.toModel(this)).collect(Collectors.toSet());
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Set<CaracteristicaProduto> getCaracteristicas() {
		return caracteristicas;
	}

	public Usuario getUsuarioCriacao() {
		return usuarioCriacao;
	}

	public Set<ImagemProduto> getImagens() {
		return imagens;
	}
	
	public Set<Opiniao> getOpinioes() {
		return opinioes;
	}

	public Set<PerguntaProduto> getPerguntas() {
		return perguntas;
	}
	
	public Integer getTotalOpinioes() {
		return this.getOpinioes().size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
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
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public void adicionaImagens(Set<String> links) {
		
		Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link)).collect(Collectors.toSet());
		this.imagens.addAll(imagens);
	}
	
	public boolean pertenceAoUsuario(Usuario usuarioLogado) {
		return this.usuarioCriacao.equals(usuarioLogado);
	}
	
	public Double getMediaOpnioes() {
		Double mediaNota = 0.0;
		
		List<Integer> notas = this.getOpinioes().stream().map(o -> o.getNota()).collect(Collectors.toList());
		OptionalDouble average = notas.stream().mapToInt(nota -> nota).average();
		
		if (average.isPresent()) {
			mediaNota = average.getAsDouble();
		}
		
		double mediaArredondada = Math.round(mediaNota * 10.0) / 10.0;
		
		return mediaArredondada;
	}
	
	public HashMap<Integer, Integer> getTotalPorTipoNota() {
		
		HashMap<Integer, Integer> mapaNotas = new HashMap<Integer, Integer>();
		
		List<Integer> notas = this.getOpinioes().stream().map(o -> o.getNota()).collect(Collectors.toList());
		
		for (Integer valor : notas) {
			
			if (mapaNotas.containsKey(valor))
				mapaNotas.put(valor, mapaNotas.get(valor) + 1);
			 else 
				mapaNotas.put(valor, 1);
		}
		
		return mapaNotas;
	}
}
