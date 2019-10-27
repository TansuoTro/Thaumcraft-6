/*     */ package thaumcraft.api.aspects;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.LinkedHashMap;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ 
/*     */ public class AspectList
/*     */   implements Serializable
/*     */ {
/*  12 */   public LinkedHashMap<Aspect, Integer> aspects = new LinkedHashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AspectList(ItemStack stack) {
/*     */     try {
/*  21 */       AspectList temp = AspectHelper.getObjectAspects(stack);
/*  22 */       if (temp != null)
/*  23 */         for (Aspect tag : temp.getAspects()) {
/*  24 */           add(tag, temp.getAmount(tag));
/*     */         } 
/*  26 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */   
/*     */   public AspectList() {}
/*     */   
/*     */   public AspectList copy() {
/*  33 */     AspectList out = new AspectList();
/*  34 */     for (Aspect a : getAspects())
/*  35 */       out.add(a, getAmount(a)); 
/*  36 */     return out;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   public int size() { return this.aspects.size(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int visSize() {
/*  51 */     int q = 0;
/*     */     
/*  53 */     for (Aspect as : this.aspects.keySet()) {
/*  54 */       q += getAmount(as);
/*     */     }
/*     */     
/*  57 */     return q;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   public Aspect[] getAspects() { return (Aspect[])this.aspects.keySet().toArray(new Aspect[0]); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Aspect[] getAspectsSortedByName() {
/*     */     try {
/*  73 */       Aspect[] out = (Aspect[])this.aspects.keySet().toArray(new Aspect[0]);
/*  74 */       boolean change = false;
/*     */       do {
/*  76 */         change = false;
/*  77 */         for (int a = 0; a < out.length - 1; a++) {
/*  78 */           Aspect e1 = out[a];
/*  79 */           Aspect e2 = out[a + 1];
/*  80 */           if (e1 != null && e2 != null && e1.getTag().compareTo(e2.getTag()) > 0) {
/*  81 */             out[a] = e2;
/*  82 */             out[a + 1] = e1;
/*  83 */             change = true;
/*     */             break;
/*     */           } 
/*     */         } 
/*  87 */       } while (change == true);
/*  88 */       return out;
/*  89 */     } catch (Exception e) {
/*  90 */       return getAspects();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Aspect[] getAspectsSortedByAmount() {
/*     */     try {
/*  99 */       Aspect[] out = (Aspect[])this.aspects.keySet().toArray(new Aspect[0]);
/* 100 */       boolean change = false;
/*     */       do {
/* 102 */         change = false;
/* 103 */         for (int a = 0; a < out.length - 1; a++) {
/* 104 */           int e1 = getAmount(out[a]);
/* 105 */           int e2 = getAmount(out[a + 1]);
/* 106 */           if (e1 > 0 && e2 > 0 && e2 > e1) {
/* 107 */             Aspect ea = out[a];
/* 108 */             Aspect eb = out[a + 1];
/* 109 */             out[a] = eb;
/* 110 */             out[a + 1] = ea;
/* 111 */             change = true;
/*     */             break;
/*     */           } 
/*     */         } 
/* 115 */       } while (change == true);
/* 116 */       return out;
/* 117 */     } catch (Exception e) {
/* 118 */       return getAspects();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   public int getAmount(Aspect key) { return (this.aspects.get(key) == null) ? 0 : ((Integer)this.aspects.get(key)).intValue(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean reduce(Aspect key, int amount) {
/* 137 */     if (getAmount(key) >= amount) {
/* 138 */       int am = getAmount(key) - amount;
/* 139 */       this.aspects.put(key, Integer.valueOf(am));
/* 140 */       return true;
/*     */     } 
/* 142 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AspectList remove(Aspect key, int amount) {
/* 153 */     int am = getAmount(key) - amount;
/* 154 */     if (am <= 0) { this.aspects.remove(key); }
/* 155 */     else { this.aspects.put(key, Integer.valueOf(am)); }
/* 156 */      return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AspectList remove(Aspect key) {
/* 166 */     this.aspects.remove(key);
/* 167 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AspectList add(Aspect aspect, int amount) {
/* 178 */     if (this.aspects.containsKey(aspect)) {
/* 179 */       int oldamount = ((Integer)this.aspects.get(aspect)).intValue();
/* 180 */       amount += oldamount;
/*     */     } 
/* 182 */     this.aspects.put(aspect, Integer.valueOf(amount));
/* 183 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AspectList merge(Aspect aspect, int amount) {
/* 195 */     if (this.aspects.containsKey(aspect)) {
/* 196 */       int oldamount = ((Integer)this.aspects.get(aspect)).intValue();
/* 197 */       if (amount < oldamount) amount = oldamount;
/*     */     
/*     */     } 
/* 200 */     this.aspects.put(aspect, Integer.valueOf(amount));
/* 201 */     return this;
/*     */   }
/*     */   
/*     */   public AspectList add(AspectList in) {
/* 205 */     for (Aspect a : in.getAspects())
/* 206 */       add(a, in.getAmount(a)); 
/* 207 */     return this;
/*     */   }
/*     */   
/*     */   public AspectList remove(AspectList in) {
/* 211 */     for (Aspect a : in.getAspects())
/* 212 */       remove(a, in.getAmount(a)); 
/* 213 */     return this;
/*     */   }
/*     */   
/*     */   public AspectList merge(AspectList in) {
/* 217 */     for (Aspect a : in.getAspects())
/* 218 */       merge(a, in.getAmount(a)); 
/* 219 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound nbttagcompound) {
/* 229 */     this.aspects.clear();
/* 230 */     NBTTagList tlist = nbttagcompound.func_150295_c("Aspects", 10);
/* 231 */     for (int j = 0; j < tlist.func_74745_c(); j++) {
/* 232 */       NBTTagCompound rs = tlist.func_150305_b(j);
/* 233 */       if (rs.func_74764_b("key")) {
/* 234 */         add(Aspect.getAspect(rs.func_74779_i("key")), rs
/* 235 */             .func_74762_e("amount"));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound nbttagcompound, String label) {
/* 242 */     this.aspects.clear();
/* 243 */     NBTTagList tlist = nbttagcompound.func_150295_c(label, 10);
/* 244 */     for (int j = 0; j < tlist.func_74745_c(); j++) {
/* 245 */       NBTTagCompound rs = tlist.func_150305_b(j);
/* 246 */       if (rs.func_74764_b("key")) {
/* 247 */         add(Aspect.getAspect(rs.func_74779_i("key")), rs
/* 248 */             .func_74762_e("amount"));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeToNBT(NBTTagCompound nbttagcompound) {
/* 260 */     NBTTagList tlist = new NBTTagList();
/* 261 */     nbttagcompound.func_74782_a("Aspects", tlist);
/* 262 */     for (Aspect aspect : getAspects()) {
/* 263 */       if (aspect != null) {
/* 264 */         NBTTagCompound f = new NBTTagCompound();
/* 265 */         f.func_74778_a("key", aspect.getTag());
/* 266 */         f.func_74768_a("amount", getAmount(aspect));
/* 267 */         tlist.func_74742_a(f);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeToNBT(NBTTagCompound nbttagcompound, String label) {
/* 273 */     NBTTagList tlist = new NBTTagList();
/* 274 */     nbttagcompound.func_74782_a(label, tlist);
/* 275 */     for (Aspect aspect : getAspects()) {
/* 276 */       if (aspect != null) {
/* 277 */         NBTTagCompound f = new NBTTagCompound();
/* 278 */         f.func_74778_a("key", aspect.getTag());
/* 279 */         f.func_74768_a("amount", getAmount(aspect));
/* 280 */         tlist.func_74742_a(f);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\aspects\AspectList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */