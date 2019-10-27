/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.BlockFaceShape;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.energy.CapabilityEnergy;
/*     */ import net.minecraftforge.energy.IEnergyStorage;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.blocks.IBlockEnabled;
/*     */ import thaumcraft.common.blocks.IBlockFacing;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ public class BlockVisGenerator
/*     */   extends BlockTCDevice
/*     */   implements IBlockFacing, IBlockEnabled
/*     */ {
/*     */   public BlockVisGenerator() {
/*  32 */     super(Material.field_151575_d, thaumcraft.common.tiles.devices.TileVisGenerator.class, "vis_generator");
/*  33 */     func_149672_a(SoundType.field_185848_a);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   public boolean func_149662_c(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   public boolean func_149686_d(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   public int func_180651_a(IBlockState state) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/*  61 */     for (EnumFacing face : EnumFacing.field_82609_l) {
/*  62 */       TileEntity tileentity = worldIn.func_175625_s(pos.func_177972_a(face));
/*  63 */       if (tileentity != null && tileentity.hasCapability(CapabilityEnergy.ENERGY, face.func_176734_d())) {
/*     */         
/*  65 */         IEnergyStorage capability = (IEnergyStorage)tileentity.getCapability(CapabilityEnergy.ENERGY, face.func_176734_d());
/*  66 */         if (capability.canReceive()) {
/*  67 */           bs = func_176223_P();
/*  68 */           bs = bs.func_177226_a(IBlockFacing.FACING, face);
/*  69 */           return bs.func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(true));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  74 */     return super.func_180642_a(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_180655_c(IBlockState state, World world, BlockPos pos, Random rand) {
/*  80 */     Block block = state.func_177230_c();
/*  81 */     if (block.hasTileEntity(state)) {
/*     */       
/*  83 */       TileEntity tileentity = world.func_175625_s(pos);
/*  84 */       if (tileentity != null) {
/*     */         
/*  86 */         EnumFacing face = BlockStateUtils.getFacing(state);
/*  87 */         if (tileentity.hasCapability(CapabilityEnergy.ENERGY, face)) {
/*     */           
/*  89 */           IEnergyStorage capability = (IEnergyStorage)tileentity.getCapability(CapabilityEnergy.ENERGY, face);
/*  90 */           if (capability.getEnergyStored() > 0) {
/*  91 */             double x = (face.func_82601_c() == 0) ? (rand.nextGaussian() * 0.1D) : (face.func_82601_c() * 0.1D);
/*  92 */             double y = (face.func_96559_d() == 0) ? (rand.nextGaussian() * 0.1D) : (face.func_96559_d() * 0.1D);
/*  93 */             double z = (face.func_82599_e() == 0) ? (rand.nextGaussian() * 0.1D) : (face.func_82599_e() * 0.1D);
/*  94 */             FXDispatcher.INSTANCE.spark(pos
/*  95 */                 .func_177958_n() + 0.5D + x, pos
/*  96 */                 .func_177956_o() + 0.5D + y, pos
/*  97 */                 .func_177952_p() + 0.5D + z, 0.66F + rand
/*  98 */                 .nextFloat(), 0.65F + rand.nextFloat() * 0.1F, 1.0F, 1.0F, 0.8F);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 107 */   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) { return Utils.rotateBlockAABB(new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.875D, 0.75D), BlockStateUtils.getFacing(func_176201_c(state))); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\devices\BlockVisGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */