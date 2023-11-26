package net.silvertide.pmmo_farmers_compat.mixin;

import net.minecraft.world.item.ItemStack;
import net.silvertide.pmmo_farmers_compat.PMMOFarmersCompat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import vectorwing.farmersdelight.common.block.entity.StoveBlockEntity;

@Mixin(StoveBlockEntity.class)
public abstract class StoveBlockEntityMixin {

    @Inject(method = "cookAndOutputItems", at = @At(value = "INVOKE", target = "vectorwing/farmersdelight/common/utility/ItemUtils.spawnItemEntity (Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;DDDDDD)V", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILSOFT, remap = false)
    public void grantXPForStove(CallbackInfo ci, boolean didInventoryChange, int i, ItemStack stoveStack) {
        PMMOFarmersCompat.LOGGER.info(stoveStack.toString());
    }
}
