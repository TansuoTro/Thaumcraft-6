/*    */ package thaumcraft.common.blocks.misc;
/*    */ 
/*    */ import net.minecraft.block.ITileEntityProvider;
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.MapColor;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.BlockFaceShape;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.item.EnumDyeColor;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.EnumBlockRenderType;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.math.AxisAlignedBB;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.blocks.BlockTC;
/*    */ import thaumcraft.common.tiles.misc.TileNitor;
/*    */ 
/*    */ public class BlockNitor
/*    */   extends BlockTC
/*    */   implements ITileEntityProvider {
/*    */   public final EnumDyeColor dye;
/*    */   
/*    */   public BlockNitor(String name, EnumDyeColor dye) {
/* 26 */     super(Material.field_151594_q, name);
/* 27 */     func_149711_c(0.1F);
/* 28 */     func_149672_a(SoundType.field_185854_g);
/* 29 */     func_149715_a(1.0F);
/* 30 */     this.dye = dye;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public TileEntity func_149915_a(World worldIn, int meta) { return new TileNitor(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 40 */   public boolean hasTileEntity(IBlockState state) { return true; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   public MapColor func_180659_g(IBlockState state, IBlockAccess worldIn, BlockPos pos) { return MapColor.func_193558_a(this.dye); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 51 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 56 */   public EnumBlockRenderType func_149645_b(IBlockState state) { return EnumBlockRenderType.INVISIBLE; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 61 */   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) { return new AxisAlignedBB(0.33000001311302185D, 0.33000001311302185D, 0.33000001311302185D, 0.6600000262260437D, 0.6600000262260437D, 0.6600000262260437D); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 66 */   public AxisAlignedBB func_180646_a(IBlockState state, IBlockAccess worldIn, BlockPos pos) { return null; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 73 */   public boolean func_149686_d(IBlockState state) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 79 */   public boolean func_149662_c(IBlockState state) { return false; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\misc\BlockNitor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */