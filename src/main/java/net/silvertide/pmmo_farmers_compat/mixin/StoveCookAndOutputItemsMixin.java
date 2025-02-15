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
    @ModifyArg(
            method = "cookAndOutputItems()V",
            at = @At(value = "INVOKE",
                    target = "vectorwing/farmersdelight/common/utility/ItemUtils.spawnItemEntity (Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;DDDDDD)V"
            ), remap = false)
    public ItemStack modifySpawnItemEntityArg(ItemStack resultStack) {
        StoveBlockEntity stoveBlockEntity = ((StoveBlockEntity)(Object)this);
        if(stoveBlockEntity != null) {
            Level level = stoveBlockEntity.getLevel();
            BlockPos pos = stoveBlockEntity.getBlockPos();
            EventUtil.postFurnaceBurnEvent(level, pos, resultStack);
        }
        return resultStack;
    }
}
