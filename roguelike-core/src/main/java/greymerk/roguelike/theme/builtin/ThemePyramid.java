package greymerk.roguelike.theme.builtin;

import com.github.fnar.minecraft.block.BlockType;
import com.github.fnar.minecraft.block.normal.StairsBlock;

import greymerk.roguelike.theme.BlockSet;
import greymerk.roguelike.theme.Theme;
import greymerk.roguelike.worldgen.BlockBrush;
import greymerk.roguelike.worldgen.BlockWeightedRandom;

public class ThemePyramid extends Theme {

  public ThemePyramid() {

    BlockWeightedRandom walls = new BlockWeightedRandom();
    walls.addBlock(BlockType.SANDSTONE_SMOOTH.getBrush(), 100);
    walls.addBlock(BlockType.SANDSTONE.getBrush(), 10);
    walls.addBlock(BlockType.CHISELED_SANDSTONE.getBrush(), 5);

    StairsBlock stair = StairsBlock.sandstone();
    BlockBrush pillar = BlockType.SANDSTONE_SMOOTH.getBrush();
    BlockBrush SegmentWall = BlockType.CHISELED_SANDSTONE.getBrush();

    this.primary = new BlockSet(walls, stair, pillar);
    this.secondary = new BlockSet(SegmentWall, stair, pillar);

  }

}
