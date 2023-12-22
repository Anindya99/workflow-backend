package com.blume.workflow.models;

import com.blume.workflow.enums.ItemTypeEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Truck_Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Builder.Default
    private int currentCapacity=0;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ItemTypeEnum itemType= ItemTypeEnum.EMPTY;

    @OneToOne(mappedBy = "status")
    private Truck truck;
}
