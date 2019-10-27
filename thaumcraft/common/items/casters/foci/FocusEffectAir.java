/*     */ package thaumcraft.common.items.casters.foci;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.casters.FocusEffect;
/*     */ import thaumcraft.api.casters.NodeSetting;
/*     */ import thaumcraft.api.casters.Trajectory;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXFocusPartImpact;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FocusEffectAir
/*     */   extends FocusEffect
/*     */ {
/*  30 */   public String getResearch() { return "FOCUSELEMENTAL"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   public String getKey() { return "thaumcraft.AIR"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  40 */   public Aspect getAspect() { return Aspect.AIR; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   public int getComplexity() { return getSettingValue("power") * 2; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   public float getDamageForDisplay(float finalPower) { return (1 + getSettingValue("power")) * finalPower; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean execute(RayTraceResult target, Trajectory trajectory, float finalPower, int num) {
/*  55 */     PacketHandler.INSTANCE.sendToAllAround(new PacketFXFocusPartImpact(target.field_72307_f.field_72450_a, target.field_72307_f.field_72448_b, target.field_72307_f.field_72449_c, new String[] {
/*  56 */             getKey() }), new NetworkRegistry.TargetPoint(
/*  57 */           (getPackage()).world.field_73011_w.getDimension(), target.field_72307_f.field_72450_a, target.field_72307_f.field_72448_b, target.field_72307_f.field_72449_c, 64.0D));
/*     */     
/*  59 */     (getPackage()).world.func_184148_a(null, target.field_72307_f.field_72450_a, target.field_72307_f.field_72448_b, target.field_72307_f.field_72449_c, SoundEvents.field_187524_aN, SoundCategory.PLAYERS, 0.5F, 0.66F);
/*     */ 
/*     */     
/*  62 */     if (target.field_72313_a == RayTraceResult.Type.ENTITY && target.field_72308_g != null) {
/*  63 */       float damage = getDamageForDisplay(finalPower);
/*  64 */       target.field_72308_g.func_70097_a(DamageSource.func_76356_a((target.field_72308_g != null) ? target.field_72308_g : getPackage().getCaster(), getPackage().getCaster()), damage);
/*  65 */       if (target.field_72308_g instanceof EntityLivingBase) {
/*  66 */         if (trajectory != null) {
/*  67 */           ((EntityLivingBase)target.field_72308_g).func_70653_a(getPackage().getCaster(), damage * 0.25F, -trajectory.direction.field_72450_a, -trajectory.direction.field_72449_c);
/*     */         } else {
/*  69 */           ((EntityLivingBase)target.field_72308_g).func_70653_a(getPackage().getCaster(), damage * 0.25F, 
/*  70 */               -MathHelper.func_76126_a(target.field_72308_g.field_70177_z * 0.017453292F), 
/*  71 */               MathHelper.func_76134_b(target.field_72308_g.field_70177_z * 0.017453292F));
/*     */         } 
/*     */       }
/*  74 */       return true;
/*     */     } 
/*  76 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  81 */   public NodeSetting[] createSettings() { return new NodeSetting[] { new NodeSetting("power", "focus.common.power", new NodeSetting.NodeSettingIntRange(1, 5)) }; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void renderParticleFX(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/*  89 */     FXDispatcher.GenPart pp = new FXDispatcher.GenPart();
/*  90 */     pp.grav = -0.1F;
/*  91 */     pp.age = 20 + world.field_73012_v.nextInt(10);
/*  92 */     pp.alpha = new float[] { 0.5F, 0.0F };
/*  93 */     pp.grid = 32;
/*  94 */     pp.partStart = 337;
/*  95 */     pp.partInc = 1;
/*  96 */     pp.partNum = 5;
/*  97 */     pp.slowDown = 0.75D;
/*  98 */     pp.rot = (float)world.field_73012_v.nextGaussian() / 2.0F;
/*  99 */     float s = (float)(2.0D + world.field_73012_v.nextGaussian() * 0.5D);
/* 100 */     pp.scale = new float[] { s, s * 2.0F };
/* 101 */     FXDispatcher.INSTANCE.drawGenericParticles(posX, posY, posZ, motionX, motionY, motionZ, pp);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 106 */   public void onCast(Entity caster) { caster.field_70170_p.func_184133_a(null, caster.func_180425_c().func_177984_a(), SoundsTC.wind, SoundCategory.PLAYERS, 0.125F, 2.0F); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\casters\foci\FocusEffectAir.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */