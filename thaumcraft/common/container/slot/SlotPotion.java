/*    */ package thaumcraft.common.container.slot;
/*    */ 
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.init.PotionTypes;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.PotionType;
/*    */ import net.minecraft.potion.PotionUtils;
/*    */ 
/*    */ public class SlotPotion
/*    */   extends Slot
/*    */ {
/* 14 */   int limit = 64;
/*    */ 
/*    */ 
/*    */   
/* 18 */   public SlotPotion(IInventory par2IInventory, int par3, int par4, int par5) { super(par2IInventory, par3, par4, par5); }
/*    */ 
/*    */ 
/*    */   
/*    */   public SlotPotion(int limit, IInventory par2IInventory, int par3, int par4, int par5) {
/* 23 */     super(par2IInventory, par3, par4, par5);
/* 24 */     this.limit = limit;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 30 */   public boolean func_75214_a(ItemStack stack) { return (stack != null && !stack.func_190926_b() && isValidPotion(stack)); }
/*    */ 
/*    */   
/*    */   public static boolean isValidPotion(ItemStack stack) {
/* 34 */     if (stack.func_77973_b() == Items.field_151068_bn || stack
/* 35 */       .func_77973_b() == Items.field_185156_bI || stack
/* 36 */       .func_77973_b() == Items.field_185155_bH) {
/*    */       try {
/* 38 */         PotionType potion = PotionUtils.func_185191_c(stack);
/* 39 */         return (potion != null && potion != PotionTypes.field_185230_b && potion != PotionTypes.field_185233_e && potion != PotionTypes.field_185229_a && potion != PotionTypes.field_185231_c && potion != PotionTypes.field_185232_d);
/*    */       
/*    */       }
/* 42 */       catch (Exception exception) {}
/*    */     }
/* 44 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 50 */   public int func_75219_a() { return this.limit; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\slot\SlotPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */