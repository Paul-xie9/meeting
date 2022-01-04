package com.xbc.mapper;

import com.xbc.pojo.MeetingRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MeetingRoomMapper {
    List<MeetingRoom> getAllMeetingRoom();

    MeetingRoom getMeetingRoomById(@Param("roomId") Integer roomId);

    int updateRoom(@Param("mr") MeetingRoom meetingRoom);

    int AddMeetingRoom(@Param("mr") MeetingRoom meetingRoom);
}
