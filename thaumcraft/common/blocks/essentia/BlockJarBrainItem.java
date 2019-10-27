/*    */ package thaumcraft.common.blocks.essentia;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.client.util.ITooltipFlag;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.common.tiles.devices.TileJarBrain;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockJarBrainItem
/*    */   extends ItemBlock
/*    */ {
/* 24 */   public BlockJarBrainItem(Block block) { super(block); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState) {
/* 32 */     boolean b = super.placeBlockAt(stack, player, world, pos, side, hitX, hitY, hitZ, newState);
/*    */     
/* 34 */     if (b && !world.field_72995_K) {
/* 35 */       TileEntity te = world.func_175625_s(pos);
/* 36 */       if (te != null && te instanceof TileJarBrain) {
/* 37 */         TileJarBrain jar = (TileJarBrain)te;
/* 38 */         if (stack.func_77942_o()) {
/* 39 */           jar.xp = stack.func_77978_p().func_74762_e("xp");
/*    */         }
/* 41 */         te.func_70296_d();
/* 42 */         world.markAndNotifyBlock(pos, world.func_175726_f(pos), newState, newState, 3);
/*    */       } 
/*    */     } 
/*    */     
/* 46 */     return b;
/*    */   }
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
/* 52 */     if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("xp")) {
/* 53 */       int tf = stack.func_77978_p().func_74762_e("xp");
/* 54 */       tooltip.add("§a" + tf + " xp");
/*    */     } 
/* 56 */     super.func_77624_a(stack, worldIn, tooltip, flagIn);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\essentia\BlockJarBrainItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */