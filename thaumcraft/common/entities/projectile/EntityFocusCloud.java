/*     */ package thaumcraft.common.entities.projectile;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import thaumcraft.api.casters.FocusEffect;
/*     */ import thaumcraft.api.casters.FocusEngine;
/*     */ import thaumcraft.api.casters.FocusPackage;
/*     */ import thaumcraft.api.casters.Trajectory;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.lib.events.ServerEvents;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityFocusCloud
/*     */   extends Entity
/*     */   implements IEntityAdditionalSpawnData
/*     */ {
/*     */   FocusPackage focusPackage;
/*     */   private EntityLivingBase owner;
/*     */   private UUID ownerUniqueId;
/*     */   private int duration;
/*  41 */   private static final DataParameter<Float> RADIUS = EntityDataManager.func_187226_a(EntityFocusCloud.class, DataSerializers.field_187193_c);
/*     */   
/*     */   public EntityFocusCloud(World par1World) {
/*  44 */     super(par1World);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 259 */     this.effects = null; } public EntityFocusCloud(FocusPackage pack, Trajectory trajectory, float rad, int dur) { super(pack.world); this.effects = null;
/*     */     this.focusPackage = pack;
/*     */     func_70107_b(trajectory.source.field_72450_a, trajectory.source.field_72448_b, trajectory.source.field_72449_c);
/*     */     func_70105_a(0.15F, 0.15F);
/*     */     setOwner(pack.getCaster());
/*     */     setRadius(rad);
/*     */     setDuration(dur); }
/*     */ 
/*     */   
/*     */   public int getDuration() { return this.duration; }
/*     */   
/*     */   public void setDuration(int durationIn) { this.duration = durationIn; }
/*     */   
/*     */   public void setOwner(@Nullable EntityLivingBase ownerIn) {
/*     */     this.owner = ownerIn;
/*     */     this.ownerUniqueId = (ownerIn == null) ? null : ownerIn.func_110124_au();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public EntityLivingBase getOwner() {
/*     */     if (this.owner == null && this.ownerUniqueId != null && this.field_70170_p instanceof WorldServer) {
/*     */       Entity entity = ((WorldServer)this.field_70170_p).func_175733_a(this.ownerUniqueId);
/*     */       if (entity instanceof EntityLivingBase)
/*     */         this.owner = (EntityLivingBase)entity; 
/*     */     } 
/*     */     return this.owner;
/*     */   }
/*     */   
/*     */   public void func_70088_a() { func_184212_Q().func_187214_a(RADIUS, Float.valueOf(0.5F)); }
/*     */   
/*     */   public void setRadius(float radiusIn) {
/*     */     double d0 = this.field_70165_t;
/*     */     double d1 = this.field_70163_u;
/*     */     double d2 = this.field_70161_v;
/*     */     func_70105_a(radiusIn * 2.0F, 0.5F);
/*     */     func_70107_b(d0, d1, d2);
/*     */     if (!this.field_70170_p.field_72995_K)
/*     */       func_184212_Q().func_187227_b(RADIUS, Float.valueOf(radiusIn)); 
/*     */   }
/*     */   
/*     */   public float getRadius() { return ((Float)func_184212_Q().func_187225_a(RADIUS)).floatValue(); }
/*     */   
/*     */   public void writeSpawnData(ByteBuf data) { Utils.writeNBTTagCompoundToBuffer(data, this.focusPackage.serialize()); }
/*     */   
/*     */   public void readSpawnData(ByteBuf data) {
/*     */     try {
/*     */       this.focusPackage = new FocusPackage();
/*     */       this.focusPackage.deserialize(Utils.readNBTTagCompoundFromBuffer(data));
/*     */     } catch (Exception e) {
/*     */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbt) {
/*     */     nbt.func_74768_a("Age", this.field_70173_aa);
/*     */     nbt.func_74768_a("Duration", this.duration);
/*     */     nbt.func_74776_a("Radius", getRadius());
/*     */     if (this.ownerUniqueId != null)
/*     */       nbt.func_186854_a("OwnerUUID", this.ownerUniqueId); 
/*     */     nbt.func_74782_a("pack", this.focusPackage.serialize());
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbt) {
/*     */     this.field_70173_aa = nbt.func_74762_e("Age");
/*     */     this.duration = nbt.func_74762_e("Duration");
/*     */     setRadius(nbt.func_74760_g("Radius"));
/*     */     this.ownerUniqueId = nbt.func_186857_a("OwnerUUID");
/*     */     try {
/*     */       this.focusPackage = new FocusPackage();
/*     */       this.focusPackage.deserialize(nbt.func_74775_l("pack"));
/*     */     } catch (Exception exception) {}
/*     */   }
/*     */   
/*     */   static HashMap<Long, Long> cooldownMap = new HashMap();
/*     */   FocusEffect[] effects;
/*     */   
/*     */   public void func_70071_h_() {
/*     */     super.func_70071_h_();
/*     */     float rad = getRadius();
/*     */     int dur = getDuration();
/*     */     if (!this.field_70170_p.field_72995_K && (this.field_70173_aa > dur * 20 || getOwner() == null))
/*     */       func_70106_y(); 
/*     */     if (func_70089_S())
/*     */       if (this.field_70170_p.field_72995_K) {
/*     */         if (this.effects == null)
/*     */           this.effects = this.focusPackage.getFocusEffects(); 
/*     */         if (this.effects != null && this.effects.length > 0)
/*     */           for (int a = 0; a < rad; a++) {
/*     */             FocusEffect eff = this.effects[this.field_70146_Z.nextInt(this.effects.length)];
/*     */             FXDispatcher.INSTANCE.drawFocusCloudParticle(this.field_70165_t + this.field_70170_p.field_73012_v.nextGaussian() * rad / 2.0D * 0.85D, this.field_70163_u + this.field_70170_p.field_73012_v.nextGaussian() * rad / 2.0D * 0.85D, this.field_70161_v + this.field_70170_p.field_73012_v.nextGaussian() * rad / 2.0D * 0.85D, this.field_70170_p.field_73012_v.nextGaussian() * 0.01D, this.field_70170_p.field_73012_v.nextGaussian() * 0.01D, this.field_70170_p.field_73012_v.nextGaussian() * 0.01D, FocusEngine.getElementColor(eff.getKey()));
/*     */             eff.renderParticleFX(this.field_70170_p, this.field_70165_t + this.field_70170_p.field_73012_v.nextGaussian() * rad / 2.0D, this.field_70163_u + this.field_70170_p.field_73012_v.nextGaussian() * rad / 2.0D, this.field_70161_v + this.field_70170_p.field_73012_v.nextGaussian() * rad / 2.0D, this.field_70170_p.field_73012_v.nextGaussian() * 0.009999999776482582D, this.field_70170_p.field_73012_v.nextGaussian() * 0.009999999776482582D, this.field_70170_p.field_73012_v.nextGaussian() * 0.009999999776482582D);
/*     */           }  
/*     */       } else if (this.field_70173_aa % 5 == 0) {
/*     */         long t = System.currentTimeMillis();
/*     */         final ArrayList<Trajectory> trajectories = new ArrayList<Trajectory>();
/*     */         final ArrayList<RayTraceResult> targets = new ArrayList<RayTraceResult>();
/*     */         List<Entity> list = EntityUtils.getEntitiesInRange(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this, Entity.class, rad);
/*     */         for (Entity e : list) {
/*     */           if (e.field_70128_L)
/*     */             continue; 
/*     */           if (e instanceof EntityFocusCloud) {
/*     */             Vec3d v = e.func_174791_d().func_178788_d(func_174791_d());
/*     */             e.func_70091_d(MoverType.SELF, v.field_72450_a / 50.0D, v.field_72448_b / 50.0D, v.field_72449_c / 50.0D);
/*     */             ((EntityFocusCloud)e).func_145771_j(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*     */           } 
/*     */           if (!(e instanceof EntityLivingBase))
/*     */             continue; 
/*     */           if (cooldownMap.containsKey(Integer.valueOf(e.func_145782_y())) && ((Long)cooldownMap.get(Integer.valueOf(e.func_145782_y()))).longValue() > t)
/*     */             continue; 
/*     */           cooldownMap.put(Long.valueOf(e.func_145782_y()), Long.valueOf(t + 2000L));
/*     */           RayTraceResult ray = new RayTraceResult(e);
/*     */           ray.field_72307_f = e.func_174791_d().func_72441_c(0.0D, (e.field_70131_O / 2.0F), 0.0D);
/*     */           Trajectory tra = new Trajectory(func_174791_d(), func_174791_d().func_72444_a(ray.field_72307_f));
/*     */           targets.add(ray);
/*     */           trajectories.add(tra);
/*     */         } 
/*     */         for (int a = 0; a < rad; a++) {
/*     */           Vec3d dV = new Vec3d(this.field_70146_Z.nextGaussian(), this.field_70146_Z.nextGaussian(), this.field_70146_Z.nextGaussian());
/*     */           dV = dV.func_72432_b();
/*     */           RayTraceResult br = this.field_70170_p.func_72933_a(func_174791_d(), func_174791_d().func_178787_e(dV.func_186678_a(rad)));
/*     */           long bl = 0L;
/*     */           if (br != null) {
/*     */             bl = br.func_178782_a().func_177986_g();
/*     */             if (cooldownMap.containsKey(Long.valueOf(bl)))
/*     */               if (((Long)cooldownMap.get(Long.valueOf(bl))).longValue() <= t) {
/*     */                 cooldownMap.remove(Long.valueOf(bl));
/*     */               } else {
/*     */                 br = null;
/*     */               }  
/*     */           } 
/*     */           if (br != null) {
/*     */             targets.add(br);
/*     */             Trajectory tra = new Trajectory(func_174791_d(), dV);
/*     */             trajectories.add(tra);
/*     */             cooldownMap.put(Long.valueOf(bl), Long.valueOf(t + 2000L));
/*     */           } 
/*     */         } 
/*     */         if (!targets.isEmpty())
/*     */           ServerEvents.addRunnableServer(func_130014_f_(), new Runnable() {
/*     */                 public void run() { FocusEngine.runFocusPackage(EntityFocusCloud.this.focusPackage.copy(EntityFocusCloud.this.getOwner()), (Trajectory[])trajectories.toArray(new Trajectory[0]), (RayTraceResult[])targets.toArray(new RayTraceResult[0])); }
/*     */               }0); 
/*     */       }  
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\projectile\EntityFocusCloud.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */