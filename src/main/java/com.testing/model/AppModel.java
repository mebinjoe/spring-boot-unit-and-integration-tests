package com.testing.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppModel {
    private String title;
    private String description;
    private Integer volumes;
    private Double score;
}
