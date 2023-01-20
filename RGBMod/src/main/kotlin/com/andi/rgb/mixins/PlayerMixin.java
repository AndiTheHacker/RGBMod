package com.andi.rgb.mixins;
import com.andi.rgb.PlayerCallback;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;setHealth(F)V"), method = "applyDamage", cancellable = true)
    private void onDamage(DamageSource source, float amount, CallbackInfo ci) {
        ActionResult result = PlayerCallback.DAMAGE.invoker().damage( (PlayerEntity) (Object) this);

        if(result == ActionResult.FAIL) {
            ci.cancel();
        }
    }
}
