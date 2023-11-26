package net.silvertide.pmmo_farmers_compat.mixin;

import net.minecraft.world.item.ItemStack;
import net.silvertide.pmmo_farmers_compat.PMMOFarmersCompat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import vectorwing.farmersdelight.common.block.entity.StoveBlockEntity;
import harmonised.pmmo.api.APIUtils;
import harmonised.pmmo.api.enums.EventType;
import harmonised.pmmo.core.Core;
import harmonised.pmmo.features.party.PartyUtils;
import harmonised.pmmo.util.TagUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

@Mixin(StoveBlockEntity.class)
public abstract class StoveBlockEntityMixin {

    @Inject(method = "cookAndOutputItems", at = @At(value = "INVOKE", target = "vectorwing/farmersdelight/common/utility/ItemUtils.spawnItemEntity (Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;DDDDDD)V", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILSOFT, remap = false)
    public void grantXPForStove(CallbackInfo ci, boolean didInventoryChange, int i, ItemStack stoveStack) {
        Core core = Core.get(event.getLevel());
        CompoundTag eventHook = core.getEventTriggerRegistry().executeEventListeners(EventType.SMELT, event, new CompoundTag());
        eventHook.putString(APIUtils.STACK, event.getInput().serializeNBT().getAsString());
        eventHook = TagUtils.mergeTags(eventHook, core.getPerkRegistry().executePerk(EventType.SMELT, player, eventHook));
        Map<String, Long> xpAwards = core.getExperienceAwards(EventType.SMELT, event.getInput(), player, eventHook);
        List<ServerPlayer> partyMembersInRange = PartyUtils.getPartyMembersInRange(player);
        core.awardXP(partyMembersInRange, xpAwards);
        PMMOFarmersCompat.LOGGER.info(stoveStack.toString());
    }
}
