/*    */ package thaumcraft.common.blocks.basic;
/*    */ 
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import thaumcraft.common.blocks.BlockTC;
/*    */ 
/*    */ public class BlockStoneTC
/*    */   extends BlockTC
/*    */ {
/*    */   private boolean spawn;
/*    */   
/*    */   public BlockStoneTC(String name, boolean spawn) {
/* 17 */     super(Material.field_151576_e, name);
/* 18 */     this.spawn = spawn;
/* 19 */     func_149711_c(2.0F);
/* 20 */     func_149752_b(10.0F);
/* 21 */     func_149672_a(SoundType.field_185851_d);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 26 */   public boolean isBeaconBase(IBlockAccess world, BlockPos pos, BlockPos beacon) { return true; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 32 */   public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity) { return (this.field_149782_v >= 0.0F); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\basic\BlockStoneTC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */