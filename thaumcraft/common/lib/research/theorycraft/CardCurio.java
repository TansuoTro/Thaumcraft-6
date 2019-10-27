/*     */ package thaumcraft.common.lib.research.theorycraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.TextComponentTranslation;
/*     */ import thaumcraft.api.research.ResearchCategories;
/*     */ import thaumcraft.api.research.theorycraft.ResearchTableData;
/*     */ import thaumcraft.api.research.theorycraft.TheorycraftCard;
/*     */ import thaumcraft.common.items.curios.ItemCurio;
/*     */ 
/*     */ public class CardCurio
/*     */   extends TheorycraftCard
/*     */ {
/*  18 */   ItemStack curio = ItemStack.field_190927_a;
/*     */ 
/*     */   
/*     */   public NBTTagCompound serialize() {
/*  22 */     NBTTagCompound nbt = super.serialize();
/*  23 */     nbt.func_74782_a("stack", this.curio.serializeNBT());
/*  24 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void deserialize(NBTTagCompound nbt) {
/*  29 */     super.deserialize(nbt);
/*  30 */     this.curio = new ItemStack(nbt.func_74775_l("stack"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  35 */   public int getInspirationCost() { return 1; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  40 */   public String getLocalizedName() { return (new TextComponentTranslation("card.curio.name", new Object[0])).func_150254_d(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   public String getLocalizedText() { return (new TextComponentTranslation("card.curio.text", new Object[0])).func_150254_d(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   public ItemStack[] getRequiredItems() { return new ItemStack[] { this.curio }; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   public boolean[] getRequiredItemsConsumed() { return new boolean[] { true }; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean initialize(EntityPlayer player, ResearchTableData data) {
/*  60 */     Random r = new Random(getSeed());
/*  61 */     ArrayList<ItemStack> curios = new ArrayList<ItemStack>();
/*  62 */     for (ItemStack stack : player.field_71071_by.field_70462_a) {
/*  63 */       if (stack != null && !stack.func_190926_b() && stack.func_77973_b() instanceof ItemCurio) {
/*  64 */         ItemStack c = stack.func_77946_l();
/*  65 */         c.func_190920_e(1);
/*  66 */         curios.add(c);
/*     */       } 
/*     */     } 
/*  69 */     if (!curios.isEmpty()) {
/*  70 */       this.curio = (ItemStack)curios.get(r.nextInt(curios.size()));
/*     */     }
/*  72 */     return !this.curio.func_190926_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean activate(EntityPlayer player, ResearchTableData data) {
/*  77 */     data.addTotal("BASICS", 5);
/*  78 */     String[] s = (String[])ResearchCategories.researchCategories.keySet().toArray(new String[0]);
/*  79 */     data.addTotal(s[player.func_70681_au().nextInt(s.length)], 5);
/*  80 */     String type = ((ItemCurio)getRequiredItems()[0].func_77973_b()).getVariantNames()[getRequiredItems()[0].func_77952_i()];
/*  81 */     switch (type) {
/*     */       case "arcane":
/*  83 */         data.addTotal("AUROMANCY", MathHelper.func_76136_a(player.func_70681_au(), 25, 35));
/*     */         break;
/*     */       case "preserved":
/*  86 */         data.addTotal("ALCHEMY", MathHelper.func_76136_a(player.func_70681_au(), 25, 35));
/*     */         break;
/*     */       case "ancient":
/*  89 */         data.addTotal("GOLEMANCY", MathHelper.func_76136_a(player.func_70681_au(), 25, 35));
/*     */         break;
/*     */       case "eldritch":
/*  92 */         data.addTotal("ELDRITCH", MathHelper.func_76136_a(player.func_70681_au(), 25, 35));
/*     */         break;
/*     */       case "knowledge":
/*  95 */         data.addTotal("INFUSION", MathHelper.func_76136_a(player.func_70681_au(), 25, 35));
/*     */         break;
/*     */       case "twisted":
/*  98 */         data.addTotal("ARTIFICE", MathHelper.func_76136_a(player.func_70681_au(), 25, 35));
/*     */         break;
/*     */       case "rites":
/* 101 */         data.addTotal("ELDRITCH", MathHelper.func_76136_a(player.func_70681_au(), 15, 20));
/* 102 */         data.addTotal("AUROMANCY", MathHelper.func_76136_a(player.func_70681_au(), 10, 15));
/*     */         break;
/*     */       default:
/* 105 */         data.addTotal("BASICS", MathHelper.func_76136_a(player.func_70681_au(), 25, 35));
/*     */         break;
/*     */     } 
/* 108 */     if (player.func_70681_au().nextBoolean()) data.bonusDraws++; 
/* 109 */     if (player.func_70681_au().nextBoolean()) data.bonusDraws++; 
/* 110 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\research\theorycraft\CardCurio.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */