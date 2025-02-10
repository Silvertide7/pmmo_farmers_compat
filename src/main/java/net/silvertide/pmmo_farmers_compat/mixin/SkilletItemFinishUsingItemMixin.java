package net.silvertide.pmmo_farmers_compat.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.silvertide.pmmo_farmers_compat.util.EventUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import vectorwing.farmersdelight.common.block.entity.SkilletBlockEntity;
import vectorwing.farmersdelight.common.item.SkilletItem;

//@Mixin(SkilletItem.class)
public abstract class SkilletItemFinishUsingItemMixin {

//    @ModifyArg(method = "lambda$finishUsingItem$0(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/crafting/RecipeHolder;)V",
//            at = @At(value = "INVOKE",
//                    target = "Lnet/minecraft/world/entity/player/Inventory;add(Lnet/minecraft/world/item/ItemStack;)Z"),
//            remap = false)
//    private static ItemStack modifySpawnItemEntityArg(ItemStack resultStack) {
//        SkilletBlockEntity blockEntity = ((SkilletItem)(Object) this);
//
//        if(blockEntity != null) {
//            Level level = blockEntity.getLevel();
//            BlockPos pos = blockEntity.getBlockPos();
//            EventUtil.postFurnaceBurnEvent(level, pos, resultStack);
//        }
//        return resultStack;
//    }
}
