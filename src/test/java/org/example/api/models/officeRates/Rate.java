package org.example.api.models.officeRates;

public class Rate {
    private double buyRate;
    private double cbRate;
    private String currencyCode;
    private String currencyCodeSecond;
    private int lotSize;
    private double sellRate;
    private int transactionVolume;

    public double getBuyRate() { return buyRate; }
    public void setBuyRate(double buyRate) { this.buyRate = buyRate; }
    public double getCbRate() { return cbRate; }
    public void setCbRate(double cbRate) { this.cbRate = cbRate; }
    public String getCurrencyCode() { return currencyCode; }
    public void setCurrencyCode(String currencyCode) { this.currencyCode = currencyCode; }
    public String getCurrencyCodeSecond() { return currencyCodeSecond; }
    public void setCurrencyCodeSecond(String currencyCodeSecond) { this.currencyCodeSecond = currencyCodeSecond; }
    public int getLotSize() { return lotSize; }
    public void setLotSize(int lotSize) { this.lotSize = lotSize; }
    public double getSellRate() { return sellRate; }
    public void setSellRate(double sellRate) { this.sellRate = sellRate; }
    public int getTransactionVolume() { return transactionVolume; }
    public void setTransactionVolume(int transactionVolume) { this.transactionVolume = transactionVolume; }
}