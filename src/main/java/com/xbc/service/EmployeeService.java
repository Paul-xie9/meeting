package com.xbc.service;

import com.xbc.mapper.EmployeeMapper;
import com.xbc.pojo.Employee;
import com.xbc.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    public Employee login(String username, String password) {
        //将password加密
        String s = Md5Util.MD5(password);
        System.out.println("s = " + s);
        Employee employee = employeeMapper.login(username);
        if (employee==null||!employee.getPassword().equals(password)){
            return null;
        }
        return employee;
    }

    public int register(String employeeName, String username, String password, String phone, String email, String departmentId) {
        int i = employeeMapper.register(employeeName,username,password, phone, email, departmentId);
        return i;
    }

    public List<Employee> getAllEmpsByStatus(Integer status) {
        return employeeMapper.getAllEmpsByStatus(status);
    }

    public List<Employee> getAllEmps(Employee employee,Integer page,Integer pageSize) {
        //这个page是从第几行数据开始查
        page = (page - 1) * pageSize;
        return employeeMapper.getAllEmps(employee, page, pageSize);
    }

    public int updatestatus(Integer employeeId, Integer status) {
        return employeeMapper.updatestatus(employeeId,status);
    }

    public List<Employee> getEmployeeByStatus(int i) {
        return employeeMapper.getEmployeeByStatus(i);
    }

    public Long getTotal(Employee employee,Integer page,Integer pageSize) {
        page = (page - 1) * pageSize;
        return employeeMapper.getTotal(employee, page, pageSize);
    }

    public Employee getEmpById(Integer employeeid) {
        return employeeMapper.getEmpById(employeeid);
    }

    public int updatePassword(Integer employeeid, String newpassword) {
        return employeeMapper.updatePassword(employeeid,newpassword);
    }
}
