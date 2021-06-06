package com.RESTInPeace.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {
    private String id;
    private String name;
    private String email;
}
