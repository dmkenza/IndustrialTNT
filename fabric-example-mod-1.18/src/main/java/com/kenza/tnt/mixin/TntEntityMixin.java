package com.kenza.tnt.mixin;

import com.kenza.tnt.domain.TNTAttribute;
import com.kenza.tnt.domain.TntType;
import net.minecraft.entity.TntEntity;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Allow TNT entities to be dirty */
@Mixin(TntEntity.class)
public abstract class TntEntityMixin implements TNTAttribute {

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

	@Inject(method = "explode", at = @At("HEAD"), cancellable = true)
	private void onExplode(CallbackInfo ci) {
		TntEntity self = (TntEntity) (Object) this;

//		if (isDirty) {
//			DirtTntEntity.createDirtExplosion(self, self.world);
//			ci.cancel();
//		}
	}

}
