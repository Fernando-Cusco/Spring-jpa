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

	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
		return em.find(Cliente.class, id);
	}
	
	@Transactional
	public void save(Cliente cliente) {
		if (cliente.getId() != null && cliente.getId() > 0) {
			em.merge(cliente);
		} else {
			em.persist(cliente);
		}
	}

	@Transactional
	public void delete(Long id) {
		Cliente cliente = findOne(id);
		em.remove(cliente);
	}

}
