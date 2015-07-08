package greymerk.roguelike.dungeon;

import greymerk.roguelike.dungeon.settings.LevelSettings;
import greymerk.roguelike.worldgen.Coord;

import java.util.List;
import java.util.Random;

import net.minecraft.world.World;

public class DungeonLevel implements IDungeonLevel{

	private Coord origin;
	private LevelSettings settings;
	private ILevelGenerator generator;
	
	public DungeonLevel(World world, Random rand, LevelSettings settings, Coord origin){
		this.settings = settings;
		this.origin = origin;
	}
	
	public void generate(ILevelGenerator generator){
		this.generator = generator;
		generator.generate();
	}
	
	public int nodeCount(){
		return this.getNodes().size();
	}

	@Override
	public LevelSettings getSettings(){
		return this.settings;
	}

	@Override
	public List<DungeonNode> getNodes() {
		return this.generator.getNodes();
	}

	@Override
	public List<DungeonTunnel> getTunnels() {
		return this.generator.getTunnels();
	}
	
	@Override
	public boolean hasNearbyNode(Coord pos){
		
		for (DungeonNode node : this.getNodes()){
			int dist = (int) node.getPosition().distance(pos);

			if(dist < node.getSize()){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean inRange(Coord pos) {		
		int dist = (int) this.origin.distance(pos);
		return dist < this.settings.getRange();
	}
}
