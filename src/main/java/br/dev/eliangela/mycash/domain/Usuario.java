package br.dev.eliangela.mycash.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "usuario")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "ds_email") })
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_usuario")
	private Integer id;

	@Column(name = "ds_email", length = 100)
	private String email;
	
	@Column(name = "ds_senha")
	private String senha;

	@Column(name = "tp_role")
	@Enumerated(EnumType.STRING)
	private UsuarioRole role;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public UsuarioRole getRole() {
		return role;
	}

	public void setRole(UsuarioRole role) {
		this.role = role;
	}

}
