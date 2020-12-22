package ru.itis.annotations;
import java.lang.annotation.*;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface HtmlInput {
    String type() default "text";
    String name() default "";
    String placeholder() default "";
}
