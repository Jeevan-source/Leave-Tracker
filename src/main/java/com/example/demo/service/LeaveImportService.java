package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Dto.BulkLeaveUploadResponseDTO;
import com.example.demo.entity.LeaveImportLog;
import com.example.demo.repository.LeaveImportLogRepository;

@Service
public class LeaveImportService {

	private final LeaveImportLogRepository leaveImportLogRepository; // ✅ no manual = null, let Spring inject

	public LeaveImportService(LeaveImportLogRepository leaveImportLogRepository) {
		this.leaveImportLogRepository = leaveImportLogRepository;
	}

	public BulkLeaveUploadResponseDTO importLeaveData(MultipartFile file, String importedBy) {
		int total = 0, success = 0, failed = 0;
		List<String> errorMessages = new ArrayList<>();

		try {
			String filename = file.getOriginalFilename();

			// Simulated parsing logic
			total = 10;
			success = 8;
			failed = 2;
			errorMessages.add("Row 3: Invalid leave type");
			errorMessages.add("Row 7: Missing employee ID");

			LeaveImportLog log = LeaveImportLog.builder().fileName(filename).totalRows(total).successCount(success)
					.failureCount(failed).errorDetails(String.join("\n", errorMessages)).importedAt(LocalDateTime.now())
					.importedBy(importedBy).build();

			leaveImportLogRepository.save(log); // ✅ correct usage

			return new BulkLeaveUploadResponseDTO(total, success, failed, errorMessages);

		} catch (Exception e) {
			throw new RuntimeException("File processing failed", e);
		}
	}
}
