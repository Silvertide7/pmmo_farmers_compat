package net.silvertide.pmmo_farmers_compat.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.silvertide.pmmo_farmers_compat.util.EventUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import umpaz.nethersdelight.common.block.entity.AbstractStoveBlockEntity;

@Mixin(AbstractStoveBlockEntity.class)
public abstract class NetherDelightStoveCookAndOutputItemsMixin {

    @Inject(
            method = "cookTick(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lumpaz/nethersdelight/common/block/entity/AbstractStoveBlockEntity;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/Containers;dropItemStack(Lnet/minecraft/world/level/Level;DDDLnet/minecraft/world/item/ItemStack;)V",
                    shift = At.Shift.BEFORE,
                    // the target is a Minecraft method, so it must be remapped even though the
                    // enclosing method is not
                    remap = true
            ),
            locals = LocalCapture.CAPTURE_FAILHARD,
            remap = false,
            // Nether's Delight is optional and CAPTURE_FAILHARD pins its exact local layout, so a
            // future recompile should cost stove XP rather than crash the game.
            require = 0
    )
    private static void beforeDropItemStack(
            Level level, BlockPos pos, BlockState state, AbstractStoveBlockEntity<?, ?> stove,
            CallbackInfo ci,
            boolean didChange, int i, ItemStack stoveStack, int var10002, Container inventoryWrapper, ItemStack result
    ) {
        if (level == null || level.isClientSide) return;
        EventUtil.postFurnaceBurnEvent(level, stove.getBlockPos(), result);
    }
}
