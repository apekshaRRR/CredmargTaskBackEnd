package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.model.Vendor;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PostMapping("/vendor")
    public Vendor createVendor(@RequestBody Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/vendors")
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @PostMapping("/send-email")
    public String sendEmail(@RequestBody List<String> vendorEmails) {
        StringBuilder emailLog = new StringBuilder("Emails sent:\n");
        for (String email : vendorEmails) {
            Vendor vendor = vendorRepository.findByEmail(email);
            if (vendor != null) {
                emailLog.append("Sending payment to vendor ")
                        .append(vendor.getName())
                        .append(" at UPI ")
                        .append(vendor.getUpi())
                        .append("\n");
            }
        }
        return emailLog.toString();
    }

    @GetMapping("/sent-emails")
    public String viewSentEmails() {
        // Mocked sent emails log for demo purposes
        return "Sent Emails Log:\nSending payment to vendor A at UPI 123456\nSending payment to vendor B at UPI 789012";
    }
}
