package net.silvertide.pmmo_farmers_compat.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    public enum XpMode { SMELT, SMELTED }

    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.EnumValue<XpMode> XP_MODE;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        XP_MODE = builder
                .comment("Which PMMO event Farmer's Delight cooking awards XP through.",
                        "SMELT: the cooked item is treated as the recipe input (configure XP under SMELT).",
                        "SMELTED: the cooked item is treated as the recipe output (configure XP under SMELTED).")
                .defineEnum("xpMode", XpMode.SMELT);
        SPEC = builder.build();
    }
}
