package com.example.json_parser.learn_design_patterns.kotlin.adapter.model

interface UsbMini {
    val hasPower: Power
}

enum class Power {
    TRUE, FALSE
}