package com.ucar.workflow.dao;

import com.ucar.workflow.entity.Candidate;

import java.util.List;

public interface CandidateDao {
    List<Candidate> findCandidates(int i);
}
