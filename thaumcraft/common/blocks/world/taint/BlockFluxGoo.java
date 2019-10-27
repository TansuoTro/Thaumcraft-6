/*     */ package thaumcraft.common.blocks.world.taint;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fluids.BlockFluidFinite;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.ThaumcraftMaterials;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.potions.PotionVisExhaust;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.fx.particles.FXGeneric;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.entities.monster.EntityThaumicSlime;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ 
/*     */ public class BlockFluxGoo
/*     */   extends BlockFluidFinite
/*     */ {
/*     */   public BlockFluxGoo() {
/*  31 */     super(ConfigBlocks.FluidFluxGoo.instance, ThaumcraftMaterials.MATERIAL_TAINT);
/*  32 */     setRegistryName("flux_goo");
/*  33 */     func_149663_c("flux_goo");
/*  34 */     func_149647_a(ConfigItems.TABTC);
/*  35 */     func_149672_a(SoundsTC.GORE);
/*  36 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(LEVEL, Integer.valueOf(7)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  41 */   public SoundType func_185467_w() { return SoundsTC.GORE; }
/*     */ 
/*     */   
/*     */   static  {
/*  45 */     defaultDisplacements.put(BlocksTC.taintFibre, Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180634_a(World world, BlockPos pos, IBlockState state, Entity entity) {
/*  50 */     int md = ((Integer)state.func_177229_b(LEVEL)).intValue();
/*  51 */     if (entity instanceof EntityThaumicSlime) {
/*  52 */       EntityThaumicSlime slime = (EntityThaumicSlime)entity;
/*  53 */       if (slime.func_70809_q() < md && world.field_73012_v.nextBoolean()) {
/*  54 */         slime.func_70799_a(slime.func_70809_q() + 1, true);
/*  55 */         if (md > 1) {
/*  56 */           world.func_180501_a(pos, state.func_177226_a(LEVEL, Integer.valueOf(md - 1)), 2);
/*     */         } else {
/*  58 */           world.func_175698_g(pos);
/*     */         } 
/*     */       } 
/*     */     } else {
/*  62 */       entity.field_70159_w *= (1.0F - getQuantaPercentage(world, pos));
/*  63 */       entity.field_70179_y *= (1.0F - getQuantaPercentage(world, pos));
/*  64 */       if (entity instanceof EntityLivingBase) {
/*  65 */         PotionEffect pe = new PotionEffect(PotionVisExhaust.instance, 600, md / 3, true, true);
/*  66 */         pe.getCurativeItems().clear();
/*  67 */         ((EntityLivingBase)entity).func_70690_d(pe);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180650_b(World world, BlockPos pos, IBlockState state, Random rand) {
/*  75 */     int meta = ((Integer)state.func_177229_b(LEVEL)).intValue();
/*     */     
/*  77 */     if (meta >= 2 && meta < 6 && world.func_175623_d(pos.func_177984_a()) && rand.nextInt(50) == 0) {
/*  78 */       world.func_175698_g(pos);
/*  79 */       EntityThaumicSlime slime = new EntityThaumicSlime(world);
/*  80 */       slime.func_70012_b((pos.func_177958_n() + 0.5F), pos.func_177956_o(), (pos.func_177952_p() + 0.5F), 0.0F, 0.0F);
/*  81 */       slime.func_70799_a(1, true);
/*  82 */       world.func_72838_d(slime);
/*  83 */       slime.func_184185_a(SoundsTC.gore, 1.0F, 1.0F); return;
/*     */     } 
/*  85 */     if (meta >= 6 && world.func_175623_d(pos.func_177984_a()) && rand.nextInt(50) == 0) {
/*  86 */       world.func_175698_g(pos);
/*  87 */       EntityThaumicSlime slime = new EntityThaumicSlime(world);
/*  88 */       slime.func_70012_b((pos.func_177958_n() + 0.5F), pos.func_177956_o(), (pos.func_177952_p() + 0.5F), 0.0F, 0.0F);
/*  89 */       slime.func_70799_a(2, true);
/*  90 */       world.func_72838_d(slime);
/*  91 */       slime.func_184185_a(SoundsTC.gore, 1.0F, 1.0F); return;
/*     */     } 
/*  93 */     if (rand.nextInt(4) == 0) {
/*  94 */       if (meta == 0) {
/*  95 */         if (rand.nextBoolean()) {
/*  96 */           AuraHelper.polluteAura(world, pos, 1.0F, true);
/*  97 */           world.func_175698_g(pos);
/*     */         } else {
/*  99 */           world.func_175656_a(pos, BlocksTC.taintFibre.func_176223_P());
/*     */         } 
/*     */       } else {
/* 102 */         world.func_180501_a(pos, state.func_177226_a(LEVEL, Integer.valueOf(meta - 1)), 2);
/* 103 */         AuraHelper.polluteAura(world, pos, 1.0F, true);
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/* 108 */     super.func_180650_b(world, pos, state, rand);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 113 */   public boolean func_176200_f(IBlockAccess world, BlockPos pos) { return (((Integer)world.func_180495_p(pos).func_177229_b(LEVEL)).intValue() < 4); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_180655_c(IBlockState state, World world, BlockPos pos, Random rand) {
/* 131 */     int meta = func_176201_c(state);
/* 132 */     if (rand.nextInt(44) <= meta) {
/*     */ 
/*     */       
/* 135 */       FXGeneric fb = new FXGeneric(world, (pos.func_177958_n() + rand.nextFloat()), (pos.func_177956_o() + 0.125F * meta), (pos.func_177952_p() + rand.nextFloat()), 0.0D, 0.0D, 0.0D);
/* 136 */       fb.func_187114_a(2 + world.field_73012_v.nextInt(3));
/* 137 */       fb.setScale(new float[] { world.field_73012_v.nextFloat() * 0.3F + 0.2F });
/* 138 */       fb.func_70538_b(1.0F, 0.0F, 0.5F);
/* 139 */       fb.setRandomMovementScale(0.001F, 0.001F, 0.001F);
/* 140 */       fb.setGravity(-0.01F);
/* 141 */       fb.func_82338_g(0.25F);
/* 142 */       fb.func_70536_a(64);
/* 143 */       fb.setFinalFrames(new int[] { 65, 66 });
/* 144 */       ParticleEngine.addEffect(world, fb);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\world\taint\BlockFluxGoo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */