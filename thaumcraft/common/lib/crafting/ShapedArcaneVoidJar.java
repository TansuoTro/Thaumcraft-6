/*    */ package thaumcraft.common.lib.crafting;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.inventory.InventoryCrafting;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.api.blocks.BlocksTC;
/*    */ import thaumcraft.api.crafting.ShapedArcaneRecipe;
/*    */ 
/*    */ 
/*    */ public class ShapedArcaneVoidJar
/*    */   extends ShapedArcaneRecipe
/*    */ {
/* 16 */   public ShapedArcaneVoidJar(ResourceLocation group, String res, int vis, AspectList crystals, ItemStack result, Object... recipe) { super(group, res, vis, crystals, result, recipe); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack func_77572_b(InventoryCrafting var1) {
/* 21 */     NBTTagCompound nbt = null;
/* 22 */     for (int a = 0; a < var1.func_70302_i_(); a++) {
/* 23 */       if (Block.func_149634_a(var1.func_70301_a(a).func_77973_b()) == BlocksTC.jarNormal) {
/* 24 */         nbt = var1.func_70301_a(a).func_77978_p();
/*    */         break;
/*    */       } 
/*    */     } 
/* 28 */     ItemStack res = super.func_77572_b(var1);
/* 29 */     res.func_77982_d(nbt);
/* 30 */     return res;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\crafting\ShapedArcaneVoidJar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */