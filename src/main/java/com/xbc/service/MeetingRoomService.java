package com.xbc.service;

import com.xbc.mapper.MeetingRoomMapper;
import com.xbc.pojo.MeetingRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingRoomService {

    @Autowired
    MeetingRoomMapper meetingRoomMapper;

    public  MeetingRoom getMeetingRoomDetails(Integer roomId) {
        return meetingRoomMapper.getMeetingRoomById(roomId);
    }

    public List<MeetingRoom> getAllMeetingRoom() {
        return meetingRoomMapper.getAllMeetingRoom();
    }

    public int updateRoom(MeetingRoom meetingRoom) {
        return meetingRoomMapper.updateRoom(meetingRoom);
    }

    public int AddMeetingRoom(MeetingRoom meetingRoom) {
        return meetingRoomMapper.AddMeetingRoom(meetingRoom);
    }
}
