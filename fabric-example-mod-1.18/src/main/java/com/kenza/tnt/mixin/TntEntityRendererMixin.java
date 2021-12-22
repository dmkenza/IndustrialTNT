package com.kenza.tnt.mixin;

import com.kenza.tnt.Blocks;
import com.kenza.tnt.domain.TNTAttribute;
import com.kenza.tnt.domain.TntType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.TntEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.TntEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(TntEntityRenderer.class)
public abstract class TntEntityRendererMixin {

	private TntType tntTypeFlag;

	@Inject(method = "render(Lnet/minecraft/entity/TntEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
			at = @At("HEAD"))
	private void onRender(TntEntity tntEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
		if(tntEntity instanceof TNTAttribute){
			tntTypeFlag = ((TNTAttribute) tntEntity).getTntType();
		}
	}

	@ModifyArg(method = "render(Lnet/minecraft/entity/TntEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/TntMinecartEntityRenderer;renderFlashingBlock(Lnet/minecraft/block/BlockState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IZ)V"),
		index = 0)
	private BlockState replaceTntTexture(BlockState blockState) {
		return Blocks.INSTANCE.getTNTBlockByType(tntTypeFlag);
	}
}
