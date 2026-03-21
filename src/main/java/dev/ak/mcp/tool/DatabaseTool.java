package dev.ak.mcp.tool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class DatabaseTool {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseTool(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @McpTool(
        name = "get_invoice_details",
        description = "Get invoice details using invoice number"
    )
    public List<Map<String, Object>> getInvoiceDetails(String invoiceNumber) {

        log.info("Calling get_invoice_details");

        String sql = """
            SELECT i.invoice_number, v.name AS vendor, i.amount, i.status
            FROM invoice i
            JOIN vendor v ON i.vendor_id = v.id
            WHERE i.invoice_number = ?
        """;

        return jdbcTemplate.queryForList(sql, invoiceNumber);
    }

    @McpTool(
        name = "list_pending_invoices",
        description = "Get all pending invoices"
    )
    public List<Map<String, Object>> getPendingInvoices() {

        log.info("Calling list_pending_invoices");

        String sql = """
            SELECT i.invoice_number, v.name, i.amount
            FROM invoice i
            JOIN vendor v ON i.vendor_id = v.id
            WHERE i.status = 'PENDING'
        """;

        return jdbcTemplate.queryForList(sql);
    }
}