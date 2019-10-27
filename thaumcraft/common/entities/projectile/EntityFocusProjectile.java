/*     */ package thaumcraft.common.entities.projectile;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.awt.Color;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
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
/*     */ public class EntityFocusProjectile
/*     */   extends EntityThrowable
/*     */   implements IEntityAdditionalSpawnData
/*     */ {
/*     */   FocusPackage focusPackage;
/*     */   
/*     */   public EntityFocusProjectile(World par1World) {
/*  37 */     super(par1World);
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
/* 133 */     this.noTouchy = false;
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
/* 241 */     this.firstParticle = false;
/* 242 */     this.lastRenderTick = 0.0F;
/*     */     
/* 244 */     this.effects = null; func_70105_a(0.15F, 0.15F); } private static final DataParameter<Integer> SPECIAL = EntityDataManager.func_187226_a(EntityFocusProjectile.class, DataSerializers.field_187192_b); private static final DataParameter<Integer> OWNER = EntityDataManager.func_187226_a(EntityFocusProjectile.class, DataSerializers.field_187192_b); boolean noTouchy; private Entity target; boolean firstParticle; public EntityFocusProjectile(FocusPackage pack, float speed, Trajectory trajectory, int special) { super(pack.world, pack.getCaster()); this.noTouchy = false; this.firstParticle = false; this.lastRenderTick = 0.0F; this.effects = null; this.focusPackage = pack; func_70107_b(trajectory.source.field_72450_a + trajectory.direction.field_72450_a * (pack.getCaster()).field_70130_N * 2.1D, trajectory.source.field_72448_b + trajectory.direction.field_72448_b * (pack.getCaster()).field_70130_N * 2.1D, trajectory.source.field_72449_c + trajectory.direction.field_72449_c * (pack.getCaster()).field_70130_N * 2.1D); func_70186_c(trajectory.direction.field_72450_a, trajectory.direction.field_72448_b, trajectory.direction.field_72449_c, speed, 0.0F); func_70105_a(0.15F, 0.15F);
/*     */     setSpecial(special);
/*     */     this.field_184539_c = pack.getCaster();
/*     */     setOwner(func_85052_h().func_145782_y()); }
/*     */   public float lastRenderTick; FocusEffect[] effects; protected float func_70185_h() { return (getSpecial() > 1) ? 0.005F : 0.01F; } public void func_70088_a() { super.func_70088_a();
/*     */     func_184212_Q().func_187214_a(SPECIAL, Integer.valueOf(0));
/* 250 */     func_184212_Q().func_187214_a(OWNER, Integer.valueOf(0)); } public void setOwner(int s) { func_184212_Q().func_187227_b(OWNER, Integer.valueOf(s)); } public Vec3d func_70040_Z() { return (new Vec3d(this.field_70159_w, this.field_70181_x, this.field_70179_y)).func_72432_b(); } public int getOwner() { return ((Integer)func_184212_Q().func_187225_a(OWNER)).intValue(); } public EntityLivingBase func_85052_h() { if (this.field_70170_p.field_72995_K) {
/*     */       Entity e = this.field_70170_p.func_73045_a(getOwner()); if (e != null && e instanceof EntityLivingBase)
/*     */         return (EntityLivingBase)e; 
/*     */     }  return super.func_85052_h(); } public void setSpecial(int s) { func_184212_Q().func_187227_b(SPECIAL, Integer.valueOf(s)); } public int getSpecial() { return ((Integer)func_184212_Q().func_187225_a(SPECIAL)).intValue(); } public void writeSpawnData(ByteBuf data) { Utils.writeNBTTagCompoundToBuffer(data, this.focusPackage.serialize()); }
/* 254 */   public void renderParticle(float coeff) { this.lastRenderTick = coeff;
/* 255 */     if (this.effects == null) this.effects = this.focusPackage.getFocusEffects(); 
/* 256 */     if (this.effects != null && this.effects.length > 0) {
/* 257 */       FocusEffect eff = this.effects[this.field_70146_Z.nextInt(this.effects.length)];
/* 258 */       float scale = 1.0F;
/* 259 */       Color c1 = new Color(FocusEngine.getElementColor(eff.getKey()));
/*     */       
/* 261 */       FXDispatcher.INSTANCE.drawFireMote((float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * coeff), (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * coeff) + this.field_70131_O / 2.0F, (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * coeff), 0.0125F * (this.field_70146_Z
/*     */           
/* 263 */           .nextFloat() - 0.5F) * scale, 0.0125F * (this.field_70146_Z.nextFloat() - 0.5F) * scale, 0.0125F * (this.field_70146_Z.nextFloat() - 0.5F) * scale, c1
/* 264 */           .getRed() / 255.0F, c1
/* 265 */           .getGreen() / 255.0F, c1
/* 266 */           .getBlue() / 255.0F, 0.5F, 7.0F * scale);
/*     */ 
/*     */       
/* 269 */       if (this.firstParticle) {
/* 270 */         this.firstParticle = false;
/* 271 */         eff.renderParticleFX(this.field_70170_p, this.field_70169_q + (this.field_70165_t - this.field_70169_q) * coeff + this.field_70170_p.field_73012_v
/* 272 */             .nextGaussian() * 0.10000000149011612D, this.field_70167_r + (this.field_70163_u - this.field_70167_r) * coeff + (this.field_70131_O / 2.0F) + this.field_70170_p.field_73012_v
/* 273 */             .nextGaussian() * 0.10000000149011612D, this.field_70166_s + (this.field_70161_v - this.field_70166_s) * coeff + this.field_70170_p.field_73012_v
/* 274 */             .nextGaussian() * 0.10000000149011612D, this.field_70170_p.field_73012_v
/* 275 */             .nextGaussian() * 0.009999999776482582D, this.field_70170_p.field_73012_v.nextGaussian() * 0.009999999776482582D, this.field_70170_p.field_73012_v.nextGaussian() * 0.009999999776482582D);
/*     */       } 
/*     */     }  }
/*     */ 
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
/*     */     super.func_70014_b(nbt);
/*     */     nbt.func_74782_a("pack", this.focusPackage.serialize());
/*     */     nbt.func_74768_a("special", getSpecial());
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbt) {
/*     */     super.func_70037_a(nbt);
/*     */     setSpecial(nbt.func_74762_e("special"));
/*     */     try {
/*     */       this.focusPackage = new FocusPackage();
/*     */       this.focusPackage.deserialize(nbt.func_74775_l("pack"));
/*     */     } catch (Exception exception) {}
/*     */     if (func_85052_h() != null)
/*     */       setOwner(func_85052_h().func_145782_y()); 
/*     */   }
/*     */   
/*     */   protected void func_70184_a(final RayTraceResult mop) {
/*     */     if (mop != null) {
/*     */       if (getSpecial() == 1 && mop.field_72313_a == RayTraceResult.Type.BLOCK) {
/*     */         IBlockState bs = this.field_70170_p.func_180495_p(mop.func_178782_a());
/*     */         AxisAlignedBB bb = bs.func_185890_d(this.field_70170_p, mop.func_178782_a());
/*     */         if (bb == null)
/*     */           return; 
/*     */         this.field_70165_t -= this.field_70159_w;
/*     */         this.field_70163_u -= this.field_70181_x;
/*     */         this.field_70161_v -= this.field_70179_y;
/*     */         if (mop.field_178784_b.func_82599_e() != 0)
/*     */           this.field_70179_y *= -1.0D; 
/*     */         if (mop.field_178784_b.func_82601_c() != 0)
/*     */           this.field_70159_w *= -1.0D; 
/*     */         if (mop.field_178784_b.func_96559_d() != 0)
/*     */           this.field_70181_x *= -0.9D; 
/*     */         this.field_70159_w *= 0.9D;
/*     */         this.field_70181_x *= 0.9D;
/*     */         this.field_70179_y *= 0.9D;
/*     */         float var20 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
/*     */         this.field_70165_t -= this.field_70159_w / var20 * 0.05000000074505806D;
/*     */         this.field_70163_u -= this.field_70181_x / var20 * 0.05000000074505806D;
/*     */         this.field_70161_v -= this.field_70179_y / var20 * 0.05000000074505806D;
/*     */         if (!this.field_70170_p.field_72995_K)
/*     */           func_184185_a(SoundEvents.field_187748_db, 0.25F, 1.0F); 
/*     */         if (!this.field_70170_p.field_72995_K && (new Vec3d(this.field_70159_w, this.field_70181_x, this.field_70179_y)).func_72433_c() < 0.2D)
/*     */           func_70106_y(); 
/*     */         return;
/*     */       } 
/*     */       if (!this.field_70170_p.field_72995_K) {
/*     */         if (mop.field_72308_g != null)
/*     */           mop.field_72307_f = func_174791_d(); 
/*     */         final Vec3d pv = new Vec3d(this.field_70169_q, this.field_70167_r, this.field_70166_s);
/*     */         final Vec3d vf = new Vec3d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */         ServerEvents.addRunnableServer(func_130014_f_(), new Runnable() {
/*     */               public void run() {
/*     */                 FocusEngine.runFocusPackage(EntityFocusProjectile.this.focusPackage, new Trajectory[] { new Trajectory(pv, vf.func_72432_b()) }new RayTraceResult[] { mop });
/*     */               }
/*     */             }0);
/*     */         func_70106_y();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_70071_h_() {
/*     */     super.func_70071_h_();
/*     */     if (this.field_70173_aa > 1200 || (!this.field_70170_p.field_72995_K && func_85052_h() == null))
/*     */       func_70106_y(); 
/*     */     this.firstParticle = true;
/*     */     if (this.target == null && this.field_70173_aa % 5 == 0 && getSpecial() > 1) {
/*     */       List<EntityLivingBase> list = EntityUtils.getEntitiesInRangeSorted(func_130014_f_(), this, EntityLivingBase.class, 16.0D);
/*     */       for (EntityLivingBase pt : list) {
/*     */         if (pt.field_70128_L || !EntityUtils.isVisibleTo(1.75F, this, pt, 16.0F) || !EntityUtils.canEntityBeSeen(this, pt))
/*     */           continue; 
/*     */         boolean f = EntityUtils.isFriendly(func_85052_h(), pt);
/*     */         if (f && getSpecial() == 3) {
/*     */           this.target = pt;
/*     */           break;
/*     */         } 
/*     */         if (!f && getSpecial() == 2) {
/*     */           this.target = pt;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     if (this.target != null) {
/*     */       double d = func_70068_e(this.target);
/*     */       double dx = this.target.field_70165_t - this.field_70165_t;
/*     */       double dy = (this.target.func_174813_aQ()).field_72338_b + this.target.field_70131_O * 0.6D - this.field_70163_u;
/*     */       double dz = this.target.field_70161_v - this.field_70161_v;
/*     */       Vec3d v = new Vec3d(dx, dy, dz);
/*     */       v = v.func_72432_b();
/*     */       Vec3d mv = new Vec3d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */       double lv = mv.func_72433_c();
/*     */       mv = mv.func_72432_b().func_178787_e(v.func_186678_a(0.275D));
/*     */       mv = mv.func_72432_b().func_186678_a(lv);
/*     */       this.field_70159_w = mv.field_72450_a;
/*     */       this.field_70181_x = mv.field_72448_b;
/*     */       this.field_70179_y = mv.field_72449_c;
/*     */       if (this.field_70173_aa % 5 == 0 && (this.target.field_70128_L || !EntityUtils.isVisibleTo(1.75F, this, this.target, 16.0F) || !EntityUtils.canEntityBeSeen(this, this.target)))
/*     */         this.target = null; 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\projectile\EntityFocusProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */