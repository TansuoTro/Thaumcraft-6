/*     */ package thaumcraft.common.entities.projectile;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ 
/*     */ public class EntityHomingShard extends EntityThrowable implements IEntityAdditionalSpawnData {
/*     */   Class tclass;
/*     */   boolean persistant;
/*     */   int targetID;
/*     */   EntityLivingBase target;
/*     */   
/*  29 */   public EntityHomingShard(World par1World) { super(par1World);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  48 */     this.tclass = null;
/*  49 */     this.persistant = false;
/*     */     
/*  51 */     this.targetID = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     this.vl = new ArrayList(); } public EntityHomingShard(World par1World, EntityLivingBase p, EntityLivingBase t, int strength, boolean b) { super(par1World, p); this.tclass = null; this.persistant = false; this.targetID = 0; this.vl = new ArrayList(); this.target = t; this.tclass = t.getClass(); this.persistant = b; setStrength(strength); Vec3d v = p.func_70040_Z(); func_70012_b(p.field_70165_t + v.field_72450_a / 2.0D, p.field_70163_u + p.func_70047_e() + v.field_72448_b / 2.0D, p.field_70161_v + v.field_72449_c / 2.0D, p.field_70177_z, p.field_70125_A); float f = 0.5F;
/*     */     float ry = p.field_70177_z + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 60.0F;
/*     */     float rp = p.field_70125_A + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 60.0F;
/*     */     this.field_70159_w = (-MathHelper.func_76126_a(ry / 180.0F * 3.1415927F) * MathHelper.func_76134_b(rp / 180.0F * 3.1415927F) * f);
/*     */     this.field_70179_y = (MathHelper.func_76134_b(ry / 180.0F * 3.1415927F) * MathHelper.func_76134_b(rp / 180.0F * 3.1415927F) * f);
/* 134 */     this.field_70181_x = (-MathHelper.func_76126_a(rp / 180.0F * 3.1415927F) * f); } public void func_70071_h_() { this.vl.add(0, new UtilsFX.Vector((float)this.field_70142_S, (float)this.field_70137_T, (float)this.field_70136_U));
/* 135 */     if (this.vl.size() > 6) this.vl.remove(this.vl.size() - 1);
/*     */     
/* 137 */     super.func_70071_h_();
/*     */     
/* 139 */     if (!this.field_70170_p.field_72995_K) {
/* 140 */       if (this.persistant && (this.target == null || this.target.field_70128_L || this.target.func_70068_e(this) > 1250.0D)) {
/* 141 */         List<Entity> es = EntityUtils.getEntitiesInRange(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this, this.tclass, 16.0D);
/* 142 */         for (Entity e : es) {
/* 143 */           if (e instanceof EntityLivingBase && !e.field_70128_L && (
/* 144 */             func_85052_h() == null || e.func_145782_y() != func_85052_h().func_145782_y())) {
/* 145 */             this.target = (EntityLivingBase)e;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 150 */       if (this.target == null || this.target.field_70128_L) {
/* 151 */         this.field_70170_p.func_72960_a(this, (byte)16);
/* 152 */         func_70106_y();
/*     */       } 
/*     */     } 
/*     */     
/* 156 */     if (this.field_70173_aa > 300) {
/* 157 */       this.field_70170_p.func_72960_a(this, (byte)16);
/* 158 */       func_70106_y();
/*     */     } 
/*     */ 
/*     */     
/* 162 */     if (this.field_70173_aa % 20 == 0 && this.target != null && !this.target.field_70128_L) {
/* 163 */       double d = func_70032_d(this.target);
/* 164 */       double dx = this.target.field_70165_t - this.field_70165_t;
/* 165 */       double dy = (this.target.func_174813_aQ()).field_72338_b + this.target.field_70131_O * 0.6D - this.field_70163_u;
/* 166 */       double dz = this.target.field_70161_v - this.field_70161_v;
/* 167 */       dx /= d;
/* 168 */       dy /= d;
/* 169 */       dz /= d;
/*     */       
/* 171 */       this.field_70159_w = dx;
/* 172 */       this.field_70181_x = dy;
/* 173 */       this.field_70179_y = dz;
/*     */     } 
/*     */ 
/*     */     
/* 177 */     this.field_70159_w *= 0.85D;
/* 178 */     this.field_70181_x *= 0.85D;
/* 179 */     this.field_70179_y *= 0.85D; }
/*     */ 
/*     */   
/*     */   private static final DataParameter<Byte> STRENGTH = EntityDataManager.func_187226_a(EntityHomingShard.class, DataSerializers.field_187191_a);
/*     */   public ArrayList<UtilsFX.Vector> vl;
/*     */   
/*     */   public void func_70088_a() {
/*     */     super.func_70088_a();
/*     */     func_184212_Q().func_187214_a(STRENGTH, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */   public void setStrength(int str) { func_184212_Q().func_187227_b(STRENGTH, Byte.valueOf((byte)str)); }
/*     */   
/*     */   public int getStrength() { return ((Byte)func_184212_Q().func_187225_a(STRENGTH)).byteValue(); }
/*     */   
/*     */   protected float func_70185_h() { return 0.0F; }
/*     */   
/*     */   public void writeSpawnData(ByteBuf data) {
/*     */     int id = -1;
/*     */     if (this.target != null)
/*     */       id = this.target.func_145782_y(); 
/*     */     data.writeInt(id);
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data) {
/*     */     int id = data.readInt();
/*     */     try {
/*     */       if (id >= 0)
/*     */         this.target = (EntityLivingBase)this.field_70170_p.func_73045_a(id); 
/*     */     } catch (Exception exception) {}
/*     */   }
/*     */   
/*     */   protected void func_70184_a(RayTraceResult mop) {
/*     */     if (!this.field_70170_p.field_72995_K && mop.field_72313_a == RayTraceResult.Type.ENTITY && (func_85052_h() == null || (func_85052_h() != null && mop.field_72308_g != func_85052_h()))) {
/*     */       mop.field_72308_g.func_70097_a(DamageSource.func_76354_b(this, func_85052_h()), 1.0F + getStrength() * 0.5F);
/*     */       func_184185_a(SoundsTC.zap, 1.0F, 1.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F);
/*     */       this.field_70170_p.func_72960_a(this, (byte)16);
/*     */       func_70106_y();
/*     */     } 
/*     */     if (mop.field_72313_a == RayTraceResult.Type.BLOCK) {
/*     */       if (mop.field_178784_b.func_82599_e() != 0)
/*     */         this.field_70179_y *= -0.8D; 
/*     */       if (mop.field_178784_b.func_82601_c() != 0)
/*     */         this.field_70159_w *= -0.8D; 
/*     */       if (mop.field_178784_b.func_96559_d() != 0)
/*     */         this.field_70181_x *= -0.8D; 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte par1) {
/*     */     if (par1 == 16) {
/*     */       FXDispatcher.INSTANCE.burst(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.3F);
/*     */     } else {
/*     */       super.func_70103_a(par1);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\projectile\EntityHomingShard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */