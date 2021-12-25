package com.kenza.tnt.block

import com.kenza.tnt.domain.FortuneAttribute
import com.kenza.tnt.utils.getFortuneEnchantment
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.WoodenButtonBlock
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.util.ActionResult
import net.minecraft.world.World

class TntItem(block: Block?, settings: Settings?) : BlockItem(block, settings) {

//    override fun isEnchantable(stack: ItemStack?): Boolean {
//        return true
//    }


    override fun hasGlint(stack: ItemStack?): Boolean {
        return super.hasGlint(stack)
    }

    override fun onCraft(stack: ItemStack?, world: World?, player: PlayerEntity?) {
        super.onCraft(stack, world, player)
    }

    override fun useOnBlock(context: ItemUsageContext?): ActionResult {
        return super.useOnBlock(context)
    }


    override fun getBlock(): Block {
        return super.getBlock()
    }

    override fun place(context: ItemPlacementContext, state: BlockState): Boolean {

        context.stack.nbt?.let { nbt ->
//            nbt[""]
            val level = nbt.getFortuneEnchantment()
            (state.block as? FortuneAttribute)?.fortuneLevel = level
        }


        return super.place(context, state)
    }
}