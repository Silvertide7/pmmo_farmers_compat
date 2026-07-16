package net.silvertide.pmmo_farmers_compat;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.silvertide.pmmo_farmers_compat.config.Config;

@Mod(PMMOFarmersCompat.MOD_ID)
public class PMMOFarmersCompat
{
    public static final String MOD_ID = "pmmo_farmers_compat";
    public PMMOFarmersCompat() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        MinecraftForge.EVENT_BUS.register(this);
    }
}
