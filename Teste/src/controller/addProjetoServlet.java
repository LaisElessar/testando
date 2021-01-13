package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Bean.Projeto;
import Model.Dao.ProjetoDao;

/**
 * Servlet implementation class addProjetoServlet
 */
@WebServlet("/addProjetoServlet")
public class addProjetoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	protected void service(HttpServletRequest request, 
			HttpServletResponse response)
			throws IOException, ServletException {

		//Busca o writer 
		PrintWriter out = response.getWriter();

		//Busscando os par√¢metros do request
		String nome = request.getParameter("nome");
		String dataInicioTexto = request.getParameter("dataInicial");
		String dataFinalTexto = request.getParameter("dataFinal");
		Double valor = Double.parseDouble(request.getParameter("valor"));
		Integer risco = Integer.parseInt(request.getParameter("risco"));
		String participantes = request.getParameter("participantes");
		
		//Convers„o da data de dd/mm/yyy para yyy-mm-dd
		Calendar dataInicio = null;
		try {
			Date date = new SimpleDateFormat("yyy-MM-dd").parse(dataInicioTexto);
			dataInicio = Calendar.getInstance();
			dataInicio.setTime(date);
		} catch (ParseException e) {
			out.println("Erro de conversao de data da Data inicial no Servlet");
			return;
		}
		
		
		Calendar dataFinal = null;
		try {
			Date date = new SimpleDateFormat("yyy-MM-dd").parse(dataFinalTexto);
			dataFinal = Calendar.getInstance();
			dataFinal.setTime(date);
		} catch (ParseException e) {
			out.println("Erro de conversao de data da Data final no Servlet");
			return;
		}
		
		
		//Monta o Objeto contato
		Projeto projeto = new Projeto();
		projeto.setNome(nome);
		projeto.setDataInicio(dataInicio);
		projeto.setDataFinal(dataFinal);
		projeto.setValor(valor);
		projeto.setRisco(risco);
		projeto.setParticipantes(participantes);

		//Salva o contato
		ProjetoDao dao = new ProjetoDao();
		dao.adiciona(projeto);

		RequestDispatcher rd = request.getRequestDispatcher("/ListaProjeto.jsp");
		rd.forward(request, response);
	}

}
