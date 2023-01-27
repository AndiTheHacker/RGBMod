package com.andi.rgb

import io.gitlab.mguimard.openrgb.client.OpenRGBClient
import io.gitlab.mguimard.openrgb.entity.OpenRGBColor
import io.gitlab.mguimard.openrgb.entity.OpenRGBDevice
import net.fabricmc.api.ModInitializer
import net.minecraft.client.MinecraftClient
import net.minecraft.server.MinecraftServer

@Suppress("UNUSED")
object RGBMod: ModInitializer {
    private const val MOD_ID = "rgb"
    private val client: OpenRGBClient = OpenRGBClient("localhost", 6742, "minecraft")
    private var cIndex: Int = 0
    private var controller: OpenRGBDevice = OpenRGBDevice()

    override fun onInitialize() {
        println("RGBMod has been initialized.")

        try {
            client.connect()
            cIndex =  client.controllerCount - 2
            controller = client.getDeviceController(cIndex)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }

        client.updateMode(cIndex, 17, controller.modes[17])
    }
    fun damage() {
        Thread {
            client.updateMode(cIndex, 1, controller.modes[1])
            client.updateLeds(cIndex, Array(controller.colors.size) { OpenRGBColor.fromHSB(0f, 255f, 255f) })
            Thread.sleep(300)

            client.updateMode(
                cIndex,
                17,
                controller.modes[17]
            )
        }.start()
    }
}
