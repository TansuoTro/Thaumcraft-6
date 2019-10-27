/*    */ package thaumcraft.common.blocks;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.NonNullList;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockTC
/*    */   extends Block
/*    */ {
/*    */   public BlockTC(Material material, String name) {
/* 20 */     super(material);
/* 21 */     func_149663_c(name);
/* 22 */     setRegistryName("thaumcraft", name);
/* 23 */     func_149647_a(ConfigItems.TABTC);
/* 24 */     func_149752_b(2.0F);
/* 25 */     func_149711_c(1.5F);
/*    */   }
/*    */   
/*    */   public BlockTC(Material mat, String name, SoundType st) {
/* 29 */     this(mat, name);
/* 30 */     func_149672_a(st);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/* 36 */   public void func_149666_a(CreativeTabs tab, NonNullList<ItemStack> list) { list.add(new ItemStack(this, 1, 0)); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 41 */   public int func_180651_a(IBlockState state) { return 0; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\BlockTC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */