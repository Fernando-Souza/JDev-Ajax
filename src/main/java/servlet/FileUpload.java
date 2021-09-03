package servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;

import beans.UsuarioBean;
import dao.UsuarioDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/pages/fileUpload")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioDao daoUsuario = new UsuarioDao();

	public FileUpload() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		if(acao.equalsIgnoreCase("carregar")) {
			
			RequestDispatcher viDispatcher =  request.getRequestDispatcher("upload.jsp");
			request.setAttribute("listaUserImagem", daoUsuario.listar());
			viDispatcher.forward(request, response);
			
		}else if(acao.equalsIgnoreCase("download")) {
			
			String idUser = request.getParameter("iduser");
			
			Map<String,String> imagem = daoUsuario.buscaImagem(idUser);
			
			if(imagem!= null) {
				
				response.setHeader("Content-Disposition", "attachment;filename=arquivo."+ imagem.get("tipofile"));
				
				String imagemPura = imagem.get("imagem");
				byte [] imgBytes =  new Base64().decodeBase64(imagemPura);
				/* Cria uma stream de leitura para os dados*/
				InputStream inputBytes = new ByteArrayInputStream(imgBytes);
				
				/* INICIO -  Escrever dados na resposta*/
				int read = 0;
				byte[] bytes =  new byte[1024];
				OutputStream outputBytes = response.getOutputStream();//cria uma stream de saída para os bytes lidos
				
				while((read=inputBytes.read(bytes))!= -1) {
					
					outputBytes.write(bytes,0,read);
					
				}
				
				outputBytes.flush();
				outputBytes.close();
				
			}
		}
		
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		try {

			String file = request.getParameter("fileUpload");
			UsuarioBean usuario = (UsuarioBean) session.getAttribute("usuario");

			daoUsuario.gravarImagem(file,usuario.getLogin(),usuario.getSenha());

			response.getWriter().write("Upload realizado com sucesso!");

		} catch (Exception ex) {

			response.getWriter().write("Falha ao realizar o upload!");

		}
	}

}
