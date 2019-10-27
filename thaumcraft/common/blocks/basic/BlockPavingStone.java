/*     */ package thaumcraft.common.blocks.basic;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.MobEffects;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ import thaumcraft.common.tiles.misc.TileBarrierStone;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockPavingStone
/*     */   extends BlockTC
/*     */ {
/*     */   public BlockPavingStone(String name) {
/*  30 */     super(Material.field_151576_e, name);
/*  31 */     func_149711_c(2.5F);
/*  32 */     func_149672_a(SoundType.field_185851_d);
/*  33 */     func_149675_a(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  38 */   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) { return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   public boolean hasTileEntity(IBlockState state) { return (state.func_177230_c() == BlocksTC.pavingStoneBarrier); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   public TileEntity createTileEntity(World world, IBlockState state) { return (state.func_177230_c() == BlocksTC.pavingStoneBarrier) ? new TileBarrierStone() : null; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_176199_a(World worldIn, BlockPos pos, Entity e) {
/*  58 */     IBlockState state = worldIn.func_180495_p(pos);
/*  59 */     if (!worldIn.field_72995_K && state.func_177230_c() == BlocksTC.pavingStoneTravel && e instanceof EntityLivingBase) {
/*  60 */       ((EntityLivingBase)e).func_70690_d(new PotionEffect(MobEffects.field_76424_c, 40, 1, false, false));
/*  61 */       ((EntityLivingBase)e).func_70690_d(new PotionEffect(MobEffects.field_76430_j, 40, 0, false, false));
/*     */     } 
/*  63 */     super.func_176199_a(worldIn, pos, e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   public boolean func_149662_c(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   public boolean func_149686_d(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180655_c(IBlockState state, World world, BlockPos pos, Random random) {
/*  81 */     if (state.func_177230_c() == BlocksTC.pavingStoneBarrier)
/*  82 */       if (world.func_175687_A(pos) > 0) {
/*  83 */         for (int a = 0; a < 4; a++) {
/*  84 */           FXDispatcher.INSTANCE.blockRunes(pos.func_177958_n(), (pos.func_177956_o() + 0.7F), pos.func_177952_p(), 0.2F + random
/*  85 */               .nextFloat() * 0.4F, random.nextFloat() * 0.3F, 0.8F + random.nextFloat() * 0.2F, 20, -0.02F);
/*     */         }
/*  87 */       } else if ((world.func_180495_p(pos.func_177981_b(true)) == BlocksTC.barrier.func_176223_P() && world
/*  88 */         .func_180495_p(pos.func_177981_b(1)).func_177230_c().func_176205_b(world, pos.func_177981_b(1))) || (world
/*  89 */         .func_180495_p(pos.func_177981_b(2)) == BlocksTC.barrier.func_176223_P() && world
/*  90 */         .func_180495_p(pos.func_177981_b(2)).func_177230_c().func_176205_b(world, pos.func_177981_b(2)))) {
/*     */         
/*  92 */         for (int a = 0; a < 6; a++) {
/*  93 */           FXDispatcher.INSTANCE.blockRunes(pos.func_177958_n(), (pos.func_177956_o() + 0.7F), pos.func_177952_p(), 0.9F + random
/*  94 */               .nextFloat() * 0.1F, random.nextFloat() * 0.3F, random.nextFloat() * 0.3F, 24, -0.02F);
/*     */         }
/*     */       } else {
/*  97 */         List list = world.func_72839_b((Entity)null, (new AxisAlignedBB(pos
/*  98 */               .func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), (pos.func_177958_n() + 1), (pos.func_177956_o() + 1), (pos.func_177952_p() + 1))).func_72314_b(1.0D, 1.0D, 1.0D));
/*     */         
/* 100 */         if (!list.isEmpty()) {
/*     */           
/* 102 */           Iterator iterator = list.iterator();
/* 103 */           while (iterator.hasNext()) {
/*     */             
/* 105 */             Entity entity = (Entity)iterator.next();
/* 106 */             if (entity instanceof EntityLivingBase && !(entity instanceof EntityPlayer)) {
/*     */               
/* 108 */               FXDispatcher.INSTANCE.blockRunes(pos.func_177958_n(), (pos.func_177956_o() + 0.6F + random.nextFloat() * Math.max(0.8F, entity.func_70047_e())), pos.func_177952_p(), 0.6F + random
/* 109 */                   .nextFloat() * 0.4F, 0.0F, 0.3F + random.nextFloat() * 0.7F, 20, 0.0F);
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }  
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\basic\BlockPavingStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */