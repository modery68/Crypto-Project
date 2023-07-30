package org.crypto.training.controller;
import org.crypto.training.model.Investment;
import org.crypto.training.service.InvestmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/investment")
public class InvestmentController {
        private final Logger logger = LoggerFactory.getLogger(org.crypto.training.controller.InvestmentController.class);

        @Autowired
        private InvestmentService investmentService;

        @RequestMapping(value = "", method = RequestMethod.GET)
        public List<Investment> getInvestments() {
            List<Investment> investments = investmentService.getInvestments();
            return investments;
        }

        @RequestMapping(value = "/{id}", method = RequestMethod.GET)
        public Investment getInvestmentById(@PathVariable(name = "id") Long id) {
            logger.info("THis is user controller, get by {}", id);
            return investmentService.getBy(id);
        }

        @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, params = {"quantity"})
        public Investment updateQuantity(@PathVariable("id") Long id, @RequestParam("quantity") BigDecimal quantity) {
            logger.info("pass in variable id: {} and name: {}", id.toString(), quantity);
            Investment i = investmentService.getBy(id);
            i.setQuantity(quantity);
            i = investmentService.update(i);
            return i;
        }
        @RequestMapping(value = "")
        public void create(@RequestBody Investment investment) {
            logger.info("Post a new object {}", investment.getQuantity());
        }


}
