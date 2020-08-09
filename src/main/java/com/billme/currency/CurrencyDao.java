package com.billme.currency;

import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class CurrencyDao {

    @Autowired
    private SessionFactory sessionFactory;

    void save(Currency currency) {
        sessionFactory.getCurrentSession().save(currency);
    }

    Currency getCurrency(String code) {
        return sessionFactory.getCurrentSession().get(Currency.class, code, LockMode.NONE);
    }

}
