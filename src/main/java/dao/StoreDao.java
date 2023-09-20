package dao;

import entities.Store;
import org.hibernate.SessionFactory;

public class StoreDao extends AbstractHibernateDao<Store>{
    public StoreDao(SessionFactory sessionFactory) {
        super(Store.class, sessionFactory);
    }
}
