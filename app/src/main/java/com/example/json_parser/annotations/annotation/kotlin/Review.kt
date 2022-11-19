package com.example.json_parser.annotations.annotation.kotlin

annotation class Review(
    val status: ReviewStatus = ReviewStatus.PENDING,
    val comments: String = ""
) {
    public enum class ReviewStatus {
        PENDING,
        FAILED,
        PASSED,
        PASSED_WITH_CHANGES
    }
}
