package pl.olafcio.unrestricted_enchants.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "pl.olafcio.renewed.commands.EnchantCommand")
public class EnchantCommandMixin {
    @Redirect(
            at = @At(
                    value = "INVOKE",
                    target = "Lpl/olafcio/renewed/commands/EnchantCommand;parseUnsignedInt(Ljava/lang/String;)I"
            ),
            method = "execute"
    )
    public int parseLevel(@Coerce Object instance, String string) {
        return Integer.parseInt(string);
    }
}
