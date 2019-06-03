package com.timezone.demo.repositories.impl;


import com.timezone.demo.model.User;
import com.timezone.demo.repositories.UserRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {


        private SessionFactory sessionFactory;

        @Override
        public User getUserByUsername(String username) {
            Query<User> query = sessionFactory.getCurrentSession().createQuery("FROM User u where u.username=:username", User.class);
            query.setParameter("username", username);
            return query.uniqueResult();
        }


    @Override
    public Integer add(User user) {
        return (Integer) sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void delete(Integer id) {
           sessionFactory.getCurrentSession().delete(id);
    }

    @Override
    public boolean update(User user) {
        if (sessionFactory.getCurrentSession().contains(user)) {
            sessionFactory.getCurrentSession().update(user);
            return true;
        }
        if (!sessionFactory.getCurrentSession().contains(user)) {
        }
            return false;
    }

    @Override
    public User findOne(Integer id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public List<User> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM User", User.class).list();
    }
}
