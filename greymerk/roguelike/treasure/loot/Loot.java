package greymerk.roguelike.treasure.loot;

import greymerk.roguelike.config.ConfigFile;
import greymerk.roguelike.config.RogueConfig;
import greymerk.roguelike.treasure.loot.provider.ItemArmour;
import greymerk.roguelike.treasure.loot.provider.ItemBlock;
import greymerk.roguelike.treasure.loot.provider.ItemEnchBonus;
import greymerk.roguelike.treasure.loot.provider.ItemEnchBook;
import greymerk.roguelike.treasure.loot.provider.ItemFood;
import greymerk.roguelike.treasure.loot.provider.ItemJunk;
import greymerk.roguelike.treasure.loot.provider.ItemNovelty;
import greymerk.roguelike.treasure.loot.provider.ItemOre;
import greymerk.roguelike.treasure.loot.provider.ItemPotion;
import greymerk.roguelike.treasure.loot.provider.ItemRecord;
import greymerk.roguelike.treasure.loot.provider.ItemSmithy;
import greymerk.roguelike.treasure.loot.provider.ItemSupply;
import greymerk.roguelike.treasure.loot.provider.ItemTool;
import greymerk.roguelike.treasure.loot.provider.ItemWeapon;
import greymerk.roguelike.util.TextFormat;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.EnchantmentData;
import net.minecraft.src.EnchantmentHelper;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntitySkeleton;
import net.minecraft.src.EntityZombie;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.NBTTagString;
import net.minecraft.src.World;

public enum Loot {

	WEAPON, ARMOUR, BLOCK, JUNK, ORE, TOOL, POTION, FOOD, ENCHANTBOOK, ENCHANTBONUS, SUPPLY, MUSIC, SMITHY;
	
	private static Map<String, ILootProvider> loot = new HashMap<String, ILootProvider>();
	static{
		init();
	}
	
	
	public static void init(){
	
		for(Loot type : Loot.values()){
			LootProvider loots;
			
			loot.put(type.name(), loots = new LootProvider(0));
			
			switch(type){
			case WEAPON: loots.addAllLevels(new ItemWeapon(1000)); continue;
			case ARMOUR: loots.addAllLevels(new ItemArmour(1000)); continue;
			case BLOCK: loots.addAllLevels(new ItemBlock(1000)); continue;
			case JUNK: loots.addAllLevels(new ItemJunk(1000)); continue;
			case ORE: loots.addAllLevels(new ItemOre(1000)); continue;
			case TOOL: loots.addAllLevels(new ItemTool(1000)); continue;
			case POTION: loots.addAllLevels(new ItemPotion(1000)); continue;
			case FOOD: loots.addAllLevels(new ItemFood(1000)); continue;
			case ENCHANTBOOK: loots.addAllLevels(new ItemEnchBook(1000)); continue;
			case ENCHANTBONUS: loots.addAllLevels(new ItemEnchBonus(1000)); continue;
			case SUPPLY: loots.addAllLevels(new ItemSupply(1000)); continue;
			case MUSIC: loots.addAllLevels(new ItemRecord(1000)); continue;
			case SMITHY: loots.addAllLevels(new ItemSmithy(1000)); continue;
			}
		}

		CustomLoot.parseLoot();
		
	}
	
	public static void clear(Loot type){
		LootProvider lootp = (LootProvider)loot.get(type.name());
		lootp.clear();
	}
	
	public static void addAllLevels(Loot type, ILootProvider toAdd){
		LootProvider lootp = (LootProvider)loot.get(type.name());
		lootp.addAllLevels(toAdd);
	}
	
	public static void add(Loot type, ILootProvider toAdd, int level){
		LootProvider lootp = (LootProvider)loot.get(type.name());
		lootp.add(level, toAdd);
	}
	
	public static ItemStack getLoot(Loot type, Random rand, int level){
		return loot.get(type.name()).getLootItem(rand, level);
	}
	
	public static ItemStack getEquipmentBySlot(Random rand, Slot slot, int level, boolean enchant){
		
		ItemStack item;
		
		if(slot == Slot.WEAPON){
			return ItemWeapon.getRandom(rand, level, enchant);
		}
		
		return ItemArmour.getRandom(rand, level, slot, enchant);
		
	}
		
	public static ItemStack getSupplyItem(Random rand, int level){

		if(rand.nextInt(500) == 0){
			ItemNovelty[] items = {
					ItemNovelty.GUUDE,
					ItemNovelty.JOHNNYRAGGOT,
					ItemNovelty.FOURLES
			};
			
			return ItemNovelty.getItem(items[rand.nextInt(items.length)]);
		}
		
		if(rand.nextInt(10) == 0){
			return ItemBlock.getRandom(rand, level);
		}
		
		return pickSupplyItem(rand);
	}
	
