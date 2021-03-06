package com.github.fnar.minecraft.item;

import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.Random;

import greymerk.roguelike.treasure.loot.Equipment;
import greymerk.roguelike.treasure.loot.Quality;

public enum WeaponType {
  BOW,
  SWORD,
  ;

  public static Item getBowItem() {
    return Items.BOW;
  }

  public static Item getSwordItem(Quality quality) {
    return SWORD.asItem(quality);
  }

  public static WeaponType random(Random random) {
    int choice = random.nextInt(values().length);
    return values()[choice];
  }

  public Item asItem(Quality quality) {
    switch(this) {
      case BOW:
        return EquipmentType.asItem(quality, Items.BOW, Items.BOW, Items.BOW, Items.BOW, Items.BOW);
      case SWORD:
        return EquipmentType.asItem(quality, Items.WOODEN_SWORD, Items.STONE_SWORD, Items.IRON_SWORD, Items.GOLDEN_SWORD, Items.DIAMOND_SWORD);
    }
    throw new IllegalArgumentException("Unexpected WeaponType: " + this);
  }

  public Equipment asEquipment() {
    switch(this) {
      case BOW:
        return Equipment.BOW;
      case SWORD:
        return Equipment.SWORD;
    }
    throw new IllegalArgumentException("Unexpected WeaponType: " + this);
  }
}
