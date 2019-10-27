/*     */ package thaumcraft.common.items.casters.foci;
/*     */ 
/*     */ import net.minecraft.block.BlockLiquid;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.MobEffects;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.BlockPos;
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
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.fx.particles.FXGeneric;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXFocusPartImpact;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FocusEffectFrost
/*     */   extends FocusEffect
/*     */ {
/*  37 */   public String getResearch() { return "FOCUSELEMENTAL"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   public String getKey() { return "thaumcraft.FROST"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  47 */   public Aspect getAspect() { return Aspect.COLD; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   public int getComplexity() { return getSettingValue("duration") + getSettingValue("power") * 2; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public float getDamageForDisplay(float finalPower) { return (3 + getSettingValue("power")) * finalPower; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean execute(RayTraceResult target, Trajectory trajectory, float finalPower, int num) {
/*  62 */     PacketHandler.INSTANCE.sendToAllAround(new PacketFXFocusPartImpact(target.field_72307_f.field_72450_a, target.field_72307_f.field_72448_b, target.field_72307_f.field_72449_c, new String[] {
/*  63 */             getKey() }), new NetworkRegistry.TargetPoint(
/*  64 */           (getPackage()).world.field_73011_w.getDimension(), target.field_72307_f.field_72450_a, target.field_72307_f.field_72448_b, target.field_72307_f.field_72449_c, 64.0D));
/*     */     
/*  66 */     if (target.field_72313_a == RayTraceResult.Type.ENTITY && target.field_72308_g != null) {
/*  67 */       float damage = getDamageForDisplay(finalPower);
/*  68 */       int duration = 20 * getSettingValue("duration");
/*  69 */       int potency = (int)(1.0F + getSettingValue("power") * finalPower / 3.0F);
/*     */       
/*  71 */       target.field_72308_g.func_70097_a(DamageSource.func_76356_a((target.field_72308_g != null) ? target.field_72308_g : getPackage().getCaster(), getPackage().getCaster()), damage);
/*  72 */       if (target.field_72308_g instanceof EntityLivingBase) {
/*  73 */         ((EntityLivingBase)target.field_72308_g).func_70690_d(new PotionEffect(MobEffects.field_76421_d, duration, potency));
/*     */       }
/*  75 */     } else if (target.field_72313_a == RayTraceResult.Type.BLOCK) {
/*     */       
/*  77 */       float f = Math.min(16.0F, (2 * getSettingValue("power")) * finalPower);
/*     */       
/*  79 */       for (BlockPos.MutableBlockPos blockpos$mutableblockpos1 : BlockPos.func_177975_b(target.func_178782_a().func_177963_a(-f, -f, -f), target.func_178782_a().func_177963_a(f, f, f))) {
/*     */         
/*  81 */         if (blockpos$mutableblockpos1.func_177957_d(target.field_72307_f.field_72450_a, target.field_72307_f.field_72448_b, target.field_72307_f.field_72449_c) <= (f * f)) {
/*     */           
/*  83 */           IBlockState iblockstate1 = (getPackage()).world.func_180495_p(blockpos$mutableblockpos1);
/*  84 */           if (iblockstate1.func_185904_a() == Material.field_151586_h && ((Integer)iblockstate1.func_177229_b(BlockLiquid.field_176367_b)).intValue() == 0 && (getPackage()).world.func_190527_a(Blocks.field_185778_de, blockpos$mutableblockpos1, false, EnumFacing.DOWN, (Entity)null)) {
/*     */             
/*  86 */             (getPackage()).world.func_175656_a(blockpos$mutableblockpos1, Blocks.field_185778_de.func_176223_P());
/*  87 */             (getPackage()).world.func_175684_a(blockpos$mutableblockpos1.func_185334_h(), Blocks.field_185778_de, MathHelper.func_76136_a((getPackage()).world.field_73012_v, 60, 120));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  98 */   public NodeSetting[] createSettings() { return new NodeSetting[] { new NodeSetting("power", "focus.common.power", new NodeSetting.NodeSettingIntRange(1, 5)), new NodeSetting("duration", "focus.common.duration", new NodeSetting.NodeSettingIntRange(2, 10)) }; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void renderParticleFX(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 107 */     FXGeneric fb = new FXGeneric(world, posX, posY, posZ, motionX, motionY, motionZ);
/* 108 */     fb.func_187114_a(40 + world.field_73012_v.nextInt(40));
/* 109 */     fb.setAlphaF(new float[] { 1.0F, 0.0F });
/* 110 */     fb.setParticles(8, 1, 1);
/* 111 */     fb.setGravity(0.033F);
/* 112 */     fb.setSlowDown(0.8D);
/* 113 */     fb.setRandomMovementScale(0.0025F, 1.0E-4F, 0.0025F);
/* 114 */     fb.setScale(new float[] { (float)(0.699999988079071D + world.field_73012_v.nextGaussian() * 0.30000001192092896D) });
/* 115 */     fb.setRotationSpeed(world.field_73012_v.nextFloat() * 3.0F, (float)world.field_73012_v.nextGaussian() / 4.0F);
/* 116 */     ParticleEngine.addEffectWithDelay(world, fb, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 121 */   public void onCast(Entity caster) { caster.field_70170_p.func_184133_a(null, caster.func_180425_c().func_177984_a(), SoundEvents.field_187942_hp, SoundCategory.PLAYERS, 0.2F, 1.0F + (float)(caster.field_70170_p.field_73012_v.nextGaussian() * 0.05000000074505806D)); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\casters\foci\FocusEffectFrost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */