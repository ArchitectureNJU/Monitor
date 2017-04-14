package cross.controller;

import com.sun.deploy.net.HttpResponse;
import cross.entity.ServerInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by cxworks on 17-4-14.
 */
@Controller
public class State {

    private volatile Map<String,ServerInfo> state;
    public State(){state=new ConcurrentHashMap<>();}

    @RequestMapping(value = "/beat",method = RequestMethod.POST)
    @ResponseBody
    public synchronized String beat(@ModelAttribute("ServerInfo")ServerInfo serverInfo){
        state.put(serverInfo.getIp(),serverInfo);
        return "OK";
    }


    @RequestMapping(value = "/state",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public synchronized Map<String,ServerInfo> queryState(HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin", "*");
        state.entrySet().forEach(e->e.getValue().selfCall());
        return state;
    }



}
