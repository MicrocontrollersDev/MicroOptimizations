package dev.microcontrollers.microoptimizations.mixin.block;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.properties.PropertyBool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PropertyBool.class)
public class MixinPropertyBool_ConstantAllowedValues {
    @Unique
    private static final ImmutableSet<Boolean> MICROOPTIMIZATIONS$ALLOWED_VALUES = ImmutableSet.of(true, false);

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableSet;of(Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/common/collect/ImmutableSet;", remap = false))
    private ImmutableSet<Boolean> microoptimizations$useCached(Object first, Object second) {
        return MICROOPTIMIZATIONS$ALLOWED_VALUES;
    }
}
