package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.LeaveApplicationDTO;
import com.example.demo.Dto.LeaveFilterDTO;
import com.example.demo.Dto.LeaveSummaryDto;
import com.example.demo.service.LeaveApplicationService;

@RestController
@RequestMapping("/api/leaves")
public class LeaveTrackerController {

	private final LeaveApplicationService leaveApplicationService;

	public LeaveTrackerController(LeaveApplicationService leaveApplicationService) {
		this.leaveApplicationService = leaveApplicationService;
	}

	@PostMapping("/filter")
	public List<LeaveApplicationDTO> filterLeaves(@RequestBody LeaveFilterDTO filterDTO) {
		return leaveApplicationService.filterLeaves(filterDTO);
	}

	@GetMapping("/summary/{employeeId}")
	public LeaveSummaryDto getLeaveSummary(@PathVariable Long employeeId) {
		return leaveApplicationService.getLeaveSummary(employeeId);
	}
}
