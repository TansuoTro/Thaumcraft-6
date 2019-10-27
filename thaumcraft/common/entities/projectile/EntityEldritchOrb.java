/*    */ package thaumcraft.common.entities.projectile;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.projectile.EntityThrowable;
/*    */ import net.minecraft.init.MobEffects;
/*    */ import net.minecraft.init.SoundEvents;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class EntityEldritchOrb
/*    */   extends EntityThrowable
/*    */ {
/* 19 */   public EntityEldritchOrb(World par1World) { super(par1World); }
/*    */ 
/*    */   
/*    */   public EntityEldritchOrb(World par1World, EntityLivingBase p) {
/* 23 */     super(par1World, p);
/* 24 */     func_184538_a(p, p.field_70125_A, p.field_70177_z, -5.0F, 0.75F, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 29 */   protected float func_70185_h() { return 0.0F; }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_70071_h_() {
/* 34 */     super.func_70071_h_();
/* 35 */     if (this.field_70173_aa > 100) {
/* 36 */       func_70106_y();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_70184_a(RayTraceResult mop) {
/* 64 */     if (!this.field_70170_p.field_72995_K && func_85052_h() != null) {
/* 65 */       List list = this.field_70170_p.func_72839_b(func_85052_h(), func_174813_aQ().func_72314_b(2.0D, 2.0D, 2.0D));
/* 66 */       for (int i = 0; i < list.size(); i++) {
/* 67 */         Entity entity1 = (Entity)list.get(i);
/* 68 */         if (entity1 != null && entity1 instanceof EntityLivingBase && !((EntityLivingBase)entity1).func_70662_br()) {
/* 69 */           ((EntityLivingBase)entity1).func_70097_a(
/* 70 */               DamageSource.func_76354_b(this, func_85052_h()), 
/* 71 */               (float)func_85052_h().func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e() * 0.666F);
/*    */           try {
/* 73 */             ((EntityLivingBase)entity1).func_70690_d(new PotionEffect(MobEffects.field_76437_t, 160, 0));
/* 74 */           } catch (Exception exception) {}
/*    */         } 
/*    */       } 
/*    */ 
/*    */       
/* 79 */       func_184185_a(SoundEvents.field_187659_cY, 0.5F, 2.6F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.8F);
/*    */ 
/*    */ 
/*    */       
/* 83 */       func_70106_y();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\projectile\EntityEldritchOrb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */