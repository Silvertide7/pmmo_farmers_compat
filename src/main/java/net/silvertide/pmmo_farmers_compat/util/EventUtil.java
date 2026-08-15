package net.silvertide.pmmo_farmers_compat.util;

import harmonised.pmmo.api.events.FurnaceBurnEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.silvertide.pmmo_farmers_compat.config.Config;

public class EventUtil {

    public static void postPlayerCraftEvent(Player player, ItemStack stack) {
        if (!(player instanceof ServerPlayer serverPlayer)) return;
        MinecraftForge.EVENT_BUS.post(new PlayerEvent.ItemCraftedEvent(serverPlayer, stack, new SimpleContainer(0)));
    }

    public static void postFurnaceBurnEvent(Level level, BlockPos pos, ItemStack stack) {
        if (level == null || level.isClientSide) return;
        boolean asInput = Config.XP_MODE.get() == Config.XpMode.SMELT;
        ItemStack input = asInput ? stack : ItemStack.EMPTY;
        ItemStack output = asInput ? ItemStack.EMPTY : stack;
        MinecraftForge.EVENT_BUS.post(new FurnaceBurnEvent(input, output, level, pos));
    }
}
