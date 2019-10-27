/*    */ package thaumcraft.api.golems.seals;
/*    */ 
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ 
/*    */ public class SealPos
/*    */ {
/*    */   public BlockPos pos;
/*    */   public EnumFacing face;
/*    */   
/*    */   public SealPos(BlockPos pos, EnumFacing face) {
/* 12 */     this.pos = pos;
/* 13 */     this.face = face;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 19 */     byte b0 = (byte)(this.face.ordinal() + 1);
/* 20 */     i = 31 * b0 + this.pos.func_177958_n();
/* 21 */     i = 31 * i + this.pos.func_177956_o();
/* 22 */     return 31 * i + this.pos.func_177952_p();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object p_equals_1_) {
/* 29 */     if (this == p_equals_1_)
/*    */     {
/* 31 */       return true;
/*    */     }
/* 33 */     if (!(p_equals_1_ instanceof SealPos))
/*    */     {
/* 35 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 39 */     SealPos sp = (SealPos)p_equals_1_;
/* 40 */     return !this.pos.equals(sp.pos) ? false : this.face.equals(sp.face);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\golems\seals\SealPos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */