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
 * Servlet implementation class altProjetoServlet
 */
@WebServlet("/altProjetoServlet")
public class altProjetoServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	protected void service( HttpServletRequest request, HttpServletResponse response ) 
		throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		 
		//Buscando os parametros do request
		
		Integer codigo = Integer.parseInt(request.getParameter("codigo"));
		String nome = request.getParameter("nome");
		String dataInicioTexto = request.getParameter("dataInicio");
		String dataFinalTexto = request.getParameter("dataFinal");
		Double valor = Double.parseDouble(request.getParameter("valor"));
		Integer risco = Integer.parseInt(request.getParameter("risco"));
		String participantes = request.getParameter("participantes");
				
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
		projeto.setCodigo(codigo);
		projeto.setNome(nome);
		projeto.setDataInicio(dataInicio);
		projeto.setDataFinal(dataFinal);
		projeto.setValor(valor);
		projeto.setRisco(risco);
		projeto.setParticipantes(participantes);
		
		ProjetoDao dao = new ProjetoDao();
		dao.altera(projeto);
		
		RequestDispatcher rd = request.getRequestDispatcher("/ListaProjeto.jsp");
		rd.forward(request, response);
	}

}
