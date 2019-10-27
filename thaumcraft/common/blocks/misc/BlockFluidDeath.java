/*    */ package thaumcraft.common.blocks.misc;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.material.MapColor;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.material.MaterialLiquid;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.init.SoundEvents;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fluids.BlockFluidClassic;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.damagesource.DamageSourceThaumcraft;
/*    */ import thaumcraft.client.fx.ParticleEngine;
/*    */ import thaumcraft.client.fx.particles.FXSlimyBubble;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ 
/*    */ 
/*    */ public class BlockFluidDeath
/*    */   extends BlockFluidClassic
/*    */ {
/* 26 */   public static final Material FLUID_DEATH_MATERIAL = new MaterialLiquid(MapColor.field_151678_z);
/*    */   
/*    */   public BlockFluidDeath() {
/* 29 */     super(ConfigBlocks.FluidDeath.instance, FLUID_DEATH_MATERIAL);
/* 30 */     setRegistryName("liquid_death");
/* 31 */     func_149663_c("liquid_death");
/* 32 */     func_149647_a(ConfigItems.TABTC);
/* 33 */     setQuantaPerBlock(4);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180634_a(World world, BlockPos pos, IBlockState state, Entity entity) {
/* 38 */     entity.field_70159_w *= (1.0F - getQuantaPercentage(world, pos) / 2.0F);
/* 39 */     entity.field_70179_y *= (1.0F - getQuantaPercentage(world, pos) / 2.0F);
/* 40 */     if (!world.field_72995_K && entity instanceof net.minecraft.entity.EntityLivingBase) {
/* 41 */       entity.func_70097_a(DamageSourceThaumcraft.dissolve, (4 - func_176201_c(state) + 1));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/* 46 */   public int getQuanta() { return this.quantaPerBlock; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_180655_c(IBlockState state, World world, BlockPos pos, Random rand) {
/* 54 */     if (rand.nextInt(20) == 0) {
/* 55 */       int meta = func_176201_c(state);
/* 56 */       float h = rand.nextFloat() * 0.075F;
/*    */ 
/*    */ 
/*    */       
/* 60 */       FXSlimyBubble ef = new FXSlimyBubble(world, (pos.func_177958_n() + rand.nextFloat()), (pos.func_177956_o() + 0.1F + 0.225F * (4 - meta)), (pos.func_177952_p() + rand.nextFloat()), 0.075F + h);
/*    */ 
/*    */       
/* 63 */       ef.func_82338_g(0.8F);
/* 64 */       ef.func_70538_b(0.3F - rand.nextFloat() * 0.1F, 0.0F, 0.4F + rand.nextFloat() * 0.1F);
/*    */       
/* 66 */       ParticleEngine.addEffect(world, ef);
/*    */     } 
/*    */     
/* 69 */     if (rand.nextInt(50) == 0) {
/* 70 */       double var21 = (pos.func_177958_n() + rand.nextFloat());
/* 71 */       double var22 = pos.func_177956_o() + (getMaxRenderHeightMeta() / 4.0F);
/* 72 */       double var23 = (pos.func_177952_p() + rand.nextFloat());
/* 73 */       world.func_184134_a(var21, var22, var23, SoundEvents.field_187662_cZ, SoundCategory.BLOCKS, 0.1F + rand
/* 74 */           .nextFloat() * 0.1F, 0.9F + rand
/* 75 */           .nextFloat() * 0.15F, false);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\misc\BlockFluidDeath.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */