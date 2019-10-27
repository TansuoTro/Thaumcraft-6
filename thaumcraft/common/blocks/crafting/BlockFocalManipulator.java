/*    */ package thaumcraft.common.blocks.crafting;
/*    */ 
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.BlockFaceShape;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.Thaumcraft;
/*    */ import thaumcraft.common.blocks.BlockTCDevice;
/*    */ 
/*    */ 
/*    */ public class BlockFocalManipulator
/*    */   extends BlockTCDevice
/*    */ {
/*    */   public BlockFocalManipulator() {
/* 21 */     super(Material.field_151576_e, thaumcraft.common.tiles.crafting.TileFocalManipulator.class, "wand_workbench");
/* 22 */     func_149672_a(SoundType.field_185851_d);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 28 */   public boolean func_149662_c(IBlockState state) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 34 */   public boolean func_149686_d(IBlockState state) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 40 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 47 */     if (world.field_72995_K) return true; 
/* 48 */     player.openGui(Thaumcraft.instance, 7, world, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/* 49 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\crafting\BlockFocalManipulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */