package com.xbc.controller;

import com.alibaba.druid.util.StringUtils;
import com.xbc.pojo.Department;
import com.xbc.pojo.Employee;
import com.xbc.pojo.MeetingRoom;
import com.xbc.pojo.ReserveMeeting;
import com.xbc.query.CommonQuery;
import com.xbc.service.DepartmentService;
import com.xbc.service.EmployeeService;
import com.xbc.service.MeetingRoomService;
import com.xbc.service.ReserveMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

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
    public String main(Model model,HttpSession session) {
        Employee employee = (Employee) session.getAttribute("currentUser");
        Integer employeeId = employee.getEmployeeId();
        List<ReserveMeeting> reserveMeetingList = reserveMeetingService.getMeetingByParticipate(employeeId);
        reserveMeetingList.forEach(l->{
            System.out.println("l.getMeetingName() = " + l.getMeetingName());
        });

        List<ReserveMeeting> reserveMeetings= reserveMeetingList.stream()
                .filter(reserveMeeting -> reserveMeeting.getStatus()==0)
                .collect(Collectors.toList());//未取消的会议
        List<ReserveMeeting> cancelMeetings=reserveMeetingList.stream()
                .filter(re->re.getStatus()==1)
                .collect(Collectors.toList());//取消的会议

        model.addAttribute("ms", reserveMeetings);
        model.addAttribute("cms", cancelMeetings);
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
        int status=0;
        List<Employee> employeeList = employeeService.getAllEmpsByStatus(status);
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
    @RequestMapping({"to_searchemployees","selectAllEmps"})
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

        List<ReserveMeeting> reserveMeetings= reserveMeetingList.stream()
                .filter(reserveMeeting -> reserveMeeting.getStatus()==0)
                .collect(Collectors.toList());//未取消的会议
        List<ReserveMeeting> cancelMeetings=reserveMeetingList.stream()
                .filter(re->re.getStatus()==1)
                .collect(Collectors.toList());//取消的会议

        model.addAttribute("ms", reserveMeetings);
        model.addAttribute("cms", cancelMeetings);
        return "notifications";
    }

    /**
     * 到达我的预定页面
     *
     * @return
     */
    @RequestMapping("to_myOrders")
    public String toMybookings(Model model,HttpSession session ,CommonQuery query) {
        Employee currentUser = this.getCurrentUser(session);
        query.setReservationId(currentUser.getEmployeeId());
        List<ReserveMeeting> allReserveMeeting = reserveMeetingService.getAllReserveMeeting(query);

        model.addAttribute("mlist",allReserveMeeting);
        return "myOders";
    }

    /**
     * 预定会议
     * @return
     */
    @RequestMapping("to_ordermeeting")
    public String toOrderMeeting(Model model,CommonQuery query){
        List<MeetingRoom> meetingRoomList =reserveMeetingService.getAllRoom(query);
        List<Employee> emps = employeeService.getAllEmpsByStatus(null);
        model.addAttribute("emps",emps);
        model.addAttribute("mrs",meetingRoomList);
        return "ordermeeting";
    }

    @PostMapping("orderMeeting")
    public String orderMeeting(ReserveMeeting reserveMeeting,Model model){

        return "forward:/admin/to_myOrders";
    }

    /**
     * 获取当前登录的用户信息
     * @param session
     * @return
     */
    private Employee getCurrentUser(HttpSession session){
        return (Employee) session.getAttribute("currentUser");
    }
}
