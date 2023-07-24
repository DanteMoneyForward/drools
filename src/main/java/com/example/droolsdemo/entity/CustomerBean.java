package com.example.droolsdemo.entity;

import lombok.Data;

@Data
public class CustomerBean {
    private String code;
    private String name;
    private String departmentName;
    private String contactName;
    private String honorificTitle;
    private String zipCode;
    private String addressCode;
    private String address1;
    private String address2;
    private String email;
    private boolean usingMailingAgent;
    private String usingWebIssue;
    private String usingEmail;
    private int line;

}
