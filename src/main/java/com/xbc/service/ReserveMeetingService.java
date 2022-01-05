package com.xbc.service;

import com.xbc.mapper.ReserveMeetingMapper;
import com.xbc.pojo.Employee;
import com.xbc.pojo.MeetingRoom;
import com.xbc.pojo.ReserveMeeting;
import com.xbc.query.CommonQuery;
import com.xbc.vo.ReserveMeetingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReserveMeetingService {
    @Autowired
    ReserveMeetingMapper reserveMeetingMapper;

    public List<ReserveMeeting> getMeetingByParticipate(Integer employeeId) {
        return reserveMeetingMapper.getMeetingByParticipate(employeeId);
    }

    public List<ReserveMeeting> getAllReserveMeeting(CommonQuery query) {
        return reserveMeetingMapper.getAllReserveMeeting(query);
    }

    public Integer geTotal() {
        return reserveMeetingMapper.geTotal();
    }

    public ReserveMeeting getMeetingRoomDetails(Integer meetingId) {
        return reserveMeetingMapper.getMeetingRoomDetails(meetingId);
    }

    public List<Employee> getParticipateByMeetingId(Integer meetingId) {
        return reserveMeetingMapper.getParticipateByMeetingId(meetingId);
    }

    public List<MeetingRoom> getAllRoom(CommonQuery query) {
        return reserveMeetingMapper.getAllRoom(query);
    }
}
