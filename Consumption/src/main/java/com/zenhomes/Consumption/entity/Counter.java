package com.zenhomes.Consumption.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Counter")
public class Counter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cntId;

    @Column(name = "counterId")
    private  int counterId;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "consumptionDate")
    private LocalDateTime consumptionDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "villageId")
    private Village village;

    public int getCntId() {
        return cntId;
    }

    public void setCntId(final int cntId) {
        this.cntId = cntId;
    }

    public int getCounterId() {
        return counterId;
    }

    public void setCounterId(final int counterId) {
        this.counterId = counterId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(final Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getConsumptionDate() {
        return consumptionDate;
    }

    public void setConsumptionDate(final LocalDateTime consumptionDate) {
        this.consumptionDate = consumptionDate;
    }

    public Village getVillage() {
        return village;
    }

    public void setVillage(final Village village) {
        this.village = village;
    }

    public Counter(final int cntId, final int counterId, final Double amount, final LocalDateTime consumptionDate) {
        this.cntId = cntId;
        this.counterId = counterId;
        this.amount = amount;
        this.consumptionDate = consumptionDate;
    }

    public Counter() {}
}
