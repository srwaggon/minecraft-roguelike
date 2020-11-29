package greymerk.roguelike.dungeon.segment.part;

import java.util.Random;

import greymerk.roguelike.dungeon.DungeonLevel;
import greymerk.roguelike.theme.ThemeBase;
import greymerk.roguelike.worldgen.Cardinal;
import greymerk.roguelike.worldgen.Coord;
import greymerk.roguelike.worldgen.IStair;
import greymerk.roguelike.worldgen.MetaBlock;
import greymerk.roguelike.worldgen.WorldEditor;
import greymerk.roguelike.worldgen.blocks.BlockType;
import greymerk.roguelike.worldgen.shapes.RectSolid;

public class SegmentSewerArch extends SegmentBase {

  @Override
  protected void genWall(WorldEditor editor, Random rand, DungeonLevel level, Cardinal dir, ThemeBase theme, Coord origin) {

    IStair stair = theme.getSecondary().getStair();
    stair.setOrientation(dir.reverse(), true);
    MetaBlock water = BlockType.get(BlockType.WATER_FLOWING);
    MetaBlock air = BlockType.get(BlockType.AIR);
    MetaBlock bars = BlockType.get(BlockType.IRON_BAR);
    MetaBlock mossy = BlockType.get(BlockType.COBBLESTONE_MOSSY);

    Coord cursor;
    Coord start;
    Coord end;

    Cardinal[] orth = dir.orthogonal();

    cursor = new Coord(origin);
    cursor.translate(Cardinal.UP, 3);
    mossy.set(editor, rand, cursor, false, true);
    cursor.translate(Cardinal.UP);
    water.set(editor, rand, cursor, false, true);

    cursor = new Coord(origin);
    cursor.translate(dir, 2);
    air.set(editor, cursor);
    cursor.translate(Cardinal.UP, 1);
    air.set(editor, cursor);
    cursor.translate(Cardinal.UP, 1);
    stair.set(editor, cursor);

    cursor = new Coord(origin);
    cursor.translate(dir, 2);
    bars.set(editor, cursor);
    cursor.translate(Cardinal.UP);
    bars.set(editor, cursor);

    start = new Coord(origin);
    start.translate(Cardinal.DOWN);
    end = new Coord(start);
    start.translate(orth[0]);
    end.translate(orth[1]);
    RectSolid.fill(editor, rand, start, end, air);
    start.translate(Cardinal.DOWN);
    end.translate(Cardinal.DOWN);
    RectSolid.fill(editor, rand, start, end, water);

    for (Cardinal o : orth) {
      cursor = new Coord(origin);
      cursor.translate(o, 1);
      cursor.translate(dir, 2);
      theme.getSecondary().getPillar().set(editor, rand, cursor);
      cursor.translate(Cardinal.UP, 1);
      theme.getSecondary().getPillar().set(editor, rand, cursor);
      cursor.translate(Cardinal.UP, 1);
      theme.getPrimary().getWall().set(editor, rand, cursor);
      cursor.translate(dir.reverse(), 1);
      stair.set(editor, cursor);
    }
  }
}
