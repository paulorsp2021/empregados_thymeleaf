package com.javapaulo.empregados.repository;

import com.javapaulo.empregados.entity.Empregado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {

}
