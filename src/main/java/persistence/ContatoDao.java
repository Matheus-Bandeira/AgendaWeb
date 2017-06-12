package persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entity.Contato;

public class ContatoDao {

	EntityManager manager;
	EntityTransaction transaction;

	public ContatoDao() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("AgendaWeb");
		manager = factory.createEntityManager();
	}

	public void salvar(Contato contato) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.merge(contato);
		transaction.commit();
	}
	
	public Contato findByCode(Long cod){
		return manager.find(Contato.class, cod);
	}
	
	@SuppressWarnings("unchecked")
	public List<Contato> findAll(){
		return manager.createQuery("from Contato").getResultList();
	}
	
	public void excluir(Contato contato) throws Exception{
		transaction = manager.getTransaction();
		transaction.begin();
		Contato c = findByCode(contato.getId());
		//Contato c = manager.find(Contato.class, contato.getId());
		
		manager.remove(c);
		transaction.commit();
		//manager.flush();
	}
	
	@SuppressWarnings("unchecked")
	public List<Contato> findByFavoritos(){
		return manager.createQuery("from Contato c where favorito='true'").getResultList();
	}
	

	public static void main(String[] args) {
		ContatoDao cd = new ContatoDao();
		try {
			Contato resp = cd.findByCode(6L);
			
			cd.excluir(resp);

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
}
