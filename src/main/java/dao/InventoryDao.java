package dao;

import entities.Film;
import entities.Inventory;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class InventoryDao extends AbstractHibernateDao<Inventory> {
    public InventoryDao(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }

    public List<Inventory> findInventoryByFilm(Film film){
        Query<Inventory> inventoryQuery = getCurrentSession().createQuery("select i From Inventory i where i.film.id = :FILM", Inventory.class);
        inventoryQuery.setParameter("FILM", film.getId());
        return inventoryQuery.getResultList();
    }
}
