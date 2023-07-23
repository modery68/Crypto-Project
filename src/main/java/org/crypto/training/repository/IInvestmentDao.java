package org.crypto.training.repository;

import org.crypto.training.model.Asset;
import org.crypto.training.model.Investment;
import org.crypto.training.model.User;

import java.util.List;

public interface IInvestmentDao {

    public List<Investment> getInvestments();

    void save(Investment investment);

    Investment getById(Long id);

    boolean delete(Investment investment);

    Investment getInvestmentEagerBy(Long id);

    Investment update(Investment investment);
}
