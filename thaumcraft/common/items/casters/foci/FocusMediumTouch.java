/*     */ package thaumcraft.common.items.casters.foci;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.casters.FocusEffect;
/*     */ import thaumcraft.api.casters.FocusMedium;
/*     */ import thaumcraft.api.casters.FocusNode;
/*     */ import thaumcraft.api.casters.Trajectory;
/*     */ import thaumcraft.codechicken.lib.raytracer.RayTracer;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXFocusEffect;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FocusMediumTouch
/*     */   extends FocusMedium
/*     */ {
/*  23 */   public String getResearch() { return "BASEAUROMANCY"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  28 */   public String getKey() { return "thaumcraft.TOUCH"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   public int getComplexity() { return 2; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  38 */   public FocusNode.EnumSupplyType[] willSupply() { return new FocusNode.EnumSupplyType[] { FocusNode.EnumSupplyType.TRAJECTORY, FocusNode.EnumSupplyType.TARGET }; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   public Aspect getAspect() { return Aspect.AVERSION; }
/*     */ 
/*     */ 
/*     */   
/*     */   public Trajectory[] supplyTrajectories() {
/*  48 */     if (getParent() == null) return new Trajectory[0]; 
/*  49 */     ArrayList<Trajectory> trajectories = new ArrayList<Trajectory>();
/*  50 */     double range = (this instanceof FocusMediumBolt) ? 16.0D : RayTracer.getBlockReachDistance((EntityPlayer)getPackage().getCaster());
/*  51 */     for (Trajectory sT : getParent().supplyTrajectories()) {
/*  52 */       Vec3d end = sT.direction.func_72432_b();
/*  53 */       RayTraceResult ray = EntityUtils.getPointedEntityRay((getPackage()).world, getPackage().getCaster(), sT.source, end, 0.25D, range, 0.25F, false);
/*     */       
/*  55 */       if (ray == null) {
/*  56 */         end = end.func_186678_a(range);
/*  57 */         end = end.func_178787_e(sT.source);
/*  58 */         ray = (getPackage()).world.func_72933_a(sT.source, end);
/*  59 */         if (ray != null) {
/*  60 */           end = ray.field_72307_f;
/*     */         }
/*     */       }
/*  63 */       else if (ray.field_72308_g != null) {
/*  64 */         end = end.func_186678_a(sT.source.func_72438_d(ray.field_72308_g.func_174791_d()));
/*  65 */         end = end.func_178787_e(sT.source);
/*     */       } 
/*     */ 
/*     */       
/*  69 */       trajectories.add(new Trajectory(end, sT.direction.func_72432_b()));
/*     */     } 
/*  71 */     return (Trajectory[])trajectories.toArray(new Trajectory[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public RayTraceResult[] supplyTargets() {
/*  76 */     if (getParent() == null || !(getPackage().getCaster() instanceof EntityPlayer)) return new RayTraceResult[0]; 
/*  77 */     ArrayList<RayTraceResult> targets = new ArrayList<RayTraceResult>();
/*  78 */     double range = (this instanceof FocusMediumBolt) ? 16.0D : RayTracer.getBlockReachDistance((EntityPlayer)getPackage().getCaster());
/*  79 */     for (Trajectory sT : getParent().supplyTrajectories()) {
/*  80 */       Vec3d end = sT.direction.func_72432_b();
/*  81 */       RayTraceResult ray = EntityUtils.getPointedEntityRay((getPackage()).world, getPackage().getCaster(), sT.source, end, 0.25D, range, 0.25F, false);
/*     */       
/*  83 */       if (ray == null) {
/*  84 */         end = end.func_186678_a(range);
/*  85 */         end = end.func_178787_e(sT.source);
/*  86 */         ray = (getPackage()).world.func_72933_a(sT.source, end);
/*     */       } 
/*  88 */       if (ray != null) targets.add(ray); 
/*     */     } 
/*  90 */     return (RayTraceResult[])targets.toArray(new RayTraceResult[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean execute(Trajectory trajectory) {
/*  95 */     FocusEffect[] fe = getPackage().getFocusEffects();
/*  96 */     if (fe != null && fe.length > 0) {
/*  97 */       String[] effects = new String[fe.length];
/*  98 */       for (int a = 0; a < fe.length; ) { effects[a] = fe[a].getKey(); a++; }
/*  99 */        PacketHandler.INSTANCE.sendToAllAround(new PacketFXFocusEffect((float)trajectory.source.field_72450_a, (float)trajectory.source.field_72448_b, (float)trajectory.source.field_72449_c, (float)trajectory.direction.field_72450_a / 2.0F, (float)trajectory.direction.field_72448_b / 2.0F, (float)trajectory.direction.field_72449_c / 2.0F, effects), new NetworkRegistry.TargetPoint(
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 104 */             (getPackage()).world.field_73011_w.getDimension(), (float)trajectory.source.field_72450_a, (float)trajectory.source.field_72448_b, (float)trajectory.source.field_72449_c, 64.0D));
/*     */     } 
/*     */     
/* 107 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\casters\foci\FocusMediumTouch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */