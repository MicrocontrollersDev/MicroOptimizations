package dev.microcontrollers.microoptimizations.mixin.entity;

import net.minecraft.entity.ai.attributes.BaseAttribute;
import net.minecraft.entity.ai.attributes.IAttribute;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BaseAttribute.class)
public class MixinBaseAttribute_CacheHashcode {

    @Unique
    private int microoptimizations$cachedHashcode;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void microoptimizations$cacheHashcode(IAttribute p_i45892_1_, String p_i45892_2_, double p_i45892_3_, CallbackInfo ci) {
        this.microoptimizations$cachedHashcode = this.hashCode();
    }

    /**
     * @author FeatherOpt
     * @reason Cache hashcode
     */
    @Overwrite(remap = false)
    public int hashCode() {
        return this.microoptimizations$cachedHashcode;
    }

}
