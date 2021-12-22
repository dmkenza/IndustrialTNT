package com.kenza.tnt.mixin;

import com.kenza.tnt.block.Blocks;
import com.kenza.tnt.domain.TNTAttribute;
import com.kenza.tnt.domain.TntType;
import net.minecraft.block.Block;
import net.minecraft.block.TntBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TntBlock.class)
public abstract class TntBlockMixin implements TNTAttribute {

    private TntType tntType = TntType.Default;

    @NotNull
    @Override
    public TntType getTntType() {
        return tntType;
    }

    @Override
    public void setTntType(@NotNull TntType tntType) {
        this.tntType = tntType;
    }


    @Inject(method = "primeTnt(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/LivingEntity;)V", at = @At("HEAD"), cancellable = true)
    private static void primeDirtTnt(World world, BlockPos pos, LivingEntity igniter, CallbackInfo ci) {

        Block block = world.getBlockState(pos).getBlock();
        if (block instanceof TNTAttribute && !world.isClient) {
            TntType type = ((TNTAttribute) block).getTntType();

            Entity tntEntity = Blocks.INSTANCE.getTNTEntityByType(type, world, pos, igniter);
            world.spawnEntity(tntEntity);
            world.playSound(null, tntEntity.getX(), tntEntity.getY(), tntEntity.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
            ci.cancel();
        }
    }

    //
//    @Inject(method = "onDestroyedByExplosion", at = @At("HEAD"), cancellable = true)
//    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion, CallbackInfo ci) {
////        if (world.isClient) {
////            return;
////        }
//
//        Block block = world.getBlockState(pos).getBlock();
//        if (block instanceof TNTAttribute) {
//            TntType type = ((TNTAttribute) block).getTntType();
//
//            TntEntity tntEntity = Blocks.INSTANCE.getTNTEntityByType(type, world, pos, null);
//            tntEntity.setFuse((short) (world.random.nextInt(tntEntity.getFuse() / 4) + tntEntity.getFuse() / 8));
//            world.spawnEntity(tntEntity);
//            ci.cancel();
//        }
//    }
}
