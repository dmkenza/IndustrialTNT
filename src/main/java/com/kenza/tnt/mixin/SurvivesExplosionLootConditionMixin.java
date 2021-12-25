package com.kenza.tnt.mixin;

import com.kenza.tnt.domain.TNTAttribute;
import com.kenza.tnt.domain.TntType;
import net.minecraft.entity.Entity;
import net.minecraft.loot.condition.SurvivesExplosionLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SurvivesExplosionLootCondition.class)
public class SurvivesExplosionLootConditionMixin {

    @Inject(method = "test(Lnet/minecraft/loot/context/LootContext;)Z", at = @At("HEAD"), cancellable = true)
    public void test(LootContext lootContext, CallbackInfoReturnable<Boolean> cir) {
        try{
            Entity thisEntity = lootContext.requireParameter(LootContextParameters.THIS_ENTITY);

            if(thisEntity instanceof TNTAttribute){
                if(((TNTAttribute) thisEntity).getTntType() == TntType.Industrial){
                    cir.setReturnValue(true);
                    cir.cancel();
                }
            }
        }catch (Exception e){}
    }

}
