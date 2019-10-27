/*     */ package thaumcraft.common.world.biomes;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockFlower;
/*     */ import net.minecraft.block.BlockTallGrass;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*     */ import net.minecraft.world.gen.feature.WorldGenBigMushroom;
/*     */ import net.minecraft.world.gen.feature.WorldGenBlockBlob;
/*     */ import net.minecraft.world.gen.feature.WorldGenTallGrass;
/*     */ import net.minecraft.world.gen.feature.WorldGenerator;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.config.ModConfig;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ import thaumcraft.common.world.objects.WorldGenBigMagicTree;
/*     */ import thaumcraft.common.world.objects.WorldGenSilverwoodTrees;
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
/*     */ public class BiomeGenMagicalForest
/*     */   extends Biome
/*     */ {
/*     */   protected WorldGenBigMagicTree bigTree;
/*  39 */   private static final WorldGenBlockBlob blobs = new WorldGenBlockBlob(Blocks.field_150341_Y, 0);
/*     */ 
/*     */   
/*     */   public BiomeGenMagicalForest(Biome.BiomeProperties par1) {
/*  43 */     super(par1);
/*  44 */     setRegistryName("thaumcraft", "magical_forest");
/*  45 */     this.bigTree = new WorldGenBigMagicTree(false);
/*     */     
/*  47 */     this.field_76762_K.add(new Biome.SpawnListEntry(net.minecraft.entity.passive.EntityWolf.class, 2, 1, 3));
/*  48 */     this.field_76762_K.add(new Biome.SpawnListEntry(net.minecraft.entity.passive.EntityHorse.class, 2, 1, 3));
/*     */     
/*  50 */     this.field_76761_J.add(new Biome.SpawnListEntry(net.minecraft.entity.monster.EntityWitch.class, 3, 1, 1));
/*  51 */     this.field_76761_J.add(new Biome.SpawnListEntry(net.minecraft.entity.monster.EntityEnderman.class, 3, 1, 1));
/*  52 */     this.field_76761_J.add(new Biome.SpawnListEntry(net.minecraft.entity.monster.EntityVex.class, 1, 1, 1));
/*     */     
/*  54 */     if (ModConfig.CONFIG_WORLD.allowSpawnPech) {
/*  55 */       this.field_76761_J.add(new Biome.SpawnListEntry(thaumcraft.common.entities.monster.EntityPech.class, 20, 1, 2));
/*     */     }
/*  57 */     if (ModConfig.CONFIG_WORLD.allowSpawnWisp) {
/*  58 */       this.field_76761_J.add(new Biome.SpawnListEntry(thaumcraft.common.entities.monster.EntityWisp.class, 20, 1, 2));
/*     */     }
/*  60 */     this.field_76760_I.field_76832_z = 2;
/*  61 */     this.field_76760_I.field_76802_A = 10;
/*  62 */     this.field_76760_I.field_76803_B = 12;
/*  63 */     this.field_76760_I.field_76833_y = 6;
/*  64 */     this.field_76760_I.field_76798_D = 6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   public WorldGenAbstractTree func_150567_a(Random par1Random) { if (par1Random
/*     */       
/*  76 */       .nextInt(12) == 0) {  } else {  }  return (par1Random.nextInt(18) == 0) ? new WorldGenSilverwoodTrees(false, 8, 5) : this.bigTree; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public WorldGenerator func_76730_b(Random par1Random) { return (par1Random.nextInt(4) == 0) ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*  92 */   public int func_180627_b(BlockPos p_180627_1_) { return ModConfig.CONFIG_GRAPHICS.blueBiome ? 6728396 : 5635969; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*  99 */   public int func_180625_c(BlockPos p_180625_1_) { return ModConfig.CONFIG_GRAPHICS.blueBiome ? 7851246 : 6750149; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   public int getWaterColorMultiplier() { return 30702; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180624_a(World world, Random random, BlockPos pos) {
/* 113 */     for (a = 0; a < 3; a++) {
/* 114 */       BlockPos pp = new BlockPos(pos);
/* 115 */       pp = pp.func_177982_a(4 + random.nextInt(8), 0, 4 + random.nextInt(8));
/* 116 */       pp = world.func_175645_m(pp);
/*     */       
/* 118 */       while (pp.func_177956_o() > 30 && world.func_180495_p(pp).func_177230_c() != Blocks.field_150349_c) {
/* 119 */         pp = pp.func_177977_b();
/*     */       }
/* 121 */       Block l1 = world.func_180495_p(pp).func_177230_c();
/* 122 */       if (l1 == Blocks.field_150349_c) {
/*     */         
/* 124 */         world.func_180501_a(pp, BlocksTC.grassAmbient.func_176223_P(), 2);
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     
/* 130 */     int k = random.nextInt(3);
/*     */     int l;
/* 132 */     for (l = 0; l < k; l++) {
/*     */       
/* 134 */       BlockPos p2 = new BlockPos(pos);
/* 135 */       p2 = p2.func_177982_a(random.nextInt(16) + 8, 0, random.nextInt(16) + 8);
/* 136 */       p2 = world.func_175645_m(p2);
/* 137 */       blobs.func_180709_b(world, random, p2);
/*     */     } 
/*     */     
/* 140 */     for (k = 0; k < 4; k++) {
/*     */       
/* 142 */       for (l = 0; l < 4; l++) {
/*     */         
/* 144 */         if (random.nextInt(40) == 0) {
/*     */           
/* 146 */           BlockPos p2 = new BlockPos(pos);
/* 147 */           p2 = p2.func_177982_a(k * 4 + 1 + 8 + random.nextInt(3), 0, l * 4 + 1 + 8 + random.nextInt(3));
/* 148 */           p2 = world.func_175645_m(p2);
/* 149 */           WorldGenBigMushroom worldgenbigmushroom = new WorldGenBigMushroom();
/* 150 */           worldgenbigmushroom.func_180709_b(world, random, p2);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*     */     try {
/* 156 */       super.func_180624_a(world, random, pos);
/* 157 */     } catch (Exception a) {
/*     */       Exception exception;
/*     */     } 
/*     */     
/* 161 */     for (int a = 0; a < 8; a++) {
/* 162 */       BlockPos p2 = new BlockPos(pos);
/* 163 */       p2 = p2.func_177982_a(random.nextInt(16), 0, random.nextInt(16));
/* 164 */       p2 = world.func_175645_m(p2);
/*     */       
/* 166 */       while (p2.func_177956_o() > 50 && world.func_180495_p(p2).func_177230_c() != Blocks.field_150349_c) {
/* 167 */         p2 = p2.func_177977_b();
/*     */       }
/* 169 */       Block l2 = world.func_180495_p(p2).func_177230_c();
/* 170 */       if (l2 == Blocks.field_150349_c && world.func_180495_p(p2.func_177984_a()).func_177230_c().func_176200_f(world, p2.func_177984_a()) && 
/* 171 */         isBlockAdjacentToWood(world, p2.func_177984_a()))
/*     */       {
/* 173 */         world.func_180501_a(p2.func_177984_a(), BlocksTC.vishroom.func_176223_P(), 2);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isBlockAdjacentToWood(IBlockAccess world, BlockPos pos) {
/* 180 */     int count = 0;
/* 181 */     for (int xx = -1; xx <= 1; xx++) {
/* 182 */       for (int yy = -1; yy <= 1; yy++) {
/* 183 */         for (int zz = -1; zz <= 1; zz++) {
/* 184 */           if ((xx != 0 || yy != 0 || zz != 0) && 
/* 185 */             Utils.isWoodLog(world, pos.func_177982_a(xx, yy, zz))) return true; 
/*     */         } 
/*     */       } 
/* 188 */     }  return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockFlower.EnumFlowerType func_180623_a(Random rand, BlockPos pos) {
/* 195 */     double d0 = MathHelper.func_151237_a((1.0D + field_180281_af.func_151601_a(pos.func_177958_n() / 48.0D, pos.func_177952_p() / 48.0D)) / 2.0D, 0.0D, 0.9999D);
/* 196 */     return BlockFlower.EnumFlowerType.values()[(int)(d0 * BlockFlower.EnumFlowerType.values().length)];
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\world\biomes\BiomeGenMagicalForest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */