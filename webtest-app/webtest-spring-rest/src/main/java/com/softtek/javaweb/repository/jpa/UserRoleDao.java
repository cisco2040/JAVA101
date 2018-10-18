package com.softtek.javaweb.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.softtek.javaweb.domain.model.UserRole;
import com.softtek.javaweb.repository.MyRepository;

@Repository
public class UserRoleDao implements MyRepository<UserRole, String> {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public UserRole getOne(String id) {
		String jpql = "SELECT ur FROM UserRole ur WHERE ur.userRoleId = :id";
		return (UserRole) em.createQuery(jpql).setParameter("id", id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> list() {
		String jpql = "SELECT ur FROM UserRole ur";
		return (List<UserRole>) em.createQuery(jpql).getResultList();
	}

	@Override
	public UserRole add(UserRole entity) {
		return null;
	}

	@Override
	public UserRole update(UserRole entity) {
		return null;
	}

	@Override
	public int delete(String id) {
		return 0;
	}

}
