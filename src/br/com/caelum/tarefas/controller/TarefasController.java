package br.com.caelum.tarefas.controller;

import java.sql.Connection;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.jdbc.dao.TarefaDAO;
import br.com.caelum.jdbc.modelo.Tarefa;

@Controller
public class TarefasController {
	
	@RequestMapping("novaTarefa")
	public String form()
	{
		return "tarefa/formulario";
	}
	
	@RequestMapping("adicionaTarefa")
	public String adiciona(@Valid Tarefa tarefa, BindingResult result) throws ClassNotFoundException
	{
		if(result.hasFieldErrors("descricao"))
		{
		return "tarefa/formulario";
	}
		
		TarefaDAO dao = new TarefaDAO();
		dao.adciona(tarefa);
		
		return "tarefa/adicionada";
	}

}
