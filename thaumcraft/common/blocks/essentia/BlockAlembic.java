/*     */ package thaumcraft.common.blocks.essentia;
/*     */ 
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.BlockFaceShape;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.ILabelable;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.blocks.BlockTCTile;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.tiles.essentia.TileAlembic;
/*     */ 
/*     */ public class BlockAlembic
/*     */   extends BlockTCTile
/*     */   implements ILabelable
/*     */ {
/*     */   public BlockAlembic() {
/*  35 */     super(Material.field_151575_d, TileAlembic.class, "alembic");
/*  36 */     func_149672_a(SoundType.field_185848_a);
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity func_149915_a(World world, int metadata) {
/*  41 */     if (metadata == 0) return new TileAlembic(); 
/*  42 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   public boolean func_149662_c(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   public boolean func_149686_d(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) { return func_176203_a(meta); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  73 */     TileEntity te = world.func_175625_s(pos);
/*  74 */     if (te != null && te instanceof TileAlembic && player.func_70093_af() && ((TileAlembic)te).aspectFilter != null && side
/*  75 */       .ordinal() == ((TileAlembic)te).facing) {
/*  76 */       ((TileAlembic)te).aspectFilter = null;
/*  77 */       ((TileAlembic)te).facing = EnumFacing.DOWN.ordinal();
/*  78 */       te.func_70296_d();
/*  79 */       world.markAndNotifyBlock(pos, world.func_175726_f(pos), state, state, 3);
/*  80 */       if (world.field_72995_K) {
/*  81 */         world.func_184134_a(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, SoundsTC.page, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
/*     */       } else {
/*  83 */         world.func_72838_d(new EntityItem(world, (pos
/*  84 */               .func_177958_n() + 0.5F + side.func_82601_c() / 3.0F), (pos.func_177956_o() + 0.5F), (pos.func_177952_p() + 0.5F + side.func_82599_e() / 3.0F), new ItemStack(ItemsTC.label)));
/*     */       } 
/*     */       
/*  87 */       return true;
/*     */     } 
/*     */     
/*  90 */     if (te != null && te instanceof TileAlembic && player.func_70093_af() && player.func_184614_ca().func_190926_b() && (((TileAlembic)te).aspectFilter == null || side
/*  91 */       .ordinal() != ((TileAlembic)te).facing)) {
/*  92 */       ((TileAlembic)te).aspect = null;
/*  93 */       if (world.field_72995_K) {
/*  94 */         world.func_184134_a(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, SoundsTC.jar, SoundCategory.BLOCKS, 0.4F, 1.0F, false);
/*  95 */         world.func_184134_a(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, SoundEvents.field_187615_H, SoundCategory.BLOCKS, 0.5F, 1.0F + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.3F, false);
/*     */       } else {
/*  97 */         AuraHelper.polluteAura(world, pos, ((TileAlembic)te).amount, true);
/*     */       } 
/*  99 */       ((TileAlembic)te).amount = 0;
/* 100 */       te.func_70296_d();
/* 101 */       world.markAndNotifyBlock(pos, world.func_175726_f(pos), state, state, 3);
/*     */     } 
/*     */     
/* 104 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 109 */   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) { return new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 1.0D, 0.875D); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   public boolean func_149740_M(IBlockState state) { return true; }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_180641_l(IBlockState state, World world, BlockPos pos) {
/* 119 */     TileEntity tile = world.func_175625_s(pos);
/* 120 */     if (tile != null && tile instanceof TileAlembic) {
/* 121 */       float r = ((TileAlembic)tile).amount / ((TileAlembic)tile).maxAmount;
/* 122 */       return MathHelper.func_76141_d(r * 14.0F) + ((((TileAlembic)tile).amount > 0) ? 1 : 0);
/*     */     } 
/* 124 */     return super.func_180641_l(state, world, pos);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean applyLabel(EntityPlayer player, BlockPos pos, EnumFacing side, ItemStack labelstack) {
/* 129 */     TileEntity te = player.field_70170_p.func_175625_s(pos);
/* 130 */     if (te != null && te instanceof TileAlembic && side.ordinal() > 1 && ((TileAlembic)te).aspectFilter == null) {
/* 131 */       Aspect la = null;
/* 132 */       if (((IEssentiaContainerItem)labelstack.func_77973_b()).getAspects(labelstack) != null) {
/* 133 */         la = ((IEssentiaContainerItem)labelstack.func_77973_b()).getAspects(labelstack).getAspects()[0];
/*     */       }
/* 135 */       if (((TileAlembic)te).amount == 0 && la == null) {
/* 136 */         return false;
/*     */       }
/* 138 */       Aspect aspect = null;
/* 139 */       if (((TileAlembic)te).amount == 0 && la != null) {
/* 140 */         aspect = la;
/*     */       }
/* 142 */       if (((TileAlembic)te).amount > 0) {
/* 143 */         aspect = ((TileAlembic)te).aspect;
/*     */       }
/*     */       
/* 146 */       if (aspect == null) return false; 
/* 147 */       IBlockState state = player.field_70170_p.func_180495_p(pos);
/* 148 */       func_180633_a(player.field_70170_p, pos, state, player, null);
/* 149 */       ((TileAlembic)te).aspectFilter = aspect;
/* 150 */       ((TileAlembic)te).facing = side.ordinal();
/* 151 */       te.func_70296_d();
/* 152 */       player.field_70170_p.markAndNotifyBlock(pos, player.field_70170_p.func_175726_f(pos), state, state, 3);
/* 153 */       player.field_70170_p.func_184148_a(null, pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, SoundsTC.page, SoundCategory.BLOCKS, 1.0F, 1.0F);
/* 154 */       return true;
/*     */     } 
/* 156 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\essentia\BlockAlembic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */