package net.silvertide.pmmo_farmers_compat.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.silvertide.pmmo_farmers_compat.util.EventUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import vectorwing.farmersdelight.common.block.entity.CuttingBoardBlockEntity;

@Mixin(CuttingBoardBlockEntity.class)
public abstract class CuttingBoardProcessStoredItemMixin {
    @ModifyArg(method = "lambda$processStoredItemUsingTool$2(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/crafting/RecipeHolder;)V",
            at = @At(value = "INVOKE",
                    target = "vectorwing/farmersdelight/common/utility/ItemUtils.spawnItemEntity (Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;DDDDDD)V"),
            remap = false)
    public ItemStack modifySpawnItemEntityArg(ItemStack resultStack) {
        CuttingBoardBlockEntity cuttingBoardBlockEntity = ((CuttingBoardBlockEntity)(Object)this);
        if(cuttingBoardBlockEntity != null) {
            Level level = cuttingBoardBlockEntity.getLevel();
            BlockPos pos = cuttingBoardBlockEntity.getBlockPos();
            for(int i = 0; i < resultStack.getCount(); i++) {
                EventUtil.postPlayerCraftEvent(level, pos, resultStack);
            }
        }
        return resultStack;
    }
}
