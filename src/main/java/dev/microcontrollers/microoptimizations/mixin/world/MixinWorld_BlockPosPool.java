package dev.microcontrollers.microoptimizations.mixin.world;

import dev.microcontrollers.microoptimizations.helpers.PooledMutableBlockPos;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(World.class)
public abstract class MixinWorld_BlockPosPool {
    @Unique
    private PooledMutableBlockPos microoptimizations$checkLightFor$blockPos;
    @Unique
    private int microoptimizations$checkLightFor$x;
    @Unique
    private int microoptimizations$checkLightFor$y;
    @Unique
    private int microoptimizations$checkLightFor$z;
    @Unique
    private BlockPos microoptimizations$rawLight$originalBlockPos;
    @Unique
    private PooledMutableBlockPos microoptimizations$rawLight$blockPos;

    @Redirect(method = "getCollisionBoxes", at = @At(value = "NEW", target = "net/minecraft/util/BlockPos$MutableBlockPos"))
    private BlockPos.MutableBlockPos microoptimizations$getCollisionBoxes$usePooled() {
        return PooledMutableBlockPos.get();
    }

    @Inject(method = "getCollisionBoxes", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void microoptimizations$getCollisionBoxes$returnToPool(AxisAlignedBB p_getCollisionBoxes_1_, CallbackInfoReturnable<List<AxisAlignedBB>> cir, List list, int i, int j, int k, int l, int i1, int j1, BlockPos.MutableBlockPos blockPos) {
        ((PooledMutableBlockPos) blockPos).release();
    }

    @Redirect(method = "isAnyLiquid", at = @At(value = "NEW", target = "net/minecraft/util/BlockPos$MutableBlockPos"))
    private BlockPos.MutableBlockPos microoptimizations$isAnyLiquid$usePooled() {
        return PooledMutableBlockPos.get();
    }

    @Inject(method = "isAnyLiquid", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void microoptimizations$isAnyLiquid$returnToPool(AxisAlignedBB p_isAnyLiquid_1_, CallbackInfoReturnable<Boolean> cir, int i, int j, int k, int l, int i1, int j1, BlockPos.MutableBlockPos blockPos) {
        ((PooledMutableBlockPos) blockPos).release();
    }

    @Redirect(method = "isFlammableWithin", at = @At(value = "NEW", target = "net/minecraft/util/BlockPos$MutableBlockPos"))
    private BlockPos.MutableBlockPos microoptimizations$isFlammableWithin$usePooled() {
        return PooledMutableBlockPos.get();
    }

    @Inject(
            method = "isFlammableWithin", at = @At(value = "RETURN", ordinal = 0),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private void microoptimizations$isFlammableWithin$returnToPool$first(AxisAlignedBB p_isFlammableWithin_1_, CallbackInfoReturnable<Boolean> cir, int i, int j, int k, int l, int i1, int j1, BlockPos.MutableBlockPos blockPos) {
        ((PooledMutableBlockPos) blockPos).release();
    }

    @Inject(
            method = "isFlammableWithin", at = @At(value = "RETURN", ordinal = 1),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private void microoptimizations$isFlammableWithin$returnToPool$second(AxisAlignedBB p_isFlammableWithin_1_, CallbackInfoReturnable<Boolean> cir, int i, int j, int k, int l, int i1, int j1, BlockPos.MutableBlockPos blockPos) {
        ((PooledMutableBlockPos) blockPos).release();
    }

    @Redirect(method = "handleMaterialAcceleration", at = @At(value = "NEW", target = "net/minecraft/util/BlockPos$MutableBlockPos"))
    private BlockPos.MutableBlockPos microoptimizations$handleMaterialAcceleration$usePooled() {
        return PooledMutableBlockPos.get();
    }

    @Inject(method = "handleMaterialAcceleration", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Vec3;lengthVector()D"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void microoptimizations$handleMaterialAcceleration$returnToPool(AxisAlignedBB p_handleMaterialAcceleration_1_, Material p_handleMaterialAcceleration_2_, Entity p_handleMaterialAcceleration_3_, CallbackInfoReturnable<Boolean> cir, int i, int j, int k, int l, int m, int n, boolean x, Vec3 vec3, BlockPos.MutableBlockPos blockPos) {
        ((PooledMutableBlockPos) blockPos).release();
    }

    @Redirect(method = "isMaterialInBB", at = @At(value = "NEW", target = "net/minecraft/util/BlockPos$MutableBlockPos"))
    private BlockPos.MutableBlockPos microoptimizations$isMaterialInBB$usePooled() {
        return PooledMutableBlockPos.get();
    }

    @Inject(method = "isMaterialInBB", at = @At(value = "RETURN"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void microoptimizations$isMaterialInBB$returnToPool(AxisAlignedBB p_isMaterialInBB_1_, Material p_isMaterialInBB_2_, CallbackInfoReturnable<Boolean> cir, int i, int j, int k, int l, int i1, int j1, BlockPos.MutableBlockPos blockPos) {
        ((PooledMutableBlockPos) blockPos).release();
    }

    @Redirect(method = "isAABBInMaterial", at = @At(value = "NEW", target = "net/minecraft/util/BlockPos$MutableBlockPos"))
    private BlockPos.MutableBlockPos microoptimizations$isAABBInMaterial$usePooled() {
        return PooledMutableBlockPos.get();
    }

    @Inject(method = "isAABBInMaterial", at = @At(value = "RETURN"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void microoptimizations$isAABBInMaterial$returnToPool(AxisAlignedBB p_isAABBInMaterial_1_, Material p_isAABBInMaterial_2_, CallbackInfoReturnable<Boolean> cir, int i, int j, int k, int l, int i1, int j1, BlockPos.MutableBlockPos blockPos) {
        ((PooledMutableBlockPos) blockPos).release();
    }

    @Inject(method = "isAABBInMaterial", at = @At(value = "RETURN"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void microoptimizations$getRawLight$returnToPool(AxisAlignedBB p_isAABBInMaterial_1_, Material p_isAABBInMaterial_2_, CallbackInfoReturnable<Boolean> cir, int i, int j, int k, int l, int i1, int j1, BlockPos.MutableBlockPos blockPos) {
        ((PooledMutableBlockPos) blockPos).release();
    }

    @Inject(
            method = "getRawLight",
            at = @At(value = "CONSTANT", args = "intValue=0", ordinal = 2)
    )
    private void microoptimizations$getRawLight$getFromPool(BlockPos blockPos, EnumSkyBlock enumSkyBlock, CallbackInfoReturnable<Integer> cir) {
        this.microoptimizations$rawLight$originalBlockPos = blockPos;
        this.microoptimizations$rawLight$blockPos = PooledMutableBlockPos.get();
    }

    @Redirect(
            method = "getRawLight",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/BlockPos;offset(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/BlockPos;")
    )
    private BlockPos microoptimizations$getRawLight$usePooled(BlockPos instance, EnumFacing direction) {
        return microoptimizations$rawLight$blockPos.set(microoptimizations$rawLight$originalBlockPos).move(direction);
    }

    @Inject(
            method = "getRawLight",
            at = @At("RETURN"),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getLightFor(Lnet/minecraft/world/EnumSkyBlock;Lnet/minecraft/util/BlockPos;)I")
            )
    )
    private void microoptimizations$getRawLight$returnToPool(BlockPos blockPos, EnumSkyBlock p_getRawLight_2_, CallbackInfoReturnable<Integer> cir) {
        microoptimizations$rawLight$blockPos.release();
    }

    @Inject(
            method = "checkLightFor",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/profiler/Profiler;startSection(Ljava/lang/String;)V", ordinal = 0)
    )
    private void microoptimizations$checkLightFor$usePooled$init1(EnumSkyBlock p_checkLightFor_1_, BlockPos p_checkLightFor_2_, CallbackInfoReturnable<Boolean> cir) {
        this.microoptimizations$checkLightFor$blockPos = PooledMutableBlockPos.get();
    }

    @Redirect(
            method = "checkLightFor",
            at = @At(value = "NEW", target = "net/minecraft/util/BlockPos", ordinal = 0),
            require = 1
    )
    private BlockPos microoptimizations$checkLightFor$usePooled$set(int x, int y, int z) {
        return microoptimizations$checkLightFor$blockPos.set(x, y, z);
    }

    @Redirect(
            method = "checkLightFor",
            at = @At(value = "NEW", target = "net/minecraft/util/BlockPos", ordinal = 1)
    )
    private BlockPos microoptimizations$checkLightFor$usePooled$init2(int x, int y, int z) {
        this.microoptimizations$checkLightFor$x = x;
        this.microoptimizations$checkLightFor$y = y;
        this.microoptimizations$checkLightFor$z = z;
        return microoptimizations$checkLightFor$blockPos.set(x, y, z);
    }

    @Redirect(
            method = "checkLightFor",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/BlockPos;west()Lnet/minecraft/util/BlockPos;")
    )
    private BlockPos microoptimizations$checkLightFor$usePooled$directions$west(BlockPos instance) {
        return ((PooledMutableBlockPos) instance).set(microoptimizations$checkLightFor$x, microoptimizations$checkLightFor$y, microoptimizations$checkLightFor$z).move(EnumFacing.WEST);
    }

    @Redirect(
            method = "checkLightFor",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/BlockPos;east()Lnet/minecraft/util/BlockPos;")
    )
    private BlockPos microoptimizations$checkLightFor$usePooled$directions$east(BlockPos instance) {
        return ((PooledMutableBlockPos) instance).set(microoptimizations$checkLightFor$x, microoptimizations$checkLightFor$y, microoptimizations$checkLightFor$z).move(EnumFacing.WEST);
    }

    @Redirect(
            method = "checkLightFor",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/BlockPos;down()Lnet/minecraft/util/BlockPos;")
    )
    private BlockPos microoptimizations$checkLightFor$usePooled$directions$down(BlockPos instance) {
        return ((PooledMutableBlockPos) instance).set(microoptimizations$checkLightFor$x, microoptimizations$checkLightFor$y, microoptimizations$checkLightFor$z).move(EnumFacing.WEST);
    }

    @Redirect(
            method = "checkLightFor",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/BlockPos;up()Lnet/minecraft/util/BlockPos;")
    )
    private BlockPos microoptimizations$checkLightFor$usePooled$directions$up(BlockPos instance) {
        return ((PooledMutableBlockPos) instance).set(microoptimizations$checkLightFor$x, microoptimizations$checkLightFor$y, microoptimizations$checkLightFor$z).move(EnumFacing.WEST);
    }

    @Redirect(
            method = "checkLightFor",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/BlockPos;north()Lnet/minecraft/util/BlockPos;")
    )
    private BlockPos microoptimizations$checkLightFor$usePooled$directions$north(BlockPos instance) {
        return ((PooledMutableBlockPos) instance).set(microoptimizations$checkLightFor$x, microoptimizations$checkLightFor$y, microoptimizations$checkLightFor$z).move(EnumFacing.WEST);
    }

    @Redirect(
            method = "checkLightFor",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/BlockPos;south()Lnet/minecraft/util/BlockPos;")
    )
    private BlockPos microoptimizations$checkLightFor$usePooled$directions$south(BlockPos instance) {
        return ((PooledMutableBlockPos) instance).set(microoptimizations$checkLightFor$x, microoptimizations$checkLightFor$y, microoptimizations$checkLightFor$z).move(EnumFacing.WEST);
    }

    @Inject(method = "checkLightFor", at = @At(value = "INVOKE", target = "Lnet/minecraft/profiler/Profiler;endSection()V", ordinal = 1))
    public void microoptimizations$checkLightFor$release(EnumSkyBlock p_checkLightFor_1_, BlockPos p_checkLightFor_2_, CallbackInfoReturnable<Boolean> cir) {
        microoptimizations$checkLightFor$blockPos.release();
    }
}
