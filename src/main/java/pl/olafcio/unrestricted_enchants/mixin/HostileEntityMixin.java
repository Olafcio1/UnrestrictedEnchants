package pl.olafcio.unrestricted_enchants.mixin;

import net.minecraft.entity.mob.HostileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import pl.olafcio.unrestricted_enchants.Feature;

@Mixin(HostileEntity.class)
public class HostileEntityMixin {
    @ModifyConstant(constant = {
            @Constant(expandZeroConditions = {
                    Constant.Condition.GREATER_THAN_ZERO
            }, ordinal = 0)
    }, method = "tryAttack")
    @Feature("Allow negative knockback")
    public int tryAttack(int constant) {
        return Integer.MIN_VALUE;
    }
}
