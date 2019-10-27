/*    */ package thaumcraft.common.items.casters.foci;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.init.SoundEvents;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.casters.FocusEffect;
/*    */ import thaumcraft.api.casters.NodeSetting;
/*    */ import thaumcraft.api.casters.Trajectory;
/*    */ import thaumcraft.client.fx.ParticleEngine;
/*    */ import thaumcraft.client.fx.particles.FXGeneric;
/*    */ import thaumcraft.common.lib.network.PacketHandler;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXFocusPartImpact;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FocusEffectHeal
/*    */   extends FocusEffect
/*    */ {
/* 28 */   public String getResearch() { return "FOCUSHEAL"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 33 */   public String getKey() { return "thaumcraft.HEAL"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   public Aspect getAspect() { return Aspect.LIFE; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 43 */   public int getComplexity() { return getSettingValue("power") * 4; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 48 */   public float getDamageForDisplay(float finalPower) { return -getSettingValue("power") * finalPower; }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean execute(RayTraceResult target, Trajectory trajectory, float finalPower, int num) {
/* 53 */     PacketHandler.INSTANCE.sendToAllAround(new PacketFXFocusPartImpact(target.field_72307_f.field_72450_a, target.field_72307_f.field_72448_b, target.field_72307_f.field_72449_c, new String[] {
/* 54 */             getKey() }), new NetworkRegistry.TargetPoint(
/* 55 */           (getPackage()).world.field_73011_w.getDimension(), target.field_72307_f.field_72450_a, target.field_72307_f.field_72448_b, target.field_72307_f.field_72449_c, 64.0D));
/*    */     
/* 57 */     if (target.field_72313_a == RayTraceResult.Type.ENTITY && target.field_72308_g != null && target.field_72308_g instanceof EntityLivingBase)
/* 58 */       if (((EntityLivingBase)target.field_72308_g).func_70662_br()) {
/* 59 */         target.field_72308_g.func_70097_a(DamageSource.func_76354_b(getPackage().getCaster(), getPackage().getCaster()), getSettingValue("power") * finalPower * 1.5F);
/*    */       } else {
/* 61 */         ((EntityLivingBase)target.field_72308_g).func_70691_i(getSettingValue("power") * finalPower);
/*    */       }  
/* 63 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 68 */   public NodeSetting[] createSettings() { return new NodeSetting[] { new NodeSetting("power", "focus.heal.power", new NodeSetting.NodeSettingIntRange(1, 5)) }; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 75 */   public void onCast(Entity caster) { caster.field_70170_p.func_184133_a(null, caster.func_180425_c().func_177984_a(), SoundEvents.field_187542_ac, SoundCategory.PLAYERS, 2.0F, 2.0F + (float)(caster.field_70170_p.field_73012_v.nextGaussian() * 0.10000000149011612D)); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void renderParticleFX(World world, double x, double y, double z, double vx, double vy, double vz) {
/* 84 */     FXGeneric fb = new FXGeneric(world, x, y, z, vx + world.field_73012_v.nextGaussian() * 0.01D, vy + world.field_73012_v.nextGaussian() * 0.01D, vz + world.field_73012_v.nextGaussian() * 0.01D);
/* 85 */     fb.func_187114_a((int)(10.0F + 10.0F * world.field_73012_v.nextFloat()));
/* 86 */     fb.func_70538_b(1.0F, 1.0F, 1.0F);
/* 87 */     fb.setAlphaF(new float[] { 0.0F, 0.7F, 0.7F, 0.0F });
/* 88 */     fb.setGridSize(64);
/* 89 */     fb.setParticles(0, 1, 1);
/* 90 */     fb.setScale(new float[] { world.field_73012_v.nextFloat() * 2.0F, world.field_73012_v.nextFloat() });
/* 91 */     fb.setSlowDown(0.8D);
/* 92 */     fb.setGravity((float)(world.field_73012_v.nextGaussian() * 0.10000000149011612D));
/* 93 */     fb.setRandomMovementScale(0.0125F, 0.0125F, 0.0125F);
/* 94 */     fb.setRotationSpeed((float)world.field_73012_v.nextGaussian());
/* 95 */     ParticleEngine.addEffectWithDelay(world, fb, world.field_73012_v.nextInt(4));
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\casters\foci\FocusEffectHeal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */