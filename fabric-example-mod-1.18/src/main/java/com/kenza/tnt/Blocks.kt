package com.kenza.tnt

import com.kenza.tnt.domain.TNTAttribute
import com.kenza.tnt.domain.TntType
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.*
import net.minecraft.block.Blocks
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.TntEntity
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.registry.Registry
import net.minecraft.world.World

object Blocks {

    private lateinit var INDUSTRIAL_TNT_BLOCK: Block

    fun registerAll() {
        INDUSTRIAL_TNT_BLOCK = registerBlock(
            INDUSTRIAL_TNT_BLOCk_ID, TntBlock(
                FabricBlockSettings.of(Material.TNT)
            ).apply {
                (this as TNTAttribute).tntType = TntType.Industrial
            }
        )
    }

    fun getTNTBlockByType(type: TntType?): BlockState {
        return when (type) {
            TntType.Industrial -> INDUSTRIAL_TNT_BLOCK
            else -> Blocks.TNT
        }.defaultState
    }

    fun getTNTEntityByType(
        type: TntType?,
        world: World,
        pos: BlockPos,
        igniter: LivingEntity
    ): TntEntity {

        val tntEntity = TntEntity(
            world, pos.x.toDouble() + 0.5,
            pos.y.toDouble(), pos.z.toDouble() + 0.5, igniter
        )

         when (type) {
            TntType.Industrial -> {
                (tntEntity as TNTAttribute).tntType = TntType.Industrial
            }
            else -> {}

        }

        return tntEntity
    }


    private fun registerBlock(name: String, block: Block): Block {
        registerBlockItem(name, block)
        return Registry.register(Registry.BLOCK, Identifier(MOD_ID, name), block)
    }

    private fun registerBlockItem(name: String, block: Block): Item? {
        return Registry.register(
            Registry.ITEM, Identifier(MOD_ID, name),
            BlockItem(block, FabricItemSettings().group(ItemGroup.MISC))
        )
    }




}