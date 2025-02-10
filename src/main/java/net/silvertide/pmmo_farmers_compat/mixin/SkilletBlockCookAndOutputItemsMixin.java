package net.silvertide.pmmo_farmers_compat.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.silvertide.pmmo_farmers_compat.util.EventUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import vectorwing.farmersdelight.common.block.entity.SkilletBlockEntity;

@Mixin(SkilletBlockEntity.class)
public abstract class SkilletBlockCookAndOutputItemsMixin {
    @ModifyArg(method = "cookAndOutputItems(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;)V",
            at = @At(value = "INVOKE", target = "vectorwing/farmersdelight/common/utility/ItemUtils.spawnItemEntity (Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;DDDDDD)V"),
            remap = false)
    public ItemStack modifySpawnItemEntityArg(ItemStack resultStack) {
        SkilletBlockEntity blockEntity = ((SkilletBlockEntity)(Object)this);
        if(blockEntity != null) {
            Level level = blockEntity.getLevel();
            BlockPos pos = blockEntity.getBlockPos();
            EventUtil.postFurnaceBurnEvent(level, pos, resultStack);
        }
        return resultStack;
    }
}
