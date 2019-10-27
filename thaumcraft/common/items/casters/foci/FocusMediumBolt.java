/*    */ package thaumcraft.common.items.casters.foci;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.casters.FocusEffect;
/*    */ import thaumcraft.api.casters.FocusEngine;
/*    */ import thaumcraft.api.casters.Trajectory;
/*    */ import thaumcraft.common.lib.network.PacketHandler;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXZap;
/*    */ import thaumcraft.common.lib.utils.EntityUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FocusMediumBolt
/*    */   extends FocusMediumTouch
/*    */ {
/* 21 */   public String getResearch() { return "FOCUSBOLT"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 26 */   public String getKey() { return "thaumcraft.BOLT"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   public int getComplexity() { return 5; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 36 */   public Aspect getAspect() { return Aspect.ENERGY; }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean execute(Trajectory trajectory) {
/* 41 */     float range = 16.0F;
/* 42 */     Vec3d end = trajectory.direction.func_72432_b();
/* 43 */     RayTraceResult ray = EntityUtils.getPointedEntityRay((getPackage()).world, getPackage().getCaster(), trajectory.source, end, 0.25D, range, 0.25F, false);
/*    */ 
/*    */     
/* 46 */     if (ray == null) {
/* 47 */       end = end.func_186678_a(range);
/* 48 */       end = end.func_178787_e(trajectory.source);
/* 49 */       ray = (getPackage()).world.func_72933_a(trajectory.source, end);
/* 50 */       if (ray != null) {
/* 51 */         end = ray.field_72307_f;
/*    */       }
/*    */     }
/* 54 */     else if (ray.field_72308_g != null) {
/* 55 */       end = end.func_186678_a(trajectory.source.func_72438_d(ray.field_72308_g.func_174791_d()));
/* 56 */       end = end.func_178787_e(trajectory.source);
/*    */     } 
/*    */ 
/*    */     
/* 60 */     int r = 0;
/* 61 */     int g = 0;
/* 62 */     int b = 0;
/* 63 */     for (FocusEffect ef : getPackage().getFocusEffects()) {
/* 64 */       Color c = new Color(FocusEngine.getElementColor(ef.getKey()));
/* 65 */       r += c.getRed();
/* 66 */       g += c.getGreen();
/* 67 */       b += c.getBlue();
/*    */     } 
/* 69 */     r /= getPackage().getFocusEffects().length;
/* 70 */     g /= getPackage().getFocusEffects().length;
/* 71 */     b /= getPackage().getFocusEffects().length;
/* 72 */     Color c = new Color(r, g, b);
/*    */     
/* 74 */     PacketHandler.INSTANCE.sendToAllAround(new PacketFXZap(trajectory.source, end, c
/* 75 */           .getRGB(), getPackage().getPower() * 0.66F), new NetworkRegistry.TargetPoint(
/* 76 */           (getPackage()).world.field_73011_w.getDimension(), trajectory.source.field_72450_a, trajectory.source.field_72448_b, trajectory.source.field_72449_c, 64.0D));
/*    */     
/* 78 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\casters\foci\FocusMediumBolt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */