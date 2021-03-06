package greymerk.roguelike.monster.profiles;

import com.github.fnar.util.Colors;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

import greymerk.roguelike.monster.IEntity;
import greymerk.roguelike.monster.IMonsterProfile;
import greymerk.roguelike.monster.MonsterProfile;
import greymerk.roguelike.treasure.loot.provider.ItemNovelty;

public class ProfileAshlea implements IMonsterProfile {

  @Override
  public void equip(World world, Random rand, int level, IEntity mob) {
    mob.setChild(true);
    MonsterProfile.VILLAGER.getMonsterProfile().equip(world, rand, level, mob);
    ItemStack weapon = ItemNovelty.getItem(ItemNovelty.ASHLEA);
    mob.equipMainhand(weapon);
    mob.equipArmor(world, rand, level, Colors.PINK_FLAMINGO);
  }
}
