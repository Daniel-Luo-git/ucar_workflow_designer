package com.ucar.workflow.controller;

import com.ucar.workflow.dao.CandidateDao;
import com.ucar.workflow.dao.GroupDao;
import com.ucar.workflow.entity.Candidate;
import com.ucar.workflow.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/Candidate")
public class GroupController {

    @Autowired
    private GroupDao groupDao;
    @Autowired
    private CandidateDao candidateDao;
    List<String> nameList = new ArrayList<String>();
    Map<String,List> groupAndCandidate=new HashMap<String, List>();

    @RequestMapping(value = "/getGroup", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List> getGroupNameList(HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","GET,POST");
        List<Group> groups = groupDao.findGroups();

        for (Group group : groups) {
            nameList.add(group.getGroupName());
            List<String> name=new ArrayList<String>();

            //System.out.println(group.getGroupName());
            List<Candidate> cdi=candidateDao.findCandidates(group.getGroupId());
            for(Candidate candidate:cdi){
                name.add(candidate.getName());
                //System.out.println(candidate.getName());
            }
            groupAndCandidate.put(group.getGroupName(),name);
        }
        System.out.println(groupAndCandidate);
        return groupAndCandidate;
    }

}
