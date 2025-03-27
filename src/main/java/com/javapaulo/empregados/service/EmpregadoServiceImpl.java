package com.javapaulo.empregados.service;

import java.util.List;
import java.util.Optional;

import com.javapaulo.empregados.entity.Empregado;
import com.javapaulo.empregados.repository.EmpregadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EmpregadoServiceImpl implements EmpregadoService {

    @Autowired
    private EmpregadoRepository empregadoRepository;

    @Override
    public List<Empregado> getAllEmpregados() {
        return empregadoRepository.findAll();
    }

    @Override
    public void saveEmpregado(Empregado empregado) {
        this.empregadoRepository.save(empregado);
    }

    @Override
    public Empregado getEmpregadoById(Long id) {
        Optional<Empregado> optional = empregadoRepository.findById(id);
        Empregado empregado = null;
        if (optional.isPresent()) {
            empregado = optional.get();
        } else {
            throw new RuntimeException(" Empregado não localizado pelo id :: " + id);
        }
        return empregado;
    }

    @Override
    public void deleteEmpregadoById(Long id) {
        this.empregadoRepository.deleteById(id);
    }

    @Override
    public Page<Empregado> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.empregadoRepository.findAll(pageable);
    }

}
