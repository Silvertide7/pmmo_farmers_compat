package net.silvertide.pmmo_farmers_compat;


import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.silvertide.pmmo_farmers_compat.config.Config;

@Mod(PMMOFarmersCompat.MOD_ID)
public class PMMOFarmersCompat
{
    public static final String MOD_ID = "pmmo_farmers_compat";
    public PMMOFarmersCompat(ModContainer container) {
        container.registerConfig(ModConfig.Type.SERVER, Config.SPEC);
    }
}
