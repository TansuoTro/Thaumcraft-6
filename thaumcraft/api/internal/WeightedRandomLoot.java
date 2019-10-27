/*    */ package thaumcraft.api.internal;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.WeightedRandom;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WeightedRandomLoot
/*    */   extends WeightedRandom.Item
/*    */ {
/*    */   public ItemStack item;
/*    */   
/*    */   public WeightedRandomLoot(ItemStack stack, int weight) {
/* 16 */     super(weight);
/* 17 */     this.item = stack;
/*    */   }
/*    */   
/* 20 */   public static ArrayList<WeightedRandomLoot> lootBagCommon = new ArrayList();
/* 21 */   public static ArrayList<WeightedRandomLoot> lootBagUncommon = new ArrayList();
/* 22 */   public static ArrayList<WeightedRandomLoot> lootBagRare = new ArrayList();
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\internal\WeightedRandomLoot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */