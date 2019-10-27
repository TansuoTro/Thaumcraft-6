/*     */ package thaumcraft.api.casters;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import thaumcraft.api.aspects.Aspect;
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
/*     */ 
/*     */ public abstract class FocusNode
/*     */   implements IFocusElement
/*     */ {
/*     */   FocusPackage pack;
/*     */   private FocusNode parent;
/*     */   final HashMap<String, NodeSetting> settings;
/*     */   
/*     */   public String getUnlocalizedName() { return getKey() + ".name"; }
/*     */   
/*     */   public String getUnlocalizedText() { return getKey() + ".text"; }
/*     */   
/*     */   public abstract int getComplexity();
/*     */   
/*     */   public abstract Aspect getAspect();
/*     */   
/*     */   public abstract EnumSupplyType[] mustBeSupplied();
/*     */   
/*     */   public abstract EnumSupplyType[] willSupply();
/*     */   
/*     */   public FocusNode() {
/*  81 */     this.settings = new HashMap(); initialize();
/*     */   } public boolean canSupply(EnumSupplyType type) { if (willSupply() != null)
/*     */       for (EnumSupplyType st : willSupply()) { if (st == type)
/*  84 */           return true;  }   return false; } public final Set<String> getSettingList() { return this.settings.keySet(); } public enum EnumSupplyType {
/*     */     TARGET, TRAJECTORY; }
/*     */   public RayTraceResult[] supplyTargets() { return null; }
/*     */   public Trajectory[] supplyTrajectories() { return null; }
/*  88 */   public final NodeSetting getSetting(String key) { return (NodeSetting)this.settings.get(key); }
/*     */   public final void setPackage(FocusPackage pack) { this.pack = pack; }
/*     */   public final FocusPackage getPackage() { return this.pack; }
/*     */   
/*  92 */   public final int getSettingValue(String key) { return this.settings.containsKey(key) ? ((NodeSetting)this.settings.get(key)).getValue() : 0; }
/*     */ 
/*     */ 
/*     */   
/*  96 */   public NodeSetting[] createSettings() { return null; }
/*     */   public final FocusPackage getRemainingPackage() { FocusPackage p = getPackage(); List<IFocusElement> l = p.nodes.subList(p.index + 1, p.nodes.size()); List<IFocusElement> l2 = Collections.synchronizedList(new ArrayList()); for (IFocusElement fe : l)
/*     */       l2.add(fe);  FocusPackage p2 = new FocusPackage(); p2.setUniqueID(p.getUniqueID()); p2.world = p.world; p2.multiplyPower(p.getPower()); p2.nodes = l2;
/*     */     p2.setCasterUUID(p.getCasterUUID());
/* 100 */     return l2.isEmpty() ? null : p2; } public final FocusNode getParent() { return this.parent; } public final void initialize() { NodeSetting[] set = createSettings();
/* 101 */     if (set != null) {
/* 102 */       for (NodeSetting setting : set) {
/* 103 */         this.settings.put(setting.key, setting);
/*     */       }
/*     */     } }
/*     */ 
/*     */ 
/*     */   
/* 109 */   public void setParent(FocusNode parent) { this.parent = parent; }
/*     */ 
/*     */ 
/*     */   
/* 113 */   public float getPowerMultiplier() { return 1.0F; }
/*     */ 
/*     */ 
/*     */   
/* 117 */   public boolean isExclusive() { return false; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\casters\FocusNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */