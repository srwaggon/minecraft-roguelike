package greymerk.roguelike.monster.profiles;

import net.minecraft.world.World;

import java.util.Random;

import greymerk.roguelike.monster.IEntity;
import greymerk.roguelike.monster.IMonsterProfile;
import greymerk.roguelike.monster.MobType;

public class ProfileEvoker implements IMonsterProfile {

  @Override
  public void equip(World world, Random rand, int level, IEntity mob) {
    mob.setMobClass(MobType.EVOKER, true);
  }

}
