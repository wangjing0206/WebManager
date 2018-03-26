package com.unisys.omse.webmanager.po;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.Date;

public class TblNode {
    private int id;
    private int nodeid;
    private String nodename;
    private int configid;
    private String ipaddress;
    private String status;
    @JSONField(format="yyyy-MM-dd hh:mm")
    private Date laststarttime;
    @JSONField(format="yyyy-MM-dd hh:mm")
    private Date laststoptime;

    public TblNode() {
    }

    public TblNode(int nodeid, String nodename, int configid, String ipaddress, String status, Date laststarttime, Date laststoptime) {
        this.nodeid = nodeid;
        this.nodename = nodename;
        this.configid = configid;
        this.ipaddress = ipaddress;
        this.status = status;
        this.laststarttime = laststarttime;
        this.laststoptime = laststoptime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNodeid() {
        return nodeid;
    }

    public void setNodeid(int nodeid) {
        this.nodeid = nodeid;
    }

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public int getConfigid() {
        return configid;
    }

    public void setConfigid(int configid) {
        this.configid = configid;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLaststarttime() {
        return laststarttime;
    }

    public void setLaststarttime(Date laststarttime) {
        this.laststarttime = laststarttime;
    }

    public Date getLaststoptime() {
        return laststoptime;
    }

    public void setLaststoptime(Date laststoptime) {
        this.laststoptime = laststoptime;
    }

    @Override
    public String toString() {
        return "TblNode{" +
                "id=" + id +
                ", nodeid=" + nodeid +
                ", nodename='" + nodename + '\'' +
                ", configid=" + configid +
                ", ipaddress='" + ipaddress + '\'' +
                ", status='" + status + '\'' +
                ", laststarttime=" + laststarttime +
                ", laststoptime=" + laststoptime +
                '}';
    }
}
