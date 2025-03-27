package com.javapaulo.empregados.controller;

import com.javapaulo.empregados.entity.Empregado;
import com.javapaulo.empregados.service.EmpregadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmpregadoController {

    @Autowired
    private EmpregadoService empregadoService;

    @GetMapping("/")
    public String paginaInicial(Model model) {
        return findPaginated(1, "nome", "asc", model);
    }

    @GetMapping("/cadastraEmpregadoForm")
    public String cadastraEmpregadoForm(Model model) {
        // create model attribute to bind form data
        Empregado empregado = new Empregado();
        model.addAttribute("empregado", empregado);
        return "cadastra_empregado";
    }

    @PostMapping("/salvaEmpregado")
    public String salvaEmpregado(@ModelAttribute("empregado") Empregado empregado) {
        // save employee to database
        empregadoService.saveEmpregado(empregado);
        return "redirect:/";
    }

    @GetMapping("/atualizaEmpregadoForm/{id}")
    public String atualizaEmpregadoForm(@PathVariable ( value = "id") Long id, Model model) {

        // get employee from the service
        Empregado empregado = empregadoService.getEmpregadoById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("empregado", empregado);
        return "atualiza_empregado";
    }

    @GetMapping("/excluiEmpregado/{id}")
    public String excluiEmpregado(@PathVariable (value = "id") Long id) {

        // call delete employee method
        this.empregadoService.deleteEmpregadoById(id);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Empregado> page = empregadoService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Empregado> listEmpregados = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listEmpregados", listEmpregados);
        return "index";
    }
}
