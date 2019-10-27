/*    */ package thaumcraft.common.blocks.devices;
/*    */ 
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.blocks.BlockTCDevice;
/*    */ import thaumcraft.common.blocks.IBlockEnabled;
/*    */ import thaumcraft.common.tiles.devices.TileDioptra;
/*    */ 
/*    */ public class BlockDioptra
/*    */   extends BlockTCDevice implements IBlockEnabled {
/*    */   public BlockDioptra() {
/* 20 */     super(Material.field_151576_e, TileDioptra.class, "dioptra");
/* 21 */     func_149672_a(SoundType.field_185851_d);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 26 */   public int func_180651_a(IBlockState state) { return 0; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 32 */   public boolean func_149662_c(IBlockState state) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   public boolean func_149686_d(IBlockState state) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 43 */   public boolean func_149740_M(IBlockState state) { return true; }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_180641_l(IBlockState state, World world, BlockPos pos) {
/* 48 */     TileEntity tile = world.func_175625_s(pos);
/* 49 */     if (tile != null && tile instanceof TileDioptra) {
/* 50 */       float r = ((TileDioptra)tile).grid_amt[84] / 64.0F;
/* 51 */       return MathHelper.func_76141_d(r * 14.0F) + ((r > 0.0F) ? 1 : 0);
/*    */     } 
/* 53 */     return super.func_180641_l(state, world, pos);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void updateState(World worldIn, BlockPos pos, IBlockState state) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 67 */     boolean b = ((Boolean)state.func_177229_b(IBlockEnabled.ENABLED)).booleanValue();
/* 68 */     world.func_180501_a(pos, state.func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(!b)), 3);
/*    */     
/* 70 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\devices\BlockDioptra.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */