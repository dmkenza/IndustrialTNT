package com.kenza.tnt.mixin;

import com.kenza.tnt.domain.TNTAttribute;
import com.kenza.tnt.domain.TntType;
import com.kenza.tnt.ext.TntEntityExt;
import com.kenza.tnt.ext.TntEntityExtImpl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TntEntity.class)
public abstract class TntEntityMixin extends Entity implements  TntEntityExt {



    protected TntEntityExtImpl tntEntityExt;

    public TntEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    public void test() {

    }

    @NotNull
    @Override
    public TntType getTntType() {
        return tntEntityExt.getTntType();
    }

    @Override
    public void setTntType(@NotNull TntType tntType) {
        tntEntityExt.setTntType(tntType);
    }

    @Inject(method = "explode", at = @At("HEAD"), cancellable = true)
    private void onExplode(CallbackInfo ci) {
        TntEntity self = (TntEntity) (Object) this;

//		if (isDirty) {
//			DirtTntEntity.createDirtExplosion(self, self.world);
//			ci.cancel();
//		}
    }


    @Inject(method = "initDataTracker", at = @At("TAIL"))
    protected void initDataTracker(CallbackInfo ci) {
        tntEntityExt = new TntEntityExtImpl(this);
        tntEntityExt.initDataTracker();
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        tntEntityExt.writeNbt(nbt);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        tntEntityExt.readNbt(nbt);
    }


}
