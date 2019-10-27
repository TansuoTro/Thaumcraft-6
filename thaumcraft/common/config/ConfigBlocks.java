/*     */ package thaumcraft.common.config;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockRailPowered;
/*     */ import net.minecraft.block.BlockSlab;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemSlab;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fml.common.registry.ForgeRegistries;
/*     */ import net.minecraftforge.fml.common.registry.GameRegistry;
/*     */ import net.minecraftforge.registries.IForgeRegistry;
/*     */ import thaumcraft.Thaumcraft;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ import thaumcraft.common.blocks.basic.BlockBannerTC;
/*     */ import thaumcraft.common.blocks.basic.BlockBannerTCItem;
/*     */ import thaumcraft.common.blocks.basic.BlockCandle;
/*     */ import thaumcraft.common.blocks.basic.BlockMetalTC;
/*     */ import thaumcraft.common.blocks.basic.BlockPavingStone;
/*     */ import thaumcraft.common.blocks.basic.BlockPillar;
/*     */ import thaumcraft.common.blocks.basic.BlockPlanksTC;
/*     */ import thaumcraft.common.blocks.basic.BlockSlabTC;
/*     */ import thaumcraft.common.blocks.basic.BlockStairsTC;
/*     */ import thaumcraft.common.blocks.basic.BlockStonePorous;
/*     */ import thaumcraft.common.blocks.basic.BlockStoneTC;
/*     */ import thaumcraft.common.blocks.basic.BlockTable;
/*     */ import thaumcraft.common.blocks.basic.BlockTranslucent;
/*     */ import thaumcraft.common.blocks.crafting.BlockArcaneWorkbench;
/*     */ import thaumcraft.common.blocks.crafting.BlockArcaneWorkbenchCharger;
/*     */ import thaumcraft.common.blocks.crafting.BlockCrucible;
/*     */ import thaumcraft.common.blocks.crafting.BlockFocalManipulator;
/*     */ import thaumcraft.common.blocks.crafting.BlockGolemBuilder;
/*     */ import thaumcraft.common.blocks.crafting.BlockInfusionMatrix;
/*     */ import thaumcraft.common.blocks.crafting.BlockPatternCrafter;
/*     */ import thaumcraft.common.blocks.crafting.BlockResearchTable;
/*     */ import thaumcraft.common.blocks.crafting.BlockThaumatorium;
/*     */ import thaumcraft.common.blocks.crafting.BlockVoidSiphon;
/*     */ import thaumcraft.common.blocks.devices.BlockArcaneEar;
/*     */ import thaumcraft.common.blocks.devices.BlockArcaneEarToggle;
/*     */ import thaumcraft.common.blocks.devices.BlockBellows;
/*     */ import thaumcraft.common.blocks.devices.BlockBrainBox;
/*     */ import thaumcraft.common.blocks.devices.BlockCondenser;
/*     */ import thaumcraft.common.blocks.devices.BlockCondenserLattice;
/*     */ import thaumcraft.common.blocks.devices.BlockDioptra;
/*     */ import thaumcraft.common.blocks.devices.BlockHungryChest;
/*     */ import thaumcraft.common.blocks.devices.BlockInfernalFurnace;
/*     */ import thaumcraft.common.blocks.devices.BlockInlay;
/*     */ import thaumcraft.common.blocks.devices.BlockLamp;
/*     */ import thaumcraft.common.blocks.devices.BlockLevitator;
/*     */ import thaumcraft.common.blocks.devices.BlockMirror;
/*     */ import thaumcraft.common.blocks.devices.BlockPedestal;
/*     */ import thaumcraft.common.blocks.devices.BlockPotionSprayer;
/*     */ import thaumcraft.common.blocks.devices.BlockRechargePedestal;
/*     */ import thaumcraft.common.blocks.devices.BlockRedstoneRelay;
/*     */ import thaumcraft.common.blocks.devices.BlockSpa;
/*     */ import thaumcraft.common.blocks.devices.BlockStabilizer;
/*     */ import thaumcraft.common.blocks.devices.BlockVisBattery;
/*     */ import thaumcraft.common.blocks.devices.BlockVisGenerator;
/*     */ import thaumcraft.common.blocks.devices.BlockWaterJug;
/*     */ import thaumcraft.common.blocks.essentia.BlockAlembic;
/*     */ import thaumcraft.common.blocks.essentia.BlockCentrifuge;
/*     */ import thaumcraft.common.blocks.essentia.BlockEssentiaTransport;
/*     */ import thaumcraft.common.blocks.essentia.BlockJar;
/*     */ import thaumcraft.common.blocks.essentia.BlockSmelter;
/*     */ import thaumcraft.common.blocks.essentia.BlockSmelterAux;
/*     */ import thaumcraft.common.blocks.essentia.BlockSmelterVent;
/*     */ import thaumcraft.common.blocks.essentia.BlockTube;
/*     */ import thaumcraft.common.blocks.misc.BlockBarrier;
/*     */ import thaumcraft.common.blocks.misc.BlockEffect;
/*     */ import thaumcraft.common.blocks.misc.BlockFlesh;
/*     */ import thaumcraft.common.blocks.misc.BlockFluidDeath;
/*     */ import thaumcraft.common.blocks.misc.BlockFluidPure;
/*     */ import thaumcraft.common.blocks.misc.BlockHole;
/*     */ import thaumcraft.common.blocks.misc.BlockNitor;
/*     */ import thaumcraft.common.blocks.misc.BlockPlaceholder;
/*     */ import thaumcraft.common.blocks.world.BlockGrassAmbient;
/*     */ import thaumcraft.common.blocks.world.BlockLoot;
/*     */ import thaumcraft.common.blocks.world.ore.BlockCrystal;
/*     */ import thaumcraft.common.blocks.world.ore.BlockOreTC;
/*     */ import thaumcraft.common.blocks.world.ore.ShardType;
/*     */ import thaumcraft.common.blocks.world.plants.BlockLeavesTC;
/*     */ import thaumcraft.common.blocks.world.plants.BlockLogsTC;
/*     */ import thaumcraft.common.blocks.world.plants.BlockPlantCinderpearl;
/*     */ import thaumcraft.common.blocks.world.plants.BlockPlantShimmerleaf;
/*     */ import thaumcraft.common.blocks.world.plants.BlockPlantVishroom;
/*     */ import thaumcraft.common.blocks.world.plants.BlockSaplingTC;
/*     */ import thaumcraft.common.blocks.world.taint.BlockFluxGoo;
/*     */ import thaumcraft.common.blocks.world.taint.BlockTaint;
/*     */ import thaumcraft.common.blocks.world.taint.BlockTaintFeature;
/*     */ import thaumcraft.common.blocks.world.taint.BlockTaintFibre;
/*     */ import thaumcraft.common.blocks.world.taint.BlockTaintLog;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConfigBlocks
/*     */ {
/*     */   public static void initMisc() {
/* 158 */     BlocksTC.oreAmber.setHarvestLevel("pickaxe", 1);
/* 159 */     BlocksTC.oreCinnabar.setHarvestLevel("pickaxe", 2);
/*     */ 
/*     */     
/* 162 */     BlockUtils.portableHoleBlackList.add("minecraft:bed");
/* 163 */     BlockUtils.portableHoleBlackList.add("minecraft:piston");
/* 164 */     BlockUtils.portableHoleBlackList.add("minecraft:piston_head");
/* 165 */     BlockUtils.portableHoleBlackList.add("minecraft:sticky_piston");
/* 166 */     BlockUtils.portableHoleBlackList.add("minecraft:piston_extension");
/* 167 */     BlockUtils.portableHoleBlackList.add("minecraft:wooden_door");
/* 168 */     BlockUtils.portableHoleBlackList.add("minecraft:spruce_door");
/* 169 */     BlockUtils.portableHoleBlackList.add("minecraft:birch_door");
/* 170 */     BlockUtils.portableHoleBlackList.add("minecraft:jungle_door");
/* 171 */     BlockUtils.portableHoleBlackList.add("minecraft:acacia_door");
/* 172 */     BlockUtils.portableHoleBlackList.add("minecraft:dark_oak_door");
/* 173 */     BlockUtils.portableHoleBlackList.add("minecraft:iron_door");
/* 174 */     BlockUtils.portableHoleBlackList.add("thaumcraft:infernal_furnace");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void initBlocks(IForgeRegistry<Block> iForgeRegistry) {
/* 183 */     BlocksTC.oreAmber = registerBlock((new BlockOreTC("ore_amber")).func_149711_c(1.5F));
/* 184 */     BlocksTC.oreCinnabar = registerBlock((new BlockOreTC("ore_cinnabar")).func_149711_c(2.0F));
/* 185 */     BlocksTC.oreQuartz = registerBlock((new BlockOreTC("ore_quartz")).func_149711_c(3.0F));
/* 186 */     BlocksTC.crystalAir = registerBlock(new BlockCrystal("crystal_aer", Aspect.AIR));
/* 187 */     BlocksTC.crystalFire = registerBlock(new BlockCrystal("crystal_ignis", Aspect.FIRE));
/* 188 */     BlocksTC.crystalWater = registerBlock(new BlockCrystal("crystal_aqua", Aspect.WATER));
/* 189 */     BlocksTC.crystalEarth = registerBlock(new BlockCrystal("crystal_terra", Aspect.EARTH));
/* 190 */     BlocksTC.crystalOrder = registerBlock(new BlockCrystal("crystal_ordo", Aspect.ORDER));
/* 191 */     BlocksTC.crystalEntropy = registerBlock(new BlockCrystal("crystal_perditio", Aspect.ENTROPY));
/* 192 */     BlocksTC.crystalTaint = registerBlock(new BlockCrystal("crystal_vitium", Aspect.FLUX));
/* 193 */     ShardType.AIR.setOre(BlocksTC.crystalAir);
/* 194 */     ShardType.FIRE.setOre(BlocksTC.crystalFire);
/* 195 */     ShardType.WATER.setOre(BlocksTC.crystalWater);
/* 196 */     ShardType.EARTH.setOre(BlocksTC.crystalEarth);
/* 197 */     ShardType.ORDER.setOre(BlocksTC.crystalOrder);
/* 198 */     ShardType.ENTROPY.setOre(BlocksTC.crystalEntropy);
/* 199 */     ShardType.FLUX.setOre(BlocksTC.crystalTaint);
/*     */ 
/*     */     
/* 202 */     BlocksTC.stoneArcane = registerBlock(new BlockStoneTC("stone_arcane", true));
/* 203 */     BlocksTC.stoneArcaneBrick = registerBlock(new BlockStoneTC("stone_arcane_brick", true));
/* 204 */     BlocksTC.stoneAncient = registerBlock(new BlockStoneTC("stone_ancient", true));
/* 205 */     BlocksTC.stoneAncientTile = registerBlock(new BlockStoneTC("stone_ancient_tile", false));
/* 206 */     BlocksTC.stoneAncientRock = registerBlock((BlockTC)(new BlockStoneTC("stone_ancient_rock", false)).func_149711_c(-1.0F));
/* 207 */     BlocksTC.stoneAncientGlyphed = registerBlock(new BlockStoneTC("stone_ancient_glyphed", false));
/* 208 */     BlocksTC.stoneAncientDoorway = registerBlock((BlockTC)(new BlockStoneTC("stone_ancient_doorway", false)).func_149711_c(-1.0F));
/* 209 */     BlocksTC.stoneEldritchTile = registerBlock((BlockTC)(new BlockStoneTC("stone_eldritch_tile", true)).func_149711_c(15.0F).func_149752_b(1000.0F));
/* 210 */     BlocksTC.stonePorous = registerBlock(new BlockStonePorous());
/*     */     
/* 212 */     BlocksTC.stairsArcane = registerBlock(new BlockStairsTC("stairs_arcane", BlocksTC.stoneArcane.func_176223_P()));
/* 213 */     BlocksTC.stairsArcaneBrick = registerBlock(new BlockStairsTC("stairs_arcane_brick", BlocksTC.stoneArcaneBrick.func_176223_P()));
/* 214 */     BlocksTC.stairsAncient = registerBlock(new BlockStairsTC("stairs_ancient", BlocksTC.stoneAncient.func_176223_P()));
/*     */     
/* 216 */     BlocksTC.slabArcaneStone = (BlockSlab)(new BlockSlabTC.Half("slab_arcane_stone", null, false)).func_149711_c(2.0F).func_149752_b(10.0F);
/* 217 */     BlocksTC.doubleSlabArcaneStone = (BlockSlab)(new BlockSlabTC.Double("slab_double_arcane_stone", BlocksTC.slabArcaneStone, false)).func_149711_c(2.0F).func_149752_b(10.0F);
/* 218 */     BlocksTC.slabArcaneBrick = (BlockSlab)(new BlockSlabTC.Half("slab_arcane_brick", null, false)).func_149711_c(2.0F).func_149752_b(10.0F);
/* 219 */     BlocksTC.doubleSlabArcaneBrick = (BlockSlab)(new BlockSlabTC.Double("slab_double_arcane_brick", BlocksTC.slabArcaneBrick, false)).func_149711_c(2.0F).func_149752_b(10.0F);
/* 220 */     BlocksTC.slabAncient = (BlockSlab)(new BlockSlabTC.Half("slab_ancient", null, false)).func_149711_c(2.0F).func_149752_b(10.0F);
/* 221 */     BlocksTC.doubleSlabAncient = (BlockSlab)(new BlockSlabTC.Double("slab_double_ancient", BlocksTC.slabAncient, false)).func_149711_c(2.0F).func_149752_b(10.0F);
/* 222 */     BlocksTC.slabEldritch = (BlockSlab)(new BlockSlabTC.Half("slab_eldritch", null, false)).func_149711_c(2.0F).func_149752_b(10.0F);
/* 223 */     BlocksTC.doubleSlabEldritch = (BlockSlab)(new BlockSlabTC.Double("slab_double_eldritch", BlocksTC.slabEldritch, false)).func_149711_c(2.0F).func_149752_b(10.0F);
/*     */     
/* 225 */     ForgeRegistries.BLOCKS.register(BlocksTC.slabArcaneStone);
/* 226 */     ForgeRegistries.BLOCKS.register(BlocksTC.doubleSlabArcaneStone);
/* 227 */     ForgeRegistries.BLOCKS.register(BlocksTC.slabArcaneBrick);
/* 228 */     ForgeRegistries.BLOCKS.register(BlocksTC.doubleSlabArcaneBrick);
/* 229 */     ForgeRegistries.BLOCKS.register(BlocksTC.slabAncient);
/* 230 */     ForgeRegistries.BLOCKS.register(BlocksTC.doubleSlabAncient);
/* 231 */     ForgeRegistries.BLOCKS.register(BlocksTC.slabEldritch);
/* 232 */     ForgeRegistries.BLOCKS.register(BlocksTC.doubleSlabEldritch);
/* 233 */     ForgeRegistries.ITEMS.register((new ItemSlab(BlocksTC.slabArcaneStone, BlocksTC.slabArcaneStone, BlocksTC.doubleSlabArcaneStone)).setRegistryName(BlocksTC.slabArcaneStone.getRegistryName()));
/* 234 */     ForgeRegistries.ITEMS.register((new ItemSlab(BlocksTC.slabArcaneBrick, BlocksTC.slabArcaneBrick, BlocksTC.doubleSlabArcaneBrick)).setRegistryName(BlocksTC.slabArcaneBrick.getRegistryName()));
/* 235 */     ForgeRegistries.ITEMS.register((new ItemSlab(BlocksTC.slabAncient, BlocksTC.slabAncient, BlocksTC.doubleSlabAncient)).setRegistryName(BlocksTC.slabAncient.getRegistryName()));
/* 236 */     ForgeRegistries.ITEMS.register((new ItemSlab(BlocksTC.slabEldritch, BlocksTC.slabEldritch, BlocksTC.doubleSlabEldritch)).setRegistryName(BlocksTC.slabEldritch.getRegistryName()));
/*     */ 
/*     */     
/* 239 */     BlocksTC.saplingGreatwood = registerBlock(new BlockSaplingTC("sapling_greatwood"));
/* 240 */     BlocksTC.saplingSilverwood = registerBlock(new BlockSaplingTC("sapling_silverwood"));
/*     */     
/* 242 */     BlocksTC.logGreatwood = registerBlock(new BlockLogsTC("log_greatwood"));
/* 243 */     BlocksTC.logSilverwood = registerBlock(new BlockLogsTC("log_silverwood"));
/*     */     
/* 245 */     BlocksTC.leafGreatwood = registerBlock(new BlockLeavesTC("leaves_greatwood"));
/* 246 */     BlocksTC.leafSilverwood = registerBlock(new BlockLeavesTC("leaves_silverwood"));
/*     */     
/* 248 */     BlocksTC.shimmerleaf = registerBlock(new BlockPlantShimmerleaf());
/* 249 */     BlocksTC.cinderpearl = registerBlock(new BlockPlantCinderpearl());
/* 250 */     BlocksTC.vishroom = registerBlock(new BlockPlantVishroom());
/*     */ 
/*     */     
/* 253 */     BlocksTC.plankGreatwood = registerBlock(new BlockPlanksTC("plank_greatwood"));
/* 254 */     BlocksTC.plankSilverwood = registerBlock(new BlockPlanksTC("plank_silverwood"));
/* 255 */     BlocksTC.stairsGreatwood = registerBlock(new BlockStairsTC("stairs_greatwood", BlocksTC.plankGreatwood.func_176223_P()));
/* 256 */     BlocksTC.stairsSilverwood = registerBlock(new BlockStairsTC("stairs_silverwood", BlocksTC.plankSilverwood.func_176223_P()));
/*     */     
/* 258 */     BlocksTC.slabGreatwood = (BlockSlab)(new BlockSlabTC.Half("slab_greatwood", null, true)).func_149711_c(1.2F).func_149752_b(2.0F);
/* 259 */     BlocksTC.doubleSlabGreatwood = (BlockSlab)(new BlockSlabTC.Double("slab_double_greatwood", BlocksTC.slabGreatwood, true)).func_149711_c(1.2F).func_149752_b(2.0F);
/* 260 */     BlocksTC.slabSilverwood = (BlockSlab)(new BlockSlabTC.Half("slab_silverwood", null, true)).func_149711_c(1.0F).func_149752_b(2.0F);
/* 261 */     BlocksTC.doubleSlabSilverwood = (BlockSlab)(new BlockSlabTC.Double("slab_double_silverwood", BlocksTC.slabSilverwood, true)).func_149711_c(1.0F).func_149752_b(2.0F);
/* 262 */     ForgeRegistries.BLOCKS.register(BlocksTC.slabGreatwood);
/* 263 */     ForgeRegistries.BLOCKS.register(BlocksTC.doubleSlabGreatwood);
/* 264 */     ForgeRegistries.BLOCKS.register(BlocksTC.slabSilverwood);
/* 265 */     ForgeRegistries.BLOCKS.register(BlocksTC.doubleSlabSilverwood);
/* 266 */     ForgeRegistries.ITEMS.register((new ItemSlab(BlocksTC.slabGreatwood, BlocksTC.slabGreatwood, BlocksTC.doubleSlabGreatwood)).setRegistryName(BlocksTC.slabGreatwood.getRegistryName()));
/* 267 */     ForgeRegistries.ITEMS.register((new ItemSlab(BlocksTC.slabSilverwood, BlocksTC.slabSilverwood, BlocksTC.doubleSlabSilverwood)).setRegistryName(BlocksTC.slabSilverwood.getRegistryName()));
/*     */     
/* 269 */     BlocksTC.amberBlock = registerBlock(new BlockTranslucent("amber_block"));
/* 270 */     BlocksTC.amberBrick = registerBlock(new BlockTranslucent("amber_brick"));
/*     */     
/* 272 */     BlocksTC.fleshBlock = registerBlock(new BlockFlesh());
/* 273 */     BlocksTC.lootCrateCommon = registerBlock(new BlockLoot(Material.field_151575_d, "loot_crate_common", BlockLoot.LootType.COMMON));
/* 274 */     BlocksTC.lootCrateUncommon = registerBlock(new BlockLoot(Material.field_151575_d, "loot_crate_uncommon", BlockLoot.LootType.UNCOMMON));
/* 275 */     BlocksTC.lootCrateRare = registerBlock(new BlockLoot(Material.field_151575_d, "loot_crate_rare", BlockLoot.LootType.RARE));
/* 276 */     BlocksTC.lootUrnCommon = registerBlock(new BlockLoot(Material.field_151576_e, "loot_urn_common", BlockLoot.LootType.COMMON));
/* 277 */     BlocksTC.lootUrnUncommon = registerBlock(new BlockLoot(Material.field_151576_e, "loot_urn_uncommon", BlockLoot.LootType.UNCOMMON));
/* 278 */     BlocksTC.lootUrnRare = registerBlock(new BlockLoot(Material.field_151576_e, "loot_urn_rare", BlockLoot.LootType.RARE));
/*     */     
/* 280 */     BlocksTC.taintFibre = registerBlock(new BlockTaintFibre());
/* 281 */     BlocksTC.taintCrust = registerBlock(new BlockTaint("taint_crust"));
/* 282 */     BlocksTC.taintSoil = registerBlock(new BlockTaint("taint_soil"));
/* 283 */     BlocksTC.taintRock = registerBlock(new BlockTaint("taint_rock"));
/* 284 */     BlocksTC.taintGeyser = registerBlock(new BlockTaint("taint_geyser"));
/* 285 */     BlocksTC.taintFeature = registerBlock(new BlockTaintFeature());
/* 286 */     BlocksTC.taintLog = registerBlock(new BlockTaintLog());
/* 287 */     BlocksTC.grassAmbient = registerBlock(new BlockGrassAmbient());
/*     */ 
/*     */     
/* 290 */     BlocksTC.tableWood = registerBlock((new BlockTable(Material.field_151575_d, "table_wood", SoundType.field_185848_a)).func_149711_c(2.0F));
/* 291 */     BlocksTC.tableStone = registerBlock((new BlockTable(Material.field_151576_e, "table_stone", SoundType.field_185851_d)).func_149711_c(2.5F));
/* 292 */     BlocksTC.pedestalArcane = registerBlock(new BlockPedestal("pedestal_arcane"));
/* 293 */     BlocksTC.pedestalAncient = registerBlock(new BlockPedestal("pedestal_ancient"));
/* 294 */     BlocksTC.pedestalEldritch = registerBlock(new BlockPedestal("pedestal_eldritch"));
/* 295 */     BlocksTC.metalBlockBrass = registerBlock(new BlockMetalTC("metal_brass"));
/* 296 */     BlocksTC.metalBlockThaumium = registerBlock(new BlockMetalTC("metal_thaumium"));
/* 297 */     BlocksTC.metalBlockVoid = registerBlock(new BlockMetalTC("metal_void"));
/* 298 */     BlocksTC.metalAlchemical = registerBlock(new BlockMetalTC("metal_alchemical"));
/* 299 */     BlocksTC.metalAlchemicalAdvanced = registerBlock(new BlockMetalTC("metal_alchemical_advanced"));
/* 300 */     BlocksTC.pavingStoneTravel = registerBlock(new BlockPavingStone("paving_stone_travel"));
/* 301 */     BlocksTC.pavingStoneBarrier = registerBlock(new BlockPavingStone("paving_stone_barrier"));
/*     */     
/* 303 */     BlocksTC.pillarArcane = registerBlock(new BlockPillar("pillar_arcane"));
/* 304 */     BlocksTC.pillarAncient = registerBlock(new BlockPillar("pillar_ancient"));
/* 305 */     BlocksTC.pillarEldritch = registerBlock(new BlockPillar("pillar_eldritch"));
/* 306 */     BlocksTC.matrixSpeed = registerBlock(new BlockStoneTC("matrix_speed", false));
/* 307 */     BlocksTC.matrixCost = registerBlock(new BlockStoneTC("matrix_cost", false));
/*     */ 
/*     */     
/* 310 */     for (EnumDyeColor dye : EnumDyeColor.values()) {
/* 311 */       BlocksTC.candles.put(dye, registerBlock(new BlockCandle("candle_" + dye.func_176762_d().toLowerCase(), dye)));
/*     */     }
/* 313 */     for (EnumDyeColor dye : EnumDyeColor.values()) {
/* 314 */       BlockBannerTC block = new BlockBannerTC("banner_" + dye.func_176762_d().toLowerCase(), dye);
/* 315 */       ForgeRegistries.BLOCKS.register(block);
/* 316 */       ForgeRegistries.ITEMS.register((new BlockBannerTCItem(block)).setRegistryName(block.getRegistryName()));
/* 317 */       BlocksTC.banners.put(dye, block);
/*     */     } 
/*     */     
/* 320 */     BlocksTC.bannerCrimsonCult = new BlockBannerTC("banner_crimson_cult", null);
/* 321 */     ForgeRegistries.BLOCKS.register(BlocksTC.bannerCrimsonCult);
/* 322 */     ForgeRegistries.ITEMS.register((new BlockBannerTCItem((BlockBannerTC)BlocksTC.bannerCrimsonCult)).setRegistryName(BlocksTC.bannerCrimsonCult.getRegistryName()));
/*     */     
/* 324 */     for (EnumDyeColor dye : EnumDyeColor.values()) {
/* 325 */       BlocksTC.nitor.put(dye, registerBlock(new BlockNitor("nitor_" + dye.func_176762_d().toLowerCase(), dye)));
/*     */     }
/* 327 */     BlocksTC.visBattery = registerBlock(new BlockVisBattery());
/*     */     
/* 329 */     BlocksTC.inlay = registerBlock(new BlockInlay());
/*     */ 
/*     */     
/* 332 */     BlocksTC.arcaneWorkbench = registerBlock(new BlockArcaneWorkbench());
/* 333 */     BlocksTC.arcaneWorkbenchCharger = registerBlock(new BlockArcaneWorkbenchCharger());
/* 334 */     BlocksTC.dioptra = registerBlock(new BlockDioptra());
/* 335 */     BlocksTC.researchTable = registerBlock(new BlockResearchTable());
/* 336 */     BlocksTC.crucible = registerBlock(new BlockCrucible());
/* 337 */     BlocksTC.arcaneEar = registerBlock(new BlockArcaneEar("arcane_ear"));
/* 338 */     BlocksTC.arcaneEarToggle = registerBlock(new BlockArcaneEarToggle());
/* 339 */     BlocksTC.lampArcane = registerBlock(new BlockLamp(thaumcraft.common.tiles.devices.TileLampArcane.class, "lamp_arcane"));
/* 340 */     BlocksTC.lampFertility = registerBlock(new BlockLamp(thaumcraft.common.tiles.devices.TileLampFertility.class, "lamp_fertility"));
/* 341 */     BlocksTC.lampGrowth = registerBlock(new BlockLamp(thaumcraft.common.tiles.devices.TileLampGrowth.class, "lamp_growth"));
/* 342 */     BlocksTC.levitator = registerBlock(new BlockLevitator());
/* 343 */     BlocksTC.centrifuge = registerBlock(new BlockCentrifuge());
/*     */     
/* 345 */     BlocksTC.bellows = registerBlock(new BlockBellows());
/*     */     
/* 347 */     BlocksTC.smelterBasic = registerBlock(new BlockSmelter("smelter_basic"));
/* 348 */     BlocksTC.smelterThaumium = registerBlock(new BlockSmelter("smelter_thaumium"));
/* 349 */     BlocksTC.smelterVoid = registerBlock(new BlockSmelter("smelter_void"));
/* 350 */     BlocksTC.smelterAux = registerBlock(new BlockSmelterAux());
/* 351 */     BlocksTC.smelterVent = registerBlock(new BlockSmelterVent());
/* 352 */     BlocksTC.alembic = registerBlock(new BlockAlembic());
/*     */     
/* 354 */     BlocksTC.rechargePedestal = registerBlock(new BlockRechargePedestal());
/* 355 */     BlocksTC.wandWorkbench = registerBlock(new BlockFocalManipulator());
/* 356 */     BlocksTC.hungryChest = registerBlock(new BlockHungryChest());
/*     */     
/* 358 */     BlocksTC.tube = registerBlock(new BlockTube(thaumcraft.common.tiles.essentia.TileTube.class, "tube"));
/* 359 */     BlocksTC.tubeValve = registerBlock(new BlockTube(thaumcraft.common.tiles.essentia.TileTubeValve.class, "tube_valve"));
/* 360 */     BlocksTC.tubeRestrict = registerBlock(new BlockTube(thaumcraft.common.tiles.essentia.TileTubeRestrict.class, "tube_restrict"));
/* 361 */     BlocksTC.tubeOneway = registerBlock(new BlockTube(thaumcraft.common.tiles.essentia.TileTubeOneway.class, "tube_oneway"));
/* 362 */     BlocksTC.tubeFilter = registerBlock(new BlockTube(thaumcraft.common.tiles.essentia.TileTubeFilter.class, "tube_filter"));
/* 363 */     BlocksTC.tubeBuffer = registerBlock(new BlockTube(thaumcraft.common.tiles.essentia.TileTubeBuffer.class, "tube_buffer"));
/*     */     
/* 365 */     BlocksTC.jarNormal = registerBlock(new BlockJar(thaumcraft.common.tiles.essentia.TileJarFillable.class, "jar_normal"), thaumcraft.common.blocks.essentia.BlockJarItem.class);
/* 366 */     BlocksTC.jarVoid = registerBlock(new BlockJar(thaumcraft.common.tiles.essentia.TileJarFillableVoid.class, "jar_void"), thaumcraft.common.blocks.essentia.BlockJarItem.class);
/* 367 */     BlocksTC.jarBrain = registerBlock(new BlockJar(thaumcraft.common.tiles.devices.TileJarBrain.class, "jar_brain"), thaumcraft.common.blocks.essentia.BlockJarBrainItem.class);
/* 368 */     BlocksTC.infusionMatrix = registerBlock(new BlockInfusionMatrix());
/*     */     
/* 370 */     BlocksTC.infernalFurnace = registerBlock(new BlockInfernalFurnace());
/* 371 */     BlocksTC.everfullUrn = registerBlock(new BlockWaterJug());
/* 372 */     BlocksTC.thaumatorium = registerBlock(new BlockThaumatorium(false));
/* 373 */     BlocksTC.thaumatoriumTop = registerBlock(new BlockThaumatorium(true));
/* 374 */     BlocksTC.brainBox = registerBlock(new BlockBrainBox());
/* 375 */     BlocksTC.spa = registerBlock(new BlockSpa());
/* 376 */     BlocksTC.golemBuilder = registerBlock(new BlockGolemBuilder());
/*     */     
/* 378 */     BlocksTC.mirror = registerBlock(new BlockMirror(thaumcraft.common.tiles.devices.TileMirror.class, "mirror"), thaumcraft.common.blocks.devices.BlockMirrorItem.class);
/* 379 */     BlocksTC.mirrorEssentia = registerBlock(new BlockMirror(thaumcraft.common.tiles.devices.TileMirrorEssentia.class, "mirror_essentia"), thaumcraft.common.blocks.devices.BlockMirrorItem.class);
/*     */     
/* 381 */     BlocksTC.essentiaTransportInput = registerBlock(new BlockEssentiaTransport(thaumcraft.common.tiles.essentia.TileEssentiaInput.class, "essentia_input"));
/* 382 */     BlocksTC.essentiaTransportOutput = registerBlock(new BlockEssentiaTransport(thaumcraft.common.tiles.essentia.TileEssentiaOutput.class, "essentia_output"));
/*     */     
/* 384 */     BlocksTC.redstoneRelay = registerBlock(new BlockRedstoneRelay());
/* 385 */     BlocksTC.patternCrafter = registerBlock(new BlockPatternCrafter());
/*     */     
/* 387 */     BlocksTC.potionSprayer = registerBlock(new BlockPotionSprayer());
/*     */     
/* 389 */     BlocksTC.activatorRail = registerBlock(((Block)(new BlockRailPowered()).func_149711_c(0.7F).func_149647_a(ConfigItems.TABTC)
/* 390 */         .setRegistryName("thaumcraft", "activator_rail")).func_149663_c("activator_rail"));
/*     */     
/* 392 */     BlocksTC.stabilizer = registerBlock(new BlockStabilizer());
/* 393 */     BlocksTC.visGenerator = registerBlock(new BlockVisGenerator());
/*     */     
/* 395 */     BlocksTC.condenser = registerBlock(new BlockCondenser());
/* 396 */     BlocksTC.condenserlattice = registerBlock(new BlockCondenserLattice(false));
/* 397 */     BlocksTC.condenserlatticeDirty = registerBlock(new BlockCondenserLattice(true));
/*     */     
/* 399 */     BlocksTC.voidSiphon = registerBlock(new BlockVoidSiphon());
/*     */ 
/*     */     
/* 402 */     FluidRegistry.registerFluid(FluidFluxGoo.instance);
/* 403 */     BlocksTC.fluxGoo = new BlockFluxGoo();
/* 404 */     iForgeRegistry.register(BlocksTC.fluxGoo);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 410 */     FluidRegistry.registerFluid(FluidDeath.instance);
/* 411 */     FluidRegistry.addBucketForFluid(FluidDeath.instance);
/* 412 */     BlocksTC.liquidDeath = new BlockFluidDeath();
/* 413 */     iForgeRegistry.register(BlocksTC.liquidDeath);
/*     */     
/* 415 */     FluidRegistry.registerFluid(FluidPure.instance);
/* 416 */     FluidRegistry.addBucketForFluid(FluidPure.instance);
/* 417 */     BlocksTC.purifyingFluid = new BlockFluidPure();
/* 418 */     iForgeRegistry.register(BlocksTC.purifyingFluid);
/*     */ 
/*     */     
/* 421 */     BlocksTC.hole = registerBlock(new BlockHole());
/* 422 */     BlocksTC.effectShock = registerBlock(new BlockEffect("effect_shock"));
/* 423 */     BlocksTC.effectSap = registerBlock(new BlockEffect("effect_sap"));
/* 424 */     BlocksTC.effectGlimmer = registerBlock(new BlockEffect("effect_glimmer"));
/* 425 */     BlocksTC.placeholderNetherbrick = registerBlock(new BlockPlaceholder("placeholder_brick"));
/* 426 */     BlocksTC.placeholderObsidian = registerBlock(new BlockPlaceholder("placeholder_obsidian"));
/* 427 */     BlocksTC.placeholderBars = registerBlock(new BlockPlaceholder("placeholder_bars"));
/* 428 */     BlocksTC.placeholderAnvil = registerBlock(new BlockPlaceholder("placeholder_anvil"));
/* 429 */     BlocksTC.placeholderCauldron = registerBlock(new BlockPlaceholder("placeholder_cauldron"));
/* 430 */     BlocksTC.placeholderTable = registerBlock(new BlockPlaceholder("placeholder_table"));
/* 431 */     BlocksTC.empty = registerBlock(new BlockTranslucent("empty"));
/* 432 */     BlocksTC.barrier = registerBlock(new BlockBarrier());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void initTileEntities() {
/* 438 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.crafting.TileArcaneWorkbench.class, "thaumcraft:TileArcaneWorkbench");
/* 439 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.devices.TileDioptra.class, "thaumcraft:TileDioptra");
/* 440 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.devices.TileArcaneEar.class, "thaumcraft:TileArcaneEar");
/* 441 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.devices.TileLevitator.class, "thaumcraft:TileLevitator");
/* 442 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.crafting.TileCrucible.class, "thaumcraft:TileCrucible");
/* 443 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.misc.TileNitor.class, "thaumcraft:TileNitor");
/* 444 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.crafting.TileFocalManipulator.class, "thaumcraft:TileFocalManipulator");
/* 445 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.crafting.TilePedestal.class, "thaumcraft:TilePedestal");
/* 446 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.devices.TileRechargePedestal.class, "thaumcraft:TileRechargePedestal");
/* 447 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.crafting.TileResearchTable.class, "thaumcraft:TileResearchTable");
/* 448 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.essentia.TileTube.class, "thaumcraft:TileTube");
/* 449 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.essentia.TileTubeValve.class, "thaumcraft:TileTubeValve");
/* 450 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.essentia.TileTubeFilter.class, "thaumcraft:TileTubeFilter");
/* 451 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.essentia.TileTubeRestrict.class, "thaumcraft:TileTubeRestrict");
/* 452 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.essentia.TileTubeOneway.class, "thaumcraft:TileTubeOneway");
/* 453 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.essentia.TileTubeBuffer.class, "thaumcraft:TileTubeBuffer");
/* 454 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.devices.TileHungryChest.class, "thaumcraft:TileChestHungry");
/* 455 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.essentia.TileCentrifuge.class, "thaumcraft:TileCentrifuge");
/* 456 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.essentia.TileJarFillable.class, "thaumcraft:TileJar");
/* 457 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.essentia.TileJarFillableVoid.class, "thaumcraft:TileJarVoid");
/* 458 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.devices.TileJarBrain.class, "thaumcraft:TileJarBrain");
/* 459 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.devices.TileBellows.class, "thaumcraft:TileBellows");
/* 460 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.essentia.TileSmelter.class, "thaumcraft:TileSmelter");
/* 461 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.essentia.TileAlembic.class, "thaumcraft:TileAlembic");
/* 462 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.crafting.TileInfusionMatrix.class, "thaumcraft:TileInfusionMatrix");
/* 463 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.devices.TileWaterJug.class, "thaumcraft:TileWaterJug");
/* 464 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.devices.TileInfernalFurnace.class, "thaumcraft:TileInfernalFurnace");
/* 465 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.crafting.TileThaumatorium.class, "thaumcraft:TileThaumatorium");
/* 466 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.crafting.TileThaumatoriumTop.class, "thaumcraft:TileThaumatoriumTop");
/* 467 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.devices.TileSpa.class, "thaumcraft:TileSpa");
/* 468 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.devices.TileLampGrowth.class, "thaumcraft:TileLampGrowth");
/* 469 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.devices.TileLampArcane.class, "thaumcraft:TileLampArcane");
/* 470 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.devices.TileLampFertility.class, "thaumcraft:TileLampFertility");
/* 471 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.devices.TileMirror.class, "thaumcraft:TileMirror");
/* 472 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.devices.TileMirrorEssentia.class, "thaumcraft:TileMirrorEssentia");
/* 473 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.devices.TileRedstoneRelay.class, "thaumcraft:TileRedstoneRelay");
/* 474 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.crafting.TileGolemBuilder.class, "thaumcraft:TileGolemBuilder");
/* 475 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.essentia.TileEssentiaInput.class, "thaumcraft:TileEssentiaInput");
/* 476 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.essentia.TileEssentiaOutput.class, "thaumcraft:TileEssentiaOutput");
/* 477 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.crafting.TilePatternCrafter.class, "thaumcraft:TilePatternCrafter");
/* 478 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.devices.TilePotionSprayer.class, "thaumcraft:TilePotionSprayer");
/* 479 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.devices.TileVisGenerator.class, "thaumcraft:TileVisGenerator");
/* 480 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.devices.TileStabilizer.class, "thaumcraft:TileStabilizer");
/* 481 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.devices.TileCondenser.class, "thaumcraft:TileCondenser");
/* 482 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.crafting.TileVoidSiphon.class, "thaumcraft:TileVoidSiphon");
/*     */ 
/*     */     
/* 485 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.misc.TileBanner.class, "thaumcraft:TileBanner");
/*     */     
/* 487 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.misc.TileHole.class, "thaumcraft:TileHole");
/* 488 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.misc.TileBarrierStone.class, "thaumcraft:TileBarrierStone");
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
/* 503 */   private static Block registerBlock(Block block) { return registerBlock(block, new ItemBlock(block)); }
/*     */ 
/*     */ 
/*     */   
/*     */   private static Block registerBlock(Block block, ItemBlock itemBlock) {
/* 508 */     ForgeRegistries.BLOCKS.register(block);
/* 509 */     itemBlock.setRegistryName(block.getRegistryName());
/* 510 */     ForgeRegistries.ITEMS.register(itemBlock);
/* 511 */     Thaumcraft.proxy.registerModel(itemBlock);
/* 512 */     return block;
/*     */   }
/*     */ 
/*     */   
/*     */   private static Block registerBlock(Block block, Class clazz) {
/* 517 */     ForgeRegistries.BLOCKS.register(block);
/*     */     try {
/* 519 */       ItemBlock itemBlock = (ItemBlock)clazz.getConstructors()[0].newInstance(new Object[] { block });
/* 520 */       itemBlock.setRegistryName(block.getRegistryName());
/* 521 */       ForgeRegistries.ITEMS.register(itemBlock);
/* 522 */       Thaumcraft.proxy.registerModel(itemBlock);
/* 523 */     } catch (Exception e) {
/* 524 */       e.printStackTrace();
/*     */     } 
/* 526 */     return block;
/*     */   }
/*     */ 
/*     */   
/*     */   public static final class FluidPure
/*     */     extends Fluid
/*     */   {
/*     */     public static final String name = "purifying_fluid";
/* 534 */     public static final FluidPure instance = new FluidPure();
/*     */ 
/*     */     
/*     */     private FluidPure() {
/* 538 */       super("purifying_fluid", new ResourceLocation("blocks/water_still"), new ResourceLocation("blocks/water_flow"));
/* 539 */       setLuminosity(5);
/* 540 */       setRarity(EnumRarity.RARE);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 546 */     public int getColor() { return 2013252778; }
/*     */   }
/*     */   
/*     */   public static final class FluidDeath
/*     */     extends Fluid
/*     */   {
/*     */     public static final String name = "liquid_death";
/* 553 */     public static final FluidDeath instance = new FluidDeath();
/*     */ 
/*     */     
/*     */     private FluidDeath() {
/* 557 */       super("liquid_death", new ResourceLocation("thaumcraft:blocks/animatedglow"), new ResourceLocation("thaumcraft:blocks/animatedglow"));
/*     */       
/* 559 */       setViscosity(1500);
/* 560 */       setRarity(EnumRarity.RARE);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 565 */     public int getColor() { return -263978855; }
/*     */   }
/*     */   
/*     */   public static final class FluidFluxGoo
/*     */     extends Fluid
/*     */   {
/*     */     public static final String name = "flux_goo";
/* 572 */     public static final FluidFluxGoo instance = new FluidFluxGoo();
/*     */ 
/*     */     
/*     */     private FluidFluxGoo() {
/* 576 */       super("flux_goo", new ResourceLocation("thaumcraft:blocks/flux_goo"), new ResourceLocation("thaumcraft:blocks/flux_goo"));
/* 577 */       setViscosity(6000);
/* 578 */       setDensity(8);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\config\ConfigBlocks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */