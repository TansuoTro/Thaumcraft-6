/*     */ package thaumcraft.common.items.casters.foci;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.casters.FocusEffect;
/*     */ import thaumcraft.api.casters.IFocusBlockPicker;
/*     */ import thaumcraft.api.casters.NodeSetting;
/*     */ import thaumcraft.api.casters.Trajectory;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.fx.particles.FXGeneric;
/*     */ import thaumcraft.common.items.casters.ItemCaster;
/*     */ import thaumcraft.common.lib.events.ServerEvents;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FocusEffectExchange
/*     */   extends FocusEffect
/*     */   implements IFocusBlockPicker
/*     */ {
/*  28 */   public String getResearch() { return "FOCUSEXCHANGE"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   public String getKey() { return "thaumcraft.EXCHANGE"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  38 */   public Aspect getAspect() { return Aspect.EXCHANGE; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   public int getComplexity() { return (5 + getSettingValue("silk") * 4 + getSettingValue("fortune") == 0) ? 0 : ((getSettingValue("fortune") + 1) * 3); }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean execute(RayTraceResult target, Trajectory trajectory, float finalPower, int num) {
/*  48 */     if (target.field_72313_a == RayTraceResult.Type.BLOCK) {
/*  49 */       ItemStack casterStack = ItemStack.field_190927_a;
/*  50 */       if (getPackage().getCaster().func_184614_ca() != null && getPackage().getCaster().func_184614_ca().func_77973_b() instanceof ItemCaster) {
/*  51 */         casterStack = getPackage().getCaster().func_184614_ca();
/*     */       }
/*  53 */       else if (getPackage().getCaster().func_184592_cb() != null && getPackage().getCaster().func_184592_cb().func_77973_b() instanceof ItemCaster) {
/*  54 */         casterStack = getPackage().getCaster().func_184592_cb();
/*     */       } 
/*  56 */       if (casterStack.func_190926_b()) return false;
/*     */       
/*  58 */       boolean silk = (getSettingValue("silk") > 0);
/*  59 */       int fortune = getSettingValue("fortune");
/*  60 */       if (getPackage().getCaster() instanceof EntityPlayer && ((ItemCaster)casterStack
/*  61 */         .func_77973_b()).getPickedBlock(casterStack) != null && 
/*  62 */         !((ItemCaster)casterStack.func_77973_b()).getPickedBlock(casterStack).func_190926_b()) {
/*  63 */         ServerEvents.addSwapper((getPackage()).world, target.func_178782_a(), 
/*  64 */             (getPackage()).world.func_180495_p(target.func_178782_a()), ((ItemCaster)casterStack.func_77973_b()).getPickedBlock(casterStack), true, 0, (EntityPlayer)
/*  65 */             getPackage().getCaster(), true, false, 8038177, true, silk, fortune, ServerEvents.DEFAULT_PREDICATE, 0.25F + (silk ? 0.25F : 0.0F) + fortune * 0.1F);
/*     */       }
/*     */       
/*  68 */       return true;
/*     */     } 
/*     */     
/*  71 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public NodeSetting[] createSettings() {
/*  76 */     int[] silk = { 0, 1 };
/*  77 */     String[] silkDesc = { "focus.common.no", "focus.common.yes" };
/*  78 */     int[] fortune = { 0, 1, 2, 3, 4 };
/*  79 */     String[] fortuneDesc = { "focus.common.no", "I", "II", "III", "IV" };
/*  80 */     return new NodeSetting[] { new NodeSetting("fortune", "focus.common.fortune", new NodeSetting.NodeSettingIntList(fortune, fortuneDesc)), new NodeSetting("silk", "focus.common.silk", new NodeSetting.NodeSettingIntList(silk, silkDesc)) };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void renderParticleFX(World world, double x, double y, double z, double vx, double vy, double vz) {
/*  92 */     FXGeneric fb = new FXGeneric(world, x, y, z, vx + world.field_73012_v.nextGaussian() * 0.01D, vy + world.field_73012_v.nextGaussian() * 0.01D, vz + world.field_73012_v.nextGaussian() * 0.01D);
/*  93 */     fb.func_187114_a(9);
/*  94 */     fb.func_70538_b(0.25F + world.field_73012_v.nextFloat() * 0.25F, 0.25F + world.field_73012_v.nextFloat() * 0.25F, 0.25F + world.field_73012_v.nextFloat() * 0.25F);
/*  95 */     fb.setAlphaF(new float[] { 0.0F, 0.6F, 0.6F, 0.0F });
/*  96 */     fb.setGridSize(64);
/*  97 */     fb.setParticles(448, 9, 1);
/*  98 */     fb.setScale(new float[] { 0.5F, 0.25F });
/*  99 */     fb.setGravity((float)(world.field_73012_v.nextGaussian() * 0.009999999776482582D));
/* 100 */     fb.setRandomMovementScale(0.0025F, 0.0025F, 0.0025F);
/* 101 */     ParticleEngine.addEffect(world, fb);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 106 */   public void onCast(Entity caster) { caster.field_70170_p.func_184133_a(null, caster.func_180425_c().func_177984_a(), SoundEvents.field_190021_aL, SoundCategory.PLAYERS, 0.2F, 2.0F + (float)(caster.field_70170_p.field_73012_v.nextGaussian() * 0.05000000074505806D)); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\casters\foci\FocusEffectExchange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */