/*     */ package thaumcraft.common.items.casters.foci;
/*     */ 
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.casters.FocusEffect;
/*     */ import thaumcraft.api.casters.NodeSetting;
/*     */ import thaumcraft.api.casters.Trajectory;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.fx.particles.FXGeneric;
/*     */ import thaumcraft.common.config.ModConfig;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.tiles.misc.TileHole;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FocusEffectRift
/*     */   extends FocusEffect
/*     */ {
/*  33 */   public String getResearch() { return "FOCUSRIFT"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  38 */   public String getKey() { return "thaumcraft.RIFT"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   public Aspect getAspect() { return Aspect.ELDRITCH; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   public int getComplexity() { return 3 + getSettingValue("duration") / 2 + getSettingValue("depth") / 4; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean execute(RayTraceResult target, Trajectory trajectory, float finalPower, int num) {
/*  53 */     if (target.field_72313_a == RayTraceResult.Type.BLOCK) {
/*     */       
/*  55 */       if ((getPackage()).world.field_73011_w.getDimension() == ModConfig.CONFIG_WORLD.dimensionOuterId) {
/*  56 */         (getPackage()).world.func_184148_a(null, target
/*  57 */             .func_178782_a().func_177958_n() + 0.5D, target.func_178782_a().func_177956_o() + 0.5D, target.func_178782_a().func_177952_p() + 0.5D, SoundsTC.wandfail, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*     */         
/*  59 */         return false;
/*     */       } 
/*     */       
/*  62 */       float maxdis = getSettingValue("depth") * finalPower;
/*  63 */       int dur = 20 * getSettingValue("duration");
/*     */       
/*  65 */       int distance = 0;
/*  66 */       BlockPos pos = new BlockPos(target.func_178782_a());
/*  67 */       for (distance = 0; distance < maxdis; distance++) {
/*  68 */         IBlockState bi = (getPackage()).world.func_180495_p(pos);
/*  69 */         if (BlockUtils.isPortableHoleBlackListed(bi) || bi
/*  70 */           .func_177230_c() == Blocks.field_150357_h || bi.func_177230_c() == BlocksTC.hole || bi
/*  71 */           .func_177230_c().isAir(bi, (getPackage()).world, pos) || bi
/*  72 */           .func_185887_b((getPackage()).world, pos) == -1.0F) {
/*     */           break;
/*     */         }
/*  75 */         pos = pos.func_177972_a(target.field_178784_b.func_176734_d());
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  80 */       createHole((getPackage()).world, target.func_178782_a(), target.field_178784_b, (byte)Math.round((distance + 1)), dur);
/*     */       
/*  82 */       return true;
/*     */     } 
/*     */     
/*  85 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean createHole(World world, BlockPos pos, EnumFacing side, byte count, int max) {
/*  89 */     IBlockState bs = world.func_180495_p(pos);
/*  90 */     if (!world.field_72995_K && world.func_175625_s(pos) == null && 
/*  91 */       !BlockUtils.isPortableHoleBlackListed(bs) && bs
/*  92 */       .func_177230_c() != Blocks.field_150357_h && bs.func_177230_c() != BlocksTC.hole && (bs
/*  93 */       .func_177230_c().isAir(bs, world, pos) || !bs.func_177230_c().func_176196_c(world, pos)) && bs
/*  94 */       .func_185887_b(world, pos) != -1.0F) {
/*     */       
/*  96 */       if (world.func_175656_a(pos, BlocksTC.hole.func_176223_P())) {
/*  97 */         TileHole ts = (TileHole)world.func_175625_s(pos);
/*  98 */         ts.oldblock = bs;
/*  99 */         ts.countdownmax = (short)max;
/* 100 */         ts.count = count;
/* 101 */         ts.direction = side;
/* 102 */         ts.func_70296_d();
/*     */       } 
/* 104 */       return true;
/* 105 */     }  return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public NodeSetting[] createSettings() {
/* 110 */     int[] depth = { 8, 16, 24, 32 };
/* 111 */     String[] depthDesc = { "8", "16", "24", "32" };
/* 112 */     return new NodeSetting[] { new NodeSetting("depth", "focus.rift.depth", new NodeSetting.NodeSettingIntList(depth, depthDesc)), new NodeSetting("duration", "focus.common.duration", new NodeSetting.NodeSettingIntRange(2, 10)) };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void renderParticleFX(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 121 */     FXGeneric fb = new FXGeneric(world, posX, posY, posZ, motionX, motionY, motionZ);
/* 122 */     fb.func_187114_a(16 + world.field_73012_v.nextInt(16));
/* 123 */     fb.setParticles(384 + world.field_73012_v.nextInt(16), 1, 1);
/* 124 */     fb.setSlowDown(0.75D);
/* 125 */     fb.setAlphaF(new float[] { 1.0F, 0.0F });
/* 126 */     fb.setScale(new float[] { (float)(0.699999988079071D + world.field_73012_v.nextGaussian() * 0.30000001192092896D) });
/* 127 */     fb.func_70538_b(0.25F, 0.25F, 1.0F);
/* 128 */     fb.setRandomMovementScale(0.01F, 0.01F, 0.01F);
/* 129 */     ParticleEngine.addEffectWithDelay(world, fb, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 134 */   public void onCast(Entity caster) { caster.field_70170_p.func_184133_a(null, caster.func_180425_c().func_177984_a(), SoundEvents.field_190021_aL, SoundCategory.PLAYERS, 0.2F, 0.7F); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\casters\foci\FocusEffectRift.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */