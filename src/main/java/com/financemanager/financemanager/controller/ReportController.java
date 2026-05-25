package com.financemanager.financemanager.controller;

import com.financemanager.financemanager.dto.response.CategoryExpenseResponse;
import com.financemanager.financemanager.dto.response.MonthlySummaryResponse;
import com.financemanager.financemanager.dto.response.ReportResponse;
import com.financemanager.financemanager.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    // OVERALL REPORT
    @GetMapping("/overall")
    public ReportResponse getOverallReport() {

        return reportService.getOverallReport();
    }

    // MONTHLY SUMMARY
    @GetMapping("/monthly")
    public MonthlySummaryResponse getMonthlySummary(

            @RequestParam int month,

            @RequestParam int year
    ) {

        return reportService.getMonthlySummary(
                month,
                year
        );
    }

    // CATEGORY EXPENSE REPORT
    @GetMapping("/category-expenses")
    public List<CategoryExpenseResponse>
    getCategoryExpenseReport() {

        return reportService
                .getCategoryExpenseReport();
    }
}