package api.domain;

public class User {
	
	long codigo;
	String nombre;
	String password;
	
	public User(long codigo, String nombre, String password) {
		
		this.codigo=codigo;
		this.nombre = nombre;
		this.password = password;
		
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
