package com.blume.workflow.models;

import com.blume.workflow.enums.ItemTypeEnum;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Embeddable
@Data
public class TruckInfo {

    @Enumerated(EnumType.STRING)
    private ItemTypeEnum itemType;

    private  int ratePerKmPerKg;

    private int otherCharges;

}
