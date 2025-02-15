package net.silvertide.pmmo_farmers_compat.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.silvertide.pmmo_farmers_compat.util.EventUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;

@Mixin(CookingPotBlockEntity.class)
public abstract class CookingPotProcessCookingMixin {
    @ModifyArg(method = "processCooking",
            at = @At(value = "INVOKE",
                    target = "net/neoforged/neoforge/items/ItemStackHandler.setStackInSlot (ILnet/minecraft/world/item/ItemStack;)V"),
            remap = false)
    public ItemStack modifySetStackInSlotArg(ItemStack resultStack) {
        postEvent(resultStack);
        return resultStack;
    }

    @ModifyArg(method = "processCooking",
            at = @At(value = "INVOKE",
                    target = "net/minecraft/world/item/ItemStack.isSameItem (Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)Z"),
            index = 1)
    public ItemStack modifyIsSameItemArg(ItemStack resultStack) {
        postEvent(resultStack);
        return resultStack;
    }

    private void postEvent(ItemStack stack) {
        CookingPotBlockEntity cookingPotBlockEntity = ((CookingPotBlockEntity)(Object)this);
        if(cookingPotBlockEntity != null) {
            Level level = cookingPotBlockEntity.getLevel();
            BlockPos pos = cookingPotBlockEntity.getBlockPos();
            for(int i = 0; i < stack.getCount(); i++) {
                EventUtil.postFurnaceBurnEvent(level, pos, stack);
            }
        }

    }
}
