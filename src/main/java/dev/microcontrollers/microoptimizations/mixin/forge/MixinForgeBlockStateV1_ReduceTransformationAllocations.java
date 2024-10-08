package dev.microcontrollers.microoptimizations.mixin.forge;

import com.google.common.base.Optional;
import com.google.gson.JsonParseException;
import dev.microcontrollers.microoptimizations.helpers.ForgeBlockStateV1Hook;
import net.minecraftforge.client.model.ForgeBlockStateV1;
import net.minecraftforge.client.model.IModelState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

/**
 * Backported from Forge 1.12.2:
 * <a href="https://github.com/MinecraftForge/MinecraftForge/commit/0b5a6a3b031816ca46d4bc405a0062781dec914b">github</a>
 *
 * @author bs2609
 */
@Mixin(value = ForgeBlockStateV1.Variant.Deserializer.class, remap = false)
public class MixinForgeBlockStateV1_ReduceTransformationAllocations {
    @Unique
    private String microoptimizations$caughtTransform;

    /*
     * Hacky way to force all calls inside the `transform.equals("identity")` block, so we don't have to overwrite
     * the hundreds of lines of code.
     */
    @Redirect(
            method = "deserialize(Lcom/google/gson/JsonElement;Ljava/lang/reflect/Type;Lcom/google/gson/JsonDeserializationContext;)Lnet/minecraftforge/client/model/ForgeBlockStateV1$Variant;",
            at = @At(value = "INVOKE", target = "java/lang/String.equals (Ljava/lang/Object;)Z", ordinal = 0)
    )
    public boolean microoptimizations$deserialize$forwardIntoIdentityBlock(String instance, Object obj) {
        this.microoptimizations$caughtTransform = instance;
        return true;
    }

    /*
     * Continuation of our hacky approach: all transformations land here now; we can get them from our cache
     * now.
     */
    @Redirect(
            method = "deserialize(Lcom/google/gson/JsonElement;Ljava/lang/reflect/Type;Lcom/google/gson/JsonDeserializationContext;)Lnet/minecraftforge/client/model/ForgeBlockStateV1$Variant;",
            at = @At(value = "INVOKE", target = "Lcom/google/common/base/Optional;of(Ljava/lang/Object;)Lcom/google/common/base/Optional;", ordinal = 0),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "java/lang/String.equals (Ljava/lang/Object;)Z", ordinal = 0)
            )
    )
    public Optional<IModelState> microoptimizations$deserialize$cacheJsonTransformations(Object reference) {
        IModelState modelState = ForgeBlockStateV1Hook.get(this.microoptimizations$caughtTransform);
        // we still want to throw the exception in case of a bad transformation
        if (modelState == null) {
            throw new JsonParseException("transform: unknown default string: " + this.microoptimizations$caughtTransform);
        } else {
            return Optional.of(modelState);
        }
    }
}
