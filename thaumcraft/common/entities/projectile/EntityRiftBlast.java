/*     */ package thaumcraft.common.entities.projectile;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.codechicken.lib.vec.Quat;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ 
/*     */ public class EntityRiftBlast extends EntityThrowable implements IEntityAdditionalSpawnData {
/*     */   int targetID;
/*     */   EntityLivingBase target;
/*     */   public boolean red;
/*     */   public double[][] points;
/*     */   
/*  21 */   public EntityRiftBlast(World par1World) { super(par1World);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  31 */     this.targetID = 0;
/*     */     
/*  33 */     this.red = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 151 */     this.growing = -1;
/* 152 */     this.vecs = new ArrayList(); } public float[][] colours; public double[] radii; int growing; ArrayList<Quat> vecs; public EntityRiftBlast(World par1World, EntityLivingBase par2EntityLiving, EntityLivingBase t, boolean r) { super(par1World, par2EntityLiving); this.targetID = 0; this.red = false; this.growing = -1; this.vecs = new ArrayList();
/*     */     this.target = t;
/*     */     this.red = r; }
/*     */   
/*     */   protected float func_70185_h() { return 0.0F; }
/*     */   public boolean func_70097_a(DamageSource source, float damage) {
/* 158 */     if (func_180431_b(source))
/*     */     {
/* 160 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 166 */     if (source.func_76346_g() != null) {
/*     */       
/* 168 */       Vec3d vec3 = source.func_76346_g().func_70040_Z();
/*     */       
/* 170 */       if (vec3 != null) {
/*     */         
/* 172 */         this.field_70159_w = vec3.field_72450_a;
/* 173 */         this.field_70181_x = vec3.field_72448_b;
/* 174 */         this.field_70179_y = vec3.field_72449_c;
/* 175 */         this.field_70159_w *= 0.9D;
/* 176 */         this.field_70181_x *= 0.9D;
/* 177 */         this.field_70179_y *= 0.9D;
/* 178 */         func_184185_a(SoundsTC.zap, 1.0F, 1.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F);
/*     */       } 
/* 180 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 184 */     return false;
/*     */   }
/*     */   
/*     */   public void writeSpawnData(ByteBuf data) {
/*     */     int id = -1;
/*     */     if (this.target != null)
/*     */       id = this.target.func_145782_y(); 
/*     */     data.writeInt(id);
/*     */     data.writeBoolean(this.red);
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data) {
/*     */     int id = data.readInt();
/*     */     try {
/*     */       if (id >= 0)
/*     */         this.target = (EntityLivingBase)this.field_70170_p.func_73045_a(id); 
/*     */     } catch (Exception exception) {}
/*     */     this.red = data.readBoolean();
/*     */   }
/*     */   
/*     */   protected void func_70184_a(RayTraceResult mop) {
/*     */     if (!this.field_70170_p.field_72995_K && func_85052_h() != null && mop.field_72313_a == RayTraceResult.Type.ENTITY)
/*     */       mop.field_72308_g.func_70097_a(DamageSource.func_76354_b(this, func_85052_h()), (float)func_85052_h().func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e() * (this.red ? 1.0F : 0.6F)); 
/*     */     func_184185_a(SoundsTC.shock, 1.0F, 1.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F);
/*     */     if (this.field_70170_p.field_72995_K)
/*     */       FXDispatcher.INSTANCE.burst(this.field_70165_t, this.field_70163_u, this.field_70161_v, 6.0F); 
/*     */     func_70106_y();
/*     */   }
/*     */   
/*     */   public void func_70071_h_() {
/*     */     super.func_70071_h_();
/*     */     if (this.field_70173_aa > (this.red ? 240 : 160))
/*     */       func_70106_y(); 
/*     */     if (this.target != null) {
/*     */       if (this.target == null || this.target.field_70128_L)
/*     */         func_70106_y(); 
/*     */       double d = func_70068_e(this.target);
/*     */       double dx = this.target.field_70165_t - this.field_70165_t;
/*     */       double dy = (this.target.func_174813_aQ()).field_72338_b + this.target.field_70131_O * 0.6D - this.field_70163_u;
/*     */       double dz = this.target.field_70161_v - this.field_70161_v;
/*     */       double d13 = 1.0D;
/*     */       dx /= d;
/*     */       dy /= d;
/*     */       dz /= d;
/*     */       this.field_70159_w += dx * d13;
/*     */       this.field_70181_x += dy * d13;
/*     */       this.field_70179_y += dz * d13;
/*     */       this.field_70159_w = MathHelper.func_76131_a((float)this.field_70159_w, -0.33F, 0.33F);
/*     */       this.field_70181_x = MathHelper.func_76131_a((float)this.field_70181_x, -0.33F, 0.33F);
/*     */       this.field_70179_y = MathHelper.func_76131_a((float)this.field_70179_y, -0.33F, 0.33F);
/*     */       if (this.field_70170_p.field_72995_K) {
/*     */         Quat q = new Quat(0.1D, this.field_70165_t + this.field_70146_Z.nextGaussian() * 0.05D, this.field_70163_u + this.field_70146_Z.nextGaussian() * 0.05D, this.field_70161_v + this.field_70146_Z.nextGaussian() * 0.05D);
/*     */         this.vecs.add(q);
/*     */         FXDispatcher.INSTANCE.drawCurlyWisp(q.x, q.y, q.z, 0.0D, 0.0D, 0.0D, 0.3F + this.field_70146_Z.nextFloat() * 0.2F, this.field_70146_Z.nextFloat(), this.field_70146_Z.nextFloat() * 0.2F, this.field_70146_Z.nextFloat() * 0.2F, 0.5F, null, 1, this.field_70146_Z.nextInt(2), 0);
/*     */         if (this.vecs.size() > 9)
/*     */           this.vecs.remove(0); 
/*     */         this.points = new double[this.vecs.size()][3];
/*     */         this.colours = new float[this.vecs.size()][4];
/*     */         this.radii = new double[this.vecs.size()];
/*     */         int c = 0;
/*     */         if (this.vecs.size() > 1) {
/*     */           float vv = (float)(Math.PI / (this.vecs.size() - 1));
/*     */           for (Quat v : this.vecs) {
/*     */             float variance = 1.0F + MathHelper.func_76126_a((c + this.field_70173_aa) / 3.0F) * 0.2F;
/*     */             float xx = MathHelper.func_76126_a((c + this.field_70173_aa) / 6.0F) * 0.01F;
/*     */             float yy = MathHelper.func_76126_a((c + this.field_70173_aa) / 7.0F) * 0.01F;
/*     */             float zz = MathHelper.func_76126_a((c + this.field_70173_aa) / 8.0F) * 0.01F;
/*     */             this.points[c][0] = v.x + xx;
/*     */             this.points[c][1] = v.y + yy;
/*     */             this.points[c][2] = v.z + zz;
/*     */             this.radii[c] = v.s * variance;
/*     */             this.radii[c] = this.radii[c] * MathHelper.func_76126_a(c * vv);
/*     */             this.colours[c][0] = 1.0F;
/*     */             this.colours[c][1] = 0.0F;
/*     */             this.colours[c][2] = 0.0F;
/*     */             this.colours[c][3] = 1.0F;
/*     */             c++;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\projectile\EntityRiftBlast.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */