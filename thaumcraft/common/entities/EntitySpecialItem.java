/*    */ package thaumcraft.common.entities;
/*    */ 
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntitySpecialItem
/*    */   extends EntityItem
/*    */ {
/*    */   public EntitySpecialItem(World par1World, double par2, double par4, double par6, ItemStack par8ItemStack) {
/* 14 */     super(par1World);
/* 15 */     func_70105_a(0.25F, 0.25F);
/* 16 */     func_70107_b(par2, par4, par6);
/* 17 */     func_92058_a(par8ItemStack);
/* 18 */     this.field_70177_z = (float)(Math.random() * 360.0D);
/* 19 */     this.field_70159_w = (float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D);
/* 20 */     this.field_70181_x = 0.20000000298023224D;
/* 21 */     this.field_70179_y = (float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D);
/*    */   }
/*    */   
/*    */   public EntitySpecialItem(World par1World) {
/* 25 */     super(par1World);
/* 26 */     func_70105_a(0.25F, 0.25F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_70071_h_() {
/* 32 */     if (this.field_70173_aa > 1) {
/* 33 */       if (this.field_70181_x > 0.0D) this.field_70181_x *= 0.8999999761581421D; 
/* 34 */       this.field_70181_x += 0.03999999910593033D;
/* 35 */       super.func_70071_h_();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_70097_a(DamageSource source, float damage) {
/* 42 */     if (source.func_94541_c())
/*    */     {
/* 44 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 48 */     return super.func_70097_a(source, damage);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\EntitySpecialItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */