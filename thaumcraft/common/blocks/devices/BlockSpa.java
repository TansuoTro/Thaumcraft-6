/*    */ package thaumcraft.common.blocks.devices;
/*    */ 
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.SoundEvents;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ import net.minecraftforge.fluids.FluidUtil;
/*    */ import thaumcraft.Thaumcraft;
/*    */ import thaumcraft.common.blocks.BlockTCDevice;
/*    */ import thaumcraft.common.tiles.devices.TileSpa;
/*    */ 
/*    */ public class BlockSpa
/*    */   extends BlockTCDevice
/*    */ {
/*    */   public BlockSpa() {
/* 24 */     super(Material.field_151576_e, TileSpa.class, "spa");
/* 25 */     func_149672_a(SoundType.field_185851_d);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 32 */     if (world.field_72995_K) return true; 
/* 33 */     TileEntity tileEntity = world.func_175625_s(pos);
/* 34 */     if (tileEntity instanceof TileSpa && !player.func_70093_af()) {
/* 35 */       FluidStack fs = FluidUtil.getFluidContained(player.func_184586_b(hand));
/* 36 */       if (fs != null) {
/* 37 */         TileSpa tile = (TileSpa)tileEntity;
/* 38 */         if (tile.tank.getFluidAmount() < tile.tank.getCapacity() && (tile.tank
/* 39 */           .getFluid() == null || tile.tank.getFluid().isFluidEqual(fs)) && 
/* 40 */           FluidUtil.interactWithFluidHandler(player, hand, tile.tank)) {
/* 41 */           player.field_71069_bz.func_75142_b();
/* 42 */           tile.func_70296_d();
/* 43 */           world.markAndNotifyBlock(pos, world.func_175726_f(pos), state, state, 3);
/* 44 */           world.func_184133_a(null, pos, SoundEvents.field_187615_H, SoundCategory.BLOCKS, 0.33F, 1.0F + (world.field_73012_v
/* 45 */               .nextFloat() - world.field_73012_v.nextFloat()) * 0.3F);
/*    */         } 
/*    */       } else {
/*    */         
/* 49 */         player.openGui(Thaumcraft.instance, 6, world, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*    */       } 
/*    */     } 
/* 52 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\devices\BlockSpa.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */