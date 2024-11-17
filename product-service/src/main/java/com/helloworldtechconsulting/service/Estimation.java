package com.helloworldtechconsulting.service;

import dev.langchain4j.model.output.structured.Description;

public class Estimation {

    @Description("reason for the estimation")
    public String reason;

    @Description("the estimated no of orders")
    public String orders;
}
