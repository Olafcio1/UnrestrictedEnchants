package pl.olafcio.unrestricted_enchants.mixin;

import net.minecraft.enchantment.DamageEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import pl.olafcio.unrestricted_enchants.Unlimiter;

@Mixin(DamageEnchantment.class)
public class DamageEnchantmentMixin {
    @Redirect(at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(II)I"), method = "getDamageModifier")
    @Unlimiter("SHARPNESS")
    public int getDamageNotifier__max(int zero, int level) {
        return level;
    }
}
