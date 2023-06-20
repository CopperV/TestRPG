package me.Vark123.TestRPG.Repositories.Impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.query.Query;

import me.Vark123.TestRPG.API.TestRPGApi;
import me.Vark123.TestRPG.Players.RpgPlayer;
import me.Vark123.TestRPG.Repositories.APlayerRepository;

public class TestPlayerRepository implements APlayerRepository {

	@Override
	public boolean create(RpgPlayer rpg) {
		Session session = TestRPGApi.get().getSessionFactory().openSession();
		try {
			session.clear();
			session.beginTransaction();
			session.persist(rpg);
			session.getTransaction().commit();
		} catch (Exception e) {
			if(session.getTransaction() != null
					&& session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return true;
	}

	@Override
	public boolean update(RpgPlayer rpg) {
		Session session = TestRPGApi.get().getSessionFactory().openSession();
		try {
			session.clear();
			session.beginTransaction();
			session.merge(rpg);
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			if(session.getTransaction() != null
					&& session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return true;
	}

	@Override
	public boolean delete(RpgPlayer rpg) {
		Session session = TestRPGApi.get().getSessionFactory().openSession();
		try {
			session.clear();
			session.beginTransaction();
			session.remove(rpg);
			session.getTransaction().commit();
		} catch (Exception e) {
			if(session.getTransaction() != null
					&& session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return true;
	}

	@Override
	public RpgPlayer getPlayerByID(UUID uid) {
		Session session = TestRPGApi.get().getSessionFactory().openSession();
		Query<RpgPlayer> query = session
				.createNamedQuery("RpgPlayer.getByUUID", RpgPlayer.class);
		query.setParameter("uid", uid);
		RpgPlayer rpg = null;
		try {
			rpg = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return rpg;
	}

	@Override
	public List<RpgPlayer> getAllPlayers() {
		Session session = TestRPGApi.get().getSessionFactory().openSession();
		Query<RpgPlayer> query = session.createNamedQuery("RpgPlayer.getAll", RpgPlayer.class);
		List<RpgPlayer> list = query.getResultList();
		session.close();
		return list;
	}

}
