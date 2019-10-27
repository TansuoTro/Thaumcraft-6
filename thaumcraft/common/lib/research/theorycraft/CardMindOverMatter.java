/*    */ package thaumcraft.common.lib.research.theorycraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.text.TextComponentTranslation;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ import thaumcraft.api.research.theorycraft.ResearchTableData;
/*    */ import thaumcraft.api.research.theorycraft.TheorycraftCard;
/*    */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*    */ 
/*    */ public class CardMindOverMatter
/*    */   extends TheorycraftCard
/*    */ {
/* 18 */   ItemStack stack = ItemStack.field_190927_a;
/*    */   static ItemStack[] options = { 
/* 20 */       new ItemStack(ItemsTC.visResonator), new ItemStack(ItemsTC.thaumometer), new ItemStack(Blocks.field_150467_bQ), new ItemStack(Blocks.field_150408_cc), new ItemStack(Blocks.field_150367_z), new ItemStack(Blocks.field_150409_cd), new ItemStack(Blocks.field_150381_bn), new ItemStack(Blocks.field_150477_bB), new ItemStack(Blocks.field_150421_aI), new ItemStack(Blocks.field_150453_bW), new ItemStack(Blocks.field_150331_J), new ItemStack(Blocks.field_150438_bZ), new ItemStack(Blocks.field_150320_F), new ItemStack(Items.field_151148_bJ), new ItemStack(Items.field_151111_aL), new ItemStack(Items.field_151142_bV), new ItemStack(Items.field_151132_bS), new ItemStack(Items.field_151113_aN) };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NBTTagCompound serialize() {
/* 34 */     NBTTagCompound nbt = super.serialize();
/* 35 */     nbt.func_74782_a("stack", this.stack.serializeNBT());
/* 36 */     return nbt;
/*    */   }
/*    */ 
/*    */   
/*    */   public void deserialize(NBTTagCompound nbt) {
/* 41 */     super.deserialize(nbt);
/* 42 */     this.stack = new ItemStack(nbt.func_74775_l("stack"));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean initialize(EntityPlayer player, ResearchTableData data) {
/* 47 */     Random r = new Random(getSeed());
/* 48 */     this.stack = options[r.nextInt(options.length)].func_77946_l();
/* 49 */     return (this.stack != null);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 54 */   public int getInspirationCost() { return 1; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 59 */   public String getResearchCategory() { return "ARTIFICE"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 64 */   public String getLocalizedName() { return (new TextComponentTranslation("card.mindmatter.name", new Object[0])).func_150254_d(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 69 */   public String getLocalizedText() { return (new TextComponentTranslation("card.mindmatter.text", new Object[] { Integer.valueOf(getVal()) })).func_150254_d(); }
/*    */ 
/*    */   
/*    */   private int getVal() {
/* 73 */     int q = 10;
/*    */     try {
/* 75 */       q = (int)(q + Math.sqrt(ThaumcraftCraftingManager.getObjectTags(this.stack).visSize()));
/* 76 */     } catch (Exception exception) {}
/* 77 */     return q;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 82 */   public ItemStack[] getRequiredItems() { return new ItemStack[] { this.stack }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 87 */   public boolean[] getRequiredItemsConsumed() { return new boolean[] { true }; }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean activate(EntityPlayer player, ResearchTableData data) {
/* 92 */     data.addTotal(getResearchCategory(), getVal());
/* 93 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\research\theorycraft\CardMindOverMatter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */