package dev.microcontrollers.microoptimizations.mixin.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * Idea backport from Forge 1.12.2:
 * <a href="https://github.com/MinecraftForge/MinecraftForge/commit/50bf03b82b72866bd54e796a37707d45bcbf3d80">github</a>
 */
@Mixin(EntityList.class)
public class MixinEntityList_CacheConstructors {
    @Unique
    private static final Map<Class<? extends Entity>, Constructor<?>> MICROOPTIMIZATIONS$CLASS_TO_CONSTRUCTOR = new HashMap<>();

    @Redirect(method = "createEntityByName", at = @At(value = "INVOKE", target = "Ljava/lang/Class;getConstructor([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;", remap = false))
    private static Constructor<?> microoptimizations$createEntityByName$cacheConstructor(Class<? extends Entity> instance, Class<?>[] parameterTypes) {
        return microoptimizations$getConstructor(instance);
    }

    @Redirect(method = "createEntityFromNBT", at = @At(value = "INVOKE", target = "Ljava/lang/Class;getConstructor([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;", remap = false))
    private static Constructor<?> microoptimizations$createEntityFromNBT$cacheConstructor(Class<? extends Entity> instance, Class<?>[] parameterTypes) {
        return microoptimizations$getConstructor(instance);
    }

    @Redirect(method = "createEntityByID", at = @At(value = "INVOKE", target = "Ljava/lang/Class;getConstructor([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;", remap = false))
    private static Constructor<?> microoptimizations$createEntityByID$cacheConstructor(Class<? extends Entity> instance, Class<?>[] parameterTypes) {
        return microoptimizations$getConstructor(instance);
    }

    @Unique
    private static Constructor<?> microoptimizations$getConstructor(Class<? extends Entity> entityClass) {
        Constructor<?> constructor = MICROOPTIMIZATIONS$CLASS_TO_CONSTRUCTOR.get(entityClass);
        if (constructor == null) {
            try {
                constructor = entityClass.getConstructor(World.class);
                MICROOPTIMIZATIONS$CLASS_TO_CONSTRUCTOR.put(entityClass, constructor);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }

        return constructor;
    }
}
