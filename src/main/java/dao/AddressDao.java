package dao;

import entities.Address;
import org.hibernate.SessionFactory;

public class AddressDao extends AbstractHibernateDao<Address>{

    public AddressDao(SessionFactory sessionFactory) {
        super(Address.class, sessionFactory);
    }
}
