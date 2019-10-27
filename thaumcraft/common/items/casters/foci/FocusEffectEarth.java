/*     */ package thaumcraft.common.items.casters.foci;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.util.DamageSource;
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
/*     */ import thaumcraft.common.lib.events.ServerEvents;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXFocusPartImpact;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FocusEffectEarth
/*     */   extends FocusEffect
/*     */ {
/*  30 */   public String getResearch() { return "FOCUSELEMENTAL"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   public String getKey() { return "thaumcraft.EARTH"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  40 */   public Aspect getAspect() { return Aspect.EARTH; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   public int getComplexity() { return getSettingValue("power") * 3; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   public float getDamageForDisplay(float finalPower) { return (2 * getSettingValue("power")) * finalPower; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean execute(RayTraceResult target, Trajectory trajectory, float finalPower, int num) {
/*  55 */     PacketHandler.INSTANCE.sendToAllAround(new PacketFXFocusPartImpact(target.field_72307_f.field_72450_a, target.field_72307_f.field_72448_b, target.field_72307_f.field_72449_c, new String[] {
/*  56 */             getKey() }), new NetworkRegistry.TargetPoint(
/*  57 */           (getPackage()).world.field_73011_w.getDimension(), target.field_72307_f.field_72450_a, target.field_72307_f.field_72448_b, target.field_72307_f.field_72449_c, 64.0D));
/*     */     
/*  59 */     if (target.field_72313_a == RayTraceResult.Type.ENTITY && target.field_72308_g != null) {
/*  60 */       float damage = getDamageForDisplay(finalPower);
/*  61 */       target.field_72308_g.func_70097_a(DamageSource.func_76356_a((target.field_72308_g != null) ? target.field_72308_g : getPackage().getCaster(), getPackage().getCaster()), damage);
/*  62 */       return true;
/*     */     } 
/*  64 */     if (target.field_72313_a == RayTraceResult.Type.BLOCK) {
/*  65 */       BlockPos pos = target.func_178782_a();
/*  66 */       if (getPackage().getCaster() instanceof EntityPlayer && (getPackage()).world.func_180495_p(pos).func_185887_b((getPackage()).world, pos) <= getDamageForDisplay(finalPower) / 25.0F) {
/*  67 */         ServerEvents.addBreaker((getPackage()).world, pos, (getPackage()).world.func_180495_p(pos), (EntityPlayer)getPackage().getCaster(), false, false, 0, 1.0F, 0.0F, 1.0F, num, 0.1F, null);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  77 */   public NodeSetting[] createSettings() { return new NodeSetting[] { new NodeSetting("power", "focus.common.power", new NodeSetting.NodeSettingIntRange(1, 5)) }; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void renderParticleFX(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/*  85 */     FXDispatcher.GenPart pp = new FXDispatcher.GenPart();
/*  86 */     pp.grav = 0.4F;
/*  87 */     pp.layer = 1;
/*  88 */     pp.age = 20 + world.field_73012_v.nextInt(10);
/*  89 */     pp.alpha = new float[] { 1.0F, 0.0F };
/*  90 */     pp.partStart = 75 + world.field_73012_v.nextInt(4);
/*  91 */     pp.partInc = 1;
/*  92 */     pp.partNum = 1;
/*  93 */     pp.slowDown = 0.9D;
/*  94 */     pp.rot = (float)world.field_73012_v.nextGaussian();
/*  95 */     float s = (float)(1.0D + world.field_73012_v.nextGaussian() * 0.20000000298023224D);
/*  96 */     pp.scale = new float[] { s, s / 2.0F };
/*  97 */     FXDispatcher.INSTANCE.drawGenericParticles(posX, posY, posZ, motionX, motionY, motionZ, pp);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 102 */   public void onCast(Entity caster) { caster.field_70170_p.func_184133_a(null, caster.func_180425_c().func_177984_a(), SoundEvents.field_187523_aM, SoundCategory.PLAYERS, 0.25F, 1.0F + (float)(caster.field_70170_p.field_73012_v.nextGaussian() * 0.05000000074505806D)); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\casters\foci\FocusEffectEarth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */