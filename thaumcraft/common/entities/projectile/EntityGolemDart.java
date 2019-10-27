/*    */ package thaumcraft.common.entities.projectile;
/*    */ 
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.projectile.EntityArrow;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class EntityGolemDart
/*    */   extends EntityArrow
/*    */ {
/*    */   public EntityGolemDart(World par1World) {
/* 14 */     super(par1World);
/* 15 */     func_70105_a(0.2F, 0.2F);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityGolemDart(World par1World, double par2, double par4, double par6) {
/* 20 */     super(par1World, par2, par4, par6);
/* 21 */     func_70105_a(0.2F, 0.2F);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityGolemDart(World par1World, EntityLivingBase par2EntityLivingBase) {
/* 26 */     super(par1World, par2EntityLivingBase);
/* 27 */     func_70105_a(0.2F, 0.2F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 32 */   protected ItemStack func_184550_j() { return new ItemStack(Items.field_151032_g); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\projectile\EntityGolemDart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */