package org.crypto.training.controller;
import org.crypto.training.model.Asset;
import org.crypto.training.service.AssetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/asset")
public class AssetController {
    private final Logger logger = LoggerFactory.getLogger(org.crypto.training.controller.AssetController.class);

    @Autowired
    private AssetService assetService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Asset> getAssets() {
        List<Asset> assets = assetService.getAssets();
        return assets;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Asset getAssetById(@PathVariable(name = "id") Long id) {
        logger.info("THis is user controller, get by {}", id);
        return assetService.getBy(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, params = {"name"})
    public Asset updateName(@PathVariable("id") Long id, @RequestParam("name") String name) {
        logger.info("pass in variable id: {} and name: {}", id.toString(), name);
        Asset i = assetService.getBy(id);
        i.setName(name);
        i = assetService.update(i);
        return i;
    }
    @RequestMapping(value = "")
    public void create(@RequestBody Asset asset) {
        logger.info("Post a new object {}", asset.getName());
    }

}
