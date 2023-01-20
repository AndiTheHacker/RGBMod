package com.andi.rgb

import io.gitlab.mguimard.openrgb.client.OpenRGBClient
import io.gitlab.mguimard.openrgb.entity.OpenRGBColor
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents
import net.fabricmc.fabric.api.event.Event
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
import net.minecraft.util.ActionResult

@Suppress("UNUSED")
object RGBMod: ModInitializer {
    private const val MOD_ID = "rgb"
    override fun onInitialize() {
        println("RGBMod has been initialized.")

        val client = OpenRGBClient("localhost", 6742, "minecraft")
        try {
            client.connect()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }

        val cIndex = client.controllerCount - 1
        val controller = client.getDeviceController(cIndex)

        client.updateMode(cIndex, 17, controller.modes[17])

        PlayerBlockBreakEvents.AFTER.register(PlayerBlockBreakEvents.After { world, player, pos, state, blockEntity ->

            client.updateMode(cIndex, 1, controller.modes[1])
            client.updateLeds(cIndex, Array(controller.colors.size) {OpenRGBColor.fromHexaString("#00FFFF")})
            Thread.sleep(300)
            client.updateMode(client.controllerCount-1, 17, client.getDeviceController(client.controllerCount-1).modes[17])
        })
        PlayerCallback.DAMAGE.register(PlayerCallback { player ->
            client.updateMode(cIndex, 1, controller.modes[1])
            client.updateLeds(cIndex, Array(controller.colors.size) {OpenRGBColor.fromHexaString("#00FFFF")})
            Thread.sleep(300)
            client.updateMode(client.controllerCount-1, 17, client.getDeviceController(client.controllerCount-1).modes[17])
            return@PlayerCallback ActionResult.SUCCESS
        })
    }
}
