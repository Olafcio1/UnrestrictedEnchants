package pl.olafcio.unrestricted_enchants;

import java.lang.annotation.*;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
@Repeatable(Features.class)
public @interface Feature {
    String value();
}
