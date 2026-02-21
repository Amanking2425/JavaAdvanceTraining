package com.java.CRM;

import jakarta.persistence.*;

@Entity
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leadId;
    private String name;
    private String contactInfo;

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	private String source;
    private String status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private SalesEmployee employee;

    public Long getLeadId() {
        return leadId;
    }

    public void setLeadId(Long leadId) {
        this.leadId = leadId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SalesEmployee getEmployee() {
        return employee;
    }

    public void setEmployee(SalesEmployee employee) {
        this.employee = employee;
    }
}