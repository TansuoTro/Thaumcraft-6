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
/*    */ public class CardReactions
/*    */   extends TheorycraftCard
/*    */ {
/*    */   Aspect aspect1;
/*    */   Aspect aspect2;
/*    */   
/*    */   public NBTTagCompound serialize() {
/* 23 */     NBTTagCompound nbt = super.serialize();
/* 24 */     nbt.func_74778_a("aspect1", this.aspect1.getTag());
/* 25 */     nbt.func_74778_a("aspect2", this.aspect2.getTag());
/* 26 */     return nbt;
/*    */   }
/*    */ 
/*    */   
/*    */   public void deserialize(NBTTagCompound nbt) {
/* 31 */     super.deserialize(nbt);
/* 32 */     this.aspect1 = Aspect.getAspect(nbt.func_74779_i("aspect1"));
/* 33 */     this.aspect2 = Aspect.getAspect(nbt.func_74779_i("aspect2"));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean initialize(EntityPlayer player, ResearchTableData data) {
/* 38 */     Random r = new Random(getSeed());
/* 39 */     int num = MathHelper.func_76136_a(r, 0, Aspect.getCompoundAspects().size() - 1);
/* 40 */     this.aspect1 = (Aspect)Aspect.getCompoundAspects().get(num);
/* 41 */     int num2 = num;
/* 42 */     for (; num2 == num; num2 = MathHelper.func_76136_a(r, 0, Aspect.getCompoundAspects().size() - 1));
/* 43 */     this.aspect2 = (Aspect)Aspect.getCompoundAspects().get(num2);
/* 44 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 49 */   public int getInspirationCost() { return 1; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public String getResearchCategory() { return "ALCHEMY"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 59 */   public String getLocalizedName() { return (new TextComponentTranslation("card.reactions.name", new Object[0])).func_150254_d(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 64 */   public String getLocalizedText() { return (new TextComponentTranslation("card.reactions.text", new Object[] { TextFormatting.BOLD + this.aspect1
/*    */           
/* 66 */           .getName() + TextFormatting.RESET, TextFormatting.BOLD + this.aspect2
/* 67 */           .getName() + TextFormatting.RESET
/* 68 */         })).func_150254_d(); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack[] getRequiredItems() {
/* 73 */     return new ItemStack[] {
/* 74 */         ThaumcraftApiHelper.makeCrystal(this.aspect1), 
/* 75 */         ThaumcraftApiHelper.makeCrystal(this.aspect2)
/*    */       };
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean activate(EntityPlayer player, ResearchTableData data) {
/* 81 */     data.addTotal(getResearchCategory(), 25);
/* 82 */     if (player.func_70681_au().nextFloat() < 0.33D) data.addInspiration(1); 
/* 83 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\research\theorycraft\CardReactions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */