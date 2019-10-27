/*    */ package thaumcraft.common.items.consumables;
/*    */ 
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.items.ItemTCBase;
/*    */ 
/*    */ public class ItemBathSalts
/*    */   extends ItemTCBase
/*    */ {
/*    */   public ItemBathSalts() {
/* 11 */     super("bath_salts", new String[0]);
/* 12 */     func_77627_a(false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 18 */   public int getEntityLifespan(ItemStack itemStack, World world) { return 200; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\consumables\ItemBathSalts.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */