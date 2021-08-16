package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pages/capturarExcecao")
public class CapturarExcecao extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
    public CapturarExcecao() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	try {
		
		String valor =  request.getParameter("valorParam");
		
		Integer.parseInt(valor);
		
		response.setStatus(200); // indica nenhum erro
		response.getWriter().write("Processada com sucesso");
		
	}catch(Exception e) {
		
		response.setStatus(500); //erro interno do servidor
		response.getWriter().write("Erro ao processar: " + e.getMessage());
		
	}

	}

}
