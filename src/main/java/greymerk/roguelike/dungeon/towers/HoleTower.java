package greymerk.roguelike.dungeon.towers;

import java.util.Random;

import greymerk.roguelike.theme.ThemeBase;
import greymerk.roguelike.worldgen.BlockJumble;
import greymerk.roguelike.worldgen.Cardinal;
import greymerk.roguelike.worldgen.Coord;
import greymerk.roguelike.worldgen.IBlockFactory;
import greymerk.roguelike.worldgen.WorldEditor;
import greymerk.roguelike.worldgen.blocks.BlockType;
import greymerk.roguelike.worldgen.blocks.Vine;
import greymerk.roguelike.worldgen.shapes.RectSolid;

public class HoleTower implements ITower {

  @Override
  public void generate(WorldEditor editor, Random rand, ThemeBase theme, Coord origin) {

    IBlockFactory air = BlockType.get(BlockType.AIR);
    IBlockFactory blocks = theme.getPrimary().getWall();
    Coord floor = Tower.getBaseCoord(editor, origin);

    Coord start;
    Coord end;

    start = new Coord(floor);
    start.translate(Cardinal.NORTH);
    start.translate(Cardinal.EAST);
    start.translate(Cardinal.UP, 3);
    end = new Coord(origin);
    end.translate(Cardinal.SOUTH);
    end.translate(Cardinal.WEST);

    RectSolid.fill(editor, rand, start, end, air);
    start.translate(Cardinal.NORTH, 2);
    start.translate(Cardinal.EAST, 2);
    end.translate(Cardinal.SOUTH, 2);
    end.translate(Cardinal.WEST, 2);
    end.translate(Cardinal.UP);

    BlockJumble rubble = new BlockJumble();
    rubble.addBlock(blocks);
    rubble.addBlock(BlockType.get(BlockType.AIR));
    rubble.addBlock(BlockType.get(BlockType.DIRT));
    rubble.addBlock(BlockType.get(BlockType.DIRT_COARSE));
    rubble.addBlock(BlockType.get(BlockType.STONE_SMOOTH));

    RectSolid.fill(editor, rand, start, end, rubble, false, true);
    Vine.fill(editor, start, end);
  }

}
