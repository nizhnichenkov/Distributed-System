package com.RESTInPeace.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Coffin {
    private String id;
    private String image;
    private String userEmail;
    private String name;
    private String description;
    private Long timeStamp;
    private Integer price;
}
