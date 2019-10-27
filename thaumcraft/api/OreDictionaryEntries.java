/*    */ package thaumcraft.api;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.oredict.OreDictionary;
/*    */ import thaumcraft.api.blocks.BlocksTC;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OreDictionaryEntries
/*    */ {
/*    */   public static void initializeOreDictionary() {
/* 16 */     OreDictionary.registerOre("oreAmber", new ItemStack(BlocksTC.oreAmber));
/* 17 */     OreDictionary.registerOre("oreCinnabar", new ItemStack(BlocksTC.oreCinnabar));
/* 18 */     OreDictionary.registerOre("oreQuartz", new ItemStack(BlocksTC.oreQuartz));
/*    */     
/* 20 */     OreDictionary.registerOre("oreCrystalAir", new ItemStack(BlocksTC.crystalAir, 1, 32767));
/* 21 */     OreDictionary.registerOre("oreCrystalEarth", new ItemStack(BlocksTC.crystalEarth, 1, 32767));
/* 22 */     OreDictionary.registerOre("oreCrystalWater", new ItemStack(BlocksTC.crystalWater, 1, 32767));
/* 23 */     OreDictionary.registerOre("oreCrystalFire", new ItemStack(BlocksTC.crystalFire, 1, 32767));
/* 24 */     OreDictionary.registerOre("oreCrystalOrder", new ItemStack(BlocksTC.crystalOrder, 1, 32767));
/* 25 */     OreDictionary.registerOre("oreCrystalEntropy", new ItemStack(BlocksTC.crystalEntropy, 1, 32767));
/* 26 */     OreDictionary.registerOre("oreCrystalTaint", new ItemStack(BlocksTC.crystalTaint, 1, 32767));
/*    */     
/* 28 */     OreDictionary.registerOre("logWood", new ItemStack(BlocksTC.logGreatwood));
/* 29 */     OreDictionary.registerOre("logWood", new ItemStack(BlocksTC.logSilverwood));
/* 30 */     OreDictionary.registerOre("plankWood", new ItemStack(BlocksTC.plankGreatwood));
/* 31 */     OreDictionary.registerOre("plankWood", new ItemStack(BlocksTC.plankSilverwood));
/* 32 */     OreDictionary.registerOre("slabWood", new ItemStack(BlocksTC.slabGreatwood));
/* 33 */     OreDictionary.registerOre("slabWood", new ItemStack(BlocksTC.slabSilverwood));
/* 34 */     OreDictionary.registerOre("treeSapling", new ItemStack(BlocksTC.saplingGreatwood));
/* 35 */     OreDictionary.registerOre("treeSapling", new ItemStack(BlocksTC.saplingSilverwood));
/* 36 */     OreDictionary.registerOre("treeLeaves", new ItemStack(BlocksTC.leafGreatwood, 1, 32767));
/* 37 */     OreDictionary.registerOre("treeLeaves", new ItemStack(BlocksTC.leafSilverwood, 1, 32767));
/*    */     
/* 39 */     for (Block b : BlocksTC.nitor.values()) {
/* 40 */       OreDictionary.registerOre("nitor", new ItemStack(b));
/*    */     }
/* 42 */     OreDictionary.registerOre("gemAmber", new ItemStack(ItemsTC.amber));
/* 43 */     OreDictionary.registerOre("quicksilver", new ItemStack(ItemsTC.quicksilver));
/*    */     
/* 45 */     OreDictionary.registerOre("nuggetIron", new ItemStack(ItemsTC.nuggets, 1, 0));
/* 46 */     OreDictionary.registerOre("nuggetCopper", new ItemStack(ItemsTC.nuggets, 1, 1));
/* 47 */     OreDictionary.registerOre("nuggetTin", new ItemStack(ItemsTC.nuggets, 1, 2));
/* 48 */     OreDictionary.registerOre("nuggetSilver", new ItemStack(ItemsTC.nuggets, 1, 3));
/* 49 */     OreDictionary.registerOre("nuggetLead", new ItemStack(ItemsTC.nuggets, 1, 4));
/* 50 */     OreDictionary.registerOre("nuggetQuicksilver", new ItemStack(ItemsTC.nuggets, 1, 5));
/* 51 */     OreDictionary.registerOre("nuggetThaumium", new ItemStack(ItemsTC.nuggets, 1, 6));
/* 52 */     OreDictionary.registerOre("nuggetVoid", new ItemStack(ItemsTC.nuggets, 1, 7));
/* 53 */     OreDictionary.registerOre("nuggetBrass", new ItemStack(ItemsTC.nuggets, 1, 8));
/* 54 */     OreDictionary.registerOre("nuggetQuartz", new ItemStack(ItemsTC.nuggets, 1, 9));
/*    */     
/* 56 */     OreDictionary.registerOre("nuggetMeat", new ItemStack(ItemsTC.chunks, 1, 0));
/* 57 */     OreDictionary.registerOre("nuggetMeat", new ItemStack(ItemsTC.chunks, 1, 1));
/* 58 */     OreDictionary.registerOre("nuggetMeat", new ItemStack(ItemsTC.chunks, 1, 2));
/* 59 */     OreDictionary.registerOre("nuggetMeat", new ItemStack(ItemsTC.chunks, 1, 3));
/* 60 */     OreDictionary.registerOre("nuggetMeat", new ItemStack(ItemsTC.chunks, 1, 4));
/* 61 */     OreDictionary.registerOre("nuggetMeat", new ItemStack(ItemsTC.chunks, 1, 5));
/*    */     
/* 63 */     OreDictionary.registerOre("ingotThaumium", new ItemStack(ItemsTC.ingots, 1, 0));
/* 64 */     OreDictionary.registerOre("ingotVoid", new ItemStack(ItemsTC.ingots, 1, 1));
/* 65 */     OreDictionary.registerOre("ingotBrass", new ItemStack(ItemsTC.ingots, 1, 2));
/*    */     
/* 67 */     OreDictionary.registerOre("blockThaumium", new ItemStack(BlocksTC.metalBlockThaumium));
/* 68 */     OreDictionary.registerOre("blockVoid", new ItemStack(BlocksTC.metalBlockVoid));
/* 69 */     OreDictionary.registerOre("blockBrass", new ItemStack(BlocksTC.metalBlockBrass));
/*    */     
/* 71 */     OreDictionary.registerOre("plateIron", new ItemStack(ItemsTC.plate, 1, 1));
/* 72 */     OreDictionary.registerOre("plateBrass", new ItemStack(ItemsTC.plate, 1, 0));
/* 73 */     OreDictionary.registerOre("plateThaumium", new ItemStack(ItemsTC.plate, 1, 2));
/* 74 */     OreDictionary.registerOre("plateVoid", new ItemStack(ItemsTC.plate, 1, 3));
/*    */     
/* 76 */     OreDictionary.registerOre("clusterIron", new ItemStack(ItemsTC.clusters, 1, 0));
/* 77 */     OreDictionary.registerOre("clusterGold", new ItemStack(ItemsTC.clusters, 1, 1));
/* 78 */     OreDictionary.registerOre("clusterCopper", new ItemStack(ItemsTC.clusters, 1, 2));
/* 79 */     OreDictionary.registerOre("clusterTin", new ItemStack(ItemsTC.clusters, 1, 3));
/* 80 */     OreDictionary.registerOre("clusterSilver", new ItemStack(ItemsTC.clusters, 1, 4));
/* 81 */     OreDictionary.registerOre("clusterLead", new ItemStack(ItemsTC.clusters, 1, 5));
/* 82 */     OreDictionary.registerOre("clusterCinnabar", new ItemStack(ItemsTC.clusters, 1, 6));
/* 83 */     OreDictionary.registerOre("clusterQuartz", new ItemStack(ItemsTC.clusters, 1, 7));
/*    */ 
/*    */     
/* 86 */     OreDictionary.registerOre("trapdoorWood", new ItemStack(Blocks.field_150415_aT));
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\OreDictionaryEntries.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */