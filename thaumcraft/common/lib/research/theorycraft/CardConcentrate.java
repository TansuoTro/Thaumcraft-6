/*    */ package thaumcraft.common.lib.research.theorycraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.text.TextComponentTranslation;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import thaumcraft.api.ThaumcraftApiHelper;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.research.theorycraft.ResearchTableData;
/*    */ import thaumcraft.api.research.theorycraft.TheorycraftCard;
/*    */ 
/*    */ 
/*    */ public class CardConcentrate
/*    */   extends TheorycraftCard
/*    */ {
/*    */   Aspect aspect;
/*    */   
/*    */   public NBTTagCompound serialize() {
/* 21 */     NBTTagCompound nbt = super.serialize();
/* 22 */     nbt.func_74778_a("aspect", this.aspect.getTag());
/* 23 */     return nbt;
/*    */   }
/*    */ 
/*    */   
/*    */   public void deserialize(NBTTagCompound nbt) {
/* 28 */     super.deserialize(nbt);
/* 29 */     this.aspect = Aspect.getAspect(nbt.func_74779_i("aspect"));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean initialize(EntityPlayer player, ResearchTableData data) {
/* 34 */     Random r = new Random(getSeed());
/* 35 */     int num = r.nextInt(Aspect.getCompoundAspects().size());
/* 36 */     this.aspect = (Aspect)Aspect.getCompoundAspects().get(num);
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 42 */   public int getInspirationCost() { return 1; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 47 */   public String getResearchCategory() { return "ALCHEMY"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 52 */   public String getLocalizedName() { return (new TextComponentTranslation("card.concentrate.name", new Object[0])).func_150254_d(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 57 */   public String getLocalizedText() { return (new TextComponentTranslation("card.concentrate.text", new Object[] { TextFormatting.BOLD + this.aspect
/*    */           
/* 59 */           .getName() + TextFormatting.RESET
/* 60 */         })).func_150254_d(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 65 */   public ItemStack[] getRequiredItems() { return new ItemStack[] { ThaumcraftApiHelper.makeCrystal(this.aspect) }; }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean activate(EntityPlayer player, ResearchTableData data) {
/* 70 */     data.addTotal(getResearchCategory(), 15);
/* 71 */     data.bonusDraws++;
/* 72 */     if (player.func_70681_au().nextFloat() < 0.33D) data.addInspiration(1); 
/* 73 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\research\theorycraft\CardConcentrate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */