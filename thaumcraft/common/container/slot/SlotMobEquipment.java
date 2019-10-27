/*    */ package thaumcraft.common.container.slot;
/*    */ 
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumHand;
/*    */ 
/*    */ 
/*    */ public class SlotMobEquipment
/*    */   extends Slot
/*    */ {
/*    */   EntityLiving entity;
/*    */   
/*    */   public SlotMobEquipment(EntityLiving entity, int par3, int par4, int par5) {
/* 16 */     super(null, par3, par4, par5);
/* 17 */     this.entity = entity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   public ItemStack func_75211_c() { return this.entity.func_184586_b(EnumHand.MAIN_HAND); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_75215_d(ItemStack stack) {
/* 30 */     this.entity.func_184611_a(EnumHand.MAIN_HAND, stack);
/*    */     
/* 32 */     if (stack != null && !stack.func_190926_b() && stack.func_190916_E() > func_75219_a())
/*    */     {
/* 34 */       stack.func_190920_e(func_75219_a());
/*    */     }
/* 36 */     func_75218_e();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_75218_e() {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 48 */   public int func_75219_a() { return 64; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack func_75209_a(int amount) {
/* 54 */     if (!func_75211_c().func_190926_b()) {
/*    */ 
/*    */ 
/*    */       
/* 58 */       if (func_75211_c().func_190916_E() <= amount) {
/*    */         
/* 60 */         ItemStack itemstack = func_75211_c();
/* 61 */         func_75215_d(ItemStack.field_190927_a);
/* 62 */         return itemstack;
/*    */       } 
/*    */ 
/*    */       
/* 66 */       ItemStack itemstack = func_75211_c().func_77979_a(amount);
/* 67 */       if (func_75211_c().func_190916_E() == 0)
/*    */       {
/* 69 */         func_75215_d(ItemStack.field_190927_a);
/*    */       }
/* 71 */       return itemstack;
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 76 */     return ItemStack.field_190927_a;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 84 */   public boolean func_75217_a(IInventory inv, int slotIn) { return (slotIn == getSlotIndex()); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\slot\SlotMobEquipment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */