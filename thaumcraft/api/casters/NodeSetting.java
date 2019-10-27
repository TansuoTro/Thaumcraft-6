/*     */ package thaumcraft.api.casters;
/*     */ 
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.translation.I18n;
/*     */ 
/*     */ public class NodeSetting
/*     */ {
/*     */   int value;
/*     */   public String key;
/*     */   String description;
/*     */   INodeSettingType type;
/*     */   String research;
/*     */   
/*     */   public NodeSetting(String key, String description, INodeSettingType setting, String research) {
/*  15 */     this.key = key;
/*  16 */     this.type = setting;
/*  17 */     this.value = setting.getDefault();
/*  18 */     this.description = description;
/*  19 */     this.research = research;
/*     */   }
/*     */ 
/*     */   
/*  23 */   public NodeSetting(String key, String description, INodeSettingType setting) { this(key, description, setting, null); }
/*     */ 
/*     */ 
/*     */   
/*  27 */   public int getValue() { return this.type.getValue(this.value); }
/*     */ 
/*     */   
/*     */   public void setValue(int truevalue) {
/*  31 */     int lv = -1;
/*  32 */     this.value = 0;
/*  33 */     while (getValue() != truevalue && lv != this.value) {
/*  34 */       lv = this.value;
/*  35 */       increment();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   public String getResearch() { return this.research; }
/*     */ 
/*     */ 
/*     */   
/*  48 */   public String getValueText() { return I18n.func_74838_a(this.type.getValueText(this.value)); }
/*     */ 
/*     */   
/*     */   public void increment() {
/*  52 */     this.value++;
/*  53 */     this.value = getType().clamp(this.value);
/*     */   }
/*     */   
/*     */   public void decrement() {
/*  57 */     this.value--;
/*  58 */     this.value = getType().clamp(this.value);
/*     */   }
/*     */ 
/*     */   
/*  62 */   public INodeSettingType getType() { return this.type; }
/*     */ 
/*     */ 
/*     */   
/*  66 */   public String getLocalizedName() { return I18n.func_74838_a(this.description); }
/*     */   
/*     */   public static interface INodeSettingType {
/*     */     int getDefault();
/*     */     
/*     */     int clamp(int param1Int);
/*     */     
/*     */     int getValue(int param1Int);
/*     */     
/*     */     String getValueText(int param1Int);
/*     */   }
/*     */   
/*     */   public static class NodeSettingIntList
/*     */     implements INodeSettingType {
/*     */     int[] values;
/*     */     String[] descriptions;
/*     */     
/*     */     public NodeSettingIntList(int[] values, String[] descriptions) {
/*  84 */       this.values = values;
/*  85 */       this.descriptions = descriptions;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  90 */     public int getDefault() { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  95 */     public int clamp(int old) { return MathHelper.func_76125_a(old, 0, this.values.length - 1); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 100 */     public int getValue(int value) { return this.values[clamp(value)]; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 105 */     public String getValueText(int value) { return this.descriptions[value]; }
/*     */   }
/*     */   
/*     */   public static class NodeSettingIntRange implements INodeSettingType {
/*     */     int min;
/*     */     int max;
/*     */     
/*     */     public NodeSettingIntRange(int min, int max) {
/* 113 */       this.min = min;
/* 114 */       this.max = max;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 119 */     public int getDefault() { return this.min; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 124 */     public int clamp(int old) { return MathHelper.func_76125_a(old, this.min, this.max); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     public int getValue(int value) { return clamp(value); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 134 */     public String getValueText(int value) { return "" + getValue(value); }
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\casters\NodeSetting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */