/*    */ package thaumcraft.common.blocks.devices;
/*    */ 
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.BlockFaceShape;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.SoundEvents;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.blocks.BlockTCDevice;
/*    */ import thaumcraft.common.lib.utils.InventoryUtils;
/*    */ import thaumcraft.common.tiles.devices.TileRechargePedestal;
/*    */ 
/*    */ public class BlockRechargePedestal
/*    */   extends BlockTCDevice
/*    */ {
/*    */   public BlockRechargePedestal() {
/* 25 */     super(Material.field_151576_e, TileRechargePedestal.class, "recharge_pedestal");
/* 26 */     func_149672_a(SoundType.field_185851_d);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 32 */   public boolean func_149662_c(IBlockState state) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   public boolean func_149686_d(IBlockState state) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 44 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 52 */     if (world.field_72995_K) return true;
/*    */     
/* 54 */     TileEntity tile = world.func_175625_s(pos);
/*    */     
/* 56 */     if (tile != null && tile instanceof TileRechargePedestal) {
/*    */       
/* 58 */       TileRechargePedestal ped = (TileRechargePedestal)tile;
/* 59 */       if (ped.func_70301_a(0).func_190926_b() && player.field_71071_by.func_70448_g().func_77973_b() instanceof thaumcraft.api.items.IRechargable) {
/* 60 */         ItemStack i = player.func_184586_b(hand).func_77946_l();
/* 61 */         i.func_190920_e(1);
/* 62 */         ped.func_70299_a(0, i);
/* 63 */         player.func_184586_b(hand).func_190918_g(1);
/* 64 */         if (player.func_184586_b(hand).func_190916_E() == 0) {
/* 65 */           player.func_184611_a(hand, ItemStack.field_190927_a);
/*    */         }
/* 67 */         player.field_71071_by.func_70296_d();
/* 68 */         world.func_184133_a(null, pos, SoundEvents.field_187638_cR, SoundCategory.BLOCKS, 0.2F, ((world.field_73012_v
/* 69 */             .nextFloat() - world.field_73012_v
/* 70 */             .nextFloat()) * 0.7F + 1.0F) * 1.6F);
/* 71 */         return true;
/* 72 */       }  if (!ped.func_70301_a(0).func_190926_b()) {
/* 73 */         InventoryUtils.dropItemsAtEntity(world, pos, player);
/* 74 */         world.func_184133_a(null, pos, SoundEvents.field_187638_cR, SoundCategory.BLOCKS, 0.2F, ((world.field_73012_v
/* 75 */             .nextFloat() - world.field_73012_v
/* 76 */             .nextFloat()) * 0.7F + 1.0F) * 1.5F);
/* 77 */         return true;
/*    */       } 
/*    */     } 
/*    */     
/* 81 */     return super.func_180639_a(world, pos, state, player, hand, side, hitX, hitY, hitZ);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\devices\BlockRechargePedestal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */