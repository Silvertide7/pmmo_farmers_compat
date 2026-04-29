## 1.2.2
- Removed custom compat for My Nethers Delight and Vanilla's Delight, because it's not needed anymore. All farmers delight compat mods that add a stove should extend farmers delights AbstractStoveBlockEntity now, and this will work automatically. My Nethers Delight does this so that works fine now, Vanillas Delight has yet to update.

## 1.2.1
- Added back in compat with My Nethers Delight and Vanilla's Delight

## 1.2.0
- Updated for Farmer's Delight 1.3.0 (now requires 1.3+ and NeoForge 21.1.219+).
- Added a server config (`config/pmmo_farmers_compat-server.toml`) with `xp_event_type` — choose `SMELT` (XP based on the input ingredient) or `SMELTED` (XP based on the cooked result). Default is `SMELTED`, which preserves 1.1.x behavior.
- The cooking-pot mixin no longer over-fires when an incompatible meal is in the output slot — XP now only awards on cooks that actually deposit the result.
- Temporarily removed compat for My Nether's Delight and Vanilla's Delight. They need to be updated to work with Farmers 1.3. I'll add them back after.

## 1.1.1
- CONFIG UPDATE REQUIRED: Update all SMELT xp awards to SMELTED xp awards for all items that are created by farmers delight blocks. The latest Project MMO update added the SMELTED event. This event is emitted on the item outputted from a process like burning in the furnace. This is now the most correct way to grant XP for this mod as I am giving xp based on the output items from the Stove and the Cooking Pot, not any of the input items. This means you will now need to configure xp for the SMELTED event for these output items.
- Created a custom event to handle the cook events. This will stop occasionally getting double experience when you shouldn't.

## 1.1.0
- CONFIG UPDATE REQUIRED: Update all SMELT xp awards to SMELTED xp awards for all items that are created by farmers delight blocks. The latest Project MMO update added the SMELTED event. This event is emitted on the item outputted from a process like burning in the furnace. This is now the most correct way to grant XP for this mod as I am giving xp based on the output items from the Stove and the Cooking Pot, not any of the input items. This means you will now need to configure xp for the SMELTED event for these output items.
- Added compat with My Nethers Delight stove.
- Added compat with the Vanilla's Delight stoves.
- This mod will now complain if you have it installed but not Project MMO and Farmers Delight installed.