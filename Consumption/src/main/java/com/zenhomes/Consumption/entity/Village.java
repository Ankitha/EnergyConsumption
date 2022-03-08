package com.zenhomes.Consumption.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Village")
public class Village {

    @Id
    @Column(name = "villageId")
    private int villageId;

    @Column(name = "villageName")
    private String villageName;

    public Village() {}

    public Village(final int villageId, final String villageName, final Counter counter) {
        this.villageId = villageId;
        this.villageName = villageName;
    }

    public int getVillageId() {
        return villageId;
    }

    public void setVillageId(final int villageId) {
        this.villageId = villageId;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(final String villageName) {
        this.villageName = villageName;
    }

}
