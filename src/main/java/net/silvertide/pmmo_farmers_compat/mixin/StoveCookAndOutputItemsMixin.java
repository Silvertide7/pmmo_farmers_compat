package net.silvertide.pmmo_farmers_compat.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.silvertide.pmmo_farmers_compat.util.EventUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import vectorwing.farmersdelight.common.block.entity.StoveBlockEntity;

@Mixin(StoveBlockEntity.class)
public abstract class StoveCookAndOutputItemsMixin {
    @ModifyArg(method = "cookAndOutputItems()V", at = @At(value = "INVOKE", target = "vectorwing/farmersdelight/common/utility/ItemUtils.spawnItemEntity (Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;DDDDDD)V"), index = 1, remap = false)
    public ItemStack modifyResultStackArg(Level level, ItemStack resultStack, double x, double y, double z, double xMotion, double yMotion, double zMotion) {
        int xPos = (int) (x - 0.5);
        int yPos = (int) (y - 1.0);
        int zPos = (int) (z - 0.5);
        EventUtil.postFurnaceBurnEvent(level, new BlockPos(xPos, yPos, zPos), resultStack);
        return resultStack;
    }
}
