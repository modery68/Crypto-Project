package org.crypto.training.service;

import org.crypto.training.model.Asset;
import org.crypto.training.repository.IAssetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

    @Autowired
    private IAssetDao assetDao;

    public void save(Asset asset) {
        assetDao.save(asset);
    }

    public List<Asset> getAssets() {
        return assetDao.getAssets();
    }

    public Asset update(Asset asset) {
        return assetDao.update(asset);
    }

    public boolean delete(Asset asset) {
        return assetDao.delete(asset);
    }

    public Asset getAssetEager(long id) {
        return assetDao.getAssetEagerBy(id);
    }

    public Asset getBy(long id) {
        return assetDao.getById(id);
    }
    }
