/*     */ package thaumcraft.common.blocks.crafting;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.FluidUtil;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.common.blocks.BlockTCTile;
/*     */ import thaumcraft.common.tiles.crafting.TileCrucible;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockCrucible
/*     */   extends BlockTCTile
/*     */ {
/*     */   private int delay;
/*     */   
/*     */   public BlockCrucible() {
/*  39 */     super(Material.field_151573_f, TileCrucible.class, "crucible");
/*     */ 
/*     */ 
/*     */     
/*  43 */     this.delay = 0;
/*     */     func_149672_a(SoundType.field_185852_e);
/*     */   }
/*     */   public void func_180634_a(World world, BlockPos pos, IBlockState state, Entity entity) {
/*  47 */     if (!world.field_72995_K) {
/*  48 */       TileCrucible tile = (TileCrucible)world.func_175625_s(pos);
/*  49 */       if (tile != null && entity instanceof EntityItem && !(entity instanceof thaumcraft.common.entities.EntitySpecialItem) && tile.heat > 150 && tile.tank
/*     */         
/*  51 */         .getFluidAmount() > 0) {
/*     */         
/*  53 */         tile.attemptSmelt((EntityItem)entity);
/*     */       } else {
/*  55 */         this.delay++;
/*  56 */         if (this.delay < 10)
/*  57 */           return;  this.delay = 0;
/*  58 */         if (entity instanceof net.minecraft.entity.EntityLivingBase && tile != null && tile.heat > 150 && tile.tank
/*  59 */           .getFluidAmount() > 0) {
/*     */           
/*  61 */           entity.func_70097_a(DamageSource.field_76372_a, 1.0F);
/*  62 */           world.func_184148_a(null, pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, SoundEvents.field_187659_cY, SoundCategory.BLOCKS, 0.4F, 2.0F + world.field_73012_v
/*  63 */               .nextFloat() * 0.4F);
/*     */         } 
/*     */       } 
/*     */     } 
/*  67 */     super.func_180634_a(world, pos, state, entity);
/*     */   }
/*     */   
/*  70 */   protected static final AxisAlignedBB AABB_LEGS = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D);
/*  71 */   protected static final AxisAlignedBB AABB_WALL_NORTH = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);
/*  72 */   protected static final AxisAlignedBB AABB_WALL_SOUTH = new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);
/*  73 */   protected static final AxisAlignedBB AABB_WALL_EAST = new AxisAlignedBB(0.875D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*  74 */   protected static final AxisAlignedBB AABB_WALL_WEST = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.125D, 1.0D, 1.0D);
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_185477_a(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB AABB, List<AxisAlignedBB> list, Entity p_185477_6_, boolean isActualState) {
/*  79 */     func_185492_a(pos, AABB, list, AABB_LEGS);
/*  80 */     func_185492_a(pos, AABB, list, AABB_WALL_WEST);
/*  81 */     func_185492_a(pos, AABB, list, AABB_WALL_NORTH);
/*  82 */     func_185492_a(pos, AABB, list, AABB_WALL_EAST);
/*  83 */     func_185492_a(pos, AABB, list, AABB_WALL_SOUTH);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  88 */   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) { return field_185505_j; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   public boolean func_149662_c(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   public boolean func_149686_d(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
/* 106 */     TileEntity te = worldIn.func_175625_s(pos);
/* 107 */     if (te != null && te instanceof TileCrucible) {
/* 108 */       ((TileCrucible)te).spillRemnants();
/*     */     }
/* 110 */     super.func_180663_b(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 119 */     if (!world.field_72995_K) {
/* 120 */       FluidStack fs = FluidUtil.getFluidContained(player.func_184586_b(hand));
/* 121 */       FluidRegistry.WATER; if (fs != null && fs.containsFluid(new FluidStack(FluidRegistry.WATER, 1000))) {
/* 122 */         TileEntity te = world.func_175625_s(pos);
/* 123 */         if (te != null && te instanceof TileCrucible) {
/* 124 */           TileCrucible tile = (TileCrucible)te;
/* 125 */           if (tile.tank.getFluidAmount() < tile.tank.getCapacity()) {
/* 126 */             if (FluidUtil.interactWithFluidHandler(player, hand, tile.tank)) {
/* 127 */               player.field_71069_bz.func_75142_b();
/* 128 */               te.func_70296_d();
/* 129 */               world.markAndNotifyBlock(pos, world.func_175726_f(pos), state, state, 3);
/* 130 */               world.func_184148_a(null, pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, SoundEvents.field_187615_H, SoundCategory.BLOCKS, 0.33F, 1.0F + (world.field_73012_v
/* 131 */                   .nextFloat() - world.field_73012_v.nextFloat()) * 0.3F);
/*     */             } 
/* 133 */             return true;
/*     */           }
/*     */         
/*     */         } 
/* 137 */       } else if (!player.func_70093_af() && !(player.func_184586_b(hand).func_77973_b() instanceof thaumcraft.api.casters.ICaster) && side == EnumFacing.UP) {
/* 138 */         TileEntity te = world.func_175625_s(pos);
/* 139 */         if (te != null && te instanceof TileCrucible) {
/* 140 */           TileCrucible tile = (TileCrucible)te;
/* 141 */           ItemStack ti = player.func_184586_b(hand).func_77946_l();
/* 142 */           ti.func_190920_e(1);
/* 143 */           if (tile.heat > 150 && tile.tank.getFluidAmount() > 0 && tile.attemptSmelt(ti, player.func_70005_c_()) == null) {
/* 144 */             player.field_71071_by.func_70298_a(player.field_71071_by.field_70461_c, 1);
/* 145 */             return true;
/*     */           }
/*     */         
/*     */         } 
/* 149 */       } else if (player.func_184586_b(hand).func_190926_b() && player.func_70093_af()) {
/* 150 */         TileEntity te = world.func_175625_s(pos);
/* 151 */         if (te != null && te instanceof TileCrucible) {
/* 152 */           TileCrucible tile = (TileCrucible)te;
/* 153 */           tile.spillRemnants();
/* 154 */           return true;
/*     */         } 
/*     */       } 
/*     */     } else {
/* 158 */       return true;
/*     */     } 
/*     */     
/* 161 */     return super.func_180639_a(world, pos, state, player, hand, side, hitX, hitY, hitZ);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 166 */   public boolean func_149740_M(IBlockState state) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_180641_l(IBlockState state, World world, BlockPos pos) {
/* 172 */     TileEntity te = world.func_175625_s(pos);
/* 173 */     if (te != null && te instanceof TileCrucible) {
/* 174 */       ((TileCrucible)te).getClass(); float r = ((TileCrucible)te).aspects.visSize() / 500.0F;
/* 175 */       return MathHelper.func_76141_d(r * 14.0F) + ((((TileCrucible)te).aspects.visSize() > 0) ? 1 : 0);
/*     */     } 
/* 177 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_180655_c(IBlockState state, World w, BlockPos pos, Random r) {
/* 183 */     if (r.nextInt(10) == 0) {
/* 184 */       TileEntity te = w.func_175625_s(pos);
/* 185 */       if (te != null && te instanceof TileCrucible && 
/* 186 */         ((TileCrucible)te).tank.getFluidAmount() > 0 && ((TileCrucible)te).heat > 150)
/* 187 */         w.func_184134_a(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), SoundEvents.field_187662_cZ, SoundCategory.BLOCKS, 0.1F + r
/* 188 */             .nextFloat() * 0.1F, 1.2F + r.nextFloat() * 0.2F, false); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\crafting\BlockCrucible.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */