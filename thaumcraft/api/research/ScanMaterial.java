/*    */ package thaumcraft.api.research;
/*    */ 
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ 
/*    */ public class ScanMaterial
/*    */   implements IScanThing {
/*    */   String research;
/*    */   Material[] mats;
/*    */   
/*    */   public ScanMaterial(Material mat) {
/* 13 */     this.research = "!" + mat.getClass().getTypeName();
/* 14 */     this.mats = new Material[] { mat };
/*    */   }
/*    */   
/*    */   public ScanMaterial(String research, Material... mats) {
/* 18 */     this.research = research;
/* 19 */     this.mats = mats;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean checkThing(EntityPlayer player, Object obj) {
/* 24 */     if (obj != null && obj instanceof BlockPos)
/* 25 */       for (Material mat : this.mats) {
/* 26 */         if (player.field_70170_p.func_180495_p((BlockPos)obj).func_185904_a() == mat)
/* 27 */           return true; 
/*    */       }  
/* 29 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 34 */   public String getResearchKey(EntityPlayer player, Object object) { return this.research; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\research\ScanMaterial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */