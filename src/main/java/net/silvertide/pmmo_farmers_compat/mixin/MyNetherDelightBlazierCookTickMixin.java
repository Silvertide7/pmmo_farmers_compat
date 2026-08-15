package net.silvertide.pmmo_farmers_compat.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.soytutta.mynethersdelight.common.block.entity.BlazierBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.silvertide.pmmo_farmers_compat.util.EventUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

// My Nether's Delight is a separate mod from Nether's Delight. Its stove extends Farmer's
// Delight's and is already covered by StoveCookAndOutputItemsMixin; only the blazier, which
// has its own block entity, needs hooking.
// ordinal = 1: cookTick drops twice. The first drops the stored item when the blazier cannot
// cook it; only the second drops the assembled recipe result.
// require = 0: the mod is optional, so a future change there should cost blazier XP rather
// than crash the game.
@Mixin(BlazierBlockEntity.class)
public abstract class MyNetherDelightBlazierCookTickMixin {
    @ModifyArg(
            method = "cookTick(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lcom/soytutta/mynethersdelight/common/block/entity/BlazierBlockEntity;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/Containers;dropItemStack(Lnet/minecraft/world/level/Level;DDDLnet/minecraft/world/item/ItemStack;)V",
                    ordinal = 1,
                    // the target is a Minecraft method, so it must be remapped even though the
                    // enclosing method is not
                    remap = true
            ),
            remap = false,
            require = 0
    )
    private static ItemStack modifyDropItemStackArg(ItemStack resultStack,
                                                    @Local(argsOnly = true) Level level,
                                                    @Local(argsOnly = true) BlockPos pos) {
        EventUtil.postFurnaceBurnEvent(level, pos, resultStack);
        return resultStack;
    }
}
