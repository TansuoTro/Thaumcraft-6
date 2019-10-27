/*      */ package thaumcraft.client.fx;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.util.Random;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.state.IBlockState;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.particle.Particle;
/*      */ import net.minecraft.client.particle.ParticleLava;
/*      */ import net.minecraft.client.renderer.RenderGlobal;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.init.SoundEvents;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.util.EnumFacing;
/*      */ import net.minecraft.util.EnumParticleTypes;
/*      */ import net.minecraft.util.SoundCategory;
/*      */ import net.minecraft.util.math.AxisAlignedBB;
/*      */ import net.minecraft.util.math.BlockPos;
/*      */ import net.minecraft.util.math.MathHelper;
/*      */ import net.minecraft.util.math.Vec3d;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraftforge.fml.client.FMLClientHandler;
/*      */ import thaumcraft.api.items.ItemsTC;
/*      */ import thaumcraft.client.fx.beams.FXArc;
/*      */ import thaumcraft.client.fx.beams.FXBeamBore;
/*      */ import thaumcraft.client.fx.beams.FXBeamWand;
/*      */ import thaumcraft.client.fx.beams.FXBolt;
/*      */ import thaumcraft.client.fx.other.FXBlockWard;
/*      */ import thaumcraft.client.fx.other.FXBoreStream;
/*      */ import thaumcraft.client.fx.other.FXEssentiaStream;
/*      */ import thaumcraft.client.fx.other.FXShieldRunes;
/*      */ import thaumcraft.client.fx.other.FXVoidStream;
/*      */ import thaumcraft.client.fx.particles.FXBlockRunes;
/*      */ import thaumcraft.client.fx.particles.FXBoreParticles;
/*      */ import thaumcraft.client.fx.particles.FXBoreSparkle;
/*      */ import thaumcraft.client.fx.particles.FXBreakingFade;
/*      */ import thaumcraft.client.fx.particles.FXFireMote;
/*      */ import thaumcraft.client.fx.particles.FXGeneric;
/*      */ import thaumcraft.client.fx.particles.FXGenericGui;
/*      */ import thaumcraft.client.fx.particles.FXGenericP2E;
/*      */ import thaumcraft.client.fx.particles.FXPlane;
/*      */ import thaumcraft.client.fx.particles.FXSmokeSpiral;
/*      */ import thaumcraft.client.fx.particles.FXSwarm;
/*      */ import thaumcraft.client.fx.particles.FXVent;
/*      */ import thaumcraft.client.fx.particles.FXVent2;
/*      */ import thaumcraft.client.fx.particles.FXVisSparkle;
/*      */ import thaumcraft.client.fx.particles.FXWispEG;
/*      */ import thaumcraft.common.lib.SoundsTC;
/*      */ import thaumcraft.common.tiles.crafting.TileCrucible;
/*      */ 
/*      */ 
/*      */ public class FXDispatcher
/*      */ {
/*   57 */   public static FXDispatcher INSTANCE = new FXDispatcher();
/*      */ 
/*      */   
/*   60 */   public World getWorld() { return (FMLClientHandler.instance().getClient()).field_71441_e; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void drawFireMote(float x, float y, float z, float vx, float vy, float vz, float r, float g, float b, float alpha, float scale) {
/*   69 */     boolean bb = (getWorld()).field_73012_v.nextBoolean();
/*   70 */     FXFireMote glow = new FXFireMote(getWorld(), x, y, z, vx, vy, vz, r, g, b, bb ? (scale / 3.0F) : scale, bb ? 1 : 0);
/*   71 */     glow.func_82338_g(alpha);
/*   72 */     ParticleEngine.addEffect(getWorld(), glow);
/*      */   }
/*      */   
/*      */   public void drawAlumentum(float x, float y, float z, float vx, float vy, float vz, float r, float g, float b, float alpha, float scale) {
/*   76 */     FXFireMote glow = new FXFireMote(getWorld(), x, y, z, vx, vy, vz, r, g, b, scale, 1);
/*   77 */     glow.func_82338_g(alpha);
/*      */     
/*   79 */     ParticleEngine.addEffect(getWorld(), glow);
/*      */   }
/*      */   
/*      */   public void drawTaintParticles(float x, float y, float z, float vx, float vy, float vz, float scale) {
/*   83 */     FXGeneric fb = new FXGeneric(getWorld(), x, y, z, vx, vy, vz);
/*   84 */     fb.func_187114_a(80 + (getWorld()).field_73012_v.nextInt(20));
/*   85 */     fb.func_70538_b(0.4F + (getWorld()).field_73012_v.nextFloat() * 0.2F, 0.1F + (getWorld()).field_73012_v.nextFloat() * 0.3F, 0.5F + (getWorld()).field_73012_v.nextFloat() * 0.2F);
/*   86 */     fb.setAlphaF(new float[] { 0.75F, 0.0F });
/*   87 */     fb.setGridSize(16);
/*   88 */     fb.setParticles(57 + (getWorld()).field_73012_v.nextInt(3), 1, 1);
/*   89 */     fb.setScale(new float[] { scale, scale / 4.0F });
/*   90 */     fb.setLayer(1);
/*   91 */     fb.setSlowDown(0.9750000238418579D);
/*   92 */     fb.setGravity(0.2F);
/*   93 */     fb.setRotationSpeed((getWorld()).field_73012_v.nextFloat(), (getWorld()).field_73012_v.nextBoolean() ? -1.0F : 1.0F);
/*   94 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void drawLightningFlash(double x, double y, double z, float r, float g, float b, float alpha, float scale) {
/*  100 */     FXGeneric fb = new FXGeneric(getWorld(), x, y, z, 0.0D, 0.0D, 0.0D);
/*  101 */     fb.func_187114_a(5 + (getWorld()).field_73012_v.nextInt(5));
/*  102 */     fb.setGridSize(16);
/*  103 */     fb.func_70538_b(r, g, b);
/*  104 */     fb.setAlphaF(new float[] { alpha, 0.0F });
/*  105 */     fb.setParticles(108 + (getWorld()).field_73012_v.nextInt(4), 1, 1);
/*  106 */     fb.setScale(new float[] { scale });
/*  107 */     fb.setLayer(0);
/*  108 */     fb.setRotationSpeed((getWorld()).field_73012_v.nextFloat(), 0.0F);
/*  109 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */   
/*      */   public static class GenPart {
/*  113 */     public int grid = 64;
/*  114 */     public int age = 0;
/*  115 */     public float redStart = 1.0F;
/*  116 */     public float greenStart = 1.0F;
/*  117 */     public float blueStart = 1.0F;
/*  118 */     public float redEnd = 1.0F;
/*  119 */     public float greenEnd = 1.0F;
/*  120 */     public float blueEnd = 1.0F;
/*  121 */     public float[] alpha = { 1.0F };
/*  122 */     public float[] scale = { 1.0F };
/*  123 */     public float rotstart = 0.0F; public float rot;
/*      */     public boolean loop = false;
/*  125 */     public int partStart = 0;
/*  126 */     public int partNum = 1;
/*  127 */     public int partInc = 1;
/*  128 */     public int layer = 0;
/*  129 */     public double slowDown = 0.9800000190734863D;
/*  130 */     public float grav = 0.0F;
/*  131 */     public int delay = 0;
/*      */   }
/*      */   
/*      */   public void drawGenericParticles(double x, double y, double z, double mx, double my, double mz, GenPart part) {
/*  135 */     FXGeneric fb = new FXGeneric(getWorld(), x, y, z, mx, my, mz);
/*  136 */     fb.func_187114_a(part.age);
/*  137 */     fb.setRBGColorF(part.redStart, part.greenStart, part.blueStart, part.redEnd, part.greenEnd, part.blueEnd);
/*  138 */     fb.setAlphaF(part.alpha);
/*  139 */     fb.setLoop(part.loop);
/*  140 */     fb.setParticles(part.partStart, part.partNum, part.partInc);
/*  141 */     fb.setScale(part.scale);
/*  142 */     fb.setLayer(part.layer);
/*  143 */     fb.setRotationSpeed(part.rotstart, part.rot);
/*  144 */     fb.setSlowDown(part.slowDown);
/*  145 */     fb.setGravity(part.grav);
/*  146 */     fb.setGridSize(part.grid);
/*  147 */     ParticleEngine.addEffectWithDelay(getWorld(), fb, part.delay);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void drawGenericParticles(double x, double y, double z, double x2, double y2, double z2, float r, float g, float b, float alpha, boolean loop, int start, int num, int inc, int age, int delay, float scale, float rot, int layer) {
/*  157 */     FXGeneric fb = new FXGeneric(getWorld(), x, y, z, x2, y2, z2);
/*  158 */     fb.func_187114_a(age);
/*  159 */     fb.func_70538_b(r, g, b);
/*  160 */     fb.func_82338_g(alpha);
/*  161 */     fb.setLoop(loop);
/*  162 */     fb.setParticles(start, num, inc);
/*  163 */     fb.setScale(new float[] { scale });
/*  164 */     fb.setLayer(layer);
/*  165 */     fb.setRotationSpeed(rot);
/*  166 */     ParticleEngine.addEffectWithDelay(getWorld(), fb, delay);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void drawGenericParticles16(double x, double y, double z, double x2, double y2, double z2, float r, float g, float b, float alpha, boolean loop, int start, int num, int inc, int age, int delay, float scale, float rot, int layer) {
/*  176 */     FXGeneric fb = new FXGeneric(getWorld(), x, y, z, x2, y2, z2);
/*  177 */     fb.setGridSize(16);
/*  178 */     fb.func_187114_a(age);
/*  179 */     fb.func_70538_b(r, g, b);
/*  180 */     fb.func_82338_g(alpha);
/*  181 */     fb.setLoop(loop);
/*  182 */     fb.setParticles(start, num, inc);
/*  183 */     fb.setScale(new float[] { scale });
/*  184 */     fb.setLayer(layer);
/*  185 */     fb.setRotationSpeed(rot);
/*  186 */     ParticleEngine.addEffectWithDelay(getWorld(), fb, delay);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void drawLevitatorParticles(double x, double y, double z, double x2, double y2, double z2) {
/*  198 */     FXGeneric fb = new FXGeneric(getWorld(), x, y, z, x2, y2, z2);
/*  199 */     fb.func_187114_a(200 + (getWorld()).field_73012_v.nextInt(100));
/*  200 */     fb.func_70538_b(0.5F, 0.5F, 0.2F);
/*  201 */     fb.setAlphaF(new float[] { 0.3F, 0.0F });
/*  202 */     fb.setGridSize(16);
/*  203 */     fb.setParticles(56, 1, 1);
/*  204 */     fb.setScale(new float[] { 2.0F, 5.0F });
/*  205 */     fb.setLayer(0);
/*  206 */     fb.setSlowDown(1.0D);
/*  207 */     fb.setRotationSpeed((getWorld()).field_73012_v.nextFloat(), (getWorld()).field_73012_v.nextBoolean() ? -1.0F : 1.0F);
/*  208 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */   
/*      */   public void drawStabilizerParticles(double x, double y, double z, double x2, double y2, double z2, int life) {
/*  212 */     FXGeneric fb = new FXGeneric(getWorld(), x, y, z, x2, y2, z2);
/*  213 */     fb.func_187114_a(life + (getWorld()).field_73012_v.nextInt(life));
/*  214 */     fb.func_70538_b(0.5F, 0.2F, 0.5F);
/*  215 */     fb.setAlphaF(new float[] { 0.3F, 0.0F });
/*  216 */     fb.setGridSize(16);
/*  217 */     fb.setParticles(72 + (getWorld()).field_73012_v.nextInt(4), 1, 1);
/*  218 */     fb.setScale(new float[] { 1.0F, 10.0F });
/*  219 */     fb.setLayer(0);
/*  220 */     fb.setSlowDown(1.01D);
/*  221 */     fb.setRotationSpeed((getWorld()).field_73012_v.nextFloat(), (getWorld()).field_73012_v.nextBoolean() ? -1.0F : 1.0F);
/*  222 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */   
/*      */   public void drawGolemFlyParticles(double x, double y, double z, double x2, double y2, double z2) {
/*      */     try {
/*  227 */       FXGeneric fb = new FXGeneric(getWorld(), x, y, z, x2, y2, z2);
/*  228 */       fb.func_187114_a(20 + (getWorld()).field_73012_v.nextInt(5));
/*  229 */       fb.setAlphaF(new float[] { 0.3F, 0.0F });
/*  230 */       fb.setGridSize(16);
/*  231 */       fb.setParticles(56, 1, 1);
/*  232 */       fb.setScale(new float[] { 1.5F, 3.0F, 8.0F });
/*  233 */       fb.setLayer(0);
/*  234 */       fb.setSlowDown(1.0D);
/*  235 */       fb.setWind(0.001D);
/*      */       
/*  237 */       fb.setRotationSpeed((getWorld()).field_73012_v.nextFloat(), (getWorld()).field_73012_v.nextBoolean() ? -1.0F : 1.0F);
/*  238 */       ParticleEngine.addEffect(getWorld(), fb);
/*  239 */     } catch (Exception exception) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void drawPollutionParticles(BlockPos p) {
/*  249 */     float x = p.func_177958_n() + 0.2F + (getWorld()).field_73012_v.nextFloat() * 0.6F;
/*  250 */     float y = p.func_177956_o() + 0.2F + (getWorld()).field_73012_v.nextFloat() * 0.6F;
/*  251 */     float z = p.func_177952_p() + 0.2F + (getWorld()).field_73012_v.nextFloat() * 0.6F;
/*      */ 
/*      */ 
/*      */     
/*  255 */     FXGeneric fb = new FXGeneric(getWorld(), x, y, z, ((getWorld()).field_73012_v.nextFloat() - (getWorld()).field_73012_v.nextFloat()) * 0.005D, 0.02D, ((getWorld()).field_73012_v.nextFloat() - (getWorld()).field_73012_v.nextFloat()) * 0.005D);
/*  256 */     fb.func_187114_a(100 + (getWorld()).field_73012_v.nextInt(60));
/*  257 */     fb.func_70538_b(1.0F, 0.3F, 0.9F);
/*  258 */     fb.setAlphaF(new float[] { 0.5F, 0.0F });
/*  259 */     fb.setGridSize(16);
/*  260 */     fb.setParticles(56, 1, 1);
/*  261 */     fb.setScale(new float[] { 2.0F, 5.0F });
/*  262 */     fb.setLayer(1);
/*  263 */     fb.setSlowDown(1.0D);
/*  264 */     fb.setWind(0.001D);
/*  265 */     fb.setRotationSpeed((getWorld()).field_73012_v.nextFloat(), (getWorld()).field_73012_v.nextBoolean() ? -1.0F : 1.0F);
/*  266 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void drawBlockSparkles(BlockPos p, Vec3d start) {
/*  283 */     AxisAlignedBB bs = getWorld().func_180495_p(p).func_185900_c(getWorld(), p);
/*  284 */     bs.func_72314_b(0.1D, 0.1D, 0.1D);
/*  285 */     int num = (int)(bs.func_72320_b() * 20.0D);
/*      */     
/*  287 */     for (EnumFacing face : EnumFacing.values()) {
/*  288 */       IBlockState state = getWorld().func_180495_p(p.func_177972_a(face));
/*  289 */       if (!state.func_185914_p() && !state.isSideSolid(getWorld(), p.func_177972_a(face), face.func_176734_d())) {
/*  290 */         boolean rx = (face.func_82601_c() == 0);
/*  291 */         boolean ry = (face.func_96559_d() == 0);
/*  292 */         boolean rz = (face.func_82599_e() == 0);
/*  293 */         double mx = 0.5D + face.func_82601_c() * 0.51D;
/*  294 */         double my = 0.5D + face.func_96559_d() * 0.51D;
/*  295 */         double mz = 0.5D + face.func_82599_e() * 0.51D;
/*      */         
/*  297 */         for (int a = 0; a < num * 2; a++) {
/*  298 */           double x = mx;
/*  299 */           double y = my;
/*  300 */           double z = mz;
/*  301 */           if (rx) x += (getWorld()).field_73012_v.nextGaussian() * 0.6D; 
/*  302 */           if (ry) y += (getWorld()).field_73012_v.nextGaussian() * 0.6D; 
/*  303 */           if (rz) z += (getWorld()).field_73012_v.nextGaussian() * 0.6D; 
/*  304 */           x = MathHelper.func_151237_a(x, bs.field_72340_a, bs.field_72336_d);
/*  305 */           y = MathHelper.func_151237_a(y, bs.field_72338_b, bs.field_72337_e);
/*  306 */           z = MathHelper.func_151237_a(z, bs.field_72339_c, bs.field_72334_f);
/*  307 */           float r = MathHelper.func_76136_a((getWorld()).field_73012_v, 255, 255) / 255.0F;
/*  308 */           float g = MathHelper.func_76136_a((getWorld()).field_73012_v, 189, 255) / 255.0F;
/*  309 */           float b = MathHelper.func_76136_a((getWorld()).field_73012_v, 64, 255) / 255.0F;
/*  310 */           Vec3d v1 = new Vec3d(p.func_177958_n() + x, p.func_177956_o() + y, p.func_177952_p() + z);
/*  311 */           double delay = (getWorld()).field_73012_v.nextInt(5) + v1.func_72438_d(start) * 16.0D;
/*  312 */           drawSimpleSparkle((getWorld()).field_73012_v, p.func_177958_n() + x, p.func_177956_o() + y, p.func_177952_p() + z, 0.0D, 0.0025D, 0.0D, 0.4F + 
/*      */               
/*  314 */               (float)(getWorld()).field_73012_v.nextGaussian() * 0.1F, r, g, b, (int)delay, 1.0F, 0.01F, 16);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void drawLineSparkle(Random rand, double x, double y, double z, double x2, double y2, double z2, float scale, float r, float g, float b, int delay, float decay, float grav, int baseAge) {
/*  324 */     boolean sp = (rand.nextFloat() < 0.2D);
/*  325 */     FXGeneric fb = new FXGeneric(getWorld(), x, y, z, x2, y2, z2);
/*  326 */     int age = baseAge * 4 + (getWorld()).field_73012_v.nextInt(baseAge);
/*  327 */     fb.func_187114_a(age);
/*  328 */     fb.func_70538_b(r, g, b);
/*  329 */     fb.setAlphaF(new float[] { 0.0F, 1.0F, 0.0F });
/*  330 */     fb.setParticles(sp ? 320 : 512, 16, 1);
/*  331 */     fb.setLoop(true);
/*  332 */     fb.setGravity(grav);
/*  333 */     fb.setScale(new float[] { scale, scale * 2.0F, scale });
/*  334 */     fb.setLayer(0);
/*  335 */     fb.setSlowDown(decay);
/*  336 */     fb.setRandomMovementScale(5.0E-5F, 0.0F, 5.0E-5F);
/*  337 */     ParticleEngine.addEffectWithDelay(getWorld(), fb, delay);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void drawSimpleSparkle(Random rand, double x, double y, double z, double x2, double y2, double z2, float scale, float r, float g, float b, int delay, float decay, float grav, int baseAge) {
/*  343 */     boolean sp = (rand.nextFloat() < 0.2D);
/*  344 */     FXGeneric fb = new FXGeneric(getWorld(), x, y, z, x2, y2, z2);
/*  345 */     int age = baseAge * 4 + (getWorld()).field_73012_v.nextInt(baseAge);
/*  346 */     fb.func_187114_a(age);
/*  347 */     fb.func_70538_b(r, g, b);
/*  348 */     float[] alphas = new float[6 + rand.nextInt(age / 3)];
/*  349 */     for (int a = 1; a < alphas.length - 1; ) { alphas[a] = rand.nextFloat(); a++; }
/*  350 */      fb.setAlphaF(alphas);
/*  351 */     fb.setParticles(sp ? 320 : 512, 16, 1);
/*  352 */     fb.setLoop(true);
/*  353 */     fb.setGravity(grav);
/*  354 */     fb.setScale(new float[] { scale, scale * 2.0F });
/*  355 */     fb.setLayer(0);
/*  356 */     fb.setSlowDown(decay);
/*  357 */     fb.setRandomMovementScale(5.0E-4F, 0.001F, 5.0E-4F);
/*  358 */     fb.setWind(5.0E-4D);
/*  359 */     ParticleEngine.addEffectWithDelay(getWorld(), fb, delay);
/*      */   }
/*      */ 
/*      */   
/*      */   public void drawSimpleSparkleGui(Random rand, double x, double y, double x2, double y2, float scale, float r, float g, float b, int delay, float decay, float grav) {
/*  364 */     boolean sp = (rand.nextFloat() < 0.2D);
/*  365 */     FXGenericGui fb = new FXGenericGui(getWorld(), x, y, 0.0D, x2, y2, 0.0D);
/*  366 */     fb.func_187114_a(32 + (getWorld()).field_73012_v.nextInt(8));
/*  367 */     fb.func_70538_b(r, g, b);
/*  368 */     fb.setAlphaF(new float[] { 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F });
/*  369 */     fb.setParticles(sp ? 320 : 512, 16, 1);
/*  370 */     fb.setLoop(true);
/*  371 */     fb.setGravity(grav);
/*  372 */     fb.setScale(new float[] { scale, scale * 2.0F });
/*  373 */     fb.setNoClip(false);
/*  374 */     fb.setLayer(4);
/*  375 */     fb.setSlowDown(decay);
/*  376 */     fb.setRandomMovementScale(0.025F, 0.025F, 0.0F);
/*  377 */     ParticleEngine.addEffectWithDelay(getWorld(), fb, delay);
/*      */   }
/*      */ 
/*      */   
/*      */   public void drawBlockMistParticles(BlockPos p, int c) {
/*  382 */     AxisAlignedBB bs = getWorld().func_180495_p(p).func_185900_c(getWorld(), p);
/*  383 */     Color color = new Color(c);
/*  384 */     for (int a = 0; a < 8; a++) {
/*  385 */       double x = p.func_177958_n() + bs.field_72340_a + (getWorld()).field_73012_v.nextFloat() * (bs.field_72336_d - bs.field_72340_a);
/*  386 */       double y = p.func_177956_o() + bs.field_72338_b + (getWorld()).field_73012_v.nextFloat() * (bs.field_72337_e - bs.field_72338_b);
/*  387 */       double z = p.func_177952_p() + bs.field_72339_c + (getWorld()).field_73012_v.nextFloat() * (bs.field_72334_f - bs.field_72339_c);
/*      */       
/*  389 */       FXGeneric fb = new FXGeneric(getWorld(), x, y, z, (getWorld()).field_73012_v.nextGaussian() * 0.01D, (getWorld()).field_73012_v.nextFloat() * 0.075D, (getWorld()).field_73012_v.nextGaussian() * 0.01D);
/*  390 */       fb.func_187114_a(50 + (getWorld()).field_73012_v.nextInt(25));
/*  391 */       fb.func_70538_b(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F);
/*  392 */       fb.setAlphaF(new float[] { 0.0F, 0.5F, 0.4F, 0.3F, 0.2F, 0.1F, 0.0F });
/*  393 */       fb.setGridSize(16);
/*  394 */       fb.setParticles(56, 1, 1);
/*  395 */       fb.setScale(new float[] { 5.0F, 1.0F });
/*  396 */       fb.setLayer(0);
/*  397 */       fb.setSlowDown(1.0D);
/*  398 */       fb.setGravity(0.1F);
/*  399 */       fb.setWind(0.001D);
/*  400 */       fb.setRotationSpeed((getWorld()).field_73012_v.nextFloat(), (getWorld()).field_73012_v.nextBoolean() ? -1.0F : 1.0F);
/*  401 */       ParticleEngine.addEffect(getWorld(), fb);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void drawFocusCloudParticle(double x, double y, double z, double mx, double my, double mz, int c) {
/*  406 */     Color color = new Color(c);
/*  407 */     FXGeneric fb = new FXGeneric(getWorld(), x, y, z, mx, my, mz);
/*  408 */     fb.func_187114_a(20 + (getWorld()).field_73012_v.nextInt(10));
/*  409 */     fb.func_70538_b(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F);
/*  410 */     fb.setAlphaF(new float[] { 0.0F, 0.66F, 0.0F });
/*  411 */     fb.setGridSize(16);
/*  412 */     fb.setParticles(56 + (getWorld()).field_73012_v.nextInt(4), 1, 1);
/*  413 */     fb.setScale(new float[] { 5.0F + (getWorld()).field_73012_v.nextFloat(), 10.0F + (getWorld()).field_73012_v.nextFloat() });
/*  414 */     fb.setLayer(0);
/*  415 */     fb.setSlowDown(0.99D);
/*      */     
/*  417 */     fb.setWind(0.001D);
/*  418 */     fb.setRotationSpeed((getWorld()).field_73012_v.nextFloat(), (getWorld()).field_73012_v.nextBoolean() ? -0.25F : 0.25F);
/*  419 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */   
/*      */   public void drawWispyMotesOnBlock(BlockPos pp, int age, float grav) {
/*  423 */     drawWispyMotes((pp
/*  424 */         .func_177958_n() + (getWorld()).field_73012_v.nextFloat()), pp.func_177956_o(), (pp.func_177952_p() + (getWorld()).field_73012_v.nextFloat()), 0.0D, 0.0D, 0.0D, age, 0.4F + 
/*      */         
/*  426 */         (getWorld()).field_73012_v.nextFloat() * 0.6F, 0.6F + 
/*  427 */         (getWorld()).field_73012_v.nextFloat() * 0.4F, 0.6F + 
/*  428 */         (getWorld()).field_73012_v.nextFloat() * 0.4F, grav);
/*      */   }
/*      */ 
/*      */   
/*      */   public void drawWispyMotes(double d, double e, double f, double vx, double vy, double vz, int age, float grav) {
/*  433 */     drawWispyMotes(d, e, f, vx, vy, vz, age, 0.25F + (getWorld()).field_73012_v.nextFloat() * 0.75F, 0.25F + 
/*  434 */         (getWorld()).field_73012_v.nextFloat() * 0.75F, 0.25F + (getWorld()).field_73012_v.nextFloat() * 0.75F, grav);
/*      */   }
/*      */   
/*      */   public void drawWispyMotes(double d, double e, double f, double vx, double vy, double vz, int age, float r, float g, float b, float grav) {
/*  438 */     FXGeneric fb = new FXGeneric(getWorld(), d, e, f, vx, vy, vz);
/*  439 */     fb.func_187114_a((int)(age + (age / 2) * (getWorld()).field_73012_v.nextFloat()));
/*  440 */     fb.func_70538_b(r, g, b);
/*  441 */     fb.setAlphaF(new float[] { 0.0F, 0.6F, 0.6F, 0.0F });
/*  442 */     fb.setGridSize(64);
/*  443 */     fb.setParticles(512, 16, 1);
/*  444 */     fb.setScale(new float[] { 1.0F, 0.5F });
/*  445 */     fb.setLoop(true);
/*  446 */     fb.setWind(0.001D);
/*  447 */     fb.setGravity(grav);
/*  448 */     fb.setRandomMovementScale(0.0025F, 0.0F, 0.0025F);
/*  449 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */   
/*      */   public void drawBlockMistParticlesFlat(BlockPos p, int c) {
/*  453 */     Block bs = getWorld().func_180495_p(p).func_177230_c();
/*  454 */     Color color = new Color(c);
/*  455 */     for (int a = 0; a < 6; a++) {
/*  456 */       double x = (p.func_177958_n() + (getWorld()).field_73012_v.nextFloat());
/*  457 */       double y = (p.func_177956_o() + (getWorld()).field_73012_v.nextFloat() * 0.125F);
/*  458 */       double z = (p.func_177952_p() + (getWorld()).field_73012_v.nextFloat());
/*      */ 
/*      */ 
/*      */       
/*  462 */       FXGeneric fb = new FXGeneric(getWorld(), x, y, z, ((getWorld()).field_73012_v.nextFloat() - (getWorld()).field_73012_v.nextFloat()) * 0.005D, 0.005D, ((getWorld()).field_73012_v.nextFloat() - (getWorld()).field_73012_v.nextFloat()) * 0.005D);
/*  463 */       fb.func_187114_a(400 + (getWorld()).field_73012_v.nextInt(100));
/*  464 */       fb.func_70538_b(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F);
/*  465 */       fb.setAlphaF(new float[] { 1.0F, 0.0F });
/*  466 */       fb.setGridSize(8);
/*  467 */       fb.setParticles(24, 1, 1);
/*  468 */       fb.setScale(new float[] { 2.0F, 5.0F });
/*  469 */       fb.setLayer(0);
/*  470 */       fb.setSlowDown(1.0D);
/*  471 */       fb.setWind(0.001D);
/*  472 */       fb.setRotationSpeed((getWorld()).field_73012_v.nextFloat(), (getWorld()).field_73012_v.nextBoolean() ? -1.0F : 1.0F);
/*  473 */       ParticleEngine.addEffect(getWorld(), fb);
/*      */     } 
/*      */   }
/*      */   
/*  477 */   static int q = 0;
/*      */   public void crucibleBubble(float x, float y, float z, float cr, float cg, float cb) {
/*  479 */     FXGeneric fb = new FXGeneric(getWorld(), x, y, z, 0.0D, 0.0D, 0.0D);
/*  480 */     fb.func_187114_a(15 + (getWorld()).field_73012_v.nextInt(10));
/*  481 */     fb.setScale(new float[] { (getWorld()).field_73012_v.nextFloat() * 0.3F + 0.3F });
/*  482 */     fb.func_70538_b(cr, cg, cb);
/*  483 */     fb.setRandomMovementScale(0.002F, 0.002F, 0.002F);
/*  484 */     fb.setGravity(-0.001F);
/*  485 */     fb.setParticle(64);
/*  486 */     fb.setFinalFrames(new int[] { 65, 66, 66 });
/*  487 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */   
/*      */   public void crucibleBoil(BlockPos pos, TileCrucible tile, int j) {
/*  491 */     for (int a = 0; a < 2; a++) {
/*      */ 
/*      */ 
/*      */       
/*  495 */       FXGeneric fb = new FXGeneric(getWorld(), (pos.func_177958_n() + 0.2F + (getWorld()).field_73012_v.nextFloat() * 0.6F), (pos.func_177956_o() + 0.1F + tile.getFluidHeight()), (pos.func_177952_p() + 0.2F + (getWorld()).field_73012_v.nextFloat() * 0.6F), 0.0D, 0.002D, 0.0D);
/*  496 */       fb.func_187114_a((int)(7.0D + 8.0D / (Math.random() * 0.8D + 0.2D)));
/*  497 */       fb.setScale(new float[] { (getWorld()).field_73012_v.nextFloat() * 0.3F + 0.2F });
/*  498 */       if (tile.aspects.size() == 0) {
/*  499 */         fb.func_70538_b(1.0F, 1.0F, 1.0F);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  504 */         Color color = new Color(tile.aspects.getAspects()[(getWorld()).field_73012_v.nextInt(tile.aspects.getAspects().length)].getColor());
/*  505 */         fb.func_70538_b(color.getRed() / 255.0F, color.getGreen() / 255.0F, color
/*  506 */             .getBlue() / 255.0F);
/*      */       } 
/*  508 */       fb.setRandomMovementScale(0.001F, 0.001F, 0.001F);
/*  509 */       fb.setGravity(-0.025F * j);
/*  510 */       fb.setParticle(64);
/*  511 */       fb.setFinalFrames(new int[] { 65, 66 });
/*  512 */       ParticleEngine.addEffect(getWorld(), fb);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void crucibleFroth(float x, float y, float z) {
/*  517 */     FXGeneric fb = new FXGeneric(getWorld(), x, y, z, 0.0D, 0.0D, 0.0D);
/*  518 */     fb.func_187114_a(4 + (getWorld()).field_73012_v.nextInt(3));
/*  519 */     fb.setScale(new float[] { (getWorld()).field_73012_v.nextFloat() * 0.2F + 0.2F });
/*  520 */     fb.func_70538_b(0.5F, 0.5F, 0.7F);
/*  521 */     fb.setRandomMovementScale(0.001F, 0.001F, 0.001F);
/*  522 */     fb.setGravity(0.1F);
/*  523 */     fb.setParticle(64);
/*  524 */     fb.setFinalFrames(new int[] { 65, 66 });
/*  525 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */   
/*      */   public void crucibleFrothDown(float x, float y, float z) {
/*  529 */     FXGeneric fb = new FXGeneric(getWorld(), x, y, z, 0.0D, 0.0D, 0.0D);
/*  530 */     fb.func_187114_a(12 + (getWorld()).field_73012_v.nextInt(12));
/*  531 */     fb.setScale(new float[] { (getWorld()).field_73012_v.nextFloat() * 0.2F + 0.4F });
/*  532 */     fb.func_70538_b(0.25F, 0.0F, 0.75F);
/*  533 */     fb.func_82338_g(0.8F);
/*  534 */     fb.setRandomMovementScale(0.001F, 0.001F, 0.001F);
/*  535 */     fb.setGravity(0.05F);
/*  536 */     fb.setNoClip(false);
/*  537 */     fb.setParticle(73);
/*  538 */     fb.setFinalFrames(new int[] { 65, 66 });
/*  539 */     fb.setLayer(1);
/*  540 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */ 
/*      */   
/*  544 */   public void drawBamf(BlockPos p, boolean sound, boolean flair, EnumFacing side) { drawBamf(p.func_177958_n() + 0.5D, p.func_177956_o() + 0.5D, p.func_177952_p() + 0.5D, sound, flair, side); }
/*      */ 
/*      */   
/*      */   public void drawPedestalShield(BlockPos pos) {
/*  548 */     FXShieldRunes fb = new FXShieldRunes(getWorld(), pos.func_177958_n() + 0.5D, (pos.func_177956_o() + 1), pos.func_177952_p() + 0.5D, null, 8, 0.0F, 90.0F);
/*  549 */     (FMLClientHandler.instance().getClient()).field_71452_i.func_78873_a(fb);
/*      */   }
/*      */ 
/*      */   
/*  553 */   public void drawBamf(BlockPos p, float r, float g, float b, boolean sound, boolean flair, EnumFacing side) { drawBamf(p.func_177958_n() + 0.5D, p.func_177956_o() + 0.5D, p.func_177952_p() + 0.5D, r, g, b, sound, flair, side); }
/*      */ 
/*      */ 
/*      */   
/*  557 */   public void drawBamf(BlockPos p, int color, boolean sound, boolean flair, EnumFacing side) { drawBamf(p.func_177958_n() + 0.5D, p.func_177956_o() + 0.5D, p.func_177952_p() + 0.5D, color, sound, flair, side); }
/*      */ 
/*      */   
/*      */   public void drawBamf(double x, double y, double z, int color, boolean sound, boolean flair, EnumFacing side) {
/*  561 */     Color c = new Color(color);
/*  562 */     float r = c.getRed() / 255.0F;
/*  563 */     float g = c.getGreen() / 255.0F;
/*  564 */     float b = c.getBlue() / 255.0F;
/*  565 */     drawBamf(x, y, z, r, g, b, sound, flair, side);
/*      */   }
/*      */ 
/*      */   
/*  569 */   public void drawBamf(double x, double y, double z, boolean sound, boolean flair, EnumFacing side) { drawBamf(x, y, z, 0.5F, 0.1F, 0.6F, sound, flair, side); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void drawBamf(double x, double y, double z, float r, float g, float b, boolean sound, boolean flair, EnumFacing side) {
/*  575 */     if (sound) {
/*  576 */       getWorld().func_184134_a(x, y, z, SoundsTC.poof, SoundCategory.BLOCKS, 0.4F, 1.0F + 
/*  577 */           (float)(getWorld()).field_73012_v.nextGaussian() * 0.05F, false);
/*      */     }
/*      */     
/*  580 */     for (int a = 0; a < 6 + (getWorld()).field_73012_v.nextInt(3) + 2; a++) {
/*  581 */       double vx = ((0.05F + (getWorld()).field_73012_v.nextFloat() * 0.05F) * ((getWorld()).field_73012_v.nextBoolean() ? -1 : true));
/*  582 */       double vy = ((0.05F + (getWorld()).field_73012_v.nextFloat() * 0.05F) * ((getWorld()).field_73012_v.nextBoolean() ? -1 : true));
/*  583 */       double vz = ((0.05F + (getWorld()).field_73012_v.nextFloat() * 0.05F) * ((getWorld()).field_73012_v.nextBoolean() ? -1 : true));
/*  584 */       if (side != null) {
/*  585 */         vx += (side.func_82601_c() * 0.1F);
/*  586 */         vy += (side.func_96559_d() * 0.1F);
/*  587 */         vz += (side.func_82599_e() * 0.1F);
/*      */       } 
/*      */       
/*  590 */       FXGeneric fb2 = new FXGeneric(getWorld(), x + vx * 2.0D, y + vy * 2.0D, z + vz * 2.0D, vx / 2.0D, vy / 2.0D, vz / 2.0D);
/*  591 */       fb2.func_187114_a(20 + (getWorld()).field_73012_v.nextInt(15));
/*  592 */       fb2.func_70538_b(
/*  593 */           MathHelper.func_76131_a(r * (1.0F + (float)(getWorld()).field_73012_v.nextGaussian() * 0.1F), 0.0F, 1.0F), 
/*  594 */           MathHelper.func_76131_a(g * (1.0F + (float)(getWorld()).field_73012_v.nextGaussian() * 0.1F), 0.0F, 1.0F), 
/*  595 */           MathHelper.func_76131_a(b * (1.0F + (float)(getWorld()).field_73012_v.nextGaussian() * 0.1F), 0.0F, 1.0F));
/*  596 */       fb2.setAlphaF(new float[] { 1.0F, 0.1F });
/*  597 */       fb2.setGridSize(16);
/*  598 */       fb2.setParticles(123, 5, 1);
/*  599 */       fb2.setScale(new float[] { 3.0F, 4.0F + (getWorld()).field_73012_v.nextFloat() * 3.0F });
/*  600 */       fb2.setLayer(1);
/*  601 */       fb2.setSlowDown(0.7D);
/*  602 */       fb2.setRotationSpeed((getWorld()).field_73012_v.nextFloat(), (getWorld()).field_73012_v.nextBoolean() ? -1.0F : 1.0F);
/*  603 */       ParticleEngine.addEffect(getWorld(), fb2);
/*      */     } 
/*      */     
/*  606 */     if (flair) {
/*      */       
/*  608 */       for (int a = 0; a < 2 + (getWorld()).field_73012_v.nextInt(3); a++) {
/*  609 */         double vx = ((0.025F + (getWorld()).field_73012_v.nextFloat() * 0.025F) * ((getWorld()).field_73012_v.nextBoolean() ? -1 : true));
/*  610 */         double vy = ((0.025F + (getWorld()).field_73012_v.nextFloat() * 0.025F) * ((getWorld()).field_73012_v.nextBoolean() ? -1 : true));
/*  611 */         double vz = ((0.025F + (getWorld()).field_73012_v.nextFloat() * 0.025F) * ((getWorld()).field_73012_v.nextBoolean() ? -1 : true));
/*  612 */         drawWispyMotes(x + vx * 2.0D, y + vy * 2.0D, z + vz * 2.0D, vx, vy, vz, 15 + (getWorld()).field_73012_v.nextInt(10), -0.01F);
/*      */       } 
/*      */ 
/*      */       
/*  616 */       FXGeneric fb = new FXGeneric(getWorld(), x, y, z, 0.0D, 0.0D, 0.0D);
/*  617 */       fb.func_187114_a(10 + (getWorld()).field_73012_v.nextInt(5));
/*  618 */       fb.func_70538_b(1.0F, 0.9F, 1.0F);
/*  619 */       fb.setAlphaF(new float[] { 1.0F, 0.0F });
/*  620 */       fb.setGridSize(16);
/*  621 */       fb.setParticles(77, 1, 1);
/*  622 */       fb.setScale(new float[] { 10.0F + (getWorld()).field_73012_v.nextFloat() * 2.0F, 0.0F });
/*  623 */       fb.setLayer(0);
/*  624 */       fb.setRotationSpeed((getWorld()).field_73012_v.nextFloat(), (float)(getWorld()).field_73012_v.nextGaussian());
/*  625 */       ParticleEngine.addEffect(getWorld(), fb);
/*      */     } 
/*      */ 
/*      */     
/*  629 */     for (int a = 0; a < (flair ? 2 : 0) + (getWorld()).field_73012_v.nextInt(3); a++) {
/*  630 */       drawCurlyWisp(x, y, z, 0.0D, 0.0D, 0.0D, 1.0F, (0.9F + 
/*  631 */           (getWorld()).field_73012_v.nextFloat() * 0.1F + r) / 2.0F, (0.1F + g) / 2.0F, (0.5F + 
/*      */           
/*  633 */           (getWorld()).field_73012_v.nextFloat() * 0.1F + b) / 2.0F, 0.75F, side, a, 0, 0);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void drawCurlyWisp(double x, double y, double z, double vx, double vy, double vz, float scale, float r, float g, float b, float a, EnumFacing side, int seed, int layer, int delay) {
/*  640 */     if (getWorld() == null)
/*  641 */       return;  vx += ((0.0025F + (getWorld()).field_73012_v.nextFloat() * 0.005F) * ((getWorld()).field_73012_v.nextBoolean() ? -1 : true));
/*  642 */     vy += ((0.0025F + (getWorld()).field_73012_v.nextFloat() * 0.005F) * ((getWorld()).field_73012_v.nextBoolean() ? -1 : true));
/*  643 */     vz += ((0.0025F + (getWorld()).field_73012_v.nextFloat() * 0.005F) * ((getWorld()).field_73012_v.nextBoolean() ? -1 : true));
/*  644 */     if (side != null) {
/*  645 */       vx += (side.func_82601_c() * 0.025F);
/*  646 */       vy += (side.func_96559_d() * 0.025F);
/*  647 */       vz += (side.func_82599_e() * 0.025F);
/*      */     } 
/*  649 */     FXGeneric fb2 = new FXGeneric(getWorld(), x + vx * 5.0D, y + vy * 5.0D, z + vz * 5.0D, vx, vy, vz);
/*      */     
/*  651 */     if (seed > 0 && (getWorld()).field_73012_v.nextBoolean()) {
/*  652 */       fb2.setAngles(90.0F * (float)(getWorld()).field_73012_v.nextGaussian(), 90.0F * (float)(getWorld()).field_73012_v.nextGaussian());
/*      */     }
/*      */     
/*  655 */     fb2.func_187114_a(25 + (getWorld()).field_73012_v.nextInt(20 + 20 * seed));
/*  656 */     fb2.setRBGColorF(r, g, b, 0.1F, 0.0F, 0.1F);
/*  657 */     fb2.setAlphaF(new float[] { a, 0.0F });
/*  658 */     fb2.setGridSize(16);
/*  659 */     fb2.setParticles(60 + (getWorld()).field_73012_v.nextInt(4), 1, 1);
/*  660 */     fb2.setScale(new float[] { 5.0F * scale, (10.0F + (getWorld()).field_73012_v.nextFloat() * 4.0F) * scale });
/*  661 */     fb2.setLayer(layer);
/*  662 */     fb2.setRotationSpeed((getWorld()).field_73012_v.nextFloat(), 
/*  663 */         (getWorld()).field_73012_v.nextBoolean() ? (-2.0F - (getWorld()).field_73012_v.nextFloat() * 2.0F) : (2.0F + (getWorld()).field_73012_v.nextFloat() * 2.0F));
/*  664 */     ParticleEngine.addEffectWithDelay(getWorld(), fb2, delay);
/*      */   }
/*      */   
/*      */   public void pechsCurseTick(double posX, double posY, double posZ) {
/*  668 */     FXGeneric fb2 = new FXGeneric(getWorld(), posX, posY, posZ, 0.0D, 0.0D, 0.0D);
/*  669 */     fb2.setAngles(90.0F * (float)(getWorld()).field_73012_v.nextGaussian(), 90.0F * (float)(getWorld()).field_73012_v.nextGaussian());
/*      */     
/*  671 */     fb2.func_187114_a(50 + (getWorld()).field_73012_v.nextInt(50));
/*  672 */     fb2.setRBGColorF(0.9F, 0.1F, 0.5F, 0.1F + (getWorld()).field_73012_v.nextFloat() * 0.1F, 0.0F, 0.5F + (getWorld()).field_73012_v.nextFloat() * 0.1F);
/*  673 */     fb2.setAlphaF(new float[] { 0.75F, 0.0F });
/*  674 */     fb2.setGridSize(8);
/*  675 */     fb2.setParticles(28 + (getWorld()).field_73012_v.nextInt(4), 1, 1);
/*  676 */     fb2.setScale(new float[] { 3.0F, 5.0F + (getWorld()).field_73012_v.nextFloat() * 2.0F });
/*  677 */     fb2.setLayer(0);
/*  678 */     fb2.setRotationSpeed((getWorld()).field_73012_v.nextFloat(), 
/*  679 */         (getWorld()).field_73012_v.nextBoolean() ? (-3.0F - (getWorld()).field_73012_v.nextFloat() * 3.0F) : (3.0F + (getWorld()).field_73012_v.nextFloat() * 3.0F));
/*  680 */     ParticleEngine.addEffect(getWorld(), fb2);
/*      */     
/*  682 */     drawWispyMotes(posX, posY, posZ, 0.0D, 0.0D, 0.0D, 10 + (getWorld()).field_73012_v.nextInt(10), -0.01F);
/*      */   }
/*      */   
/*      */   public void scanHighlight(BlockPos p) {
/*  686 */     AxisAlignedBB bb = getWorld().func_180495_p(p).func_185900_c(getWorld(), p);
/*  687 */     bb = bb.func_186670_a(p);
/*  688 */     scanHighlight(bb);
/*      */   }
/*      */   
/*      */   public void scanHighlight(Entity e) {
/*  692 */     AxisAlignedBB bb = e.func_174813_aQ();
/*  693 */     scanHighlight(bb);
/*      */   }
/*      */   
/*      */   public void scanHighlight(AxisAlignedBB bb) {
/*  697 */     int num = MathHelper.func_76143_f(bb.func_72320_b() * 2.0D);
/*  698 */     double ax = (bb.field_72340_a + bb.field_72336_d) / 2.0D;
/*  699 */     double ay = (bb.field_72338_b + bb.field_72337_e) / 2.0D;
/*  700 */     double az = (bb.field_72339_c + bb.field_72334_f) / 2.0D;
/*      */     
/*  702 */     for (EnumFacing face : EnumFacing.values()) {
/*  703 */       double mx = 0.5D + face.func_82601_c() * 0.51D;
/*  704 */       double my = 0.5D + face.func_96559_d() * 0.51D;
/*  705 */       double mz = 0.5D + face.func_82599_e() * 0.51D;
/*      */       
/*  707 */       for (int a = 0; a < num * 2; a++) {
/*  708 */         double x = mx;
/*  709 */         double y = my;
/*  710 */         double z = mz;
/*  711 */         x += (getWorld()).field_73012_v.nextGaussian() * (bb.field_72336_d - bb.field_72340_a);
/*  712 */         y += (getWorld()).field_73012_v.nextGaussian() * (bb.field_72337_e - bb.field_72338_b);
/*  713 */         z += (getWorld()).field_73012_v.nextGaussian() * (bb.field_72334_f - bb.field_72339_c);
/*  714 */         x = MathHelper.func_151237_a(x, bb.field_72340_a - ax, bb.field_72336_d - ax);
/*  715 */         y = MathHelper.func_151237_a(y, bb.field_72338_b - ay, bb.field_72337_e - ay);
/*  716 */         z = MathHelper.func_151237_a(z, bb.field_72339_c - az, bb.field_72334_f - az);
/*  717 */         float r = MathHelper.func_76136_a((getWorld()).field_73012_v, 16, 32) / 255.0F;
/*  718 */         float g = MathHelper.func_76136_a((getWorld()).field_73012_v, 132, 165) / 255.0F;
/*  719 */         float b = MathHelper.func_76136_a((getWorld()).field_73012_v, 223, 239) / 255.0F;
/*  720 */         drawSimpleSparkle((getWorld()).field_73012_v, ax + x, ay + y, az + z, 0.0D, 0.0D, 0.0D, 0.4F + 
/*  721 */             (float)(getWorld()).field_73012_v.nextGaussian() * 0.1F, r, g, b, 
/*  722 */             (getWorld()).field_73012_v.nextInt(10), 1.0F, 0.0F, 4);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sparkle(float x, float y, float z, float r, float g, float b) {
/*  731 */     if ((getWorld()).field_73012_v.nextInt(6) < 4) {
/*  732 */       drawGenericParticles(x, y, z, 0.0D, 0.0D, 0.0D, r, g, b, 0.9F, true, 320, 16, 1, 6 + 
/*  733 */           (getWorld()).field_73012_v.nextInt(4), 0, 0.6F + 
/*  734 */           (getWorld()).field_73012_v.nextFloat() * 0.2F, 0.0F, 0);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void visSparkle(int x, int y, int z, int x2, int y2, int z2, int color) {
/*  741 */     FXVisSparkle fb = new FXVisSparkle(getWorld(), (x + (getWorld()).field_73012_v.nextFloat()), (y + (getWorld()).field_73012_v.nextFloat()), (z + (getWorld()).field_73012_v.nextFloat()), x2 + 0.4D + ((getWorld()).field_73012_v.nextFloat() * 0.2F), y2 + 0.4D + ((getWorld()).field_73012_v.nextFloat() * 0.2F), z2 + 0.4D + ((getWorld()).field_73012_v.nextFloat() * 0.2F));
/*  742 */     Color c = new Color(color);
/*  743 */     fb.func_70538_b(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F);
/*  744 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */   
/*      */   public void voidStreak(double x, double y, double z, double x2, double y2, double z2, int seed, float scale) {
/*  748 */     FXVoidStream fb = new FXVoidStream(getWorld(), x, y, z, x2, y2, z2, seed, scale);
/*  749 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */   
/*      */   public void splooshFX(Entity e) {
/*  753 */     float f = (getWorld()).field_73012_v.nextFloat() * 3.1415927F * 2.0F;
/*  754 */     float f1 = (getWorld()).field_73012_v.nextFloat() * 0.5F + 0.5F;
/*  755 */     float f2 = MathHelper.func_76126_a(f) * 2.0F * 0.5F * f1;
/*  756 */     float f3 = MathHelper.func_76134_b(f) * 2.0F * 0.5F * f1;
/*      */     
/*  758 */     FXBreakingFade fx = new FXBreakingFade(getWorld(), e.field_70165_t + f2, e.field_70163_u + ((getWorld()).field_73012_v.nextFloat() * e.field_70131_O), e.field_70161_v + f3, Items.field_151123_aH, 0);
/*      */     
/*  760 */     if ((getWorld()).field_73012_v.nextBoolean()) {
/*  761 */       fx.func_70538_b(0.6F, 0.0F, 0.3F);
/*  762 */       fx.func_82338_g(0.4F);
/*      */     } else {
/*  764 */       fx.func_70538_b(0.3F, 0.0F, 0.3F);
/*  765 */       fx.func_82338_g(0.6F);
/*      */     } 
/*      */     
/*  768 */     fx.setParticleMaxAge((int)(66.0F / ((getWorld()).field_73012_v.nextFloat() * 0.9F + 0.1F)));
/*  769 */     (FMLClientHandler.instance().getClient()).field_71452_i.func_78873_a(fx);
/*      */   }
/*      */   
/*      */   public void taintsplosionFX(Entity e) {
/*  773 */     FXBreakingFade fx = new FXBreakingFade(getWorld(), e.field_70165_t, e.field_70163_u + ((getWorld()).field_73012_v.nextFloat() * e.field_70131_O), e.field_70161_v, Items.field_151123_aH);
/*  774 */     if ((getWorld()).field_73012_v.nextBoolean()) {
/*  775 */       fx.func_70538_b(0.6F, 0.0F, 0.3F);
/*  776 */       fx.func_82338_g(0.4F);
/*      */     } else {
/*  778 */       fx.func_70538_b(0.3F, 0.0F, 0.3F);
/*  779 */       fx.func_82338_g(0.6F);
/*      */     } 
/*  781 */     fx.setSpeed(Math.random() * 2.0D - 1.0D, Math.random() * 2.0D - 1.0D, Math.random() * 2.0D - 1.0D);
/*  782 */     fx.boom();
/*  783 */     fx.setParticleMaxAge((int)(66.0F / ((getWorld()).field_73012_v.nextFloat() * 0.9F + 0.1F)));
/*  784 */     (FMLClientHandler.instance().getClient()).field_71452_i.func_78873_a(fx);
/*      */   }
/*      */ 
/*      */   
/*      */   public void tentacleAriseFX(Entity e) {
/*  789 */     for (int j = 0; j < 2.0F * e.field_70131_O; j++) {
/*  790 */       float f = (getWorld()).field_73012_v.nextFloat() * 3.1415927F * e.field_70131_O;
/*  791 */       float f1 = (getWorld()).field_73012_v.nextFloat() * 0.5F + 0.5F;
/*  792 */       float f2 = MathHelper.func_76126_a(f) * e.field_70131_O * 0.25F * f1;
/*  793 */       float f3 = MathHelper.func_76134_b(f) * e.field_70131_O * 0.25F * f1;
/*  794 */       FXBreakingFade fx = new FXBreakingFade(getWorld(), e.field_70165_t + f2, e.field_70163_u, e.field_70161_v + f3, Items.field_151123_aH);
/*      */       
/*  796 */       fx.func_70538_b(0.4F, 0.0F, 0.4F);
/*  797 */       fx.func_82338_g(0.5F);
/*  798 */       fx.setParticleMaxAge((int)(66.0F / ((getWorld()).field_73012_v.nextFloat() * 0.9F + 0.1F)));
/*  799 */       (FMLClientHandler.instance().getClient()).field_71452_i
/*  800 */         .func_78873_a(fx);
/*      */       
/*  802 */       if (!getWorld().func_175623_d(e.func_180425_c().func_177977_b())) {
/*  803 */         f = (getWorld()).field_73012_v.nextFloat() * 3.1415927F * e.field_70131_O;
/*  804 */         f1 = (getWorld()).field_73012_v.nextFloat() * 0.5F + 0.5F;
/*  805 */         f2 = MathHelper.func_76126_a(f) * e.field_70131_O * 0.25F * f1;
/*  806 */         f3 = MathHelper.func_76134_b(f) * e.field_70131_O * 0.25F * f1;
/*      */         
/*  808 */         getWorld().func_175688_a(EnumParticleTypes.BLOCK_CRACK, e.field_70165_t + f2, e.field_70163_u, e.field_70161_v + f3, 0.0D, 0.0D, 0.0D, new int[] {
/*  809 */               Block.func_176210_f(getWorld().func_180495_p(e.func_180425_c().func_177977_b()))
/*      */             });
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void slimeJumpFX(Entity e, int i) {
/*  816 */     float f = (getWorld()).field_73012_v.nextFloat() * 3.1415927F * 2.0F;
/*  817 */     float f1 = (getWorld()).field_73012_v.nextFloat() * 0.5F + 0.5F;
/*  818 */     float f2 = MathHelper.func_76126_a(f) * i * 0.5F * f1;
/*  819 */     float f3 = MathHelper.func_76134_b(f) * i * 0.5F * f1;
/*      */     
/*  821 */     FXBreakingFade fx = new FXBreakingFade(getWorld(), e.field_70165_t + f2, ((e.func_174813_aQ()).field_72338_b + (e.func_174813_aQ()).field_72337_e) / 2.0D, e.field_70161_v + f3, Items.field_151123_aH, 0);
/*      */     
/*  823 */     fx.func_70538_b(0.7F, 0.0F, 1.0F);
/*  824 */     fx.func_82338_g(0.4F);
/*  825 */     fx.setParticleMaxAge((int)(66.0F / ((getWorld()).field_73012_v.nextFloat() * 0.9F + 0.1F)));
/*  826 */     (FMLClientHandler.instance().getClient()).field_71452_i.func_78873_a(fx);
/*      */   }
/*      */   
/*      */   public void taintLandFX(Entity e) {
/*  830 */     float f = (getWorld()).field_73012_v.nextFloat() * 3.1415927F * 2.0F;
/*  831 */     float f1 = (getWorld()).field_73012_v.nextFloat() * 0.5F + 0.5F;
/*  832 */     float f2 = MathHelper.func_76126_a(f) * 2.0F * 0.5F * f1;
/*  833 */     float f3 = MathHelper.func_76134_b(f) * 2.0F * 0.5F * f1;
/*  834 */     if ((getWorld()).field_72995_K) {
/*      */       
/*  836 */       FXBreakingFade fx = new FXBreakingFade(getWorld(), e.field_70165_t + f2, ((e.func_174813_aQ()).field_72338_b + (e.func_174813_aQ()).field_72337_e) / 2.0D, e.field_70161_v + f3, Items.field_151123_aH);
/*      */       
/*  838 */       fx.func_70538_b(0.1F, 0.0F, 0.1F);
/*  839 */       fx.func_82338_g(0.4F);
/*  840 */       fx.setParticleMaxAge((int)(66.0F / ((getWorld()).field_73012_v.nextFloat() * 0.9F + 0.1F)));
/*  841 */       (FMLClientHandler.instance().getClient()).field_71452_i.func_78873_a(fx);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void drawInfusionParticles1(double x, double y, double z, BlockPos pos, ItemStack stack) {
/*  851 */     FXBoreParticles fb = (new FXBoreParticles(getWorld(), x, y, z, pos.func_177958_n() + 0.5D, pos.func_177956_o() - 0.5D, pos.func_177952_p() + 0.5D, ((float)(getWorld()).field_73012_v.nextGaussian() * 0.03F), ((float)(getWorld()).field_73012_v.nextGaussian() * 0.03F), ((float)(getWorld()).field_73012_v.nextGaussian() * 0.03F), stack)).getObjectColor(pos);
/*  852 */     fb.func_82338_g(0.3F);
/*  853 */     (FMLClientHandler.instance().getClient()).field_71452_i.func_78873_a(fb);
/*      */   }
/*      */ 
/*      */   
/*      */   public void drawInfusionParticles2(double x, double y, double z, BlockPos pos, IBlockState id, int md) {
/*  858 */     FXBoreParticles fb = (new FXBoreParticles(getWorld(), x, y, z, pos.func_177958_n() + 0.5D, pos.func_177956_o() - 0.5D, pos.func_177952_p() + 0.5D, id, md)).getObjectColor(pos);
/*  859 */     fb.func_82338_g(0.3F);
/*  860 */     (FMLClientHandler.instance().getClient()).field_71452_i.func_78873_a(fb);
/*      */   }
/*      */   
/*      */   public void drawInfusionParticles3(double x, double y, double z, int x2, int y2, int z2) {
/*  864 */     FXBoreSparkle fb = new FXBoreSparkle(getWorld(), x, y, z, x2 + 0.5D, y2 - 0.5D, z2 + 0.5D);
/*  865 */     fb.func_70538_b(0.4F + (getWorld()).field_73012_v.nextFloat() * 0.2F, 0.2F, 0.6F + (getWorld()).field_73012_v.nextFloat() * 0.3F);
/*  866 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */   
/*      */   public void drawInfusionParticles4(double x, double y, double z, int x2, int y2, int z2) {
/*  870 */     FXBoreSparkle fb = new FXBoreSparkle(getWorld(), x, y, z, x2 + 0.5D, y2 - 0.5D, z2 + 0.5D);
/*  871 */     fb.func_70538_b(0.2F, 0.6F + (getWorld()).field_73012_v.nextFloat() * 0.3F, 0.3F);
/*  872 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */   
/*      */   public void drawVentParticles(double x, double y, double z, double x2, double y2, double z2, int color) {
/*  876 */     FXVent fb = new FXVent(getWorld(), x, y, z, x2, y2, z2, color);
/*  877 */     fb.func_82338_g(0.4F);
/*  878 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */   
/*      */   public void drawVentParticles(double x, double y, double z, double x2, double y2, double z2, int color, float scale) {
/*  882 */     FXVent fb = new FXVent(getWorld(), x, y, z, x2, y2, z2, color);
/*  883 */     fb.func_82338_g(0.4F);
/*  884 */     fb.setScale(scale);
/*  885 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */   
/*      */   public void drawVentParticles2(double x, double y, double z, double x2, double y2, double z2, int color, float scale) {
/*  889 */     FXVent2 fb = new FXVent2(getWorld(), x, y, z, x2, y2, z2, color);
/*  890 */     fb.func_82338_g(0.4F);
/*  891 */     fb.setScale(scale);
/*  892 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */     
/*  894 */     if ((getWorld()).field_73012_v.nextInt(6) < 2) {
/*  895 */       drawGenericParticles(x, y, z, x2 / 2.0D, y2 / 2.0D, z2 / 2.0D, 1.0F, 0.7F, 0.2F, 0.9F, true, 320, 16, 1, 10 + 
/*  896 */           (getWorld()).field_73012_v.nextInt(4), 0, 0.25F + 
/*  897 */           (getWorld()).field_73012_v.nextFloat() * 0.1F, 0.0F, 0);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void spark(double d, double e, double f, float size, float r, float g, float b, float a) {
/*  922 */     FXGeneric fb = new FXGeneric(getWorld(), d, e, f, 0.0D, 0.0D, 0.0D);
/*  923 */     fb.func_187114_a(5 + (getWorld()).field_73012_v.nextInt(5));
/*  924 */     fb.func_82338_g(a);
/*  925 */     fb.func_70538_b(r, g, b);
/*  926 */     fb.setGridSize(16);
/*  927 */     fb.setParticles(8 + (getWorld()).field_73012_v.nextInt(3) * 16, 8, 1);
/*  928 */     fb.setScale(new float[] { size });
/*  929 */     fb.setFlipped((getWorld()).field_73012_v.nextBoolean());
/*  930 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */   
/*      */   public void smokeSpiral(double x, double y, double z, float rad, int start, int miny, int color) {
/*  934 */     FXSmokeSpiral fx = new FXSmokeSpiral(getWorld(), x, y, z, rad, start, miny);
/*  935 */     Color c = new Color(color);
/*  936 */     fx.func_70538_b(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F);
/*  937 */     ParticleEngine.addEffect(getWorld(), fx);
/*      */   }
/*      */   
/*      */   public void wispFXEG(double posX, double posY, double posZ, Entity target) {
/*  941 */     for (int a = 0; a < 2; a++) {
/*  942 */       FXWispEG ef = new FXWispEG(getWorld(), posX, posY, posZ, target);
/*  943 */       ParticleEngine.addEffect(getWorld(), ef);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void burst(double sx, double sy, double sz, float size) {
/*  949 */     FXGeneric fb = new FXGeneric(getWorld(), sx, sy, sz, 0.0D, 0.0D, 0.0D);
/*  950 */     fb.func_187114_a(31);
/*  951 */     fb.setGridSize(16);
/*  952 */     fb.setParticles(208, 31, 1);
/*  953 */     fb.setScale(new float[] { size });
/*  954 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void excavateFX(BlockPos pos, EntityLivingBase p, int progress) {
/*  970 */     RenderGlobal rg = (Minecraft.func_71410_x()).field_71438_f;
/*  971 */     rg.func_180441_b(p.func_145782_y(), pos, progress);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object beamCont(EntityLivingBase p, double tx, double ty, double tz, int type, int color, boolean reverse, float endmod, Object input, int impact) {
/*  993 */     FXBeamWand beamcon = null;
/*  994 */     Color c = new Color(color);
/*  995 */     if (input instanceof FXBeamWand)
/*  996 */       beamcon = (FXBeamWand)input; 
/*  997 */     if (beamcon == null || !beamcon.func_187113_k()) {
/*      */       
/*  999 */       beamcon = new FXBeamWand(getWorld(), p, tx, ty, tz, c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, 8);
/*      */       
/* 1001 */       beamcon.setType(type);
/* 1002 */       beamcon.setEndMod(endmod);
/* 1003 */       beamcon.setReverse(reverse);
/* 1004 */       (FMLClientHandler.instance().getClient()).field_71452_i
/* 1005 */         .func_78873_a(beamcon);
/*      */     } else {
/* 1007 */       beamcon.updateBeam(tx, ty, tz);
/* 1008 */       beamcon.setEndMod(endmod);
/* 1009 */       beamcon.impact = impact;
/*      */     } 
/* 1011 */     return beamcon;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Object beamBore(double px, double py, double pz, double tx, double ty, double tz, int type, int color, boolean reverse, float endmod, Object input, int impact) {
/* 1017 */     FXBeamBore beamcon = null;
/* 1018 */     Color c = new Color(color);
/* 1019 */     if (input instanceof FXBeamBore)
/* 1020 */       beamcon = (FXBeamBore)input; 
/* 1021 */     if (beamcon == null || !beamcon.func_187113_k()) {
/*      */       
/* 1023 */       beamcon = new FXBeamBore(getWorld(), px, py, pz, tx, ty, tz, c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, 8);
/* 1024 */       beamcon.setType(type);
/* 1025 */       beamcon.setEndMod(endmod);
/* 1026 */       beamcon.setReverse(reverse);
/* 1027 */       (FMLClientHandler.instance().getClient()).field_71452_i.func_78873_a(beamcon);
/*      */     } else {
/* 1029 */       beamcon.updateBeam(px, py, pz, tx, ty, tz);
/* 1030 */       beamcon.setEndMod(endmod);
/* 1031 */       beamcon.impact = impact;
/*      */     } 
/* 1033 */     return beamcon;
/*      */   }
/*      */ 
/*      */   
/*      */   public void boreDigFx(int x, int y, int z, Entity e, IBlockState bi, int md, int delay) {
/* 1038 */     float p = 50.0F;
/*      */     
/* 1040 */     for (int a = 0; a < p / delay; a++) {
/* 1041 */       if ((getWorld()).field_73012_v.nextInt(4) == 0) {
/*      */ 
/*      */         
/* 1044 */         FXBoreSparkle fb = new FXBoreSparkle(getWorld(), (x + (getWorld()).field_73012_v.nextFloat()), (y + (getWorld()).field_73012_v.nextFloat()), (z + (getWorld()).field_73012_v.nextFloat()), e);
/* 1045 */         ParticleEngine.addEffect(getWorld(), fb);
/*      */       }
/*      */       else {
/*      */         
/* 1049 */         FXBoreParticles fb = new FXBoreParticles(getWorld(), (x + (getWorld()).field_73012_v.nextFloat()), (y + (getWorld()).field_73012_v.nextFloat()), (z + (getWorld()).field_73012_v.nextFloat()), e.field_70165_t, e.field_70163_u, e.field_70161_v, bi, md);
/*      */ 
/*      */         
/* 1052 */         fb.setTarget(e);
/* 1053 */         (FMLClientHandler.instance().getClient()).field_71452_i.func_78873_a(fb);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void essentiaTrailFx(BlockPos p1, BlockPos p2, int count, int color, float scale, int ext) {
/* 1061 */     FXEssentiaStream fb = new FXEssentiaStream(getWorld(), p1.func_177958_n() + 0.5D, p1.func_177956_o() + 0.5D, p1.func_177952_p() + 0.5D, p2.func_177958_n() + 0.5D, p2.func_177956_o() + 0.5D, p2.func_177952_p() + 0.5D, count, color, scale, ext, 0.0D);
/* 1062 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */ 
/*      */   
/*      */   public void boreTrailFx(BlockPos p1, Entity e, int count, int color, float scale, int ext) {
/* 1067 */     FXBoreStream fb = new FXBoreStream(getWorld(), p1.func_177958_n() + 0.5D, p1.func_177956_o() + 0.5D, p1.func_177952_p() + 0.5D, e, count, color, scale, ext, 0.0D);
/* 1068 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void essentiaDropFx(double x, double y, double z, float r, float g, float b, float alpha) {
/* 1075 */     FXGeneric fb = new FXGeneric(getWorld(), x, y, z, (getWorld()).field_73012_v.nextGaussian() * 0.004999999888241291D, (getWorld()).field_73012_v.nextGaussian() * 0.004999999888241291D, (getWorld()).field_73012_v.nextGaussian() * 0.004999999888241291D);
/* 1076 */     fb.func_187114_a(20 + (getWorld()).field_73012_v.nextInt(10));
/* 1077 */     fb.func_70538_b(r, g, b);
/* 1078 */     fb.func_82338_g(alpha);
/* 1079 */     fb.setLoop(false);
/* 1080 */     fb.setParticles(25, 1, 1);
/* 1081 */     fb.setScale(new float[] { 0.4F + (getWorld()).field_73012_v.nextFloat() * 0.2F, 0.2F });
/* 1082 */     fb.setLayer(1);
/* 1083 */     fb.setGravity(0.01F);
/* 1084 */     fb.setRotationSpeed(0.0F);
/* 1085 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void jarSplashFx(double x, double y, double z) {
/* 1095 */     FXGeneric fb = new FXGeneric(getWorld(), x + (getWorld()).field_73012_v.nextGaussian() * 0.07500000298023224D, y, z + (getWorld()).field_73012_v.nextGaussian() * 0.07500000298023224D, (getWorld()).field_73012_v.nextGaussian() * 0.014999999664723873D, (0.075F + (getWorld()).field_73012_v.nextFloat() * 0.05F), (getWorld()).field_73012_v.nextGaussian() * 0.014999999664723873D);
/* 1096 */     fb.func_187114_a(20 + (getWorld()).field_73012_v.nextInt(10));
/* 1097 */     Color c = new Color(2650102);
/* 1098 */     fb.func_70538_b(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F);
/* 1099 */     fb.func_82338_g(0.5F);
/* 1100 */     fb.setLoop(false);
/* 1101 */     fb.setParticles(73, 1, 1);
/* 1102 */     fb.setScale(new float[] { 0.4F + (getWorld()).field_73012_v.nextFloat() * 0.3F, 0.0F });
/* 1103 */     fb.setLayer(1);
/* 1104 */     fb.setGravity(0.3F);
/*      */     
/* 1106 */     fb.setRotationSpeed(0.0F);
/* 1107 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void waterTrailFx(BlockPos p1, BlockPos p2, int count, int color, float scale) {
/* 1113 */     FXEssentiaStream fb = new FXEssentiaStream(getWorld(), p1.func_177958_n() + 0.5D, p1.func_177956_o() + 0.66D, p1.func_177952_p() + 0.5D, p2.func_177958_n() + 0.5D, p2.func_177956_o() + 0.5D, p2.func_177952_p() + 0.5D, count, color, scale, 0, 0.2D);
/* 1114 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */ 
/*      */   
/*      */   public void furnaceLavaFx(int x, int y, int z, int facingX, int facingZ) {
/* 1119 */     float qx = (facingX == 0) ? (((getWorld()).field_73012_v.nextFloat() - (getWorld()).field_73012_v.nextFloat()) * 0.5F) : (facingX * (getWorld()).field_73012_v.nextFloat());
/*      */     
/* 1121 */     float qz = (facingZ == 0) ? (((getWorld()).field_73012_v.nextFloat() - (getWorld()).field_73012_v.nextFloat()) * 0.5F) : (facingZ * (getWorld()).field_73012_v.nextFloat());
/* 1122 */     Particle fb = (new ParticleLava.Factory()).func_178902_a(0, getWorld(), (x + 0.5F + (
/* 1123 */         (getWorld()).field_73012_v.nextFloat() - (getWorld()).field_73012_v.nextFloat()) * 0.3F + facingX * 1.0F), (y + 0.3F), (z + 0.5F + (
/*      */         
/* 1125 */         (getWorld()).field_73012_v.nextFloat() - (getWorld()).field_73012_v.nextFloat()) * 0.3F + facingZ * 1.0F), (0.15F * qx), (0.2F * 
/* 1126 */         (getWorld()).field_73012_v.nextFloat()), (0.15F * qz), new int[0]);
/*      */     
/* 1128 */     (FMLClientHandler.instance().getClient()).field_71452_i.func_78873_a(fb);
/*      */   }
/*      */   
/*      */   public void blockRunes(double x, double y, double z, float r, float g, float b, int dur, float grav) {
/* 1132 */     FXBlockRunes fb = new FXBlockRunes(getWorld(), x + 0.5D, y + 0.5D, z + 0.5D, r, g, b, dur);
/* 1133 */     fb.setGravity(grav);
/* 1134 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */   
/*      */   public void blockRunes2(double x, double y, double z, float r, float g, float b, int dur, float grav) {
/* 1138 */     FXBlockRunes fb = new FXBlockRunes(getWorld(), x + 0.5D, y + 0.5D, z + 0.5D, r, g, b, dur);
/* 1139 */     fb.setGravity(grav);
/* 1140 */     fb.setScale((float)(0.5D + (getWorld()).field_73012_v.nextGaussian() * 0.10000000149011612D));
/* 1141 */     fb.setOffsetX(0.0D);
/* 1142 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */   
/*      */   public void drawSlash(double x, double y, double z, double x2, double y2, double z2, int dur) {
/* 1146 */     FXPlane fb = new FXPlane(getWorld(), x, y, z, x2, y2, z2, dur);
/* 1147 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */ 
/*      */   
/*      */   public void blockWard(double x, double y, double z, EnumFacing side, float f, float f1, float f2) {
/* 1152 */     FXBlockWard fb = new FXBlockWard(getWorld(), x + 0.5D, y + 0.5D, z + 0.5D, side, f, f1, f2);
/* 1153 */     (FMLClientHandler.instance().getClient()).field_71452_i.func_78873_a(fb);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object swarmParticleFX(Entity targetedEntity, float f1, float f2, float pg) {
/* 1164 */     FXSwarm fx = new FXSwarm(getWorld(), targetedEntity.field_70165_t + (((getWorld()).field_73012_v.nextFloat() - (getWorld()).field_73012_v.nextFloat()) * 2.0F), targetedEntity.field_70163_u + (((getWorld()).field_73012_v.nextFloat() - (getWorld()).field_73012_v.nextFloat()) * 2.0F), targetedEntity.field_70161_v + (((getWorld()).field_73012_v.nextFloat() - (getWorld()).field_73012_v.nextFloat()) * 2.0F), targetedEntity, 0.8F + (getWorld()).field_73012_v.nextFloat() * 0.2F, (getWorld()).field_73012_v.nextFloat() * 0.4F, 1.0F - (getWorld()).field_73012_v.nextFloat() * 0.2F, f1, f2, pg);
/* 1165 */     ParticleEngine.addEffect(getWorld(), fx);
/* 1166 */     return fx;
/*      */   }
/*      */ 
/*      */   
/*      */   public void bottleTaintBreak(double x, double y, double z) {
/* 1171 */     for (int k1 = 0; k1 < 8; k1++) {
/*      */       
/* 1173 */       getWorld().func_175688_a(EnumParticleTypes.ITEM_CRACK, x, y, z, 
/* 1174 */           (getWorld()).field_73012_v.nextGaussian() * 0.15D, (getWorld()).field_73012_v.nextDouble() * 0.2D, 
/* 1175 */           (getWorld()).field_73012_v.nextGaussian() * 0.15D, new int[] { Item.func_150891_b(ItemsTC.bottleTaint) });
/*      */     } 
/*      */     
/* 1178 */     getWorld().func_184134_a(x, y, z, SoundEvents.field_187825_fO, SoundCategory.NEUTRAL, 1.0F, 
/* 1179 */         (getWorld()).field_73012_v.nextFloat() * 0.1F + 0.9F, false);
/*      */   }
/*      */   
/*      */   public void arcLightning(double x, double y, double z, double tx, double ty, double tz, float r, float g, float b, float h) {
/* 1183 */     if (h <= 0.0F) h = 0.1F; 
/* 1184 */     FXArc efa = new FXArc(getWorld(), x, y, z, tx, ty, tz, r, g, b, h);
/* 1185 */     (FMLClientHandler.instance().getClient()).field_71452_i.func_78873_a(efa);
/*      */   }
/*      */   
/*      */   public void arcBolt(double x, double y, double z, double tx, double ty, double tz, float r, float g, float b, float width) {
/* 1189 */     FXBolt efa = new FXBolt(getWorld(), x, y, z, tx, ty, tz, r, g, b, width);
/* 1190 */     (FMLClientHandler.instance().getClient()).field_71452_i.func_78873_a(efa);
/*      */   }
/*      */   
/*      */   public void cultistSpawn(double x, double y, double z, double a, double b, double c) {
/* 1194 */     FXGeneric fb = new FXGeneric(getWorld(), x, y, z, a, b, c);
/* 1195 */     fb.func_187114_a(10 + (getWorld()).field_73012_v.nextInt(10));
/* 1196 */     fb.setRBGColorF(1.0F, 1.0F, 1.0F, 0.6F, 0.0F, 0.0F);
/* 1197 */     fb.func_82338_g(0.8F);
/* 1198 */     fb.setGridSize(16);
/* 1199 */     fb.setParticles(160, 6, 1);
/* 1200 */     fb.setScale(new float[] { 3.0F + (getWorld()).field_73012_v.nextFloat() * 2.0F });
/* 1201 */     fb.setLayer(1);
/* 1202 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */   
/*      */   public void drawWispyMotesEntity(double x, double y, double z, Entity e, float r, float g, float b) {
/* 1206 */     FXGenericP2E fb = new FXGenericP2E(getWorld(), x, y, z, e);
/* 1207 */     fb.func_70538_b(r, g, b);
/* 1208 */     fb.func_82338_g(0.6F);
/* 1209 */     fb.setParticles(512, 16, 1);
/* 1210 */     fb.setLoop(true);
/* 1211 */     fb.setWind(0.001D);
/* 1212 */     fb.setRandomMovementScale(0.0025F, 0.0F, 0.0025F);
/* 1213 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */   
/*      */   public void drawWispParticles(double x, double y, double z, double x2, double y2, double z2, int color, int a) {
/* 1217 */     FXGeneric fb = new FXGeneric(getWorld(), x, y, z, x2, y2, z2);
/* 1218 */     fb.func_187114_a(10 + (getWorld()).field_73012_v.nextInt(5));
/* 1219 */     Color c = new Color(color);
/* 1220 */     fb.func_70538_b(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F);
/* 1221 */     fb.func_82338_g(0.5F);
/* 1222 */     fb.setLoop(true);
/* 1223 */     fb.setGridSize(64);
/* 1224 */     fb.setParticles(264, 8, 1);
/* 1225 */     fb.setScale(new float[] { 1.0F + (getWorld()).field_73012_v.nextFloat() * 0.25F, 0.05F });
/* 1226 */     fb.setWind(2.5E-4D);
/* 1227 */     fb.setRandomMovementScale(0.0025F, 0.0F, 0.0025F);
/* 1228 */     ParticleEngine.addEffectWithDelay(getWorld(), fb, a);
/*      */   }
/*      */   
/*      */   public void drawNitorCore(double x, double y, double z, double x2, double y2, double z2) {
/* 1232 */     FXGeneric fb = new FXGeneric(getWorld(), x, y, z, x2, y2, z2);
/* 1233 */     fb.func_187114_a(10);
/* 1234 */     fb.func_70538_b(1.0F, 1.0F, 1.0F);
/* 1235 */     fb.func_82338_g(1.0F);
/* 1236 */     fb.setParticles(457, 1, 1);
/* 1237 */     fb.setScale(new float[] { 1.0F, 1.0F + (float)(getWorld()).field_73012_v.nextGaussian() * 0.1F, 1.0F });
/* 1238 */     fb.setLayer(1);
/* 1239 */     fb.setRandomMovementScale(2.0E-4F, 2.0E-4F, 2.0E-4F);
/* 1240 */     ParticleEngine.addEffect(getWorld(), fb);
/*      */   }
/*      */   
/*      */   public void drawNitorFlames(double x, double y, double z, double x2, double y2, double z2, int color, int a) {
/* 1244 */     FXGeneric fb = new FXGeneric(getWorld(), x, y, z, x2, y2, z2);
/* 1245 */     fb.func_187114_a(10 + (getWorld()).field_73012_v.nextInt(5));
/* 1246 */     Color c = new Color(color);
/* 1247 */     fb.func_70538_b(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F);
/* 1248 */     fb.func_82338_g(0.66F);
/* 1249 */     fb.setLoop(true);
/* 1250 */     fb.setGridSize(64);
/* 1251 */     fb.setParticles(264, 8, 1);
/* 1252 */     fb.setScale(new float[] { 3.0F + (getWorld()).field_73012_v.nextFloat(), 0.05F });
/* 1253 */     fb.setRandomMovementScale(0.0025F, 0.0F, 0.0025F);
/* 1254 */     ParticleEngine.addEffectWithDelay(getWorld(), fb, a);
/*      */   }
/*      */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\fx\FXDispatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */