package com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.beans.Emp;
import com.dao.EmpDao;

@Controller
public class EmpController {

    @Autowired
    EmpDao dao;
    ModelAndView mv = new ModelAndView();

    @RequestMapping("/empform")
    public ModelAndView showform() {
        mv.setViewName("/WEB-INF/jsp/empform.jsp");
        mv.addObject("insertemp", new Emp());
        return mv;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("emp") Emp emp) {
        dao.save(emp);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping("/")
    public ModelAndView viewemp() {
        List<Emp> list = dao.getEmployees();
        mv.setViewName("/WEB-INF/jsp/viewemp.jsp");
        mv.addObject("list", list);
        return mv;
    }

    @RequestMapping(value = "/editemp/{id}")
    public ModelAndView edit(@PathVariable int id) {
        Emp emp = dao.getEmpById(id);
        mv.setViewName("/WEB-INF/jsp/empeditform.jsp");
        mv.addObject("command", emp);
        return mv;
    }

    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public ModelAndView editsave(@ModelAttribute("emp") Emp emp) {
        dao.update(emp);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/deleteemp/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable int id) {
        dao.delete(id);
        return new ModelAndView("redirect:/");
    }

}
