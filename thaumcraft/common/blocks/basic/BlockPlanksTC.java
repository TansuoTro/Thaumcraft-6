/*    */ package thaumcraft.common.blocks.basic;
/*    */ 
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import thaumcraft.common.blocks.BlockTC;
/*    */ 
/*    */ 
/*    */ public class BlockPlanksTC
/*    */   extends BlockTC
/*    */ {
/*    */   public BlockPlanksTC(String name) {
/* 15 */     super(Material.field_151575_d, name);
/* 16 */     setHarvestLevel("axe", 0);
/* 17 */     func_149711_c(2.0F);
/* 18 */     func_149672_a(SoundType.field_185848_a);
/*    */   }
/*    */ 
/*    */   
/* 22 */   public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) { return 20; }
/*    */ 
/*    */   
/* 25 */   public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) { return 5; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\basic\BlockPlanksTC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */