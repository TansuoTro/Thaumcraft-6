/*     */ package thaumcraft.common.blocks.basic;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.client.util.ITooltipFlag;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumActionResult;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.common.tiles.misc.TileBanner;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockBannerTCItem
/*     */   extends ItemBlock
/*     */ {
/*  26 */   public BlockBannerTCItem(BlockBannerTC block) { super(block); }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
/*  32 */     if (stack.func_77942_o() && stack.func_77978_p().func_74779_i("aspect") != null && 
/*  33 */       Aspect.getAspect(stack.func_77978_p().func_74779_i("aspect")) != null) {
/*  34 */       tooltip.add(Aspect.getAspect(stack.func_77978_p().func_74779_i("aspect")).getName());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumActionResult func_180614_a(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  43 */     if (side == EnumFacing.DOWN)
/*     */     {
/*  45 */       return EnumActionResult.FAIL;
/*     */     }
/*  47 */     if (!worldIn.func_180495_p(pos).func_185904_a().func_76220_a())
/*     */     {
/*  49 */       return EnumActionResult.FAIL;
/*     */     }
/*     */ 
/*     */     
/*  53 */     pos = pos.func_177972_a(side);
/*     */     
/*  55 */     if (!player.func_175151_a(pos, side, player.func_184586_b(hand)))
/*     */     {
/*  57 */       return EnumActionResult.FAIL;
/*     */     }
/*  59 */     if (!Blocks.field_180393_cK.func_176196_c(worldIn, pos))
/*     */     {
/*  61 */       return EnumActionResult.FAIL;
/*     */     }
/*  63 */     if (worldIn.field_72995_K)
/*     */     {
/*  65 */       return EnumActionResult.FAIL;
/*     */     }
/*     */ 
/*     */     
/*  69 */     worldIn.func_180501_a(pos, this.field_150939_a.func_176223_P(), 3);
/*     */     
/*  71 */     TileBanner tile = (TileBanner)worldIn.func_175625_s(pos);
/*  72 */     if (tile != null) {
/*  73 */       if (side == EnumFacing.UP) {
/*     */         
/*  75 */         int i = MathHelper.func_76128_c(((player.field_70177_z + 180.0F) * 16.0F / 360.0F) + 0.5D) & 0xF;
/*  76 */         tile.setBannerFacing((byte)i);
/*     */       }
/*     */       else {
/*     */         
/*  80 */         tile.setWall(true);
/*     */         
/*  82 */         int i = 0;
/*     */         
/*  84 */         if (side == EnumFacing.NORTH)
/*     */         {
/*  86 */           i = 8;
/*     */         }
/*     */         
/*  89 */         if (side == EnumFacing.WEST)
/*     */         {
/*  91 */           i = 4;
/*     */         }
/*     */         
/*  94 */         if (side == EnumFacing.EAST)
/*     */         {
/*  96 */           i = 12;
/*     */         }
/*     */         
/*  99 */         tile.setBannerFacing((byte)i);
/*     */       } 
/*     */       
/* 102 */       if (player.func_184586_b(hand).func_77942_o() && 
/* 103 */         player.func_184586_b(hand).func_77978_p().func_74779_i("aspect") != null) {
/* 104 */         tile.setAspect(Aspect.getAspect(player.func_184586_b(hand).func_77978_p().func_74779_i("aspect")));
/*     */       }
/*     */ 
/*     */       
/* 108 */       tile.func_70296_d();
/* 109 */       worldIn.markAndNotifyBlock(pos, worldIn.func_175726_f(pos), this.field_150939_a.func_176223_P(), this.field_150939_a.func_176223_P(), 3);
/*     */     } 
/*     */     
/* 112 */     player.func_184586_b(hand).func_190918_g(1);
/*     */     
/* 114 */     return EnumActionResult.SUCCESS;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\basic\BlockBannerTCItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */