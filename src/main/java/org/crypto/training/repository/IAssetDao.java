package org.crypto.training.repository;

import org.crypto.training.model.Asset;
import org.crypto.training.model.User;

import java.util.List;

public interface IAssetDao {

    public List<Asset> getAssets();

    void save(Asset asset);

    User getById(Long id);

    boolean delete(Asset asset);
}
