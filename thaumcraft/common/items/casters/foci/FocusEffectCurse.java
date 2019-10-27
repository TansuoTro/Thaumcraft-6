/*     */ package thaumcraft.common.items.casters.foci;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.MobEffects;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.casters.FocusEffect;
/*     */ import thaumcraft.api.casters.NodeSetting;
/*     */ import thaumcraft.api.casters.Trajectory;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.fx.particles.FXGeneric;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXBlockBamf;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FocusEffectCurse
/*     */   extends FocusEffect
/*     */ {
/*  32 */   public String getResearch() { return "FOCUSCURSE"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  37 */   public String getKey() { return "thaumcraft.CURSE"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   public Aspect getAspect() { return Aspect.DEATH; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  47 */   public int getComplexity() { return getSettingValue("duration") + getSettingValue("power") * 3; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   public float getDamageForDisplay(float finalPower) { return (1.0F + getSettingValue("power")) * finalPower; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean execute(RayTraceResult target, Trajectory trajectory, float finalPower, int num) {
/*  57 */     PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockBamf(target.field_72307_f.field_72450_a, target.field_72307_f.field_72448_b, target.field_72307_f.field_72449_c, 6946821, true, true, null), new NetworkRegistry.TargetPoint(
/*     */           
/*  59 */           (getPackage()).world.field_73011_w.getDimension(), target.field_72307_f.field_72450_a, target.field_72307_f.field_72448_b, target.field_72307_f.field_72449_c, 64.0D));
/*     */     
/*  61 */     if (target.field_72313_a == RayTraceResult.Type.ENTITY && target.field_72308_g != null) {
/*     */       
/*  63 */       float damage = getDamageForDisplay(finalPower);
/*  64 */       int duration = 20 * getSettingValue("duration");
/*  65 */       int eff = (int)(getSettingValue("power") * finalPower / 2.0F);
/*     */       
/*  67 */       if (eff < 0) eff = 0; 
/*  68 */       target.field_72308_g.func_70097_a(DamageSource.func_76354_b((target.field_72308_g != null) ? target.field_72308_g : getPackage().getCaster(), getPackage().getCaster()), damage);
/*  69 */       if (target.field_72308_g instanceof EntityLivingBase) {
/*  70 */         ((EntityLivingBase)target.field_72308_g).func_70690_d(new PotionEffect(MobEffects.field_76436_u, duration, Math.round(eff)));
/*  71 */         float c = 0.85F;
/*  72 */         if ((getPackage()).world.field_73012_v.nextFloat() < c) {
/*  73 */           ((EntityLivingBase)target.field_72308_g).func_70690_d(new PotionEffect(MobEffects.field_76421_d, duration, Math.round(eff)));
/*  74 */           c -= 0.15F;
/*     */         } 
/*  76 */         if ((getPackage()).world.field_73012_v.nextFloat() < c) {
/*  77 */           ((EntityLivingBase)target.field_72308_g).func_70690_d(new PotionEffect(MobEffects.field_76437_t, duration, Math.round(eff)));
/*  78 */           c -= 0.15F;
/*     */         } 
/*  80 */         if ((getPackage()).world.field_73012_v.nextFloat() < c) {
/*  81 */           ((EntityLivingBase)target.field_72308_g).func_70690_d(new PotionEffect(MobEffects.field_76419_f, duration * 2, Math.round(eff)));
/*  82 */           c -= 0.15F;
/*     */         } 
/*  84 */         if ((getPackage()).world.field_73012_v.nextFloat() < c) {
/*  85 */           ((EntityLivingBase)target.field_72308_g).func_70690_d(new PotionEffect(MobEffects.field_76438_s, duration * 3, Math.round(eff)));
/*  86 */           c -= 0.15F;
/*     */         } 
/*  88 */         if ((getPackage()).world.field_73012_v.nextFloat() < c) {
/*  89 */           ((EntityLivingBase)target.field_72308_g).func_70690_d(new PotionEffect(MobEffects.field_189112_A, duration * 3, Math.round(eff)));
/*     */         }
/*     */       }
/*     */     
/*     */     }
/*  94 */     else if (target.field_72313_a == RayTraceResult.Type.BLOCK) {
/*     */       
/*  96 */       float f = (float)Math.min(8.0D, 1.5D * getSettingValue("power") * finalPower);
/*     */       
/*  98 */       for (BlockPos.MutableBlockPos blockpos$mutableblockpos1 : BlockPos.func_177975_b(target.func_178782_a().func_177963_a(-f, -f, -f), target.func_178782_a().func_177963_a(f, f, f))) {
/*     */         
/* 100 */         if (blockpos$mutableblockpos1.func_177957_d(target.field_72307_f.field_72450_a, target.field_72307_f.field_72448_b, target.field_72307_f.field_72449_c) <= (f * f))
/*     */         {
/* 102 */           if ((getPackage()).world.func_175623_d(blockpos$mutableblockpos1.func_177984_a()) && (getPackage()).world.func_175665_u(blockpos$mutableblockpos1))
/*     */           {
/* 104 */             (getPackage()).world.func_175656_a(blockpos$mutableblockpos1.func_177984_a(), BlocksTC.effectSap.func_176223_P());
/*     */           }
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 110 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 115 */   public NodeSetting[] createSettings() { return new NodeSetting[] { new NodeSetting("power", "focus.common.power", new NodeSetting.NodeSettingIntRange(1, 5)), new NodeSetting("duration", "focus.common.duration", new NodeSetting.NodeSettingIntRange(1, 10)) }; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void renderParticleFX(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 124 */     FXGeneric fb = new FXGeneric(world, posX, posY, posZ, motionX, motionY, motionZ);
/* 125 */     fb.func_187114_a(8);
/* 126 */     fb.func_70538_b(0.41F + world.field_73012_v.nextFloat() * 0.2F, 0.0F, 0.019F + world.field_73012_v.nextFloat() * 0.2F);
/* 127 */     fb.setAlphaF(new float[] { 0.0F, world.field_73012_v.nextFloat(), world.field_73012_v.nextFloat(), world.field_73012_v.nextFloat(), 0.0F });
/* 128 */     fb.setGridSize(16);
/* 129 */     fb.setParticles(72 + world.field_73012_v.nextInt(4), 1, 1);
/* 130 */     fb.setScale(new float[] { 2.0F + world.field_73012_v.nextFloat() * 4.0F });
/* 131 */     fb.setLoop(false);
/* 132 */     fb.setSlowDown(0.9D);
/* 133 */     fb.setGravity(0.0F);
/* 134 */     fb.setRotationSpeed(world.field_73012_v.nextFloat(), 0.0F);
/* 135 */     ParticleEngine.addEffectWithDelay(world, fb, world.field_73012_v.nextInt(4));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 140 */   public void onCast(Entity caster) { caster.field_70170_p.func_184133_a(null, caster.func_180425_c().func_177984_a(), SoundEvents.field_187514_aD, SoundCategory.PLAYERS, 0.15F, 1.0F + (caster.func_130014_f_()).field_73012_v.nextFloat() / 2.0F); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\casters\foci\FocusEffectCurse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */