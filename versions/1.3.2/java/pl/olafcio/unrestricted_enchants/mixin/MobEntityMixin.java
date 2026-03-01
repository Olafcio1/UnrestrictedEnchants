package pl.olafcio.unrestricted_enchants.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import org.objectweb.asm.Opcodes;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin {
    @WrapOperation(
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/entity/mob/MobEntity;field_3345:I",
                    opcode = Opcodes.GETFIELD,
                    ordinal = 0
            ),
            method = "Lnet/minecraft/entity/mob/MobEntity;damage(Lnet/minecraft/entity/damage/DamageSource;I)Z"
    )
    public int damage__minimumDamage(MobEntity instance, Operation<Integer> original) {
        int res = original.call(instance);
        if (res == 0)
            return Integer.MIN_VALUE;
        return res;
    }
}
