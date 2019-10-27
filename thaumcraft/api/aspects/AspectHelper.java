/*     */ package thaumcraft.api.aspects;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.internal.CommonInternals;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AspectHelper
/*     */ {
/*  20 */   public static AspectList cullTags(AspectList temp) { return cullTags(temp, 7); }
/*     */ 
/*     */   
/*     */   public static AspectList cullTags(AspectList temp, int cap) {
/*  24 */     AspectList temp2 = new AspectList();
/*  25 */     for (Aspect tag : temp.getAspects()) {
/*  26 */       if (tag != null)
/*  27 */         temp2.add(tag, temp.getAmount(tag)); 
/*     */     } 
/*  29 */     while (temp2 != null && temp2.size() > cap) {
/*  30 */       Aspect lowest = null;
/*  31 */       float low = 32767.0F;
/*  32 */       for (Aspect tag : temp2.getAspects()) {
/*  33 */         if (tag != null) {
/*  34 */           float ta = temp2.getAmount(tag);
/*  35 */           if (tag.isPrimal()) {
/*  36 */             ta *= 0.9F;
/*     */           } else {
/*  38 */             if (!tag.getComponents()[0].isPrimal()) {
/*  39 */               ta *= 1.1F;
/*  40 */               if (!tag.getComponents()[0].getComponents()[0].isPrimal()) {
/*  41 */                 ta *= 1.05F;
/*     */               }
/*  43 */               if (!tag.getComponents()[0].getComponents()[1].isPrimal()) {
/*  44 */                 ta *= 1.05F;
/*     */               }
/*     */             } 
/*  47 */             if (!tag.getComponents()[1].isPrimal()) {
/*  48 */               ta *= 1.1F;
/*  49 */               if (!tag.getComponents()[1].getComponents()[0].isPrimal()) {
/*  50 */                 ta *= 1.05F;
/*     */               }
/*  52 */               if (!tag.getComponents()[1].getComponents()[1].isPrimal()) {
/*  53 */                 ta *= 1.05F;
/*     */               }
/*     */             } 
/*     */           } 
/*     */           
/*  58 */           if (ta < low) {
/*  59 */             low = ta;
/*  60 */             lowest = tag;
/*     */           } 
/*     */         } 
/*  63 */       }  temp2.aspects.remove(lowest);
/*     */     } 
/*  65 */     return temp2;
/*     */   }
/*     */ 
/*     */   
/*  69 */   public static AspectList getObjectAspects(ItemStack is) { return ThaumcraftApi.internalMethods.getObjectAspects(is); }
/*     */ 
/*     */ 
/*     */   
/*  73 */   public static AspectList generateTags(ItemStack is) { return ThaumcraftApi.internalMethods.generateTags(is); }
/*     */ 
/*     */   
/*     */   public static AspectList getEntityAspects(Entity entity) {
/*  77 */     AspectList tags = null;
/*  78 */     String entityString = EntityList.func_75621_b(entity);
/*  79 */     if (entity instanceof EntityPlayer) {
/*  80 */       tags = new AspectList();
/*  81 */       tags.add(Aspect.MAN, 4);
/*  82 */       Random rand = new Random(((EntityPlayer)entity).func_70005_c_().hashCode());
/*  83 */       Aspect[] posa = (Aspect[])Aspect.aspects.values().toArray(new Aspect[0]);
/*  84 */       tags.add(posa[rand.nextInt(posa.length)], 15);
/*  85 */       tags.add(posa[rand.nextInt(posa.length)], 15);
/*  86 */       tags.add(posa[rand.nextInt(posa.length)], 15);
/*     */     } else {
/*     */       
/*  89 */       label28: for (ThaumcraftApi.EntityTags et : CommonInternals.scanEntities) {
/*  90 */         if (!et.entityName.equals(entityString))
/*  91 */           continue;  if (et.nbts == null || et.nbts.length == 0) {
/*  92 */           tags = et.aspects; continue;
/*     */         } 
/*  94 */         NBTTagCompound tc = new NBTTagCompound();
/*  95 */         entity.func_189511_e(tc); ThaumcraftApi.EntityTagsNBT[] arrayOfEntityTagsNBT; int i; byte b;
/*  96 */         for (arrayOfEntityTagsNBT = et.nbts, i = arrayOfEntityTagsNBT.length, b = 0; b < i; ) { ThaumcraftApi.EntityTagsNBT nbt = arrayOfEntityTagsNBT[b];
/*  97 */           if (tc.func_74764_b(nbt.name)) {
/*  98 */             if (!ThaumcraftApiHelper.getNBTDataFromId(tc, tc.func_150299_b(nbt.name), nbt.name).equals(nbt.value))
/*     */               continue label28;  b++;
/*     */           } 
/*     */           continue label28; }
/*     */         
/* 103 */         tags = et.aspects;
/*     */       } 
/*     */     } 
/*     */     
/* 107 */     return tags;
/*     */   }
/*     */   
/*     */   public static Aspect getCombinationResult(Aspect aspect1, Aspect aspect2) {
/* 111 */     Collection<Aspect> aspects = Aspect.aspects.values();
/* 112 */     for (Aspect aspect : aspects) {
/* 113 */       if (aspect.getComponents() != null && ((aspect
/* 114 */         .getComponents()[false] == aspect1 && aspect.getComponents()[true] == aspect2) || (aspect
/* 115 */         .getComponents()[false] == aspect2 && aspect.getComponents()[true] == aspect1)))
/*     */       {
/* 117 */         return aspect;
/*     */       }
/*     */     } 
/* 120 */     return null;
/*     */   }
/*     */   
/*     */   public static Aspect getRandomPrimal(Random rand, Aspect aspect) {
/* 124 */     ArrayList<Aspect> list = new ArrayList<Aspect>();
/* 125 */     if (aspect != null) {
/* 126 */       AspectList temp = new AspectList();
/* 127 */       temp.add(aspect, 1);
/* 128 */       AspectList temp2 = reduceToPrimals(temp);
/* 129 */       for (Aspect a : temp2.getAspects()) {
/* 130 */         for (int b = 0; b < temp2.getAmount(a); b++) {
/* 131 */           list.add(a);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 136 */     return (list.size() > 0) ? (Aspect)list.get(rand.nextInt(list.size())) : null;
/*     */   }
/*     */   
/*     */   public static AspectList reduceToPrimals(AspectList in) {
/* 140 */     AspectList out = new AspectList();
/* 141 */     for (Aspect aspect : in.getAspects()) {
/* 142 */       if (aspect != null) {
/* 143 */         if (aspect.isPrimal()) {
/* 144 */           out.add(aspect, in.getAmount(aspect));
/*     */         } else {
/* 146 */           AspectList temp = new AspectList();
/* 147 */           temp.add(aspect.getComponents()[0], in.getAmount(aspect));
/* 148 */           temp.add(aspect.getComponents()[1], in.getAmount(aspect));
/* 149 */           AspectList temp2 = reduceToPrimals(temp);
/*     */           
/* 151 */           for (Aspect a : temp2.getAspects()) {
/* 152 */             out.add(a, temp2.getAmount(a));
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/* 157 */     return out;
/*     */   }
/*     */   
/*     */   public static AspectList getPrimalAspects(AspectList in) {
/* 161 */     AspectList t = new AspectList();
/* 162 */     t.add(Aspect.AIR, in.getAmount(Aspect.AIR));
/* 163 */     t.add(Aspect.FIRE, in.getAmount(Aspect.FIRE));
/* 164 */     t.add(Aspect.WATER, in.getAmount(Aspect.WATER));
/* 165 */     t.add(Aspect.EARTH, in.getAmount(Aspect.EARTH));
/* 166 */     t.add(Aspect.ORDER, in.getAmount(Aspect.ORDER));
/* 167 */     t.add(Aspect.ENTROPY, in.getAmount(Aspect.ENTROPY));
/* 168 */     return t;
/*     */   }
/*     */   
/*     */   public static AspectList getAuraAspects(AspectList in) {
/* 172 */     AspectList t = new AspectList();
/* 173 */     t.add(Aspect.AIR, in.getAmount(Aspect.AIR));
/* 174 */     t.add(Aspect.FIRE, in.getAmount(Aspect.FIRE));
/* 175 */     t.add(Aspect.WATER, in.getAmount(Aspect.WATER));
/* 176 */     t.add(Aspect.EARTH, in.getAmount(Aspect.EARTH));
/* 177 */     t.add(Aspect.ORDER, in.getAmount(Aspect.ORDER));
/* 178 */     t.add(Aspect.ENTROPY, in.getAmount(Aspect.ENTROPY));
/* 179 */     t.add(Aspect.FLUX, in.getAmount(Aspect.FLUX));
/* 180 */     return t;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\aspects\AspectHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */