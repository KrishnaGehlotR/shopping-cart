package org.shoppingcart.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.shoppingcart.dao.AccountDAO;
import org.shoppingcart.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountDAOImpl implements AccountDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("deprecation")
	@Override
	public Account findAccount(String username) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Account.class);
		criteria.add(Restrictions.eq("username", username));
		return (Account) criteria.uniqueResult();
	}

}
