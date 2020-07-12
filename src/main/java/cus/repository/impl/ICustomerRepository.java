package cus.repository.impl;

import cus.model.Customer;
import cus.repository.IRepository;

import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ICustomerRepository extends IRepository<Customer> {
}
