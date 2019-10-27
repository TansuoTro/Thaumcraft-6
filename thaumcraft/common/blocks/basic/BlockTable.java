/*    */ package thaumcraft.common.blocks.basic;
/*    */ 
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.BlockFaceShape;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*    */ import thaumcraft.api.blocks.BlocksTC;
/*    */ import thaumcraft.common.blocks.BlockTC;
/*    */ import thaumcraft.common.blocks.IBlockFacingHorizontal;
/*    */ import thaumcraft.common.container.InventoryFake;
/*    */ import thaumcraft.common.tiles.crafting.TileResearchTable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockTable
/*    */   extends BlockTC
/*    */ {
/* 27 */   public BlockTable(Material mat, String name, SoundType st) { super(mat, name, st); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 32 */   public boolean isSideSolid(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) { return (side == EnumFacing.UP); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 43 */   public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) { return true; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 49 */   public boolean func_149662_c(IBlockState state) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 55 */   public boolean func_149686_d(IBlockState state) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 62 */     if (world.field_72995_K) return true;
/*    */     
/* 64 */     if (this == BlocksTC.tableWood && player.func_184586_b(hand).func_77973_b() instanceof thaumcraft.api.items.IScribeTools) {
/* 65 */       IBlockState bs = BlocksTC.researchTable.func_176223_P();
/* 66 */       bs = bs.func_177226_a(IBlockFacingHorizontal.FACING, player.func_174811_aO());
/* 67 */       world.func_175656_a(pos, bs);
/* 68 */       TileResearchTable tile = (TileResearchTable)world.func_175625_s(pos);
/* 69 */       tile.func_70299_a(0, player.func_184586_b(hand).func_77946_l());
/* 70 */       player.func_184611_a(hand, ItemStack.field_190927_a);
/* 71 */       player.field_71071_by.func_70296_d();
/* 72 */       tile.func_70296_d();
/* 73 */       world.markAndNotifyBlock(pos, world.func_175726_f(pos), bs, bs, 3);
/* 74 */       FMLCommonHandler.instance().firePlayerCraftingEvent(player, new ItemStack(BlocksTC.researchTable), new InventoryFake(1));
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 79 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\basic\BlockTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */