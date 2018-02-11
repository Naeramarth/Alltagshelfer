package hello.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.Client;
import hello.Client_;

@Service
@Transactional
public class MyServiceImpl implements MyService {

	@Autowired
	private EntityManager em;

	@Autowired
	private MyRepository repo;

	private boolean mitRepository = true;

	@Override
	public Client getByName(String name) {
		if (mitRepository) {
			List<Client> l = repo.findByName(name);
			return l.get(0);
		} else {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Client> cq = cb.createQuery(Client.class);
			Root<Client> c = cq.from(Client.class);
			cq.where(cb.equal(c.get(Client_.name), name));
			List<Client> l = em.createQuery(cq).getResultList();
			return l.get(0);
		}
	}

	@Override
	public Client getById(int id) {
		if (mitRepository) {
			return repo.findOne(id);
		} else {
			em.find(Client.class, 1);
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Client> cq = cb.createQuery(Client.class);
			Root<Client> c = cq.from(Client.class);
			cq.where(cb.equal(c.get(Client_.id), id));
			return em.createQuery(cq).getSingleResult();
		}
	}

	@Override
	public void create(String name) {
		if (mitRepository) {
			repo.save(new Client(name));
		} else {
			Client c = new Client();
			c.setName(name);
			em.persist(c);
		}
	}
}
