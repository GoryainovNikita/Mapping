package dao;

import entities.Film;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;


import java.util.List;

public class FilmDao extends AbstractHibernateDao<Film>{
    public FilmDao(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }

    public List<Film> getAllFilmsCurrentlyNotRentedInStore(Byte storeId){

      Query<Film> filmQuery = getCurrentSession().createQuery("select distinct i.film from Inventory i where i.store.id = :STORE and i.id IN (select r.inventory.id from Rental r where r.returnDate Is not null)", Film.class);
      filmQuery.setParameter("STORE", storeId);
      return filmQuery.getResultList();
    }

}
