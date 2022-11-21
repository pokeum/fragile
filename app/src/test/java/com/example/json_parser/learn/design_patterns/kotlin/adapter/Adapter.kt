package com.example.json_parser.learn.design_patterns.kotlin.adapter

import com.example.json_parser.learn.design_patterns.kotlin.adapter.model.*
import org.junit.Test

/**
 * Adapter design pattern is to convert one interface to another interface.
 */
class Adapter {

    @Test
    fun main() {
        // cellPhone(charger(usPowerOutlet()))
        cellPhone(charger(usPowerOutlet().toEUPlug()).toUsbTypeC())
    }

    private fun cellPhone(chargeCable: UsbTypeC) {
        if (chargeCable.hasPower) {
            println("I've Got The Power!")
        } else {
            println("No power")
        }
    }

    private fun charger(plug: EUPlug): UsbMini {
        return object : UsbMini {
            override val hasPower: Power = Power.valueOf(plug.hasPower)
        }
    }

    private fun usPowerOutlet(): USPlug {
        return object : USPlug {
            override val hasPower: Int = 1
        }
    }

    //================================================================//
    //==================== Adapter design pattern ====================//
    //================================================================//

    private fun USPlug.toEUPlug(): EUPlug {
        val hasPower: String = if (this.hasPower == 1) "TRUE" else "FALSE"
        return object : EUPlug {
            override val hasPower: String = hasPower
        }
    }

    private fun UsbMini.toUsbTypeC(): UsbTypeC {
        val hasPower: Boolean = this.hasPower == Power.TRUE
        return object : UsbTypeC {
            override val hasPower: Boolean = hasPower
        }
    }
}