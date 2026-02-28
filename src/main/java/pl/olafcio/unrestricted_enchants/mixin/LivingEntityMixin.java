package pl.olafcio.unrestricted_enchants.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pl.olafcio.unrestricted_enchants.Feature;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow
    public abstract Iterable<ItemStack> getArmorItems();

    @Redirect(
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/Math;max(FF)F"
            ),
            method = "applyDamage"
    )
    @Feature("Allow negative damage")
    public float max(float value, float zero) {
        return value;
    }

    @Inject(
            at = @At(
                    value = "CONSTANT",
                    args = "floatValue=0.0"
            ),
            method = "applyEnchantmentsToDamage",
            cancellable = true
    )
    @Feature("Allow negative damage")
    @Feature("Allow negative protection")
    protected void applyEnchantmentsToDamage(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
        if (amount == 0.0F) {
            cir.setReturnValue(0.0F);
        } else {
            int i = EnchantmentHelper.getProtectionAmount(this.getArmorItems(), source);
            if (i != 0) {
                amount = DamageUtils.method_12937(amount, i);
            }

            cir.setReturnValue(amount);
        }
    }
}
