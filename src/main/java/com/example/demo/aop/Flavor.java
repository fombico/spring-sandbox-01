package com.example.demo.aop;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class Flavor {
    @Length(min = 3)
    private String flavor;
}
