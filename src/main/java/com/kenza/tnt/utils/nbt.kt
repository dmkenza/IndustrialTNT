package com.kenza.tnt.utils

import net.minecraft.enchantment.Enchantments
import net.minecraft.nbt.NbtByte
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtList

fun NbtCompound.getFortuneEnchantment() : Int {
    return (this[ENCHANTMENT_KEY] as? NbtList)?.asSequence()?.mapNotNull {
        it as? NbtCompound
    }?.firstOrNull {
          it[ENCHANTMENT_ID_KEY].toString() == ENCHANTMENT_FORTUNE_ID
    }?.let { enchantment ->
        return@let(enchantment[ENCHANTMENT_LVL_KEY] as? NbtByte)?.intValue()
    } ?: 0
}


const val ENCHANTMENT_KEY = "Enchantments"
const val ENCHANTMENT_LVL_KEY = "lvl"
const val ENCHANTMENT_ID_KEY = "id"

const val ENCHANTMENT_FORTUNE_ID = "\"minecraft:fortune\""
