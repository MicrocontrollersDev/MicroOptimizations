package dev.microcontrollers.microoptimizations.mixin.renderer;

import dev.microcontrollers.microoptimizations.helpers.PooledMutableBlockPos;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderGlobal.class)
public class MixinRenderGlobal_BlockPosPool {
    @Shadow
    private int renderEntitiesStartupCounter;
    @Unique
    private PooledMutableBlockPos microoptimizations$blockPos;
    @Unique
    private int microoptimizations$originalRenderEntitiesStartupCounter;

    @Inject(method = "renderEntities", at = @At("HEAD"))
    public void microoptimizations$catchRenderEntitiesStartupCounter(Entity p_renderEntities_1_, ICamera p_renderEntities_2_, float p_renderEntities_3_, CallbackInfo ci) {
        this.microoptimizations$originalRenderEntitiesStartupCounter = this.renderEntitiesStartupCounter;
    }

    @Inject(method = "renderEntities", at = @At(value = "CONSTANT", args = "stringValue=entities", ordinal = 0))
    public void microoptimizations$getFromPool(Entity p_renderEntities_1_, ICamera p_renderEntities_2_, float p_renderEntities_3_, CallbackInfo ci) {
        this.microoptimizations$blockPos = PooledMutableBlockPos.get();
    }

    @Redirect(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/BlockPos;offset(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/BlockPos;"),
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=entities", ordinal = 0))
    )
    public BlockPos microoptimizations$usedPooledBlockPos(BlockPos instance, EnumFacing direction) {
        return microoptimizations$blockPos.move(direction);
    }

    @Inject(method = "renderEntities", at = @At("RETURN"), slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=entities", ordinal = 0)))
    public void microoptimizations$release(Entity p_renderEntities_1_, ICamera p_renderEntities_2_, float p_renderEntities_3_, CallbackInfo ci) {
        if (microoptimizations$originalRenderEntitiesStartupCounter <= 0) {
            this.microoptimizations$blockPos.release();
        }
    }
}
