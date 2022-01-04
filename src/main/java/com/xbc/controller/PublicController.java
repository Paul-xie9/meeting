package com.xbc.controller;

import com.xbc.pojo.Department;
import com.xbc.pojo.Employee;
import com.xbc.pojo.MeetingRoom;
import com.xbc.pojo.ReserveMeeting;
import com.xbc.query.CommonQuery;
import com.xbc.service.DepartmentService;
import com.xbc.service.EmployeeService;
import com.xbc.service.MeetingRoomService;
import com.xbc.service.ReserveMeetingService;
import com.xbc.vo.ReserveMeetingVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

@Controller
public class PublicController {
    //一页放10条数据
    public static final Integer PAGE_SIZE = 10;
    @Autowired
    MeetingRoomService meetingRoomService;
    @Autowired
    ReserveMeetingService reserveMeetingService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentService departmentService;

    /**
     * 首页
     * @return
     */
    @RequestMapping("/")
    public String index(){
        return "login";
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @param model
     * @param httpSession
     * @return
     */
    @PostMapping("login")
    public String login(String username, String password, Model model, HttpSession httpSession){

        Employee employee = employeeService.login(username, password);
        if (employee == null) {
            model.addAttribute("error", "用户或密码输入错误，登陆失败!");
            return "forward:/";
        } else {
            if (employee.getStatus() == 0) {
                model.addAttribute("error", "用户待审批!");
                return "forward:/";
            } else if (employee.getStatus() == 2) {
                model.addAttribute("error", "用户审批未通过!");
                return "forward:/";
            } else {
                httpSession.setAttribute("currentUser", employee);
                return "redirect:admin/main";
            }
        }
    }

    /**
     * 注册用户页面
     * @return
     */
    @GetMapping("toRegister")
    public String toRegister(Model model){
        List<Department> departmentList = departmentService.getAllDeps();
        model.addAttribute("deps",departmentList);
        return "register";
    }

    /**
     * 注册用户
     * @param employeeName
     * @param username
     * @param password
     * @param phone
     * @param email
     * @param departmentId
     * @return
     */
    @PostMapping("register")
    public String register(String employeeName,String username,String password,String phone,String email,String departmentId ,Model model){
        if (employeeName==null){
            model.addAttribute("error","姓名不能为空！");
            return null;
        }
        if (username==null){
            model.addAttribute("error","账户名不能为空！");
            return null;
        }
        if (password==null){
            model.addAttribute("error","密码不能为空！");
            return null;
        }
        if (email==null){
            model.addAttribute("error","邮箱不能为空！");
            return null;
        }
        if (phone==null){
            model.addAttribute("error","电话不能为空！");
            return null;
        }
        int i = employeeService.register(employeeName,username,password,phone,email,departmentId);
        if (i>0){
            model.addAttribute("msg","用户注册成功！");
            return "redirect:/";
        }
        return "register";
    }

    /**
     * 推出登录
     * @param session
     * @return
     */
    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("currentUser");
        return "forward:/";
    }

    /**
     * 到会议室页面，并查出所以会议室
     *
     * @param model
     * @return
     */
    @RequestMapping("to_meetingrooms")
    public String toMeetingRooms(Model model) {
        List<MeetingRoom> meetingRoomList = meetingRoomService.getAllMeetingRoom();
        model.addAttribute("mrs", meetingRoomList);
        return "meetingrooms";
    }

    /**
     * 查看会议室详情
     *
     * @param roomId
     * @param model
     * @return
     */
    @RequestMapping("room_details")
    public String roomDetails(Integer roomId, Model model) {
        MeetingRoom meetingRoom = meetingRoomService.getMeetingRoomDetails(roomId);
        model.addAttribute("mr", meetingRoom);
        return "roomdetails";
    }

    /**
     * 到搜索会议的页面
     * @return
     */
    @RequestMapping("to_searchmeetings")
    public String toSearchMeetings(Model model,CommonQuery query) {
        int i = (query.getPage()-1) * query.getPageSize();
        query.setPage(i);
        List<ReserveMeetingVo> reserveMeetingList = reserveMeetingService.getAllReserveMeeting(query);
        Integer total = reserveMeetingService.geTotal();
        model.addAttribute("total",total);//总的数据条数
        model.addAttribute("page",1);
        System.out.println("total = " + total);
        int pageNum = total%PAGE_SIZE==0 ? total/PAGE_SIZE:total/PAGE_SIZE +1;
        model.addAttribute("pagenum",pageNum);
        System.out.println("pageNum = " + pageNum);
        model.addAttribute("meetings",reserveMeetingList);
        return "searchmeetings";
    }


    /**
     * 到达会议详细页面
     * @param meetingId
     * @return
     */
    @RequestMapping("to_meetingdetails")
    public String toMeetingDetail(Integer meetingId,Model model){
        ReserveMeeting meetingRoomDetails = reserveMeetingService.getMeetingRoomDetails(meetingId);
        model.addAttribute("meeting",meetingRoomDetails);
        List<Employee> employees = reserveMeetingService.getParticipateByMeetingId(meetingId);
        model.addAttribute("ems",employees);
        return "meetingdetails";
    }
}
