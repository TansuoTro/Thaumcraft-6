/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.BlockContainer;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyDirection;
/*     */ import net.minecraft.block.state.BlockFaceShape;
/*     */ import net.minecraft.block.state.BlockStateContainer;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryHelper;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumBlockRenderType;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.Mirror;
/*     */ import net.minecraft.util.Rotation;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftInvHelper;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.tiles.devices.TileHungryChest;
/*     */ 
/*     */ public class BlockHungryChest
/*     */   extends BlockContainer {
/*  39 */   public static final PropertyDirection FACING = PropertyDirection.func_177712_a("facing", EnumFacing.Plane.HORIZONTAL);
/*  40 */   private final Random rand = new Random();
/*     */   
/*     */   public BlockHungryChest() {
/*  43 */     super(Material.field_151575_d);
/*  44 */     func_149663_c("hungry_chest");
/*  45 */     setRegistryName("thaumcraft", "hungry_chest");
/*  46 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(FACING, EnumFacing.NORTH));
/*  47 */     func_149711_c(2.5F);
/*  48 */     func_149672_a(SoundType.field_185848_a);
/*  49 */     func_149647_a(ConfigItems.TABTC);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   public EnumBlockRenderType func_149645_b(IBlockState state) { return EnumBlockRenderType.INVISIBLE; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   public boolean func_149662_c(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   public boolean func_149686_d(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) { return new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public AxisAlignedBB func_180646_a(IBlockState state, IBlockAccess worldIn, BlockPos pos) { return new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) { return func_176223_P().func_177226_a(FACING, placer.func_174811_aO()); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180633_a(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
/* 100 */     EnumFacing enumfacing = EnumFacing.func_176731_b(MathHelper.func_76128_c((placer.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3).func_176734_d();
/* 101 */     state = state.func_177226_a(FACING, enumfacing);
/* 102 */     worldIn.func_180501_a(pos, state, 3);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
/* 108 */     TileEntity tileentity = worldIn.func_175625_s(pos);
/*     */     
/* 110 */     if (tileentity instanceof IInventory) {
/*     */       
/* 112 */       InventoryHelper.func_180175_a(worldIn, pos, (IInventory)tileentity);
/* 113 */       worldIn.func_175666_e(pos, this);
/*     */     } 
/*     */     
/* 116 */     super.func_180663_b(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 123 */     if (world.field_72995_K)
/*     */     {
/* 125 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 129 */     TileEntity tileentity = world.func_175625_s(pos);
/*     */     
/* 131 */     if (tileentity instanceof IInventory)
/*     */     {
/* 133 */       player.func_71007_a((IInventory)tileentity);
/*     */     }
/*     */     
/* 136 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180634_a(World world, BlockPos pos, IBlockState state, Entity entity) {
/* 144 */     Object var10 = (TileHungryChest)world.func_175625_s(pos);
/* 145 */     if (var10 == null) {
/*     */       return;
/*     */     }
/*     */     
/* 149 */     if (world.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 153 */     if (entity instanceof EntityItem && !entity.field_70128_L) {
/* 154 */       ItemStack leftovers = ThaumcraftInvHelper.insertStackAt(world, pos, EnumFacing.UP, ((EntityItem)entity).func_92059_d(), false);
/* 155 */       if (leftovers == null || leftovers.func_190926_b() || leftovers.func_190916_E() != ((EntityItem)entity).func_92059_d().func_190916_E()) {
/* 156 */         entity.func_184185_a(SoundEvents.field_187537_bA, 0.25F, (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.2F + 1.0F);
/*     */       }
/*     */       
/* 159 */       if (leftovers != null && !leftovers.func_190926_b()) {
/* 160 */         ((EntityItem)entity).func_92058_a(leftovers);
/*     */       } else {
/* 162 */         entity.func_70106_y();
/*     */       } 
/* 164 */       ((TileHungryChest)var10).func_70296_d();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 171 */   public boolean func_149740_M(IBlockState state) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_180641_l(IBlockState state, World worldIn, BlockPos pos) {
/* 177 */     Object var10 = worldIn.func_175625_s(pos);
/* 178 */     if (var10 instanceof TileHungryChest) {
/* 179 */       return Container.func_94526_b((IInventory)var10);
/*     */     }
/* 181 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis) {
/* 187 */     IBlockState state = world.func_180495_p(pos);
/* 188 */     for (UnmodifiableIterator unmodifiableIterator = state.func_177228_b().keySet().iterator(); unmodifiableIterator.hasNext(); ) { IProperty<?> prop = (IProperty)unmodifiableIterator.next();
/*     */       
/* 190 */       if (prop.func_177701_a().equals("facing")) {
/*     */         
/* 192 */         world.func_175656_a(pos, state.func_177231_a(prop));
/* 193 */         return true;
/*     */       }  }
/*     */     
/* 196 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState func_176203_a(int meta) {
/* 202 */     EnumFacing enumfacing = EnumFacing.func_82600_a(meta);
/*     */     
/* 204 */     if (enumfacing.func_176740_k() == EnumFacing.Axis.Y)
/*     */     {
/* 206 */       enumfacing = EnumFacing.NORTH;
/*     */     }
/*     */     
/* 209 */     return func_176223_P().func_177226_a(FACING, enumfacing);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 215 */   public int func_176201_c(IBlockState state) { return ((EnumFacing)state.func_177229_b(FACING)).func_176745_a(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 221 */   protected BlockStateContainer func_180661_e() { return new BlockStateContainer(this, new IProperty[] { FACING }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 228 */   public TileEntity func_149915_a(World par1World, int m) { return new TileHungryChest(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 235 */   public IBlockState func_185499_a(IBlockState state, Rotation rot) { return state.func_177226_a(FACING, rot.func_185831_a((EnumFacing)state.func_177229_b(FACING))); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 241 */   public IBlockState func_185471_a(IBlockState state, Mirror mirrorIn) { return state.func_185907_a(mirrorIn.func_185800_a((EnumFacing)state.func_177229_b(FACING))); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\devices\BlockHungryChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */