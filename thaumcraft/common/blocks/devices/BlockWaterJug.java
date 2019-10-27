/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.BlockFaceShape;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.init.PotionTypes;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.PotionUtils;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.FluidUtil;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.tiles.devices.TileWaterJug;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockWaterJug
/*     */   extends BlockTCDevice
/*     */ {
/*     */   public BlockWaterJug() {
/*  44 */     super(Material.field_151576_e, TileWaterJug.class, "everfull_urn");
/*  45 */     func_149672_a(SoundType.field_185851_d);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   public boolean func_149662_c(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public boolean func_149686_d(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) { return new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 1.0D, 0.8125D); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  76 */     if (!world.field_72995_K) {
/*  77 */       TileEntity te = world.func_175625_s(pos);
/*  78 */       if (te != null && te instanceof TileWaterJug) {
/*  79 */         TileWaterJug tile = (TileWaterJug)te;
/*  80 */         if (FluidUtil.interactWithFluidHandler(player, hand, tile.tank)) {
/*  81 */           player.field_71069_bz.func_75142_b();
/*     */           
/*  83 */           te.func_70296_d();
/*  84 */           tile.syncTile(false);
/*  85 */           world.func_184133_a(null, pos, SoundEvents.field_187615_H, SoundCategory.BLOCKS, 0.33F, 1.0F + (world.field_73012_v
/*  86 */               .nextFloat() - world.field_73012_v.nextFloat()) * 0.3F);
/*  87 */         } else if (player.func_184586_b(hand).func_77973_b() == Items.field_151069_bo && tile.tank.getFluidAmount() >= 333) {
/*  88 */           ItemStack itemstack = player.func_184586_b(hand);
/*     */           
/*  90 */           ItemStack itemstack3 = PotionUtils.func_185188_a(new ItemStack(Items.field_151068_bn), PotionTypes.field_185230_b);
/*  91 */           if (!player.field_71075_bZ.field_75098_d) itemstack.func_190918_g(1);
/*     */           
/*  93 */           if (itemstack.func_190926_b()) {
/*     */             
/*  95 */             player.func_184611_a(hand, itemstack3);
/*     */           }
/*  97 */           else if (!player.field_71071_by.func_70441_a(itemstack3)) {
/*     */             
/*  99 */             player.func_71019_a(itemstack3, false);
/*     */           }
/* 101 */           else if (player instanceof EntityPlayerMP) {
/*     */             
/* 103 */             ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*     */           } 
/*     */           
/* 106 */           tile.drain(new FluidStack(FluidRegistry.WATER, 333), true);
/*     */           
/* 108 */           world.func_184133_a(null, pos, SoundEvents.field_187615_H, SoundCategory.BLOCKS, 0.33F, 1.0F + (world.field_73012_v
/* 109 */               .nextFloat() - world.field_73012_v.nextFloat()) * 0.3F);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 114 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_180655_c(IBlockState state, World world, BlockPos pos, Random rand) {
/* 120 */     Block block = state.func_177230_c();
/* 121 */     if (block.hasTileEntity(state)) {
/*     */       
/* 123 */       TileEntity te = world.func_175625_s(pos);
/* 124 */       if (te != null && te instanceof TileWaterJug) {
/* 125 */         TileWaterJug tile = (TileWaterJug)te;
/* 126 */         if (tile.tank.getFluidAmount() >= tile.tank.getCapacity())
/* 127 */           FXDispatcher.INSTANCE.jarSplashFx(pos
/* 128 */               .func_177958_n() + 0.5D, (pos
/* 129 */               .func_177956_o() + 1), pos
/* 130 */               .func_177952_p() + 0.5D); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\devices\BlockWaterJug.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */