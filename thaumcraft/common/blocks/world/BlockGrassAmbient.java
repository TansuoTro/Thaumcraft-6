/*    */ package thaumcraft.common.blocks.world;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.BlockGrass;
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.EnumSkyBlock;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.biome.Biome;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockGrassAmbient
/*    */   extends BlockGrass
/*    */ {
/*    */   public BlockGrassAmbient() {
/* 25 */     func_149663_c("grass_ambient");
/* 26 */     setRegistryName("thaumcraft", "grass_ambient");
/* 27 */     func_149647_a(ConfigItems.TABTC);
/* 28 */     func_149711_c(0.6F);
/* 29 */     func_149672_a(SoundType.field_185849_b);
/*    */   }
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_180655_c(IBlockState state, World worldIn, BlockPos pos, Random rand) {
/* 35 */     Biome biome = worldIn.func_180494_b(pos);
/*    */     
/* 37 */     int i = worldIn.func_175642_b(EnumSkyBlock.SKY, pos.func_177984_a()) - worldIn.func_175657_ab();
/* 38 */     float f = worldIn.func_72929_e(1.0F);
/* 39 */     float f1 = (f < 3.1415927F) ? 0.0F : 6.2831855F;
/* 40 */     f += (f1 - f) * 0.2F;
/* 41 */     i = Math.round(i * MathHelper.func_76134_b(f));
/* 42 */     i = MathHelper.func_76125_a(i, 0, 15);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 49 */     if (4 + i * 2 < 1 + rand
/* 50 */       .nextInt(13)) {
/* 51 */       int x = MathHelper.func_76136_a(rand, -8, 8);
/* 52 */       int z = MathHelper.func_76136_a(rand, -8, 8);
/* 53 */       BlockPos pp = pos.func_177982_a(x, 5, z);
/* 54 */       int q = 0;
/* 55 */       while (q < 10 && pp.func_177956_o() > 50 && worldIn.func_180495_p(pp).func_177230_c() != Blocks.field_150349_c) {
/* 56 */         pp = pp.func_177977_b();
/* 57 */         q++;
/*    */       } 
/* 59 */       if (worldIn.func_180495_p(pp).func_177230_c() == Blocks.field_150349_c)
/* 60 */         FXDispatcher.INSTANCE.drawWispyMotesOnBlock(pp.func_177984_a(), 400, -0.01F); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\world\BlockGrassAmbient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */