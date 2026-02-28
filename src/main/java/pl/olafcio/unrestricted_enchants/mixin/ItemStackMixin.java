package pl.olafcio.unrestricted_enchants.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pl.olafcio.unrestricted_enchants.Feature;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Shadow
    private NbtCompound nbt;

    @Inject(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/nbt/NbtCompound;getList(Ljava/lang/String;I)Lnet/minecraft/nbt/NbtList;"
            ),
            method = "addEnchantment",
            cancellable = true
    )
    @Feature("32k enchantments")
    public void putShort(Enchantment enchantment, int level, CallbackInfo ci) {
        NbtList nbtList = this.nbt.getList("ench", 10);
        NbtCompound nbtCompound = new NbtCompound();

        nbtCompound.putShort("id", (short)Enchantment.getId(enchantment));
        nbtCompound.putShort("lvl", (short)level);

        nbtList.add(nbtCompound);
        ci.cancel();
    }
}
