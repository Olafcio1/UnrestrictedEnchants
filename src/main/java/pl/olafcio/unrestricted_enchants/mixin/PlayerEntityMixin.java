package pl.olafcio.unrestricted_enchants.mixin;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import pl.olafcio.unrestricted_enchants.Feature;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @ModifyConstant(constant = {
            @Constant(floatValue = 0.0F, ordinal = 0),
            @Constant(floatValue = 0.0F, ordinal = 1)
    }, method = "attack")
    @Feature("Allow negative damage")
    public float attack(float constant) {
        return Integer.MIN_VALUE;
    }
}
