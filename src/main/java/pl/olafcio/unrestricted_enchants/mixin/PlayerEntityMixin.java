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
    public float attack__damageGT0(float constant) {
        return Integer.MIN_VALUE;
    }

    @ModifyConstant(constant = {
            @Constant(expandZeroConditions = {
                    Constant.Condition.GREATER_THAN_ZERO
            }, ordinal = 1)
    }, method = "attack")
    @Feature("Allow negative knockback")
    public int attack__knockbackGT0(int constant) {
        return Integer.MIN_VALUE;
    }
}
