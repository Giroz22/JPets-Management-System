package com.jpets.app.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PetResponse {
    private String id;
    private String name;
    private String ownerName;
    private String pictureUrl;
}
