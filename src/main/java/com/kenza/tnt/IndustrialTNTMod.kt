package com.kenza.tnt

import com.google.gson.Gson
import com.kenza.tnt.block.Blocks
import com.kenza.tnt.utils.openLastWorldOnInit
import com.minelittlepony.common.event.ScreenInitCallback
import drawer.ForNbtCompound
import drawer.readFrom
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.block.entity.BlockEntity
import net.minecraft.client.gui.screen.Screen
import net.minecraft.entity.Entity
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolItem
import net.minecraft.item.ToolMaterial
import net.minecraft.loot.context.LootContext
import net.minecraft.loot.context.LootContextParameters
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayNetworkHandler
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.Vec3d
import org.apache.logging.log4j.LogManager
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents
import net.minecraft.client.MinecraftClient
import net.minecraft.nbt.StringNbtReader
import net.minecraft.network.ClientConnection
import net.minecraft.network.PacketByteBuf
import org.spongepowered.asm.mixin.Mixin

class IndustrialTNTMod : ModInitializer {



    override fun onInitialize() {
//        openLastWorldOnInit()


        Blocks.registerAll()


        debug("xx")
//        GlobalScope.launch {
//            try {
//                delay(100)
//            }catch (e : Exception){
//                e.printStackTrace()
//            }
//        }


//        ServerPlayConnectionEvents.INIT.register(object : ServerPlayConnectionEvents.Init {
//            override fun onPlayInit(handler: ServerPlayNetworkHandler, server: MinecraftServer) {
//
//
//                GlobalScope.launch {
//                    delay(3000)
//                    val x = handler.player.mainHandStack
//
//
//                    val y1 = "{Count:1b,Slot:1b,id:\"minecraft:diamond_pickaxe\",tag:{Damage:3,Enchantments:[{id:\"minecraft:efficiency\",lvl:3s},{id:\"minecraft:unbreaking\",lvl:3s},{id:\"minecraft:fortune\",lvl:2s}]}}"
//
//                    val t1 = "{Damage:3,Enchantments:[{id:\"minecraft:efficiency\",lvl:3s},{id:\"minecraft:unbreaking\",lvl:3s},{id:\"minecraft:fortune\",lvl:2s}]}"
//                    try {
//
//                    val nbt1 = x.toString()
////
////                    var x2 = StringNbtReader.parse(t1)
////                    var x3 = StringNbtReader.parse(nbt1)
//
//
////                        val json = Json.encodeToString(nbt)
////                        val t1 = Json.decodeFromString<NbtCompound>(json)
//                        debug("x" + nbt1)
//                    }catch (e: Exception){
//                        debug("ss" + e)
//                    }
//
//                }
//            }
//
//        })


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

