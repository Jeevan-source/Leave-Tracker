package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.TeamMemberSummaryDTO;
import com.example.demo.service.TeamService;

@RestController
@RequestMapping("/api/team")
public class TeamController {

	private final TeamService teamService;

	public TeamController(TeamService teamService) {
		this.teamService = teamService;
	}

	@GetMapping("/manager/{managerId}")
	public List<TeamMemberSummaryDTO> getTeamSummary(@PathVariable Long managerId) {
		return teamService.getTeamMembersByManagerId(managerId); // ✅ Correct method call
	}

}
