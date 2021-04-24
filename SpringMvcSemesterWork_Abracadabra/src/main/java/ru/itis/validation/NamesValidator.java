package ru.itis.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NamesValidator implements ConstraintValidator<ValidNames, Object> { //<аннотация, для которой пишем валидатор; тип того, что мы валидируем(то над чем стоит аннотация)(в данном случае целый объект класса UserForm)>

    private String namePropertyName;
    private String surnamePropertyName;


    @Override
    public void initialize(ValidNames constraintAnnotation) {//получаем значения полей у аннотации
        this.namePropertyName = constraintAnnotation.name();//название поля для name->firstName
        this.surnamePropertyName = constraintAnnotation.surname();//название поля для surname->lastName

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Object name = new BeanWrapperImpl(value).getPropertyValue(namePropertyName);//получили конкретное значение поля firstName у объекта, который мы валидируем(объекта класса UserForm)
        Object surname = new BeanWrapperImpl(value).getPropertyValue(surnamePropertyName);
        return name != null && !name.equals(surname);
    }
}
