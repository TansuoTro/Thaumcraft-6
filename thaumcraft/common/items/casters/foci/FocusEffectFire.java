/*     */ package thaumcraft.common.items.casters.foci;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.util.EntityDamageSourceIndirect;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.BlockPos;
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
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXFocusPartImpact;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FocusEffectFire
/*     */   extends FocusEffect
/*     */ {
/*  29 */   public String getResearch() { return "BASEAUROMANCY"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  34 */   public String getKey() { return "thaumcraft.FIRE"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   public Aspect getAspect() { return Aspect.FIRE; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   public int getComplexity() { return getSettingValue("duration") + getSettingValue("power") * 2; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   public float getDamageForDisplay(float finalPower) { return (3 + getSettingValue("power")) * finalPower; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean execute(RayTraceResult target, Trajectory trajectory, float finalPower, int num) {
/*  54 */     PacketHandler.INSTANCE.sendToAllAround(new PacketFXFocusPartImpact(target.field_72307_f.field_72450_a, target.field_72307_f.field_72448_b, target.field_72307_f.field_72449_c, new String[] {
/*  55 */             getKey() }), new NetworkRegistry.TargetPoint(
/*  56 */           (getPackage()).world.field_73011_w.getDimension(), target.field_72307_f.field_72450_a, target.field_72307_f.field_72448_b, target.field_72307_f.field_72449_c, 64.0D));
/*     */     
/*  58 */     if (target.field_72313_a == RayTraceResult.Type.ENTITY && target.field_72308_g != null) {
/*  59 */       if (target.field_72308_g.func_70045_F()) return false;
/*     */       
/*  61 */       float fire = (1 + getSettingValue("duration") * getSettingValue("duration"));
/*  62 */       float damage = getDamageForDisplay(finalPower);
/*  63 */       fire *= finalPower;
/*     */ 
/*     */ 
/*     */       
/*  67 */       target.field_72308_g.func_70097_a((new EntityDamageSourceIndirect("fireball", (target.field_72308_g != null) ? target.field_72308_g : getPackage().getCaster(), getPackage().getCaster())).func_76361_j(), damage);
/*  68 */       if (fire > 0.0F) target.field_72308_g.func_70015_d(Math.round(fire)); 
/*  69 */       return true;
/*     */     } 
/*  71 */     if (target.field_72313_a == RayTraceResult.Type.BLOCK && getSettingValue("duration") > 0) {
/*  72 */       BlockPos pos = target.func_178782_a();
/*  73 */       pos = pos.func_177972_a(target.field_178784_b);
/*  74 */       if ((getPackage()).world.func_175623_d(pos) && (getPackage()).world.field_73012_v.nextFloat() < finalPower) {
/*     */         
/*  76 */         (getPackage()).world.func_184133_a(null, pos, SoundEvents.field_187649_bu, SoundCategory.BLOCKS, 1.0F, (getPackage()).world.field_73012_v.nextFloat() * 0.4F + 0.8F);
/*  77 */         (getPackage()).world.func_180501_a(pos, Blocks.field_150480_ab.func_176223_P(), 11);
/*  78 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/*  82 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  87 */   public NodeSetting[] createSettings() { return new NodeSetting[] { new NodeSetting("power", "focus.common.power", new NodeSetting.NodeSettingIntRange(1, 5)), new NodeSetting("duration", "focus.fire.burn", new NodeSetting.NodeSettingIntRange(0, 5)) }; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void renderParticleFX(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/*  96 */     FXDispatcher.GenPart pp = new FXDispatcher.GenPart();
/*  97 */     pp.grav = -0.2F;
/*  98 */     pp.age = 10;
/*  99 */     pp.alpha = new float[] { 0.7F };
/* 100 */     pp.partStart = 640;
/* 101 */     pp.partInc = 1;
/* 102 */     pp.partNum = 10;
/* 103 */     pp.slowDown = 0.75D;
/* 104 */     pp.scale = new float[] { (float)(1.5D + world.field_73012_v.nextGaussian() * 0.20000000298023224D) };
/* 105 */     FXDispatcher.INSTANCE.drawGenericParticles(posX, posY, posZ, motionX, motionY, motionZ, pp);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 110 */   public void onCast(Entity caster) { caster.field_70170_p.func_184133_a(null, caster.func_180425_c().func_177984_a(), SoundEvents.field_187616_bj, SoundCategory.PLAYERS, 1.0F, 1.0F + (float)(caster.field_70170_p.field_73012_v.nextGaussian() * 0.05000000074505806D)); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\casters\foci\FocusEffectFire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */