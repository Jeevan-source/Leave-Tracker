package com.example.demo.controller;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Dto.HolidayDTO;
import com.example.demo.service.HolidayService;

@RestController
@RequestMapping("/api/holidays")
public class HolidayController {

	private final HolidayService holidayService;

	public HolidayController(HolidayService holidayService) {
		this.holidayService = holidayService;
	}

	@PostMapping
	public HolidayDTO addHoliday(@RequestBody HolidayDTO dto) {
		return holidayService.addHoliday(dto);
	}

	@GetMapping
	public List<HolidayDTO> getAllHolidays(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
		return holidayService.getAllHolidays(start, end);
	}

	@DeleteMapping("/{id}")
	public void deleteHoliday(@PathVariable Long id) {
		holidayService.deleteHoliday(id);
	}

	@PostMapping("/{id}/reprocess")
	public void reprocessLeave(@PathVariable Long id) {
		holidayService.reprocessLeaveForHoliday(id);
	}

	@PostMapping("/import")
	public ResponseEntity<String> importHolidays(@RequestParam("file") MultipartFile file) {
		String message = holidayService.importHolidays(file);
		return ResponseEntity.ok(message);
	}

	@GetMapping("/export")
	public ResponseEntity<byte[]> exportHolidays() {
		ByteArrayInputStream stream = holidayService.exportHolidays();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=holidays.csv");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("text/csv"))
				.body(stream.readAllBytes());
	}
}
