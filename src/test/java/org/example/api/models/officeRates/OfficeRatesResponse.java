package org.example.api.models.officeRates;

import java.util.List;

public class OfficeRatesResponse {
    private List<Office> items;

    public List<Office> getItems() { return items; }
    public void setItems(List<Office> items) { this.items = items; }
}