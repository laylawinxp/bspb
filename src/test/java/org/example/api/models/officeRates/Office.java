package org.example.api.models.officeRates;

import java.util.List;

public class Office {
    private String address;
    private int id;
    private String name;
    private List<Rate> rates;

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Rate> getRates() { return rates; }
    public void setRates(List<Rate> rates) { this.rates = rates; }
}