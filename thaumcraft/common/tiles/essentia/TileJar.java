/*    */ package thaumcraft.common.tiles.essentia;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.util.ITickable;
/*    */ import net.minecraft.util.math.AxisAlignedBB;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.common.tiles.TileThaumcraft;
/*    */ 
/*    */ 
/*    */ public class TileJar
/*    */   extends TileThaumcraft
/*    */   implements ITickable
/*    */ {
/* 15 */   protected static Random rand = new Random();
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public AxisAlignedBB getRenderBoundingBox() {
/* 20 */     return new AxisAlignedBB(
/* 21 */         func_174877_v().func_177958_n(), func_174877_v().func_177956_o(), func_174877_v().func_177952_p(), (
/* 22 */         func_174877_v().func_177958_n() + 1), (func_174877_v().func_177956_o() + 1), (func_174877_v().func_177952_p() + 1));
/*    */   }
/*    */   
/*    */   public void func_73660_a() {}
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\essentia\TileJar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */