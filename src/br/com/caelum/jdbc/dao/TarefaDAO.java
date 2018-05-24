package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.jdbc.modelo.Tarefa;

public class TarefaDAO {

	
private Connection connection;
	
	public TarefaDAO(Connection conn) {
		
			this.connection = conn;
	}
	
	public void adciona(Tarefa tarifa){
		String sql = "insert into tarifas "+
				"(descricao, finalizado, dataFinalizacao)" +
				"values(?,?,?)";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, tarifa.getDescricao());
			stmt.setBoolean(2, tarifa.isFinalizado());
			stmt.setDate(3, new Date(tarifa.getDataFinalização().getTimeInMillis()));
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Tarefa> getLista(){
		
		try {
			List<Tarefa> tarefas = new ArrayList<Tarefa>();
			PreparedStatement stmt = this.connection.prepareStatement("Select * from tarefas");
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Tarefa tarefa = new Tarefa();
				tarefa.setId(rs.getLong("id"));
				tarefa.setDescricao(rs.getString("descricao"));
				tarefa.setFinalizado(rs.getBoolean("finalizado"));
				
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataFinalizacao"));
				tarefa.setDataFinalização(data);
				
				tarefas.add(tarefa);
				
			}
			rs.close();
			stmt.close();
			return tarefas;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	public Tarefa findById(Long Id) throws SQLException
	{
		Tarefa tarifa = new Tarefa();
		
		PreparedStatement stmt = this.connection.prepareStatement("Select * from tarifas where id =?");
		stmt.setLong(1, Id);
		
		ResultSet rs = stmt.executeQuery();
		
		rs.next();
		tarifa.setId(rs.getLong("id"));
		tarifa.setDescricao(rs.getString("descricao"));
		tarifa.setFinalizado(rs.getBoolean("finalizado"));
		
		Calendar data = Calendar.getInstance();
		data.setTime(rs.getDate("dataNascimento"));
		tarifa.setDataFinalização(data);
		
		stmt.close();
		rs.close();
		
		return tarifa;
	}

	public void update(Tarefa tarifa) throws SQLException
	{
		PreparedStatement stmt = this.connection.prepareStatement("update tarifas set descricao=?, finalizado=?, dataFinalizacao=?, "
	+" where id=?");
		stmt.setString(1,tarifa.getDescricao());
		stmt.setBoolean(2,tarifa.isFinalizado());
		stmt.setDate(3, new Date(tarifa.getDataFinalização().getTimeInMillis()));
		
		stmt.execute();
		stmt.close();
	}

	public void remove(Tarefa tarefa)
	{
		try {
			PreparedStatement stmt = connection.prepareStatement("delete from tarefas where id=?");
			stmt.setLong(1, tarefa.getId());
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

}
