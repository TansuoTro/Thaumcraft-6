/*    */ package thaumcraft.common.blocks.world.plants;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockBush;
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.EnumParticleTypes;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.EnumPlantType;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockPlantCinderpearl
/*    */   extends BlockBush
/*    */ {
/*    */   public BlockPlantCinderpearl() {
/* 25 */     super(Material.field_151585_k);
/* 26 */     func_149663_c("cinderpearl");
/* 27 */     setRegistryName("thaumcraft", "cinderpearl");
/* 28 */     func_149647_a(ConfigItems.TABTC);
/* 29 */     func_149672_a(SoundType.field_185850_c);
/* 30 */     func_149715_a(0.5F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean func_185514_i(IBlockState state) {
/* 36 */     return (state.func_177230_c() == Blocks.field_150354_m || state.func_177230_c() == Blocks.field_150346_d || state
/* 37 */       .func_177230_c() == Blocks.field_150406_ce || state.func_177230_c() == Blocks.field_150405_ch);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 43 */   public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) { return EnumPlantType.Desert; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_180655_c(IBlockState state, World world, BlockPos pos, Random rand) {
/* 50 */     if (rand.nextBoolean()) {
/* 51 */       float xr = pos.func_177958_n() + 0.5F + (rand.nextFloat() - rand.nextFloat()) * 0.1F;
/* 52 */       float yr = pos.func_177956_o() + 0.6F + (rand.nextFloat() - rand.nextFloat()) * 0.1F;
/* 53 */       float zr = pos.func_177952_p() + 0.5F + (rand.nextFloat() - rand.nextFloat()) * 0.1F;
/* 54 */       world.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, xr, yr, zr, 0.0D, 0.0D, 0.0D, new int[0]);
/* 55 */       world.func_175688_a(EnumParticleTypes.FLAME, xr, yr, zr, 0.0D, 0.0D, 0.0D, new int[0]);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 61 */   public Block.EnumOffsetType func_176218_Q() { return Block.EnumOffsetType.XZ; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\world\plants\BlockPlantCinderpearl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */