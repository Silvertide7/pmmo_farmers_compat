package net.silvertide.pmmo_farmers_compat.util;

import harmonised.pmmo.api.events.FurnaceBurnEvent;
import harmonised.pmmo.storage.ChunkDataHandler;
import harmonised.pmmo.storage.ChunkDataProvider;
import harmonised.pmmo.storage.IChunkData;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.util.UUID;

public class EventUtil {

    public static void postPlayerCraftEvent(Level level, BlockPos pos, ItemStack stack) {
        if(level.isClientSide) return;
        IChunkData cap = level.getChunkAt(pos).getCapability(ChunkDataProvider.CHUNK_CAP).orElseGet(ChunkDataHandler::new);
        UUID pid = cap.checkPos(pos);
        if (pid == null) return;

        ServerPlayer player = level.getServer().getPlayerList().getPlayer(pid);
        if (player != null)
            MinecraftForge.EVENT_BUS.post(new PlayerEvent.ItemCraftedEvent(player, stack, getEmptyContainer()));
    }

    public static void postFurnaceBurnEvent(Level level, BlockPos pos, ItemStack stack) {
        MinecraftForge.EVENT_BUS.post(new FurnaceBurnEvent(stack, level, pos));
    }

    private static Container getEmptyContainer(){
        return new Container() {
            @Override
            public int getContainerSize() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return true;
            }

            @Override
            public ItemStack getItem(int pSlot) {
                return null;
            }

            @Override
            public ItemStack removeItem(int pSlot, int pAmount) {
                return null;
            }

            @Override
            public ItemStack removeItemNoUpdate(int pSlot) {
                return null;
            }

            @Override
            public void setItem(int pSlot, ItemStack pStack) {

            }

            @Override
            public void setChanged() {

            }

            @Override
            public boolean stillValid(Player pPlayer) {
                return false;
            }

            @Override
            public void clearContent() {

            }
        };
    }
}
