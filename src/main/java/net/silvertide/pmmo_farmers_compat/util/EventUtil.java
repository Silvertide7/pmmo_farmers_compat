package net.silvertide.pmmo_farmers_compat.util;

import harmonised.pmmo.api.events.FurnaceBurnEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;

public class EventUtil {

    public static void postFurnaceBurnEvent(Level level, BlockPos pos, ItemStack stack) {
        MinecraftForge.EVENT_BUS.post(new FurnaceBurnEvent(stack, level, pos));
    }
}