	private static ItemStack pickSupplyItem(Random rand) {

		if(rand.nextInt(20) == 0) return new ItemStack(Item.carrot, 1);
		if(rand.nextInt(20) == 0) return new ItemStack(Item.potato, 1);
		
		switch(rand.nextInt(8)){
		case 0: return new ItemStack(Item.seeds, rand.nextInt(8) + 1);
		case 1: return new ItemStack(Item.pumpkinSeeds, rand.nextInt(8) + 1);
		case 2: return new ItemStack(Item.melonSeeds, rand.nextInt(8) + 1);		
		case 3: return new ItemStack(Item.wheat, rand.nextInt(8) + 1);
		case 4: return new ItemStack(Block.torchWood, 10 + rand.nextInt(10));
		case 5: return new ItemStack(Item.paper, rand.nextInt(8) + 1);
		case 6:	return new ItemStack(Item.book, rand.nextInt(4) + 1);
		case 7:	return new ItemStack(Block.sapling, rand.nextInt(4) + 1, rand.nextInt(4));
		default: return new ItemStack(Item.stick, 1);
		}
	}

	public static int getEnchantLevel(Random rand, int level) {

		switch(level){
		case 4: return 21 + rand.nextInt(10);
		case 3: return 16 + rand.nextInt(10);
		case 2: return 5 + rand.nextInt(10);
		case 1: return 1 + rand.nextInt(5);
		case 0: return 1;
		default: return 1;
		}
	}

	public static void enchantItemChance(ItemStack item, Random rand, int level){
		if(rand.nextInt(7 - level) == 0) enchantItem(item, rand, getEnchantLevel(rand, level));
	}
	
	public static void enchantItem(ItemStack item, Random rand, int enchantLevel) {
		
        List enchants = EnchantmentHelper.buildEnchantmentList(rand, item, enchantLevel);
        boolean canEnchant = enchants != null;
        boolean isABook = item.itemID == Item.book.itemID;

        if (!canEnchant){
        	return;
        }
        
        if (isABook){
            item.itemID = Item.enchantedBook.itemID;
        }

        int var6 = isABook ? rand.nextInt(enchants.size()) : -1;

        for (int i = 0; i < enchants.size(); ++i)
        {
            EnchantmentData enchantData = (EnchantmentData)enchants.get(i);

            if (!isABook || i == var6)
            {
                if (isABook)
                {
                    Item.enchantedBook.addEnchantment(item, enchantData);
                }
                else
                {
                    item.addEnchantment(enchantData.enchantmentobj, enchantData.enchantmentLevel);
                }
            }
        }
	}
	

	
    public static void setItemLore(ItemStack item, String loreText){
    	
        if (item.stackTagCompound == null)
        {
            item.stackTagCompound = new NBTTagCompound("tag");
        }

        if (!item.stackTagCompound.hasKey("display"))
        {
            item.stackTagCompound.setCompoundTag("display", new NBTTagCompound());
        }
        
        NBTTagCompound display = item.stackTagCompound.getCompoundTag("display");
        
        if (!(display.hasKey("Lore")))
        {
        	display.setTag("Lore", new NBTTagList());
        }
        
        NBTTagList lore = display.getTagList("Lore");
        
        NBTTagString toAdd = new NBTTagString("", loreText);
        
        lore.appendTag(toAdd);
        
        display.setTag("Lore", lore);   
    }
    
    public static void setItemLore(ItemStack item, String loreText, TextFormat option){
    	setItemLore(item, TextFormat.apply(loreText, option));
    }
    
    public static void setItemName(ItemStack item, String name, TextFormat option){
    	
    	if(option == null){
    		item.setItemName(name);
    		return;
    	}
    	
    	item.setItemName(TextFormat.apply(name, option));
    }
    
    public static void addEquipment(World world, int rank, Entity mob){
			
		Random rand = world.rand;
				
		int difficulty = world.difficultySetting;
		
		boolean enchant = difficulty == 3 ? true : false;
		
		ItemStack weapon;
		
		// zombie gets a sword
		if(mob instanceof EntityZombie){
			
			if(((EntityZombie)mob).isChild() && enchant && rand.nextInt(100) == 0){
				weapon = ItemNovelty.getItem(ItemNovelty.ASHLEA);
			} else if(rand.nextInt(5) == 0){
				weapon = ItemWeapon.getSword(rand, rank, enchant);
			} else {
				weapon = ItemTool.getRandom(rand, rank, enchant);
			}
			
			mob.setCurrentItemOrArmor(0, weapon);
		}
		
		// skelly gets a bow
		if(mob instanceof EntitySkeleton){
			
			if(rand.nextInt(10) == 0 && rank > 1){
				((EntitySkeleton) mob).setSkeletonType(1);
				mob.setCurrentItemOrArmor(0, ItemWeapon.getSword(rand, rank, enchant));
			} else {
				if(rand.nextInt(20) == 0){
					mob.setCurrentItemOrArmor(0, ItemWeapon.getSword(rand, rank, enchant));
				} else {
					mob.setCurrentItemOrArmor(0, ItemWeapon.getBow(rand, rank, enchant));
				}
			}
		}
		
		// put on some armour
		for(int i = 1; i < 5; i++){
			
			int chance = 5 - rank + ((3 - difficulty) * 2);
			if (difficulty == 3 || rank == 3 || rand.nextInt(chance) == 0){
				ItemStack item = Loot.getEquipmentBySlot(rand, Slot.getSlotByNumber(i), rank, enchant);
				mob.setCurrentItemOrArmor(i, item);
			}
		}
		
		// lower drop chance

		for(int s = 0; s < 5; s++){
			((EntityLiving)mob).setEquipmentDropChance(s, (float) RogueConfig.getDouble(RogueConfig.LOOTING));
		}
    }
}