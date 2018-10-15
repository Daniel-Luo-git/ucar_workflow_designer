package com.ucar.workflow.dao;


import com.ucar.workflow.entity.Group;

import java.util.List;

public interface GroupDao {
    List<Group> findGroups();

}
