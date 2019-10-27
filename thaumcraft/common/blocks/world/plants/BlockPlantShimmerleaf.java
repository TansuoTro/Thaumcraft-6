/*    */ package thaumcraft.common.blocks.world.plants;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockBush;
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.EnumPlantType;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockPlantShimmerleaf
/*    */   extends BlockBush
/*    */ {
/*    */   public BlockPlantShimmerleaf() {
/* 25 */     super(Material.field_151585_k);
/* 26 */     func_149663_c("shimmerleaf");
/* 27 */     setRegistryName("thaumcraft", "shimmerleaf");
/* 28 */     func_149647_a(ConfigItems.TABTC);
/* 29 */     func_149672_a(SoundType.field_185850_c);
/* 30 */     func_149715_a(0.4F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 35 */   protected boolean func_185514_i(IBlockState state) { return (state.func_177230_c() == Blocks.field_150349_c || state.func_177230_c() == Blocks.field_150346_d); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 41 */   public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) { return EnumPlantType.Plains; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_180655_c(IBlockState state, World world, BlockPos pos, Random rand) {
/* 48 */     if (rand.nextInt(3) == 0) {
/* 49 */       float xr = (float)((pos.func_177958_n() + 0.5F) + rand.nextGaussian() * 0.1D);
/* 50 */       float yr = (float)((pos.func_177956_o() + 0.4F) + rand.nextGaussian() * 0.1D);
/* 51 */       float zr = (float)((pos.func_177952_p() + 0.5F) + rand.nextGaussian() * 0.1D);
/* 52 */       FXDispatcher.INSTANCE.drawWispyMotes(xr, yr, zr, rand
/* 53 */           .nextGaussian() * 0.01D, rand.nextGaussian() * 0.01D, rand.nextGaussian() * 0.01D, 10, 0.3F + world.field_73012_v
/* 54 */           .nextFloat() * 0.3F, 0.7F + world.field_73012_v.nextFloat() * 0.3F, 0.7F + world.field_73012_v.nextFloat() * 0.3F, 0.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 60 */   public Block.EnumOffsetType func_176218_Q() { return Block.EnumOffsetType.XZ; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\world\plants\BlockPlantShimmerleaf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */