package com.xbc.mapper;

import com.xbc.pojo.Employee;
import com.xbc.pojo.MeetingRoom;
import com.xbc.pojo.ReserveMeeting;
import com.xbc.query.CommonQuery;
import com.xbc.vo.ReserveMeetingVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReserveMeetingMapper {
    List<ReserveMeeting> getMeetingByParticipate(@Param("employeeId") Integer employeeId);

    ReserveMeeting getMeetingRoomDetails(@Param("meetingId") Integer meetingId);

    List<ReserveMeeting> getAllReserveMeeting(@Param("que") CommonQuery query);

    Integer geTotal();

    List<Employee> getParticipateByMeetingId(@Param("meetingId")Integer meetingId);

    List<MeetingRoom> getAllRoom(@Param("que")CommonQuery query);
}
