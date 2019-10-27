/*    */ package thaumcraft.api.research;
/*    */ 
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ 
/*    */ public class ScanBlockState
/*    */   implements IScanThing {
/*    */   String research;
/*    */   IBlockState blockState;
/*    */   
/*    */   public ScanBlockState(IBlockState blockState) {
/* 14 */     this.research = "!" + blockState.toString();
/* 15 */     this.blockState = blockState;
/*    */   }
/*    */   
/*    */   public ScanBlockState(String research, IBlockState blockState) {
/* 19 */     this.research = research;
/* 20 */     this.blockState = blockState;
/*    */   }
/*    */   
/*    */   public ScanBlockState(String research, IBlockState blockState, boolean item) {
/* 24 */     this.research = research;
/* 25 */     this.blockState = blockState;
/* 26 */     if (item) {
/* 27 */       ScanningManager.addScannableThing(new ScanItem(research, new ItemStack(blockState
/* 28 */               .func_177230_c(), 1, blockState.func_177230_c().func_176201_c(blockState))));
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean checkThing(EntityPlayer player, Object obj) {
/* 33 */     if (obj != null && obj instanceof BlockPos && player.field_70170_p.func_180495_p((BlockPos)obj) == this.blockState) {
/* 34 */       return true;
/*    */     }
/* 36 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 41 */   public String getResearchKey(EntityPlayer player, Object object) { return this.research; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\research\ScanBlockState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */