/*    */ package thaumcraft.common.lib;
/*    */ 
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ 
/*    */ 
/*    */ public final class CreativeTabThaumcraft
/*    */   extends CreativeTabs
/*    */ {
/* 13 */   public CreativeTabThaumcraft(int par1, String par2Str) { super(par1, par2Str); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/* 24 */   public ItemStack func_78016_d() { return new ItemStack(ItemsTC.goggles); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\CreativeTabThaumcraft.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */