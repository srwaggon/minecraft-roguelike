package greymerk.roguelike.dungeon.rooms.prototype;

import java.util.Random;

import greymerk.roguelike.dungeon.base.DungeonBase;
import greymerk.roguelike.dungeon.base.IDungeonRoom;
import greymerk.roguelike.dungeon.rooms.RoomSetting;
import greymerk.roguelike.dungeon.settings.LevelSettings;
import greymerk.roguelike.theme.ITheme;
import greymerk.roguelike.worldgen.Cardinal;
import greymerk.roguelike.worldgen.Coord;
import greymerk.roguelike.worldgen.IStair;
import greymerk.roguelike.worldgen.IWorldEditor;
import greymerk.roguelike.worldgen.blocks.BlockType;
import greymerk.roguelike.worldgen.shapes.RectHollow;
import greymerk.roguelike.worldgen.shapes.RectSolid;

import static greymerk.roguelike.treasure.Treasure.REWARD;
import static greymerk.roguelike.treasure.Treasure.createChest;

public class DungeonReward extends DungeonBase {

  public DungeonReward(RoomSetting roomSetting) {
    super(roomSetting);
  }

  @Override
  public IDungeonRoom generate(IWorldEditor editor, Random rand, LevelSettings settings, Coord origin, Cardinal[] entrances) {

    int x = origin.getX();
    int y = origin.getY();
    int z = origin.getZ();
    ITheme theme = settings.getTheme();

    RectSolid.fill(editor, rand, new Coord(x - 7, y, z - 7), new Coord(x + 7, y + 5, z + 7), BlockType.get(BlockType.AIR));
    RectHollow.fill(editor, rand, new Coord(x - 8, y - 1, z - 8), new Coord(x + 8, y + 6, z + 8), theme.getPrimary().getWall(), false, true);
    RectSolid.fill(editor, rand, new Coord(x - 1, y + 4, z - 1), new Coord(x + 1, y + 5, z + 1), theme.getPrimary().getWall());

    Coord cursor;
    Coord start;
    Coord end;

    IStair stair = theme.getPrimary().getStair();

    for (Cardinal dir : Cardinal.directions) {
      for (Cardinal orth : dir.orthogonal()) {
        cursor = new Coord(x, y, z);
        cursor.add(dir, 7);
        cursor.add(orth, 2);
        start = new Coord(cursor);
        end = new Coord(start);
        end.add(Cardinal.UP, 5);
        RectSolid.fill(editor, rand, start, end, theme.getPrimary().getWall(), true, true);
        cursor.add(dir.reverse());
        stair.setOrientation(dir.reverse(), false).set(editor, cursor);
        cursor.add(Cardinal.UP, 2);
        stair.setOrientation(dir.reverse(), true).set(editor, cursor);
        cursor.add(Cardinal.UP);
        start = new Coord(cursor);
        end = new Coord(start);
        end.add(Cardinal.UP, 2);
        RectSolid.fill(editor, rand, start, end, theme.getPrimary().getWall(), true, true);
        cursor.add(dir.reverse());
        stair.setOrientation(dir.reverse(), true).set(editor, cursor);
        cursor.add(Cardinal.UP);
        start = new Coord(cursor);
        end = new Coord(start);
        end.add(Cardinal.UP);
        RectSolid.fill(editor, rand, start, end, theme.getPrimary().getWall(), true, true);
        cursor.add(Cardinal.UP);
        cursor.add(dir.reverse());
        stair.setOrientation(dir.reverse(), true).set(editor, cursor);

        start = new Coord(x, y, z);
        start.add(dir, 7);
        start.add(Cardinal.UP, 3);
        end = new Coord(start);
        end.add(Cardinal.UP, 2);
        end.add(orth);
        RectSolid.fill(editor, rand, start, end, theme.getPrimary().getWall(), true, true);
        start.add(dir.reverse());
        start.add(Cardinal.UP);
        end.add(dir.reverse());
        RectSolid.fill(editor, rand, start, end, theme.getPrimary().getWall(), true, true);
        start.add(dir.reverse());
        start.add(Cardinal.UP);
        end.add(dir.reverse());
        RectSolid.fill(editor, rand, start, end, theme.getPrimary().getWall(), true, true);

        cursor = new Coord(x, y, z);
        cursor.add(dir, 8);
        cursor.add(Cardinal.UP, 2);
        cursor.add(orth);
        stair.setOrientation(orth.reverse(), true).set(editor, rand, cursor, true, false);
        cursor.add(dir.reverse());
        stair.setOrientation(orth.reverse(), true).set(editor, cursor);
        cursor.add(dir.reverse());
        cursor.add(Cardinal.UP);
        stair.setOrientation(orth.reverse(), true).set(editor, cursor);
        cursor.add(dir.reverse());
        cursor.add(Cardinal.UP);
        stair.setOrientation(orth.reverse(), true).set(editor, cursor);
        cursor.add(dir.reverse());
        cursor.add(Cardinal.UP);
        stair.setOrientation(orth.reverse(), true).set(editor, cursor);
        cursor.add(dir.reverse(), 2);
        stair.setOrientation(dir, true).set(editor, cursor);

        start = new Coord(x, y, z);
        start.add(dir, 7);
        start.add(orth, 3);
        start.add(Cardinal.UP, 3);
        end = new Coord(start);
        end.add(Cardinal.UP, 2);
        end.add(orth, 2);
        theme.getPrimary().getPillar().fill(editor, rand, new RectSolid(start, end));

        start.add(dir.reverse());
        start.add(Cardinal.UP);
        end.add(dir.reverse());
        RectSolid.fill(editor, rand, start, end, theme.getPrimary().getPillar());

        cursor = new Coord(x, y, z);
        cursor.add(dir, 7);
        cursor.add(orth, 3);
        stair.setOrientation(orth, false).set(editor, cursor);
        cursor.add(orth, 2);
        stair.setOrientation(orth.reverse(), false).set(editor, cursor);
        cursor.add(Cardinal.UP, 2);
        stair.setOrientation(orth.reverse(), true).set(editor, cursor);
        cursor.add(orth.reverse(), 2);
        stair.setOrientation(orth, true).set(editor, cursor);
        cursor.add(dir.reverse());
        cursor.add(Cardinal.UP);
        stair.setOrientation(orth, true).set(editor, cursor);
        cursor.add(orth, 2);
        stair.setOrientation(orth.reverse(), true).set(editor, cursor);
        cursor.add(dir.reverse());
        cursor.add(Cardinal.UP);
        end = new Coord(cursor);
        end.add(orth.reverse(), 2);
        RectSolid.fill(editor, rand, cursor, end, stair.setOrientation(dir.reverse(), true), true, true);
        cursor.add(Cardinal.UP);
        end.add(Cardinal.UP);
        RectSolid.fill(editor, rand, cursor, end, theme.getPrimary().getWall(), true, true);
        end.add(dir.reverse());
        stair.setOrientation(orth, true).set(editor, cursor);

        cursor = new Coord(x, y, z);
        cursor.add(dir, 7);
        cursor.add(orth, 4);
        cursor.add(Cardinal.DOWN);
        BlockType.get(BlockType.GLOWSTONE).set(editor, cursor);

      }

      Cardinal o = dir.left();

      start = new Coord(x, y, z);
      start.add(dir, 6);
      start.add(o, 6);
      end = new Coord(start);
      end.add(dir);
      end.add(o);
      end.add(Cardinal.UP, 5);
      RectSolid.fill(editor, rand, start, end, theme.getPrimary().getPillar());

      cursor = new Coord(x, y, z);
      theme.getPrimary().getWall().set(editor, rand, cursor);
      cursor.add(dir);
      stair.setOrientation(dir, false).set(editor, cursor);
      cursor.add(o);
      stair.setOrientation(dir, false).set(editor, cursor);
      cursor.add(Cardinal.UP, 4);
      stair.setOrientation(dir, true).set(editor, cursor);
      cursor.add(o.reverse());
      stair.setOrientation(dir, true).set(editor, cursor);

    }

    cursor = new Coord(x, y, z);
    cursor.add(Cardinal.UP, 4);
    BlockType.get(BlockType.GLOWSTONE).set(editor, cursor);

    cursor = new Coord(x, y, z);
    cursor.add(Cardinal.UP);
    createChest(editor, rand, settings.getDifficulty(cursor), cursor, false, REWARD);
    return this;
  }

  @Override
  public int getSize() {
    return 10;
  }
}