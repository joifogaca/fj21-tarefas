package br.com.caelum.jdbc.modelo;

import java.sql.Date;
import java.util.Calendar;

import javax.validation.constraints.Size;



public class Tarefa {

	private Long id;
	
	@Size(min=5)
	private String descricao;
	private boolean finalizado;
	private Calendar dataFinaliza��o;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public boolean isFinalizado() {
		return finalizado;
	}
	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	public Calendar getDataFinaliza��o() {
		return dataFinaliza��o;
	}
	public void setDataFinaliza��o(Calendar dataFinaliza��o) {
		this.dataFinaliza��o = dataFinaliza��o;
	}
	
	

	
}
