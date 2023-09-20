package dao;

import entities.Staff;
import org.hibernate.SessionFactory;

public class StaffDao extends AbstractHibernateDao<Staff>{
    public StaffDao(SessionFactory sessionFactory) {
        super(Staff.class, sessionFactory);
    }
}
