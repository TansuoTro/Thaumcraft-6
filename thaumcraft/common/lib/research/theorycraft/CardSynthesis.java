/*    */ package thaumcraft.common.lib.research.theorycraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.text.TextComponentTranslation;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import thaumcraft.api.ThaumcraftApiHelper;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.research.theorycraft.ResearchTableData;
/*    */ import thaumcraft.api.research.theorycraft.TheorycraftCard;
/*    */ 
/*    */ 
/*    */ public class CardSynthesis
/*    */   extends TheorycraftCard
/*    */ {
/*    */   Aspect aspect1;
/*    */   Aspect aspect2;
/*    */   Aspect aspect3;
/*    */   
/*    */   public NBTTagCompound serialize() {
/* 24 */     NBTTagCompound nbt = super.serialize();
/* 25 */     nbt.func_74778_a("aspect1", this.aspect1.getTag());
/* 26 */     nbt.func_74778_a("aspect2", this.aspect2.getTag());
/* 27 */     nbt.func_74778_a("aspect3", this.aspect3.getTag());
/* 28 */     return nbt;
/*    */   }
/*    */ 
/*    */   
/*    */   public void deserialize(NBTTagCompound nbt) {
/* 33 */     super.deserialize(nbt);
/* 34 */     this.aspect1 = Aspect.getAspect(nbt.func_74779_i("aspect1"));
/* 35 */     this.aspect2 = Aspect.getAspect(nbt.func_74779_i("aspect2"));
/* 36 */     this.aspect3 = Aspect.getAspect(nbt.func_74779_i("aspect3"));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean initialize(EntityPlayer player, ResearchTableData data) {
/* 41 */     Random r = new Random(getSeed());
/* 42 */     int num = MathHelper.func_76136_a(r, 0, Aspect.getCompoundAspects().size() - 1);
/* 43 */     this.aspect3 = (Aspect)Aspect.getCompoundAspects().get(num);
/* 44 */     this.aspect1 = this.aspect3.getComponents()[0];
/* 45 */     this.aspect2 = this.aspect3.getComponents()[1];
/* 46 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 51 */   public int getInspirationCost() { return 1; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 56 */   public String getResearchCategory() { return "ALCHEMY"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 61 */   public String getLocalizedName() { return (new TextComponentTranslation("card.synthesis.name", new Object[0])).func_150260_c(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 66 */   public String getLocalizedText() { return (new TextComponentTranslation("card.synthesis.text", new Object[] { TextFormatting.BOLD + this.aspect1
/*    */           
/* 68 */           .getName() + TextFormatting.RESET, TextFormatting.BOLD + this.aspect2
/* 69 */           .getName() + TextFormatting.RESET
/* 70 */         })).func_150260_c(); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack[] getRequiredItems() {
/* 75 */     return new ItemStack[] {
/* 76 */         ThaumcraftApiHelper.makeCrystal(this.aspect1), 
/* 77 */         ThaumcraftApiHelper.makeCrystal(this.aspect2)
/*    */       };
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 83 */   public boolean[] getRequiredItemsConsumed() { return new boolean[] { true, true }; }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean activate(EntityPlayer player, ResearchTableData data) {
/* 88 */     ItemStack res = ThaumcraftApiHelper.makeCrystal(this.aspect3);
/* 89 */     data.addTotal(getResearchCategory(), 40);
/* 90 */     if (player.func_70681_au().nextFloat() < 0.33D) data.addInspiration(1); 
/* 91 */     if (!player.field_71071_by.func_70441_a(res))
/* 92 */       player.func_71019_a(res, true); 
/* 93 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\research\theorycraft\CardSynthesis.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */