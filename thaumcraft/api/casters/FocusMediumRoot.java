/*     */ package thaumcraft.api.casters;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FocusMediumRoot
/*     */   extends FocusMedium
/*     */ {
/*  15 */   Trajectory[] trajectories = null;
/*  16 */   RayTraceResult[] targets = null;
/*     */ 
/*     */   
/*     */   public FocusMediumRoot(Trajectory[] trajectories, RayTraceResult[] targets) {
/*  20 */     this.trajectories = trajectories;
/*  21 */     this.targets = targets;
/*     */   }
/*     */   
/*     */   public FocusMediumRoot() {}
/*     */   
/*  26 */   public String getResearch() { return "BASEAUROMANCY"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  31 */   public String getKey() { return "ROOT"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  36 */   public int getComplexity() { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   public FocusNode.EnumSupplyType[] willSupply() { return new FocusNode.EnumSupplyType[] { FocusNode.EnumSupplyType.TARGET, FocusNode.EnumSupplyType.TRAJECTORY }; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   public RayTraceResult[] supplyTargets() { return this.targets; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   public Trajectory[] supplyTrajectories() { return this.trajectories; }
/*     */ 
/*     */   
/*     */   public void setupFromCaster(EntityLivingBase caster) {
/*  55 */     this.trajectories = new Trajectory[] { new Trajectory(generateSourceVector(caster), caster.func_70040_Z()) };
/*  56 */     this.targets = new RayTraceResult[] { new RayTraceResult(caster) };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setupFromCasterToTarget(EntityLivingBase caster, Entity target, double offset) {
/*  66 */     Vec3d sv = generateSourceVector(caster);
/*  67 */     double d0 = target.field_70165_t - sv.field_72450_a;
/*  68 */     double d1 = (target.func_174813_aQ()).field_72338_b + (target.field_70131_O / 2.0F) - sv.field_72448_b;
/*  69 */     double d2 = target.field_70161_v - sv.field_72449_c;
/*  70 */     Vec3d lv = new Vec3d(d0, d1 + offset, d2);
/*  71 */     this.trajectories = new Trajectory[] { new Trajectory(sv, lv.func_72432_b()) };
/*  72 */     this.targets = new RayTraceResult[] { new RayTraceResult(caster) };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setupFromCasterToTargetLoc(EntityLivingBase caster, double x, double y, double z) {
/*  83 */     Vec3d sv = generateSourceVector(caster);
/*  84 */     double d0 = x - sv.field_72450_a;
/*  85 */     double d1 = y - sv.field_72448_b;
/*  86 */     double d2 = z - sv.field_72449_c;
/*  87 */     Vec3d lv = new Vec3d(d0, d1, d2);
/*  88 */     this.trajectories = new Trajectory[] { new Trajectory(sv, lv.func_72432_b()) };
/*  89 */     this.targets = new RayTraceResult[] { new RayTraceResult(caster) };
/*     */   }
/*     */   
/*     */   private Vec3d generateSourceVector(EntityLivingBase e) {
/*  93 */     v = e.func_174791_d();
/*  94 */     return v.func_72441_c(0.0D, e.func_70047_e() - 0.10000000149011612D, 0.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   public Aspect getAspect() { return null; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\casters\FocusMediumRoot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */