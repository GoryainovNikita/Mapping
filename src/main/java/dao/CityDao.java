package dao;

import entities.City;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


public class CityDao extends AbstractHibernateDao<City>{
    public CityDao(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }

    public City getByName(String name){
        Query<City> query = getCurrentSession().createQuery("select c from City c where c.city = :city", City.class);
        query.setParameter("city", name);
        City city = query.uniqueResult();
        return city;
    }
}
