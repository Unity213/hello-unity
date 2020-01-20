package com.dh.bootio.learning.anoation;

import java.lang.annotation.Repeatable;

@Repeatable(Persons.class)
public @interface Person {
    String role() default "";
}

