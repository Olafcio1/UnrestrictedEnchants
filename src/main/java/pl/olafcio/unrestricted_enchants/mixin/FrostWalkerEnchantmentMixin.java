package pl.olafcio.unrestricted_enchants.mixin;

import net.minecraft.enchantment.FrostWalkerEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import pl.olafcio.unrestricted_enchants.Unlimiter;

@Mixin(FrostWalkerEnchantment.class)
public class FrostWalkerEnchantmentMixin {
    @Redirect(at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(II)I"), method = "method_11464")
    @Unlimiter("FROST_WALKER")
    private static int getSpread__min(int min, int spread) {
        return spread;
    }
}
