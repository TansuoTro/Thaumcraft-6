/*     */ package thaumcraft.common.blocks.world.taint;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftMaterials;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.blocks.IBlockFacing;
/*     */ import thaumcraft.common.config.ModConfig;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintSeed;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ import thaumcraft.common.world.aura.AuraHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TaintHelper
/*     */ {
/*  27 */   private static ConcurrentHashMap<Integer, ArrayList<BlockPos>> taintSeeds = new ConcurrentHashMap();
/*     */   
/*     */   public static void addTaintSeed(World world, BlockPos pos) {
/*  30 */     ArrayList<BlockPos> locs = (ArrayList)taintSeeds.get(Integer.valueOf(world.field_73011_w.getDimension()));
/*  31 */     if (locs == null) {
/*  32 */       locs = new ArrayList<BlockPos>();
/*     */     }
/*  34 */     locs.add(pos);
/*  35 */     taintSeeds.put(Integer.valueOf(world.field_73011_w.getDimension()), locs);
/*     */   }
/*     */   
/*     */   public static void removeTaintSeed(World world, BlockPos pos) {
/*  39 */     ArrayList<BlockPos> locs = (ArrayList)taintSeeds.get(Integer.valueOf(world.field_73011_w.getDimension()));
/*  40 */     if (locs != null && !locs.isEmpty()) {
/*  41 */       locs.remove(pos);
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean isNearTaintSeed(World world, BlockPos pos) {
/*  46 */     double area = (ModConfig.CONFIG_WORLD.taintSpreadArea * ModConfig.CONFIG_WORLD.taintSpreadArea);
/*  47 */     ArrayList<BlockPos> locs = (ArrayList)taintSeeds.get(Integer.valueOf(world.field_73011_w.getDimension()));
/*  48 */     if (locs != null && !locs.isEmpty()) {
/*  49 */       for (BlockPos p : locs) {
/*  50 */         if (p.func_177951_i(pos) <= area) {
/*  51 */           if (EntityUtils.getEntitiesInRange(world, p, null, EntityTaintSeed.class, 1.0D).size() <= 0) {
/*  52 */             removeTaintSeed(world, p);
/*  53 */             return false;
/*     */           } 
/*  55 */           return true;
/*     */         } 
/*     */       } 
/*     */     }
/*  59 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isAtTaintSeedEdge(World world, BlockPos pos) {
/*  64 */     double area = (ModConfig.CONFIG_WORLD.taintSpreadArea * ModConfig.CONFIG_WORLD.taintSpreadArea);
/*  65 */     double fringe = ModConfig.CONFIG_WORLD.taintSpreadArea * 0.8D * ModConfig.CONFIG_WORLD.taintSpreadArea * 0.8D;
/*  66 */     ArrayList<BlockPos> locs = (ArrayList)taintSeeds.get(Integer.valueOf(world.field_73011_w.getDimension()));
/*  67 */     if (locs != null && !locs.isEmpty()) {
/*  68 */       for (BlockPos p : locs) {
/*  69 */         double d = p.func_177951_i(pos);
/*  70 */         if (d < area && d > fringe) {
/*  71 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/*  75 */     return false;
/*     */   }
/*     */ 
/*     */   
/*  79 */   public static void spreadFibres(World world, BlockPos pos) { spreadFibres(world, pos, false); }
/*     */ 
/*     */   
/*     */   public static void spreadFibres(World world, BlockPos pos, boolean ignore) {
/*  83 */     if (!ignore && ModConfig.CONFIG_MISC.wussMode)
/*     */       return; 
/*  85 */     float mod = 0.001F + AuraHandler.getFluxSaturation(world, pos) * 2.0F;
/*  86 */     if (!ignore && world.field_73012_v.nextFloat() > ModConfig.CONFIG_WORLD.taintSpreadRate / 100.0F * mod)
/*     */       return; 
/*  88 */     if (isNearTaintSeed(world, pos)) {
/*     */       
/*  90 */       int xx = pos.func_177958_n() + world.field_73012_v.nextInt(3) - 1;
/*  91 */       int yy = pos.func_177956_o() + world.field_73012_v.nextInt(3) - 1;
/*  92 */       int zz = pos.func_177952_p() + world.field_73012_v.nextInt(3) - 1;
/*     */       
/*  94 */       BlockPos t = new BlockPos(xx, yy, zz);
/*     */       
/*  96 */       if (t.equals(pos)) {
/*     */         return;
/*     */       }
/*  99 */       IBlockState bs = world.func_180495_p(t);
/* 100 */       Material bm = bs.func_177230_c().func_149688_o(bs);
/* 101 */       float bh = bs.func_177230_c().func_176195_g(bs, world, t);
/* 102 */       if (bh < 0.0F || bh > 10.0F)
/*     */         return; 
/* 104 */       if (!bs.func_177230_c().isLeaves(bs, world, t) && !bm.func_76224_d() && (world.func_175623_d(t) || bs.func_177230_c().func_176200_f(world, t) || bs
/* 105 */         .func_177230_c() instanceof net.minecraft.block.BlockFlower || bs.func_177230_c() instanceof net.minecraftforge.common.IPlantable) && 
/* 106 */         BlockUtils.isAdjacentToSolidBlock(world, t) && !BlockTaintFibre.isOnlyAdjacentToTaint(world, t)) {
/*     */         
/* 108 */         world.func_175656_a(t, BlocksTC.taintFibre.func_176223_P());
/* 109 */         world.func_175641_c(t, BlocksTC.taintFibre, 1, 0);
/* 110 */         AuraHelper.drainFlux(world, t, 0.01F, false);
/*     */         
/*     */         return;
/*     */       } 
/* 114 */       if (bs.func_177230_c().isLeaves(bs, world, t)) {
/* 115 */         EnumFacing face = null;
/* 116 */         if (world.field_73012_v.nextFloat() < 0.6D && (face = BlockUtils.getFaceBlockTouching(world, t, BlocksTC.taintLog)) != null) {
/* 117 */           world.func_175656_a(t, BlocksTC.taintFeature.func_176223_P().func_177226_a(IBlockFacing.FACING, face.func_176734_d()));
/*     */         } else {
/* 119 */           world.func_175656_a(t, BlocksTC.taintFibre.func_176223_P());
/* 120 */           world.func_175641_c(t, BlocksTC.taintFibre, 1, 0);
/* 121 */           AuraHelper.drainFlux(world, t, 0.01F, false);
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 127 */       if (BlockTaintFibre.isHemmedByTaint(world, t) && bs.func_185887_b(world, t) < 5.0F) {
/*     */         
/* 129 */         if (Utils.isWoodLog(world, t) && bs.func_185904_a() != ThaumcraftMaterials.MATERIAL_TAINT) {
/* 130 */           world.func_175656_a(t, BlocksTC.taintLog.func_176223_P()
/* 131 */               .func_177226_a(BlockTaintLog.AXIS, BlockUtils.getBlockAxis(world, t)));
/*     */           
/*     */           return;
/*     */         } 
/* 135 */         if (bs.func_177230_c() == Blocks.field_150419_aX || bs.func_177230_c() == Blocks.field_150420_aW || bm == Material.field_151572_C || bm == Material.field_151570_A || bm == Material.field_151589_v || bm == Material.field_151583_m || bm == Material.field_151575_d) {
/*     */           
/* 137 */           world.func_175656_a(t, BlocksTC.taintCrust.func_176223_P());
/* 138 */           world.func_175641_c(t, BlocksTC.taintCrust, 1, 0);
/* 139 */           AuraHelper.drainFlux(world, t, 0.01F, false);
/*     */           
/*     */           return;
/*     */         } 
/* 143 */         if (bm == Material.field_151595_p || bm == Material.field_151578_c || bm == Material.field_151577_b || bm == Material.field_151571_B) {
/* 144 */           world.func_175656_a(t, BlocksTC.taintSoil.func_176223_P());
/* 145 */           world.func_175641_c(t, BlocksTC.taintSoil, 1, 0);
/* 146 */           AuraHelper.drainFlux(world, t, 0.01F, false);
/*     */           
/*     */           return;
/*     */         } 
/* 150 */         if (bm == Material.field_151576_e) {
/* 151 */           world.func_175656_a(t, BlocksTC.taintRock.func_176223_P());
/* 152 */           world.func_175641_c(t, BlocksTC.taintRock, 1, 0);
/* 153 */           AuraHelper.drainFlux(world, t, 0.01F, false);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 158 */       if ((bs.func_177230_c() == BlocksTC.taintSoil || bs.func_177230_c() == BlocksTC.taintRock) && world.func_175623_d(t.func_177984_a()) && AuraHelper.getFlux(world, t) >= 5.0F && world.field_73012_v
/* 159 */         .nextFloat() < (ModConfig.CONFIG_WORLD.taintSpreadRate / 100.0F) * 0.33D && isAtTaintSeedEdge(world, t)) {
/* 160 */         EntityTaintSeed e = new EntityTaintSeed(world);
/* 161 */         e.func_70012_b((t.func_177958_n() + 0.5F), t.func_177984_a().func_177956_o(), (t.func_177952_p() + 0.5F), world.field_73012_v.nextInt(360), 0.0F);
/* 162 */         if (e.func_70601_bi()) {
/* 163 */           AuraHelper.drainFlux(world, t, 5.0F, false);
/* 164 */           world.func_72838_d(e);
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\world\taint\TaintHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */