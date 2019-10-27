/*    */ package thaumcraft.api.research;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ 
/*    */ public class ScanBlock
/*    */   implements IScanThing
/*    */ {
/*    */   String research;
/*    */   Block[] blocks;
/*    */   
/* 14 */   public ScanBlock(Block block) { this("!" + block.getRegistryName().toString(), new Block[] { block }); }
/*    */ 
/*    */   
/*    */   public ScanBlock(String research, Block... blocks) {
/* 18 */     this.research = research;
/* 19 */     this.blocks = blocks;
/* 20 */     for (Block block : blocks) {
/* 21 */       ScanningManager.addScannableThing(new ScanItem(research, new ItemStack(block)));
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean checkThing(EntityPlayer player, Object obj) {
/* 26 */     if (obj != null && obj instanceof BlockPos)
/* 27 */       for (Block block : this.blocks) {
/* 28 */         if (player.field_70170_p.func_180495_p((BlockPos)obj).func_177230_c() == block)
/* 29 */           return true; 
/*    */       }  
/* 31 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 36 */   public String getResearchKey(EntityPlayer player, Object object) { return this.research; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\research\ScanBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */