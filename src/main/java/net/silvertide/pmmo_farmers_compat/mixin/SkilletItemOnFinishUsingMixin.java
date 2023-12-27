package net.silvertide.pmmo_farmers_compat.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import harmonised.pmmo.api.enums.EventType;
import harmonised.pmmo.core.Core;
import harmonised.pmmo.features.party.PartyUtils;
import harmonised.pmmo.util.TagUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import vectorwing.farmersdelight.common.item.SkilletItem;

import java.util.List;
import java.util.Map;

@Mixin(SkilletItem.class)
public abstract class SkilletItemOnFinishUsingMixin {
    @ModifyArg(method = "lambda$finishUsingItem$1(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/crafting/CampfireCookingRecipe;)V", at = @At(value = "INVOKE", target = "net/minecraft/world/entity/player/Inventory.add (Lnet/minecraft/world/item/ItemStack;)Z"),  remap = false)
    private static ItemStack modifyInventoryAddArgs(ItemStack resultStack, @Local(argsOnly = true) Level level, @Local(argsOnly = true) Player player, @Local(argsOnly = true) ItemStack itemStack, @Local(argsOnly = true) CampfireCookingRecipe campfireRecipe) {
        if(player instanceof ServerPlayer serverPlayer) {
            Core core = Core.get(level);
            CompoundTag eventHook = new CompoundTag();
            eventHook = TagUtils.mergeTags(eventHook, core.getPerkRegistry().executePerk(EventType.SMELT, player, eventHook));
            Map<String, Long> xpAwards = core.getExperienceAwards(EventType.SMELT, resultStack, player, eventHook);
            List<ServerPlayer> partyMembersInRange = PartyUtils.getPartyMembersInRange(serverPlayer);
            core.awardXP(partyMembersInRange, xpAwards);
        }
        return resultStack;
    }
}
