/*     */ package thaumcraft.common.blocks.basic;
/*     */ 
/*     */ import net.minecraft.block.ITileEntityProvider;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.BlockFaceShape;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumBlockRenderType;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ import thaumcraft.common.tiles.misc.TileBanner;
/*     */ 
/*     */ 
/*     */ public class BlockBannerTC
/*     */   extends BlockTC
/*     */   implements ITileEntityProvider
/*     */ {
/*     */   public final EnumDyeColor dye;
/*     */   
/*     */   public BlockBannerTC(String name, EnumDyeColor dye) {
/*  36 */     super(Material.field_151575_d, name);
/*  37 */     func_149711_c(1.0F);
/*  38 */     func_149672_a(SoundType.field_185848_a);
/*  39 */     this.dye = dye;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  44 */   public EnumBlockRenderType func_149645_b(IBlockState state) { return EnumBlockRenderType.INVISIBLE; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   public MapColor func_180659_g(IBlockState state, IBlockAccess worldIn, BlockPos pos) { return (this.dye == null) ? MapColor.field_151645_D : MapColor.func_193558_a(this.dye); }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) {
/*  60 */     TileEntity tile = source.func_175625_s(pos);
/*  61 */     if (tile != null && tile instanceof TileBanner) {
/*  62 */       if (((TileBanner)tile).getWall()) {
/*  63 */         switch (((TileBanner)tile).getBannerFacing()) { case 0:
/*  64 */             return new AxisAlignedBB(0.0D, -1.0D, 0.0D, 1.0D, 1.0D, 0.25D);
/*  65 */           case 8: return new AxisAlignedBB(0.0D, -1.0D, 0.75D, 1.0D, 1.0D, 1.0D);
/*  66 */           case 12: return new AxisAlignedBB(0.0D, -1.0D, 0.0D, 0.25D, 1.0D, 1.0D);
/*  67 */           case 4: return new AxisAlignedBB(0.75D, -1.0D, 0.0D, 1.0D, 1.0D, 1.0D); }
/*     */       
/*     */       } else {
/*  70 */         return new AxisAlignedBB(0.33000001311302185D, 0.0D, 0.33000001311302185D, 0.6600000262260437D, 2.0D, 0.6600000262260437D);
/*     */       } 
/*     */     }
/*  73 */     return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   public AxisAlignedBB func_180646_a(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public boolean func_149686_d(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   public boolean func_149662_c(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_180639_a(World w, BlockPos pos, IBlockState state, EntityPlayer p, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 100 */     if (!w.field_72995_K && (p.func_70093_af() || p.func_184586_b(hand).func_77973_b() instanceof thaumcraft.common.items.consumables.ItemPhial)) {
/* 101 */       TileBanner te = (TileBanner)w.func_175625_s(pos);
/* 102 */       if (te != null)
/*     */       {
/* 104 */         if (this.dye != null) {
/* 105 */           if (p.func_70093_af()) {
/* 106 */             te.setAspect(null);
/*     */           }
/* 108 */           else if (((IEssentiaContainerItem)p.func_184586_b(hand).func_77973_b()).getAspects(p.func_184586_b(hand)) != null) {
/* 109 */             te.setAspect(((IEssentiaContainerItem)p.func_184586_b(hand).func_77973_b()).getAspects(p.func_184586_b(hand)).getAspects()[0]);
/* 110 */             p.func_184586_b(hand).func_190918_g(1);
/*     */           } 
/*     */           
/* 113 */           w.markAndNotifyBlock(pos, w.func_175726_f(pos), state, state, 3);
/* 114 */           te.func_70296_d();
/* 115 */           te.syncTile(false);
/* 116 */           w.func_184133_a(null, pos, SoundEvents.field_187550_ag, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */         } 
/*     */       }
/*     */     } 
/* 120 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   public TileEntity func_149915_a(World worldIn, int meta) { return new TileBanner(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   public boolean hasTileEntity(IBlockState state) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
/* 139 */     TileEntity te = world.func_175625_s(pos);
/* 140 */     if (te instanceof TileBanner) {
/*     */       
/* 142 */       ItemStack drop = new ItemStack(this);
/* 143 */       if (this.dye != null || ((TileBanner)te).getAspect() != null) {
/* 144 */         drop.func_77982_d(new NBTTagCompound());
/* 145 */         if (((TileBanner)te).getAspect() != null) {
/* 146 */           drop.func_77978_p().func_74778_a("aspect", ((TileBanner)te).getAspect().getTag());
/*     */         }
/*     */       } 
/* 149 */       return drop;
/*     */     } 
/*     */     
/* 152 */     return super.getPickBlock(state, target, world, pos, player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180653_a(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
/* 158 */     TileEntity te = worldIn.func_175625_s(pos);
/*     */     
/* 160 */     if (te instanceof TileBanner) {
/*     */       
/* 162 */       ItemStack drop = new ItemStack(this);
/* 163 */       if (this.dye != null || ((TileBanner)te).getAspect() != null) {
/* 164 */         drop.func_77982_d(new NBTTagCompound());
/* 165 */         if (((TileBanner)te).getAspect() != null) {
/* 166 */           drop.func_77978_p().func_74778_a("aspect", ((TileBanner)te).getAspect().getTag());
/*     */         }
/*     */       } 
/*     */       
/* 170 */       func_180635_a(worldIn, pos, drop);
/*     */     }
/*     */     else {
/*     */       
/* 174 */       super.func_180653_a(worldIn, pos, state, chance, fortune);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180657_a(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack) {
/* 183 */     if (te instanceof TileBanner) {
/*     */       
/* 185 */       ItemStack drop = new ItemStack(this);
/* 186 */       if (this.dye != null || ((TileBanner)te).getAspect() != null) {
/* 187 */         drop.func_77982_d(new NBTTagCompound());
/* 188 */         if (((TileBanner)te).getAspect() != null) {
/* 189 */           drop.func_77978_p().func_74778_a("aspect", ((TileBanner)te).getAspect().getTag());
/*     */         }
/*     */       } 
/*     */       
/* 193 */       func_180635_a(worldIn, pos, drop);
/*     */     }
/*     */     else {
/*     */       
/* 197 */       super.func_180657_a(worldIn, player, pos, state, (TileEntity)null, stack);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\basic\BlockBannerTC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */