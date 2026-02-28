package pl.olafcio.unrestricted_enchants.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pl.olafcio.unrestricted_enchants.Feature;

@Mixin(EnchantmentHelper.class)
public class EnchantHelperMixin {
    @ModifyConstant(constant = {
            @Constant(intValue = 0, ordinal = 1)
    }, method = "getEquipmentLevel")
    @Feature("Negative equipment enchantments")
    private static int getEquipmentLevel__defaultValue(int constant) {
        return Integer.MIN_VALUE;
    }

    @Inject(at = @At("RETURN"), method = "getEquipmentLevel", cancellable = true)
    @Feature("Negative equipment enchantments")
    private static void getEquipmentLevel__return(Enchantment enchantment, LivingEntity entity, CallbackInfoReturnable<Integer> cir) {
        if (cir.getReturnValueI() == Integer.MIN_VALUE)
            cir.setReturnValue(0);
    }
}
