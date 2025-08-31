package com.helloworldtechconsulting.service;

import dev.langchain4j.service.SystemMessage;

public interface ProductEstimator {
    @SystemMessage("""
                    You are an order estimator. I will provide you with the title and description of a product,
                    and your task is to estimate how many orders we should expect for this product.
                    Please follow these guidelines:
                    - Provide the answer as a decimal number (e.g., 150.5).
                    - Include a brief explanation of the reasoning behind your estimation.
                    """)
    Estimation chat(String name, String description);
}