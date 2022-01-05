package com.xbc.query;

import io.swagger.annotations.ApiModelProperty;

/**
 * @module:
 * @title: commonQuery
 * @description: TODO
 * @author: xbc
 * @date 2022年01月04日 20:16
 */
public class CommonQuery {
    @ApiModelProperty(value = "会议名")
    private String meetingName;
    @ApiModelProperty(value = "会议室名")
    private String roomName;
    @ApiModelProperty(value = "预定者的名字")
    private String reservationName;
    @ApiModelProperty(value = "预定者的id")
    private Integer reservationId;
    @ApiModelProperty(value = "页")
    private int page=1;
    @ApiModelProperty(value = "单页大小")
    private int pageSize = 10;

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getReservationName() {
        return reservationName;
    }

    public void setReservationName(String reservationName) {
        this.reservationName = reservationName;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
