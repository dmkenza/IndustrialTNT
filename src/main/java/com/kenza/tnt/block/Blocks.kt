package com.kenza.tnt.block

import com.kenza.tnt.INDUSTRIAL_TNT_BLOCk_ID
import com.kenza.tnt.MOD_ID
import com.kenza.tnt.debug
import com.kenza.tnt.domain.FortuneAttribute
import com.kenza.tnt.domain.TNTAttribute
import com.kenza.tnt.domain.TntType
import com.kenza.tnt.utils.isRenderThread
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.*
import net.minecraft.block.Blocks
import net.minecraft.client.MinecraftClient
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.TntEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.server.MinecraftServer
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.registry.Registry
import net.minecraft.world.World

object Blocks {

    @JvmStatic
    val INDUSTRIAL_TNT_BLOCK = registerBlock(
        INDUSTRIAL_TNT_BLOCk_ID, TntBlock(
            FabricBlockSettings.of(Material.TNT).sounds(BlockSoundGroup.GRASS)
        ).apply {
            val block = this
            (block as TNTAttribute).tntType = TntType.Industrial
        }
    )

    fun clientTest() {

    }


    fun registerAll() {

    }

    fun getTNTBlockByType(type: TntType?): BlockState {
        return when (type) {
            TntType.Industrial -> INDUSTRIAL_TNT_BLOCK
            else -> Blocks.TNT
        }.defaultState
    }

    fun getTNTEntityByType(
        block: Block,
        world: World,
        pos: BlockPos,
        igniter: LivingEntity? = null
    ): TntEntity {

        val tntEntity = TntEntity(
            world, pos.x.toDouble() + 0.5,
            pos.y.toDouble(), pos.z.toDouble() + 0.5, igniter
        )

        (block as? TNTAttribute)?.let {
            (tntEntity as TNTAttribute).tntType = it.tntType
        }

        (block as? FortuneAttribute)?.let {
            (tntEntity as FortuneAttribute).fortuneLevel = it.fortuneLevel
        }

        return tntEntity
    }


    private fun registerBlock(name: String, block: Block): Block {
        registerBlockItem(name, block)
        return Registry.register(Registry.BLOCK, Identifier(MOD_ID, name), block)
    }

    private fun registerBlockItem(name: String, block: Block): Item? {
        return Registry.register(
            Registry.ITEM, Identifier(MOD_ID, name), TntItem(block, FabricItemSettings().group(ItemGroup.REDSTONE))
        )
    }


}