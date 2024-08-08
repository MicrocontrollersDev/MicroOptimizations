package dev.microcontrollers.microoptimizations.mixin.block;

import dev.microcontrollers.microoptimizations.helpers.ICachedHashcode;
import net.minecraft.block.properties.PropertyHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PropertyHelper.class)
public class MixinPropertyHelper_CacheHashcode implements ICachedHashcode {
    @Unique
    private int microoptimizations$cachedHashcode;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void microoptimizations$cacheHashcode(String p_i45652_1_, Class<?> p_i45652_2_, CallbackInfo ci) {
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

    @Override
    public int microoptimizations$getCachedHashcode() {
        return this.microoptimizations$cachedHashcode;
    }
}

