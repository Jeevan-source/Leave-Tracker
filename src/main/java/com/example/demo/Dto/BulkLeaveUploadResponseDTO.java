
package com.example.demo.Dto;
import java.util.List;

public class BulkLeaveUploadResponseDTO {
    private int total;
    private int success;
    private int failed;
    private List<String> errorMessages;

    public BulkLeaveUploadResponseDTO(int total, int success, int failed, List<String> errorMessages) {
        this.total = total;
        this.success = success;
        this.failed = failed;
        this.errorMessages = errorMessages;
    }


}

