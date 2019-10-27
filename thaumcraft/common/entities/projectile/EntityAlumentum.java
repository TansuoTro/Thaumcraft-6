/*    */ package thaumcraft.common.entities.projectile;
/*    */ 
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.projectile.EntityThrowable;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ 
/*    */ 
/*    */ public class EntityAlumentum
/*    */   extends EntityThrowable
/*    */ {
/* 13 */   public EntityAlumentum(World par1World) { super(par1World); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 18 */   public EntityAlumentum(World par1World, EntityLivingBase par2EntityLiving) { super(par1World, par2EntityLiving); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   public EntityAlumentum(World par1World, double par2, double par4, double par6) { super(par1World, par2, par4, par6); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 29 */   public void func_70186_c(double x, double y, double z, float velocity, float inaccuracy) { super.func_70186_c(x, y, z, 0.75F, inaccuracy); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_70071_h_() {
/* 37 */     super.func_70071_h_();
/*    */     
/* 39 */     if (this.field_70170_p.field_72995_K)
/*    */     {
/* 41 */       for (double i = 0.0D; i < 3.0D; i++) {
/* 42 */         double coeff = i / 3.0D;
/* 43 */         FXDispatcher.INSTANCE.drawAlumentum((float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * coeff), (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * coeff) + this.field_70131_O / 2.0F, (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * coeff), 0.0125F * (this.field_70146_Z
/*    */             
/* 45 */             .nextFloat() - 0.5F), 0.0125F * (this.field_70146_Z.nextFloat() - 0.5F), 0.0125F * (this.field_70146_Z.nextFloat() - 0.5F), this.field_70146_Z
/* 46 */             .nextFloat() * 0.2F, this.field_70146_Z.nextFloat() * 0.1F, this.field_70146_Z.nextFloat() * 0.1F, 0.5F, 4.0F);
/*    */         
/* 48 */         FXDispatcher.INSTANCE.drawGenericParticles(this.field_70165_t + this.field_70170_p.field_73012_v.nextGaussian() * 0.20000000298023224D, this.field_70163_u + this.field_70170_p.field_73012_v
/* 49 */             .nextGaussian() * 0.20000000298023224D, this.field_70161_v + this.field_70170_p.field_73012_v
/* 50 */             .nextGaussian() * 0.20000000298023224D, 0.0D, 0.0D, 0.0D, 1.0F, 1.0F, 1.0F, 0.7F, false, 448, 8, 1, 8, 0, 0.3F, 0.0F, 1);
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_70184_a(RayTraceResult par1RayTraceResult) {
/* 61 */     if (!this.field_70170_p.field_72995_K) {
/*    */       
/* 63 */       this.field_70170_p.func_72876_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.1F, true);
/* 64 */       func_70106_y();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\projectile\EntityAlumentum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */