package com.kenza.tnt.mixin;

import com.kenza.tnt.domain.TNTAttribute;
import com.kenza.tnt.domain.TntType;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.function.ExplosionDecayLootFunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ExplosionDecayLootFunction.class)
public class ExplosionDecayLootFunctionMixin {



    @Inject(method = "process(Lnet/minecraft/item/ItemStack;Lnet/minecraft/loot/context/LootContext;)Lnet/minecraft/item/ItemStack;", at = @At("HEAD"), cancellable = true)
    public void process(ItemStack stack, LootContext context, CallbackInfoReturnable<ItemStack> cir) {
        try{
            Entity thisEntity = context.requireParameter(LootContextParameters.THIS_ENTITY);

            if(thisEntity instanceof TNTAttribute){
                if(((TNTAttribute) thisEntity).getTntType() == TntType.Industrial){
                    cir.setReturnValue(stack);
                    cir.cancel();
                }
            }
        }catch (Exception e){}

    }
}
