/*    */ package thaumcraft.common.lib.utils;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RandomItemChooser
/*    */ {
/*    */   public Item chooseOnWeight(List<Item> items) {
/* 12 */     double completeWeight = 0.0D;
/* 13 */     for (Item item : items)
/* 14 */       completeWeight += item.getWeight(); 
/* 15 */     double r = Math.random() * completeWeight;
/* 16 */     double countWeight = 0.0D;
/* 17 */     for (Item item : items) {
/* 18 */       countWeight += item.getWeight();
/* 19 */       if (countWeight >= r)
/* 20 */         return item; 
/*    */     } 
/* 22 */     throw new RuntimeException("Should never be shown.");
/*    */   }
/*    */   
/*    */   public static interface Item {
/*    */     double getWeight();
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\li\\utils\RandomItemChooser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */