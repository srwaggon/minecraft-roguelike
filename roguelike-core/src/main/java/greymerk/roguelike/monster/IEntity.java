package greymerk.roguelike.monster;

import com.github.fnar.minecraft.item.ArmourType;
import com.github.fnar.minecraft.item.Arrow;
import com.github.fnar.minecraft.item.ItemMapper1_12;
import com.github.fnar.roguelike.loot.special.armour.SpecialArmour;
import com.github.fnar.util.Color;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.Random;

import greymerk.roguelike.treasure.loot.Enchant;
import greymerk.roguelike.treasure.loot.Shield;
import greymerk.roguelike.treasure.loot.Slot;
import greymerk.roguelike.treasure.loot.provider.ItemArmour;
import greymerk.roguelike.treasure.loot.provider.ItemWeapon;

public interface IEntity {

  default void equipArmor(World world, Random rand, int level, Color color) {
    Arrays.stream(ArmourType.values()).forEach(armourType -> {
      EntityEquipmentSlot slot = Slot.getSlot(armourType.asSlot());
      int enchantLevel = Enchant.canEnchant(world.getDifficulty(), rand, level) ? Enchant.getLevel(rand, level) : 0;
      boolean isSpecialArmour = enchantLevel > 0 && rand.nextInt(20 + (level * 10)) == 0;
      ItemStack item = isSpecialArmour
          ? SpecialArmour.createArmour(rand, level)
          : ItemArmour.getRandom(rand, level, enchantLevel, armourType, color);
      setSlot(slot, item);
    });
  }

  default void equipBow(World world, Random random, int level) {
    equipMainhand(ItemWeapon.getBow(random, level, Enchant.canEnchant(world.getDifficulty(), random, level)));
  }

  default void equipArrows(Arrow arrow) {
    equipOffhand(ItemMapper1_12.map(arrow.asItemStack()));
  }

  default void equipShield(Random rand) {
    equipOffhand(Shield.get(rand));
  }

  default void equipOffhand(ItemStack item) {
    setSlot(EntityEquipmentSlot.OFFHAND, item);
  }

  default void equipMainhand(ItemStack itemStack) {
    setSlot(EntityEquipmentSlot.MAINHAND, itemStack);
  }

  void setSlot(EntityEquipmentSlot slot, ItemStack item);

  void setMobClass(MobType type, boolean clear);

  void setChild(boolean child);

  boolean instance(Class<?> type);

  void setName(String name);
}
