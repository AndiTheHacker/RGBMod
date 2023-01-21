package com.andi.rgb;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public interface PlayerCallback {

    Event<PlayerCallback> DAMAGE = EventFactory.createArrayBacked(PlayerCallback.class,
            (listeners) -> (player) -> {
                for (PlayerCallback listener : listeners) {
                    ActionResult result = listener.damage(player);

                    if(result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            });

    ActionResult damage(PlayerEntity player);
}