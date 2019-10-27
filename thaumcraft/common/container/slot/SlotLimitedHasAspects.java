/*    */ package thaumcraft.common.container.slot;
/*    */ 
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*    */ 
/*    */ 
/*    */ public class SlotLimitedHasAspects
/*    */   extends Slot
/*    */ {
/* 13 */   public SlotLimitedHasAspects(IInventory par2IInventory, int par3, int par4, int par5) { super(par2IInventory, par3, par4, par5); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75214_a(ItemStack stack1) {
/* 19 */     AspectList al = ThaumcraftCraftingManager.getObjectTags(stack1);
/* 20 */     if (al != null && al.size() > 0) return true; 
/* 21 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\slot\SlotLimitedHasAspects.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */