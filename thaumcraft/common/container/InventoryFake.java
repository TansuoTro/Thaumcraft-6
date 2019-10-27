/*    */ package thaumcraft.common.container;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.inventory.InventoryBasic;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.NonNullList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InventoryFake
/*    */   extends InventoryBasic
/*    */ {
/* 13 */   public InventoryFake(int size) { super("container.fake", false, size); }
/*    */ 
/*    */ 
/*    */   
/*    */   public InventoryFake(NonNullList<ItemStack> inv) {
/* 18 */     super("container.fake", false, inv.size());
/* 19 */     for (int a = 0; a < inv.size(); a++) {
/* 20 */       func_70299_a(a, (ItemStack)inv.get(a));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public InventoryFake(ItemStack... stacks) {
/* 26 */     super("container.fake", false, stacks.length);
/* 27 */     for (int a = 0; a < stacks.length; a++) {
/* 28 */       func_70299_a(a, stacks[a]);
/*    */     }
/*    */   }
/*    */   
/*    */   public InventoryFake(ArrayList<ItemStack> inv) {
/* 33 */     super("container.fake", false, inv.size());
/* 34 */     for (int a = 0; a < inv.size(); a++)
/* 35 */       func_70299_a(a, (ItemStack)inv.get(a)); 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\InventoryFake.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */