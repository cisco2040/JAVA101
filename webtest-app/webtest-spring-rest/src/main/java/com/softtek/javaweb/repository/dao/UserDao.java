package com.softtek.javaweb.repository.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;

import org.springframework.stereotype.*;

import com.softtek.javaweb.domain.model.User;
import com.softtek.javaweb.repository.MyRepository;


public class UserDao implements MyRepository<User, String> {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public User getOne(String id) {
		return em.find(User.class, id);
	}

	@Override
	public List<User> list() {
		CriteriaQuery<User> criteriaQuery = em.getCriteriaBuilder().createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);
		return em.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public User add(User entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public int update(User entity) {
		return 1;
	}

	@Override
	public int delete(String id) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaDelete<User> delete = criteriaBuilder.createCriteriaDelete(User.class);
		Root<User> e = delete.from(User.class);
		delete.where(criteriaBuilder.equal(e.get("username"), id));
		return em.createQuery(delete).executeUpdate();
	}

}
