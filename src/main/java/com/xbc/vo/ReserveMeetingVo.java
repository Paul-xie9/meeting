package com.xbc.vo;

import java.util.Date;

/**
 * 项目分类:
 *
 * @author xbc
 * @module 接口分类
 * @title: ReserveMeetingVo
 * @description: 会议的vo关联searchmeetings.ftl页面
 * @date 2021年10月15日 15:37
 */
public class ReserveMeetingVo {
    private Integer meetingId;
    private String meetingName;//会议名
    private Integer meetingRoomId;//会议房间的id
    private String roomName;//会议室的名字
    private Integer reservationId;//预订会议人的ID
    private String reservationistName;//预定会议人的名字
//    private Integer numberOfParticipants;//参加人数
    private Date startTime;
    private Date endTime;
    private Date reservationTime;
//    private Date canceledTime;
//    private String description;
//    private Integer status;//状态（0没取消的会议 1取消的会议）
//    private String canceledReason;


    public Date getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Date reservationTime) {
        this.reservationTime = reservationTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public Integer getMeetingRoomId() {
        return meetingRoomId;
    }

    public void setMeetingRoomId(Integer meetingRoomId) {
        this.meetingRoomId = meetingRoomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public String getReservationistName() {
        return reservationistName;
    }

    public void setReservationistName(String reservationistName) {
        this.reservationistName = reservationistName;
    }
}
