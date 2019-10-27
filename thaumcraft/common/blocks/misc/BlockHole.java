/*    */ package thaumcraft.common.blocks.misc;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.BlockContainer;
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.BlockFaceShape;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.NonNullList;
/*    */ import net.minecraft.util.math.AxisAlignedBB;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.common.tiles.misc.TileHole;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockHole
/*    */   extends BlockContainer
/*    */ {
/*    */   public BlockHole() {
/* 32 */     super(Material.field_151576_e);
/* 33 */     func_149663_c("hole");
/* 34 */     setRegistryName("thaumcraft", "hole");
/* 35 */     func_149722_s();
/* 36 */     func_149752_b(6000000.0F);
/* 37 */     func_149672_a(SoundType.field_185854_g);
/* 38 */     func_149715_a(0.7F);
/* 39 */     func_149675_a(true);
/*    */     
/* 41 */     func_149647_a(null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 48 */   public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) { return ItemStack.field_190927_a; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149666_a(CreativeTabs par2CreativeTabs, NonNullList<ItemStack> par3List) {}
/*    */ 
/*    */ 
/*    */   
/* 63 */   public boolean isSideSolid(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing o) { return true; }
/*    */ 
/*    */ 
/*    */   
/* 67 */   public AxisAlignedBB func_180646_a(IBlockState state, IBlockAccess worldIn, BlockPos pos) { return null; }
/*    */ 
/*    */ 
/*    */   
/* 71 */   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) { return field_185505_j; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 76 */   public AxisAlignedBB func_180640_a(IBlockState blockState, World worldIn, BlockPos pos) { return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 81 */   public boolean func_149686_d(IBlockState blockState) { return false; }
/*    */ 
/*    */   
/* 84 */   public boolean func_149662_c(IBlockState blockState) { return false; }
/*    */ 
/*    */   
/* 87 */   public TileEntity func_149915_a(World var1, int var2) { return new TileHole(); }
/*    */ 
/*    */ 
/*    */   
/* 91 */   public Item func_180660_a(IBlockState state, Random rand, int fortune) { return Item.func_150899_d(0); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\misc\BlockHole.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */