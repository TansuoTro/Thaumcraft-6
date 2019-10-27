/*     */ package thaumcraft.common.world;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.block.state.pattern.BlockMatcher;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ import net.minecraft.world.gen.IChunkGenerator;
/*     */ import net.minecraft.world.gen.structure.MapGenScatteredFeature;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ import net.minecraftforge.fml.common.IWorldGenerator;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.blocks.world.ore.ShardType;
/*     */ import thaumcraft.common.config.ModConfig;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultistPortalLesser;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.world.aura.AuraHandler;
/*     */ import thaumcraft.common.world.biomes.BiomeHandler;
/*     */ import thaumcraft.common.world.objects.WorldGenCustomFlowers;
/*     */ import thaumcraft.common.world.objects.WorldGenGreatwoodTrees;
/*     */ import thaumcraft.common.world.objects.WorldGenMound;
/*     */ import thaumcraft.common.world.objects.WorldGenSilverwoodTrees;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ThaumcraftWorldGenerator
/*     */   implements IWorldGenerator
/*     */ {
/*  41 */   public static ThaumcraftWorldGenerator INSTANCE = new ThaumcraftWorldGenerator();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
/*  56 */     worldGeneration(random, chunkX, chunkZ, world, true);
/*     */     
/*  58 */     AuraHandler.generateAura(chunkProvider.func_186025_d(chunkX, chunkZ), random);
/*     */   }
/*     */ 
/*     */   
/*     */   public void worldGeneration(Random random, int chunkX, int chunkZ, World world, boolean newGen) {
/*  63 */     if (world.field_73011_w.getDimension() == ModConfig.CONFIG_WORLD.dimensionOuterId) {
/*     */       
/*  65 */       world.func_72964_e(chunkX, chunkZ).func_76630_e();
/*     */     } else {
/*     */       
/*  68 */       generateAll(world, random, chunkX, chunkZ, newGen);
/*     */       
/*  70 */       if (world.field_73011_w.getDimension() == -1) {
/*  71 */         generateNether(world, random, chunkX, chunkZ, newGen);
/*     */       }
/*  73 */       else if (world.field_73011_w.getDimension() == ModConfig.CONFIG_WORLD.overworldDim) {
/*  74 */         generateSurface(world, random, chunkX, chunkZ, newGen);
/*     */       } 
/*  76 */       if (!newGen) {
/*  77 */         world.func_72964_e(chunkX, chunkZ).func_76630_e();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void generateSurface(World world, Random random, int chunkX, int chunkZ, boolean newGen) {
/*  87 */     int blacklist = BiomeHandler.getDimBlacklist(world.field_73011_w.getDimension());
/*     */     
/*  89 */     if (blacklist == -1 && ModConfig.CONFIG_WORLD.generateStructure && world.field_73011_w.getDimension() == ModConfig.CONFIG_WORLD.overworldDim && 
/*  90 */       !world.func_72912_H().func_76067_t().func_77127_a().startsWith("flat") && (newGen || ModConfig.CONFIG_WORLD.regenStructure)) {
/*     */ 
/*     */       
/*  93 */       int randPosX = chunkX * 16 + 8 + MathHelper.func_76136_a(random, -4, 4);
/*  94 */       int randPosZ = chunkZ * 16 + 8 + MathHelper.func_76136_a(random, -4, 4);
/*     */       
/*  96 */       BlockPos p = world.func_175725_q(new BlockPos(randPosX, 0, randPosZ)).func_177979_c(9);
/*     */       
/*  98 */       if (p.func_177956_o() < world.func_72940_L()) {
/*  99 */         if (random.nextInt(100) == 0) {
/* 100 */           WorldGenMound worldGenMound = new WorldGenMound();
/* 101 */           worldGenMound.func_180709_b(world, random, p);
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
/*     */         }
/* 119 */         else if (random.nextInt(500) == 0) {
/* 120 */           BlockPos p2 = p.func_177981_b(8);
/* 121 */           IBlockState bs = world.func_180495_p(p2);
/* 122 */           if (bs.func_185904_a() == Material.field_151578_c || bs.func_185904_a() == Material.field_151576_e || bs
/* 123 */             .func_185904_a() == Material.field_151595_p || bs.func_185904_a() == Material.field_151597_y) {
/*     */             
/* 125 */             EntityCultistPortalLesser eg = new EntityCultistPortalLesser(world);
/* 126 */             eg.func_70107_b(p2.func_177958_n() + 0.5D, (p2.func_177956_o() + 1), p2.func_177952_p() + 0.5D);
/* 127 */             eg.func_180482_a(world.func_175649_E(new BlockPos(eg)), (IEntityLivingData)null);
/* 128 */             world.func_72838_d(eg);
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void generateNodes(World world, Random random, int chunkX, int chunkZ, boolean newGen, int blacklist) {
/* 137 */     if (blacklist != 0 && blacklist != 2 && ModConfig.CONFIG_WORLD.generateAura && (newGen || ModConfig.CONFIG_WORLD.regenAura)) {
/*     */       
/* 139 */       BlockPos var7 = null;
/*     */       
/*     */       try {
/* 142 */         var7 = (new MapGenScatteredFeature()).func_180706_b(world, world
/* 143 */             .func_175645_m(new BlockPos(chunkX * 16 + 8, 64, chunkZ * 16 + 8)), true);
/* 144 */       } catch (Exception exception) {}
/*     */     } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void generateVegetation(World world, Random random, int chunkX, int chunkZ, boolean newGen) {
/* 201 */     Biome bgb = world.func_180494_b(new BlockPos(chunkX * 16 + 8, 50, chunkZ * 16 + 8));
/* 202 */     if (BiomeHandler.getBiomeBlacklist(Biome.func_185362_a(bgb)) != -1) {
/*     */       return;
/*     */     }
/* 205 */     if (random.nextInt(80) == 3) {
/* 206 */       generateSilverwood(world, random, chunkX, chunkZ);
/*     */     }
/*     */ 
/*     */     
/* 210 */     if (random.nextInt(25) == 7) {
/* 211 */       generateGreatwood(world, random, chunkX, chunkZ);
/*     */     }
/*     */ 
/*     */     
/* 215 */     int randPosX = chunkX * 16 + 8;
/* 216 */     int randPosZ = chunkZ * 16 + 8;
/* 217 */     BlockPos bp = world.func_175645_m(new BlockPos(randPosX, 0, randPosZ));
/*     */     
/* 219 */     if ((world.func_180494_b(bp)).field_76752_A.func_177230_c() == Blocks.field_150354_m && world
/* 220 */       .func_180494_b(bp).func_180626_a(bp) > 1.0F && random
/* 221 */       .nextInt(30) == 0) {
/* 222 */       generateFlowers(world, random, bp, BlocksTC.cinderpearl, 0);
/*     */     }
/*     */   }
/*     */   
/* 226 */   private final Predicate<IBlockState> predicate = BlockMatcher.func_177642_a(Blocks.field_150348_b);
/*     */   
/*     */   private void generateOres(World world, Random random, int chunkX, int chunkZ, boolean newGen) {
/* 229 */     Biome bgb = world.func_180494_b(new BlockPos(chunkX * 16 + 8, 50, chunkZ * 16 + 8));
/* 230 */     if (BiomeHandler.getBiomeBlacklist(Biome.func_185362_a(bgb)) == 0 || 
/* 231 */       BiomeHandler.getBiomeBlacklist(Biome.func_185362_a(bgb)) == 2)
/*     */       return; 
/* 233 */     float density = ModConfig.CONFIG_WORLD.oreDensity / 100.0F;
/*     */     
/* 235 */     if (world.field_73011_w.getDimension() == -1)
/*     */       return; 
/* 237 */     if (ModConfig.CONFIG_WORLD.generateCinnabar && (newGen || ModConfig.CONFIG_WORLD.regenCinnabar))
/*     */     {
/* 239 */       for (int i = 0; i < Math.round(18.0F * density); i++) {
/*     */         
/* 241 */         int randPosX = chunkX * 16 + 8 + MathHelper.func_76136_a(random, -6, 6);
/* 242 */         int randPosY = random.nextInt(world.func_72800_K() / 5);
/* 243 */         int randPosZ = chunkZ * 16 + 8 + MathHelper.func_76136_a(random, -6, 6);
/*     */         
/* 245 */         BlockPos pos = new BlockPos(randPosX, randPosY, randPosZ);
/* 246 */         IBlockState block = world.func_180495_p(pos);
/* 247 */         if (block.func_177230_c().isReplaceableOreGen(block, world, pos, this.predicate))
/*     */         {
/* 249 */           world.func_180501_a(pos, BlocksTC.oreCinnabar.func_176223_P(), 2);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 254 */     if (ModConfig.CONFIG_WORLD.generateQuartz && (newGen || ModConfig.CONFIG_WORLD.regenQuartz))
/*     */     {
/* 256 */       for (int i = 0; i < Math.round(18.0F * density); i++) {
/*     */         
/* 258 */         int randPosX = chunkX * 16 + 8 + MathHelper.func_76136_a(random, -6, 6);
/* 259 */         int randPosY = random.nextInt(world.func_72800_K() / 4);
/* 260 */         int randPosZ = chunkZ * 16 + 8 + MathHelper.func_76136_a(random, -6, 6);
/*     */         
/* 262 */         BlockPos pos = new BlockPos(randPosX, randPosY, randPosZ);
/* 263 */         IBlockState block = world.func_180495_p(pos);
/* 264 */         if (block.func_177230_c().isReplaceableOreGen(block, world, pos, this.predicate))
/*     */         {
/* 266 */           world.func_180501_a(pos, BlocksTC.oreQuartz.func_176223_P(), 2);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 271 */     if (ModConfig.CONFIG_WORLD.generateAmber && (newGen || ModConfig.CONFIG_WORLD.regenAmber))
/*     */     {
/* 273 */       for (int i = 0; i < Math.round(20.0F * density); i++) {
/*     */         
/* 275 */         int randPosX = chunkX * 16 + 8 + MathHelper.func_76136_a(random, -6, 6);
/* 276 */         int randPosZ = chunkZ * 16 + 8 + MathHelper.func_76136_a(random, -6, 6);
/*     */         
/* 278 */         int randPosY = world.func_175645_m(new BlockPos(randPosX, 0, randPosZ)).func_177956_o() - random.nextInt(25);
/*     */         
/* 280 */         BlockPos pos = new BlockPos(randPosX, randPosY, randPosZ);
/* 281 */         IBlockState block = world.func_180495_p(pos);
/* 282 */         if (block.func_177230_c().isReplaceableOreGen(block, world, pos, this.predicate))
/*     */         {
/* 284 */           world.func_180501_a(pos, BlocksTC.oreAmber.func_176223_P(), 2);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 289 */     if (ModConfig.CONFIG_WORLD.generateCrystals && (newGen || ModConfig.CONFIG_WORLD.regenCrystals)) {
/* 290 */       int t = 8;
/* 291 */       int maxCrystals = Math.round(64.0F * density);
/* 292 */       int cc = 0;
/*     */       
/* 294 */       if (world.field_73011_w.getDimension() == -1) t = 1;
/*     */ 
/*     */       
/* 297 */       for (int i = 0; i < Math.round(t * density); i++) {
/*     */         
/* 299 */         int randPosX = chunkX * 16 + 8 + MathHelper.func_76136_a(random, -6, 6);
/* 300 */         int randPosZ = chunkZ * 16 + 8 + MathHelper.func_76136_a(random, -6, 6);
/* 301 */         int randPosY = random.nextInt(Math.max(5, world.func_175645_m(new BlockPos(randPosX, 0, randPosZ)).func_177956_o() - 5));
/* 302 */         BlockPos bp = new BlockPos(randPosX, randPosY, randPosZ);
/* 303 */         int md = random.nextInt(6);
/* 304 */         if (random.nextInt(3) == 0) {
/* 305 */           Aspect tag = BiomeHandler.getRandomBiomeTag(Biome.func_185362_a(world.func_180494_b(bp)), random);
/* 306 */           if (tag == null) {
/* 307 */             md = random.nextInt(6);
/*     */           } else {
/* 309 */             md = ShardType.getMetaByAspect(tag);
/*     */           } 
/*     */         } 
/* 312 */         Block oreBlock = ShardType.byMetadata(md).getOre();
/* 313 */         for (int xx = -1; xx <= 1; xx++) {
/* 314 */           for (int yy = -1; yy <= 1; yy++) {
/* 315 */             for (int zz = -1; zz <= 1; zz++) {
/* 316 */               if (random.nextInt(3) != 0) {
/* 317 */                 IBlockState bs = world.func_180495_p(bp.func_177982_a(xx, yy, zz));
/* 318 */                 Material bm = bs.func_185904_a();
/* 319 */                 if (!bm.func_76224_d() && (world
/* 320 */                   .func_175623_d(bp.func_177982_a(xx, yy, zz)) || bs.func_177230_c().func_176200_f(world, bp.func_177982_a(xx, yy, zz))) && 
/* 321 */                   BlockUtils.isBlockTouching(world, bp.func_177982_a(xx, yy, zz), Material.field_151576_e, true))
/* 322 */                 { int amt = 1 + random.nextInt(3);
/* 323 */                   world.func_180501_a(bp.func_177982_a(xx, yy, zz), oreBlock.func_176203_a(amt), 0);
/* 324 */                   cc += amt; } 
/*     */               } 
/*     */             } 
/*     */           } 
/* 328 */         }  if (cc > maxCrystals) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void generateAll(World world, Random random, int chunkX, int chunkZ, boolean newGen) {
/* 336 */     boolean auraGen = false;
/* 337 */     int blacklist = BiomeHandler.getDimBlacklist(world.field_73011_w.getDimension());
/*     */     
/* 339 */     if (blacklist == -1 && ModConfig.CONFIG_WORLD.generateTrees && !world.func_72912_H().func_76067_t().func_77127_a().startsWith("flat") && (newGen || ModConfig.CONFIG_WORLD.regenTrees)) {
/* 340 */       generateVegetation(world, random, chunkX, chunkZ, newGen);
/*     */     }
/*     */     
/* 343 */     if (blacklist != 0 && blacklist != 2) generateOres(world, random, chunkX, chunkZ, newGen);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 352 */   private void generateNether(World world, Random random, int chunkX, int chunkZ, boolean newGen) { boolean auraGen = false; }
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
/*     */   public static boolean generateFlowers(World world, Random random, BlockPos pos, Block block, int md) {
/* 365 */     WorldGenCustomFlowers worldGenCustomFlowers = new WorldGenCustomFlowers(block, md);
/* 366 */     return worldGenCustomFlowers.func_180709_b(world, random, pos);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean generateGreatwood(World world, Random random, int chunkX, int chunkZ) {
/* 371 */     int x = chunkX * 16 + 8 + MathHelper.func_76136_a(random, -4, 4);
/* 372 */     int z = chunkZ * 16 + 8 + MathHelper.func_76136_a(random, -4, 4);
/* 373 */     BlockPos bp = world.func_175725_q(new BlockPos(x, 0, z));
/* 374 */     int bio = Biome.func_185362_a(world.func_180494_b(bp));
/* 375 */     if (BiomeHandler.getBiomeSupportsGreatwood(bio) > random.nextFloat()) {
/* 376 */       return (new WorldGenGreatwoodTrees(false, (random.nextInt(8) == 0))).func_180709_b(world, random, bp);
/*     */     }
/*     */     
/* 379 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean generateSilverwood(World world, Random random, int chunkX, int chunkZ) {
/* 384 */     int x = chunkX * 16 + 8 + MathHelper.func_76136_a(random, -4, 4);
/* 385 */     int z = chunkZ * 16 + 8 + MathHelper.func_76136_a(random, -4, 4);
/* 386 */     BlockPos bp = world.func_175725_q(new BlockPos(x, 0, z));
/* 387 */     Biome bio = world.func_180494_b(bp);
/* 388 */     int bi = Biome.func_185362_a(world.func_180494_b(bp));
/* 389 */     if (BiomeHandler.getBiomeSupportsGreatwood(bi) / 2.0F > random.nextFloat() || (
/* 390 */       !bio.equals(BiomeHandler.MAGICAL_FOREST) && BiomeDictionary.hasType(bio, BiomeDictionary.Type.MAGICAL)) || bio == 
/* 391 */       Biome.func_150568_d(18) || bio == Biome.func_150568_d(28)) {
/* 392 */       return (new WorldGenSilverwoodTrees(false, 7, 4)).func_180709_b(world, random, bp);
/*     */     }
/*     */     
/* 395 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\world\ThaumcraftWorldGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */