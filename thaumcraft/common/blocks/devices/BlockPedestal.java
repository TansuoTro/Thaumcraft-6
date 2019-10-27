/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.BlockFaceShape;
/*     */ import net.minecraft.block.state.BlockStateContainer;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.crafting.IInfusionStabiliserExt;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.blocks.BlockTCTile;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.tiles.crafting.TilePedestal;
/*     */ 
/*     */ public class BlockPedestal
/*     */   extends BlockTCTile
/*     */   implements IInfusionStabiliserExt {
/*     */   public static BlockPedestal instance;
/*     */   
/*     */   public BlockPedestal(String name) {
/*  36 */     super(Material.field_151576_e, TilePedestal.class, name);
/*  37 */     func_149672_a(SoundType.field_185851_d);
/*  38 */     instance = this;
/*     */     
/*  40 */     IBlockState bs = this.field_176227_L.func_177621_b();
/*  41 */     bs.func_177226_a(BlockInlay.CHARGE, Integer.valueOf(0));
/*  42 */     func_180632_j(bs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   public boolean func_149662_c(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   public boolean func_149686_d(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  68 */     if (world.field_72995_K) return true;
/*     */     
/*  70 */     TileEntity tile = world.func_175625_s(pos);
/*     */     
/*  72 */     if (tile != null && tile instanceof TilePedestal) {
/*     */       
/*  74 */       TilePedestal ped = (TilePedestal)tile;
/*  75 */       if (ped.func_70301_a(0).func_190926_b() && 
/*  76 */         !player.field_71071_by.func_70448_g().func_190926_b() && player.field_71071_by
/*  77 */         .func_70448_g().func_190916_E() > 0) {
/*  78 */         ItemStack i = player.func_184586_b(hand).func_77946_l();
/*  79 */         i.func_190920_e(1);
/*  80 */         ped.func_70299_a(0, i);
/*  81 */         player.func_184586_b(hand).func_190918_g(1);
/*  82 */         if (player.func_184586_b(hand).func_190916_E() == 0) {
/*  83 */           player.func_184611_a(hand, ItemStack.field_190927_a);
/*     */         }
/*  85 */         player.field_71071_by.func_70296_d();
/*  86 */         world.func_184133_a(null, pos, SoundEvents.field_187638_cR, SoundCategory.BLOCKS, 0.2F, ((world.field_73012_v
/*  87 */             .nextFloat() - world.field_73012_v
/*  88 */             .nextFloat()) * 0.7F + 1.0F) * 1.6F);
/*  89 */         return true;
/*  90 */       }  if (!ped.func_70301_a(0).func_190926_b()) {
/*  91 */         InventoryUtils.dropItemsAtEntity(world, pos, player);
/*  92 */         world.func_184133_a(null, pos, SoundEvents.field_187638_cR, SoundCategory.BLOCKS, 0.2F, ((world.field_73012_v
/*  93 */             .nextFloat() - world.field_73012_v
/*  94 */             .nextFloat()) * 0.7F + 1.0F) * 1.5F);
/*  95 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/*  99 */     return super.func_180639_a(world, pos, state, player, hand, side, hitX, hitY, hitZ);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   public IBlockState func_176203_a(int meta) { return func_176223_P().func_177226_a(BlockInlay.CHARGE, Integer.valueOf(meta)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   public int func_176201_c(IBlockState state) { return ((Integer)state.func_177229_b(BlockInlay.CHARGE)).intValue(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   protected BlockStateContainer func_180661_e() { return new BlockStateContainer(this, new IProperty[] { BlockInlay.CHARGE }); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_180655_c(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
/* 124 */     int charge = ((Integer)stateIn.func_177229_b(BlockInlay.CHARGE)).intValue();
/* 125 */     if (charge > 0) {
/* 126 */       FXDispatcher.INSTANCE.blockRunes2(pos.func_177958_n(), pos.func_177956_o() - 0.375D, pos.func_177952_p(), 1.0F, 0.0F, 0.0F, 10, 0.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_176213_c(World worldIn, BlockPos pos, IBlockState state) {
/* 134 */     if (!worldIn.field_72995_K) {
/*     */       
/* 136 */       BlockInlay.updateSurroundingInlay(worldIn, pos, state);
/*     */       
/* 138 */       for (EnumFacing enumfacing1 : EnumFacing.Plane.HORIZONTAL)
/*     */       {
/* 140 */         BlockInlay.notifyInlayNeighborsOfStateChange(worldIn, pos.func_177972_a(enumfacing1));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
/* 148 */     super.func_180663_b(worldIn, pos, state);
/*     */     
/* 150 */     if (!worldIn.field_72995_K) {
/*     */       
/* 152 */       for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
/*     */       {
/* 154 */         worldIn.func_175685_c(pos.func_177972_a(enumfacing), this, false);
/*     */       }
/*     */       
/* 157 */       BlockInlay.updateSurroundingInlay(worldIn, pos, state);
/*     */       
/* 159 */       for (EnumFacing enumfacing1 : EnumFacing.Plane.HORIZONTAL)
/*     */       {
/* 161 */         BlockInlay.notifyInlayNeighborsOfStateChange(worldIn, pos.func_177972_a(enumfacing1));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_189540_a(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
/* 169 */     if (!worldIn.field_72995_K)
/*     */     {
/* 171 */       BlockInlay.updateSurroundingInlay(worldIn, pos, state);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 177 */   public boolean canStabaliseInfusion(World world, BlockPos pos) { return true; }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getStabilizationAmount(World world, BlockPos pos) {
/* 182 */     Block b = world.func_180495_p(pos).func_177230_c();
/* 183 */     return (b == BlocksTC.pedestalEldritch) ? 0.1F : 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasSymmetryPenalty(World world, BlockPos pos1, BlockPos pos2) {
/* 188 */     TileEntity te1 = world.func_175625_s(pos1);
/* 189 */     TileEntity te2 = world.func_175625_s(pos2);
/* 190 */     if (world.field_72995_K) {
/* 191 */       if (te1 != null && te2 != null && te1 instanceof TilePedestal && te2 instanceof TilePedestal) {
/* 192 */         return (((TilePedestal)te1).getSyncedStackInSlot(0).func_190926_b() != ((TilePedestal)te2).getSyncedStackInSlot(0).func_190926_b());
/*     */       }
/*     */     }
/* 195 */     else if (te1 != null && te2 != null && te1 instanceof TilePedestal && te2 instanceof TilePedestal) {
/* 196 */       return (((TilePedestal)te1).func_70301_a(0).func_190926_b() != ((TilePedestal)te2).func_70301_a(0).func_190926_b());
/*     */     } 
/*     */     
/* 199 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 204 */   public float getSymmetryPenalty(World world, BlockPos pos) { return 0.1F; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\devices\BlockPedestal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */