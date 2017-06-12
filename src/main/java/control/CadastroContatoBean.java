package control;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import entity.Contato;
import persistence.ContatoDao;

@ManagedBean
@RequestScoped
public class CadastroContatoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Contato contato = new Contato();
	
	
	public void limpar(){
		contato = new Contato();
	}


	public Contato getContato() {
		return contato;
	}


	public void setContato(Contato contato) {
		this.contato = contato;
	}
	
	public void gravar(){
		FacesContext fc = FacesContext.getCurrentInstance();
		try{
			//grava 
			//dar mensagem
			//limpa
			ContatoDao cd = new ContatoDao();
			cd.salvar(contato);
			fc.addMessage(null, new FacesMessage("Dados Gravados com sucesso!"));
			limpar();
		}catch(Exception ex){
			fc.addMessage(null, new FacesMessage(ex.getMessage()));
		}
	}
}
