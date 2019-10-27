/*    */ package thaumcraft.common.blocks;
/*    */ 
/*    */ import com.google.common.base.Predicate;
/*    */ import net.minecraft.block.properties.PropertyDirection;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ 
/*    */ public interface IBlockFacingHorizontal
/*    */ {
/*  9 */   public static final PropertyDirection FACING = PropertyDirection.func_177712_a("facing", new Predicate()
/*    */       {
/*    */         public boolean apply(EnumFacing facing)
/*    */         {
/* 13 */           return (facing != EnumFacing.UP && facing != EnumFacing.DOWN);
/*    */         }
/*    */ 
/*    */         
/* 17 */         public boolean apply(Object p_apply_1_) { return apply((EnumFacing)p_apply_1_); }
/*    */       });
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\IBlockFacingHorizontal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */