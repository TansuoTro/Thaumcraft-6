/*    */ package thaumcraft.api.casters;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.nbt.NBTTagList;
/*    */ 
/*    */ public abstract class FocusModSplit
/*    */   extends FocusMod
/*    */ {
/* 10 */   private ArrayList<FocusPackage> packages = new ArrayList();
/*    */ 
/*    */   
/* 13 */   public final ArrayList<FocusPackage> getSplitPackages() { return this.packages; }
/*    */ 
/*    */   
/*    */   public void deserialize(NBTTagCompound nbt) {
/* 17 */     NBTTagList nodelist = nbt.func_150295_c("packages", 10);
/* 18 */     this.packages.clear();
/* 19 */     for (int x = 0; x < nodelist.func_74745_c(); x++) {
/* 20 */       NBTTagCompound nodenbt = nodelist.func_150305_b(x);
/* 21 */       FocusPackage fp = new FocusPackage();
/* 22 */       fp.deserialize(nodenbt);
/* 23 */       this.packages.add(fp);
/*    */     } 
/*    */   }
/*    */   
/*    */   public NBTTagCompound serialize() {
/* 28 */     NBTTagCompound nbt = new NBTTagCompound();
/* 29 */     NBTTagList nodelist = new NBTTagList();
/* 30 */     for (FocusPackage node : this.packages) {
/* 31 */       nodelist.func_74742_a(node.serialize());
/*    */     }
/* 33 */     nbt.func_74782_a("packages", nodelist);
/* 34 */     return nbt;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 39 */   public float getPowerMultiplier() { return 0.75F; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\casters\FocusModSplit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */