package control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entity.Contato;
import persistence.ContatoDao;

@ManagedBean
@RequestScoped
public class PesquisaFavoritosBean implements Serializable{
	
	private Contato contatoFavorito;
	private List<Contato> contatosFavoritos;
	
	public PesquisaFavoritosBean() {
		contatoFavorito = new Contato();
	}

	public Contato getContatoFavorito() {
		return contatoFavorito;
	}

	public void setContatoFavorito(Contato contatoFavorito) {
		this.contatoFavorito = contatoFavorito;
	}

	public List<Contato> getContatosFavoritos() {
		contatosFavoritos = new ArrayList<>();
		try{
			contatosFavoritos = new ContatoDao().findByFavoritos();
		}catch(Exception ex){
			
		}
		return contatosFavoritos;
	}

	public void setContatosFavoritos(List<Contato> contatosFavoritos) {
		this.contatosFavoritos = contatosFavoritos;
	}
	
	
}
