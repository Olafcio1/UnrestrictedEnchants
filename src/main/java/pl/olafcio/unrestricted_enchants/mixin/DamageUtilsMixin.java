package pl.olafcio.unrestricted_enchants.mixin;

import net.minecraft.util.DamageUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import pl.olafcio.unrestricted_enchants.Feature;

@Mixin(DamageUtils.class)
public class DamageUtilsMixin {
    @Redirect(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/math/MathHelper;clamp(FFF)F"
            ),
            method = "method_12937"
    )
    @Feature("Allow negative protection")
    private static float getDamage__clamp(float value, float min, float max) {
        return value;
    }
}
