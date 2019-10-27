/*     */ package thaumcraft.common.entities.projectile;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.HashMap;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ 
/*     */ public class EntityGrapple
/*     */   extends EntityThrowable
/*     */   implements IEntityAdditionalSpawnData
/*     */ {
/*  21 */   public EnumHand hand = EnumHand.MAIN_HAND; EntityLivingBase cthrower; boolean p; boolean boost; int prevDist; int count;
/*     */   boolean added;
/*     */   public float ampl;
/*     */   
/*  25 */   public EntityGrapple(World par1World) { super(par1World);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 101 */     this.p = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 114 */     this.prevDist = 0;
/* 115 */     this.count = 0;
/* 116 */     this.added = false;
/*     */     
/* 118 */     this.ampl = 0.0F; func_70105_a(0.1F, 0.1F); } public boolean func_70112_a(double distance) { return (distance < 4096.0D); } public void func_70186_c(double x, double y, double z, float velocity, float inaccuracy) { super.func_70186_c(x, y, z, velocity, 0.0F); } public EntityGrapple(World par1World, EntityLivingBase par2EntityLiving, EnumHand hand) { super(par1World, par2EntityLiving); this.p = false; this.prevDist = 0; this.count = 0; this.added = false; this.ampl = 0.0F; func_70105_a(0.1F, 0.1F); this.hand = hand; } public EntityGrapple(World par1World, double par2, double par4, double par6) { super(par1World, par2, par4, par6); this.p = false; this.prevDist = 0; this.count = 0; this.added = false; this.ampl = 0.0F; func_70105_a(0.1F, 0.1F); }
/*     */   public void writeSpawnData(ByteBuf data) { int id = -1; if (func_85052_h() != null)
/* 120 */       id = func_85052_h().func_145782_y();  data.writeInt(id); data.writeBoolean((this.hand == EnumHand.MAIN_HAND)); } public static HashMap<Integer, Integer> grapples = new HashMap();
/*     */   public void readSpawnData(ByteBuf data) { int id = data.readInt(); this.hand = data.readBoolean() ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND; try {
/*     */       if (id >= 0)
/*     */         this.cthrower = (EntityLivingBase)this.field_70170_p.func_73045_a(id); 
/*     */     } catch (Exception exception) {} }
/*     */   public EntityLivingBase func_85052_h() { if (this.cthrower != null)
/* 126 */       return this.cthrower;  return super.func_85052_h(); } protected float func_70185_h() { return getPulling() ? 0.0F : 0.03F; } public void func_70088_a() { super.func_70088_a(); } public void setPulling() { this.p = true; } public boolean getPulling() { return this.p; } public void func_70071_h_() { super.func_70071_h_();
/*     */     
/* 128 */     if (!getPulling() && !this.field_70128_L && (this.field_70173_aa > 30 || func_85052_h() == null)) {
/* 129 */       if (func_85052_h() != null) grapples.remove(Integer.valueOf(func_85052_h().func_145782_y())); 
/* 130 */       func_70106_y();
/*     */     } 
/*     */     
/* 133 */     if (func_85052_h() != null) {
/*     */       
/* 135 */       if (!this.field_70170_p.field_72995_K && !this.field_70128_L && !this.added) {
/* 136 */         if (grapples.containsKey(Integer.valueOf(func_85052_h().func_145782_y()))) {
/* 137 */           int ii = ((Integer)grapples.get(Integer.valueOf(func_85052_h().func_145782_y()))).intValue();
/* 138 */           if (ii != func_145782_y()) {
/* 139 */             Entity e = this.field_70170_p.func_73045_a(ii);
/* 140 */             if (e != null) e.func_70106_y(); 
/*     */           } 
/*     */         } 
/* 143 */         grapples.put(Integer.valueOf(func_85052_h().func_145782_y()), Integer.valueOf(func_145782_y()));
/* 144 */         this.added = true;
/*     */       } 
/*     */       
/*     */       try {
/* 148 */         if (func_85052_h() != null && grapples.containsKey(Integer.valueOf(func_85052_h().func_145782_y())) && ((Integer)grapples.get(Integer.valueOf(func_85052_h().func_145782_y()))).intValue() != func_145782_y()) {
/* 149 */           func_70106_y();
/*     */         }
/* 151 */       } catch (Exception exception) {}
/*     */       
/* 153 */       double dis = func_85052_h().func_70032_d(this);
/* 154 */       if (func_85052_h() != null && getPulling() && !this.field_70128_L) {
/* 155 */         if (func_85052_h().func_70093_af()) {
/* 156 */           grapples.remove(Integer.valueOf(func_85052_h().func_145782_y()));
/* 157 */           func_70106_y();
/*     */         } else {
/* 159 */           if (!this.field_70170_p.field_72995_K && func_85052_h() instanceof EntityPlayerMP) {
/* 160 */             ((EntityPlayerMP)func_85052_h()).field_71135_a.field_147365_f = 0;
/*     */           }
/* 162 */           (func_85052_h()).field_70143_R = 0.0F;
/* 163 */           double mx = this.field_70165_t - (func_85052_h()).field_70165_t;
/* 164 */           double my = this.field_70163_u - (func_85052_h()).field_70163_u;
/* 165 */           double mz = this.field_70161_v - (func_85052_h()).field_70161_v;
/* 166 */           double dd = dis;
/* 167 */           if (dis < 8.0D)
/* 168 */             dd = dis * (8.0D - dis); 
/* 169 */           dd = Math.max(1.0E-9D, dd);
/* 170 */           mx /= dd * 5.0D;
/* 171 */           my /= dd * 5.0D;
/* 172 */           mz /= dd * 5.0D;
/* 173 */           Vec3d v2 = new Vec3d(mx, my, mz);
/* 174 */           if (v2.func_72433_c() > 0.25D) {
/* 175 */             v2 = v2.func_72432_b();
/* 176 */             mx = v2.field_72450_a / 4.0D;
/* 177 */             my = v2.field_72448_b / 4.0D;
/* 178 */             mz = v2.field_72449_c / 4.0D;
/*     */           } 
/* 180 */           (func_85052_h()).field_70159_w += mx;
/* 181 */           (func_85052_h()).field_70181_x += my + 0.033D;
/* 182 */           (func_85052_h()).field_70179_y += mz;
/* 183 */           if (!this.boost) {
/* 184 */             (func_85052_h()).field_70181_x += 0.4000000059604645D;
/* 185 */             this.boost = true;
/*     */           } 
/* 187 */           int d = (int)(dis / 2.0D);
/* 188 */           if (d == this.prevDist) {
/* 189 */             this.count++;
/*     */           } else {
/* 191 */             this.count = 0;
/*     */           } 
/* 193 */           this.prevDist = d;
/*     */         } 
/*     */       }
/*     */       
/* 197 */       if (this.field_70170_p.field_72995_K) {
/* 198 */         if (!getPulling()) {
/* 199 */           this.ampl += 0.02F;
/*     */         } else {
/* 201 */           this.ampl *= 0.66F;
/*     */         } 
/*     */       }
/*     */     }  }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte id) {
/* 212 */     if (id == 6) {
/*     */       
/* 214 */       setPulling();
/* 215 */       this.field_70159_w = 0.0D;
/* 216 */       this.field_70181_x = 0.0D;
/* 217 */       this.field_70179_y = 0.0D;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70184_a(RayTraceResult mop) {
/* 224 */     if (!this.field_70170_p.field_72995_K) {
/*     */       
/* 226 */       setPulling();
/* 227 */       this.field_70159_w = 0.0D;
/* 228 */       this.field_70181_x = 0.0D;
/* 229 */       this.field_70179_y = 0.0D;
/* 230 */       this.field_70165_t = mop.field_72307_f.field_72450_a;
/* 231 */       this.field_70163_u = mop.field_72307_f.field_72448_b;
/* 232 */       this.field_70161_v = mop.field_72307_f.field_72449_c;
/* 233 */       this.field_70170_p.func_72960_a(this, (byte)6);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\projectile\EntityGrapple.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */