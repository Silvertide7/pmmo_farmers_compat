package net.silvertide.pmmo_farmers_compat.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import harmonised.pmmo.api.enums.EventType;
import harmonised.pmmo.core.Core;
import harmonised.pmmo.features.party.PartyUtils;
import harmonised.pmmo.util.TagUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.common.item.SkilletItem;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mixin(SkilletItem.class)
public abstract class SkilletItemOnFinishUsingMixin {

    @Inject(
            method = "finishUsingItem(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/item/ItemStack;",
            at = @At(value = "INVOKE",
                    target = "Ljava/util/Optional;ifPresent(Ljava/util/function/Consumer;)V"))
    private void pmmo_farmers_compat$awardSmeltXp(
            ItemStack stack, Level level, LivingEntity entity, CallbackInfoReturnable<ItemStack> cir,
            @Local Player player,
            @Local Optional<CampfireCookingRecipe> cookingRecipe) {
        if (level.isClientSide) return;
        if (cookingRecipe.isEmpty()) return;
        if (!(player instanceof ServerPlayer serverPlayer)) return;

        ItemStack resultStack = cookingRecipe.get().getResultItem(level.registryAccess());
        Core core = Core.get(level);
        CompoundTag eventHook = new CompoundTag();
        eventHook = TagUtils.mergeTags(eventHook, core.getPerkRegistry().executePerk(EventType.SMELT, player, eventHook));
        Map<String, Long> xpAwards = core.getExperienceAwards(EventType.SMELT, resultStack, player, eventHook);
        List<ServerPlayer> partyMembersInRange = PartyUtils.getPartyMembersInRange(serverPlayer);
        core.awardXP(partyMembersInRange, xpAwards);
    }
}
