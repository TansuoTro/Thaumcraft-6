/*    */ package thaumcraft.common.blocks.misc;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.material.MapColor;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.material.MaterialLiquid;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.SoundEvents;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fluids.BlockFluidClassic;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.capabilities.IPlayerWarp;
/*    */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*    */ import thaumcraft.client.fx.ParticleEngine;
/*    */ import thaumcraft.client.fx.particles.FXGeneric;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ import thaumcraft.common.lib.potions.PotionWarpWard;
/*    */ 
/*    */ public class BlockFluidPure
/*    */   extends BlockFluidClassic
/*    */ {
/* 29 */   public static final Material FLUID_PURE_MATERIAL = new MaterialLiquid(MapColor.field_151680_x);
/*    */   
/*    */   public BlockFluidPure() {
/* 32 */     super(ConfigBlocks.FluidPure.instance, FLUID_PURE_MATERIAL);
/* 33 */     setRegistryName("purifying_fluid");
/* 34 */     func_149663_c("purifying_fluid");
/* 35 */     func_149647_a(ConfigItems.TABTC);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_180634_a(World world, BlockPos pos, IBlockState state, Entity entity) {
/* 41 */     entity.field_70159_w *= (1.0F - getQuantaPercentage(world, pos) / 2.0F);
/* 42 */     entity.field_70179_y *= (1.0F - getQuantaPercentage(world, pos) / 2.0F);
/* 43 */     if (!world.field_72995_K && isSourceBlock(world, pos) && entity instanceof EntityPlayer && 
/* 44 */       !((EntityPlayer)entity).func_70644_a(PotionWarpWard.instance)) {
/*    */       
/* 46 */       int warp = ThaumcraftCapabilities.getWarp((EntityPlayer)entity).get(IPlayerWarp.EnumWarpType.PERMANENT);
/* 47 */       int div = 1;
/* 48 */       if (warp > 0) {
/* 49 */         div = (int)Math.sqrt(warp);
/* 50 */         if (div < 1)
/* 51 */           div = 1; 
/*    */       } 
/* 53 */       ((EntityPlayer)entity).func_70690_d(new PotionEffect(PotionWarpWard.instance, Math.min(32000, 200000 / div), 0, true, true));
/*    */       
/* 55 */       world.func_175698_g(pos);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_180655_c(IBlockState state, World world, BlockPos pos, Random rand) {
/* 63 */     int meta = func_176201_c(state);
/*    */     
/* 65 */     if (rand.nextInt(10) == 0) {
/*    */       
/* 67 */       FXGeneric fb = new FXGeneric(world, (pos.func_177958_n() + rand.nextFloat()), (pos.func_177956_o() + 0.125F * (8 - meta)), (pos.func_177952_p() + rand.nextFloat()), 0.0D, 0.0D, 0.0D);
/* 68 */       fb.func_187114_a(10 + world.field_73012_v.nextInt(10));
/* 69 */       fb.setScale(new float[] { world.field_73012_v.nextFloat() * 0.3F + 0.3F });
/* 70 */       fb.func_70538_b(1.0F, 1.0F, 1.0F);
/* 71 */       fb.setRandomMovementScale(0.001F, 0.001F, 0.001F);
/* 72 */       fb.setGravity(-0.01F);
/* 73 */       fb.func_82338_g(0.25F);
/* 74 */       fb.setParticle(64);
/* 75 */       fb.setFinalFrames(new int[] { 65, 66 });
/* 76 */       ParticleEngine.addEffect(world, fb);
/*    */     } 
/*    */     
/* 79 */     if (rand.nextInt(50) == 0) {
/* 80 */       double var21 = (pos.func_177958_n() + rand.nextFloat());
/* 81 */       double var22 = pos.func_177956_o() + 0.5D;
/* 82 */       double var23 = (pos.func_177952_p() + rand.nextFloat());
/* 83 */       world.func_184134_a(var21, var22, var23, SoundEvents.field_187662_cZ, SoundCategory.BLOCKS, 0.1F + rand
/* 84 */           .nextFloat() * 0.1F, 0.9F + rand
/* 85 */           .nextFloat() * 0.15F, false);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\misc\BlockFluidPure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */