package net.silvertide.pmmo_farmers_compat.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import harmonised.pmmo.api.APIUtils;
import harmonised.pmmo.api.enums.EventType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.LogicalSide;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.common.item.SkilletItem;

import java.util.Optional;

@Mixin(SkilletItem.class)
public abstract class SkilletItemFinishUsingItemMixin extends BlockItem {
    public SkilletItemFinishUsingItemMixin(Block block, Properties properties) {
        super(block, properties);
    }

    @Inject(
            method = "finishUsingItem(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/item/ItemStack;",
            at = @At(value = "INVOKE",
                    target = "Ljava/util/Optional;ifPresent(Ljava/util/function/Consumer;)V"))
    private void pmmo_farmers_compat$awardSmeltXp(
            ItemStack stack, Level level, LivingEntity entity, CallbackInfoReturnable<ItemStack> cir,
            @Local Player player,
            @Local(ordinal = 1) ItemStack cookingStack,
            @Local Optional<RecipeHolder<CampfireCookingRecipe>> cookingRecipe) {
        if (level.isClientSide) return;
        if (cookingRecipe.isEmpty()) return;
        APIUtils.getXpAwardMap(cookingStack, EventType.SMELT, LogicalSide.SERVER, player).forEach((skill, xpChange) -> {
            APIUtils.addXp(skill, player, xpChange);
        });
    }
}
