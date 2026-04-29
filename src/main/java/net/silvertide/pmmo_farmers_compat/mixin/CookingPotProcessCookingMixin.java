package net.silvertide.pmmo_farmers_compat.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.silvertide.pmmo_farmers_compat.util.EventUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;

@Mixin(CookingPotBlockEntity.class)
public abstract class CookingPotProcessCookingMixin {
    @ModifyArg(method = "processCooking(Lvectorwing/farmersdelight/common/crafting/CookingPotRecipe;Lvectorwing/farmersdelight/common/block/entity/CookingPotBlockEntity;)Z",
            at = @At(value = "INVOKE",
                    target = "net/minecraftforge/items/ItemStackHandler.setStackInSlot (ILnet/minecraft/world/item/ItemStack;)V"),
            remap = false)
    public ItemStack modifySetStackInSlotArg(ItemStack resultStack) {
        postEvent(resultStack);
        return resultStack;
    }

    @Inject(method = "processCooking(Lvectorwing/farmersdelight/common/crafting/CookingPotRecipe;Lvectorwing/farmersdelight/common/block/entity/CookingPotBlockEntity;)Z",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;grow(I)V"))
    public void onMealGrow(CallbackInfoReturnable<Boolean> cir, @Local(ordinal = 0) ItemStack resultStack) {
        postEvent(resultStack);
    }

    private void postEvent(ItemStack stack) {
        Level level = ((CookingPotBlockEntity)(Object)this).getLevel();
        if (level == null || level.isClientSide) return;
        BlockPos pos = ((CookingPotBlockEntity)(Object)this).getBlockPos();
        for(int i = 0; i < stack.getCount(); i++) {
            EventUtil.postFurnaceBurnEvent(level, pos, stack);
        }
    }
}
