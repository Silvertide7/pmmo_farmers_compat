package net.silvertide.pmmo_farmers_compat.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.silvertide.pmmo_farmers_compat.util.EventUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import vectorwing.farmersdelight.common.block.entity.CuttingBoardBlockEntity;

@Mixin(
        value = {CuttingBoardBlockEntity.class},
        remap = false
)
public abstract class CuttingBoardProcessStoredItemMixin {
    public CuttingBoardProcessStoredItemMixin() {}

    @ModifyArg(
            method = {"lambda$processStoredItemUsingTool$2"},
            at = @At(
                    value = "INVOKE",
                    target = "Lvectorwing/farmersdelight/common/utility/ItemUtils;spawnItemEntity(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;DDDDDD)V"
            ),
            index = 1,
            order = 1100
    )
    public ItemStack modifySpawnItemEntityArg(ItemStack stack, @Local(argsOnly = true) Player player) {
        CuttingBoardBlockEntity cuttingBoardBlockEntity = ((CuttingBoardBlockEntity)(Object)this);
        if(cuttingBoardBlockEntity != null) {
            Level level = cuttingBoardBlockEntity.getLevel();
            for(int i = 0; i < stack.getCount(); i++) {
                if(level != null) {
                    NeoForge.EVENT_BUS.post(new PlayerEvent.ItemCraftedEvent(player, stack, new SimpleContainer(0)));
                }
            }
        }
        return stack;
    }
}
