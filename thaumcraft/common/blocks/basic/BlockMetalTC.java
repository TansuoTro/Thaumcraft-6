/*    */ package thaumcraft.common.blocks.basic;
/*    */ 
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import thaumcraft.common.blocks.BlockTC;
/*    */ 
/*    */ 
/*    */ public class BlockMetalTC
/*    */   extends BlockTC
/*    */ {
/*    */   public BlockMetalTC(String name) {
/* 14 */     super(Material.field_151573_f, name);
/* 15 */     func_149711_c(4.0F);
/* 16 */     func_149752_b(10.0F);
/* 17 */     func_149672_a(SoundType.field_185852_e);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 22 */   public boolean isBeaconBase(IBlockAccess world, BlockPos pos, BlockPos beacon) { return true; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\basic\BlockMetalTC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */