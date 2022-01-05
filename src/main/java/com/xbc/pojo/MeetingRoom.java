package com.xbc.pojo;

/**
 * 会议室
 */
public class MeetingRoom {
    private Integer roomId;
    private Integer roomNum;
    private String roomName;
    private Integer capacity;
    private Integer status;//状态（0可用 1被占用）
    private String description;

    public MeetingRoom() {
    }

    @Override
    public String toString() {
        return "MeetingRoom{" +
                "roomId=" + roomId +
                ", roomNum=" + roomNum +
                ", roomName='" + roomName + '\'' +
                ", capacity=" + capacity +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }

    public MeetingRoom(Integer roomId, Integer roomNum, String roomName, Integer capacity, Integer status, String description) {
        this.roomId = roomId;
        this.roomNum = roomNum;
        this.roomName = roomName;
        this.capacity = capacity;
        this.status = status;
        this.description = description;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
