package com.kenza.tnt.mixin;

import com.kenza.tnt.block.Blocks;
import com.kenza.tnt.domain.FortuneAttribute;
import com.kenza.tnt.domain.TNTAttribute;
import com.kenza.tnt.domain.TntType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TntBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.ref.WeakReference;

@Mixin(TntBlock.class)
public abstract class TntBlockMixin extends Block implements TNTAttribute, FortuneAttribute {

    private TntType tntType = TntType.Default;

    private static final IntProperty FORTUNE_PROPERTY = IntProperty.of("fortune", 0, 3);


    public TntBlockMixin(Settings settings) {
        super(settings);
    }

    @NotNull
    @Override
    public TntType getTntType() {
        return tntType;
    }

    @Override
    public void setTntType(@NotNull TntType tntType) {
        this.tntType = tntType;
    }

    @Override
    public void setFortuneLevel(int level) {
        this.setDefaultState(getDefaultState().with(FORTUNE_PROPERTY, level));
    }

    @Override
    public int getFortuneLevel() {
        return getDefaultState().get(FORTUNE_PROPERTY);
    }

    @ModifyArg(method = "<init>(Lnet/minecraft/block/AbstractBlock$Settings;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/TntBlock;setDefaultState(Lnet/minecraft/block/BlockState;)V"),
            index = 0)

    private BlockState captureBlockState_in_Init(BlockState blockState) {
        return blockState.with(FORTUNE_PROPERTY, 0);
    }



    @Inject(method = "primeTnt(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/LivingEntity;)V", at = @At("HEAD"), cancellable = true)
    private static void primeDirtTnt(World world, BlockPos pos, LivingEntity igniter, CallbackInfo ci) {

        Block block = world.getBlockState(pos).getBlock();
        if (block instanceof TNTAttribute && !world.isClient) {

            Entity tntEntity = Blocks.INSTANCE.getTNTEntityByType(block, world, pos, igniter);
            world.spawnEntity(tntEntity);
            world.playSound(null, tntEntity.getX(), tntEntity.getY(), tntEntity.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
            ci.cancel();
        }
    }

    //
    @Inject(method = "onDestroyedByExplosion", at = @At("HEAD"), cancellable = true)
    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion, CallbackInfo ci) {
        if (world.isClient) {
            return;
        }
        TntEntity tntEntity = Blocks.INSTANCE.getTNTEntityByType(this, world, pos, null);
        tntEntity.setFuse((short) (world.random.nextInt(tntEntity.getFuse() / 4) + tntEntity.getFuse() / 8));
        world.spawnEntity(tntEntity);
        ci.cancel();
    }

    @Inject(method = "appendProperties", at = @At("HEAD"))
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {
        builder.add(FORTUNE_PROPERTY);
    }

}
