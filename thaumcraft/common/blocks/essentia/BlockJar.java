/*     */ package thaumcraft.common.blocks.essentia;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.BlockFaceShape;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.item.EntityXPOrb;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagInt;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockRenderLayer;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.ILabelable;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.blocks.BlockTCTile;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.tiles.devices.TileJarBrain;
/*     */ import thaumcraft.common.tiles.essentia.TileJarFillable;
/*     */ 
/*     */ 
/*     */ public class BlockJar
/*     */   extends BlockTCTile
/*     */   implements ILabelable
/*     */ {
/*     */   public BlockJar(Class t, String name) {
/*  45 */     super(Material.field_151592_s, t, name);
/*  46 */     func_149711_c(0.3F);
/*  47 */     func_149672_a(SoundsTC.JAR);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  52 */   public SoundType func_185467_w() { return SoundsTC.JAR; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) { return new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.75D, 0.8125D); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*  70 */   public BlockRenderLayer func_180664_k() { return BlockRenderLayer.TRANSLUCENT; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   public boolean func_149662_c(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   public boolean func_149686_d(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) { return func_176203_a(meta); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
/*  93 */     this; spillEssentia = false;
/*  94 */     super.func_180663_b(worldIn, pos, state);
/*  95 */     this; spillEssentia = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180653_a(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
/* 101 */     TileEntity te = worldIn.func_175625_s(pos);
/* 102 */     if (te instanceof TileJarFillable) {
/*     */       
/* 104 */       spawnFilledJar(worldIn, pos, state, (TileJarFillable)te);
/*     */     
/*     */     }
/* 107 */     else if (te instanceof TileJarBrain) {
/*     */       
/* 109 */       spawnBrainJar(worldIn, pos, state, (TileJarBrain)te);
/*     */     }
/*     */     else {
/*     */       
/* 113 */       super.func_180653_a(worldIn, pos, state, chance, fortune);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180657_a(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack) {
/* 120 */     if (te instanceof TileJarFillable) {
/*     */       
/* 122 */       spawnFilledJar(worldIn, pos, state, (TileJarFillable)te);
/*     */     
/*     */     }
/* 125 */     else if (te instanceof TileJarBrain) {
/*     */       
/* 127 */       spawnBrainJar(worldIn, pos, state, (TileJarBrain)te);
/*     */     }
/*     */     else {
/*     */       
/* 131 */       super.func_180657_a(worldIn, player, pos, state, (TileEntity)null, stack);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void spawnFilledJar(World world, BlockPos pos, IBlockState state, TileJarFillable te) {
/* 136 */     ItemStack drop = new ItemStack(this, 1, func_176201_c(state));
/* 137 */     if (te.amount > 0) {
/* 138 */       ((BlockJarItem)drop.func_77973_b()).setAspects(drop, (new AspectList()).add(te.aspect, te.amount));
/*     */     }
/* 140 */     if (te.aspectFilter != null) {
/* 141 */       if (!drop.func_77942_o()) drop.func_77982_d(new NBTTagCompound()); 
/* 142 */       drop.func_77978_p().func_74778_a("AspectFilter", te.aspectFilter.getTag());
/*     */     } 
/* 144 */     if (te.blocked) func_180635_a(world, pos, new ItemStack(ItemsTC.jarBrace)); 
/* 145 */     func_180635_a(world, pos, drop);
/*     */   }
/*     */   
/*     */   private void spawnBrainJar(World world, BlockPos pos, IBlockState state, TileJarBrain te) {
/* 149 */     ItemStack drop = new ItemStack(this, 1, func_176201_c(state));
/* 150 */     if (te.xp > 0) {
/* 151 */       drop.func_77983_a("xp", new NBTTagInt(te.xp));
/*     */     }
/* 153 */     func_180635_a(world, pos, drop);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180633_a(World world, BlockPos pos, IBlockState state, EntityLivingBase ent, ItemStack stack) {
/* 159 */     int l = MathHelper.func_76128_c((ent.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
/* 160 */     TileEntity tile = world.func_175625_s(pos);
/* 161 */     if (tile instanceof TileJarFillable) {
/* 162 */       if (l == 0) ((TileJarFillable)tile).facing = 2; 
/* 163 */       if (l == 1) ((TileJarFillable)tile).facing = 5; 
/* 164 */       if (l == 2) ((TileJarFillable)tile).facing = 3; 
/* 165 */       if (l == 3) ((TileJarFillable)tile).facing = 4;
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 175 */     TileEntity te = world.func_175625_s(pos);
/* 176 */     if (te != null && te instanceof TileJarBrain) {
/* 177 */       ((TileJarBrain)te).eatDelay = 40;
/* 178 */       if (!world.field_72995_K) {
/*     */         
/* 180 */         int var6 = world.field_73012_v.nextInt(Math.min(((TileJarBrain)te).xp + 1, 64));
/* 181 */         if (var6 > 0) {
/* 182 */           ((TileJarBrain)te).xp -= var6;
/* 183 */           int xp = var6;
/* 184 */           while (xp > 0) {
/*     */             
/* 186 */             int var2 = EntityXPOrb.func_70527_a(xp);
/* 187 */             xp -= var2;
/* 188 */             world.func_72838_d(new EntityXPOrb(world, pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, var2));
/*     */           } 
/* 190 */           world.markAndNotifyBlock(pos, world.func_175726_f(pos), state, state, 3);
/* 191 */           te.func_70296_d();
/*     */         } 
/*     */       } else {
/* 194 */         world.func_184134_a(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, SoundsTC.jar, SoundCategory.BLOCKS, 0.2F, 1.0F, false);
/*     */       } 
/*     */     } 
/*     */     
/* 198 */     if (te != null && te instanceof TileJarFillable && !((TileJarFillable)te).blocked && player.func_184586_b(hand).func_77973_b() == ItemsTC.jarBrace) {
/* 199 */       ((TileJarFillable)te).blocked = true;
/* 200 */       player.func_184586_b(hand).func_190918_g(1);
/* 201 */       if (world.field_72995_K) {
/* 202 */         world.func_184134_a(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, SoundsTC.key, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
/*     */       } else {
/* 204 */         te.func_70296_d();
/*     */       }
/*     */     
/*     */     }
/* 208 */     else if (te != null && te instanceof TileJarFillable && player.func_70093_af() && ((TileJarFillable)te).aspectFilter != null && side
/* 209 */       .ordinal() == ((TileJarFillable)te).facing) {
/* 210 */       ((TileJarFillable)te).aspectFilter = null;
/* 211 */       if (world.field_72995_K) {
/* 212 */         world.func_184134_a(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, SoundsTC.page, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
/*     */       } else {
/* 214 */         world.func_72838_d(new EntityItem(world, (pos
/* 215 */               .func_177958_n() + 0.5F + side.func_82601_c() / 3.0F), (pos.func_177956_o() + 0.5F), (pos.func_177952_p() + 0.5F + side.func_82599_e() / 3.0F), new ItemStack(ItemsTC.label)));
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 220 */     else if (te != null && te instanceof TileJarFillable && player.func_70093_af() && player.func_184586_b(hand).func_190926_b()) {
/* 221 */       if (((TileJarFillable)te).aspectFilter == null)
/* 222 */         ((TileJarFillable)te).aspect = null; 
/* 223 */       if (world.field_72995_K) {
/* 224 */         world.func_184134_a(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, SoundsTC.jar, SoundCategory.BLOCKS, 0.4F, 1.0F, false);
/* 225 */         world.func_184134_a(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, SoundEvents.field_187615_H, SoundCategory.BLOCKS, 0.5F, 1.0F + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.3F, false);
/*     */       } else {
/* 227 */         AuraHelper.polluteAura(world, pos, ((TileJarFillable)te).amount, true);
/*     */       } 
/* 229 */       ((TileJarFillable)te).amount = 0;
/* 230 */       te.func_70296_d();
/*     */     } 
/*     */     
/* 233 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean applyLabel(EntityPlayer player, BlockPos pos, EnumFacing side, ItemStack labelstack) {
/* 238 */     TileEntity te = player.field_70170_p.func_175625_s(pos);
/* 239 */     if (te != null && te instanceof TileJarFillable && ((TileJarFillable)te).aspectFilter == null) {
/* 240 */       if (((TileJarFillable)te).amount == 0 && ((IEssentiaContainerItem)labelstack.func_77973_b()).getAspects(labelstack) == null) {
/* 241 */         return false;
/*     */       }
/* 243 */       if (((TileJarFillable)te).amount == 0 && ((IEssentiaContainerItem)labelstack.func_77973_b()).getAspects(labelstack) != null) {
/* 244 */         ((TileJarFillable)te).aspect = ((IEssentiaContainerItem)labelstack.func_77973_b()).getAspects(labelstack).getAspects()[0];
/*     */       }
/* 246 */       func_180633_a(player.field_70170_p, pos, player.field_70170_p.func_180495_p(pos), player, null);
/* 247 */       ((TileJarFillable)te).aspectFilter = ((TileJarFillable)te).aspect;
/* 248 */       player.field_70170_p.markAndNotifyBlock(pos, player.field_70170_p.func_175726_f(pos), player.field_70170_p.func_180495_p(pos), player.field_70170_p.func_180495_p(pos), 3);
/* 249 */       te.func_70296_d();
/* 250 */       player.field_70170_p.func_184148_a(null, pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, SoundsTC.jar, SoundCategory.BLOCKS, 0.4F, 1.0F);
/* 251 */       return true;
/*     */     } 
/* 253 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEnchantPowerBonus(World world, BlockPos pos) {
/* 258 */     TileEntity te = world.func_175625_s(pos);
/* 259 */     if (te != null && te instanceof TileJarBrain) {
/* 260 */       return 5.0F;
/*     */     }
/* 262 */     return super.getEnchantPowerBonus(world, pos);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_180655_c(IBlockState state, World world, BlockPos pos, Random rand) {
/* 269 */     TileEntity tile = world.func_175625_s(pos);
/* 270 */     if (tile != null && tile instanceof TileJarBrain && ((TileJarBrain)tile).xp >= ((TileJarBrain)tile).xpMax) {
/* 271 */       FXDispatcher.INSTANCE.spark((pos.func_177958_n() + 0.5F), (pos.func_177956_o() + 0.8F), (pos.func_177952_p() + 0.5F), 3.0F, 0.2F + rand.nextFloat() * 0.2F, 1.0F, 0.3F + rand.nextFloat() * 0.2F, 0.5F);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 277 */   public boolean func_149740_M(IBlockState state) { return true; }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_180641_l(IBlockState state, World world, BlockPos pos) {
/* 282 */     TileEntity tile = world.func_175625_s(pos);
/* 283 */     if (tile != null && tile instanceof TileJarBrain) {
/* 284 */       float r = ((TileJarBrain)tile).xp / ((TileJarBrain)tile).xpMax;
/* 285 */       return MathHelper.func_76141_d(r * 14.0F) + ((((TileJarBrain)tile).xp > 0) ? 1 : 0);
/*     */     } 
/* 287 */     if (tile != null && tile instanceof TileJarFillable) {
/* 288 */       (TileJarFillable)tile; float r = ((TileJarFillable)tile).amount / 250.0F;
/* 289 */       return MathHelper.func_76141_d(r * 14.0F) + ((((TileJarFillable)tile).amount > 0) ? 1 : 0);
/*     */     } 
/* 291 */     return super.func_180641_l(state, world, pos);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\essentia\BlockJar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */