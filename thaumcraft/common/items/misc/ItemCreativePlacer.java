/*    */ package thaumcraft.common.items.misc;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.client.util.ITooltipFlag;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumActionResult;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.common.items.ItemTCBase;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemCreativePlacer
/*    */   extends ItemTCBase
/*    */ {
/* 24 */   public ItemCreativePlacer() { super("creative_placer", new String[] { "obelisk", "node", "caster" }); }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
/* 30 */     super.func_77624_a(stack, worldIn, tooltip, flagIn);
/* 31 */     tooltip.add(TextFormatting.DARK_PURPLE + "Creative only");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
/* 39 */     IBlockState bs = world.func_180495_p(pos);
/* 40 */     if (!bs.func_185904_a().func_76220_a()) return EnumActionResult.FAIL; 
/* 41 */     if (world.field_72995_K) return EnumActionResult.PASS;
/*    */     
/* 43 */     pos = pos.func_177972_a(side);
/* 44 */     bs = world.func_180495_p(pos);
/*    */     
/* 46 */     if (!player.func_175151_a(pos, side, player.func_184586_b(hand))) return EnumActionResult.FAIL; 
/* 47 */     if (!bs.func_177230_c().func_176200_f(world, pos)) return EnumActionResult.FAIL; 
/* 48 */     if (player.func_184586_b(hand).func_77952_i() == 0 && !world.func_180495_p(pos.func_177977_b()).func_185904_a().func_76220_a()) return EnumActionResult.FAIL;
/*    */     
/* 50 */     world.func_175698_g(pos);
/*    */     
/* 52 */     player.func_184586_b(hand).func_77952_i();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 65 */     return EnumActionResult.SUCCESS;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 72 */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.EPIC; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\misc\ItemCreativePlacer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */