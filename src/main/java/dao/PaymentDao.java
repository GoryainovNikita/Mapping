package dao;

import entities.Payment;
import org.hibernate.SessionFactory;

public class PaymentDao extends AbstractHibernateDao<Payment>{
    public PaymentDao(SessionFactory sessionFactory) {
        super(Payment.class, sessionFactory);
    }
}
