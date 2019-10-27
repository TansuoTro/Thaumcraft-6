/*     */ package thaumcraft.common.items.casters;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.util.ITooltipFlag;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagInt;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.translation.I18n;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.casters.FocusEffect;
/*     */ import thaumcraft.api.casters.FocusEngine;
/*     */ import thaumcraft.api.casters.FocusModSplit;
/*     */ import thaumcraft.api.casters.FocusNode;
/*     */ import thaumcraft.api.casters.FocusPackage;
/*     */ import thaumcraft.api.casters.IFocusElement;
/*     */ import thaumcraft.api.casters.NodeSetting;
/*     */ import thaumcraft.common.items.ItemTCBase;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemFocus
/*     */   extends ItemTCBase
/*     */ {
/*     */   private int maxComplexity;
/*     */   
/*     */   public ItemFocus(String name, int complexity) {
/*  32 */     super(name, new String[0]);
/*  33 */     this.field_77777_bU = 1;
/*  34 */     func_77656_e(0);
/*  35 */     this.maxComplexity = complexity;
/*     */   }
/*     */   
/*     */   public int getFocusColor(ItemStack focusstack) {
/*  39 */     if (focusstack == null || focusstack.func_190926_b() || focusstack.func_77978_p() == null) return 16777215; 
/*  40 */     int color = 16777215;
/*  41 */     if (!focusstack.func_77978_p().func_74764_b("color")) {
/*  42 */       FocusPackage core = getPackage(focusstack);
/*  43 */       if (core != null) {
/*  44 */         FocusEffect[] fe = core.getFocusEffects();
/*  45 */         int r = 0;
/*  46 */         int g = 0;
/*  47 */         int b = 0;
/*  48 */         for (FocusEffect ef : fe) {
/*  49 */           Color c = new Color(FocusEngine.getElementColor(ef.getKey()));
/*  50 */           r += c.getRed();
/*  51 */           g += c.getGreen();
/*  52 */           b += c.getBlue();
/*     */         } 
/*  54 */         if (fe.length > 0) {
/*  55 */           r /= fe.length;
/*  56 */           g /= fe.length;
/*  57 */           b /= fe.length;
/*     */         } 
/*  59 */         Color c = new Color(r, g, b);
/*  60 */         color = c.getRGB();
/*  61 */         focusstack.func_77983_a("color", new NBTTagInt(color));
/*     */       } 
/*     */     } else {
/*  64 */       color = focusstack.func_77978_p().func_74762_e("color");
/*     */     } 
/*  66 */     return color;
/*     */   }
/*     */   
/*     */   public String getSortingHelper(ItemStack focusstack) {
/*  70 */     if (focusstack == null || focusstack.func_190926_b() || !focusstack.func_77942_o()) return null; 
/*  71 */     int sh = focusstack.func_77978_p().func_74762_e("srt");
/*  72 */     if (sh == 0) {
/*  73 */       sh = getPackage(focusstack).getSortingHelper();
/*  74 */       focusstack.func_77983_a("srt", new NBTTagInt(sh));
/*     */     } 
/*  76 */     return focusstack.func_82833_r() + sh;
/*     */   }
/*     */   
/*     */   public static void setPackage(ItemStack focusstack, FocusPackage core) {
/*  80 */     NBTTagCompound tag = core.serialize();
/*  81 */     focusstack.func_77983_a("package", tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public static FocusPackage getPackage(ItemStack focusstack) {
/*  86 */     if (focusstack == null || focusstack.func_190926_b()) return null; 
/*  87 */     NBTTagCompound tag = focusstack.func_179543_a("package");
/*  88 */     if (tag != null) {
/*  89 */       FocusPackage p = new FocusPackage();
/*  90 */       p.deserialize(tag);
/*  91 */       return p;
/*  92 */     }  return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*  98 */   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) { addFocusInformation(stack, worldIn, tooltip, flagIn); }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addFocusInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
/* 103 */     FocusPackage p = getPackage(stack);
/* 104 */     if (p != null) {
/* 105 */       float al = getVisCost(stack);
/* 106 */       String amount = ItemStack.field_111284_a.format(al);
/* 107 */       tooltip.add(amount + " " + I18n.func_74838_a("item.Focus.cost1"));
/*     */       
/* 109 */       for (IFocusElement fe : p.nodes) {
/* 110 */         if (fe instanceof FocusNode && !(fe instanceof thaumcraft.api.casters.FocusMediumRoot)) {
/* 111 */           buildInfo(tooltip, (FocusNode)fe, 0);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void buildInfo(List list, FocusNode node, int depth) {
/* 118 */     if (node instanceof FocusNode && !(node instanceof thaumcraft.api.casters.FocusMediumRoot)) {
/* 119 */       String t0 = "";
/* 120 */       for (int a = 0; a < depth; ) { t0 = t0 + "  "; a++; }
/* 121 */        t0 = t0 + TextFormatting.DARK_PURPLE + I18n.func_74838_a(node.getUnlocalizedName());
/*     */       
/* 123 */       if (!node.getSettingList().isEmpty()) {
/* 124 */         t0 = t0 + TextFormatting.DARK_AQUA + " [";
/* 125 */         boolean q = false;
/* 126 */         for (String st : node.getSettingList()) {
/* 127 */           NodeSetting ns = node.getSetting(st);
/* 128 */           t0 = t0 + (q ? ", " : "") + ns.getLocalizedName() + " " + ns.getValueText();
/* 129 */           q = true;
/*     */         } 
/* 131 */         t0 = t0 + "]";
/*     */       } 
/*     */       
/* 134 */       list.add(t0);
/*     */       
/* 136 */       if (node instanceof FocusModSplit) {
/* 137 */         FocusModSplit split = (FocusModSplit)node;
/* 138 */         for (FocusPackage p : split.getSplitPackages()) {
/* 139 */           for (IFocusElement fe : p.nodes) {
/* 140 */             if (fe instanceof FocusNode && !(fe instanceof thaumcraft.api.casters.FocusMediumRoot)) {
/* 141 */               buildInfo(list, (FocusNode)fe, depth + 1);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 152 */   public EnumRarity func_77613_e(ItemStack focusstack) { return EnumRarity.RARE; }
/*     */ 
/*     */   
/*     */   public float getVisCost(ItemStack focusstack) {
/* 156 */     FocusPackage p = getPackage(focusstack);
/* 157 */     return (p == null) ? 0.0F : (p.getComplexity() / 5.0F);
/*     */   }
/*     */   
/*     */   public int getActivationTime(ItemStack focusstack) {
/* 161 */     FocusPackage p = getPackage(focusstack);
/* 162 */     return (p == null) ? 0 : Math.max(5, p.getComplexity() / 5 * p.getComplexity() / 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 170 */   public int getMaxComplexity() { return this.maxComplexity; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\casters\ItemFocus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */