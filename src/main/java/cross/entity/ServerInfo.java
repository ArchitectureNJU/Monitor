package cross.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cxworks on 17-4-13.
 */
public class ServerInfo {

    String ip;
    int availableProcessors;
    long freeMemory;
    long totalMemory;
    long maxMemory;
    boolean running;
    Date date;


    public ServerInfo(){
        Runtime runtime=Runtime.getRuntime();
        availableProcessors=runtime.availableProcessors();
        freeMemory=runtime.freeMemory();
        totalMemory=runtime.totalMemory();
        maxMemory=runtime.maxMemory();
        date=new Date(Calendar.getInstance().getTimeInMillis());
        try {
            String ip=Inet4Address.getLocalHost().getHostAddress();
            running=true;
            this.ip=ip;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            this.ip="0.0.0.0";
        }
    }

    @Override
    public String toString() {
        ObjectMapper mapper=new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void selfCall(){
        if (Calendar.getInstance().getTimeInMillis()-date.getTime()>=180000){
            running=false;
        }
    }


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getAvailableProcessors() {
        return availableProcessors;
    }

    public void setAvailableProcessors(int availableProcessors) {
        this.availableProcessors = availableProcessors;
    }

    public long getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(long freeMemory) {
        this.freeMemory = freeMemory;
    }

    public long getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(long totalMemory) {
        this.totalMemory = totalMemory;
    }

    public long getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(long maxMemory) {
        this.maxMemory = maxMemory;
    }
}