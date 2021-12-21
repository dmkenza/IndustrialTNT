package com.kenza.tnt

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.TntEntityRenderer
import org.apache.logging.log4j.LogManager

class IndustrialTNTMod : ModInitializer {


    override fun onInitialize() {
        debug("Hello Fabric world!")
        openLastWorldOnInit()
        Blocks.registerAll()
        debug("x")

//        TntBlock

    }


//    fun x1(){
//        EntityRendererRegistry.register(DirTnt.DIRT_TNT_ENTITY_TYPE) { context: EntityRendererFactory.Context? ->
//            // use a dirty TNT renderer
//            val renderer: Dirtable = TntEntityRenderer(context) as Dirtable
//            renderer.makeDirty()
//            renderer as TntEntityRenderer
//        }
//    }


    companion object {
        // This logger is used to write text to the console and the log file.
        // It is considered best practice to use your mod id as the logger's name.
        // That way, it's clear which mod wrote info, warnings, and errors.
        @JvmField
        val LOGGER = LogManager.getLogger(MOD_ID)
    }
}

