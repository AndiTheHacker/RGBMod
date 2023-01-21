package com.andi.rgb.mixins

import com.andi.rgb.PlayerCallback
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.player.PlayerEntity
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo
@Mixin(PlayerEntity::class)
abstract class PlayerMixin {
    @Inject(
        at = [At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/damage/DamageTracker;onDamage(Lnet/minecraft/entity/damage/DamageSource;FF)V",
            shift = At.Shift.AFTER
        )], method = ["applyDamage"]
    )
    private fun onDamage(source: DamageSource, amount: Float, ci: CallbackInfo) {
        val result = PlayerCallback.DAMAGE.invoker().damage(this as PlayerEntity)
    }


}