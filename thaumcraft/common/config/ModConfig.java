/*     */ package thaumcraft.common.config;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.FurnaceRecipes;
/*     */ import net.minecraft.potion.PotionType;
/*     */ import net.minecraft.potion.PotionUtils;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.common.IPlantable;
/*     */ import net.minecraftforge.common.config.Config;
/*     */ import net.minecraftforge.common.config.Config.Comment;
/*     */ import net.minecraftforge.common.config.Config.LangKey;
/*     */ import net.minecraftforge.common.config.Config.RangeInt;
/*     */ import net.minecraftforge.common.config.Config.RequiresMcRestart;
/*     */ import net.minecraftforge.fml.common.registry.ForgeRegistries;
/*     */ import net.minecraftforge.fml.common.registry.GameRegistry;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import thaumcraft.Thaumcraft;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.lib.utils.CropUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModConfig
/*     */ {
/*     */   public static final float auraSize = 4.0F;
/*     */   
/*     */   @LangKey("thaumcraft.config.graphics")
/*     */   @Config(modid = "thaumcraft", type = Config.Type.INSTANCE, name = "thaumcraft_graphics")
/*     */   public static class CONFIG_GRAPHICS
/*     */   {
/*     */     @Comment({"Setting this to true will make the amount text in aspect tags twice as large. Useful for certain resolutions and custom fonts."})
/*     */     public static boolean largeTagText = false;
/*     */     @Comment({"This setting will disable certain thaumcraft shaders for those who experience FPS drops."})
/*     */     public static boolean disableShaders = false;
/*     */     @Comment({"Set to true to disable anxiety triggers like the heartbeat sound."})
/*     */     public static boolean nostress = false;
/*     */     @Comment({"Hate crooked labels, kittens, puppies and all things awesome? If yes, set this to false."})
/*     */     public static boolean crooked = true;
/*     */     @Comment({"Set to true to have the wand dial display in the bottom left instead of the top left."})
/*     */     public static boolean dialBottom = false;
/*     */     @Comment({"Item aspects are hidden by default and pressing shift reveals them.", "Changing this setting to 'true' will reverse this behaviour and always", "display aspects unless shift is pressed."})
/*     */     public static boolean showTags = false;
/*     */     @Comment({"Set this to true to get the old blue magical forest back."})
/*     */     public static boolean blueBiome = false;
/*     */     @Comment({"Will golems display emote particles if they recieve orders or encounter problems"})
/*     */     public static boolean showGolemEmotes = true;
/*     */   }
/*     */   
/*     */   @LangKey("thaumcraft.config.world")
/*     */   @Config(modid = "thaumcraft", type = Config.Type.INSTANCE, name = "thaumcraft_world")
/*     */   public static class CONFIG_WORLD
/*     */   {
/*     */     @Comment({"The dimension considered to be your 'overworld'. Certain TC structures will only spawn in this dim."})
/*     */     @RequiresMcRestart
/*  95 */     public static int overworldDim = 0;
/*     */     
/*     */     @Comment({"Outer lands dimension id"})
/*     */     @RequiresMcRestart
/*  99 */     public static int dimensionOuterId = -42;
/*     */ 
/*     */     
/*     */     @Comment({"The % of normal ore amounts that will be spawned. For example 50 will spawn half", "the ores while 200 will spawn double. Default 100"})
/*     */     @RangeInt(min = 1, max = 500)
/*     */     @RequiresMcRestart
/* 105 */     public static int oreDensity = 100;
/*     */ 
/*     */     
/*     */     public static boolean generateMagicForest = true;
/*     */     
/*     */     @Comment({"Higher values increases number of magical forest biomes. If you are using biome", "addon mods you probably want to increase this weight quite a bit"})
/*     */     @RangeInt(min = 0, max = 100)
/*     */     @RequiresMcRestart
/* 113 */     public static int biomeMagicalForestWeight = 5;
/*     */ 
/*     */     
/*     */     @Comment({"The % chance of taint fibres spreading on a block tick.", "Setting this to 0 will effectively stop taint fibre spread."})
/*     */     @RangeInt(min = 0, max = 500)
/* 118 */     public static float taintSpreadRate = 100.0F;
/*     */ 
/*     */     
/*     */     @Comment({"The range at which taint can spread from a taint seed.", "This value is only a base and will be modified by flux levels."})
/*     */     @RangeInt(min = 8, max = 256)
/* 123 */     public static int taintSpreadArea = 32;
/*     */     
/*     */     public static boolean generateAura = true;
/*     */     
/*     */     public static boolean generateStructure = true;
/*     */     
/*     */     public static boolean generateCinnabar = true;
/*     */     
/*     */     public static boolean generateAmber = true;
/*     */     
/*     */     public static boolean generateQuartz = true;
/*     */     
/*     */     public static boolean generateCrystals = true;
/*     */     
/*     */     public static boolean generateTrees = true;
/*     */     @Comment({"This key is used to keep track of which chunk have been generated/regenerated.", "Changing it will cause the regeneration code to run again, so only change it if you want it to happen.", "Useful to regen only one world feature at a time."})
/* 139 */     public static String regenKey = "DEFAULT";
/*     */     
/*     */     public static boolean regenAura = false;
/*     */     
/*     */     public static boolean regenStructure = false;
/*     */     
/*     */     public static boolean regenCinnabar = false;
/*     */     
/*     */     public static boolean regenAmber = false;
/*     */     
/*     */     public static boolean regenQuartz = false;
/*     */     
/*     */     public static boolean regenCrystals = false;
/*     */     
/*     */     public static boolean regenTrees = false;
/*     */     
/*     */     public static boolean allowSpawnAngryZombie = true;
/*     */     
/*     */     public static boolean allowSpawnFireBat = true;
/*     */     public static boolean allowSpawnWisp = true;
/*     */     public static boolean allowSpawnTaintacle = true;
/*     */     public static boolean allowSpawnPech = true;
/*     */     public static boolean allowSpawnElder = true;
/*     */     @Comment({"Setting this to false will disable spawning champion mobs. Even when false they will still", "have a greatly reduced chance of spawning in certain dangerous places."})
/*     */     public static boolean allowChampionMobs = true;
/*     */   }
/*     */   
/*     */   @LangKey("thaumcraft.config.misc")
/*     */   @Config(modid = "thaumcraft", type = Config.Type.INSTANCE, name = "thaumcraft_misc")
/*     */   public static class CONFIG_MISC
/*     */   {
/*     */     @Comment({"Setting this to true will make you get the recipe book for salis mundus without having to sleep first."})
/*     */     public static boolean noSleep = false;
/*     */     @Comment({"Setting this to true disables Warp, Taint spread and similar mechanics. You wuss."})
/*     */     public static boolean wussMode = false;
/*     */     @Comment({"Enables a version of the Thauminomicon in creative mode that grants you all the research when you first use it."})
/*     */     public static boolean allowCheatSheet = false;
/*     */     @Comment({"How many milliseconds passes between runic shielding recharge ticks.", "Lower values equals faster recharge. Minimum of 500."})
/*     */     @RangeInt(min = 500, max = 500000)
/* 178 */     public static int shieldRecharge = 2000;
/*     */ 
/*     */     
/*     */     @Comment({"How many milliseconds passes after a shield has been reduced to zero", "before it can start recharging again. Minimum of 0."})
/*     */     @RangeInt(min = 0, max = 50000)
/* 183 */     public static int shieldWait = 4000;
/*     */     
/*     */     @Comment({"How much vis it costs to reacharge a single unit of shielding. Minimum of 0."})
/*     */     @RangeInt(min = 0, max = 500)
/* 187 */     public static int shieldCost = 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void postInitLoot() {
/* 208 */     COMMON = 0;
/* 209 */     int UNCOMMON = 1;
/* 210 */     int RARE = 2;
/*     */     
/* 212 */     Random rand = new Random(System.currentTimeMillis());
/*     */     
/* 214 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151074_bl, 1), 2500, new int[] { 0 });
/* 215 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151074_bl, 2), 2250, new int[] { 1 });
/* 216 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151074_bl, 3), 2000, new int[] { 2 });
/* 217 */     ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.salisMundus), 3, new int[] { 0 });
/* 218 */     ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.salisMundus), 6, new int[] { 1 });
/* 219 */     ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.salisMundus), 9, new int[] { 2 });
/*     */     
/* 221 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_185161_cS), 5, new int[] { 0, 1, 2 });
/* 222 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151111_aL), 5, new int[] { 0, 1, 2 });
/* 223 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151106_aX), 5, new int[] { 0, 1, 2 });
/*     */     
/* 225 */     ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.primordialPearl, 1, 7), 1, new int[] { 0 });
/* 226 */     ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.primordialPearl, 1, 7), 3, new int[] { 1 });
/* 227 */     ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.primordialPearl, 1, 6), 1, new int[] { 1 });
/* 228 */     ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.primordialPearl, 1, 5), 9, new int[] { 2 });
/* 229 */     ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.primordialPearl, 1, 3), 3, new int[] { 2 });
/* 230 */     ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.primordialPearl, 1), 1, new int[] { 2 });
/*     */     
/* 232 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151156_bN), 1, new int[] { 2 });
/* 233 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151045_i), 10, new int[] { 0 });
/* 234 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151045_i), 50, new int[] { 1, 2 });
/* 235 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151166_bC), 15, new int[] { 0 });
/* 236 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151166_bC), 75, new int[] { 1, 2 });
/* 237 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151043_k), 100, new int[] { 0, 1, 2 });
/* 238 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151079_bi), 100, new int[] { 0, 1, 2 });
/* 239 */     ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.amuletVis, 1, 0), 6, new int[] { 1, 2 });
/* 240 */     ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.baubles, 1, 0), 10, new int[] { 0 });
/* 241 */     ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.baubles, 1, 1), 10, new int[] { 0 });
/* 242 */     ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.baubles, 1, 2), 10, new int[] { 0 });
/* 243 */     ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.baubles, 1, 3), 5, new int[] { 2 });
/* 244 */     ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.baubles, 1, 4), 5, new int[] { 1 });
/* 245 */     ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.baubles, 1, 5), 5, new int[] { 1 });
/* 246 */     ThaumcraftApi.addLootBagItem(new ItemStack(ItemsTC.baubles, 1, 6), 5, new int[] { 1 });
/* 247 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151062_by), 5, new int[] { 0 });
/* 248 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151062_by), 10, new int[] { 1 });
/* 249 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151062_by), 20, new int[] { 2 });
/* 250 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151153_ao, 1, 1), 1, new int[] { 0 });
/* 251 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151153_ao, 1, 1), 2, new int[] { 1 });
/* 252 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151153_ao, 1, 1), 3, new int[] { 2 });
/* 253 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151153_ao, 1, 0), 3, new int[] { 0 });
/* 254 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151153_ao, 1, 0), 6, new int[] { 1 });
/* 255 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151153_ao, 1, 0), 9, new int[] { 2 });
/* 256 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151122_aG), 10, new int[] { 0, 1, 2 });
/*     */     
/* 258 */     for (PotionType pt : PotionType.field_185176_a) {
/*     */       
/* 260 */       ThaumcraftApi.addLootBagItem(PotionUtils.func_185188_a(new ItemStack(Items.field_151068_bn), pt), 2, new int[] { 0, 1, 2 });
/* 261 */       ThaumcraftApi.addLootBagItem(PotionUtils.func_185188_a(new ItemStack(Items.field_185155_bH), pt), 2, new int[] { 0, 1, 2 });
/* 262 */       ThaumcraftApi.addLootBagItem(PotionUtils.func_185188_a(new ItemStack(Items.field_185156_bI), pt), 2, new int[] { 1, 2 });
/*     */     } 
/*     */     
/* 265 */     ItemStack[] commonLoot = { new ItemStack(ItemsTC.lootBag, 1, 0), new ItemStack(ItemsTC.ingots), new ItemStack(ItemsTC.amber) };
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 270 */     ItemStack[] uncommonLoot = { new ItemStack(ItemsTC.lootBag, 1, 1), new ItemStack(ItemsTC.baubles, 1, 0), new ItemStack(ItemsTC.baubles, 1, 1), new ItemStack(ItemsTC.baubles, 1, 2) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 276 */     ItemStack[] rareLoot = { new ItemStack(ItemsTC.lootBag, 1, 2), new ItemStack(ItemsTC.thaumonomicon), new ItemStack(ItemsTC.thaumiumSword), new ItemStack(ItemsTC.thaumiumAxe), new ItemStack(ItemsTC.thaumiumHoe), new ItemStack(ItemsTC.thaumiumPick), new ItemStack(ItemsTC.baubles, 1, 3), new ItemStack(ItemsTC.baubles, 1, 4), new ItemStack(ItemsTC.baubles, 1, 5), new ItemStack(ItemsTC.baubles, 1, 6), new ItemStack(ItemsTC.amuletVis, 1, 0) };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void modCompatibility() {
/* 327 */     Thaumcraft.log.info("Checking for mod & oredict compatibilities");
/*     */     
/* 329 */     defaultGroup = new ResourceLocation("");
/*     */ 
/*     */     
/*     */     try {
/* 333 */       if (OreDictionary.doesOreNameExist("oreIron") && OreDictionary.getOres("oreIron", false).size() > 1) {
/* 334 */         for (ItemStack is : OreDictionary.getOres("oreIron", false)) {
/* 335 */           if (is.func_77973_b() != Item.func_150898_a(Blocks.field_150366_p)) {
/* 336 */             Utils.addSpecialMiningResult(is, new ItemStack(ItemsTC.clusters, 1, 0), 1.0F);
/*     */           }
/*     */         } 
/*     */       }
/* 340 */       if (OreDictionary.doesOreNameExist("oreGold") && OreDictionary.getOres("oreGold", false).size() > 1)
/* 341 */         for (ItemStack is : OreDictionary.getOres("oreGold", false)) {
/* 342 */           if (is.func_77973_b() != Item.func_150898_a(Blocks.field_150352_o))
/* 343 */             Utils.addSpecialMiningResult(is, new ItemStack(ItemsTC.clusters, 1, 1), 1.0F); 
/*     */         }  
/* 345 */     } catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */     
/* 349 */     if (OreDictionary.doesOreNameExist("oreCopper"))
/* 350 */       for (ItemStack is : OreDictionary.getOres("oreCopper", false)) {
/* 351 */         Utils.addSpecialMiningResult(is, new ItemStack(ItemsTC.clusters, 1, 2), 1.0F);
/* 352 */         foundCopperOre = true;
/*     */       }  
/* 354 */     if (OreDictionary.doesOreNameExist("ingotCopper")) {
/* 355 */       boolean first = true;
/* 356 */       for (ItemStack is : OreDictionary.getOres("ingotCopper", false)) {
/* 357 */         if (is.func_190916_E() > 1) is.func_190920_e(1); 
/* 358 */         foundCopperIngot = true;
/* 359 */         GameRegistry.addShapedRecipe(new ResourceLocation("thaumcraft", "coppertonuggets"), defaultGroup, new ItemStack(ItemsTC.nuggets, 9, 1), new Object[] { "#", Character.valueOf('#'), is });
/*     */         
/* 361 */         if (first) {
/* 362 */           first = false;
/* 363 */           FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(ItemsTC.clusters, 1, 2), new ItemStack(is
/* 364 */                 .func_77973_b(), 2, is.func_77952_i()), 1.0F);
/* 365 */           ConfigRecipes.oreDictRecipe("coppernuggetstoingot", defaultGroup, is, new Object[] { "###", "###", "###", 
/* 366 */                 Character.valueOf('#'), new ItemStack(ItemsTC.nuggets, 1, 1) });
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 372 */     if (OreDictionary.doesOreNameExist("oreTin"))
/* 373 */       for (ItemStack is : OreDictionary.getOres("oreTin", false)) {
/* 374 */         Utils.addSpecialMiningResult(is, new ItemStack(ItemsTC.clusters, 1, 3), 1.0F);
/* 375 */         foundTinOre = true;
/*     */       }  
/* 377 */     if (OreDictionary.doesOreNameExist("ingotTin")) {
/* 378 */       boolean first = true;
/* 379 */       for (ItemStack is : OreDictionary.getOres("ingotTin", false)) {
/* 380 */         if (is.func_190916_E() > 1) is.func_190920_e(1); 
/* 381 */         foundTinIngot = true;
/* 382 */         GameRegistry.addShapedRecipe(new ResourceLocation("thaumcraft", "tintonuggets"), defaultGroup, new ItemStack(ItemsTC.nuggets, 9, 2), new Object[] { "#", Character.valueOf('#'), is });
/* 383 */         if (first) {
/* 384 */           first = false;
/* 385 */           FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(ItemsTC.clusters, 1, 3), new ItemStack(is
/* 386 */                 .func_77973_b(), 2, is.func_77952_i()), 1.0F);
/* 387 */           ConfigRecipes.oreDictRecipe("tinnuggetstoingot", defaultGroup, is, new Object[] { "###", "###", "###", 
/* 388 */                 Character.valueOf('#'), new ItemStack(ItemsTC.nuggets, 1, 2) });
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 394 */     if (OreDictionary.doesOreNameExist("oreSilver"))
/* 395 */       for (ItemStack is : OreDictionary.getOres("oreSilver", false)) {
/* 396 */         Utils.addSpecialMiningResult(is, new ItemStack(ItemsTC.clusters, 1, 4), 1.0F);
/* 397 */         foundSilverOre = true;
/*     */       }  
/* 399 */     if (OreDictionary.doesOreNameExist("ingotSilver")) {
/* 400 */       boolean first = true;
/* 401 */       for (ItemStack is : OreDictionary.getOres("ingotSilver", false)) {
/* 402 */         if (is.func_190916_E() > 1) is.func_190920_e(1); 
/* 403 */         foundSilverIngot = true;
/* 404 */         GameRegistry.addShapedRecipe(new ResourceLocation("thaumcraft", "silvertonuggets"), defaultGroup, new ItemStack(ItemsTC.nuggets, 9, 3), new Object[] { "#", Character.valueOf('#'), is });
/* 405 */         if (first) {
/* 406 */           first = false;
/* 407 */           FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(ItemsTC.clusters, 1, 4), new ItemStack(is
/* 408 */                 .func_77973_b(), 2, is.func_77952_i()), 1.0F);
/* 409 */           ConfigRecipes.oreDictRecipe("silvernuggetstoingot", defaultGroup, is, new Object[] { "###", "###", "###", 
/* 410 */                 Character.valueOf('#'), new ItemStack(ItemsTC.nuggets, 1, 3) });
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 416 */     if (OreDictionary.doesOreNameExist("oreLead"))
/* 417 */       for (ItemStack is : OreDictionary.getOres("oreLead", false)) {
/* 418 */         Utils.addSpecialMiningResult(is, new ItemStack(ItemsTC.clusters, 1, 5), 1.0F);
/* 419 */         foundLeadOre = true;
/*     */       }  
/* 421 */     if (OreDictionary.doesOreNameExist("ingotLead")) {
/* 422 */       boolean first = true;
/* 423 */       for (ItemStack is : OreDictionary.getOres("ingotLead", false)) {
/* 424 */         if (is.func_190916_E() > 1) is.func_190920_e(1); 
/* 425 */         foundLeadIngot = true;
/* 426 */         GameRegistry.addShapedRecipe(new ResourceLocation("thaumcraft", "leadtonuggets"), defaultGroup, new ItemStack(ItemsTC.nuggets, 9, 4), new Object[] { "#", Character.valueOf('#'), is });
/* 427 */         if (first) {
/* 428 */           first = false;
/* 429 */           FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(ItemsTC.clusters, 1, 5), new ItemStack(is
/* 430 */                 .func_77973_b(), 2, is.func_77952_i()), 1.0F);
/* 431 */           ConfigRecipes.oreDictRecipe("leadnuggetstoingot", defaultGroup, is, new Object[] { "###", "###", "###", 
/* 432 */                 Character.valueOf('#'), new ItemStack(ItemsTC.nuggets, 1, 4) });
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 437 */     Thaumcraft.log.info("Adding entities to MFR safari net blacklist.");
/* 438 */     registerSafariNetBlacklist(thaumcraft.common.entities.construct.EntityOwnedConstruct.class);
/* 439 */     registerSafariNetBlacklist(thaumcraft.common.entities.EntityFallingTaint.class);
/* 440 */     registerSafariNetBlacklist(thaumcraft.common.entities.monster.EntityWisp.class);
/* 441 */     registerSafariNetBlacklist(thaumcraft.common.entities.monster.EntityPech.class);
/* 442 */     registerSafariNetBlacklist(thaumcraft.common.entities.monster.EntityEldritchGuardian.class);
/* 443 */     registerSafariNetBlacklist(thaumcraft.common.entities.monster.boss.EntityEldritchWarden.class);
/* 444 */     registerSafariNetBlacklist(thaumcraft.common.entities.monster.boss.EntityEldritchGolem.class);
/* 445 */     registerSafariNetBlacklist(thaumcraft.common.entities.monster.cult.EntityCultistCleric.class);
/* 446 */     registerSafariNetBlacklist(thaumcraft.common.entities.monster.cult.EntityCultistKnight.class);
/* 447 */     registerSafariNetBlacklist(thaumcraft.common.entities.monster.boss.EntityCultistLeader.class);
/* 448 */     registerSafariNetBlacklist(thaumcraft.common.entities.monster.boss.EntityCultistPortalGreater.class);
/* 449 */     registerSafariNetBlacklist(thaumcraft.common.entities.monster.cult.EntityCultistPortalLesser.class);
/* 450 */     registerSafariNetBlacklist(thaumcraft.common.entities.monster.EntityEldritchCrab.class);
/* 451 */     registerSafariNetBlacklist(thaumcraft.common.entities.monster.EntityInhabitedZombie.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void registerSafariNetBlacklist(Class<?> blacklistedEntity) {
/*     */     try {
/* 457 */       Class<?> registry = Class.forName("powercrystals.minefactoryreloaded.MFRRegistry");
/* 458 */       if (registry != null) {
/* 459 */         Method reg = registry.getMethod("registerSafariNetBlacklist", new Class[] { Class.class });
/* 460 */         reg.invoke(registry, new Object[] { blacklistedEntity });
/*     */       } 
/* 462 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void postInitMisc() {
/* 468 */     for (Item item : ForgeRegistries.ITEMS.getValuesCollection()) {
/* 469 */       if (item != null && item instanceof IPlantable) {
/*     */         try {
/* 471 */           IBlockState bs = ((IPlantable)item).getPlant(null, null);
/* 472 */           if (bs != null) {
/* 473 */             ThaumcraftApi.registerSeed(bs.func_177230_c(), new ItemStack(item));
/*     */           }
/* 475 */         } catch (Exception exception) {}
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 480 */     CropUtils.addStandardCrop(Blocks.field_150440_ba, 32767);
/* 481 */     CropUtils.addStandardCrop(Blocks.field_150423_aK, 32767);
/* 482 */     CropUtils.addStackedCrop(Blocks.field_150436_aH, 32767);
/* 483 */     CropUtils.addStackedCrop(Blocks.field_150434_aF, 32767);
/* 484 */     CropUtils.addStandardCrop(Blocks.field_150388_bm, 3);
/* 485 */     ThaumcraftApi.registerSeed(Blocks.field_150375_by, new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.BROWN.func_176767_b()));
/*     */     
/* 487 */     Utils.addSpecialMiningResult(new ItemStack(Blocks.field_150366_p), new ItemStack(ItemsTC.clusters, 1, 0), 1.0F);
/* 488 */     Utils.addSpecialMiningResult(new ItemStack(Blocks.field_150352_o), new ItemStack(ItemsTC.clusters, 1, 1), 1.0F);
/* 489 */     Utils.addSpecialMiningResult(new ItemStack(BlocksTC.oreCinnabar), new ItemStack(ItemsTC.clusters, 1, 6), 1.0F);
/* 490 */     Utils.addSpecialMiningResult(new ItemStack(Items.field_151128_bU), new ItemStack(ItemsTC.clusters, 1, 7), 1.0F);
/*     */     
/* 492 */     pa = Aspect.aspects.values();
/* 493 */     for (Aspect aspect : pa) {
/* 494 */       aspectOrder.add(aspect);
/*     */     }
/*     */   }
/*     */   
/* 498 */   public static ArrayList<Aspect> aspectOrder = new ArrayList();
/*     */   public static boolean foundCopperIngot = false;
/*     */   public static boolean foundTinIngot = false;
/*     */   public static boolean foundSilverIngot = false;
/*     */   public static boolean foundLeadIngot = false;
/*     */   public static boolean foundCopperOre = false;
/*     */   public static boolean foundTinOre = false;
/*     */   public static boolean foundSilverOre = false;
/*     */   public static boolean foundLeadOre = false;
/*     */   public static boolean isHalloween;
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\config\ModConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */