/*    */ package thaumcraft.common.tiles.crafting;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import thaumcraft.api.casters.FocusEngine;
/*    */ import thaumcraft.api.casters.FocusNode;
/*    */ import thaumcraft.api.casters.IFocusElement;
/*    */ 
/*    */ 
/*    */ public class FocusElementNode
/*    */ {
/*    */   public int x;
/*    */   public int y;
/* 14 */   public int parent = -1; public int id; public boolean target = false; public boolean trajectory = false;
/* 15 */   public int[] children = new int[0];
/* 16 */   public float complexityMultiplier = 1.0F;
/*    */   
/* 18 */   public FocusNode node = null;
/*    */   
/*    */   public float getPower(HashMap<Integer, FocusElementNode> data) {
/* 21 */     if (this.node == null) return 1.0F; 
/* 22 */     float pow = this.node.getPowerMultiplier();
/* 23 */     FocusElementNode p = (FocusElementNode)data.get(Integer.valueOf(this.parent));
/* 24 */     if (p != null && p.node != null) {
/* 25 */       pow *= p.getPower(data);
/*    */     }
/* 27 */     return pow;
/*    */   }
/*    */   
/*    */   public void deserialize(NBTTagCompound nbt) {
/* 31 */     this.x = nbt.func_74762_e("x");
/* 32 */     this.y = nbt.func_74762_e("y");
/* 33 */     this.id = nbt.func_74762_e("id");
/* 34 */     this.target = nbt.func_74767_n("target");
/* 35 */     this.trajectory = nbt.func_74767_n("trajectory");
/* 36 */     this.parent = nbt.func_74762_e("parent");
/* 37 */     this.children = nbt.func_74759_k("children");
/* 38 */     this.complexityMultiplier = nbt.func_74760_g("complexity");
/*    */     
/* 40 */     IFocusElement fe = FocusEngine.getElement(nbt.func_74779_i("key"));
/* 41 */     if (fe != null) {
/* 42 */       this.node = (FocusNode)fe;
/* 43 */       ((FocusNode)fe).initialize();
/* 44 */       if (((FocusNode)fe).getSettingList() != null) {
/* 45 */         for (String ns : ((FocusNode)fe).getSettingList()) {
/* 46 */           ((FocusNode)fe).getSetting(ns).setValue(nbt.func_74762_e("setting." + ns));
/*    */         }
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public NBTTagCompound serialize() {
/* 53 */     NBTTagCompound nbt = new NBTTagCompound();
/*    */     
/* 55 */     nbt.func_74768_a("x", this.x);
/* 56 */     nbt.func_74768_a("y", this.y);
/* 57 */     nbt.func_74768_a("id", this.id);
/* 58 */     nbt.func_74757_a("target", this.target);
/* 59 */     nbt.func_74757_a("trajectory", this.trajectory);
/* 60 */     nbt.func_74768_a("parent", this.parent);
/* 61 */     nbt.func_74783_a("children", this.children);
/* 62 */     nbt.func_74776_a("complexity", this.complexityMultiplier);
/* 63 */     if (this.node != null) {
/* 64 */       nbt.func_74778_a("key", this.node.getKey());
/* 65 */       if (this.node.getSettingList() != null)
/* 66 */         for (String ns : this.node.getSettingList()) {
/* 67 */           nbt.func_74768_a("setting." + ns, this.node.getSettingValue(ns));
/*    */         } 
/*    */     } 
/* 70 */     return nbt;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\crafting\FocusElementNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */