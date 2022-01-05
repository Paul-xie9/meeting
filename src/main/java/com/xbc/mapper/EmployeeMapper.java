package com.xbc.mapper;

import com.xbc.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    Employee login(@Param("username") String username);

    int register(@Param("employeeName") String employeeName,
                 @Param("username")String username,@Param("password") String password,
                 @Param("phone")String phone,@Param("email") String email,
                 @Param("departmentId") String departmentId);

    List<Employee> getAllEmpsByStatus(@Param("status") Integer status);

    List<Employee> getAllEmps(@Param("emp") Employee employee, @Param("page") Integer page, @Param("pageSize") Integer pageSize);

    int updatestatus(@Param("employeeId") Integer employeeId, @Param("status") Integer status);

    List<Employee> getEmployeeByStatus(@Param("status") int i);

    Long getTotal(@Param("emp") Employee employee, @Param("page") Integer page, @Param("pageSize") Integer pageSize);

    Employee getEmpById(@Param("employeeid") Integer employeeid);

    int updatePassword(@Param("employeeid") Integer employeeid, @Param("newpassword") String newpassword);
}
