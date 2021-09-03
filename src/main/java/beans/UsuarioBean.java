package beans;

public class UsuarioBean {

	private Long id;
	private String nome;
	private String email;
	private String login;
	private String senha;
	private String imagem;

		
	public UsuarioBean() {

	}
	
	public UsuarioBean(Long id, String nome, String email,String login, String senha, String imagem) {

		this(id, nome,email,login);
		this.senha = senha;
		this.imagem= imagem;	

	}
	
	public UsuarioBean(Long id, String nome, String email,String login, String senha) {

		this(id, nome,email,login);
		this.senha = senha;
	
	}
	
	public UsuarioBean(Long id, String nome, String email,String login) {

		this.id = id;
		this.nome = nome;
		this.email=email;
		this.login = login;
		

	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	@Override
	public String toString() {
		return "UsuarioBean [id=" + id + ", nome=" + nome + ", email=" + email + ", login=" + login + ", senha=" + senha
				+ ",imagem=" + imagem +"]";
	}

}
