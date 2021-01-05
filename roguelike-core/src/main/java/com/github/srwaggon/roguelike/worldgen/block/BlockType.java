package com.github.srwaggon.roguelike.worldgen.block;

import com.github.srwaggon.roguelike.worldgen.SingleBlockBrush;

public enum BlockType {

  ACACIA_DOOR,
  ACACIA_FENCE,
  ACACIA_LEAVES2,
  ACACIA_LOG,
  ACACIA_PLANK,
  ACACIA_SAPLING,
  ACACIA_SLAB,
  ACACIA_STAIRS,
  AIR,
  ANDESITE,
  ANDESITE_POLISHED,
  ANVIL,
  BED,
  BEDROCK,
  BIRCH_DOOR,
  BIRCH_FENCE,
  BIRCH_LEAVES,
  BIRCH_LOG,
  BIRCH_PLANK,
  BIRCH_SAPLING,
  BIRCH_SLAB,
  BIRCH_STAIRS,
  BONE_BLOCK,
  BREWING_STAND,
  BRICK,
  BRICK_SLAB,
  BRICK_STAIRS,
  BROWN_MUSHROOM,
  CACTUS,
  CAKE,
  CARPET,
  CHEST,
  CHISELED_QUARTZ,
  COAL_BLOCK,
  COBBLESTONE,
  COBBLESTONE_MOSSY,
  COBBLESTONE_WALL,
  COBBLE_SLAB,
  COMPARATOR,
  CONCRETE,
  CONCRETE_POWDER,
  CRAFTING_TABLE,
  CRIMSON_DOOR,
  DARK_OAK_DOOR,
  DARK_OAK_FENCE,
  DARK_OAK_LEAVES,
  DARK_OAK_LOG,
  DARK_OAK_PLANK,
  DARK_OAK_SAPLING,
  DARK_OAK_SLAB,
  DARK_OAK_STAIRS,
  DIAMOND_BLOCK,
  DIORITE,
  DIORITE_POLISHED,
  DIRT,
  DIRT_COARSE,
  DIRT_PODZOL,
  DISPENSER,
  DROPPER,
  EMERALD_BLOCK,
  ENCHANTING_TABLE,
  ENDER_BRICK,
  ENDER_CHEST,
  END_STONE,
  FARMLAND,
  FENCE_NETHER_BRICK,
  FIRE,
  FLOWER_POT,
  FURNACE,
  GLASS,
  GLOWSTONE,
  GOLD_BLOCK,
  GRANITE,
  GRANITE_POLISHED,
  GRASS,
  GRAVEL,
  HOPPER,
  ICE,
  ICE_PACKED,
  IRON_BAR,
  IRON_BLOCK,
  IRON_DOOR,
  JUKEBOX,
  JUNGLE_DOOR,
  JUNGLE_FENCE,
  JUNGLE_LEAVES,
  JUNGLE_LOG,
  JUNGLE_PLANK,
  JUNGLE_SAPLING,
  JUNGLE_SLAB,
  JUNGLE_STAIRS,
  LAPIS_BLOCK,
  LAVA_FLOWING,
  LAVA_STILL,
  LEGACY_OAK_SLAB,
  LEVER,
  MAGMA,
  MOB_SPAWNER,
  MYCELIUM,
  NETHERBRICK,
  NETHERBRICK_SLAB,
  NETHERRACK,
  NETHER_BRICK_STAIRS,
  NETHER_WART_BLOCK,
  NOTEBLOCK,
  OAK_DOOR,
  OAK_FENCE,
  OAK_LEAVES,
  OAK_LOG,
  OAK_PLANK,
  OAK_SAPLING,
  OAK_SLAB,
  OAK_STAIRS,
  OBSIDIAN,
  ORE_COAL,
  ORE_DIAMOND,
  ORE_EMERALD,
  ORE_GOLD,
  ORE_IRON,
  ORE_LAPIS,
  ORE_QUARTZ,
  ORE_REDSTONE,
  PILLAR_QUARTZ,
  PISTON,
  PRESSURE_PLATE_STONE,
  PRESSURE_PLATE_WOODEN,
  PRISMARINE,
  PRISMARINE_DARK,
  PRISMITE,
  PUMPKIN,
  PURPUR_BLOCK,
  PURPUR_DOUBLE_SLAB,
  PURPUR_PILLAR,
  PURPUR_SLAB,
  PURPUR_STAIR,
  PURPUR_STAIRS,
  QUARTZ,
  QUARTZ_SLAB,
  QUARTZ_STAIRS,
  REDSTONE_BLOCK,
  REDSTONE_LAMP,
  REDSTONE_LAMP_LIT,
  REDSTONE_TORCH,
  REDSTONE_WIRE,
  RED_FLOWER,
  RED_MUSHROOM,
  RED_NETHERBRICK,
  RED_SANDSTONE_SLAB,
  RED_SANDSTONE_STAIRS,
  REEDS,
  SAND,
  SANDSTONE,
  SANDSTONE_CHISELED,
  SANDSTONE_RED,
  SANDSTONE_RED_CHISELED,
  SANDSTONE_RED_SMOOTH,
  SANDSTONE_SLAB,
  SANDSTONE_SMOOTH,
  SANDSTONE_STAIRS,
  SAND_RED,
  SEA_LANTERN,
  BOOKSHELF,
  SKULL,
  SMOOTH_QUARTZ,
  SMOOTH_RED_SANDSTONE_SLAB,
  SNOW,
  SOUL_SAND,
  SPRUCE_DOOR,
  SPRUCE_FENCE,
  SPRUCE_LEAVES,
  SPRUCE_LOG,
  SPRUCE_PLANK,
  SPRUCE_SAPLING,
  SPRUCE_SLAB,
  SPRUCE_STAIRS,
  STAINED_GLASS,
  STAINED_GLASS_PANE,
  CLAY,
  STAINED_HARDENED_CLAY,
  STICKY_PISTON,
  STONEBRICK_SLAB,
  STONE_BRICK,
  STONE_BRICK_CHISELED,
  STONE_BRICK_CRACKED,
  STONE_BRICK_MOSSY,
  STONE_BRICK_STAIRS,
  INFESTED_BLOCK,
  STONE_SLAB,
  STONE_SMOOTH,
  STONE_STAIRS,
  TALL_PLANT,
  TERRACOTTA,
  TNT,
  TORCH,
  TRAPDOOR,
  TRAPPED_CHEST,
  REPEATER,
  VINE,
  WARPED_DOOR,
  WATER_FLOWING,
  WATER_STILL,
  WEB,
  WOOL,
  YELLOW_FLOWER,
  PLANT,
  CROP;

  public SingleBlockBrush getBrush() {
    return new SingleBlockBrush(this);
  }

}
