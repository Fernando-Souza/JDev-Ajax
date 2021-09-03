package servlet;

import java.io.IOException;

import beans.UsuarioBean;
import dao.UsuarioDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.UserLogado;

@WebServlet(urlPatterns={"/pages/ServletAutenticacao"})
public class ServletAutenticacao extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UsuarioDao userDao = new UsuarioDao();

    
    public ServletAutenticacao() {
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(Boolean.parseBoolean(request.getParameter("deslogar"))){
			
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();			
			session.invalidate();			
			response.sendRedirect("../index.jsp");
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		UsuarioBean usuarioLogado = userDao.consultar(login, senha);
		
		if(usuarioLogado != null) {
			
			session.setAttribute("usuario", usuarioLogado);
			
			RequestDispatcher dispatcher =  request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			
		}else {
			RequestDispatcher dispatcher =  request.getRequestDispatcher("/autenticar.jsp");
			dispatcher.forward(request, response);
		}
	}

}
