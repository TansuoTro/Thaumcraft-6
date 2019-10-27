/*    */ package thaumcraft.common.entities.projectile;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.projectile.EntityThrowable;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ import thaumcraft.common.entities.EntityFluxRift;
/*    */ import thaumcraft.common.lib.utils.EntityUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityCausalityCollapser
/*    */   extends EntityThrowable
/*    */ {
/* 17 */   public EntityCausalityCollapser(World par1World) { super(par1World); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 22 */   public EntityCausalityCollapser(World par1World, EntityLivingBase par2EntityLiving) { super(par1World, par2EntityLiving); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 27 */   public EntityCausalityCollapser(World par1World, double par2, double par4, double par6) { super(par1World, par2, par4, par6); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 33 */   public void func_70186_c(double x, double y, double z, float velocity, float inaccuracy) { super.func_70186_c(x, y, z, 0.8F, inaccuracy); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_70071_h_() {
/* 41 */     super.func_70071_h_();
/*    */     
/* 43 */     if (this.field_70170_p.field_72995_K)
/*    */     {
/* 45 */       for (double i = 0.0D; i < 3.0D; i++) {
/* 46 */         double coeff = i / 3.0D;
/* 47 */         FXDispatcher.INSTANCE.drawAlumentum((float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * coeff), (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * coeff) + this.field_70131_O / 2.0F, (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * coeff), 0.0125F * (this.field_70146_Z
/*    */             
/* 49 */             .nextFloat() - 0.5F), 0.0125F * (this.field_70146_Z.nextFloat() - 0.5F), 0.0125F * (this.field_70146_Z.nextFloat() - 0.5F), 0.8F + this.field_70146_Z
/* 50 */             .nextFloat() * 0.2F, 0.3F + this.field_70146_Z.nextFloat() * 0.1F, this.field_70146_Z.nextFloat() * 0.1F, 0.5F, 4.0F);
/*    */         
/* 52 */         FXDispatcher.INSTANCE.drawGenericParticles(this.field_70165_t + this.field_70170_p.field_73012_v.nextGaussian() * 0.20000000298023224D, this.field_70163_u + this.field_70170_p.field_73012_v
/* 53 */             .nextGaussian() * 0.20000000298023224D, this.field_70161_v + this.field_70170_p.field_73012_v
/* 54 */             .nextGaussian() * 0.20000000298023224D, 0.0D, 0.0D, 0.0D, 1.0F, 1.0F, 1.0F, 0.7F, false, 448, 8, 1, 8, 0, 0.3F, 0.0F, 1);
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
/* 65 */     if (!this.field_70170_p.field_72995_K) {
/*    */       
/* 67 */       this.field_70170_p.func_72876_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 2.0F, true);
/* 68 */       List<EntityFluxRift> list = EntityUtils.getEntitiesInRange(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this, EntityFluxRift.class, 3.0D);
/* 69 */       for (EntityFluxRift fr : list) {
/* 70 */         fr.setCollapse(true);
/*    */       }
/* 72 */       func_70106_y();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\projectile\EntityCausalityCollapser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */