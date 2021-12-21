package com.kenza.tnt

import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.LogManager

class IndustrialTNTMod : ModInitializer {


    override fun onInitialize() {
        debug("Hello Fabric world!")
        openLastWorldOnInit()
        Blocks.registerAll()
        debug("x")

    }

    companion object {
        // This logger is used to write text to the console and the log file.
        // It is considered best practice to use your mod id as the logger's name.
        // That way, it's clear which mod wrote info, warnings, and errors.
        @JvmField
        val LOGGER = LogManager.getLogger(MOD_ID)
    }
}

