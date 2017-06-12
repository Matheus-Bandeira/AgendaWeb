package persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entity.Usuario;

public class UsuarioDao {
	
	EntityManager manager;
	EntityTransaction transaction;
	Query query;
	
	public UsuarioDao() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("AgendaWeb");
		manager = factory.createEntityManager();
	}
	
	public void salvar(Usuario usuario) throws Exception{
		transaction = manager.getTransaction();
		transaction.begin();
		manager.merge(usuario);
		transaction.commit();
	}
	
	public void excluir(Usuario usuario){
		Usuario resp = manager.find(Usuario.class, usuario.getId());
		manager.remove(resp);
		manager.flush();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Usuario> findAll(){
		return manager.createQuery("from Usuario").getResultList();
	}
	
	public Usuario findByCode(Long code){
		return manager.find(Usuario.class, code);
	}
	
	public Usuario findByLogin(Usuario usuario){
		transaction = manager.getTransaction();
		transaction.begin();
		
		query = manager.createQuery("from Usuario u where u.login=:param1 and u.senha=:param2");
		query.setParameter("param1", usuario.getLogin());
		query.setParameter("param2", usuario.getSenha());
		
		Usuario resp = (Usuario) query.getSingleResult();
		return resp;
	}
	
	public static void main(String[] args) {
		UsuarioDao ud = new UsuarioDao();
		try{
			Usuario usuario = new Usuario(null, "9999", "9999");
			ud.salvar(usuario);
		}catch(Exception ex){
			
		}
	}
}
