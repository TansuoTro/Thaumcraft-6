/*     */ package thaumcraft.common.entities.projectile;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ 
/*     */ public class EntityGolemOrb extends EntityThrowable implements IEntityAdditionalSpawnData {
/*     */   int targetID;
/*     */   EntityLivingBase target;
/*     */   public boolean red;
/*     */   
/*  18 */   public EntityGolemOrb(World par1World) { super(par1World);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  27 */     this.targetID = 0;
/*     */     
/*  29 */     this.red = false; } public EntityGolemOrb(World par1World, EntityLivingBase par2EntityLiving, EntityLivingBase t, boolean r) { super(par1World, par2EntityLiving); this.targetID = 0; this.red = false;
/*     */     this.target = t;
/*     */     this.red = r; }
/*     */ 
/*     */   
/*  34 */   protected float func_70185_h() { return 0.0F; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeSpawnData(ByteBuf data) {
/*  40 */     int id = -1;
/*  41 */     if (this.target != null) id = this.target.func_145782_y(); 
/*  42 */     data.writeInt(id);
/*  43 */     data.writeBoolean(this.red);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readSpawnData(ByteBuf data) {
/*  48 */     int id = data.readInt();
/*     */     try {
/*  50 */       if (id >= 0) this.target = (EntityLivingBase)this.field_70170_p.func_73045_a(id); 
/*  51 */     } catch (Exception exception) {}
/*  52 */     this.red = data.readBoolean();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70184_a(RayTraceResult mop) {
/*  60 */     if (!this.field_70170_p.field_72995_K && func_85052_h() != null && mop.field_72313_a == RayTraceResult.Type.ENTITY) {
/*  61 */       mop.field_72308_g.func_70097_a(DamageSource.func_76354_b(this, func_85052_h()), 
/*  62 */           (float)func_85052_h().func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e() * (this.red ? 1.0F : 0.6F));
/*     */     }
/*  64 */     func_184185_a(SoundsTC.shock, 1.0F, 1.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F);
/*  65 */     if (this.field_70170_p.field_72995_K) FXDispatcher.INSTANCE.burst(this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.0F); 
/*  66 */     func_70106_y();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  72 */     super.func_70071_h_();
/*  73 */     if (this.field_70173_aa > (this.red ? 240 : 160)) func_70106_y();
/*     */     
/*  75 */     if (this.target != null) {
/*  76 */       double d = func_70068_e(this.target);
/*  77 */       double dx = this.target.field_70165_t - this.field_70165_t;
/*  78 */       double dy = (this.target.func_174813_aQ()).field_72338_b + this.target.field_70131_O * 0.6D - this.field_70163_u;
/*  79 */       double dz = this.target.field_70161_v - this.field_70161_v;
/*  80 */       double d13 = 0.2D;
/*  81 */       dx /= d;
/*  82 */       dy /= d;
/*  83 */       dz /= d;
/*     */       
/*  85 */       this.field_70159_w += dx * d13;
/*  86 */       this.field_70181_x += dy * d13;
/*  87 */       this.field_70179_y += dz * d13;
/*     */       
/*  89 */       this.field_70159_w = MathHelper.func_76131_a((float)this.field_70159_w, -0.25F, 0.25F);
/*  90 */       this.field_70181_x = MathHelper.func_76131_a((float)this.field_70181_x, -0.25F, 0.25F);
/*  91 */       this.field_70179_y = MathHelper.func_76131_a((float)this.field_70179_y, -0.25F, 0.25F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource source, float damage) {
/*  98 */     if (func_180431_b(source))
/*     */     {
/* 100 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 106 */     if (source.func_76346_g() != null) {
/*     */       
/* 108 */       Vec3d vec3 = source.func_76346_g().func_70040_Z();
/*     */       
/* 110 */       if (vec3 != null) {
/*     */         
/* 112 */         this.field_70159_w = vec3.field_72450_a;
/* 113 */         this.field_70181_x = vec3.field_72448_b;
/* 114 */         this.field_70179_y = vec3.field_72449_c;
/* 115 */         this.field_70159_w *= 0.9D;
/* 116 */         this.field_70181_x *= 0.9D;
/* 117 */         this.field_70179_y *= 0.9D;
/* 118 */         func_184185_a(SoundsTC.zap, 1.0F, 1.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F);
/*     */       } 
/* 120 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 124 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\projectile\EntityGolemOrb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */