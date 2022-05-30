package br.com.fiap.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Visitante;

public class VisitanteDao {

	@Inject
	private EntityManager manager;
	
	public void create(Visitante visi) {
		
		manager.getTransaction().begin();
		manager.persist(visi);
		manager.getTransaction().commit();
		
		manager.clear();
		
	}
	
	public List<Visitante> listAll() {
		
		TypedQuery<Visitante> query = 
				manager.createQuery("SELECT u FROM Visitante u", Visitante.class);
		
		return query.getResultList();
		
	}
	
	public boolean exist(Visitante visi) {
		
		String jpql = "SELECT u FROM User u WHERE cpf=:cpf AND rg=:rg";
		TypedQuery<Visitante> query = manager.createQuery(jpql, Visitante.class);
		query.setParameter("cpf", visi.getCpf());
		query.setParameter("rg", visi.getRg());
		
		try {
			query.getSingleResult();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
}
