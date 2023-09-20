package dao;

import entities.Category;
import org.hibernate.SessionFactory;

public class CategoryDao extends AbstractHibernateDao<Category>{
    public CategoryDao(SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }
}
