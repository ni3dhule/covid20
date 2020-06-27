package com.covid20.user.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.covid20.user.model.User;

public class UserRepositoryImpl implements UserRepository{
	
	@PersistenceContext
	EntityManager em;
	
	Logger log = LoggerFactory.getLogger(UserRepositoryImpl.class);
	
	@Override
	public <S extends User> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<User> findById(Integer id) {
		return null;
		//return em.find(User.class, id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<User> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(User entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteAll(Iterable<? extends User> entities) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean getUserStatus(int iUserId) {
		log.warn("user id => "+iUserId);
		Query query = em.createNamedQuery("findUserStatusById");
		query.setParameter("id", iUserId);
		log.info("Named SQL Query => "+query.toString());
		boolean bActive = (boolean)query.getSingleResult();
		return bActive;
	}

	@Override
	@Transactional
	public boolean activateUser(int iUserId) {
		log.warn("activating user with user id => "+iUserId);
		Query query = em.createNamedQuery("activateOrDeactivateUser");
		query.setParameter("userId", iUserId);
		query.setParameter("actValue", true);
		log.info("Named SQL Query => "+query.toString());
		int iCount = query.executeUpdate();
		log.warn("User activated successfully [ userId : "+iUserId+" ]");
		return iCount > 0;
	}

	@Override
	@Transactional
	public boolean deactivateUser(int iUserId) {
		log.warn("deactivating user with user id => "+iUserId);
		Query query = em.createNamedQuery("activateOrDeactivateUser");
		query.setParameter("userId", iUserId);
		query.setParameter("actValue", false);
		log.info("Named SQL Query => "+query.toString());
		int iCount = query.executeUpdate();
		log.warn("User deactivated successfully [ userId : "+iUserId+" ]");
		return iCount > 0;
	}
	

}
