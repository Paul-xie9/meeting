package com.xbc.controller;

import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.util.StringUtils;
import com.xbc.pojo.Department;
import com.xbc.pojo.Employee;
import com.xbc.pojo.MeetingRoom;
import com.xbc.pojo.ReserveMeeting;
import com.xbc.service.DepartmentService;
import com.xbc.service.EmployeeService;
import com.xbc.service.MeetingRoomService;
import com.xbc.service.ReserveMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.List;

@Controller
@RequestMapping("admin/")
public class AdminController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    MeetingRoomService meetingRoomService;
    @Autowired
    ReserveMeetingService reserveMeetingService;

    //一页放10条数据
    public static final Integer PAGE_SIZE = 10;

    /**
     * 登录成功的主页
     *
     * @return
     */
    @RequestMapping("main")
    public String main() {
        return "notifications";
    }

    /**
     * 查出所以未审批的员工
     *
     * @param model
     * @return
     */
    @RequestMapping("to_approveaccount")
    public String approveAccount(Model model) {
        List<Employee> employeeList = employeeService.getAllEmpsByStatus();
        model.addAttribute("emps", employeeList);
        return "approveaccount";
    }

    /**
     * 修改员工的审批状态
     *
     * @return
     */
    @RequestMapping("updatestatus")
    public String updatestatus(Integer employeeid, Integer status, Model model) {
        int result = employeeService.updatestatus(employeeid, status);
        if (result > 0) {
            model.addAttribute("msg", "审批成功！");
            return "forward:approveaccount";
        }
        model.addAttribute("error", "审批失败！");
        return "forward:approveaccount";
    }

    /**
     * 跳转到部门管理页面，并查出所以部门
     *
     * @return
     */
    @RequestMapping("to_departments")
    public String toDepartments(Model model) {
        List<Department> departmentList = departmentService.getAllDeps();
        model.addAttribute("deps", departmentList);
        return "departments";
    }

    /**
     * 添加部门信息
     *
     * @return
     */
    @RequestMapping("add_department")
    public String addDepartment(String departmentname, Model model) {
        if (StringUtils.isEmpty(departmentname)) {
            model.addAttribute("error", "输入名不能为空！");
            return "forward:to_departments";
        }
        Department department = departmentService.getDepartmentByName(departmentname);
        if (department != null) {
            model.addAttribute("error", "部门名已存在，请重新输入！");
            return "forward:to_departments";
        }
        int result = departmentService.addDepartment(departmentname);
        if (result > 0) {
            model.addAttribute("msg", "部门添加成功！");
            return "forward:to_departments";
        }
        model.addAttribute("error", "部门添加失败！");
        return "forward:to_departments";
    }

    /**
     * 删除一个部门
     *
     * @param departmentid
     * @return
     */
    @RequestMapping("deletedep")
    public String deleteDepartment(Integer departmentid, Model model) {
        int result = departmentService.deleteDepartment(departmentid);
        if (result > 0) {
            model.addAttribute("msg", "删除成功！");
            return "forward:to_departments";
        }
        model.addAttribute("error", "删除失败！");
        return "forward:to_departments";
    }

    /**
     * 更新一个部门
     */
    @RequestMapping("update_dep")
    @ResponseBody
    public String updateDepartment(Integer id, String name, Model model) {
        int result = departmentService.updateDepartment(id, name);
        if (result > 0) {
            model.addAttribute("msg", "部门更新成功！");
            return "success";
        }
        model.addAttribute("error", "部门更新失败！");
        return "error";
    }

    /**
     * 到员工搜索的页面
     *
     * @return
     */
    @RequestMapping("to_searchemployees")
    public String toSearchemployees(Employee employee, @RequestParam(defaultValue = "1") Integer page, Model model) {
        List<Employee> employeeList = employeeService.getAllEmps(employee, page, PAGE_SIZE);
        Long total = employeeService.getTotal(employee, page, PAGE_SIZE);
        for (Employee employee1 : employeeList) {
            System.out.println("employee1.getEmployeeName() = " + employee1.getEmployeeName());
        }

        model.addAttribute("emps", employeeList);
        model.addAttribute("total", total);//查询到的总数居
        model.addAttribute("page", page);//第几页
        long pagenum = total % PAGE_SIZE == 0 ? total / PAGE_SIZE : total / PAGE_SIZE + 1;
        model.addAttribute("pagenum", pagenum);//被分成了几页
        System.out.println("total = " + total);
        System.out.println("page = " + page);
        System.out.println("pagenum = " + pagenum);
        return "searchemployees";
    }

    /**
     * 关闭账号，即修改账号状态为未通过
     *
     * @return
     */
    @RequestMapping("updateStatus")
    public String updateStatus(Integer employeeId) {
        Integer status = 2;//2表示审核未通过
        employeeService.updatestatus(employeeId, status);
        return "redirect:to_searchemployees";
    }

    /**
     * 到修改密码的页面
     *
     * @return
     */
    @RequestMapping("to_change_password")
    public String toChangePassword(Model model, HttpSession httpSession) {
        Employee employee = (Employee) httpSession.getAttribute("currentUser");
        model.addAttribute("employee", employee);
        return "changepassword";
    }

    /**
     * 修改密码
     *
     * @return
     */
    @RequestMapping("change_password")
    public String changePassword(String password, String newpassword1, Model model, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("currentUser");
        Integer employeeId = employee.getEmployeeId();
        String password1 = employee.getPassword();

        if (password1.equals(password)) {
            int result = employeeService.updatePassword(employeeId, newpassword1);   //修改密码
            if (result > 0) {
                model.addAttribute("msg", "密码修改成功,请重新登录！");
                session.removeAttribute("currentUser");
                return "forward:/";
            }
        } else {
            model.addAttribute("error", "原密码输入错误");
            return "forward:to_change_password";
        }
        return "forward:to_change_password";
    }

    /**
     * 更新会议室
     *
     * @param
     * @param model
     * @return
     */
    @PostMapping("update_room")
    public String updateRoom(Integer roomId, Integer roomNum,
                             String roomName, Integer capacity,
                             Integer status, String description, Model model) {
        MeetingRoom meetingRoom = new MeetingRoom(roomId, roomNum,
                roomName, capacity, status, description);
        meetingRoom.toString();
        int result = meetingRoomService.updateRoom(meetingRoom);
        if (result > 0) {
            return "redirect:/to_meetingrooms";
        }
        model.addAttribute("error", "更新失败！");
        return "forward:room_details";
    }

    /**
     * 到添加会议室的页面
     * @return
     */
    @RequestMapping("to_addmeetingroom")
    public String toAddMeetingRoom(){
        return "addmeetingroom";
    }

    @PostMapping("addmeetingroom")
    public String AddMeetingRoom(MeetingRoom meetingRoom,Model model){
        int result = meetingRoomService.AddMeetingRoom(meetingRoom);
        if (result>0){
            model.addAttribute("msg","添加会议室成功！");
            return "forward:/to_meetingrooms";
        }
        model.addAttribute("error","添加会议室失败！");
        return "forward:to_addmeetingroom";
    }

    /**
     * 到达最新通知页面
     *
     * @return
     */
    @RequestMapping("to_notifications")
    public String toNotifications(Model model, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("currentUser");
        Integer employeeId = employee.getEmployeeId();
        List<ReserveMeeting> reserveMeetingList = reserveMeetingService.getMeetingByParticipate(employeeId);
        model.addAttribute("ms", reserveMeetingList);
        return "notifications";
    }

    /**
     * 到达我的预定页面
     *
     * @return
     */
    @RequestMapping("to_mybookings")
    public String toMybookings() {
        return "mybookings";
    }

    @RequestMapping("to_meetingdetails")
    public String getMeetingDetails(Integer meetingid,Model model){
//        MeetingRoom meetingRoomDetails = meetingRoomService.getMeetingRoomDetails(meetingid);
        model.addAttribute("meeting",null);
        model.addAttribute("ems",null);
        return "meetingdetails";
    }

    /**
     * 暂时不做
     * @return
     */
    @RequestMapping("searchemployees")
    public String queryMeetings(){

        return "redirect:to_searchemployees";
    }
}
