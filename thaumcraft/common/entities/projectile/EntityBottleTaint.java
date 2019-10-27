/*    */ package thaumcraft.common.entities.projectile;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.projectile.EntityThrowable;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.math.AxisAlignedBB;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.blocks.BlocksTC;
/*    */ import thaumcraft.api.potions.PotionFluxTaint;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityBottleTaint
/*    */   extends EntityThrowable
/*    */ {
/* 28 */   public EntityBottleTaint(World p_i1788_1_) { super(p_i1788_1_); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 33 */   public EntityBottleTaint(World p_i1790_1_, EntityLivingBase p_i1790_2) { super(p_i1790_1_, p_i1790_2); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   public EntityBottleTaint(World worldIn, double x, double y, double z) { super(worldIn, x, y, z); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_70103_a(byte id) {
/* 45 */     if (id == 3) {
/*    */       
/* 47 */       for (int a = 0; a < 100; ) { FXDispatcher.INSTANCE.taintsplosionFX(this); a++; }
/* 48 */        FXDispatcher.INSTANCE.bottleTaintBreak(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_70184_a(RayTraceResult ray) {
/* 55 */     if (!this.field_70170_p.field_72995_K) {
/*    */       
/* 57 */       List ents = this.field_70170_p.func_72872_a(EntityLivingBase.class, (new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t, this.field_70163_u, this.field_70161_v))
/*    */ 
/*    */ 
/*    */           
/* 61 */           .func_72314_b(5.0D, 5.0D, 5.0D));
/* 62 */       if (ents.size() > 0) {
/* 63 */         for (Object ent : ents) {
/* 64 */           EntityLivingBase el = (EntityLivingBase)ent;
/* 65 */           if (!(el instanceof thaumcraft.api.entities.ITaintedMob) && !el.func_70662_br()) {
/* 66 */             el.func_70690_d(new PotionEffect(PotionFluxTaint.instance, 100, 0, false, true));
/*    */           }
/*    */         } 
/*    */       }
/*    */       
/* 71 */       for (int a = 0; a < 10; a++) {
/* 72 */         int xx = (int)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 4.0F);
/* 73 */         int zz = (int)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 4.0F);
/* 74 */         BlockPos p = func_180425_c().func_177982_a(xx, 0, zz);
/* 75 */         if (this.field_70170_p.field_73012_v.nextBoolean()) {
/* 76 */           if (this.field_70170_p.func_175677_d(p.func_177977_b(), false) && this.field_70170_p
/* 77 */             .func_180495_p(p).func_177230_c().func_176200_f(this.field_70170_p, p)) {
/* 78 */             this.field_70170_p.func_175656_a(p, BlocksTC.fluxGoo.func_176223_P());
/*    */           } else {
/* 80 */             p = p.func_177977_b();
/* 81 */             if (this.field_70170_p.func_175677_d(p.func_177977_b(), false) && this.field_70170_p
/* 82 */               .func_180495_p(p).func_177230_c().func_176200_f(this.field_70170_p, p)) {
/* 83 */               this.field_70170_p.func_175656_a(p, BlocksTC.fluxGoo.func_176223_P());
/*    */             }
/*    */           } 
/*    */         }
/*    */       } 
/*    */       
/* 89 */       this.field_70170_p.func_72960_a(this, (byte)3);
/* 90 */       func_70106_y();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\projectile\EntityBottleTaint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */