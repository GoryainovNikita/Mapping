package dao;

import entities.Rental;
import org.hibernate.SessionFactory;

public class RentalDao extends AbstractHibernateDao<Rental>{
    public RentalDao(SessionFactory sessionFactory) {
        super(Rental.class, sessionFactory);
    }
}
