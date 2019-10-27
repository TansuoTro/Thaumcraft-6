/*     */ package thaumcraft.common.blocks.misc;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.BlockFaceShape;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.MobEffects;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumBlockRenderType;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.events.ServerEvents;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockEffect
/*     */   extends BlockTC
/*     */ {
/*     */   public BlockEffect(String name) {
/*  39 */     super(Material.field_151579_a, name);
/*  40 */     func_149675_a(true);
/*  41 */     this.field_149781_w = 999.0F;
/*  42 */     func_149715_a(0.5F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
/*  48 */     if (state.func_177230_c() != this) return super.getLightValue(state, world, pos); 
/*  49 */     if (state.func_177230_c() == BlocksTC.effectGlimmer) {
/*  50 */       return 15;
/*     */     }
/*  52 */     return super.getLightValue(state, world, pos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180634_a(World world, BlockPos pos, IBlockState state, final Entity entity) {
/*  63 */     if (state.func_177230_c() == BlocksTC.effectShock) {
/*  64 */       if (entity instanceof EntityLivingBase)
/*  65 */         ServerEvents.addRunnableServer(world, new Runnable() { public void run() {
/*  66 */                 entity.func_70097_a(DamageSource.field_76376_m, 1.0F);
/*  67 */                 PotionEffect pe = new PotionEffect(MobEffects.field_76421_d, 20, 0, true, true);
/*  68 */                 ((EntityLivingBase)entity).func_70690_d(pe);
/*     */               } }
/*     */             0); 
/*  71 */       if (!world.field_72995_K && world.field_73012_v.nextInt(100) == 0) {
/*  72 */         world.func_175698_g(pos);
/*     */       }
/*     */     }
/*  75 */     else if (state.func_177230_c() == BlocksTC.effectSap && !(entity instanceof thaumcraft.api.entities.IEldritchMob) && 
/*  76 */       entity instanceof EntityLivingBase && !((EntityLivingBase)entity).func_70644_a(MobEffects.field_82731_v)) {
/*  77 */       ServerEvents.addRunnableServer(world, new Runnable() { public void run() {
/*  78 */               PotionEffect pe0 = new PotionEffect(MobEffects.field_82731_v, 40, 0, true, true);
/*  79 */               ((EntityLivingBase)entity).func_70690_d(pe0);
/*  80 */               PotionEffect pe1 = new PotionEffect(MobEffects.field_76421_d, 40, 1, true, true);
/*  81 */               ((EntityLivingBase)entity).func_70690_d(pe1);
/*  82 */               PotionEffect pe2 = new PotionEffect(MobEffects.field_76438_s, 40, 1, true, true);
/*  83 */               ((EntityLivingBase)entity).func_70690_d(pe2);
/*     */             } }
/*     */           0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/*  91 */     super.func_180650_b(worldIn, pos, state, rand);
/*  92 */     if (!worldIn.field_72995_K && state.func_177230_c() != BlocksTC.effectGlimmer) {
/*  93 */       worldIn.func_175698_g(pos);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_180655_c(IBlockState state, World w, BlockPos pos, Random r) {
/* 101 */     if (state.func_177230_c() != BlocksTC.effectGlimmer) {
/* 102 */       float h = r.nextFloat() * 0.33F;
/* 103 */       if (state.func_177230_c() == BlocksTC.effectShock) {
/* 104 */         FXDispatcher.INSTANCE.spark((pos.func_177958_n() + w.field_73012_v.nextFloat()), (pos.func_177956_o() + 0.1515F + h / 2.0F), (pos.func_177952_p() + w.field_73012_v.nextFloat()), 3.0F + h * 6.0F, 0.65F + w.field_73012_v
/* 105 */             .nextFloat() * 0.1F, 1.0F, 1.0F, 0.8F);
/*     */       } else {
/*     */         
/* 108 */         FXDispatcher.INSTANCE.spark((pos.func_177958_n() + w.field_73012_v.nextFloat()), (pos.func_177956_o() + 0.1515F + h / 2.0F), (pos.func_177952_p() + w.field_73012_v.nextFloat()), 3.0F + h * 6.0F, 0.3F - w.field_73012_v
/* 109 */             .nextFloat() * 0.1F, 0.0F, 0.5F + w.field_73012_v.nextFloat() * 0.2F, 1.0F);
/*     */       } 
/*     */ 
/*     */       
/* 113 */       if (r.nextInt(50) == 0) {
/* 114 */         w.func_184134_a(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), SoundsTC.jacobs, SoundCategory.AMBIENT, 0.25F, 1.0F + (r
/* 115 */             .nextFloat() - r.nextFloat()) * 0.2F, false);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 122 */   public boolean isAir(IBlockState state, IBlockAccess world, BlockPos pos) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   public EnumBlockRenderType func_149645_b(IBlockState state) { return EnumBlockRenderType.INVISIBLE; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   public boolean func_176200_f(IBlockAccess worldIn, BlockPos pos) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) { return ItemStack.field_190927_a; }
/*     */ 
/*     */ 
/*     */   
/* 142 */   public boolean isSideSolid(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing o) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   public boolean func_176205_b(IBlockAccess worldIn, BlockPos pos) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 153 */   public AxisAlignedBB func_180646_a(IBlockState state, IBlockAccess worldIn, BlockPos pos) { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 159 */   public boolean func_176209_a(IBlockState state, boolean hitIfLiquid) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 164 */   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) { return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 169 */   public boolean func_149662_c(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 174 */   public boolean func_149686_d(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 179 */   public Item func_180660_a(IBlockState state, Random rand, int fortune) { return Item.func_150899_d(0); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\misc\BlockEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */