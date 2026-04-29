package net.silvertide.pmmo_farmers_compat.mixin;

import net.chaolux.vanilladelight.common.block.entity.CommonStoveBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.silvertide.pmmo_farmers_compat.util.EventUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(CommonStoveBlockEntity.class)
public abstract class VanillaDelightStoveCookAndOutputItemsMixin {
    @ModifyArg(
            method = "cookAndOutputItems()V",
            at = @At(value = "INVOKE",
                    target = "vectorwing/farmersdelight/common/utility/ItemUtils.spawnItemEntity (Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;DDDDDD)V"
            ), remap = false)
    public ItemStack modifySpawnItemEntityArg(ItemStack resultStack) {
        CommonStoveBlockEntity stoveBlockEntity = ((CommonStoveBlockEntity)(Object)this);
        Level level = stoveBlockEntity.getLevel();
        if (level == null || level.isClientSide) return resultStack;
        BlockPos pos = stoveBlockEntity.getBlockPos();
        EventUtil.postFurnaceBurnEvent(level, pos, resultStack);
        return resultStack;
    }
}
