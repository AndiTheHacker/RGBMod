package com.andi.rgb.mixins

import com.andi.rgb.RGBMod
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
            value = "RETURN"
        )], method = ["applyDamage"]
    )
    private fun dmg(source: DamageSource, amount: Float, ci: CallbackInfo) {
    RGBMod.damage()
    }


}