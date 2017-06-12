package control;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import entity.Usuario;
import persistence.UsuarioDao;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//private Usuario usuario;
		private Usuario usuario;
		private Usuario logado;

	public LoginBean() {
		usuario = new Usuario();
		logado = new Usuario();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getLogado() {
		return logado;
	}

	public void setLogado(Usuario logado) {
		this.logado = logado;
	}
	

	public String logar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		UsuarioDao ud = new UsuarioDao();
		try {
			logado = ud.findByLogin(usuario);
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
			
			if (logado != null) {
				logado = new Usuario();
				usuario = new Usuario();
				
				fc.addMessage("formLogin", new FacesMessage("Logado Com Sucesso"));
				session.setAttribute("logado", logado);
				return "/contato/Home.jsf?faces-redirect=true";
			}else {
				usuario = new Usuario();
				fc.addMessage("formLogin", new FacesMessage("Usuario Invalido"));
				return null;
			}
		}catch (Exception ex) {
			fc.addMessage(null, new FacesMessage("Error:" + ex.getMessage()));
			return null;
		}
	}
	
	public String logout(){
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);

			session.setAttribute("logado", null);

			usuario = new Usuario();
			fc.addMessage("formHome", new FacesMessage("Logout do Sistema"));
			return "/Login.jsf";
		} catch (Exception ex) {
			fc.addMessage("", new FacesMessage("Error:" + ex.getMessage()));
			return "/login.jsf";
		}
	}
}
