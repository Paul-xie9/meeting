package com.xbc.mapper;

import com.xbc.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    Department getDepById(@Param("id") Integer id);

    List<Department> getAllDeps();

    int addDepartment(@Param("departmentname") String departmentname);

    int deleteDepartment(@Param("departmentid") Integer departmentid);

    Department getDepartmentByName(@Param("departmentname") String departmentname);

    int updateDepartment(@Param("depid") Integer depid, @Param("val") String val);
}