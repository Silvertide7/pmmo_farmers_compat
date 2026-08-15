## 2.1.0
---
- Fixed crash with Vanilla's Delight 1.0.5a. Its stove now extends Farmer's Delight's stove, so the existing stove mixin covers it and the dedicated one was removed.
- Added XP support for Vanilla's Delight cutting boards.
- Vanilla's Delight 1.0.5a is now the minimum supported version.
- Cutting board XP now goes to the player who did the cutting instead of whoever placed the board. Affects both Farmer's Delight and Vanilla's Delight boards.
- Fixed the Farmer's Delight version requirement, which accepted any 1.x version instead of 1.3.1+.
- A future Nether's Delight, My Nether's Delight or Vanilla's Delight update that changes their internals now costs the affected XP instead of crashing the game.
- Added support for My Nether's Delight. Its stove was already covered; its blazier now awards XP too.
- Fixed Nether's Delight stove XP, which never applied because its injection point was not being remapped.