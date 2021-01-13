package Model.Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import Model.Bean.Projeto;
import Model.Connection.Conexao;

public class ProjetoDao {
private Connection connection;
	
	public ProjetoDao(){
		this.connection = new Conexao().getConnection();
	}
	
	// Metodo para Insert no Banco
		public void adiciona(Projeto projeto) {
			String sql = "insert into projeto " + "(nome,dataInicio,dataFinal,valor,risco,participantes)" + 
		" values (?,?,?,?,?,?)";
			try {
				// prepared statement para inserção
				PreparedStatement stmt = connection.prepareStatement(sql);

				// setando os valores no banco de dados
				stmt.setString(1, projeto.getNome());
				stmt.setDate(2, new Date(projeto.getDataInicio().getTimeInMillis()));
				stmt.setDate(3, new Date(projeto.getDataFinal().getTimeInMillis()));
				stmt.setDouble(4, projeto.getValor());
				stmt.setInt(5, projeto.getRisco());
				stmt.setString(6, projeto.getParticipantes());

				// executa
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		// Metodo para Executar uma Select no Banco de dados, buscar todas as informaçoes 
		public List<Projeto> getLista() {
			try {
				List<Projeto> projetos = new ArrayList<Projeto>();
				PreparedStatement stmt = this.connection.prepareStatement("select * from projeto");
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					
					// criando o objeto Projeto
					Projeto projeto = new Projeto();
					projeto.setCodigo(rs.getInt("codigo"));
					projeto.setNome(rs.getString("nome"));
					
					// montando as datas pelo Calendar
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("dataInicio"));
					data.setTime(rs.getDate("dataFinal"));
					projeto.setDataInicio(data);
					projeto.setDataFinal(data);
					projeto.setValor(rs.getDouble("valor"));
					projeto.setRisco(rs.getInt("risco"));
					projeto.setParticipantes(rs.getString("participantes"));
					

					// adicionando o objeto a lista
					projetos.add(projeto);
				
					
				}
				rs.close();
				stmt.close();
				return projetos;

			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		public void altera(Projeto projeto) {
			String sql = "update projeto set nome=?, dataInicio=?, dataFinal=?,valor=?,risco=?," 
		+ " participantes=? where codigo=?";

			try {
				PreparedStatement stmt = connection.prepareStatement(sql);

				stmt.setString(1, projeto.getNome());
				stmt.setDate(2, new Date(projeto.getDataInicio().getTimeInMillis()));
				stmt.setDate(3, new Date(projeto.getDataFinal().getTimeInMillis()));
				stmt.setDouble(4, projeto.getValor());
				stmt.setInt(5, projeto.getRisco());
				stmt.setString(6, projeto.getParticipantes());
				stmt.setInt(7, projeto.getCodigo());
				

				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		public void remove(Projeto projeto) {
			try {
				PreparedStatement stmt = connection.prepareStatement("delete" + "from projeto where codigo=?");
				stmt.setInt(1, projeto.getCodigo());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		public void remove(Integer codigo) {
			try {
				PreparedStatement stmt = connection.prepareStatement("delete from projeto where codigo=?");
				stmt.setInt(1, codigo);
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		public Projeto getProjeto(HttpServletRequest request, HttpServletResponse response)
				throws SerialException, IOException {

			try {
				int codProjeto = Integer.parseInt(request.getParameter("codigo").toString());
				PreparedStatement stmt = this.connection.prepareStatement("Select * From projeto Where codigo =" + 
				codProjeto);
				ResultSet rs = stmt.executeQuery();
				Projeto proj = new Projeto();

				while (rs.next()) {
					
					proj.setCodigo(rs.getInt("codigo"));
					proj.setNome(rs.getString("nome"));
					
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("dataInicio"));
					data.setTime(rs.getDate("dataFinal"));
					proj.setDataInicio(data);
					proj.setDataFinal(data);
					
					proj.setValor(rs.getDouble("valor"));
					proj.setRisco(rs.getInt("risco"));
					proj.setParticipantes(rs.getString("participantes"));

				}

				rs.close();
				stmt.close();
				return proj;

			} catch (SQLException e) {
				throw new RuntimeException("Erro aqui"+e);
			}

		}

}
