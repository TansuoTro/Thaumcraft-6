/*     */ package thaumcraft.api.casters;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ 
/*     */ 
/*     */ public class FocusEngine
/*     */ {
/*  13 */   public static HashMap<String, Class<IFocusElement>> elements = new HashMap();
/*  14 */   private static HashMap<String, ResourceLocation> elementIcons = new HashMap();
/*  15 */   private static HashMap<String, Integer> elementColor = new HashMap();
/*     */   
/*     */   public static void registerElement(Class element, ResourceLocation icon, int color) {
/*     */     try {
/*  19 */       IFocusElement fe = (IFocusElement)element.newInstance();
/*  20 */       elements.put(fe.getKey(), element);
/*  21 */       elementIcons.put(fe.getKey(), icon);
/*  22 */       elementColor.put(fe.getKey(), Integer.valueOf(color));
/*  23 */     } catch (Exception exception) {}
/*     */   }
/*     */   
/*     */   public static IFocusElement getElement(String key) {
/*     */     try {
/*  28 */       return (IFocusElement)((Class)elements.get(key)).newInstance();
/*  29 */     } catch (Exception exception) {
/*  30 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*  34 */   public static ResourceLocation getElementIcon(String key) { return (ResourceLocation)elementIcons.get(key); }
/*     */ 
/*     */ 
/*     */   
/*  38 */   public static int getElementColor(String key) { return ((Integer)elementColor.get(key)).intValue(); }
/*     */ 
/*     */   
/*     */   public static boolean doesPackageContainElement(FocusPackage focusPackage, String key) {
/*  42 */     for (IFocusElement node : focusPackage.nodes) {
/*  43 */       if (node.getKey().equals(key)) return true; 
/*     */     } 
/*  45 */     return false;
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
/*     */   
/*     */   public static void castFocusPackage(EntityLivingBase caster, FocusPackage focusPackage, boolean nocopy) {
/*     */     FocusPackage focusPackageCopy;
/*  59 */     if (nocopy) {
/*  60 */       focusPackageCopy = focusPackage;
/*     */     } else {
/*  62 */       focusPackageCopy = focusPackage.copy(caster);
/*     */     } 
/*  64 */     focusPackageCopy.initialize(caster);
/*  65 */     focusPackageCopy.setUniqueID(UUID.randomUUID());
/*  66 */     for (FocusEffect effect : focusPackageCopy.getFocusEffects()) {
/*  67 */       effect.onCast(caster);
/*     */     }
/*     */     
/*  70 */     runFocusPackage(focusPackageCopy, null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   public static void castFocusPackage(EntityLivingBase caster, FocusPackage focusPackage) { castFocusPackage(caster, focusPackage, false); }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void runFocusPackage(FocusPackage focusPackage, Trajectory[] trajectories, RayTraceResult[] targets) {
/*  84 */     Trajectory[] prevTrajectories = trajectories;
/*  85 */     RayTraceResult[] prevTargets = targets;
/*     */     
/*  87 */     synchronized (focusPackage.nodes) {
/*     */       
/*  89 */       if (!(focusPackage.nodes.get(0) instanceof FocusMediumRoot)) {
/*  90 */         focusPackage.nodes.add(0, new FocusMediumRoot(trajectories, targets));
/*     */       }
/*     */       
/*  93 */       for (int idx = 0; idx < focusPackage.nodes.size(); idx++) {
/*     */         
/*  95 */         focusPackage.setExecutionIndex(idx);
/*     */         
/*  97 */         IFocusElement node = (IFocusElement)focusPackage.nodes.get(idx);
/*  98 */         if (idx > 0 && ((FocusNode)node).getParent() == null) {
/*  99 */           IFocusElement nodePrev = (IFocusElement)focusPackage.nodes.get(idx - 1);
/* 100 */           if (node instanceof FocusNode && nodePrev instanceof FocusNode) {
/* 101 */             ((FocusNode)node).setParent((FocusNode)nodePrev);
/*     */           }
/*     */         } 
/*     */         
/* 105 */         if (node instanceof FocusNode && ((FocusNode)node).getPackage() == null) {
/* 106 */           ((FocusNode)node).setPackage(focusPackage);
/*     */         }
/*     */         
/* 109 */         if (node instanceof FocusNode) {
/* 110 */           focusPackage.multiplyPower(((FocusNode)node).getPowerMultiplier());
/*     */         }
/*     */         
/* 113 */         if (node instanceof FocusPackage) {
/* 114 */           runFocusPackage((FocusPackage)node, prevTrajectories, prevTargets);
/*     */           
/*     */           break;
/*     */         } 
/* 118 */         if (node instanceof FocusMedium) {
/* 119 */           FocusMedium medium = (FocusMedium)node;
/* 120 */           if (prevTrajectories != null) {
/* 121 */             for (Trajectory trajectory : prevTrajectories) {
/* 122 */               medium.execute(trajectory);
/*     */             }
/*     */           }
/* 125 */           if (medium.hasIntermediary()) {
/*     */             break;
/*     */           }
/* 128 */         } else if (node instanceof FocusMod) {
/* 129 */           if (node instanceof FocusModSplit) {
/* 130 */             FocusModSplit split = (FocusModSplit)node;
/* 131 */             for (FocusPackage sp : split.getSplitPackages()) {
/* 132 */               split.setPackage(sp);
/* 133 */               sp.multiplyPower(focusPackage.getPower());
/* 134 */               split.execute();
/* 135 */               runFocusPackage(sp, split.supplyTrajectories(), split.supplyTargets());
/*     */             } 
/*     */             break;
/*     */           } 
/* 139 */           ((FocusMod)node).execute();
/*     */ 
/*     */         
/*     */         }
/* 143 */         else if (node instanceof FocusEffect) {
/* 144 */           FocusEffect effect = (FocusEffect)node;
/* 145 */           if (prevTargets != null) {
/* 146 */             int num = 0;
/* 147 */             for (RayTraceResult target : prevTargets) {
/* 148 */               if (target.field_72308_g != null) {
/* 149 */                 String k = target.field_72308_g.func_145782_y() + focusPackage.getUniqueID().toString();
/* 150 */                 if (damageResistList.contains(k) && target.field_72308_g.field_70172_ad > 0) {
/* 151 */                   target.field_72308_g.field_70172_ad = 0;
/*     */                 } else {
/* 153 */                   if (damageResistList.size() > 10) damageResistList.remove(0); 
/* 154 */                   damageResistList.add(k);
/*     */                 } 
/*     */               } 
/* 157 */               Trajectory tra = (prevTrajectories != null) ? ((prevTrajectories.length == prevTargets.length) ? prevTrajectories[num] : prevTrajectories[0]) : null;
/* 158 */               effect.execute(target, tra, focusPackage.getPower(), num);
/* 159 */               num++;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 164 */         if (node instanceof FocusNode) {
/* 165 */           prevTrajectories = ((FocusNode)node).supplyTrajectories();
/* 166 */           prevTargets = ((FocusNode)node).supplyTargets();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 174 */   private static ArrayList<String> damageResistList = new ArrayList();
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\casters\FocusEngine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */