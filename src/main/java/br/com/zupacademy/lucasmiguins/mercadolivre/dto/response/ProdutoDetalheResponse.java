package br.com.zupacademy.lucasmiguins.mercadolivre.dto.response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import br.com.zupacademy.lucasmiguins.mercadolivre.model.Produto;

public class ProdutoDetalheResponse {
	
	private String nome;
	private BigDecimal preco;
	private String descricao;
	private Integer quantidadeDisponivel;
	
	private CategoriaResponse categoria;
	
	private List<String> linksImagens = new ArrayList<>();
	private List<CaracteristicasProdutoDetalheResponse> caracteristicas;
	private List<OpinioesProdutoDetalheResponse> opinioes;
	private List<PerguntasProdutoDetalheResponse> perguntas;
	
	private Integer totalOpinioes;
	private Double mediaNotas;
	
	private HashMap<Integer, Integer> totalPorTipoNota;

	public ProdutoDetalheResponse(Produto produto) {
		
		this.nome = produto.getNome();
		this.preco = produto.getValor();
		this.descricao = produto.getDescricao();
		this.quantidadeDisponivel = produto.getQuantidade();
		
		this.categoria = new CategoriaResponse(produto.getCategoria());
		
		this.linksImagens = produto.getImagens().stream().map(imagem -> imagem.getLink()).collect(Collectors.toList());
		this.caracteristicas = produto.getCaracteristicas().stream().map(caract -> new CaracteristicasProdutoDetalheResponse(caract)).collect(Collectors.toList());
		this.opinioes = produto.getOpinioes().stream().map(opiniao -> new OpinioesProdutoDetalheResponse(opiniao)).collect(Collectors.toList());
		this.perguntas = produto.getPerguntas().stream().map(perg -> new PerguntasProdutoDetalheResponse(perg)).collect(Collectors.toList());
		
		this.totalOpinioes = produto.getTotalOpinioes();
		this.mediaNotas = produto.getMediaOpnioes();
		
		this.totalPorTipoNota = produto.getTotalPorTipoNota();
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	public CategoriaResponse getCategoria() {
		return categoria;
	}

	public List<String> getLinksImagens() {
		return linksImagens;
	}

	public List<CaracteristicasProdutoDetalheResponse> getCaracteristicas() {
		return caracteristicas;
	}

	public List<OpinioesProdutoDetalheResponse> getOpinioes() {
		return opinioes;
	}

	public List<PerguntasProdutoDetalheResponse> getPerguntas() {
		return perguntas;
	}

	public Integer getTotalOpinioes() {
		return totalOpinioes;
	}

	public Double getMediaNotas() {
		return mediaNotas;
	}

	public HashMap<Integer, Integer> getTotalPorTipoNota() {
		return totalPorTipoNota;
	}
}
