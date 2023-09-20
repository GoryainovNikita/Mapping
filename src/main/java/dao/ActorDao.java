package dao;

import entities.Actor;
import org.hibernate.SessionFactory;

public class ActorDao extends AbstractHibernateDao<Actor>{
    public ActorDao(SessionFactory sessionFactory) {
        super(Actor.class, sessionFactory);
    }
}
