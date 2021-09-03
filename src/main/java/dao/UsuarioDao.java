package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.UsuarioBean;
import connection.SingleConnection;

public class UsuarioDao {

	private Connection conn;

	public UsuarioDao() {

		conn = SingleConnection.getConnection();
	}

	public UsuarioBean salvar(UsuarioBean usuario) {

		String query = "insert into usuario (login,senha,nome, email) values (?,?,?,?)";

		try {
			PreparedStatement insert = conn.prepareStatement(query);

			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getEmail());

			insert.execute();
			conn.commit();

		} catch (SQLException e) {

			e.printStackTrace();

			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		return this.consultar(usuario.getLogin(),usuario.getSenha());
	}

	public List<UsuarioBean> listar() {

		List<UsuarioBean> listar = new ArrayList<>();
		String query = "select * from usuario";
		
		try {
			PreparedStatement statment = conn.prepareStatement(query);
			ResultSet resultSet = statment.executeQuery();

			while (resultSet.next()) {

				UsuarioBean usuario = new UsuarioBean(resultSet.getLong("id"), resultSet.getString("nome"),
						resultSet.getString("email"), resultSet.getString("login"), resultSet.getString("senha"),resultSet.getString("imagem"));
				listar.add(usuario);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listar;

	}

	public void delete(String id) {

		String sql = "delete from usuario where id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, Long.parseLong(id));

			ps.execute();
			conn.commit();

		} catch (SQLException e) {

			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	public UsuarioBean consultar(String login, String senha) {

		String query = "select * from usuario where upper(login) = upper(?) and senha=?";
		UsuarioBean usuario = null;

		try {
			PreparedStatement statment = conn.prepareStatement(query);
			statment.setString(1, login);
			statment.setString(2, senha);
			ResultSet resultSet = statment.executeQuery();

			if (resultSet.next()) {

				usuario = new UsuarioBean(resultSet.getLong("id"), resultSet.getString("nome"),
						resultSet.getString("email"), resultSet.getString("login"), resultSet.getString("senha"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return usuario;

	}
	
	public UsuarioBean consultarById(String id) {

		String query = "select * from usuario where id = ?";
		UsuarioBean usuario = null;

		try {
			PreparedStatement statment = conn.prepareStatement(query);
			statment.setLong(1, Long.parseLong(id));
			ResultSet resultSet = statment.executeQuery();

			if (resultSet.next()) {

				usuario = new UsuarioBean(resultSet.getLong("id"), resultSet.getString("nome"),
						resultSet.getString("email"), resultSet.getString("login"), resultSet.getString("senha"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return usuario;

	}
	
	public List<UsuarioBean> consultaByName(String nome) {

		List<UsuarioBean> listar = new ArrayList<>();
		String query = "select * from usuario where upper(nome) like upper(?) ";
		try {			
		
			PreparedStatement statment = conn.prepareStatement(query);
			statment.setString(1, "%" +nome+ "%");
			ResultSet resultSet = statment.executeQuery();

			while (resultSet.next()) {

				UsuarioBean usuario = new UsuarioBean(resultSet.getLong("id"), resultSet.getString("nome"),
						resultSet.getString("email"), resultSet.getString("login"));
				listar.add(usuario);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listar;

	}

	public void atualizar(UsuarioBean user) {

		String query = "update usuario set nome=?, email=?, login=?, senha=? where id=?";

		try {
			PreparedStatement statment = conn.prepareStatement(query);
			statment.setString(1, user.getNome());
			statment.setString(2, user.getEmail());
			statment.setString(3, user.getLogin());
			statment.setString(4, user.getSenha());
			statment.setLong(5, user.getId());

			statment.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public boolean validarLogin(String login) {

		String query = "select count(1) > 0 as existe from usuario where upper(login) = upper(?)";
		
		boolean teste=false;
		

		try {
			
			PreparedStatement st = conn.prepareStatement(query);

			st.setString(1, login);

			ResultSet resultado =  st.executeQuery();

			resultado.next();

			teste = resultado.getBoolean("existe");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return teste;

	}

	public void gravarImagem(String file,String login, String senha) {
		
		String query = "UPDATE USUARIO SET imagem = ?, tipofile=? WHERE login = ? AND senha=?";
		String base_64 = file.split(",")[1];
		String tipo_arquivo = file.split(",")[0].split(";")[0].split("/")[1];
		
		try {
			PreparedStatement insert = conn.prepareStatement(query);
			
			insert.setString(1, base_64);
			insert.setString(2, tipo_arquivo);
			insert.setString(3, login);
			insert.setString(4, senha);
						
			insert.executeUpdate();
			
			conn.commit();
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Map<String,String> buscaImagem(String idUser) {
		
		String query = "SELECT imagem,tipofile FROM usuario WHERE id= ?";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setLong(1, Long.parseLong(idUser));
			
			ResultSet rs = ps.executeQuery();
			
			String imagem;
			String tipoImagem;
			
			Map<String,String> dataImagem = new HashMap<>();
			
			while(rs.next()) {
				
			imagem = rs.getString("imagem");
			tipoImagem = rs.getString("tipofile");
			dataImagem.put("tipoImagem", tipoImagem);
			dataImagem.put("imagem", imagem);
			
			}
			
			return dataImagem;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		
	}

}
