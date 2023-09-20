package dao;

import entities.Country;
import org.hibernate.SessionFactory;

public class CountryDao extends AbstractHibernateDao<Country>{
    public CountryDao(SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }
}
