package com.xbc.service;

import com.xbc.mapper.DepartmentMapper;
import com.xbc.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired()
    private DepartmentMapper departmentMapper;

    /**
     * 根据id查出单位
     * @param id
     * @return
     */
    public Department getDepById(Integer id) {
        return departmentMapper.getDepById(id);
    }

    public List<Department> getAllDeps() {
        return departmentMapper.getAllDeps();
    }

    public int addDepartment(String departmentname) {
        return departmentMapper.addDepartment(departmentname);
    }

    public int deleteDepartment(Integer departmentid) {
        return departmentMapper.deleteDepartment(departmentid);
    }

    public Department getDepartmentByName(String departmentname) {
        return departmentMapper.getDepartmentByName(departmentname);
    }

    public int updateDepartment(Integer depid, String val) {
        return departmentMapper.updateDepartment(depid,val);
    }
}
