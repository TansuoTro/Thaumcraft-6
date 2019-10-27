/*    */ package thaumcraft.common.blocks.crafting;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.Thaumcraft;
/*    */ import thaumcraft.client.fx.ParticleEngine;
/*    */ import thaumcraft.client.fx.particles.FXGeneric;
/*    */ import thaumcraft.common.blocks.BlockTCDevice;
/*    */ import thaumcraft.common.blocks.IBlockFacingHorizontal;
/*    */ import thaumcraft.common.tiles.crafting.TileResearchTable;
/*    */ 
/*    */ public class BlockResearchTable
/*    */   extends BlockTCDevice
/*    */   implements IBlockFacingHorizontal
/*    */ {
/*    */   public BlockResearchTable() {
/* 29 */     super(Material.field_151575_d, TileResearchTable.class, "research_table");
/* 30 */     func_149672_a(SoundType.field_185848_a);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public int func_180651_a(IBlockState state) { return 0; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 41 */   public boolean func_149662_c(IBlockState state) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 47 */   public boolean func_149686_d(IBlockState state) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 52 */   public boolean isSideSolid(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 61 */     if (world.field_72995_K) return true;
/*    */     
/* 63 */     player.openGui(Thaumcraft.instance, 10, world, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*    */     
/* 65 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/* 71 */     bs = func_176223_P();
/* 72 */     return bs.func_177226_a(IBlockFacingHorizontal.FACING, placer.func_174811_aO());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_180655_c(IBlockState state, World world, BlockPos pos, Random rand) {
/* 79 */     TileEntity te = world.func_175625_s(pos);
/* 80 */     if (rand.nextInt(5) == 0 && te != null && ((TileResearchTable)te).data != null) {
/* 81 */       double xx = rand.nextGaussian() / 2.0D;
/* 82 */       double zz = rand.nextGaussian() / 2.0D;
/* 83 */       double yy = 1.5D + rand.nextFloat();
/* 84 */       int a = 40 + rand.nextInt(20);
/* 85 */       FXGeneric fb = new FXGeneric(world, pos.func_177958_n() + 0.5D + xx, pos.func_177956_o() + yy, pos.func_177952_p() + 0.5D + zz, -xx / a, -(yy - 0.85D) / a, -zz / a);
/*    */       
/* 87 */       fb.func_187114_a(a);
/* 88 */       fb.func_70538_b(0.5F + rand.nextFloat() * 0.5F, 0.5F + rand.nextFloat() * 0.5F, 0.5F + rand.nextFloat() * 0.5F);
/* 89 */       fb.setAlphaF(new float[] { 0.0F, 0.25F, 0.5F, 0.75F, 0.0F });
/* 90 */       fb.setParticles(384 + rand.nextInt(16), 1, 1);
/* 91 */       fb.setScale(new float[] { 0.8F + rand.nextFloat() * 0.3F, 0.3F });
/* 92 */       fb.setLayer(0);
/* 93 */       ParticleEngine.addEffect(world, fb);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\crafting\BlockResearchTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */