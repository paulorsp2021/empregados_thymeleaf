package com.javapaulo.empregados.service;

import com.javapaulo.empregados.entity.Empregado;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmpregadoService {

    List<Empregado> getAllEmpregados();
        void saveEmpregado(Empregado Empregado);
        Empregado getEmpregadoById(Long id);
        void deleteEmpregadoById(Long id);
        Page<Empregado> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
