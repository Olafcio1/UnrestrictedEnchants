package pl.olafcio.unrestricted_enchants.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.EnchantCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import pl.olafcio.unrestricted_enchants.Feature;

@Mixin(EnchantCommand.class)
public class EnchantCommandMixin {
    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;hasNbt()Z"), method = "method_3279")
    @Feature("Unrestricted /enchant")
    public boolean mayContainIncompatibleEnchants(ItemStack instance) {
        return false;
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;isAcceptableItem(Lnet/minecraft/item/ItemStack;)Z"), method = "method_3279")
    @Feature("Unrestricted /enchant")
    public boolean isAcceptableItem(Enchantment instance, ItemStack stack) {
        return true;
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;getMinimumLevel()I"), method = "method_3279")
    @Feature("Unrestricted /enchant")
    public int getMinimumLevel(Enchantment instance) {
        return Integer.MIN_VALUE;
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;getMaximumLevel()I"), method = "method_3279")
    @Feature("Unrestricted /enchant")
    public int getMaximumLevel(Enchantment instance) {
        return Integer.MAX_VALUE;
    }
}
