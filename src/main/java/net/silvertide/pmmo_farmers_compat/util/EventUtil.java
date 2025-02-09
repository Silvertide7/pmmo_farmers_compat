package net.silvertide.pmmo_farmers_compat.util;

import com.mojang.authlib.GameProfile;
import harmonised.pmmo.api.events.FurnaceBurnEvent;
import harmonised.pmmo.storage.DataAttachmentTypes;
import harmonised.pmmo.util.Reference;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ClientInformation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.Optional;
import java.util.UUID;

public class EventUtil {

    public static void postPlayerCraftEvent(Level level, BlockPos pos, ItemStack stack) {
        //Checkers to exit early for non-applicable conditions
        if (level.isClientSide) return;

        UUID pid = level.getChunkAt(pos)
                .getData(DataAttachmentTypes.PLACED_MAP.get())
                .getOrDefault(pos, Reference.NIL);
        if (pid == null) return;
        ServerPlayer player = level.getServer().getPlayerList().getPlayer(pid);
        if (player == null) {
            Optional<GameProfile> playerProfile = level.getServer().getProfileCache().get(pid);
            if (playerProfile.isEmpty())
                return;
            player = new ServerPlayer(level.getServer(), (ServerLevel) level, playerProfile.get(), ClientInformation.createDefault());
        }
        if (player != null)
            NeoForge.EVENT_BUS.post(new PlayerEvent.ItemCraftedEvent(player, stack, new SimpleContainer(0)));
    }

    public static void postFurnaceBurnEvent(Level level, BlockPos pos, ItemStack stack) {
        NeoForge.EVENT_BUS.post(new FurnaceBurnEvent(stack, level, pos));
    }
}
