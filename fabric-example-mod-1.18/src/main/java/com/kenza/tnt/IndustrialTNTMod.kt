package com.kenza.tnt

import com.kenza.tnt.block.Blocks
import net.fabricmc.api.ModInitializer
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.Entity
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolItem
import net.minecraft.item.ToolMaterial
import net.minecraft.loot.context.LootContext
import net.minecraft.loot.context.LootContextParameters
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.Vec3d
import org.apache.logging.log4j.LogManager

class IndustrialTNTMod : ModInitializer {


    override fun onInitialize() {
        debug("Hello Fabric world!")
        openLastWorldOnInit()
        Blocks.registerAll()
        debug("x")


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

