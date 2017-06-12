package control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import entity.Contato;
import persistence.ContatoDao;

@ManagedBean
@RequestScoped
public class PesquisaContatoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Contato> contatos = new ArrayList<>();

	private Contato contatoSelecionado;

	public PesquisaContatoBean() {
		contatoSelecionado = new Contato();
	}

	public List<Contato> getContatos() {
		contatos = new ContatoDao().findAll();
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public Contato getContatoSelecionado() {
		return contatoSelecionado;
	}

	public void setContatoSelecionado(Contato contatoSelecionado) {
		this.contatoSelecionado = contatoSelecionado;
	}

	public void excluir() {
		ContatoDao cd = new ContatoDao();
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			cd.excluir(contatoSelecionado);
			fc.addMessage("", new FacesMessage("Excluido com sucesso"));
		} catch (Exception ex) {
			fc.addMessage("", new FacesMessage(ex.getMessage()));
			ex.printStackTrace();
		}
	}
	
	public void editar(){
		ContatoDao cd = new ContatoDao();
		FacesContext fc = FacesContext.getCurrentInstance();
		try{
			cd.salvar(contatoSelecionado);
			fc.addMessage("", new FacesMessage("Salvo com sucesso"));
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
