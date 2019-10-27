/*    */ package thaumcraft.common.blocks.devices;
/*    */ 
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.BlockFaceShape;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.util.BlockRenderLayer;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.crafting.IInfusionStabiliserExt;
/*    */ import thaumcraft.common.blocks.BlockTCDevice;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockStabilizer
/*    */   extends BlockTCDevice
/*    */   implements IInfusionStabiliserExt
/*    */ {
/*    */   public BlockStabilizer() {
/* 28 */     super(Material.field_151576_e, thaumcraft.common.tiles.devices.TileStabilizer.class, "stabilizer");
/* 29 */     func_149672_a(SoundType.field_185851_d);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 35 */   public boolean func_149662_c(IBlockState state) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 41 */   public boolean func_149686_d(IBlockState state) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 46 */   public int func_180651_a(IBlockState state) { return 0; }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public static int colorMultiplier(int meta) {
/* 52 */     float f = meta / 15.0F;
/* 53 */     float f1 = f * 0.5F + 0.5F;
/* 54 */     if (meta == 0) f1 = 0.3F; 
/* 55 */     int i = MathHelper.func_76125_a((int)(f1 * 255.0F), 0, 255);
/* 56 */     int j = MathHelper.func_76125_a((int)(f1 * 255.0F), 0, 255);
/* 57 */     int k = MathHelper.func_76125_a((int)(f1 * 255.0F), 0, 255);
/* 58 */     return 0xFF000000 | i << 16 | j << 8 | k;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 63 */   public BlockRenderLayer func_180664_k() { return BlockRenderLayer.CUTOUT; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 69 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 74 */   public int func_149750_m(IBlockState state) { return 4; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 79 */   public boolean canStabaliseInfusion(World world, BlockPos pos) { return true; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 84 */   public float getStabilizationAmount(World world, BlockPos pos) { return 0.25F; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\devices\BlockStabilizer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */