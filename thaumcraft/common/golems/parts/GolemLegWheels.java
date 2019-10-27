/*    */ package thaumcraft.common.golems.parts;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.util.EnumBlockRenderType;
/*    */ import net.minecraft.util.EnumParticleTypes;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import thaumcraft.api.golems.IGolemAPI;
/*    */ import thaumcraft.api.golems.parts.GolemLeg;
/*    */ 
/*    */ public class GolemLegWheels
/*    */   implements GolemLeg.ILegFunction
/*    */ {
/* 16 */   public static HashMap<Integer, Float> ani = new HashMap();
/*    */ 
/*    */   
/*    */   public void onUpdateTick(IGolemAPI golem) {
/* 20 */     if ((golem.getGolemWorld()).field_72995_K) {
/*    */       
/* 22 */       double dist = golem.getGolemEntity().func_70011_f((golem.getGolemEntity()).field_70169_q, (golem.getGolemEntity()).field_70167_r, (golem.getGolemEntity()).field_70166_s);
/* 23 */       float lastRot = 0.0F;
/*    */       
/* 25 */       if (ani.containsKey(Integer.valueOf(golem.getGolemEntity().func_145782_y()))) {
/* 26 */         lastRot = ((Float)ani.get(Integer.valueOf(golem.getGolemEntity().func_145782_y()))).floatValue();
/*    */       }
/*    */       
/* 29 */       double d0 = (golem.getGolemEntity()).field_70165_t - (golem.getGolemEntity()).field_70169_q;
/* 30 */       double d1 = (golem.getGolemEntity()).field_70163_u - (golem.getGolemEntity()).field_70167_r;
/* 31 */       double d2 = (golem.getGolemEntity()).field_70161_v - (golem.getGolemEntity()).field_70166_s;
/* 32 */       float dirTravel = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
/*    */       
/* 34 */       double dir = (360.0F - (golem.getGolemEntity()).field_70177_z - dirTravel);
/* 35 */       lastRot = (float)(lastRot + dist / 1.571D * dir);
/* 36 */       if (lastRot > 360.0F) lastRot -= 360.0F;
/*    */       
/* 38 */       ani.put(Integer.valueOf(golem.getGolemEntity().func_145782_y()), Float.valueOf(lastRot));
/*    */       
/* 40 */       if ((golem.getGolemEntity()).field_70122_E && !golem.getGolemEntity().func_70090_H() && dist > 0.25D) {
/* 41 */         int i = MathHelper.func_76128_c((golem.getGolemEntity()).field_70165_t);
/* 42 */         int j = MathHelper.func_76128_c((golem.getGolemEntity()).field_70163_u - 0.20000000298023224D);
/* 43 */         int k = MathHelper.func_76128_c((golem.getGolemEntity()).field_70161_v);
/* 44 */         BlockPos blockpos = new BlockPos(i, j, k);
/* 45 */         IBlockState iblockstate = (golem.getGolemEntity()).field_70170_p.func_180495_p(blockpos);
/* 46 */         Block block = iblockstate.func_177230_c();
/*    */         
/* 48 */         if (block.func_149645_b(iblockstate) != EnumBlockRenderType.INVISIBLE)
/*    */         {
/* 50 */           (golem.getGolemEntity()).field_70170_p.func_175688_a(EnumParticleTypes.BLOCK_CRACK, 
/* 51 */               (golem.getGolemEntity()).field_70165_t + ((golem.getGolemWorld()).field_73012_v.nextFloat() - 0.5D) * (golem.getGolemEntity()).field_70130_N, 
/* 52 */               (golem.getGolemEntity().func_174813_aQ()).field_72338_b + 0.1D, 
/* 53 */               (golem.getGolemEntity()).field_70161_v + ((golem.getGolemWorld()).field_73012_v.nextFloat() - 0.5D) * (golem.getGolemEntity()).field_70130_N, 
/* 54 */               -(golem.getGolemEntity()).field_70159_w * 4.0D, 1.5D, -(golem.getGolemEntity()).field_70179_y * 4.0D, new int[] { Block.func_176210_f(iblockstate) });
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\parts\GolemLegWheels.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */