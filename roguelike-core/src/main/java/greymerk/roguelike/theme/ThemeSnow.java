package greymerk.roguelike.theme;

import com.github.fnar.minecraft.block.BlockType;
import com.github.fnar.minecraft.block.normal.StairsBlock;
import com.github.fnar.minecraft.block.normal.Wood;
import com.github.fnar.minecraft.block.redstone.DoorBlock;

import greymerk.roguelike.worldgen.BlockBrush;
import greymerk.roguelike.worldgen.BlockWeightedRandom;

public class ThemeSnow extends ThemeBase {

  public ThemeSnow() {
    BlockWeightedRandom walls = new BlockWeightedRandom();
    walls.addBlock(BlockType.STONE_BRICK.getBrush(), 10);
    walls.addBlock(BlockType.STONE_BRICK_CRACKED.getBrush(), 1);
    walls.addBlock(BlockType.STONE_BRICK_MOSSY.getBrush(), 1);

    StairsBlock stair = StairsBlock.stoneBrick();
    BlockBrush pillar = Wood.SPRUCE.getLog();

    this.primary = new BlockSet(
        walls,
        walls,
        stair,
        pillar,
        DoorBlock.oak(),
        BlockType.GLOWSTONE.getBrush(),
        BlockType.WATER_FLOWING.getBrush()
    );

    BlockBrush SegmentWall = BlockType.SNOW.getBrush();
    StairsBlock SegmentStair = StairsBlock.brick();
    BlockBrush pillar2 = BlockType.BRICK.getBrush();

    this.secondary = new BlockSet(
        SegmentWall,
        SegmentWall,
        SegmentStair,
        pillar2,
        DoorBlock.oak(),
        BlockType.GLOWSTONE.getBrush(),
        BlockType.WATER_FLOWING.getBrush()
    );
  }
}
