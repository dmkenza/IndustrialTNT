package com.kenza.tnt.mixin;


import com.kenza.tnt.domain.TNTAttribute;
import com.kenza.tnt.domain.TntType;
import com.kenza.tnt.ext.ExplosionExtImpl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

@Mixin(Explosion.class)
public class ExplosionMixin {


    @Shadow
    private Entity entity;

    @Shadow
    private float power;

    private WeakReference<Entity> capturedEntity;

    ExplosionExtImpl explosionExt = new ExplosionExtImpl();

    @ModifyArg(method = "affectWorld(Z)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;getDroppedStacks(Lnet/minecraft/loot/context/LootContext$Builder;)Ljava/util/List;"),
            index = 0)
    private LootContext.Builder replaceLootContext(LootContext.Builder builder) {
        if(entity == null){
            return builder;
        }else{
            return explosionExt.replaceLootContext(builder, entity, power);
        }

    }


    @ModifyVariable(method = "collectBlocksAndDamageEntities()V", at = @At("STORE"), ordinal = 0)
    private Entity captureEntityFor_setNoItemDamage(Entity entity) {
        this.capturedEntity = new WeakReference<>(entity);
        return entity;
    }




    @ModifyArg(method = "collectBlocksAndDamageEntities()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"), index = 1)
    private float setNoItemDamage_in_collectBlocksAndDamageEntities(float value) {
        if (this.capturedEntity.get() instanceof ItemEntity) {
            if(entity instanceof TNTAttribute){
                if(((TNTAttribute) entity).getTntType() == TntType.Industrial){
                    return 0f;
                }
            }
        } else {
            return value;
        }
        return value;
    }


}
