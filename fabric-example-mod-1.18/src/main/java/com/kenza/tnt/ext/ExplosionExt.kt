package com.kenza.tnt.ext

import com.kenza.tnt.domain.TNTAttribute
import com.kenza.tnt.domain.TntType
import com.kenza.tnt.domain.TntType.*
import com.mojang.brigadier.exceptions.CommandSyntaxException
import net.minecraft.entity.Entity
import net.minecraft.item.ItemStack
import net.minecraft.loot.context.LootContext
import net.minecraft.loot.context.LootContextParameters
import net.minecraft.nbt.StringNbtReader

interface ExplosionExt {

}

class ExplosionExtImpl() {


    fun replaceLootContext(
        builder: LootContext.Builder,
        entity: Entity,
        power: Float
    ): LootContext.Builder {

        (entity as? TNTAttribute)?.let {

            when (entity.tntType) {
                Default -> {

                }
                Industrial -> {
                    builder.parameter(LootContextParameters.EXPLOSION_RADIUS, power)
                    builder.parameter(LootContextParameters.TOOL, getDiamondPickaxeItemStack(3))
                }
            }


        }
        return builder
    }


    private fun getDiamondPickaxeItemStack(fortuneLevel: Int): ItemStack {
        val json =
            "{Count:1b,id:\"minecraft:diamond_pickaxe\",tag:{Enchantments:[{id:\"minecraft:fortune\",lvl:${fortuneLevel}s}]}}"
        return ItemStack.fromNbt(StringNbtReader.parse(json))
    }


}