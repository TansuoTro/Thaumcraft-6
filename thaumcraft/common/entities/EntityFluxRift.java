/*     */ package thaumcraft.common.entities;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.MobEffects;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.text.TextComponentString;
/*     */ import net.minecraft.util.text.translation.I18n;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.capabilities.IPlayerKnowledge;
/*     */ import thaumcraft.api.capabilities.IPlayerWarp;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.api.casters.FocusEngine;
/*     */ import thaumcraft.api.casters.FocusMediumRoot;
/*     */ import thaumcraft.api.casters.FocusPackage;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.potions.PotionFluxTaint;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.blocks.world.taint.TaintHelper;
/*     */ import thaumcraft.common.entities.monster.EntityWisp;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintSeedPrime;
/*     */ import thaumcraft.common.items.casters.foci.FocusEffectFlux;
/*     */ import thaumcraft.common.items.casters.foci.FocusMediumCloud;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXBlockBamf;
/*     */ import thaumcraft.common.lib.potions.PotionInfectiousVisExhaust;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import thaumcraft.common.lib.utils.RandomItemChooser;
/*     */ import thaumcraft.common.world.aura.AuraHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityFluxRift
/*     */   extends Entity
/*     */ {
/*     */   public EntityFluxRift(World par1World) {
/*  61 */     super(par1World);
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
/* 157 */     this.maxSize = 0;
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
/* 185 */     this.lastSize = -1;
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
/* 439 */     this.points = new ArrayList();
/* 440 */     this.pointsWidth = new ArrayList(); func_70105_a(2.0F, 2.0F);
/*     */   } private static final DataParameter<Integer> SEED = EntityDataManager.func_187226_a(EntityFluxRift.class, DataSerializers.field_187192_b); private static final DataParameter<Integer> SIZE = EntityDataManager.func_187226_a(EntityFluxRift.class, DataSerializers.field_187192_b); private static final DataParameter<Float> STABILITY = EntityDataManager.func_187226_a(EntityFluxRift.class, DataSerializers.field_187193_c); private static final DataParameter<Boolean> COLLAPSE = EntityDataManager.func_187226_a(EntityFluxRift.class, DataSerializers.field_187198_h); int maxSize; int lastSize; protected void func_70088_a() { func_184212_Q().func_187214_a(SEED, Integer.valueOf(0)); func_184212_Q().func_187214_a(SIZE, Integer.valueOf(5)); func_184212_Q().func_187214_a(STABILITY, Float.valueOf(0.0F)); func_184212_Q().func_187214_a(COLLAPSE, Boolean.valueOf(false)); } public boolean getCollapse() { return ((Boolean)func_184212_Q().func_187225_a(COLLAPSE)).booleanValue(); } public void setCollapse(boolean b) { if (b) this.maxSize = getRiftSize();  func_184212_Q().func_187227_b(COLLAPSE, Boolean.valueOf(b)); } public float getRiftStability() { return ((Float)func_184212_Q().func_187225_a(STABILITY)).floatValue(); } public void setRiftStability(float s) { if (s > 100.0F) s = 100.0F;  if (s < -100.0F) s = -100.0F;  func_184212_Q().func_187227_b(STABILITY, Float.valueOf(s)); } public int getRiftSize() { return ((Integer)func_184212_Q().func_187225_a(SIZE)).intValue(); } public void setRiftSize(int s) { func_184212_Q().func_187227_b(SIZE, Integer.valueOf(s)); setSize(); }
/*     */   public double func_70033_W() { return (-this.field_70131_O / 2.0F); }
/* 443 */   private void calcSteps(ArrayList<Vec3d> pp, ArrayList<Float> ww, Random rr) { pp.clear();
/* 444 */     ww.clear();
/*     */     
/* 446 */     Vec3d right = (new Vec3d(rr.nextGaussian(), rr.nextGaussian(), rr.nextGaussian())).func_72432_b();
/* 447 */     Vec3d left = right.func_186678_a(-1.0D);
/*     */     
/* 449 */     Vec3d lr = new Vec3d(0.0D, 0.0D, 0.0D);
/* 450 */     Vec3d ll = new Vec3d(0.0D, 0.0D, 0.0D);
/*     */     
/* 452 */     int steps = MathHelper.func_76123_f(getRiftSize() / 3.0F);
/* 453 */     float girth = getRiftSize() / 300.0F;
/*     */     
/* 455 */     double angle = 0.33D;
/*     */     
/* 457 */     float dec = girth / steps;
/*     */     
/* 459 */     for (int a = 0; a < steps; a++) {
/* 460 */       girth -= dec;
/*     */       
/* 462 */       right = right.func_178789_a((float)(rr.nextGaussian() * angle));
/* 463 */       right = right.func_178785_b((float)(rr.nextGaussian() * angle));
/* 464 */       lr = lr.func_178787_e(right.func_186678_a(0.2D));
/* 465 */       pp.add(new Vec3d(lr.field_72450_a, lr.field_72448_b, lr.field_72449_c));
/* 466 */       ww.add(Float.valueOf(girth));
/*     */       
/* 468 */       left = left.func_178789_a((float)(rr.nextGaussian() * angle));
/* 469 */       left = left.func_178785_b((float)(rr.nextGaussian() * angle));
/* 470 */       ll = ll.func_178787_e(left.func_186678_a(0.2D));
/* 471 */       pp.add(0, new Vec3d(ll.field_72450_a, ll.field_72448_b, ll.field_72449_c));
/* 472 */       ww.add(0, Float.valueOf(girth));
/*     */     } 
/*     */     
/* 475 */     lr = lr.func_178787_e(right.func_186678_a(0.1D));
/* 476 */     pp.add(new Vec3d(lr.field_72450_a, lr.field_72448_b, lr.field_72449_c));
/* 477 */     ww.add(Float.valueOf(0.0F));
/* 478 */     ll = ll.func_178787_e(left.func_186678_a(0.1D));
/* 479 */     pp.add(0, new Vec3d(ll.field_72450_a, ll.field_72448_b, ll.field_72449_c));
/* 480 */     ww.add(0, Float.valueOf(0.0F)); }
/*     */   protected void setSize() { calcSteps(this.points, this.pointsWidth, new Random(getRiftSeed())); this.lastSize = getRiftSize(); double x0 = Double.MAX_VALUE; double y0 = Double.MAX_VALUE; double z0 = Double.MAX_VALUE; double x1 = Double.MIN_VALUE; double y1 = Double.MIN_VALUE; double z1 = Double.MIN_VALUE; for (Vec3d v : this.points) { if (v.field_72450_a < x0) x0 = v.field_72450_a;  if (v.field_72450_a > x1) x1 = v.field_72450_a;  if (v.field_72448_b < y0) y0 = v.field_72448_b;  if (v.field_72448_b > y1) y1 = v.field_72448_b;  if (v.field_72449_c < z0) z0 = v.field_72449_c;  if (v.field_72449_c > z1) z1 = v.field_72449_c;  }  func_174826_a(new AxisAlignedBB(this.field_70165_t + x0, this.field_70163_u + y0, this.field_70161_v + z0, this.field_70165_t + x1, this.field_70163_u + y1, this.field_70161_v + z1)); this.field_70130_N = Math.abs((float)Math.max(x1 - x0, z1 - z0)); this.field_70131_O = Math.abs((float)(y1 - y0)); }
/*     */   public void func_70107_b(double x, double y, double z) { this.field_70165_t = x; this.field_70163_u = y; this.field_70161_v = z; if (func_184212_Q() != null) { setSize(); } else { super.func_70107_b(x, y, z); }  }
/*     */   public int getRiftSeed() { return ((Integer)func_184212_Q().func_187225_a(SEED)).intValue(); }
/* 484 */   public void setRiftSeed(int s) { func_184212_Q().func_187227_b(SEED, Integer.valueOf(s)); } public void func_70014_b(NBTTagCompound nbttagcompound) { nbttagcompound.func_74768_a("MaxSize", this.maxSize); nbttagcompound.func_74768_a("RiftSize", getRiftSize()); nbttagcompound.func_74768_a("RiftSeed", getRiftSeed()); nbttagcompound.func_74776_a("Stability", getRiftStability()); nbttagcompound.func_74757_a("collapse", getCollapse()); } public void func_70037_a(NBTTagCompound nbttagcompound) { this.maxSize = nbttagcompound.func_74762_e("MaxSize"); setRiftSize(nbttagcompound.func_74762_e("RiftSize")); setRiftSeed(nbttagcompound.func_74762_e("RiftSeed")); setRiftStability(nbttagcompound.func_74762_e("Stability")); setCollapse(nbttagcompound.func_74767_n("collapse")); } public void func_70091_d(MoverType type, double x, double y, double z) {} public void addStability() { setRiftStability(getRiftStability() + 0.125F); }
/*     */   public void func_70071_h_() { super.func_70071_h_(); if (this.lastSize != getRiftSize()) setSize();  if (!this.field_70170_p.field_72995_K) { if (getRiftSeed() == 0) setRiftSeed(this.field_70146_Z.nextInt());  if (!this.points.isEmpty()) { int pi = this.field_70146_Z.nextInt(this.points.size() - 1); Vec3d v1 = ((Vec3d)this.points.get(pi)).func_72441_c(this.field_70165_t, this.field_70163_u, this.field_70161_v); Vec3d v2 = ((Vec3d)this.points.get(pi + 1)).func_72441_c(this.field_70165_t, this.field_70163_u, this.field_70161_v); RayTraceResult rt = this.field_70170_p.func_72901_a(v1, v2, false); if (rt != null && rt.func_178782_a() != null) { BlockPos p = new BlockPos(rt.func_178782_a()); IBlockState bs = this.field_70170_p.func_180495_p(p); if (!this.field_70170_p.func_175623_d(p) && bs.func_185887_b(this.field_70170_p, p) >= 0.0F && bs.func_177230_c().func_176209_a(bs, false)) { this.field_70170_p.func_180498_a(null, 2001, p, Block.func_176210_f(this.field_70170_p.func_180495_p(p))); this.field_70170_p.func_175698_g(p); }  }  List<Entity> el = EntityUtils.getEntitiesInRange(func_130014_f_(), v1.field_72450_a, v1.field_72448_b, v1.field_72449_c, this, Entity.class, 0.5D); for (Entity e : el) { if (e.field_70128_L || (e instanceof EntityPlayer && ((EntityPlayer)e).func_184812_l_())) continue;  try { e.func_70097_a(DamageSource.field_76380_i, 2.0F); if (e instanceof net.minecraft.entity.item.EntityItem) e.func_70106_y();  } catch (Exception exception) {} }  }  if (this.points.size() < 3 && !getCollapse()) setCollapse(true);  if (getCollapse()) { setRiftSize(getRiftSize() - 1); if (this.field_70146_Z.nextBoolean()) { AuraHelper.addVis(this.field_70170_p, func_180425_c(), 1.0F); } else { AuraHelper.polluteAura(this.field_70170_p, func_180425_c(), 1.0F, false); }  if (this.field_70146_Z.nextInt(10) == 0) this.field_70170_p.func_72876_a(this, this.field_70165_t + this.field_70146_Z.nextGaussian() * 2.0D, this.field_70163_u + this.field_70146_Z.nextGaussian() * 2.0D, this.field_70161_v + this.field_70146_Z.nextGaussian() * 2.0D, this.field_70146_Z.nextFloat() / 2.0F, false);  if (getRiftSize() <= 1) { completeCollapse(); return; }  }  if (this.field_70173_aa % 120 == 0) setRiftStability(getRiftStability() - 0.2F);  if (this.field_70173_aa % 600 == func_145782_y() % 600) { float taint = AuraHandler.getFlux(this.field_70170_p, func_180425_c()); double size = Math.sqrt((getRiftSize() * 2)); if (taint >= size && getRiftSize() < 100 && getStability() != EnumStability.VERY_STABLE) { AuraHandler.drainFlux(func_130014_f_(), func_180425_c(), (float)size, false); setRiftSize(getRiftSize() + 1); }  if (getRiftStability() < 0.0F && this.field_70146_Z.nextInt(1000) < Math.abs(getRiftStability()) + getRiftSize()) executeRiftEvent();  }  if (!this.field_70128_L && this.field_70173_aa % 300 == 0) func_184185_a(SoundsTC.evilportal, (float)(0.15000000596046448D + this.field_70146_Z.nextGaussian() * 0.066D), (float)(0.75D + this.field_70146_Z.nextGaussian() * 0.1D));  } else { if (!this.points.isEmpty() && this.points.size() > 2 && !getCollapse() && getRiftStability() < 0.0F && this.field_70146_Z.nextInt(150) < Math.abs(getRiftStability())) { int pi = 1 + this.field_70146_Z.nextInt(this.points.size() - 2); Vec3d v1 = ((Vec3d)this.points.get(pi)).func_72441_c(this.field_70165_t, this.field_70163_u, this.field_70161_v); FXDispatcher.INSTANCE.drawCurlyWisp(v1.field_72450_a, v1.field_72448_b, v1.field_72449_c, 0.0D, 0.0D, 0.0D, 0.1F + ((Float)this.pointsWidth.get(pi)).floatValue() * 3.0F, 1.0F, 1.0F, 1.0F, 0.25F, null, 1, 0, 0); }  if (!this.points.isEmpty() && this.points.size() > 2 && getCollapse()) { int pi = 1 + this.field_70146_Z.nextInt(this.points.size() - 2); Vec3d v1 = ((Vec3d)this.points.get(pi)).func_72441_c(this.field_70165_t, this.field_70163_u, this.field_70161_v); FXDispatcher.INSTANCE.drawCurlyWisp(v1.field_72450_a, v1.field_72448_b, v1.field_72449_c, 0.0D, 0.0D, 0.0D, 0.1F + ((Float)this.pointsWidth.get(pi)).floatValue() * 3.0F, 1.0F, 0.3F + this.field_70146_Z.nextFloat() * 0.1F, 0.3F + this.field_70146_Z.nextFloat() * 0.1F, 0.4F, null, 1, 0, 0); }  }  } public static void createRift(World world, BlockPos pos) { pos = pos.func_177982_a(world.field_73012_v.nextInt(16), 0, world.field_73012_v.nextInt(16)); BlockPos p2 = world.func_175725_q(pos); if (!world.field_73011_w.func_191066_m()) for (p2 = new BlockPos(p2.func_177958_n(), 10, p2.func_177952_p()); !world.func_175623_d(p2); p2 = p2.func_177981_b(world.field_73012_v.nextInt(5) + 1)) { if (p2.func_177956_o() > world.func_72940_L() - 5) return;  }   if (p2.func_177956_o() < world.func_72940_L() - 4) { if (EntityUtils.getEntitiesInRange(world, p2, null, EntityFluxRift.class, 32.0D).size() > 0) return;  EntityFluxRift rift = new EntityFluxRift(world); rift.setRiftSeed(world.field_73012_v.nextInt()); rift.func_70012_b(p2.func_177958_n() + 0.5D, p2.func_177956_o() + 0.5D, p2.func_177952_p() + 0.5D, world.field_73012_v.nextInt(360), 0.0F); float taint = AuraHandler.getFlux(world, p2); double size = Math.sqrt((taint * 3.0F)); if (size > 5.0D && world.func_72838_d(rift)) { rift.setRiftSize((int)size); AuraHandler.drainFlux(world, p2, (float)size, false); List<EntityPlayer> targets2 = world.func_72872_a(EntityPlayer.class, (new AxisAlignedBB(p2.func_177958_n(), p2.func_177956_o(), p2.func_177952_p(), (p2.func_177958_n() + 1), (p2.func_177956_o() + 1), (p2.func_177952_p() + 1))).func_72314_b(32.0D, 32.0D, 32.0D)); if (targets2 != null && targets2.size() > 0) for (EntityPlayer target : targets2) { IPlayerKnowledge knowledge = ThaumcraftCapabilities.getKnowledge(target); if (!knowledge.isResearchKnown("f_toomuchflux")) { target.func_146105_b(new TextComponentString("§5§o" + I18n.func_74838_a("tc.fluxevent.3")), true); ThaumcraftApi.internalMethods.completeResearch(target, "f_toomuchflux"); }  }   }  }  } private void executeRiftEvent() { EntityPlayer target; List<EntityLivingBase> targets2; EntityTaintSeedPrime seed; EntityWisp wisp; RandomItemChooser ric = new RandomItemChooser(); FluxEventEntry ei = (FluxEventEntry)ric.chooseOnWeight(events); if (ei == null) return;  if (!ei.nearTaintAllowed && TaintHelper.isNearTaintSeed(this.field_70170_p, func_180425_c())) return;  boolean didit = false; switch (ei.event) { case 0: wisp = new EntityWisp(this.field_70170_p); wisp.func_70012_b(this.field_70165_t + this.field_70146_Z.nextGaussian() * 5.0D, this.field_70163_u + this.field_70146_Z.nextGaussian() * 5.0D, this.field_70161_v + this.field_70146_Z.nextGaussian() * 5.0D, 0.0F, 0.0F); if (this.field_70170_p.field_73012_v.nextInt(5) == 0) wisp.setType(Aspect.FLUX.getTag());  if (wisp.func_70601_bi() && this.field_70170_p.func_72838_d(wisp)) didit = true;  break;case 1: seed = new EntityTaintSeedPrime(this.field_70170_p); seed.func_70012_b((int)(this.field_70165_t + this.field_70146_Z.nextGaussian() * 5.0D) + 0.5D, (int)(this.field_70163_u + this.field_70146_Z.nextGaussian() * 5.0D), (int)(this.field_70161_v + this.field_70146_Z.nextGaussian() * 5.0D) + 0.5D, this.field_70170_p.field_73012_v.nextInt(360), 0.0F); if (seed.func_70601_bi() && this.field_70170_p.func_72838_d(seed)) { didit = true; seed.boost = getRiftSize(); AuraHelper.polluteAura(func_130014_f_(), func_180425_c(), (getRiftSize() / 2), true); func_70106_y(); }  break;case 2: targets2 = this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(16.0D, 16.0D, 16.0D)); if (targets2 != null && targets2.size() > 0) for (EntityLivingBase target : targets2) { didit = true; if (target instanceof EntityPlayer) ((EntityPlayer)target).func_146105_b(new TextComponentString("§5§o" + I18n.func_74838_a("tc.fluxevent.2")), true);  PotionEffect pe = new PotionEffect(PotionInfectiousVisExhaust.instance, 3000, 2); pe.getCurativeItems().clear(); try { target.func_70690_d(pe); } catch (Exception exception) {} }   break;case 3: target = this.field_70170_p.func_72890_a(this, 16.0D); if (target != null) { FocusPackage p = new FocusPackage(target); FocusMediumRoot root = new FocusMediumRoot(); root.setupFromCasterToTarget(target, target, 0.5D); p.addNode(root); FocusMediumCloud fp = new FocusMediumCloud(); fp.initialize(); fp.getSetting("radius").setValue(MathHelper.func_76136_a(this.field_70146_Z, 1, 3)); fp.getSetting("duration").setValue(MathHelper.func_76136_a(this.field_70146_Z, Math.min(getRiftSize() / 2, 30), Math.min(getRiftSize(), 120))); p.addNode(fp); p.addNode(new FocusEffectFlux()); FocusEngine.castFocusPackage(target, p, true); }  break;case 4: setCollapse(true); break; }  if (didit) setRiftStability(getRiftStability() + ei.cost);  } static class FluxEventEntry implements RandomItemChooser.Item {
/*     */     int weight; int event; int cost; boolean nearTaintAllowed; protected FluxEventEntry(int event, int weight, int cost, boolean nearTaintAllowed) { this.weight = weight; this.event = event; this.cost = cost; this.nearTaintAllowed = nearTaintAllowed; } public double getWeight() { return this.weight; }
/*     */   } static ArrayList<RandomItemChooser.Item> events = new ArrayList(); public ArrayList<Vec3d> points; public ArrayList<Float> pointsWidth; static  { events.add(new FluxEventEntry(0, 50, 5, true)); events.add(new FluxEventEntry(1, 10, 0, false)); events.add(new FluxEventEntry(2, 20, 10, true)); events.add(new FluxEventEntry(3, 20, 10, true)); events.add(new FluxEventEntry(4, 1, 0, true)); } public enum EnumStability {
/* 488 */     VERY_STABLE, STABLE, UNSTABLE, VERY_UNSTABLE;
/*     */   }
/*     */   
/*     */   public EnumStability getStability() {
/* 492 */     return (getRiftStability() > 50.0F) ? EnumStability.VERY_STABLE : (
/* 493 */       (getRiftStability() >= 0.0F) ? EnumStability.STABLE : (
/* 494 */       (getRiftStability() > -25.0F) ? EnumStability.UNSTABLE : EnumStability.VERY_UNSTABLE));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70015_d(int seconds) {}
/*     */ 
/*     */   
/* 502 */   public boolean func_70027_ad() { return false; }
/*     */ 
/*     */   
/* 505 */   public boolean func_90999_ad() { return false; }
/*     */   
/*     */   private void completeCollapse() {
/* 508 */     int qq = (int)Math.sqrt(this.maxSize);
/* 509 */     if (this.field_70146_Z.nextInt(100) < qq)
/* 510 */       func_70099_a(new ItemStack(ItemsTC.primordialPearl, 1, 4 + this.field_70146_Z.nextInt(4)), 0.0F); 
/* 511 */     for (int a = 0; a < qq; a++) {
/* 512 */       func_70099_a(new ItemStack(ItemsTC.voidSeed), 0.0F);
/*     */     }
/* 514 */     PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockBamf(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0, true, true, null), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w
/*     */           
/* 516 */           .getDimension(), this.field_70165_t, this.field_70163_u, this.field_70161_v, 64.0D));
/*     */     
/* 518 */     List<EntityLivingBase> list = EntityUtils.getEntitiesInRange(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this, EntityLivingBase.class, 32.0D);
/*     */     
/* 520 */     switch (getStability()) {
/*     */       case VERY_UNSTABLE:
/* 522 */         for (EntityLivingBase p : list) {
/* 523 */           int w = (int)((1.0D - p.func_70068_e(this) / 32.0D) * 120.0D);
/* 524 */           if (w > 0) p.func_70690_d(new PotionEffect(PotionFluxTaint.instance, w * 20, 0)); 
/*     */         } 
/*     */       case UNSTABLE:
/* 527 */         for (EntityLivingBase p : list) {
/* 528 */           int w = (int)((1.0D - p.func_70068_e(this) / 32.0D) * 300.0D);
/* 529 */           if (w > 0) p.func_70690_d(new PotionEffect(MobEffects.field_76437_t, w * 20, 0));
/*     */         
/*     */         } 
/*     */       case STABLE:
/* 533 */         for (EntityLivingBase p : list) {
/* 534 */           if (p instanceof EntityPlayer) {
/* 535 */             int w = (int)((1.0D - p.func_70068_e(this) / 32.0D) * 25.0D);
/* 536 */             if (w > 0) {
/* 537 */               ThaumcraftApi.internalMethods.addWarpToPlayer((EntityPlayer)p, w, IPlayerWarp.EnumWarpType.NORMAL);
/* 538 */               ThaumcraftApi.internalMethods.addWarpToPlayer((EntityPlayer)p, w, IPlayerWarp.EnumWarpType.TEMPORARY);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         break;
/*     */     } 
/* 544 */     func_70106_y();
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\EntityFluxRift.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */