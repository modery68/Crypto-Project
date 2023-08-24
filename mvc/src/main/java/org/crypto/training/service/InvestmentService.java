package org.crypto.training.service;

import org.crypto.training.model.Investment;
import org.crypto.training.repository.IInvestmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InvestmentService {
    @Autowired
    private IInvestmentDao investmentDao;

    public void save(Investment investment) {
        investmentDao.save(investment);
    }

    public List<Investment> getInvestments() {
        return investmentDao.getInvestments();
    }

    public Investment update(Investment investment) {
        return investmentDao.update(investment);
    }

    public boolean delete(Investment investment) {
        return investmentDao.delete(investment);
    }

    public Investment getInvestmentEager(long id) {
        return investmentDao.getInvestmentEagerBy(id);
    }

    public Investment getBy(long id) {
        return investmentDao.getById(id);
    }
}
