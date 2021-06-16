package ru.itis.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.models.Method;
import ru.itis.repositories.MethodsRepository;

@Aspect
@Component
public class MethodAmountAspect {

    @Autowired
    private MethodsRepository methodRepository;

    @Before("@annotation(MethodCounting)")
    public void addCount(JoinPoint joinPoint) {
        System.out.println("Method " + joinPoint.getSignature() + " was called");
        Method method = methodRepository.findBySignature(joinPoint.getSignature().toLongString())
                .orElse(Method.builder()
                        .signature(joinPoint.getSignature().toLongString())
                        .amount(0)
                        .build());
        method.setAmount(method.getAmount() + 1);
        methodRepository.save(method);

    }

}










//@Before(value = "execution(* ru.itis.controllers.SignUpController.*(..))")


