package sk.tsystems.gamestudio.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import sk.tsystems.gamestudio.entity.Score;

@Component
@Transactional
public class ScoreServiceJPA implements ScoreService {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addScore(Score score) {
		entityManager.persist(score);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Score> getTopScores(String game) {
		try {
			return (List<Score>) entityManager
					.createQuery("select s from Score s where s.game = :game order by s.score desc")
					.setParameter("game", game).setMaxResults(10).getResultList();

		} catch (NoResultException e) {
			return null;
		}
	}

}
