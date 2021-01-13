package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Dao.ProjetoDao;

/**
 * Servlet implementation class delProjetoServlet
 */
@WebServlet("/delProjetoServlet")
public class delProjetoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void service(HttpServletRequest request, 
			HttpServletResponse response)
			throws IOException, ServletException {
		//pega o id e armazena em codigo
		Integer codigo = Integer.parseInt(request.getParameter("codigo"));
		//chama o metodo para remover com o codigo como parâmetro
		ProjetoDao dao = new ProjetoDao();
		dao.remove(codigo);
		//redireciona para a pagina de listagem
		RequestDispatcher rd = request.getRequestDispatcher("/ListaProjeto.jsp");
			rd.forward(request, response);
	}

}
