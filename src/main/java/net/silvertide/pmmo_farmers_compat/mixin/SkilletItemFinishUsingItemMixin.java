package net.silvertide.pmmo_farmers_compat.mixin;

import harmonised.pmmo.api.APIUtils;
import harmonised.pmmo.api.enums.EventType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.LogicalSide;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.common.item.SkilletItem;

@Mixin(SkilletItem.class)
public abstract class SkilletItemFinishUsingItemMixin extends BlockItem {
    public SkilletItemFinishUsingItemMixin(Block block, Properties properties) {
        super(block, properties);
    }

    @Inject(method = "lambda$finishUsingItem$0(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/crafting/RecipeHolder;)V",
            at = @At(value = "INVOKE",
                    target = "net/minecraft/world/item/crafting/CampfireCookingRecipe.assemble (Lnet/minecraft/world/item/crafting/SingleRecipeInput;Lnet/minecraft/core/HolderLookup$Provider;)Lnet/minecraft/world/item/ItemStack;",
                    shift = At.Shift.AFTER),
            remap = false)
    private static void modifySpawnItemEntityArg(ItemStack cookingStack, Level level, Player player, ItemStack stack, RecipeHolder recipe, CallbackInfo ci) {
        APIUtils.getXpAwardMap(cookingStack, EventType.SMELT, LogicalSide.SERVER, player).forEach((skill, xpChange) -> {
            APIUtils.addXp(skill, player, xpChange);
        });
    }
}
