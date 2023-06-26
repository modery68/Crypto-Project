package org.crypto.training.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Investment {

    public Investment() {}

    private long id;

    private long user_id;

    private long asset_id;

    private BigDecimal quantity;

    private BigDecimal  purchase_price;

    private LocalDate   purchase_date;
}
