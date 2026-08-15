package net.silvertide.pmmo_farmers_compat.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.chaolux.vanilladelight.common.block.entity.CommonCuttingBoardBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.silvertide.pmmo_farmers_compat.util.EventUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

// Vanilla's Delight's cutting board *block* extends FD's, but its block entity copies
// FD's rather than extending it, so the FD cutting board mixin does not cover it.
// require = 0: VD is optional and this targets a compiler-generated lambda name, so a
// future VD recompile should cost cutting board XP rather than crash the game.
@Mixin(CommonCuttingBoardBlockEntity.class)
public abstract class VanillaDelightCuttingBoardProcessStoredItemMixin {
    @ModifyArg(method = "lambda$processStoredItemUsingTool$2(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;Lvectorwing/farmersdelight/common/crafting/CuttingBoardRecipe;)V", at = @At(value = "INVOKE", target = "vectorwing/farmersdelight/common/utility/ItemUtils.spawnItemEntity(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;DDDDDD)V"), remap = false, require = 0)
    public ItemStack modifySpawnItemEntityArg(ItemStack resultStack, @Local(argsOnly = true) Player player){
        for(int i = 0; i < resultStack.getCount(); i++) {
            EventUtil.postPlayerCraftEvent(player, resultStack);
        }
        return resultStack;
    }
}
