package com.riwi.MealMapSpring.dtos.Response;

import lombok.*;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class TablesResponse {

    private int numberTable;
    private boolean isAvaliable;
    private int capacity;
}
