package net.silvertide.pmmo_farmers_compat.events;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.Event;

public class FarmersCookEvent extends Event {
    ItemStack output;
    Level level;
    BlockPos pos;

    /**
     * @param output the cooking result (matches PMMO's SMELTED event semantics)
     * @param level the world/level the cook is occurring in
     * @param pos the position of the block doing the cooking
     */
    public FarmersCookEvent(ItemStack output, Level level, BlockPos pos) {
        this.output = output;
        this.level = level;
        this.pos = pos;
    }

    public ItemStack getOutput() {return output;}
    public Level getLevel() {return level;}
    public BlockPos getPos() {return pos;}
}
