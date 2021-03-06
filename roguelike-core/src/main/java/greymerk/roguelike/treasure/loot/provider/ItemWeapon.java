package greymerk.roguelike.treasure.loot.provider;

import com.google.gson.JsonObject;

import com.github.fnar.minecraft.item.WeaponType;
import com.github.fnar.roguelike.loot.special.weapons.SpecialBow;
import com.github.fnar.roguelike.loot.special.weapons.SpecialSword;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import greymerk.roguelike.treasure.loot.Enchant;
import greymerk.roguelike.treasure.loot.Equipment;
import greymerk.roguelike.treasure.loot.Quality;
import greymerk.roguelike.util.IWeighted;
import greymerk.roguelike.util.WeightedChoice;
import greymerk.roguelike.util.WeightedRandomizer;


public class ItemWeapon extends ItemBase {

  private static final Map<Integer, IWeighted<Quality>> weaponQuality = new HashMap<>();
  static {
    ItemWeapon.loadWeaponQualityOddsTable();
  }

  private Equipment type;
  private boolean enchant;
  private Quality quality;

  public ItemWeapon(int weight, int level) {
    super(weight, level);
  }

  public ItemWeapon(JsonObject data, int weight) throws Exception {
    super(weight);

    this.enchant = !data.has("ench") || data.get("ench").getAsBoolean();

    if (data.has("quality")) {
      try {
        this.quality = Quality.valueOf(data.get("quality").getAsString().toUpperCase());
      } catch (Exception e) {
        throw new Exception("No such Quality as: " + data.get("quality").getAsString());
      }
    }

    if (data.has("equipment")) {
      try {
        this.type = Equipment.valueOf(data.get("equipment").getAsString().toUpperCase());
      } catch (Exception e) {
        throw new Exception("No such Equipment as: " + data.get("equipment").getAsString());
      }
    }

    if (!data.has("level")) {
      throw new Exception("Weapon Loot requires a level");
    }
    this.level = data.get("level").getAsInt();
  }

  public static ItemStack getRandom(Random rand, int rank, boolean enchant) {
    if (rand.nextInt(10) == 0) {
      return ItemWeapon.getBow(rand, rank, enchant);
    } else {
      return ItemWeapon.getSword(rand, rank, enchant);
    }
  }

  public static ItemStack getBow(Random rand, int level, boolean enchant) {

    if (rand.nextInt(20 + (level * 10)) == 0) {
      return new SpecialBow(rand, level).complete();
    }

    ItemStack bow = new ItemStack(WeaponType.getBowItem());

    if (enchant && rand.nextInt(6 - level) == 0) {
      Enchant.enchantItem(rand, bow, Enchant.getLevel(rand, level));
    }

    return bow;

  }

  public static ItemStack getSword(Random random, int level, boolean enchant) {
    ItemStack sword;

    if (enchant && random.nextInt(10 + (level * 10)) == 0) {
      return new SpecialSword(random, level).complete();
    }

    sword = new ItemStack(randomSword(random, level));

    if (enchant && random.nextInt(6 - level) == 0) {
      Enchant.enchantItem(random, sword, Enchant.getLevel(random, level));
    }

    return sword;
  }

  public static ItemStack getSword(Random rand, int level, boolean enchant, Quality quality) {
    Item swordItem = quality == null
        ? randomSword(rand, level)
        : WeaponType.getSwordItem(quality);

    ItemStack swordItemStack = new ItemStack(swordItem);
    if (enchant) {
      return Enchant.enchantItem(rand, swordItemStack, Enchant.getLevel(rand, level));
    }
    return swordItemStack;
  }

  private static Item randomSword(Random random, int level) {
    Quality quality = rollWeaponQuality(random, level);
    return WeaponType.getSwordItem(quality);
  }

  public static void loadWeaponQualityOddsTable() {
    for (int i = 0; i < 5; ++i) {
      WeightedRandomizer<Quality> weapon = new WeightedRandomizer<>();
      switch (i) {
        case 0:
          weapon.add(new WeightedChoice<>(Quality.WOOD, 200));
          weapon.add(new WeightedChoice<>(Quality.STONE, 50));
          weapon.add(new WeightedChoice<>(Quality.IRON, 10));
          weapon.add(new WeightedChoice<>(Quality.GOLD, 3));
          weapon.add(new WeightedChoice<>(Quality.DIAMOND, 1));
          break;
        case 1:
          weapon.add(new WeightedChoice<>(Quality.WOOD, 100));
          weapon.add(new WeightedChoice<>(Quality.STONE, 30));
          weapon.add(new WeightedChoice<>(Quality.IRON, 10));
          weapon.add(new WeightedChoice<>(Quality.GOLD, 3));
          weapon.add(new WeightedChoice<>(Quality.DIAMOND, 1));
          break;
        case 2:
          weapon.add(new WeightedChoice<>(Quality.WOOD, 50));
          weapon.add(new WeightedChoice<>(Quality.STONE, 20));
          weapon.add(new WeightedChoice<>(Quality.IRON, 10));
          weapon.add(new WeightedChoice<>(Quality.GOLD, 3));
          weapon.add(new WeightedChoice<>(Quality.DIAMOND, 1));
          break;
        case 3:
          weapon.add(new WeightedChoice<>(Quality.WOOD, 1));
          weapon.add(new WeightedChoice<>(Quality.STONE, 3));
          weapon.add(new WeightedChoice<>(Quality.IRON, 5));
          weapon.add(new WeightedChoice<>(Quality.GOLD, 3));
          weapon.add(new WeightedChoice<>(Quality.DIAMOND, 1));
          break;
        case 4:
          weapon.add(new WeightedChoice<>(Quality.WOOD, 1));
          weapon.add(new WeightedChoice<>(Quality.STONE, 2));
          weapon.add(new WeightedChoice<>(Quality.IRON, 15));
          weapon.add(new WeightedChoice<>(Quality.GOLD, 5));
          weapon.add(new WeightedChoice<>(Quality.DIAMOND, 3));
          break;
      }
      weaponQuality.put(i, weapon);
    }
  }

  public static Quality rollWeaponQuality(Random rand, int level) {
    return weaponQuality.get(level).get(rand);
  }

  @Override
  public ItemStack getLootItem(Random rand, int level) {
    if (type != null) {
      switch (type) {
        case BOW:
          return getBow(rand, level, enchant);
        case SWORD:
          return getSword(rand, level, enchant, quality);
        default:
          return getSword(rand, level, enchant);
      }
    }
    return getRandom(rand, level, true);
  }

}
