/*     */ package thaumcraft.common.blocks.world.taint;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.BlockFaceShape;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.BlockRenderLayer;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fluids.BlockFluidFinite;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.ThaumcraftMaterials;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.internal.WeightedRandomLoot;
/*     */ import thaumcraft.api.potions.PotionFluxTaint;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.entities.EntityFallingTaint;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintSwarm;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ 
/*     */ public class BlockTaint
/*     */   extends BlockTC
/*     */   implements ITaintBlock
/*     */ {
/*     */   public BlockTaint(String name) {
/*  48 */     super(ThaumcraftMaterials.MATERIAL_TAINT, name);
/*  49 */     func_149711_c(10.0F);
/*  50 */     func_149752_b(100.0F);
/*  51 */     func_149672_a(SoundsTC.GORE);
/*  52 */     func_149675_a(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  57 */   public SoundType func_185467_w() { return SoundsTC.GORE; }
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
/*  68 */   public MapColor func_180659_g(IBlockState state, IBlockAccess worldIn, BlockPos pos) { return MapColor.field_151678_z; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*  75 */   public BlockRenderLayer func_180664_k() { return BlockRenderLayer.CUTOUT; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void die(World world, BlockPos pos, IBlockState state) {
/*  80 */     if (state.func_177230_c() == BlocksTC.taintRock) {
/*  81 */       world.func_175656_a(pos, BlocksTC.stonePorous.func_176223_P());
/*     */     }
/*  83 */     else if (state.func_177230_c() == BlocksTC.taintSoil) {
/*  84 */       world.func_175656_a(pos, Blocks.field_150346_d.func_176223_P());
/*     */     
/*     */     }
/*  87 */     else if (state.func_177230_c() == BlocksTC.taintCrust) {
/*  88 */       world.func_175656_a(pos, BlocksTC.fluxGoo.func_176223_P());
/*     */     
/*     */     }
/*  91 */     else if (state.func_177230_c() == BlocksTC.taintGeyser) {
/*  92 */       world.func_175656_a(pos, BlocksTC.fluxGoo.func_176223_P());
/*     */     } else {
/*  94 */       world.func_175698_g(pos);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180650_b(World world, BlockPos pos, IBlockState state, Random random) {
/* 102 */     if (!world.field_72995_K) {
/*     */ 
/*     */       
/* 105 */       if (!TaintHelper.isNearTaintSeed(world, pos) && random.nextInt(10) == 0) {
/* 106 */         die(world, pos, state);
/*     */         return;
/*     */       } 
/* 109 */       if (state.func_177230_c() == BlocksTC.taintRock) TaintHelper.spreadFibres(world, pos);
/*     */ 
/*     */       
/* 112 */       if (state.func_177230_c() == BlocksTC.taintCrust) {
/* 113 */         Random r = new Random(pos.func_177986_g());
/* 114 */         if (tryToFall(world, pos, pos)) {
/*     */           return;
/*     */         }
/* 117 */         if (world.func_175623_d(pos.func_177984_a())) {
/* 118 */           boolean doIt = true;
/* 119 */           EnumFacing dir = EnumFacing.field_176754_o[random.nextInt(4)];
/* 120 */           for (int a = 1; a < 4; a++) {
/* 121 */             if (!world.func_175623_d(pos.func_177972_a(dir).func_177979_c(a))) {
/* 122 */               doIt = false;
/*     */               break;
/*     */             } 
/* 125 */             if (world.func_180495_p(pos.func_177979_c(a)).func_177230_c() != this) {
/* 126 */               doIt = false;
/*     */               break;
/*     */             } 
/*     */           } 
/* 130 */           if (doIt && 
/* 131 */             tryToFall(world, pos, pos.func_177972_a(dir))) {
/*     */             return;
/*     */           }
/*     */         }
/*     */       
/* 136 */       } else if (state.func_177230_c() == BlocksTC.taintGeyser) {
/* 137 */         if (world.field_73012_v.nextFloat() < 0.2D && world
/* 138 */           .func_175636_b((pos.func_177958_n() + 0.5F), (pos.func_177956_o() + 0.5F), (pos.func_177952_p() + 0.5F), 32.0D) && 
/* 139 */           EntityUtils.getEntitiesInRange(world, pos, null, EntityTaintSwarm.class, 32.0D).isEmpty()) {
/* 140 */           EntityTaintSwarm entityTaintSwarm = new EntityTaintSwarm(world);
/* 141 */           entityTaintSwarm.func_70012_b((pos.func_177958_n() + 0.5F), (pos.func_177956_o() + 1.25F), (pos.func_177952_p() + 0.5F), world.field_73012_v.nextInt(360), 0.0F);
/* 142 */           world.func_72838_d(entityTaintSwarm);
/*     */         }
/* 144 */         else if (AuraHelper.getFlux(world, pos) < 2.0F) {
/* 145 */           AuraHelper.polluteAura(world, pos, 0.25F, true);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 153 */   public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) { return true; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_176199_a(World world, BlockPos pos, Entity entity) {
/* 158 */     if (!world.field_72995_K && entity instanceof EntityLivingBase && !((EntityLivingBase)entity).func_70662_br() && 
/* 159 */       world.field_73012_v.nextInt(250) == 0) {
/* 160 */       ((EntityLivingBase)entity).func_70690_d(new PotionEffect(PotionFluxTaint.instance, 200, 0, false, true));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_189539_a(IBlockState state, World worldIn, BlockPos pos, int eventID, int eventParam) {
/* 168 */     if (eventID == 1) {
/*     */       
/* 170 */       if (worldIn.field_72995_K) {
/* 171 */         worldIn.func_184133_a(null, pos, SoundEvents.field_187540_ab, SoundCategory.BLOCKS, 0.1F, 0.9F + worldIn.field_73012_v.nextFloat() * 0.2F);
/*     */       }
/* 173 */       return true;
/*     */     } 
/* 175 */     return super.func_189539_a(state, worldIn, pos, eventID, eventParam);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean canFallBelow(World world, BlockPos pos) {
/* 180 */     IBlockState bs = world.func_180495_p(pos);
/* 181 */     Block l = bs.func_177230_c();
/*     */     
/* 183 */     for (int xx = -1; xx <= 1; ) { for (int zz = -1; zz <= 1; ) { for (int yy = -1; yy <= 1; yy++) {
/* 184 */           if (Utils.isWoodLog(world, pos.func_177982_a(xx, yy, zz)))
/* 185 */             return false; 
/*     */         }  zz++; }
/*     */        xx++; }
/* 188 */      if (l.isAir(bs, world, pos))
/*     */     {
/* 190 */       return true;
/*     */     }
/* 192 */     if (l == BlocksTC.fluxGoo && ((Integer)bs.func_177229_b(BlockFluidFinite.LEVEL)).intValue() >= 4)
/*     */     {
/* 194 */       return false;
/*     */     }
/* 196 */     if (l == Blocks.field_150480_ab || l == BlocksTC.taintFibre)
/*     */     {
/* 198 */       return true;
/*     */     }
/* 200 */     if (l.func_176200_f(world, pos))
/*     */     {
/* 202 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 206 */     return (bs.func_185904_a() == Material.field_151586_h) ? true : ((bs.func_185904_a() == Material.field_151587_i));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean tryToFall(World world, BlockPos pos, BlockPos pos2) {
/* 212 */     if (!BlockTaintFibre.isOnlyAdjacentToTaint(world, pos)) return false;
/*     */     
/* 214 */     if (canFallBelow(world, pos2.func_177977_b()) && pos2.func_177956_o() >= 0) {
/*     */       
/* 216 */       byte b0 = 32;
/*     */       
/* 218 */       if (world.func_175707_a(pos2.func_177982_a(-b0, -b0, -b0), pos2.func_177982_a(b0, b0, b0))) {
/*     */         
/* 220 */         if (!world.field_72995_K)
/*     */         {
/*     */           
/* 223 */           EntityFallingTaint entityfalling = new EntityFallingTaint(world, (pos2.func_177958_n() + 0.5F), (pos2.func_177956_o() + 0.5F), (pos2.func_177952_p() + 0.5F), world.func_180495_p(pos), pos);
/* 224 */           world.func_72838_d(entityfalling);
/* 225 */           return true;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 230 */         world.func_175698_g(pos);
/* 231 */         BlockPos p2 = new BlockPos(pos2);
/* 232 */         while (canFallBelow(world, p2.func_177977_b()) && p2.func_177956_o() > 0)
/*     */         {
/* 234 */           p2 = p2.func_177977_b();
/*     */         }
/*     */         
/* 237 */         if (p2.func_177956_o() > 0)
/*     */         {
/* 239 */           world.func_175656_a(p2, BlocksTC.taintCrust.func_176223_P());
/*     */         }
/*     */       } 
/*     */     } 
/* 243 */     return false;
/*     */   }
/*     */   
/* 246 */   static Random r = new Random(System.currentTimeMillis());
/* 247 */   static ArrayList<WeightedRandomLoot> pdrops = null;
/*     */ 
/*     */ 
/*     */   
/*     */   public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
/* 252 */     if (state.func_177230_c() == this && state.func_177230_c() == BlocksTC.taintRock) {
/* 253 */       int rr = r.nextInt(15) + fortune;
/* 254 */       if (rr > 13) {
/* 255 */         List<ItemStack> ret = new ArrayList<ItemStack>();
/* 256 */         ret.add(ConfigItems.FLUX_CRYSTAL.func_77946_l());
/* 257 */         return ret;
/*     */       } 
/*     */     } 
/* 260 */     return super.getDrops(world, pos, state, fortune);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 265 */   protected boolean func_149700_E() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 271 */   public Item func_180660_a(IBlockState state, Random rand, int fortune) { return Item.func_150899_d(0); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\world\taint\BlockTaint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */