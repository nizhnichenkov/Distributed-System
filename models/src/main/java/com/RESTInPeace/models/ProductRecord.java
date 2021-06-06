package com.RESTInPeace.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductRecord {
    private Coffin coffinInfo;
    private User userInfo;
}
