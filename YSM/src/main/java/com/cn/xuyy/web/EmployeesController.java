//package com.cn.xuyy.web;
//
//import com.cn.xuyy.entity.Employees;
//import com.cn.xuyy.service.EmployeesService;
//import java.util.List;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
///**
// * Created by hisen on 17-4-24.
// */
//@Controller
//@RequestMapping("/book")
//public class EmployeesController {
//  private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//  @Autowired
//  private EmployeesService employeesService;
//
//  @RequestMapping(value = "/list", method = RequestMethod.GET)
//  private String list(Model model) {
//    List<Employees> list = employeesService.getList(0, 1000);
//    model.addAttribute("list", list);
//    return "list";// WEB-INF/jsp/"list".jsp
//  }
//
//  @RequestMapping(value = "/detail/{empId}", method = RequestMethod.GET)
//  private String detail(@PathVariable("empId") Long empId, Model model) {
//    Employees employees= employeesService.getById(empId);
//    model.addAttribute("employees", employees);
//    return "detail";
//  }
//
//  @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
//  @ResponseBody
//  private String add(Employees employees) {
//    //查看数据库中该ID是否有员工
//    Employees hasEmployees = employeesService.getById(employees.getEmpId());
//    int i = -2;
//    //如果该ID没有员工，将employess加入数据库；
//    if (hasEmployees == null) {
//      i = employeesService.addEmployees(employees);
//    }
//    return i > 0 ? "success" : "error";
//  }
//
//  @RequestMapping(value = "/del/{empId}", method = RequestMethod.GET)
//  @ResponseBody
//  private String deleteEmployeesByID(@PathVariable("empId") Long id) {
//    int i = employeesService.deleteEmployeesById(id);
//    return i > 0 ? "success" : "error";
//  }
//
//  @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
//  @ResponseBody
//  private String update(Employees employees) {
//    //查看数据库中该ID是否有员工
//    Employees hasEmployees = employeesService.getById(employees.getEmpId());
//    int i = -2;
//    //如果该ID没有员工，将employess加入数据库；
//    if (hasEmployees != null) {
//      i = employeesService.updateEmployees(employees);
//    }
//    return i > 0 ? "success" : "error";
//  }
//
//
//}
