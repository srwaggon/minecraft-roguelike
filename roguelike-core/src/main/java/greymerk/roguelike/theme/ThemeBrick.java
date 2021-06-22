package greymerk.roguelike.theme;

import com.github.fnar.minecraft.block.BlockType;
import com.github.fnar.minecraft.block.normal.StairsBlock;
import com.github.fnar.minecraft.block.normal.Wood;

import greymerk.roguelike.worldgen.BlockBrush;

public class ThemeBrick extends ThemeBase {

  public ThemeBrick() {
    BlockBrush walls = BlockType.BRICK.getBrush();
    StairsBlock stair = StairsBlock.brick();
    BlockBrush pillar = Wood.SPRUCE.getLog();
    BlockBrush segmentWall = Wood.SPRUCE.getPlanks();
    this.primary = new BlockSet(walls, stair, walls);
    this.secondary = new BlockSet(segmentWall, stair, pillar);

  }
}
