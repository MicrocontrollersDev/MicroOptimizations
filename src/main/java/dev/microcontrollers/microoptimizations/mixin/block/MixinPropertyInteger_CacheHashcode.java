package dev.microcontrollers.microoptimizations.mixin.block;

import dev.microcontrollers.microoptimizations.helpers.ICachedHashcode;
import net.minecraft.block.properties.PropertyInteger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(PropertyInteger.class)
public class MixinPropertyInteger_CacheHashcode {
    /**
     * @author FeatherOpt
     * @reason Cache hashcode
     * <p>
     * {@link PropertyInteger} is a subclass of {@link net.minecraft.block.properties.PropertyHelper}, so we don't have to cache
     * the hashcode ourselves.
     */
    @Overwrite(remap = false)
    public int hashCode() {
        return ((ICachedHashcode) this).microoptimizations$getCachedHashcode();
    }
}
