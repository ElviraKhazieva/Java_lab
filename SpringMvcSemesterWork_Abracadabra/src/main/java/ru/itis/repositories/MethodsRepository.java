package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import ru.itis.models.Method;
import java.util.Optional;

public interface MethodsRepository extends JpaRepository<Method, Long> {

    Optional<Method> findBySignature(String signature);

}
