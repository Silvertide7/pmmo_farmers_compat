package net.silvertide.pmmo_farmers_compat.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForge;
import net.silvertide.pmmo_farmers_compat.events.FarmersCookEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import vectorwing.farmersdelight.common.block.entity.AbstractStoveBlockEntity;

@Mixin(AbstractStoveBlockEntity.class)
public abstract class StoveCookAndOutputItemsMixin {
    @ModifyArg(
            method = "cookAndOutputItems()V",
            at = @At(value = "INVOKE",
                    target = "vectorwing/farmersdelight/common/utility/ItemUtils.spawnItemEntity (Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;DDDDDD)V"
            ), remap = false)
    public ItemStack modifySpawnItemEntityArg(ItemStack resultStack) {
        AbstractStoveBlockEntity stoveBlockEntity = ((AbstractStoveBlockEntity)(Object)this);
        Level level = stoveBlockEntity.getLevel();
        if (level == null || level.isClientSide) return resultStack;
        BlockPos pos = stoveBlockEntity.getBlockPos();
        NeoForge.EVENT_BUS.post(new FarmersCookEvent(resultStack, level, pos));
        return resultStack;
    }
}
