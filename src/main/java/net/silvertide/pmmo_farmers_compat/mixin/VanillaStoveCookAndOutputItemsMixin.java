package net.silvertide.pmmo_farmers_compat.mixin;

import net.chaolux.vanilladelight.common.block.entity.CommonStoveBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForge;
import net.silvertide.pmmo_farmers_compat.events.FarmersCookEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(CommonStoveBlockEntity.class)
public abstract class VanillaStoveCookAndOutputItemsMixin {
    @ModifyArg(
            method = "cookAndOutputItems()V",
            at = @At(value = "INVOKE",
                    target = "vectorwing/farmersdelight/common/utility/ItemUtils.spawnItemEntity (Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;DDDDDD)V"
            ), remap = false)
    public ItemStack modifySpawnItemEntityArg(ItemStack resultStack) {
        CommonStoveBlockEntity stoveBlockEntity = ((CommonStoveBlockEntity)(Object)this);
        if(stoveBlockEntity != null) {
            Level level = stoveBlockEntity.getLevel();
            BlockPos pos = stoveBlockEntity.getBlockPos();
            NeoForge.EVENT_BUS.post(new FarmersCookEvent(resultStack, level, pos));
        }
        return resultStack;
    }
}
