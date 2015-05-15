package greymerk.roguelike.worldgen;

import net.minecraft.util.BlockPos;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Coord extends BlockPos{

	private int x;
	private int y;
	private int z;
	
	public Coord(int x, int y, int z){
		super(x, y, z);
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Coord(Coord toClone){
		super(toClone.x, toClone.y, toClone.z);
		this.x = toClone.x;
		this.y = toClone.y;
		this.z = toClone.z;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getZ(){
		return z;
	}
	
	public void add(Cardinal dir, int amount){
		
		switch(dir){
		case EAST: x += amount; return;
		case WEST: x -= amount; return;
		case UP: y += amount; return;
		case DOWN: y -= amount; return;
		case NORTH: z -= amount; return;
		case SOUTH: z += amount; return;

		}		
	}
	
	public BlockPos add(int x, int y, int z){
		this.x += x;
		this.y += y;
		this.z += z;
		return new BlockPos(x, y, z);
	}
	
	public void add(Coord other){
		x += other.x;
		y += other.y;
		z += other.z;
	}
	
	public void add(Cardinal dir){
		add(dir, 1);
	}

	public double distance(Coord other){
		double side1 = Math.abs(this.getX() - other.getX());
		double side2 = Math.abs(this.getZ() - other.getZ());
		
		return Math.sqrt((side1 * side1) + (side2 * side2));
	}
	
	// Arranges two coords so that the they create a positive cube.
	// used in fill routines.
	public static void correct(Coord one, Coord two){
		
		int temp;
		
		if(two.x < one.x){
			temp = two.x;
			two.x = one.x;
			one.x = temp;
		}

		if(two.y < one.y){
			temp = two.y;
			two.y = one.y;
			one.y = temp;
		}
		
		if(two.z < one.z){
			temp = two.z;
			two.z = one.z;
			one.z = temp;
		}
	}
	
	@Override
	public int hashCode(){
		 return new HashCodeBuilder(17, 31)
		 .append(x)
		 .append(y)
		 .append(z)
		 .toHashCode();
	}
	
	@Override
	public String toString(){
		String toReturn = "";
		toReturn += "x: " + x + " ";
		toReturn += "y: " + y + " ";
		toReturn += "z: " + z;
		return toReturn;
	}
}
