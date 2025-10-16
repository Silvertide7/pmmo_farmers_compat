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
     * @param output the item being smelted.  NOT the output item
     * @param level the world/level the smelting is occurring in
     * @param pos the position of the block smelting the item
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
