package net.silvertide.pmmo_farmers_compat;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(PMMOFarmersCompat.MOD_ID)
public class PMMOFarmersCompat
{
    public static final String MOD_ID = "pmmo_farmers_compat";
    public static final Logger LOGGER = LogUtils.getLogger();

    public PMMOFarmersCompat()
    {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
}
