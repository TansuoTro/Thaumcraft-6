/*    */ package thaumcraft.common.items.casters.foci;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.SoundEvents;
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
/*    */ import thaumcraft.common.lib.events.ServerEvents;
/*    */ import thaumcraft.common.lib.network.PacketHandler;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXFocusPartImpact;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FocusEffectBreak
/*    */   extends FocusEffect
/*    */ {
/* 29 */   public String getResearch() { return "FOCUSBREAK"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 34 */   public String getKey() { return "thaumcraft.BREAK"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 39 */   public Aspect getAspect() { return Aspect.ENTROPY; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 44 */   public int getComplexity() { return getSettingValue("power") * 3 + getSettingValue("silk") * 4 + ((getSettingValue("fortune") == 0) ? 0 : ((getSettingValue("fortune") + 1) * 3)); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean execute(RayTraceResult target, Trajectory trajectory, float finalPower, int num) {
/* 49 */     if (target.field_72313_a == RayTraceResult.Type.BLOCK) {
/* 50 */       PacketHandler.INSTANCE.sendToAllAround(new PacketFXFocusPartImpact(target
/* 51 */             .func_178782_a().func_177958_n() + 0.5D, target.func_178782_a().func_177956_o() + 0.5D, target.func_178782_a().func_177952_p() + 0.5D, new String[] { getKey() }), new NetworkRegistry.TargetPoint(
/* 52 */             (getPackage()).world.field_73011_w.getDimension(), target.field_72307_f.field_72450_a, target.field_72307_f.field_72448_b, target.field_72307_f.field_72449_c, 64.0D));
/*    */       
/* 54 */       boolean silk = (getSettingValue("silk") > 0);
/* 55 */       int fortune = getSettingValue("fortune");
/* 56 */       float strength = getSettingValue("power") * finalPower;
/*    */       
/* 58 */       float dur = (getPackage()).world.func_180495_p(target.func_178782_a()).func_185887_b((getPackage()).world, target.func_178782_a()) * 100.0F;
/* 59 */       dur = (float)Math.sqrt(dur);
/* 60 */       if (getPackage().getCaster() instanceof EntityPlayer) {
/* 61 */         ServerEvents.addBreaker((getPackage()).world, target.func_178782_a(), 
/* 62 */             (getPackage()).world.func_180495_p(target.func_178782_a()), (EntityPlayer)getPackage().getCaster(), true, silk, fortune, strength, dur, dur, (int)(dur / strength / 3.0F * num), 0.25F + (silk ? 0.25F : 0.0F) + fortune * 0.1F, null);
/*    */       }
/*    */       
/* 65 */       return true;
/*    */     } 
/*    */ 
/*    */     
/* 69 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public NodeSetting[] createSettings() {
/* 74 */     int[] silk = { 0, 1 };
/* 75 */     String[] silkDesc = { "focus.common.no", "focus.common.yes" };
/* 76 */     int[] fortune = { 0, 1, 2, 3, 4 };
/* 77 */     String[] fortuneDesc = { "focus.common.no", "I", "II", "III", "IV" };
/* 78 */     return new NodeSetting[] { new NodeSetting("power", "focus.break.power", new NodeSetting.NodeSettingIntRange(1, 5)), new NodeSetting("fortune", "focus.common.fortune", new NodeSetting.NodeSettingIntList(fortune, fortuneDesc)), new NodeSetting("silk", "focus.common.silk", new NodeSetting.NodeSettingIntList(silk, silkDesc)) };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void renderParticleFX(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 88 */     FXGeneric fb = new FXGeneric(world, posX, posY, posZ, motionX, motionY, motionZ);
/* 89 */     fb.func_187114_a(6 + world.field_73012_v.nextInt(6));
/* 90 */     int q = world.field_73012_v.nextInt(4);
/* 91 */     fb.setParticles(704 + q * 3, 3, 1);
/* 92 */     fb.setSlowDown(0.8D);
/* 93 */     fb.setScale(new float[] { (float)(1.7000000476837158D + world.field_73012_v.nextGaussian() * 0.30000001192092896D) });
/* 94 */     ParticleEngine.addEffect(world, fb);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 99 */   public void onCast(Entity caster) { caster.field_70170_p.func_184133_a(null, caster.func_180425_c().func_177984_a(), SoundEvents.field_187598_bd, SoundCategory.PLAYERS, 0.1F, 2.0F + (float)(caster.field_70170_p.field_73012_v.nextGaussian() * 0.05000000074505806D)); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\casters\foci\FocusEffectBreak.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */