package com.codesquad.issuetracker.service;

import com.codesquad.issuetracker.domain.Milestone;
import com.codesquad.issuetracker.repository.MilestoneRepository;
import com.codesquad.issuetracker.request.MilestoneRequest;
import com.codesquad.issuetracker.response.MilestoneResponse;
import com.codesquad.issuetracker.util.PaginationUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;

    private static final int PAGE_SIZE = 10;
    private static final String SORT_BY = "id";

    public MilestoneService(MilestoneRepository milestoneRepository) {
        this.milestoneRepository = milestoneRepository;
    }

    @Transactional
    public Milestone create(MilestoneRequest milestoneRequest) {
        Milestone milestone = milestoneRequest.create();
        return milestoneRepository.save(milestone);
    }

    public List<MilestoneResponse> getMilestones(int page) {
        return milestoneRepository.findAll(PaginationUtil.descendingPageable(page, PAGE_SIZE, SORT_BY)).stream()
                .map(milestone -> MilestoneResponse.create(milestone))
                .collect(Collectors.toList());
    }


    public MilestoneResponse getMilestone(Long id) {
        Milestone milestone = milestoneRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        return MilestoneResponse.create(milestone);
    }
}
