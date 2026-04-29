package net.silvertide.pmmo_farmers_compat.config;

import harmonised.pmmo.api.enums.EventType;
import net.neoforged.neoforge.common.ModConfigSpec;

public final class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.EnumValue<EventType> XP_EVENT_TYPE = BUILDER
            .comment(
                    "Which PMMO EventType to emit when a Farmer's Delight block finishes cooking.",
                    "SMELT awards XP based on the input ingredient.",
                    "SMELTED awards XP based on the cooked result. (default)"
            )
            .defineEnum("xp_event_type", EventType.SMELTED, EventType.SMELT, EventType.SMELTED);

    public static final ModConfigSpec SPEC = BUILDER.build();

    private Config() {}
}
