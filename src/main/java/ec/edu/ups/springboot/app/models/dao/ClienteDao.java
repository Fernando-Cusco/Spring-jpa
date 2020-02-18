package ec.edu.ups.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.ups.springboot.app.models.entity.Cliente;

@Repository("clientedaojpa")
public class ClienteDao implements IClienteDao {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return em.createQuery("from Cliente").getResultList();
	}
	
	@Transactional
	public void save(Cliente cliente) {
		em.persist(cliente);
	}

}
