/*    */ package thaumcraft.common.blocks.misc;
/*    */ 
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import thaumcraft.common.blocks.BlockTC;
/*    */ import thaumcraft.common.lib.SoundsTC;
/*    */ 
/*    */ public class BlockFlesh
/*    */   extends BlockTC
/*    */ {
/*    */   public BlockFlesh() {
/* 12 */     super(Material.field_151583_m, "flesh_block");
/* 13 */     func_149752_b(2.0F);
/* 14 */     func_149711_c(0.25F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 19 */   public SoundType func_185467_w() { return SoundsTC.GORE; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\misc\BlockFlesh.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */